package com.yince.controller;

import com.yince.pojo.Clazz;
import com.yince.pojo.ClazzQueryParam;
import com.yince.pojo.PageResult;
import com.yince.pojo.Result;
import com.yince.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 分页查询班级
     * @param clazzQueryParam
     * @return 统一返回结果
     */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("班级分页查询,参数:{}", clazzQueryParam);
        PageResult<Clazz> result = clazzService.page(clazzQueryParam);
        return Result.success(result);
    }
}
