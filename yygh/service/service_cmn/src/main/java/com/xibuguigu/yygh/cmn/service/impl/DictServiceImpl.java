package com.xibuguigu.yygh.cmn.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xibuguigu.yygh.cmn.mapper.DictMapper;
import com.xibuguigu.yygh.cmn.service.DictService;
import com.xibuguigu.yygh.model.cmn.Dict;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 组织架构表 服务实现类
 * </p>
 *
 * @author atxibuguigu
 * @since 2022-11-21
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    //注意 ： 这里的parentId 是动态的 可以是任意数字 这是父  根据父的id查
    public List<Dict> findTreeByParentId(Long id) {
       //我们最后需要的数据就是这个子集合
        //把这个数字当作父的id查, parentId=这个id的值的子集合
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parentId",id);//这里要特别注意
        //这里查出的就是子的集合
        List<Dict> dictList = baseMapper.selectList(queryWrapper);
        //查子有没有子 遍历子集合，设置这个子集合的属性
        for (Dict dict : dictList) {
            hasChildren(dict);
            dict.setHasChildren(true);

        }

        return dictList;
    }

    private boolean hasChildren(Dict dict) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parentId", dict.getId());
        Integer count = baseMapper.selectCount(wrapper);
        return count>0;
    }
    @Override
    public String getNameByParentDictCodeAndValue(String parentDictCode, String value) {

        //根据parentDictCode和value查找dict name
        if(!StringUtils.isEmpty(parentDictCode)){
            //根据parentDictCode查找父记录
            QueryWrapper<Dict> qw1 = new QueryWrapper<>();
            qw1.eq("dict_code", parentDictCode);
            Dict parentDict = baseMapper.selectOne(qw1);

            if(parentDict == null) return "";

            //根据父记录id查找子记录
            QueryWrapper<Dict> qw2 = new QueryWrapper<>();
            qw2.eq("parent_id",parentDict.getId()).eq("value", value);
            Dict dict = baseMapper.selectOne(qw2);

            if(dict == null) return "";

            //返回数据字典名称
            return dict.getName();
        }

        //根据value查找dict name（专门针对地区字典）
        QueryWrapper<Dict> qw3 = new QueryWrapper<>();
        qw3.eq("value", value);
        Dict dict = baseMapper.selectOne(qw3);
        return dict.getName();
    }




}
