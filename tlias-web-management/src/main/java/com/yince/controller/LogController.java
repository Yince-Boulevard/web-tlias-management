package com.yince.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yince.mapper.OperateLogMapper;
import com.yince.pojo.Emp;
import com.yince.pojo.OperateLog;
import com.yince.pojo.PageResult;
import com.yince.pojo.Result;
import com.yince.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private OperateLogService operateLogService;

    @GetMapping("/page")
    public Result page(@RequestParam Integer page, @RequestParam Integer pageSize) {
        log.info("访问日志");
        log.info("分页查询,参数:{}", page, pageSize);
        PageResult<OperateLog> result = operateLogService.page(page, pageSize);
        return Result.success(result);
    }
}
