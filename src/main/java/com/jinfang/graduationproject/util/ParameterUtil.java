/**
 * 
 */
package com.jinfang.graduationproject.util;

import com.jinfang.graduationproject.dto.page.ColumnFilter;
import com.jinfang.graduationproject.dto.page.PageRequest;

/**
* @Description: (查询参数获取工具类)
* @author lz
* @date 2020年8月18日
* @version V1.0
*/
public class ParameterUtil {
	
	/**
	 * 获取过滤字段的值
	 * @param filterName
	 * @return
	 */
	public static String getColumnFilterValue(PageRequest pageRequest, String filterName) {
		String value = null;
		ColumnFilter columnFilter = pageRequest.getColumnFilter(filterName);
		if(columnFilter != null) {
			value = columnFilter.getValue();
		}
		return value;
	}
}
