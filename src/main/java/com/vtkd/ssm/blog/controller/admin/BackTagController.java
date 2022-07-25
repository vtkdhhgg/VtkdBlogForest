package com.vtkd.ssm.blog.controller.admin;

import com.vtkd.ssm.blog.entity.Tag;
import com.vtkd.ssm.blog.service.TagService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 后台 标签 管理
 *
 * @author 君上
 * @date 2022-7-25
 */
@Api("后台 标签 管理")
@Controller
@RequestMapping("/admin/tag")
public class BackTagController {

    @Autowired
    private TagService tagService;

    /**
     * 标签 列表页面展示
     * @return
     */
    @RequestMapping("")
    public ModelAndView tagListView() {
        ModelAndView modelAndView = new ModelAndView();
        // todo 还没有进行文件数的查询
        List<Tag> tags = tagService.listTag();
        modelAndView.addObject("tagList", tags);

        modelAndView.setViewName("Admin/Tag/index");
        return modelAndView;
    }

    /**
     * 标签编辑页面展示
     * @return
     */
    @RequestMapping("/edit/{tagId}")
    public ModelAndView editTagView(@PathVariable("tagId")Integer tagId){
        ModelAndView modelAndView = new ModelAndView();

        Tag tagById = tagService.getTagById(tagId);
        modelAndView.addObject("tag", tagById);

        modelAndView.setViewName("Admin/Tag/edit");
        return modelAndView;
    }

    /**
     * 修改 标签提交
     * @param tag
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editTagSubmit(Tag tag){

        tagService.updateTag(tag);

        return "redirect:/admin/tag";
    }


    /**
     * 添加 标签提交
     * @param tag
     * @return
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertTagSubmit(Tag tag){
        tagService.insertTag(tag);

        return "redirect:/admin/tag";
    }

    /**
     * 根据 id 删除 tag
     * @param tagId id
     * @return
     */
    @RequestMapping("/delete/{tagId}")
    public String deleteTagById(@PathVariable("tagId")Integer tagId){
        Tag tagById = tagService.getTagById(tagId);
        // todo 这里是不对的 是在查询tag的时候赋值 articleCount
        tagById.setArticleCount(1);
        Integer articleCount = tagById.getArticleCount();
        if (articleCount == 0){
            tagService.deleteTagById(tagId);
        }

        return "redirect:/admin/tag";
    }

    /**
     * 通过 标签名字 查询 标签
     * @param tagName
     * @return
     */
    public String getTagByName(String tagName){
        tagService.getTagByName(tagName);

        return "";
    }





}
