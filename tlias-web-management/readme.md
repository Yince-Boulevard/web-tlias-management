# Day 01
1. Mybatis 默认数据封装规则
    * 实体类名属性名 和 数据库表的字段名一致， mybatis会自动封装
2. 如果字段名与实体类属性名不一致？
    * 手动结果映射 @Result({....})
    * sql语句起别名
    * 开启camel命名开关

3. 接受参数的注解: @RequestParam
4. 接受请求体的注解: @RequestBody

## 获取请求中发送的参数
```java
@RequestParam(value = "属性名", required = "true") Integer id;
```
## 获取请求体中的参数
```java
@RequestBody User user;
```

## 注意事项：
    Mapper层中 接口传入的参数若是一个封装实体，则#{}内直接填属性就行，不用"对象.属性"

## 遇到的问题
    1. lombok失效: 
        **删除pom.xml中的有关lombok的build标签，固定lombok的版本，在dependency中**

# Day 02
## 1. 查询回显
路径参数: 通过请求URL直接传递参数, 使用{...}来标识该路径参数, 需要使用 @PathVariable 获取
格式：
```java
public Result get(@PathVariable("id") Integer id) {
   ;
}
// 若PathVariable中的属性值与参数名一致，可以简化为
public Result get(@PathVariable Integer id) {}
```
## 2. @RequestMapping
若一个Controller中所有方法的请求路径相同，则可以使用@RequestMapping注解 抽取到这个类上, 
里面的方法就不要再补充GetMapping,RestMapping,PostMapping中的value值了

```java
@RequestMapping("/depts")
@RestController
public class DeptController {
    ;
}
```
## 3. 日志记录
JUL: JavaSE官方提供的日志框架

Log4j: 一个流行的日志框架, 提供了灵活的配置选项, 支持多种输出目标

Logback: 基于Log4j的轻量级日志框架, 配置简单, 性能高

SLF4J: 简单日志门面,一个日志统一接口, 不提供具体实现，支持多种日志框架

### Logback 快速入门
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
## 4.分页查询
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
            "name": "技术部",
            ""
         }
      ]
   }
}
```

## 若多表查询结果中有字段在实体类中不存在，则需要手动添加封装该字段
