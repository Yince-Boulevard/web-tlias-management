package com.yince.controller;

import com.yince.pojo.Emp;
import com.yince.pojo.LoginInfo;
import com.yince.pojo.Result;
import com.yince.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yince boulevard
 * @date 2025-3-4 19:21:56
 */
@Slf4j
@RestController
@RequestMapping
public class LoginController {
    @Autowired
    private EmpService empService;
    @PostMapping(value = "/login")
    public Result login(@RequestBody Emp emp) {
        log.info("登录信息：{}", emp);
        LoginInfo loginInfo = empService.login(emp);
        if(loginInfo.getId() > 0){
        return Result.success(loginInfo);
        }else if(loginInfo.getId() == 0){
            return Result.error("密码错误");
        }
        return Result.error("用户名不存在");
    }
}
