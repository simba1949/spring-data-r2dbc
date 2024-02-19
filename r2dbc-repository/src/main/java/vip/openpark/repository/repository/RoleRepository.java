package vip.openpark.repository.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import vip.openpark.repository.domain.RoleEntity;

/**
 * @author anthony
 * @version 2024/2/19 9:29
 */
@Repository
public interface RoleRepository extends R2dbcRepository<RoleEntity, Long> {
}