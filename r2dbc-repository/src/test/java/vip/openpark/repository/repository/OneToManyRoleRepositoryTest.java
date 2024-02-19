package vip.openpark.repository.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.r2dbc.core.DatabaseClient;
import vip.openpark.repository.domain.PermissionEntity;
import vip.openpark.repository.domain.RoleEntity;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * @author anthony
 * @version 2024/2/19 10:52
 */
@Slf4j
@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired()))
public class OneToManyRoleRepositoryTest {
	private final DatabaseClient databaseClient;
	
	@Test
	public void oneToManyTest() {
		databaseClient.sql("SELECT " +
			                   " r.*," +
			                   " p.id p_id," +
			                   " p.code p_code," +
			                   " p.name p_name," +
			                   " p.url p_url" +
			                   " FROM role r" +
			                   " LEFT JOIN role_permission rp ON r.id = rp.role_id " +
			                   " LEFT JOIN permission p ON p.id = rp.permission_id " +
			                   " WHERE r.id IN (1,2,3,4);")
			.fetch()
			.all()
			// bufferUntilChanged() 用于分组，这里是根据 role_id 根据分组
			.bufferUntilChanged(rowMap -> Long.parseLong(rowMap.get("id").toString()))
			.map(dataList -> {
				RoleEntity roleEntity =
					dataList
						.stream()
						.findFirst()
						.map(rowMap -> {
							RoleEntity tempRoleEntity = new RoleEntity();
							tempRoleEntity.setId(Long.parseLong(rowMap.get("id").toString()));
							tempRoleEntity.setCode(rowMap.get("code").toString());
							tempRoleEntity.setName(rowMap.get("name").toString());
							
							return tempRoleEntity;
						})
						.orElseThrow(() -> new RuntimeException("roleEntity is null"));
				
				List<PermissionEntity> permissionEntityList =
					dataList
						.stream()
						.map(rowMap -> {
							PermissionEntity permissionEntity = new PermissionEntity();
							permissionEntity.setId(Long.parseLong(rowMap.get("p_id").toString()));
							permissionEntity.setCode(rowMap.get("p_code").toString());
							permissionEntity.setName(rowMap.get("p_name").toString());
							return permissionEntity;
						})
						.sorted(Comparator.comparing(PermissionEntity::getId))
						.toList();
				roleEntity.setPermissionEntityList(permissionEntityList);
				return roleEntity;
			})
			.subscribe(roleEntity -> {
				try {
					// 打印成 JSON 便于查看结果
					log.info("roleEntity:{}", new ObjectMapper().writer().writeValueAsString(roleEntity));
				} catch (JsonProcessingException e) {
					throw new RuntimeException(e);
				}
			});
		
	}
	
	@AfterEach
	public void afterEach() throws IOException {
		// 防止主线程结束
		System.in.read();
	}
}