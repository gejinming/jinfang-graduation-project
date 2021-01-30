
/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author lz
 * @date 2020年8月26日
 * @version V1.0
 */

package com.jinfang.graduationproject.vo.student.topic;

import lombok.Data;

import java.io.Serializable;

@Data
public class FindStudenttPageResqVo implements Serializable {

    /**
     * 课题名称
     */
    private String subjectName;

    /**
     * 教师名称
     */
    private String teacherName;
    //

    /**
     * 学员上限个数
     */
    private int memberLimit;

}
