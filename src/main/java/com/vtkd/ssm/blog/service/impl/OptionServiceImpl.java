package com.vtkd.ssm.blog.service.impl;

import com.vtkd.ssm.blog.entity.Option;
import com.vtkd.ssm.blog.mapper.OptionMapper;
import com.vtkd.ssm.blog.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Option 服务层 实现类
 *
 * @author 君上
 * @date 2022-7-24
 * */
@Service
public class OptionServiceImpl implements OptionService {

    @Autowired
    OptionMapper optionMapper;

    @Override
    public void insertOption(Option option) {
        optionMapper.insertOption(option);
    }

    @Override
    public void deleteOption(Integer optionId) {
        optionMapper.deleteOption(optionId);
    }

    @Override
    public void updateOption(Option option) {
        optionMapper.updateOption(option);
    }

    @Override
    public Option getOptionById(Integer optionId) {
        return optionMapper.getOptionById(optionId);
    }

    @Override
    public Option listOption() {
        return optionMapper.listOption();
    }
}
