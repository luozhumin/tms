package com.jhjc.app.web.aspect;

import com.jhjc.app.common.exception.InvalidException;
import com.jhjc.app.common.utils.HttpUtil;
import com.jhjc.app.web.dto.ResponseVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

/**
 * Author: yuanhy
 * Time: 2017-6-3  11:09
 * Description:
 */

@Aspect
public class ApiExceptionAspect {

    private Logger log = LoggerFactory.getLogger(ApiExceptionAspect.class);

    @Around("@annotation(com.jhjc.app.web.aspect.ApiException)")
    public Object apiCheck(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (InvalidException e) {
            writeErrorMsg(joinPoint, e.getMessage());
            log.error("请求" + joinPoint.getSignature().getName() + "失败，失败原因为:" + e.getMessage());
        } catch (Throwable e) {
            writeErrorMsg(joinPoint, "未知异常");
            log.error("请求" + joinPoint.getSignature().getName() + "失败", e);
        }
        return result;
    }

    private void writeErrorMsg(ProceedingJoinPoint joinPoint, String errorMsg) {
        HttpServletResponse response = null;
        for (Object o : joinPoint.getArgs()) {
            if (o instanceof HttpServletResponse) {
                response = (HttpServletResponse) o;
                break;
            }
        }
        if (response != null) {
            HttpUtil.write(response, new ResponseVo(false, errorMsg));
        }
    }


}