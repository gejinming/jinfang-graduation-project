package com.jinfang.graduationproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ---------------------------
 * 文件表 (DocMeta)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-14 23:07:27
 * 说明：  
 * ---------------------------
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GpDocMeta {

	/**  */
	private Long id;
	/** 文件原名称 */
	private String originName;
	/** 文件新名称 */
	private String newName;
	/** 文件类型 */
	private String type;
	/** 文件大小，存储字节 */
	private Long size;
	/** 存储路径(物理路径) */
	private String path;
	/** 网络全路径 */
	private String url;
	/**  */
	private java.util.Date createDate;
	/**  */
	private java.util.Date modifyDate;

	/** 是否删除 0 :未删除，1：已删除 */
	private Integer isDel;

}