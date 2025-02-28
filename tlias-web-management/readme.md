# Day 01 = 2025-2-25

## 今日内容

1. Mybatis 默认数据封装规则
2. 如果字段名与实体类属性名不一致？
3. 接受参数的注解: @RequestParam
4. 接受请求体的注解: @RequestBody
5. ### Mybatis 默认数据封装规则

   * 实体类名属性名 和 数据库表的字段名一致， mybatis会自动封装
6. ### 如果字段名与实体类属性名不一致？

   * 手动结果映射 @Result({....})
   * sql语句起别名
   * 开启camel命名开关
7. ### 接受参数的注解: @RequestParam
8. ### 接受请求体的注解: @RequestBody

## Point

### 获取请求中发送的参数

```markdown
请求格式:
    http://localhost:8080/depts?id=1
```

```java
@RequestParam(value = "属性名", required = "true") Integer id;
```

### 获取请求体中的参数

```java
@RequestBody User user;
```

### 注意事项：

Mapper层中 接口传入的参数若是一个封装实体，则#{}内直接填属性就行，不用"对象.属性"

### 遇到的问题

1. lombok失效:
   **删除pom.xml中的有关lombok的build标签，固定lombok的版本，在dependency中**

# Day 02 = 2025-2-26

## 今日内容:

1. 查询回显
2. @RequestMapping
3. 日志记录
4. 分页查询

### 1. 查询回显

路径参数: 通过请求URL直接传递参数, 使用{...}来标识该路径参数, 需要使用 @PathVariable 获取

```markdown
请求格式:
    http://localhost:8080/depts/1
```

格式：

```java
public Result get(@PathVariable("id") Integer id) {
   ;
}
// 若PathVariable中的属性值与参数名一致，可以简化为
public Result get(@PathVariable Integer id) {}
```

### 2. @RequestMapping

若一个Controller中所有方法的请求路径相同，则可以使用@RequestMapping注解 抽取到这个类上,
里面的方法就不要再补充GetMapping,RestMapping,PostMapping中的value值了

```java
@RequestMapping("/depts")
@RestController
public class DeptController {
    ;
}
```

### 3. 日志记录

JUL: JavaSE官方提供的日志框架

Log4j: 一个流行的日志框架, 提供了灵活的配置选项, 支持多种输出目标

Logback: 基于Log4j的轻量级日志框架, 配置简单, 性能高

SLF4J: 简单日志门面,一个日志统一接口, 不提供具体实现，支持多种日志框架

#### Logback 快速入门

```
引入logback的依赖(SpringBoot项目中该依赖已经传递下来了)
配置logback.xml文件

可以配置日志输出的格式、位置、输出目标、开关

具体配置文件地址:
tlias-web-management/src/main/resources/logback.xml
有详细注释

log日志级别(从低到高):
   level: ALL> TRACE> DEBUG> INFO> WARN> ERROR> FATAL> OFF
   默认级别: INFO
      ALL: 所有
      TRACE: 追踪
      DEBUG: 调试 (视为最低级别日志，记录调试过程)
      INFO: 信息 (记录一般信息，描述程序运行的关键事件)
      WARN: 警告 (记录程序运行过程中可能引起问题的事件)
      ERROR: 错误 (记录程序运行过程中的错误信息)
      FATAL: 致命 
      OFF: 关闭
```

### 4.分页查询

```markdown
1. 引入分页插件
    是Mybatis框架中用来实现分页的插件，简化分页操作，提高开发效率
2. 在查询方法上添加@PageHelper注解
```

```json
{
   "code": 1,
   "msg": "查询成功",
   "data": {
      "total": 2,
      "rows": [
         {
            "id": 1,
            "name": "技术部"
         },
         {
         }
      ]
   }
}
```

## Point

### 若多表查询结果中有字段在实体类中不存在，则需要手动添加封装该字段

# Day 03 = 2025-2-28

## 今日内容

1. 条件分页查询优化
   1. 优化参数，将参数封装为一个实体类
   2. 动态SQL, 优化SQL语句，某些查询条件可以为空,可以模糊匹配
2. 新增员工
   1. 新增员工基本信息
   2. 新增员工经历信息（批量插入）
3. 事务管理

### 查询参数封装实体类

```java
public class EmpQueryParam {
   private Integer page = 1;
   ;
//   其余参数
}
```

### Mybatis中的动态 SQL

1. <where> 根据查询条件来生成where关键字，自动去除多余的and或者or

### 批量插入

