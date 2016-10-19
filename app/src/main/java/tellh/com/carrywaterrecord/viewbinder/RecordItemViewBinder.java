package tellh.com.carrywaterrecord.viewbinder;

import android.view.View;
import android.widget.TextView;

import com.tellh.nolistadapter.IListAdapter;
import com.tellh.nolistadapter.viewbinder.base.RecyclerViewBinder;

import tellh.com.carrywaterrecord.R;
import tellh.com.carrywaterrecord.net.Record;

/**
 * Created by tlh on 2016/10/18 :)
 */

public class RecordItemViewBinder extends RecyclerViewBinder<Record, RecordItemViewBinder.ViewHolder> {
    @Override
    public ViewHolder provideViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(IListAdapter iListAdapter, ViewHolder viewHolder, int i, Record record) {
        viewHolder.tvName.setText(record.getMember().getName());
        viewHolder.tvTime.setText(record.getTime());
    }

    @Override
    public int getItemLayoutId(IListAdapter iListAdapter) {
        return R.layout.item_record;
    }

    static class ViewHolder extends RecyclerViewBinder.ViewHolder {
        TextView tvName;
        TextView tvTime;

        ViewHolder(View rootView) {
            super(rootView);
            this.tvName = (TextView) rootView.findViewById(R.id.tv_name);
            this.tvTime = (TextView) rootView.findViewById(R.id.tv_time);
        }

    }
}
