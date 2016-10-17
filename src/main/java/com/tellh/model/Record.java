package com.tellh.model;

import java.sql.Timestamp;

/**
 * Created by tlh on 2016/10/17.
 */
public class Record {
    private int mid;
    private Member member;
    private String time;
    private Timestamp timestamp;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

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

    @Override
    public String toString() {
        return "Record{" +
                "mid=" + mid +
                ", member=" + member +
                ", time='" + time + '\'' +
                '}';
    }
}