
    /**
    * @Description: TODO(用一句话描述该文件做什么)
    * @author lz
    * @date 2020年9月5日
    * @version V1.0  
    */
    
package com.jinfang.graduationproject.vo.teacher.leader.topic;

import java.io.Serializable;

import lombok.Data;

/**
    * @Description: TODO(审核)
    * @author lz
    * @date 2020年9月5日
    *
    */
@Data
public class ExamineReqVo implements Serializable{

	private static final long serialVersionUID = 5306776171650938997L;
	//记录id
	private Long id;
	//审核状态
	private Integer status;
	//审核不通过原因
	private String approveReason;
	//审核账号
	private String approveUser;
	//审核备注
	private String approveRemark;
}
