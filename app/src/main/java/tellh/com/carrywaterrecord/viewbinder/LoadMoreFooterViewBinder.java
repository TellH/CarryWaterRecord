package tellh.com.carrywaterrecord.viewbinder;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tellh.nolistadapter.IListAdapter;
import com.tellh.nolistadapter.adapter.FooterLoadMoreAdapterWrapper;
import com.tellh.nolistadapter.viewbinder.base.RecyclerViewBinder;
import com.tellh.nolistadapter.viewbinder.sub.FooterRecyclerViewBinder;

import tellh.com.carrywaterrecord.R;

import static com.tellh.nolistadapter.adapter.FooterLoadMoreAdapterWrapper.LOADING;
import static com.tellh.nolistadapter.adapter.FooterLoadMoreAdapterWrapper.NO_MORE;
import static com.tellh.nolistadapter.adapter.FooterLoadMoreAdapterWrapper.PULL_TO_LOAD_MORE;

public class LoadMoreFooterViewBinder extends FooterRecyclerViewBinder<LoadMoreFooterViewBinder.ViewHolder> {
    private String toLoadText = "上拉加载更多";
    private String noMoreText = "没有更多记录了";
    private String loadingText = "正在拼命加载...";

    @Override
    protected void bindFooter(IListAdapter adapter, ViewHolder holder, int position) {
        FooterLoadMoreAdapterWrapper adapterWrapper = (FooterLoadMoreAdapterWrapper) adapter;
        int size = adapter.getDisplayList().size();
        if (size < 10) {
            holder.tvFooter.setText(noMoreText);
            holder.progressBar.setVisibility(View.INVISIBLE);
            return;
        }
        switch (adapterWrapper.getFooterStatus()) {
            case PULL_TO_LOAD_MORE:
                holder.progressBar.setVisibility(View.VISIBLE);
                holder.tvFooter.setText(toLoadText);
                break;
            case LOADING:
                holder.progressBar.setVisibility(View.VISIBLE);
                holder.tvFooter.setText(loadingText);
                break;
            case NO_MORE:
                holder.tvFooter.setText(noMoreText);
                holder.progressBar.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    public ViewHolder provideViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemLayoutId(IListAdapter adapter) {
        return R.layout.footer_load_more;
    }

    class ViewHolder extends RecyclerViewBinder.ViewHolder {
        TextView tvFooter;
        ProgressBar progressBar;

        ViewHolder(View rootView) {
            super(rootView);
            this.tvFooter = (TextView) rootView.findViewById(R.id.tv_footer);
            this.progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        }

    }

}