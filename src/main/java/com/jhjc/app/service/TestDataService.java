package com.jhjc.app.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.jhjc.app.domain.TestData;
import com.jhjc.app.dao.TestDataDao;

@Service
public class TestDataService {

    @Resource
    private TestDataDao testDataDao;

    public int insert(TestData pojo){
        return testDataDao.insert(pojo);
    }

    public int insertList(List< TestData> pojos){
        return testDataDao.insertList(pojos);
    }

    public List<TestData> select(TestData pojo){
        return testDataDao.select(pojo);
    }

    public int update(TestData pojo){
        return testDataDao.update(pojo);
    }

    public  List<TestData> listByName(  String name){
        return  testDataDao.listByName(name);
    }

}
