/**
 *
 */
package com.jinfang.graduationproject.vo.teacher.topic;

import com.jinfang.graduationproject.domain.GpSubject;
import com.jinfang.graduationproject.domain.GpSubjectLiterature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 文献
 * @author lz
 * @date 2020年8月16日
 * @version V1.0
 */
public class GpSubjectSaveResqVo extends GpSubject implements Serializable {

    private static final long serialVersionUID = -6828427890476364822L;
    // 文献集合数据
    private List<GpSubjectLiterature> literatureList = new ArrayList<>();

    public List<GpSubjectLiterature> getLiteratureList() {
        return literatureList;
    }

    public void setLiteratureList(List<GpSubjectLiterature> literatureList) {
        this.literatureList = literatureList;
    }

}
