package epsilon.ssm.exceptionHandler;

import epsilon.ssm.exceptionHandler.myException.NoPermissionException;
import epsilon.ssm.exceptionHandler.myException.UnauthorizedException;
import epsilon.ssm.util.ResultType;
import epsilon.ssm.util.ResultUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Component  //注意要在spring-mvc增加扫描器
public class GlobleExtHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultUtil handlerException(Exception e, HttpServletRequest request) {

        if (e instanceof UnauthorizedException){
            //....
            return ResultUtil.buildAny(ResultType.UNAUTHORIZED);
        } else if (e instanceof NoPermissionException){

            return ResultUtil.buildAny(ResultType.NO_PERMISSION);
        } else {
            e.printStackTrace();
        }
        return ResultUtil.buildAny(ResultType.ERROR);
    }
}
