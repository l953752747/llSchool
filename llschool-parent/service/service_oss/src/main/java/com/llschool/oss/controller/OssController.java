package com.llschool.oss.controller;


import com.llschool.commonutils.RR;
import com.llschool.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RequestMapping("/edu/fileByOss")
@RestController
@Api(description = "上传讲师头像")
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * 上传头像
     */
    @ApiOperation(value = "上传讲师头像")
    @PostMapping
    public RR uploadFile(MultipartFile file){
        //获取上传的文件 MultipartFile
        String url = ossService.uploadFileAvatar(file);
        return RR.ok().data("url",url);
    }
}
