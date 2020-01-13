package com.wxyj.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping( value = "/upload")
public class FileUploaController {
    /**
     * 文件上传
     */
    @PostMapping
    public Result upload(@RequestParam(value = "file")MultipartFile file)
    {
        //封装文件信息

        //调用fastdfsutil工具类将文件传入到FastDFS中
        return new Result(true, StatusCode.OK,"上传成功");
    }
}
