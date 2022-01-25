package seulki.lee.springexam.controller.login.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    @Around("execution(* *..*.*Controller.*(..))")
    public Object startLog(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("메소드 시작： " + pjp.getSignature());
        try {
            // 메소드 실행
            Object result = pjp.proceed();
            System.out.println("메소드 종료： " + pjp.getSignature());
            return result;
        } catch (Exception e) {
            System.out.println("메소드 이상 종료： " + pjp.getSignature());
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Dao 클래스의 로그 출력용 애스팩트 추가
     */
    @Around("execution(* *..*.*UserDao*.*(..))")
    public Object daoLog(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("메소드 시작： " + pjp.getSignature());
        try {
            Object result = pjp.proceed();
            System.out.println("메소드 종료： " + pjp.getSignature());
            return result;
        } catch (Exception e) {
            System.out.println("메소드 이상 종료： " + pjp.getSignature());
            e.printStackTrace();
            throw e;
        }
    }
}