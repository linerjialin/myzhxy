package com.liner.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liner.zhxy.entity.Grade;
import com.liner.zhxy.mapper.GradeMapper;
import com.liner.zhxy.service.GradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Service("gradeServiceImpl")
@Transactional
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {
    private QueryWrapper<Grade> queryWrapper = new QueryWrapper();

    /**
     * 分页模糊查询年级信息
     * @param pageParam
     * @param gradeName
     * @return
     */
    @Override
    public IPage<Grade> getGradeByOpr(Page<Grade> pageParam, String gradeName) {

       if (!StringUtils.isEmpty(gradeName)){
            queryWrapper.like("name",gradeName);
       }
        queryWrapper.orderByAsc("id");
        return  baseMapper.selectPage(pageParam, queryWrapper);
    }

    /**
     * 查询所有年级信息
     * @return
     */
    @Override
    public List<Grade> getGrades() {
        return baseMapper.selectList(null);
    }
}
