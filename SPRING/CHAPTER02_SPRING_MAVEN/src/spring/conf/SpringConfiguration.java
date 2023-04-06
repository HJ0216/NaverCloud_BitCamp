package spring.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sample01.MessageBeanImpl;
import sample02.CalcAdd;
import sample02.CalcMul;

@Configuration // @Configuration: 환경 설정 파일 선언, applicationContect.xml과 동일
public class SpringConfiguration {
	
	// sample01
	@Bean
	// @Bean: 메소드 위에서 메소드 return 값을 Bean으로 생성
	// 메소드 이름: Bean id명(필수)
	public MessageBeanImpl messageBeanImpl(){
		return new MessageBeanImpl("사과");
	}
	
	
	// sample02
	@Bean
	public CalcAdd calcAdd() {
		return new CalcAdd(42, 18);
	}
//	@Bean
//	public CalcMul calcMul() { // Default Constructor
//		return new CalcMul();
//	}
	@Bean(name="calcMul")
	// getter 함수 명칭을 method명으로 선언 후 객체 이름을 calcMul로 선언	
	public CalcMul getCalcMul() { // Default Constructor
		return new CalcMul();
	}
	
	
}
