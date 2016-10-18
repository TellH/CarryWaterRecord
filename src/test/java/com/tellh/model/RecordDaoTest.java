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
        for (int i = 1; i <= 4; i++) {
            Record record = new Record();
            record.setMid(i);
            memberDao.insert(record);
        }
    }

    @Autowired
    RecordDao memberDao;

    @Test
    public void getAllRecord() throws Exception {
        int pageIndex = 1;
        int pageSize = 3;
        Page page = new Page(pageIndex, memberDao.getRecordCount(),
                pageSize);
        System.out.println(memberDao.getRecords(page.getStartIndex(), pageSize));
    }

}