package demo.springbootdemo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
    /*
    AOP (Aspect Oriented Programming) 활용

    "실행 시간 측정"은 모든 기능에 붙이고 싶은 공통 관심사인데, 메서드마다 try-finally문으로 시간 측정 로직을 붙이면
    코드도 더러워지고, 적용해야할 범위가 매우 클 때 매우 비효율적인 방식이 됨.

    이럴 때 AOP를 활용하면 핵심 관심사인 각 메서드의 비즈니스 로직은 그대로 둘 수 있음 (덕지덕지 시간 측정 로직 안붙임)
    별도의 AOP 클래스를 만들어 공통 관심사인 시간 측정 로직을 따로 만들고, 적용 범위를 정해 효율적으로 적용할 수 있음
     */

    // @Around 애너태이션으로 적용 대상 지정
    @Around("execution(* demo.springbootdemo..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        /*
        AOP는 컨트롤러로 하여금 실제 Bean이 아닌, AOP가 진짜를 복제해서 만들어낸 가짜(프록시) Bean에 먼저 연결되도록 함
        즉, 실행 시점에 끼어들어와 AOP 로직을 먼저 실행시키고, 그 다음에 실제 Bean으로 연결시켜줌

        끼어드는 시점을 joinPoint, 실제 Bean으로 연결시켜주는 행위를 joinPoint.proceed()로 보면 됨
         */
        long start = System.currentTimeMillis();

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("소요 시간: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
