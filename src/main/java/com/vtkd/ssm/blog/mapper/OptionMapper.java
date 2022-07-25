package com.vtkd.ssm.blog.mapper;

import com.vtkd.ssm.blog.entity.Option;
import com.vtkd.ssm.blog.entity.User;

import java.util.List;

/**
 * Option 数据库 接口
 * @author 君上
 * @date 2022-7-24
 */
public interface OptionMapper {

    /**
     * 添加 option
     * @param option 选项
     */
    int insertOption(Option option);

    /**
     * 根据 id 删除 option
     * @param optionId id
     * @return
     */
    int deleteOption(Integer optionId);

    /**
     * 修改 option 信息
     * @param option 选项
     * @return
     */
    int updateOption(Option option);

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
    List<Option> listOption();


}
