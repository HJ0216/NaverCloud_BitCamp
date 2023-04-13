package spring.conf;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
// @Configuration: 일반 java 파일이 아닌, applicationContext.xml과 같은 환경설정 파일임을 안내
@PropertySource("classpath:spring/db.properties")
@EnableTransactionManagement
// <tx:annotation-driven transaction-manager="transactionManager" />
public class SpringConfiguration {
	// jdbc value 입력
	private @Value("${jdbc.driver}") String driver;

	// jdbc value 입력
	private @Value("${jdbc.url}") String url;

	// jdbc value 입력
	private @Value("${jdbc.username}") String username;

	// jdbc value 입력
	private @Value("${jdbc.password}") String password;
	
	
	// ConnectionPool 생성
	@Bean // return값을 bean으로 설정
	public BasicDataSource dataSource() {	
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(driver);
		basicDataSource.setUrl(url);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);
		
		return basicDataSource;
/*
		<!-- connection pool -->
		<context:property-placeholder location="classpath:spring/db.properties"/>
		<!-- classpath: src가 기본 경로 -->
		<bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource">
			<property name="driverClassName" value="${jdbc.driver}" />
			<property name="url" value="${jdbc.url}" />
			<property name="username" value="${jdbc.username}" />
			<property name="password" value="${jdbc.password}" />
		</bean>
 */
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		// <property name="dataSource" ref="dataSource"></property>
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("spring/mybatis-config.xml"));
		// setConfigLocation(Resource)
		// Resource 자리에 String이 올 수 없으므로 new ClassPathResource() 사용
		// <property name="configLocation" value="classpath:spring/mybatis-config.xml"></property>
		sqlSessionFactoryBean.setMapperLocations(new ClassPathResource("user/dao/userMapper.xml"));
		// ClassPathResource: ""를 1개밖에 못 받음
		
		return sqlSessionFactoryBean.getObject();
		// sqlSessionFactoryBean.getObject(): return sqlSessionFactory
	}

/*
	<mappers>
 		<mapper resource="user/dao/userMapper.xml" />
 		resource 경로 지정 시, .이 아닌 / 사용
 	</mappers>
 */
	
	
/*
Mapper가 2개 이상
Ver 1.
sqlSessionFactoryBean.setMapperLocations(new ClassPathResource("user/dao/userMapper.xml"),
										 new ClassPathResource("board/dao/boardMapper.xml"),
										 new ClassPathResource("member/dao/memberMapper.xml"),
										 ... );

Ver 2.
field 지정
@Autowired
private ApplicationContext context;

*/
// sqlSessionFactoryBean.steMapperLocatoins(context.getResources("classpath:*/dao/*Mapper.xml"))

	
	
	
	
	
	
	
	
	
	
	
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		// throws가 선언된 메서드를 호출하는 경우, 호출한 메서드도 throws를 선언해야 함
		return sqlSessionTemplate;
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager(){
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource());
		return dataSourceTransactionManager;
	}
/*
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<constructor-arg ref="dataSource"/>
	</bean> 
*/

}
