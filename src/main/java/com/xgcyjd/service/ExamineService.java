package com.xgcyjd.service;

import com.xgcyjd.po.ExamineRecord;
import com.xgcyjd.po.ExamineSystem;
import com.xgcyjd.po.SignReord;

import java.util.HashMap;
import java.util.List;

public interface ExamineService {
    public void addExamineSystem(ExamineSystem examineSystem) throws Exception;
    public List<ExamineSystem> getExamineSystem() throws Exception;
    public void deleteExamineSystemById(int id) throws Exception;
    public HashMap addExamineRecord(int examiner_stu_id ,int examine_system_id) throws Exception;
    public void addSignRecord(int examiner_stu_id, String time) throws Exception;
    public List<SignReord> getSignRecordByStuId(int stu_id) throws  Exception;
}
