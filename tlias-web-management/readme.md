1. Mybatis 默认数据封装规则
    * 实体类名属性名 和 数据库表的字段名一致， mybatis会自动封装
2. 如果字段名与实体类属性名不一致？
    * 手动结果映射 @Result({....})
    * sql语句起别名
    * 开启camel命名开关

3. 接受参数的注解: @RequestParam
4. 接受请求体的注解: @RequestBody


# 注意事项：
    Mapper层中 接口传入的参数若是一个封装实体，则#{}内直接填属性就行，不用"对象.属性"

# 遇到的问题
    1. lombok失效: 
        删除pom.xml中的有关lombok的build标签，固定lombok的版本，在dependency中
