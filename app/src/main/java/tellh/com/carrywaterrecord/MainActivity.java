package tellh.com.carrywaterrecord;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.tellh.nolistadapter.DataBean;
import com.tellh.nolistadapter.adapter.FooterLoadMoreAdapterWrapper;
import com.tellh.nolistadapter.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tellh.com.carrywaterrecord.net.Member;
import tellh.com.carrywaterrecord.net.Message;
import tellh.com.carrywaterrecord.net.NetWorkHelper;
import tellh.com.carrywaterrecord.net.Record;
import tellh.com.carrywaterrecord.viewbinder.LoadMoreFooterViewBinder;
import tellh.com.carrywaterrecord.viewbinder.NextTurnViewBinder;
import tellh.com.carrywaterrecord.viewbinder.RecordItemViewBinder;

import static com.tellh.nolistadapter.adapter.FooterLoadMoreAdapterWrapper.LOAD_MORE;
import static com.tellh.nolistadapter.adapter.FooterLoadMoreAdapterWrapper.REFRESH;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, FooterLoadMoreAdapterWrapper.OnReachFooterListener {

    private List<String> memberList;
    private MaterialDialog dialogRecord;
    private RecyclerView recyclerview;
    private SwipeRefreshLayout refreshLayout;
    private Member nextTurnMember;
    private FooterLoadMoreAdapterWrapper loadMoreWrapper;
    private View mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initRecyclerView();

        memberList = Arrays.asList("王先宇 ", "谭乐华 ", "骆宁 ", "徐夏磊 ");
        initRecordDialog();
        initMemberList();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogRecord.show();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshRecord();
            }
        }, 100);
    }

    private void initRecyclerView() {
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        loadMoreWrapper = (FooterLoadMoreAdapterWrapper) RecyclerViewAdapter.builder()
                .addItemType(new NextTurnViewBinder())
                .addItemType(new RecordItemViewBinder())
                .setLoadMoreFooter(new LoadMoreFooterViewBinder(), recyclerview, this)
                .build();
        recyclerview.setAdapter(loadMoreWrapper);
    }

    private void initMemberList() {
        NetWorkHelper.getClient().listAllMembers().enqueue(new Callback<List<Member>>() {
            @Override
            public void onResponse(Call<List<Member>> call, Response<List<Member>> response) {
                List<Member> members = response.body();
                if (members != null && !members.isEmpty()) {
                    for (int i = 0; i < members.size(); i++) {
                        Member member = members.get(i);
                        memberList.set(i, member.getName() + " 搬了 " + member.getTimes() + " 次");
                    }
                }
                dialogRecord.setItems(listToArray());
            }

            @Override
            public void onFailure(Call<List<Member>> call, Throwable t) {
            }
        });
    }

    private void initRecordDialog() {
        dialogRecord = new MaterialDialog.Builder(this)
                .title("搬水请打卡")
                .items(memberList)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, final View view, int which, CharSequence text) {
                        Snackbar.make(mainView, "正在打卡...", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        NetWorkHelper.getClient().addRecord(which + 1).enqueue(new Callback<Message>() {
                            @Override
                            public void onResponse(Call<Message> call, Response<Message> response) {
                                Message message = response.body();
                                if (message != null) {
                                    Snackbar.make(mainView, message.getMessage(), Snackbar.LENGTH_SHORT)
                                            .setAction("Action", null).show();
                                    refreshRecord();
                                } else
                                    Snackbar.make(mainView, "打卡失败！", Snackbar.LENGTH_SHORT)
                                            .setAction("Action", null).show();
                            }

                            @Override
                            public void onFailure(Call<Message> call, Throwable t) {
                                Snackbar.make(mainView, "打卡失败！", Snackbar.LENGTH_SHORT)
                                        .setAction("Action", null).show();
                            }
                        });
                        return true;
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                        materialDialog.dismiss();
                    }
                })
                .positiveText("OK")
                .negativeText("Cancel")
                .build();
    }

    private String[] listToArray() {
        if (memberList.size() > 0) {
            final String[] array = new String[memberList.size()];
            int i = 0;
            for (Object obj : memberList) {
                array[i] = obj.toString();
                i++;
            }
            return array;
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Snackbar.make(mainView, "别点了，并没有什么好设置的～", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        mainView = findViewById(R.id.container);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        refreshRecord();
    }

    private void refreshRecord() {
        if (!refreshLayout.isRefreshing())
            refreshLayout.setRefreshing(true);
//        getRecords();
        initMemberList();
        NetWorkHelper.getClient().getNextTurn().enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                closeRefresh();
                Member member = response.body();
                if (member == null) {
                    return;
                }
                nextTurnMember = member;
                if (loadMoreWrapper.getDisplayList().isEmpty())
                    loadMoreWrapper.add(0, nextTurnMember);
                else {
                    Member oldMember = (Member) loadMoreWrapper.getDisplayList().get(0);
                    oldMember.setName(nextTurnMember.getName());
                    oldMember.setId(nextTurnMember.getId());
                    oldMember.setTimes(nextTurnMember.getTimes());
                }
                loadMoreWrapper.notifyItemChanged(0);
                getRecords();
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                closeRefresh();
                Toast.makeText(MainActivity.this, "无法获取数据", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getRecords() {
        NetWorkHelper.getClient().listRecords(1, 10).enqueue(new Callback<List<Record>>() {
            @Override
            public void onResponse(Call<List<Record>> call, Response<List<Record>> response) {
                closeRefresh();
                List<Record> records = response.body();
                if (records == null) {
                    Toast.makeText(MainActivity.this, "无法获取数据", Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<DataBean> list = new ArrayList<>(records.size() + 1);
                list.add(nextTurnMember);
                list.addAll(records);
                loadMoreWrapper.OnGetData(list, REFRESH);
            }

            @Override
            public void onFailure(Call<List<Record>> call, Throwable t) {
                closeRefresh();
                Toast.makeText(MainActivity.this, "无法获取数据", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onToLoadMore(int curPage) {
        NetWorkHelper.getClient().listRecords(curPage + 1, 10).enqueue(new Callback<List<Record>>() {
            @Override
            public void onResponse(Call<List<Record>> call, Response<List<Record>> response) {
                List<Record> records = response.body();
                if (records == null) {
                    Toast.makeText(MainActivity.this, "无法获取数据", Toast.LENGTH_SHORT).show();
                    return;
                }
                loadMoreWrapper.OnGetData(records, LOAD_MORE);
            }

            @Override
            public void onFailure(Call<List<Record>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "无法获取数据", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void closeRefresh() {
        refreshLayout.setRefreshing(false);
    }
}
