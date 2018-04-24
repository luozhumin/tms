package com.jhjc.app.web.dto;

import com.jhjc.app.common.constants.Constants;

/**
 * Author: yuanhy
 * Time: 2017-6-9  11:12
 * Description:
 */
public class RespFactory {

    public static ResponseVo getByDBResult(int result){
        ResponseVo vo = new ResponseVo(true, "操作成功", 0);
        if (result < Constants.MYBATIS_SUCC_RESULT) {
            vo = new ResponseVo(false, "操作失败", 0);
        }
        return vo;
    }

}