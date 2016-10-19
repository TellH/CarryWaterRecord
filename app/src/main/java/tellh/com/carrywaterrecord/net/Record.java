package tellh.com.carrywaterrecord.net;

import com.tellh.nolistadapter.DataBean;
import com.tellh.nolistadapter.IListAdapter;

import tellh.com.carrywaterrecord.R;

public class Record extends DataBean {
    private int mid;
    private Member member;
    private String time;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Record(int mid) {
        this.mid = mid;
    }

    public Record() {
    }

    @Override
    public String toString() {
        return "Record{" +
                "mid=" + mid +
                ", member=" + member +
                ", time='" + time + '\'' +
                '}';
    }

    @Override
    public int getItemLayoutId(IListAdapter iListAdapter) {
        return R.layout.item_record;
    }
}