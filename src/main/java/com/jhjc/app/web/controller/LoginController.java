package com.jhjc.app.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jhjc.app.domain.ReturnPage;
import com.jhjc.app.domain.SysOperator;
import com.jhjc.app.service.SysOperatorService;
import com.jhjc.app.web.aspect.ApiException;
import com.jhjc.app.web.dto.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private SysOperatorService service;

    @ApiException
    @ResponseBody
    @RequestMapping(value = "loginByOperator",method = RequestMethod.POST)
    public ResponseVo login(@RequestParam Map<String, Object> param) throws Exception {
        PageHelper.startPage((Integer) param.get("pageNum"),(Integer) param.get("pageSize"));
        List<SysOperator> _list = service.select(new SysOperator());
        PageInfo page = new PageInfo(_list);
        ReturnPage _page = new ReturnPage<>();
        _page.setTotal(page.getTotal());
        _page.setDatas(page.getList());
        return  new ResponseVo(_page);



    }
}
