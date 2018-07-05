package com.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class HelloControllerAspect {

    private int executionCount = 0;


    /*
        An example of a @PointCut, and the @Around annotation with parameters
     */

    @Pointcut("execution(* HelloController.hello(String)) && args(name)")
    public void helloPC(String name){}

    @Around(value = "helloPC(name)", argNames = "proceedingJoinPoint,name")
    public Object aroundHello(ProceedingJoinPoint proceedingJoinPoint, String name) throws Throwable {
        log.info(String.format("===> Hello, %s!", name));

        Object object = proceedingJoinPoint.proceed();
        executionCount++;

        log.info(String.format("===> Execution count: %d", executionCount));

        return object;
    }

    /*
        A second @Around on the same method
        - value not using a @PointCut
        - check order of statements
     */

    @Around(value = "execution(* HelloController.hello(String)) && args(name)", argNames = "proceedingJoinPoint,name")
    public Object aroundHelloAgain(ProceedingJoinPoint proceedingJoinPoint, String name) throws Throwable {
        log.info(String.format("==> Hello again, %s!", name));
        Object object = proceedingJoinPoint.proceed();
        log.info("==> Goodbye again");
        return object;
    }

    /*
        Show usage of @Before, @After, @AfterReturning, @AfterThrowing
     */

    @Pointcut("execution(* HelloController.teapot())")
    public void teapotPC(){}

    @Before("teapotPC()")
    public void beforeTeapot() {
        log.info("==> Before teapot");
    }

    @After("teapotPC()")
    public void afterTeapot() {
        log.info("==> Executes regardless of result");
    }

    @AfterReturning("teapotPC()")
    public void afterReturningTeapot() {
        log.info("==> Executes after success");
    }

    @AfterThrowing("teapotPC()")
    public void afterThrowingError() {
        log.info("==> Teapot was thrown");
    }
}
