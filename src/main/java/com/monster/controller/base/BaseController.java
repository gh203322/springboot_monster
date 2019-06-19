package com.monster.controller.base;

import com.monster.constant.StaticConstant;
import com.monster.model.entity.system.SysUser;
import com.monster.utils.DataUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        private HttpServletRequest request;

        private HttpServletResponse response;

      /**
       * 获取 ServletRequestAttributes
       */
      public ServletRequestAttributes getServletRequestAttributes(){
               if(DataUtil.isEmptyObj(servletRequestAttributes)){
                   servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
               }
               if(DataUtil.isNotEmptyObj(servletRequestAttributes)){
                   return servletRequestAttributes;
               }
             return null;
        }

        /**
         * 获取 session 中的值
         */
        public Object getSessionValue(String key){

                getServletRequestAttributes();
                if(DataUtil.isNotEmptyObj(servletRequestAttributes)){
                    return servletRequestAttributes.getAttribute(key, RequestAttributes.SCOPE_SESSION);
                }
               return null;
        }

        /**
         * 设置 session 中的值
         */
        public Boolean setSessionValue(String key, Object value){

            getServletRequestAttributes();
            if(DataUtil.isNotEmptyObj(servletRequestAttributes)){
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

    /**
     * 获取 HttpServletRequest
     */
    public HttpServletRequest getRequest(){

            getServletRequestAttributes();
            if(DataUtil.isNotEmptyObj(servletRequestAttributes)){
                if(DataUtil.isEmptyObj(request)){
                     request = servletRequestAttributes.getRequest();
                }
                return request;
            }
            return null;
    }

    /**
     * 获取 HttpServletRequest
     */
    public HttpServletResponse getResponse(){

        getServletRequestAttributes();
        if(DataUtil.isNotEmptyObj(servletRequestAttributes)){
            if(DataUtil.isEmptyObj(response)){
                response = servletRequestAttributes.getResponse();
            }
            return response;
        }
        return null;
    }

}
