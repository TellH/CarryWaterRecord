package com.tellh.controller;

import com.tellh.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tlh on 2016/10/18.
 *
 */
@Controller
public class MainController {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private RecordDao recordDao;

    @ResponseBody
    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public List<Member> getMembers() {
        return memberDao.getAllMembers();
    }

    @ResponseBody
    @RequestMapping(value = "/records", method = RequestMethod.GET)
    public List<Record> getRecords(@RequestParam(required = false, defaultValue = "1", value = "page") int pageIndex,
                                   @RequestParam(required = false, defaultValue = "10") int pageSize) {
        Page page = new Page(pageIndex, recordDao.getRecordCount(), pageSize);
        return recordDao.getRecords(page.getStartIndex(), pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "/member/turn", method = RequestMethod.GET)
    public Member getNextTurn() {
        return memberDao.getNextTurn();
    }

    @ResponseBody
    @RequestMapping(value = "/records", method = RequestMethod.POST)
    public Message addRecord(@RequestParam int mid) {
        Message message = new Message();
        try {
            recordDao.insert(new Record(mid));
            message.setOk(true);
            message.setMessage("打卡成功！");
        } catch (Exception e) {
            message.setOk(false);
            message.setMessage("打卡失败！");
        }
        return message;
    }

}
