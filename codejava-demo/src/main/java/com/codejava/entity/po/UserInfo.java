package com.codejava.entity.po;
import java.io.Serializable;
import java.util.Date; 

/**
*@Author: 杨开虎
*@Description: 用户者信息
*@Date: 2024/05/28
*/
public class UserInfo  implements   Serializable {
	 /**
Id
	 */
private Integer id;

	 /**
用户ID
	 */
private String userId;

	 /**
昵称
	 */
private String nickName;

	 /**
邮箱
	 */
private String email;

	 /**
QQ登录ID
	 */
private String qqOpenId;

	 /**
QQ头像
	 */
private String qqAvatar;

	 /**
密码
	 */
private String password;

	 /**
加入时间
	 */
private Date joinTime;

	 /**
最后登录时间
	 */
private Date lastLoginTime;

	 /**
状态
	 */
private Integer status;

	 /**
用户空间
	 */
private Long useSpace;

	 /**
总空间
	 */
private Long totalSpace;


}