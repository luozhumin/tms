package com.jhjc.app.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.jhjc.app.domain.TestData;

public interface TestDataDao {

    int insert(@Param("pojo") TestData pojo);

    int insertList(@Param("pojos") List< TestData> pojo);

    List<TestData> select(@Param("pojo") TestData pojo);

    int update(@Param("pojo") TestData pojo);

    List<TestData> listByName( @Param(value="name") String name);

}
