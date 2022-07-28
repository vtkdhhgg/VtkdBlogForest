package com.vtkd.ssm.blog.controller.admin;

import com.vtkd.ssm.blog.dto.JsonResult;
import com.vtkd.ssm.blog.dto.UploadFileVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

/**
 * 文件上传 管理
 *
 * @author 君上
 * @date 2022-7-28
 */
@Api("文件上传 管理")
@Slf4j
@Controller
@RequestMapping("/admin/upload")
public class UploadFileController {

    public final String rootPath = "/uploads";

    public final String allowSuffix = ".bmp.jpg.jpeg.png.gif.pdf.doc.zip.rar.gz";


    @RequestMapping(value = "/img", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult imgUpload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {

        if (file.isEmpty()) {
            return new JsonResult().fail("文件数据为空");
        }

        // 如spring.jpeg
        String filename = file.getOriginalFilename();
        // 把文件名设置唯一值,uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid + "_" + filename;

        //文件名,如spring
        String name = Objects.requireNonNull(filename).substring(0, filename.indexOf("."));
        //文件后缀,如.jpeg
        String suffix = filename.substring(filename.lastIndexOf("."));

        if (!allowSuffix.contains(suffix)) {
            return new JsonResult().fail("不允许上传该后缀的文件！");
        }

        // 文件目录 uploads
        Calendar date = Calendar.getInstance();
        String path = request.getServletContext().getRealPath(rootPath);
        File realPath = new File(path);
        if (!realPath.exists()) {
            realPath.mkdir();
        }

        //文件目录年月
        File descDirs = new File(File.separator + date.get(Calendar.YEAR) +
                File.separator + (date.get(Calendar.MONTH) + 1));
        if (!descDirs.exists()) {
            descDirs.mkdir();
        }
        // 目标文件
        File descFile = new File(path + File.separator + descDirs + "/" + filename);
        log.info("上传文件保存地址: descFile:{}", descFile);

        // 通过 CommonsMultipartFile的方法直接写文件
        try {
            file.transferTo(descFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传文件失败, uploadPath:{}, fileName:{}, cause:{}", realPath, filename, e);
        }
        // 完整的url
        String fileUrl = rootPath + descDirs + "\\" + descFile.getName();
        // 返回 url
        UploadFileVO uploadFileVO = new UploadFileVO();
        uploadFileVO.setSrc(fileUrl);
        uploadFileVO.setTitle(filename);

        return new JsonResult().ok(uploadFileVO);
    }


}
