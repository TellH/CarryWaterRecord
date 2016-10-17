package com.tellh.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tlh on 2016/10/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:BeanAppContext.xml"})
public class RecordDaoTest {
    @Test
    public void getRecordsByMid() throws Exception {
        System.out.println(memberDao.getRecordsByMid(1));
    }

    @Test
    public void insert() throws Exception {
        Record record = new Record();
        record.setMid(1);
        memberDao.insert(record);
    }

    @Autowired
    RecordDao memberDao;

    @Test
    public void getAllRecord() throws Exception {
        System.out.println(memberDao.getAllRecord());
    }

}