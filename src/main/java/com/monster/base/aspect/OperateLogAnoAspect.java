package com.monster.base.aspect;

import com.alibaba.fastjson.JSON;
import com.monster.base.annotation.OperateLogAno;
import com.monster.constant.StaticConstant;
import com.monster.controller.base.BaseController;
import com.monster.model.entity.system.SysOperate;
import com.monster.service.system.SysOperateService;
import com.monster.utils.DataUtil;
import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @Date 2019/6/19
 * @see
 * @系统操作日志记录 切面处理类
 */
@Aspect
@Component
public class OperateLogAnoAspect extends BaseController {

        @Autowired
        private SysOperateService operateService;

        /**
         * 切点
         */
        @Pointcut("@annotation(com.monster.base.annotation.OperateLogAno)")
        public void logPointCut() { }



        /**
         * 环绕通知 @Around  ， 当然也可以使用 @Before (前置通知)  @After (后置通知)
         * @param joinPoint
         * @return
         * @throws Throwable
         */
        @Around("logPointCut()")
        private Object saveSysLog(ProceedingJoinPoint joinPoint) throws Throwable  {

                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                Method method = signature.getMethod();

                SysOperate sysOperate = new SysOperate();
                OperateLogAno operateLogAno = method.getAnnotation(OperateLogAno.class);
                if(operateLogAno != null){
                    //注解上的描述
                    sysOperate.setMethod(operateLogAno.value());
                }

                //请求的方法名
                String className = joinPoint.getTarget().getClass().getName();
                String methodName = signature.getName();
                sysOperate.setUrl(className + "." + methodName + "()");

                //请求的参数
                Object[] args = joinPoint.getArgs();
                try{
                    sysOperate.setRequestContext(JSON.toJSONString(args[0]));
                }catch (Exception e){ }

                //获取request
                if(DataUtil.isNotEmptyObj(getRequest())){
                    //设置IP地址
                    sysOperate.setIp(getRequest().getRemoteHost());

                    //用户名
                    sysOperate.setName(DataUtil.isNotEmptyObj(getLoginUser()) == true ? getLoginUser().getName() : StaticConstant.UNKNOWN_USER);
                }

                //返回结果
                Object result = joinPoint.proceed();
                sysOperate.setResponseContext(JSON.toJSONString(result));

                //保存系统操作记录
                operateService.save(sysOperate);

                return result;
            }
}
