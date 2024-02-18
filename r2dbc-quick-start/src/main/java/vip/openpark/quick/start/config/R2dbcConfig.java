package vip.openpark.quick.start.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * @author anthony
 * @since 2024/2/18 23:28
 */
@EnableR2dbcRepositories(basePackages = {"vip.openpark.quick.start.repository"})
@Configuration
public class R2dbcConfig {
}