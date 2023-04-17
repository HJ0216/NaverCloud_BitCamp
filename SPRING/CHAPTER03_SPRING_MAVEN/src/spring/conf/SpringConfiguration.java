package spring.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import sample01.LoggingAdvice;
import sample01.MsgBeanImpl;

@Configuration // 일반 자바파일이 아닌 환경설정 파일임을 안내
@EnableAspectJAutoProxy //<aop:aspectj-autoproxy></aop:aspectj-autoproxy> 대체
public class SpringConfiguration {

	@Bean
	public MsgBeanImpl msgBeanImpl() {
		return new MsgBeanImpl();
	}
	
	@Bean
	public LoggingAdvice loggingAdvice() {
		return new LoggingAdvice();
	}
	
	
}
