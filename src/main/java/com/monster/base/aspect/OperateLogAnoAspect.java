package com.monster.base.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.monster.base.reqAndRsp.Result;
import com.monster.constant.EnumContant;
import com.monster.constant.StaticConstant;
import com.monster.controller.base.BaseController;
import com.monster.model.entity.system.SysOperate;
import com.monster.service.system.SysOperateService;
import com.monster.utils.DataUtil;
import com.monster.utils.NetworkUtil;
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
         * 方法切点（在方法上使用注解@OperateLogAno）
         */
        @Pointcut("@annotation(com.monster.base.annotation.OperateLogAno)")
        public void methodPointCut() { }

        /**
         * controller层 切点（对contrller层所有方法进行切入）
         */
        @Pointcut("execution(* com.monster..controller..*.*(..))")
        public void controllerPointCut() { }

        /**
         * 环绕通知 @Around  ， 当然也可以使用 @Before (前置通知)  @After (后置通知)
         * @param joinPoint
         * @return
         * @throws Throwable
         */
        @Around("controllerPointCut(),methodPointCut()")
        private Object saveControllerOperate(ProceedingJoinPoint joinPoint) throws Throwable  {
                //返回结果
                Object result = joinPoint.proceed();
                try {
                    saveOperate(joinPoint, result);
                }catch (Exception e){}
                finally {
                      return result;
                }
        }

        /**
         * 保存操作日志
         */
        private void saveOperate(ProceedingJoinPoint joinPoint, Object result) throws Exception{
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                //controller 及方法
                String className = joinPoint.getTarget().getClass().getName();
                String methodName = signature.getName();

                //只记录CRUD 操作(并且不记录 系统操作页面的 CRUD 操作)
                if(!EnumContant.SysOperate.OTHER.getName().equals(getOperate(methodName))
                        && !className.contains("SysOperateController")){
                        SysOperate sysOperate = new SysOperate();

                        //控制层及方法名
                        sysOperate.setMethod(className + "." + methodName + "()");

                        //请求的参数
                        Object[] args = joinPoint.getArgs();
                        try{
                            sysOperate.setRequestContext(JSON.toJSONString(args[0]));
                        }catch (Exception e){ }

                        //获取request
                        if(DataUtil.isNotEmptyObj(getRequest())){
                            //请求的方法名
                            sysOperate.setUrl(getRequest().getRequestURI());

                            //设置IP地址
                            sysOperate.setIp(NetworkUtil.getIpAddress(getRequest()));

                            //用户名
                            sysOperate.setName(DataUtil.isNotEmptyObj(getLoginUser()) == true ? getLoginUser().getName() : StaticConstant.UNKNOWN_USER);
                        }

                        //操作类型
                        sysOperate.setType(getOperate(methodName));

                        //操作结果
                        if(DataUtil.isNotEmptyObj(result)){
                            if(String.valueOf(result).startsWith("{") && String.valueOf(result).endsWith("}")){
                                Result resObj = JSONObject.parseObject(String.valueOf(result), Result.class);
                                if(DataUtil.isNotEmptyObj(resObj)){
                                    sysOperate.setResult(resObj.isState() == true ? "成功" : "失败");
                                }
                            }
                        }

                        //保存系统操作记录
                        operateService.save(sysOperate);
                }
        }

    /**
         * 操作类型获取
         */
        private String getOperate(String method){

               if(DataUtil.isEmptyObj(method)){
                   return EnumContant.SysOperate.OTHER.getName();
               }
               /*if(EnumContant.SysOperate.ADDVIEW.getValue().contains(method)){
                   return EnumContant.SysOperate.ADD.getName();
               }else if(EnumContant.SysOperate.EDITVIEW.getValue().contains(method)){
                   return EnumContant.SysOperate.EDITVIEW.getName();
               }*/
               if(EnumContant.SysOperate.ADD.getValue().contains(method)){
                   return EnumContant.SysOperate.ADD.getName();
               }else if(EnumContant.SysOperate.EDIT.getValue().contains(method)){
                   return EnumContant.SysOperate.EDIT.getName();
               }else if(EnumContant.SysOperate.DELETE.getValue().contains(method)){
                   return EnumContant.SysOperate.DELETE.getName();
               }else if(EnumContant.SysOperate.List.getValue().contains(method)){
                   return EnumContant.SysOperate.List.getName();
               }else if(EnumContant.SysOperate.SEARCH.getValue().contains(method)){
                   return EnumContant.SysOperate.SEARCH.getName();
               }
            return EnumContant.SysOperate.OTHER.getName();
        }

}
