package com.codejava.po;
import java.io.Serializable;
import java.util.Date; 
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
/**
*@Author: 杨开虎
*@Description: 用户者信息
*@Date: 2024/06/02
*/
@Data
public class UserInfo  implements   Serializable {
	 /**
	 * Id
	 */

	private Integer id;

	 /**
	 * 用户ID
	 */
	@JsonIgnore
	private String userId;

	 /**
	 * 昵称
	 */

	private String nickName;

	 /**
	 * 邮箱
	 */

	private String email;

	 /**
	 * QQ登录ID
	 */

	private String qqOpenId;

	 /**
	 * QQ头像
	 */

	private String qqAvatar;

	 /**
	 * 密码
	 */

	private String password;

	 /**
	 * 加入时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date joinTime;

	 /**
	 * 最后登录时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastLoginTime;

	 /**
	 * 状态
	 */
	@JsonIgnore
	private Integer status;

	 /**
	 * 用户空间
	 */

	private Long useSpace;

	 /**
	 * 总空间
	 */

	private Long totalSpace;


}