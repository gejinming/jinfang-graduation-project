
/**
* @Description: TODO(用一句话描述该文件做什么)
* @author lz
* @date 2020年9月1日
* @version V1.0  
*/

package com.jinfang.graduationproject.vo.student.review;

import java.io.Serializable;
import java.util.List;

import com.jinfang.graduationproject.domain.GpReview;
import com.jinfang.graduationproject.domain.GpReviewLiterature;

/**
 * @Description: TODO(文献描述写入)
 * @author lz
 * @date 2020年9月1日
 *
 */
public class WriteReqVo extends GpReview implements Serializable {

	private static final long serialVersionUID = -7170286684798229193L;
	// 文献综述列表
	private List<GpReviewLiterature> literatureList;

	public List<GpReviewLiterature> getLiteratureList() {
		return literatureList;
	}

	public void setLiteratureList(List<GpReviewLiterature> literatureList) {
		this.literatureList = literatureList;
	}

}
