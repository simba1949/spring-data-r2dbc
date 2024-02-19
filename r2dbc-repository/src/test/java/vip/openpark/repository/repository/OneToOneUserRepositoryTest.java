package vip.openpark.repository.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.r2dbc.core.DatabaseClient;
import vip.openpark.repository.domain.RoleEntity;
import vip.openpark.repository.domain.UserEntity;

import java.io.IOException;
import java.math.BigInteger;

/**
 * @author anthony
 * @version 2024/2/19 10:14
 */
@Slf4j
@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired()))
public class OneToOneUserRepositoryTest {
	private final UserRepository userRepository;
	private final DatabaseClient databaseClient;
	
	@Test
	public void oneToOneWithCustomConverterTest() {
		userRepository.findById(1L)
			.subscribe(user -> log.info("{}", user));
	}
	
	/**
	 * 通过 DatabaseClient 查询
	 * 需要注入 DatabaseClient
	 */
	@Test
	public void oneToOneWithDatabaseClientTest() {
		databaseClient.sql("SELECT" +
			                   " user.*," +
			                   " role.id role_id," +
			                   " role.code role_code," +
			                   " role.name role_name" +
			                   " FROM" +
			                   " user" +
			                   " LEFT JOIN user_role ur ON user.id = ur.user_id" +
			                   " LEFT JOIN role role ON ur.role_id = role.id" +
			                   " WHERE " +
			                   " user.id = ?")
			.bind(0, 1)
			.fetch()
			.all()
			.map(row -> {
				UserEntity userEntity = new UserEntity();
				// 用户信息
				userEntity.setId(((BigInteger) row.get("id")).longValue());
				userEntity.setCode((String) row.get("code"));
				userEntity.setUsername((String) row.get("username"));
				userEntity.setRealName((String) row.get("real_name"));
				// 角色信息
				RoleEntity roleEntity = new RoleEntity();
				roleEntity.setId(((BigInteger) row.get("role_id")).longValue());
				roleEntity.setCode(row.get("role_code").toString());
				roleEntity.setName(row.get("role_name").toString());
				userEntity.setRoleEntity(roleEntity);
				return userEntity;
			})
			.subscribe(user -> log.info("{}", user));
	}
	
	@AfterEach
	public void afterEach() throws IOException {
		// 防止主线程结束
		System.in.read();
	}
}