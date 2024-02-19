package vip.openpark.repository.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @author anthony
 * @version 2024/2/19 9:31
 */
@Slf4j
@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired()))
public class UserRepositoryTest {
	private final UserRepository userRepository;
	
	@Test
	public void findAllTest() {
		userRepository.findAll()
			.subscribe(user -> log.info("{}", user));
	}
	
	@Test
	public void findByIdTest() {
		userRepository.findById(1L)
			.subscribe(user -> log.info("{}", user));
	}
	
	@AfterEach
	public void afterEach() throws IOException {
		// 防止主线程结束
		System.in.read();
	}
}