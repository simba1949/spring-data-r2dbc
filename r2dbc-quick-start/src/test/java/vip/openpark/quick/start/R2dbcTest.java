package vip.openpark.quick.start;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.r2dbc.core.DatabaseClient;
import vip.openpark.quick.start.domain.UserEntity;

/**
 * @author anthony
 * @since 2024/2/18 22:32
 */
@Slf4j
@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired()))
public class R2dbcTest {
	/**
	 * <a href="https://docs.spring.io/spring-data/relational/reference/r2dbc/entity-persistence.html">r2dbcEntityTemplate</a>
	 */
	private final R2dbcEntityTemplate r2dbcEntityTemplate;
	/**
	 * 贴近底层，好做 join 操作，用于复杂查询
	 */
	private final DatabaseClient databaseClient;
	
	@Test
	public void r2dbcEntityTemplateTest() throws InterruptedException {
		Criteria criteria = Criteria.empty()
			.and("username").is("LiBai");
		Query query = Query.query(criteria);
		
		r2dbcEntityTemplate.select(query, UserEntity.class)
			.log()
			.subscribe(userEntity -> log.info("userEntity = {}", userEntity));
		
		// 防止主线程结束
		Thread.sleep(2000);
	}
	
	@Test
	public void databaseClientTest() throws InterruptedException {
		databaseClient
			// sql 语句
			.sql("select * from user where id > ?")
			// 绑定参数
			.bind(0, "LiBai")
			// 抓取数据
			.fetch()
			.all()
			.subscribe(userEntity -> log.info("userEntity = {}", userEntity));
		
		// 防止主线程结束
		Thread.sleep(2000);
	}
}