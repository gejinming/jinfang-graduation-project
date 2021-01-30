/**
 * 
 */
package com.jinfang.graduationproject.vo.teacher.opening;

import java.io.Serializable;
import java.util.List;

import com.jinfang.graduationproject.domain.GpOpeningReport;
import com.jinfang.graduationproject.domain.GpOpeningReportLiterature;
import com.jinfang.graduationproject.domain.GpOpeningReportScore;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* @Description: (开题报告)
* @author lz
* @date 2020年8月22日
* @version V1.0
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryOpeningResqVo extends GpOpeningReport implements Serializable{

	private static final long serialVersionUID = -570494600237513109L;
	/** 课题名称 */
	private String name;
	/** 届别/所属年级 */
	private String grade;
	//学生姓名
	private String studentName;
	//年级
	private String className;
	//文献数据
	private List<GpOpeningReportLiterature> literatureList;
	//评分项
	private List<GpOpeningReportScore> scoreList;
	
}
