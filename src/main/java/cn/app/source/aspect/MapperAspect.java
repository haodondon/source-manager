package cn.app.source.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 *  @author: hdd
 *  @Date: 2020/11/22
 *  @Description: 统计sql执行时间
 */

@Aspect
@Component
@Slf4j
public class MapperAspect {

    /**
     * 监控com.lsj.xcjfs.dao..*Mapper包及其子包的所有public方法
     */
    @Pointcut("execution(* cn.app.source.mapper.*Mapper.*(..))")
    private void pointCutMethod() {
    }

    /**
     * 声明环绕通知
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.nanoTime();
        Object obj = pjp.proceed();
        long end = System.nanoTime();

        log.info("\n------------------------------------------------------------------------------------------------\n调用Mapper方法：{}，执行耗时：{}毫秒\n------------------------------------------------------------------------------------------------",
                pjp.getSignature().toString(),
                (end - begin) / 1000000 );
        return obj;
    }
}