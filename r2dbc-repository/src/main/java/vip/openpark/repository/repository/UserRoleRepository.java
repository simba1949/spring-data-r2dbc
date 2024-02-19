package vip.openpark.repository.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import vip.openpark.repository.domain.UserRoleEntity;

/**
 * @author anthony
 * @version 2024/2/19 9:30
 */
@Repository
public interface UserRoleRepository extends R2dbcRepository<UserRoleEntity, Long> {
}