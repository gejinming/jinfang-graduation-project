package com.jinfang.graduationproject.mapper;

import java.util.List;

import com.jinfang.graduationproject.domain.GpDocMeta;
import org.apache.ibatis.annotations.Param;

/**
 * ---------------------------
 * 文件表 (DocMetaMapper)         
 * ---------------------------
 * 作者：  lz
 * 时间：  2020-08-14 23:07:27
 * 说明：  
 * ---------------------------
 */
public interface GpDocMetaMapper {

	/**
	 * 添加文件表
	 * @param record
	 * @return
	 */
    int add(GpDocMeta record);

    /**
     * 删除文件表
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改文件表
     * @param record
     * @return
     */
    int update(GpDocMeta record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    GpDocMeta findById(Long id);

    List<GpDocMeta> findByIds(@Param("ids") List<String> ids);

    /**
     * 基础分页查询
     * @return
     */    
    List<GpDocMeta> findPage();
    
}