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
public class MemberDaoTest {
    @Test
    public void getNextTurn() throws Exception {
        System.out.println(memberDao.getNextTurn());
    }

    @Test
    public void addRecordTimes() throws Exception {
        memberDao.addRecordTimes(1);
    }

    @Test
    public void get() throws Exception {
        System.out.println(memberDao.get(1));
    }

    @Autowired
    MemberDao memberDao;

    @Test
    public void testGetAllMembers() {
        System.out.println(memberDao.getAllMembers());
    }
}