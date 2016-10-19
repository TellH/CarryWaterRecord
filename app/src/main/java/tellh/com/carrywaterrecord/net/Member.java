package tellh.com.carrywaterrecord.net;

import com.tellh.nolistadapter.DataBean;
import com.tellh.nolistadapter.IListAdapter;

import tellh.com.carrywaterrecord.R;

public class Member extends DataBean {
    private int id;
    private String name;
    private int times;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", times=" + times +
                '}';
    }

    @Override
    public int getItemLayoutId(IListAdapter iListAdapter) {
        return R.layout.header_next_turn;
    }
}