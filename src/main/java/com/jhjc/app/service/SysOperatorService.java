package com.jhjc.app.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.jhjc.app.domain.SysOperator;
import com.jhjc.app.dao.SysOperatorDao;

@Service
public class SysOperatorService {

    @Resource
    private SysOperatorDao sysOperatorDao;

    public int insert(SysOperator pojo){
        return sysOperatorDao.insert(pojo);
    }

    public int insertList(List< SysOperator> pojos){
        return sysOperatorDao.insertList(pojos);
    }

    public List<SysOperator> select(SysOperator pojo){
        return sysOperatorDao.select(pojo);
    }

    public int update(SysOperator pojo){
        return sysOperatorDao.update(pojo);
    }

}
