package vip.openpark.quick.start;

import io.asyncer.r2dbc.mysql.MySqlConnectionConfiguration;
import io.asyncer.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.ZoneId;

/**
 * <a href="https://github.com/asyncer-io/r2dbc-mysql/wiki/getting-started">r2dbc-mysql WIKI</a>
 *
 * @author anthony
 * @version 2024/2/18 17:18
 */
public class MySQLApplication {
	public static void main(String[] args) throws IOException {
		MySqlConnectionConfiguration configuration =
			MySqlConnectionConfiguration
				.builder()
				.host("127.0.0.1")
				.port(3306)
				.username("root")
				.password("123456")
				.database("example")
				.serverZoneId(ZoneId.of("Asia/Shanghai"))
				.build();
		
		ConnectionFactory connectionFactory = MySqlConnectionFactory.from(configuration);
		Mono.from(connectionFactory.create())
			.flatMapMany(
				connection ->
					connection
						.createStatement("SELECT * FROM user WHERE username = ?")
						.bind(0, "LiBai")
						.execute())
			.flatMap(
				result ->
					// 获取结果集
					result.map((row, rowMetadata) -> row.get("real_name", String.class)))
			.doOnNext(System.out::println)
			.subscribe();
		
		// 防止主线程结束
		System.in.read();
	}
}