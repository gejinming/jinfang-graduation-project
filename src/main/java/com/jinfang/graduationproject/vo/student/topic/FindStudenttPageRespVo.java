
/**
* @Description: TODO(用一句话描述该文件做什么)
* @author lz
* @date 2020年8月26日
* @version V1.0  
*/

package com.jinfang.graduationproject.vo.student.topic;

import java.io.Serializable;
import java.util.List;

import com.jinfang.graduationproject.domain.GpSubjectLiterature;
import com.jinfang.graduationproject.domain.GpSubjectStatistics;

import lombok.Data;

/**
 * @Description: TODO(学生选题查询 分页)
 * @author lz
 * @date 2020年8月26日
 *
 */
@Data
public class FindStudenttPageRespVo extends GpSubjectStatistics implements Serializable {
	private static final long serialVersionUID = -3424118589041259066L;
	//课题id
	private Long subjectId;
	//课题名称
	private String subjectName;
	// 教师名称
	private String teacherName;
	/** 限选学生数 */
	private Integer memberLimit;
	/** 届别/所属年级 */
	private String grade;
	/** 课题类型  1: 毕业设计,2:毕业论文 */
	private Integer type;
	/** 课题性质 1:学术论文,2:工程设计 3:实验  4:理论计算  5:其他 */
	private Integer nature;
	/** 课题来源 1: 科研 2:生产实际 3:工程实际 4:实验 5:教师研究课题 6:其他 */
	private Integer source;
	/** 论文简介 */
	private String description;
	/**  */
	private String content;
	//已接受學生
	private String studentNames;
	//学号 用于判断是否已选择过
	private String studentNo;
	// 文献数据
	private List<GpSubjectLiterature> literatureList;
	
	
}