动态SQL
`<foreach>标签`，
`collection`指定集合、列表，
`item` 指定item名，
`separator` 指定分隔符
`open` 遍历开始前拼接的片段（可选）`close` 遍历结束后拼接的片段（可选）

```xml
<insert id="insertBatch">
        insert into emp_expr(emp_id,begin,end,company,job) values
        <foreach collection="exprList" item="expr" separator=",">
            (#{expr.empId},#{expr.begin},#{expr.end},#{expr.company},#{expr.job})
        </foreach>
    </insert>
```

#### 主键回显

`@Options(useGeneratedKeys = true)`获取到生成的主键，返回主键赋值给emp中的id

```java
@Options(useGeneratedKeys = true)
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
    "values (#{username}, #{name},#{gender},#{phone},#{job},#{salary},#{image},#{entry_date},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);
```

### 事务管理

thinking: 保存emp成功了，而保存empExpr失败了，该怎么办？

* `@Transactional`注解
* 作用：将当前方法交给spring进行事务管理，方法执行前，开启事务；成功执行完毕，提交事务；出现异常，回滚事务
* 位置：最好加在（操作了两次及以上数据库）业务层的方法上，而不是类或者接口
* 要在 `application.yml`中配置事务管理相关

#### 指定异常回滚

> 指定出现某种异常，回滚事务,@Transactional默认RuntimeException（运行异常）才回滚

* `RollbackFor` 参数

  ```java
  @Transactional(rollbackFor = {Exception.class})
  ```

#### 事务的传播行为

> 当一个事务方法被另一个事务方法调用时，这个事务方法该如何进行事务表现

* `propagation `
  ```java
  @Transaction(propagation = Propagation.REQUIRED)
  ```

| 属性值        | 含义                             |
| ------------- | -------------------------------- |
| REQUIRED      | 【默认】有则加入，无则创建新事物 |
| REQUIRES_NEW  | 无论有无，总是创建新事物         |
| SUPPORTS      | 有则加入，无则在无实物状态中运行 |
| NOT_SUPPORTED | 如过有事务，则挂起               |
| MANDATORY     | 必须有事务，否则异常             |
| NEVER         | 必须没事务，否则异常             |

### try - finnally 语法

```java
try {
...
} finally {
...总会执行
}
```

## Point

# Project Summary

## 1. 项目构建

## 2. application.yml、logback.xml配置

java版本，依赖，annotation注解开关,常用插件

1. 数据库相关
2. Mybatis相关
3. 日志相关
4. 事务管理相关

application.yml

```xml
# 配置项目信息
spring:
  application:
    name: tlais-web-management
  # 配置数据库连接信息
  datasource:
    url: jdbc:mysql://localhost:3306/tlias
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: root
# 配置mybatis-plus
mybatis:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    # 开启  驼峰命名
    map-underscore-to-camel-case: true

# 配置事务管理的日志级别
logging:
  level:
    org.springframework.jdbc.support.JdbcTransactionManager: debug
```

logback.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <!-- 控制台输出 -->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <!-- 日志格式-->
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!-- 格式化输出:
                %d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n
                %d表示日期
                %thread: 线程名
                %-5level: 日志级别，5位长度，左对齐
                %logger{36}: logger的36位长度，左对齐 包含类名
                %msg: 日志消息
            -->
            <pattern>%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <!-- 文件输出 -->
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--日志文件输出的文件名-->
            <fileNamePattern>logs/application.log.%d{yyyy-MM-dd}</fileNamePattern>
            <!--日志文件输出的目录-->
            <!--最多保存30个历史记录文件-->
            <!--<maxHistory>30</maxHistory>-->
            <!--最大文件大小，超过会触发滚动，到新文件，默认为10MB-->
<!--            <maxFileSize>30MB</maxFileSize>-->
        </rollingPolicy>
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <!-- 日志级别设置 -->
    <!--
    log日志级别(从低到高):
        level: ALL> TRACE> DEBUG> INFO> WARN> ERROR> FATAL> OFF
        默认级别: INFO
          ALL: 所有
          TRACE: 追踪
          DEBUG: 调试
          INFO: 信息
          WARN: 警告
          ERROR: 错误
          FATAL: 致命
          OFF: 关闭
    -->
    <!--大于等于level级别的日志，才会输出记录-->
    <root level="info">
        <!--控制STDOUT 输出-->
        <appender-ref ref="STDOUT" />
        <!--控制FILE 输出-->
        <!--关闭FILE输出-->
<!--        <appender-ref ref="FILE" />-->
    </root>
</configuration>
```
