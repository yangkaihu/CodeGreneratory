package com.codejava.entity.query;


import java.util.Date; 
import lombok.Data;


/**
*@Author: 杨开虎
*@Description: 用户者信息
*@Date: 2024/07/24
*/
@Data
public class UserInfoQuery { 
	 /**
	 * Id
	 */
	private Integer id;
	 /**
	 * 用户ID
	 */
	private String userId;
	private String userIdFuzzy;

	 /**
	 * 昵称
	 */
	private String nickName;
	private String nickNameFuzzy;

	 /**
	 * 邮箱
	 */
	private String email;
	private String emailFuzzy;

	 /**
	 * QQ登录ID
	 */
	private String qqOpenId;
	private String qqOpenIdFuzzy;

	 /**
	 * QQ头像
	 */
	private String qqAvatar;
	private String qqAvatarFuzzy;

	 /**
	 * 密码
	 */
	private String password;
	private String passwordFuzzy;

	 /**
	 * 加入时间
	 */
	private Date joinTime;
	private  String  joinTimeStart;

	private  String  joinTimeEnd;

	 /**
	 * 最后登录时间
	 */
	private Date lastLoginTime;
	private  String  lastLoginTimeStart;

	private  String  lastLoginTimeEnd;

	 /**
	 * 状态
	 */
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