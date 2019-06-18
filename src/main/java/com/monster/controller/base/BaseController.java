package com.monster.controller.base;

import com.monster.constant.StaticConstant;
import com.monster.model.entity.system.SysUser;
import com.monster.utils.DataUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.*;

/**
 * @author Administrator
 * @Date 2019/6/18
 * @Describe 控制层父类
 * @see
 */

@Component
public class BaseController {

        private ServletRequestAttributes servletRequestAttributes;

      /**
       * 获取 ServletRequestAttributes
       */
      public Boolean getAttributes(){
               if(DataUtil.isEmptyObj(servletRequestAttributes)){
                   servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
               }
               if(DataUtil.isNotEmptyObj(servletRequestAttributes)){
                   return true;
               }
             return false;
        }

        /**
         * 获取 session 中的值
         */
        public Object getSessionValue(String key){

                if(getAttributes()){
                    return servletRequestAttributes.getAttribute(key, RequestAttributes.SCOPE_SESSION);
                }
               return null;
        }

        /**
         * 设置 session 中的值
         */
        public Boolean setSessionValue(String key, Object value){

            if(getAttributes()){
                servletRequestAttributes.setAttribute(key, value, RequestAttributes.SCOPE_SESSION);
                return true;
            }
            return false;
        }

        /**
         * 获取登录用户
         * @return
         */
        public SysUser getLoginUser(){

                Object o = getSessionValue(StaticConstant.LOGIN_USER);
                if(DataUtil.isNotEmptyObj(o)){
                    SysUser user = (SysUser)o;
                    return user;
                }
                return null;
        }

}
