package com.vtkd.ssm.blog.service;

import com.vtkd.ssm.blog.entity.Option;

import java.util.List;

/**
 * Option 服务层 接口
 * @author 君上
 * @date 2022-7-24
 */
public interface OptionService {


    /**
     * 添加 option
     * @param option 选项
     */
    void insertOption(Option option);

    /**
     * 根据 id 删除 option
     * @param optionId id
     * @return
     */
    void deleteOption(Integer optionId);

    /**
     * 修改 option 信息
     * @param option 选项
     * @return
     */
    void updateOption(Option option);

    /**
     * 根据 id 查询 option
     * @param optionId id
     * @return
     */
    Option getOptionById(Integer optionId);

    /**
     * 查询所有 option
     * @return
     */
    Option listOption();
}
