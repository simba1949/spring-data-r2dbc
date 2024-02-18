package vip.openpark.quick.start.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author anthony
 * @since 2024/2/18 23:34
 */
@Slf4j
@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired()))
public class UserRepositoryTest {
	private final UserRepository userRepository;
	
	@Test
	public void findByIdTest() throws InterruptedException {
		userRepository.findById(1L)
			.subscribe(val -> log.info("{}", val));
		
		// // 防止主线程结束
		Thread.sleep(5000);
	}
}