package com.yince.service;

import com.yince.pojo.Dept;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();
}
