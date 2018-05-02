package com.jhjc.app.web.controller;

import com.jhjc.app.domain.SysOperator;
import com.jhjc.app.service.SysOperatorService;
import com.jhjc.app.web.aspect.ApiException;
import com.jhjc.app.web.dto.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private SysOperatorService service;

    @ApiException
    @ResponseBody
    @RequestMapping(value = "loginByOperator",method = RequestMethod.POST)
    public ResponseVo add(HttpServletRequest request,
                          HttpServletResponse response) throws Exception {
        return  new ResponseVo(service.select(new SysOperator()));



    }
}
