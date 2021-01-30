package com.jinfang.graduationproject.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录校验后结果
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResult {

	/**
	 * 登录人ID
	 */
	private Long id;

	/**
	 * 登录人姓名
	 */
	private String name;

	/**
	 * 登录人角色，多个以逗号分隔
	 */
	private String role;

	/**
	 * token令牌
	 */
	private String token;

}
