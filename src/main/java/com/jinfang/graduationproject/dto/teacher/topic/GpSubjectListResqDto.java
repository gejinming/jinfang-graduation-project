/**
 *
 */
package com.jinfang.graduationproject.dto.teacher.topic;

import java.io.Serializable;
import java.util.List;

import com.jinfang.graduationproject.domain.GpSubject;
import com.jinfang.graduationproject.domain.GpSubjectLiterature;
import com.jinfang.graduationproject.domain.GpSubjectStatistics;

/**
 * @Description: (查询返回dto)
 * @author lz
 * @date 2020年8月16日
 * @version V1.0
 */
public class GpSubjectListResqDto extends GpSubject implements Serializable {

    private static final long serialVersionUID = 5108082603800461152L;
    //已接收学生数
    private String studentNames = "";
    // 文献数据
    private List<GpSubjectLiterature> literatureList;
    private GpSubjectStatistics subjectStatistics;

    /**
     * 是否允许修改
     */
    private boolean isAllowModify = false;

    public String getStudentNames() {
        return studentNames;
    }

    public void setStudentNames(String studentNames) {
        this.studentNames = studentNames;
    }

    public List<GpSubjectLiterature> getLiteratureList() {
        return literatureList;
    }

    public void setLiteratureList(List<GpSubjectLiterature> literatureList) {
        this.literatureList = literatureList;
    }

    public GpSubjectStatistics getSubjectStatistics() {
        return subjectStatistics;
    }

    public void setSubjectStatistics(GpSubjectStatistics subjectStatistics) {
        this.subjectStatistics = subjectStatistics;
    }

    public boolean isAllowModify() {
        return isAllowModify;
    }

    public void setAllowModify(boolean allowModify) {
        isAllowModify = allowModify;
    }
}
