package com.jhjc.app.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.jhjc.app.domain.SysOperator;

public interface SysOperatorDao {

    int insert(@Param("pojo") SysOperator pojo);

    int insertList(@Param("pojos") List< SysOperator> pojo);

    List<SysOperator> select(@Param("pojo") SysOperator pojo);

    int update(@Param("pojo") SysOperator pojo);

}
