package com.monster.base.exception;

import com.monster.base.reqAndRsp.Result;
import com.monster.constant.StaticConstant;
import com.monster.controller.base.BaseController;
import com.monster.model.entity.system.SysError;
import com.monster.service.system.SysErrorService;
import com.monster.utils.DataUtil;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler extends BaseController {

    @Autowired
    private SysErrorService sysErrorService;

    /**
     * 用来处理bean validation异常
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public String resolveConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request){
        ex.printStackTrace();;
        sysErrorService.save(createSysError(ex,request));

        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if(!CollectionUtils.isEmpty(constraintViolations)){
            StringBuilder msgBuilder = new StringBuilder();
            for(ConstraintViolation constraintViolation :constraintViolations){
                msgBuilder.append(constraintViolation.getMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if(errorMessage.length()>1){
                errorMessage = errorMessage.substring(0,errorMessage.length()-1);
            }
            return Result.error(errorMessage);
        }
        
        return Result.error(ex.getMessage());
    }

    /**
     * 用来处理方法 validation异常
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public String resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request){
        ex.printStackTrace();;
        sysErrorService.save(createSysError(ex,request));

        List<ObjectError>  objectErrors = ex.getBindingResult().getAllErrors();
        if(!CollectionUtils.isEmpty(objectErrors)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                msgBuilder.append(objectError.getDefaultMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            return Result.error(errorMessage);
        }
        
        return Result.error(ex.getMessage());
    }

    /**
     * 用来系统其它异常
     * @param ex
     * @return
     */
   @ExceptionHandler(Exception.class)
   @ResponseBody
    public String exceptionHandler(Exception ex, HttpServletRequest request){
        ex.printStackTrace();
        sysErrorService.save(createSysError(ex,request));

        return Result.error(ex.getMessage());
    }

    /**
     * 组装异常信息
     * @param ex
     * @return
     */
    private SysError createSysError(Exception ex, HttpServletRequest request){

              SysError sysError = new SysError();
                sysError.setName(DataUtil.isNotEmptyObj(getLoginUser()) == true ? getLoginUser().getName() : StaticConstant.UNKNOWN_USER);
                sysError.setUrl(request.getRequestURI());
                sysError.setMsg(ex.getMessage());

        return sysError;
    }

}
