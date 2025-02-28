package com.yince.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpLog {
    private Integer id; //操作日志id
    private LocalDateTime operateTime; //操作时间
    private String info; //操作信息
}
