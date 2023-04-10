package spring.conf;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
// @Configuration: 일반 java 파일이 아닌, applicationContext.xml과 같은 환경설정 파일임을 안내
@PropertySource("classpath:spring/db.properties") // db.properties가 2개 이상
public class SpringConfiguration {
	@Value("${jdbc.driver}") // jdbc value 입력
	private String driver;

	@Value("${jdbc.url}") // jdbc value 입력
	private String url;

	@Value("${jdbc.username}") // jdbc value 입력
	private String username;

	@Value("${jdbc.password}") // jdbc value 입력
	private String password;
	
	// ConnectionPool 생성
	@Bean // return값을 bean으로 설정
	public BasicDataSource dataSource() {
	
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(driver); // Setter 활용
		basicDataSource.setUrl(url);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);
		
		return basicDataSource;
	}

}
