package com.tellh.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by tlh on 2016/10/17.
 */
@Repository
public class RecordDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate_;

    @Autowired
    private MemberDao memberDao;

    public List<Record> getRecords(int startIndex, int pageSize) {
        List<Record> records = jdbcTemplate_.query("SELECT * FROM carry_water.record ORDER BY rid DESC LIMIT ?,?",
                new BeanPropertyRowMapper<>(Record.class), startIndex, pageSize);
        for (Record record : records) {
            Member member = memberDao.get(record.getMid());
            record.setMember(member);
        }
        return records;
    }

    public int getRecordCount() {
        return jdbcTemplate_.queryForObject("SELECT count(*) FROM carry_water.record", Integer.class);
    }

    @Transactional
    public void insert(Record record) {
        record.setTimestamp(new Timestamp(System.currentTimeMillis()));
        memberDao.addRecordTimes(record.getMid());
        jdbcTemplate.update("INSERT INTO carry_water.record(mid, time) VALUES (:mid,:timestamp)",
                new BeanPropertySqlParameterSource(record));
    }

    public List<Record> getRecordsByMid(int mid) {
        List<Record> records = jdbcTemplate_.query("SELECT * FROM record WHERE mid=?", new BeanPropertyRowMapper<>(Record.class), mid);
        Member member = memberDao.get(mid);
        for (Record record : records) {
            record.setMember(member);
        }
        return records;
    }
}
