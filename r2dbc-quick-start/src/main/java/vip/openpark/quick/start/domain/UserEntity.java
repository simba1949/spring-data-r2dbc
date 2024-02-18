package vip.openpark.quick.start.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author anthony
 * @since 2024/2/18 22:40
 */
@Table("user")
@Getter
@Setter
@ToString
public class UserEntity {
	@Id
	private Long id;
	private String code;
	private String username;
	private String realName;
}