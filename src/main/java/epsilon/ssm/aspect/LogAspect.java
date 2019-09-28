package epsilon.ssm.aspect;

import epsilon.ssm.util.LogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LogAspect {

    /**
     * try{
     *      @Before前置通知
     *      method.invoke();
     *      @AfterRunning返回通知
     * }catch(e){
     *      @AfterThrowing：异常通知，
     * }
     * @After
     *
     * 告诉Spring这些放在都在那个方法的哪个位置执行
     * 1）、告诉位置
    [1]@Before：前置通知，在方法执行之前执行
    [2]@After：后置通知，在方法执行最终结束之后执行。
    如果没异常
    [3]@AfterRunning：返回通知，在方法返回结果之后执行
    [4]@AfterThrowing：异常通知，在方法抛出异常之后执行
    1、编写切入点表达式，来告诉spring是切入哪个方法的这个位置
     */
    /**在编写AspectJ切面时，可以直接在通知注解中书写切入点表达式。但同一个切点表达式可能会在多个通知中重复出现。
    在AspectJ切面中，可以通过@Pointcut注解将一个切入点声明成简单的方法。切入点的方法体通常是空的，因为将切入点定义与应用程序逻辑混在一起是不合理的。
    切入点方法的访问控制符同时也控制着这个切入点的可见性。如果切入点要在多个切面中共用，最好将它们集中在一个公共的类中。在这种情况下，它们必须被声明为public。
    在引入这个切入点时，必须将类名也包括在内。如果类没有与这个切面放在同一个包中，还必须包含包名。
    其他通知可以通过方法名称引入该切入点 */

    private LogUtil logger = new LogUtil(LogAspect.class);

    @Pointcut(value = "execution(* epsilon.ssm.service.impl.*.*(..))")
/*
    因为我把controller层、service层的扫描放在不同的地方，而aop只扫描当前配置的xml，所以只扫到了controller层
    我在service层也加了aop扫描，就可以了，或者合在一起配置
    @Pointcut(value = "execution(* epsilon.ssm.controller..*(..))")*/
    public void mypoint() {
    }

    @Before(value = "mypoint()")
    public void logStart(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        String name = joinPoint.getSignature().getName();
       // System.out.println("log Start：开始进入【"+name+"】方法,参数是："+ Arrays.asList(args));
        logger.info("log Start：开始进入【"+name+"】方法,参数是："+ Arrays.asList(args));
    }

    @After(value = "mypoint()")
    public void logAfter(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
       // System.out.println("log after：结束，退出【"+name+"】方法");
        logger.info("log after：结束，退出【"+name+"】方法" );

    }

    @AfterReturning(value = "mypoint()", returning = "res")
    public void logAfterReturning(JoinPoint joinPoint,Object res){
        String name = joinPoint.getSignature().getName();
       // System.out.println("log after returning：【"+name+"】方法返回值是："+ res);
        logger.info("log after returning：【"+name+"】方法返回值是："+ res);
    }

    @AfterThrowing(value = "mypoint()",throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint,Exception e){
       // System.out.println("log after throwing：方法出现异常");
           logger.logException(e);
           logger.info("log after throwing：方法出现异常");
    }
}
