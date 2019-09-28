package epsilon.ssm.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class ControllerAsperctTest {

    @Pointcut(value = "execution(* epsilon.ssm.controller..*(..))")
    public void mypoint() {
    }

    @Before(value = "mypoint()")
    public void before(JoinPoint joinPoint){
         System.out.println("进入Controller前的操作");
    }

    @After(value = "mypoint()")
    public void after(JoinPoint joinPoint){
        System.out.println("退出Controller后的操作");
    }

}
