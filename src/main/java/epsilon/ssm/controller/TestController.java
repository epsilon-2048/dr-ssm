package epsilon.ssm.controller;

import epsilon.ssm.annotation.MyAnnotation;
import epsilon.ssm.service.TestService;
import epsilon.ssm.util.JsonUtil;
import epsilon.ssm.util.LogUtil;
import epsilon.ssm.util.ResultUtil;
import epsilon.ssm.util.StringRedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@Api
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    StringRedisCache cache;

    private LogUtil logger = new LogUtil(TestController.class);
    private static final String CACHE_PERFIX = "TEST_PAGE_";

    @RequestMapping(value = "welcome")
    @ResponseBody
    @ApiOperation(value = "test")
    @MyAnnotation(name = "这是在controller层注解的方法")
    public String test(){
        return "welcome to spring";
    }


    @RequestMapping(value = "test/{pn}")
    @ResponseBody
    public ResultUtil test1(@PathVariable int pn) throws IOException {
/*        String json = cache.get(CACHE_PERFIX+pn);
        if (json != null){
            System.out.println(json);
            return JsonUtil.string2Obj(json,ResultUtil.class);
        }else{
            ResultUtil resultUtil = ResultUtil.buildSuccess().append("data",testService.select(pn));
            cache.set(CACHE_PERFIX+pn,JsonUtil.obj2String(resultUtil));
            return resultUtil;
        }*/
        return ResultUtil.buildSuccess().append("data",testService.select(pn));
    }


}
