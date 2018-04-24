package com.jhjc.app.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.jhjc.app.common.exception.InvalidException;
import com.jhjc.app.common.utils.HttpUtil;
import com.jhjc.app.domain.TestData;
import com.jhjc.app.service.TestDataService;
import com.jhjc.app.service.dataSource.DbcontextHolder;
import com.jhjc.app.web.aspect.ApiException;
import com.jhjc.app.web.dto.RespFactory;
import com.jhjc.app.web.dto.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestDataService service;
    @RequestMapping(value = "/index")
    public String test() {
        return "test";
    }

    @ApiException
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(HttpServletRequest request,
                    HttpServletResponse response) throws Exception {

        JSONObject param = HttpUtil.getRequestDataJson(request);

        if (param.get("name") == null)
            throw new InvalidException("未上名字！");

        String name = param.getString("name");
        int id = param.getIntValue("id");
        TestData pojo = new TestData();
        pojo.setName(name);
        pojo.setId(id);
        int result = service.insert(pojo);
        HttpUtil.write(response, RespFactory.getByDBResult(result));
    }

    @ApiException
    @ResponseBody
    @RequestMapping(value="/list",method = RequestMethod.POST)
    public ResponseVo list(HttpServletRequest request,HttpServletResponse response) throws Exception {
        JSONObject param = HttpUtil.getRequestDataJson(request);
        String name = param.getString("name");
        List<TestData> _list = service.listByName(name);
        return new ResponseVo(_list);

    }



    //    @ResponseBody
//    @ApiException
//    @RequestMapping(value = "/get", method = RequestMethod.POST)
//    public ResponseVo get(HttpServletRequest request,
//                          HttpServletResponse response) throws Exception {
//
//        JSONObject param = HttpUtil.getRequestDataJson(request);
//
//        if (param.get("name") == null)
//            throw new InvalidException("未上名字！");
//
//        String name = param.getString("name");
//        JHDemo pojo = new JHDemo();
//        pojo.setName(name);
//        List<JHDemo> list = jhDemoService.select(pojo);
//        return new ResponseVo(list);
//    }
}
