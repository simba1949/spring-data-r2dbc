package vip.openpark.repository.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import vip.openpark.repository.domain.UserEntity;

/**
 * @author anthony
 * @version 2024/2/19 9:16
 */
@Repository
public interface UserRepository extends R2dbcRepository<UserEntity, Long> {
	@Query("SELECT" +
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
	Mono<UserEntity> findById(long id);
}