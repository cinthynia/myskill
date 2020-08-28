package com.my.logdemo.aop;

import com.google.gson.Gson;
import com.my.logdemo.annotation.WebLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 切面类
 * @author 3897
 */
@Aspect
@Slf4j
@Component
public class WebLogAspect {
    //换行符
    private final static String LINE_SEPARATOR = System.lineSeparator();

    /**
     * 声明自定义@WebLog注解为切点
     */
    @Pointcut("execution (* com.my.logdemo.controller.*.*(..))")
    public void webLog(){}

    /**
     * 环绕
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        //打印出参
        log.info("Response Args : {}",new Gson().toJson(result));
        //执行耗时
        log.info("Time-Consuming : {} ms",System.currentTimeMillis()-startTime);
        return result;
    }

    /**
     * 在切点前织入
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        //开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //获取@WebLog注解的描述信息
       // String methodDescription = getAspectLogDescription(joinPoint);

        //打印相关请求参数
        log.info("=============== Start ===============");
        // 打印请求 url
        log.info("URL            : {}", request.getRequestURL().toString());
        // 打印描述信息
       // log.info("Description    : 请求了[{}]接口", methodDescription);
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        log.info("Request Args   : {}", new Gson().toJson(joinPoint.getArgs()));
    }

    /**
     * 在切点之后织入
     */
    @After("webLog()")
    public void doAfter() throws Throwable{
        //接口结束后换行，方便分割查看
        log.info("=============== End ===============" + LINE_SEPARATOR);
    }

    /**
     * 获取切面注解的描述
     */
    public String getAspectLogDescription(JoinPoint joinpoint) throws Exception{
        String targetName = joinpoint.getTarget().getClass().getName();
        String methodName = joinpoint.getSignature().getName();
        Object[] arguments = joinpoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(WebLog.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }
}
