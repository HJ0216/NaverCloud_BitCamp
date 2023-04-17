package sample01;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class LoggingAdvice {
	// LoggingAdvice: 공통관심사항, 중복된 코드 작성
	
	public void beforeTrace() {
		System.out.println("Before Trace");
	}

	public void afterTrace() {
		System.out.println("After Trace");
	}

	public void trace(ProceedingJoinPoint joinPoint) throws Throwable {
		// 핵심코드 앞에 삽입될 코드
		String methodName = joinPoint.getSignature().toShortString();
		System.out.println("Method: " + methodName); // 호출된 매서드 이름
		
		StopWatch sw = new StopWatch();
		sw.start(methodName); // 스탑워치를 시작하여 소요시간 확인
		
		// 핵심코드 call
		Object obj = joinPoint.proceed();
		System.out.println(obj);
		// null, HelloPring에서 return값이 null이므로

		// 핵심코드 뒤에 삽입될 코드
		System.out.println("End");
		sw.stop();
		System.out.println("Spending Time: " + sw.getTotalTimeMillis()/1000  + "sec");
	}
}
