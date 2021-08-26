package com.llschool.eduService.controller;

import com.llschool.commonutils.RR;
import com.llschool.eduService.entity.EduTeacher;
import com.llschool.eduService.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 * 访问地址： http://localhost:8001/eduservice/edu-teacher/findAll
 *
 * @author liulei
 * @since 2021-08-26
 */

@Api(description = "讲师信息管理")
@RestController
@RequestMapping("/eduService/edu-teacher")
public class EduTeacherController {


    /**
     * 注入serice
     */
    @Autowired
    private EduTeacherService teacherService;


    /**
     * 添加讲师的信息
     */
    @ApiOperation(value = "添加讲师信息")
    @PostMapping("addTeacherInfo")
    public RR addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = teacherService.save(eduTeacher);
        if (save) {
            return RR.ok();
        } else {
            return RR.error();
        }
    }

    /**
     * 删除讲师信息
     */
    @ApiOperation(value = "删除讲师信息")
    @DeleteMapping("deleteTeacher/{id}")
    public RR deleteTeacher(@ApiParam(name = "id", required = true) @PathVariable String id) {
        boolean delete = teacherService.removeById(id);
        if (delete) {
            return RR.ok();
        } else {
            return RR.error();
        }
    }

    /**
     * 修改讲师信息
     */
    @ApiOperation(value = "修改讲师信息")
    @PostMapping("updateTeacher")
    public RR updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean update = teacherService.updateById(eduTeacher);
        if (update) {
            return RR.ok();
        } else {
            return RR.error();
        }


    }

    /**
     * 根据id查询讲师信息
     */
    @ApiOperation(value = "查询单个讲师信息")
    @GetMapping("getTeacher/{id}")
    public RR findByIdTeacher(@PathVariable String id) {
        EduTeacher eduTeacher = teacherService.getById(id);
        return RR.ok();
    }

    /**
     * 查询所有讲师的信息
     */
    @ApiOperation(value = "所有讲师的信息")
    @GetMapping("findAll")
    public RR findAllTeacher() {
        //调用service的方法实现查询所有的操作
        List<EduTeacher> list = teacherService.list(null);
        return RR.ok();
    }


}

