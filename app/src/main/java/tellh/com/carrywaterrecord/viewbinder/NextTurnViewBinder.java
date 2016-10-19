package tellh.com.carrywaterrecord.viewbinder;

import android.view.View;
import android.widget.TextView;

import com.tellh.nolistadapter.IListAdapter;
import com.tellh.nolistadapter.viewbinder.base.RecyclerViewBinder;

import tellh.com.carrywaterrecord.R;
import tellh.com.carrywaterrecord.net.Member;

/**
 * Created by tlh on 2016/10/18 :)
 */

public class NextTurnViewBinder extends RecyclerViewBinder<Member, NextTurnViewBinder.ViewHolder> {
    @Override
    public ViewHolder provideViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(IListAdapter iListAdapter, ViewHolder viewHolder, int pos, Member member) {
        viewHolder.tvNextTurn.setText(member.getName());
    }

    @Override
    public int getItemLayoutId(IListAdapter iListAdapter) {
        return R.layout.header_next_turn;
    }

    static class ViewHolder extends RecyclerViewBinder.ViewHolder {
        TextView tvNextTurn;

        ViewHolder(View rootView) {
            super(rootView);
            this.tvNextTurn = (TextView) rootView.findViewById(R.id.tv_next_turn);
        }

    }

//    class ViewHolder extends RecyclerViewBinder.ViewHolder {
//
//    }
}
