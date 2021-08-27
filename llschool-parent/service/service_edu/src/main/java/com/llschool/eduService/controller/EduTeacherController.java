package com.llschool.eduService.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.llschool.commonutils.RR;
import com.llschool.eduService.entity.EduTeacher;
import com.llschool.eduService.entity.vo.TeacherQuery;
import com.llschool.eduService.service.EduTeacherService;
import com.mysql.cj.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return RR.ok().data("rows",eduTeacher);
    }

    /**
     * 查询所有讲师的信息
     */
    @ApiOperation(value = "查询所有讲师的信息")
    @GetMapping("findAll")
    public RR findAllTeacher() {
        //调用service的方法实现查询所有的操作
        List<EduTeacher> list = teacherService.list(null);
        return RR.ok().data("items",list);
    }

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页查询")
    @GetMapping("pageTeacher/{currentpage}/{limit}")
    public RR pageListTeacher(@PathVariable Long currentpage,@PathVariable Long limit){
        Page<EduTeacher> pageTeacher = new Page<EduTeacher>(currentpage,limit);
        //调用方法实现分页,调用时会进行底层封装，把分页的所有数据放在pageteacher中
        teacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();
        List<EduTeacher> list = pageTeacher.getRecords();//数据集合
//        return RR.ok().data("total",total).data("rows",list);
        Map map = new HashMap();
        map.put("total",total);
        map.put("rows",list);
        return RR.ok().data(map);
    }

    /**
     * 多条件条件查询带分页功能
     */
    @ApiOperation(value = "条件查询分页显示")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public RR pageTeacherCondition(@PathVariable Long current,
                                   @PathVariable Long limit,
                                   @RequestBody(required = false) TeacherQuery teacherQuery) { //require=false参数指可以为空
        //创建配置对象
        Page<EduTeacher> page = new Page<EduTeacher>(current, limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //判断条件是否为空，如果不为空，拼接条件
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String startTime = teacherQuery.getStartTime();
        String endTime = teacherQuery.getEndTime();
        if (!StringUtils.isNullOrEmpty(name)){
            wrapper.like("name",name);//column是数据库表的字段名
        }
        if (!StringUtils.isNullOrEmpty(String.valueOf(level))){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isNullOrEmpty(startTime)){
            wrapper.ge("create_time",startTime);
        }
        if (!StringUtils.isNullOrEmpty(endTime)) {
            wrapper.le("create_time", endTime);
        }
        //调用方法
        teacherService.page(page, wrapper);
        long total = page.getTotal();
        List<EduTeacher> list = page.getRecords();
        return RR.ok().data("total", total).data("rows", list);

    }


}

