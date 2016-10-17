package com.tellh.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tlh on 2016/10/17.
 */
@Repository
public class MemberDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Member get(int mid) {
        return jdbcTemplate.queryForObject("SELECT * FROM carry_water.member WHERE id=?", new BeanPropertyRowMapper<>(Member.class), mid);
    }

    public List<Member> getAllMembers() {
        return jdbcTemplate.query("SELECT * FROM carry_water.member", new BeanPropertyRowMapper<>(Member.class));
    }

    public void addRecordTimes(int mid) {
        jdbcTemplate.update("UPDATE carry_water.member SET times=times+1 WHERE id=?", mid);
    }
}
