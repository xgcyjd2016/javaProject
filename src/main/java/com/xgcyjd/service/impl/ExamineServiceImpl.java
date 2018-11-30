package com.xgcyjd.service.impl;

import com.xgcyjd.dao.*;
import com.xgcyjd.po.*;
import com.xgcyjd.service.ExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ExamineServiceImpl implements ExamineService {

    @Autowired
    ExamineSystemMapper examineSystemMapper;
    @Autowired
    ExamineRecordMapper examineRecordMapper;
    @Autowired
    ManagerMapper managerMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    SignRecordMapper signRecordMapper;

    @Override
    public void addExamineSystem(ExamineSystem examineSystem) throws Exception {
        examineSystemMapper.insertExamineSystem(examineSystem);
    }

    @Override
    public List<ExamineSystem> getExamineSystem() throws Exception {
        return  examineSystemMapper.getExamineSystem();
    }

    @Override
    public void deleteExamineSystemById(int id) throws Exception {
        examineSystemMapper.deleteExamineSystem(id);
    }

    @Override
    public void addSignRecord(int examiner_stu_id , String time) throws Exception {

            SignReord signReord =new SignReord(examiner_stu_id,time);

            signRecordMapper.insertSignRecord(signReord);
    }

    @Override
    public List<SignReord> getSignRecordByStuId(int stu_id) throws Exception {
        return signRecordMapper.getSignRecordByStuId(stu_id);
    }
    @Override
    public HashMap<String,Object> addExamineRecord(int examiner_stu_id,int examine_system_id) throws Exception {

        HashMap<String,Object> hashMap =new HashMap<>();

       Manager manager =  managerMapper.findManagerByStuId(examiner_stu_id);

       String post=  manager.getPost();

       String[] strings = post.split("|");

       if(strings.length>4){

           hashMap.put("status", 0);
           hashMap.put("message", "权限不够");

           return hashMap;
       }else {

           List<User> usersList = userMapper.getUserByGroup(Integer.parseInt(strings[2]));

           for (int i =0;i<usersList.size();i++) {
               ExamineRecord examineRecord = new ExamineRecord();

               examineRecord.setExaminer_stu_id(examiner_stu_id);
               examineRecord.setExamined_stu_id(usersList.get(i).getStu_id());
               examineRecord.setSystem_id(examine_system_id);

               examineRecordMapper.insertExamineRecord(examineRecord);

           }
           hashMap.put("Status", 1);//成功
           hashMap.put("message","OK");

           return hashMap;
       }


    }
}
