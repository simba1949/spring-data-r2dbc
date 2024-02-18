package vip.openpark.quick.start;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * @author anthony
 * @version 2024/2/18 16:52
 */
public class QuickStartApplication {
	public static void main(String[] args) throws IOException {
		ConnectionFactoryOptions options =
			ConnectionFactoryOptions.builder()
				.option(ConnectionFactoryOptions.PROTOCOL, "r2dbc")
				.option(ConnectionFactoryOptions.DRIVER, "mysql")
				.option(ConnectionFactoryOptions.HOST, "172.17.35.120")
				.option(ConnectionFactoryOptions.PORT, 3306)
				.option(ConnectionFactoryOptions.DATABASE, "example")
				.option(Option.valueOf("serverTimezone"), "Asia/Shanghai")
				.option(ConnectionFactoryOptions.USER, "root")
				.option(ConnectionFactoryOptions.PASSWORD, "123456")
				.build();
		ConnectionFactory connectionFactory = ConnectionFactories.get(options);
		
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