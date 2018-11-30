package com.xgcyjd.dao;

import com.xgcyjd.po.SignReord;

import java.util.List;

public interface SignRecordMapper {
    public void insertSignRecord(SignReord signReord);
    public List<SignReord> getSignRecordByStuId(int stuId);
}
