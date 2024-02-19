package vip.openpark.repository.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.MySqlDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import vip.openpark.repository.repository.converter.UserEntityConverter;

/**
 * @author anthony
 * @version 2024/2/19 9:16
 */
@EnableR2dbcRepositories(basePackages = {"vip.openpark.repository.repository"})
@Configuration
public class R2dbcConfig {
	
	@Bean
	@ConditionalOnMissingBean
	public R2dbcCustomConversions customConversions() {
		// 自定义转换器加入到转换器中
		return R2dbcCustomConversions.of(MySqlDialect.INSTANCE, new UserEntityConverter());
	}
}