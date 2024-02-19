package vip.openpark.repository.repository.converter;

import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.lang.NonNull;
import vip.openpark.repository.domain.RoleEntity;
import vip.openpark.repository.domain.UserEntity;

/**
 * {@link ReadingConverter} 读取数据库是的数据转换器
 *
 * @author anthony
 * @version 2024/2/19 9:40
 */
@ReadingConverter
public class UserEntityConverter implements Converter<Row, UserEntity> {
	@Override
	public UserEntity convert(@NonNull Row source) {
		UserEntity userEntity = new UserEntity();
		// 用户信息
		userEntity.setId(source.get("id", Long.class));
		userEntity.setCode(source.get("code", String.class));
		userEntity.setUsername(source.get("username", String.class));
		userEntity.setRealName(source.get("real_name", String.class));
		// 角色信息
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setId(source.get("role_id", Long.class));
		roleEntity.setCode(source.get("role_code", String.class));
		roleEntity.setName(source.get("role_name", String.class));
		userEntity.setRoleEntity(roleEntity);
		
		return userEntity;
	}
}