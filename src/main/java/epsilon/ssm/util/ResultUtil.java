package epsilon.ssm.util;

import java.security.PublicKey;
import java.util.HashMap;

public class ResultUtil extends HashMap<String,Object> {

    private ResultUtil(){}

    public static ResultUtil buildSuccess(){
        return buildAny(ResultType.SUCCESS);
    }

    public static ResultUtil buildAny(ResultType type){
        return new ResultUtil()
                .append("code",type.getCode())
                .append("msg",type.getMsg());
    }

    public ResultUtil append(String key, Object value){
        this.put(key,value);
        return this;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(ResultUtil.buildSuccess().append("n","b"));
    }

}
