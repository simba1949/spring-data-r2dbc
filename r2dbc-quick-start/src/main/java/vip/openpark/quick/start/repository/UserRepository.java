package vip.openpark.quick.start.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import vip.openpark.quick.start.domain.UserEntity;

/**
 * <div>
 *     1.继承 {@link ReactiveCrudRepository}
 *     2.继承 {@link R2dbcRepository}【推荐】
 * </div>
 * <div>
 *    {@link R2dbcRepository} 继承了 {@link ReactiveCrudRepository}/{@link ReactiveSortingRepository}/{@link ReactiveQueryByExampleExecutor}
 * </div>
 * <div>
 *     简单查条件询：spring data r2dbc 已经封装好了的接口
 *     复杂条件查询：
 *     1. QBC：Query By {@link Criteria}
 *     2. QBE：Query By {@link Example}
 *     返回直接使用 {@link reactor.core.publisher.Flux} 或者 {@link reactor.core.publisher.Mono}
 * </div>
 *
 * @author anthony
 * @since 2024/2/18 23:29
 */
@Repository
public interface UserRepository extends R2dbcRepository<UserEntity, Long> {
}