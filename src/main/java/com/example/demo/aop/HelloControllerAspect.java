package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HelloControllerAspect {

    private Logger Logger = LoggerFactory.getLogger(HelloControllerAspect.class);

    private int executionCount = 0;


    /*
        An example of a @PointCut, and the @Around annotation with parameters
     */

    @Pointcut("execution(* HelloController.hello(String)) && args(name)")
    public void helloPC(String name){}

    @Around(value = "helloPC(name)", argNames = "proceedingJoinPoint,name")
    public Object aroundHello(ProceedingJoinPoint proceedingJoinPoint, String name) throws Throwable {
        Logger.info(String.format("===> Hello, %s!", name));

        Object object = proceedingJoinPoint.proceed();
        executionCount++;

        Logger.info(String.format("===> Execution count: %d", executionCount));

        return object;
    }

    /*
        A second @Around on the same method
        - value not using a @PointCut
        - check order of statements
     */

    @Around(value = "execution(* HelloController.hello(String)) && args(name)", argNames = "proceedingJoinPoint,name")
    public Object aroundHelloAgain(ProceedingJoinPoint proceedingJoinPoint, String name) throws Throwable {
        Logger.info(String.format("==> Hello again, %s!", name));
        Object object = proceedingJoinPoint.proceed();
        Logger.info("==> Goodbye again");
        return object;
    }

    /*
        Show usage of @Before, @After, @AfterReturning, @AfterThrowing
     */

    @Pointcut("execution(* HelloController.teapot())")
    public void teapotPC(){}

    @Before("teapotPC()")
    public void beforeTeapot() {
        Logger.info("==> Before teapot");
    }

    @After("teapotPC()")
    public void afterTeapot() {
        Logger.info("==> Executes regardless of result");
    }

    @AfterReturning("teapotPC()")
    public void afterReturningTeapot() {
        Logger.info("==> Executes after success");
    }

    @AfterThrowing("teapotPC()")
    public void afterThrowingError() {
        Logger.info("==> Teapot was thrown");
    }
}
