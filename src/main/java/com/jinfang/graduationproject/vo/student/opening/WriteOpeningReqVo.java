
/**
* @Description: TODO(用一句话描述该文件做什么)
* @author lz
* @date 2020年9月1日
* @version V1.0  
*/

package com.jinfang.graduationproject.vo.student.opening;

import java.io.Serializable;
import java.util.List;

import com.jinfang.graduationproject.domain.GpOpeningReport;
import com.jinfang.graduationproject.domain.GpOpeningReportLiterature;

/**
 * @Description: TODO(写入)
 * @author lz
 * @date 2020年9月1日
 *
 */

public class WriteOpeningReqVo extends GpOpeningReport implements Serializable {

	private static final long serialVersionUID = -7170286684798229193L;
	// 文献综述列表
	private List<GpOpeningReportLiterature> literatureList;

	public List<GpOpeningReportLiterature> getLiteratureList() {
		return literatureList;
	}

	public void setLiteratureList(List<GpOpeningReportLiterature> literatureList) {
		this.literatureList = literatureList;
	}

}
