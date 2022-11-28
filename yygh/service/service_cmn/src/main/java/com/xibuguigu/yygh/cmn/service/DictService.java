package com.xibuguigu.yygh.cmn.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xibuguigu.yygh.model.cmn.Dict;

import java.util.List;

/**
 * <p>
 * 组织架构表 服务类
 * </p>
 *
 * @author atxibuguigu
 * @since 2022-11-21
 */
public interface DictService extends IService<Dict> {

    List<Dict> findTreeByParentId(Long parentId);
    String getNameByParentDictCodeAndValue(String parentDictCode, String value);


}
