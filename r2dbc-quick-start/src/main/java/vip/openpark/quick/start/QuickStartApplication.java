package vip.openpark.quick.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.r2dbc.R2dbcDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.r2dbc.R2dbcRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcTransactionManagerAutoConfiguration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;

/**
 * <div>
 *     spring boot 对 r2dbc 的自动配置：
 *     1. {@link R2dbcAutoConfiguration}：主要是配置连接工厂、连接池等；
 *     2. {@link R2dbcDataAutoConfiguration}：操作数据库响应式客户端，提供 CRUD 操作；
 *     3. {@link R2dbcRepositoriesAutoConfiguration}：开启 spring data 声明式接口方式的 CRUD；
 *     4. {@link R2dbcTransactionManagerAutoConfiguration}：事务管理
 * </div>
 * <div>
 *     <title>{@link R2dbcDataAutoConfiguration}</title>
 *     {@link R2dbcEntityTemplate}：操作数据库响应式客户端，提供 CRUD API
 *     数据库类型映射关系、转换器、自定义 {@link R2dbcCustomConversions} 转换组件；
 *     数据类型转换：如 int/Integer/varchar/string/datetime/instant
 * </div>
 *
 * @author anthony
 * @version 2024/2/18 17:39
 */
@SpringBootApplication
public class QuickStartApplication {
	public static void main(String[] args) {
		SpringApplication.run(QuickStartApplication.class, args);
	}
}