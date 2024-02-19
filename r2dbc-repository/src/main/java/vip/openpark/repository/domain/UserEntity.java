package vip.openpark.repository.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * @author anthony
 * @version 2024/2/19 9:16
 */
@Getter
@Setter
@ToString
@Table("user")
public class UserEntity {
	/**
	 * 假设每个用户只有一个角色信息
	 */
	private RoleEntity roleEntity;
	
	@Id
	private Long id;
	/**
	 * 业务编码
	 */
	@Column("code")
	private String code;
	
	/**
	 * 用户登录名
	 */
	@Column("username")
	private String username;
	
	/**
	 * 真实姓名
	 */
	@Column("real_name")
	private String realName;
	
	/**
	 * 用户昵称
	 */
	@Column("nick_name")
	private String nickName;
	
	/**
	 * 密码
	 */
	@Column("password")
	private String password;
	
	/**
	 * 性别，0表示女，1表示男
	 */
	@Column("gender")
	private Byte gender;
	
	/**
	 * 出生日期
	 */
	@Column("birthday")
	private LocalDateTime birthday;
	
	/**
	 * 民族
	 */
	@Column("nation")
	private String nation;
	
	/**
	 * 国家
	 */
	@Column("country_name")
	private String countryName;
	
	/**
	 * 身份证信息
	 */
	@Column("id_card")
	private String idCard;
	
	/**
	 * 地址
	 */
	@Column("address")
	private String address;
	
	/**
	 * 手机号码
	 */
	@Column("phone")
	private String phone;
	
	/**
	 * 邮件
	 */
	@Column("email")
	private String email;
	
	/**
	 * 是否启用，0表示否，1表示是
	 */
	@Column("bl_enable")
	private Byte blEnable;
	
	/**
	 * 是否删除，0表示否，1表示是
	 */
	@Column("bl_delete")
	private Byte blDelete;
	
	/**
	 * 版本号
	 */
	@Column("version")
	private Long version;
	
	/**
	 * 创建时间
	 */
	@Column("gmt_create")
	private LocalDateTime gmtCreate;
	
	/**
	 * 创建人真实姓名
	 */
	@Column("creator")
	private String creator;
	
	/**
	 * 创建人ID
	 */
	@Column("creator_id")
	private Long creatorId;
	
	/**
	 * 创建人code
	 */
	@Column("creator_code")
	private String creatorCode;
	
	/**
	 * 修改时间
	 */
	@Column("gmt_modify")
	private LocalDateTime gmtModify;
	
	/**
	 * 修改人真实姓名
	 */
	@Column("modifier")
	private String modifier;
	
	/**
	 * 修改人ID
	 */
	@Column("modifier_id")
	private Long modifierId;
	
	/**
	 * 修改人code
	 */
	@Column("modifier_code")
	private String modifierCode;
}