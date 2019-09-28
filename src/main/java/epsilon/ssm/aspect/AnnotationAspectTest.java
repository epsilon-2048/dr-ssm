package epsilon.ssm.aspect;

import epsilon.ssm.annotation.MyAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class AnnotationAspectTest {
    /*
        拦截带有Myannotation注解的方法
        使用自定义注解拦截service层要开启gclib
        <aop:config proxy-target-class="true"></aop:config>
     */
    @Pointcut(value = "@annotation(epsilon.ssm.annotation.MyAnnotation)")
    public void myPoint(){}

    @Before("myPoint()")
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
        System.out.println("进入带有MyAnnotation注解的方法前的操作，name："+ myAnnotation.name());
    }

    @After("myPoint()")
    public void after(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
        System.out.println("退出带有MyAnnotation注解的方法后的操作，name："+ myAnnotation.name());
    }
}
