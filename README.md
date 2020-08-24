## dcy-dubbo-cloud

spring cloud alibaba + dubbo 2.7.8 对内RPC 对外REST

## 框架集成

集成 | 完成 
----|----
Spring Boot | ✔
Spring Cloud Alibaba | ✔ 
Spring Cloud Alibaba Dubbo | ✔ 
Spring Cloud Alibaba Sentinel | ✘ 
Spring Cloud Alibaba Seata | ✘ 
Spring Security oAuth2 | ✔
Swagger-ui |  ✔
MyBatis Plus| ✔ 
HikariCP | ✔ 
OKHttp3 | ✘ 
Feign 传递 Token | ✘ 


## 后台功能清单

### 认证授权

功能 | 完成 
----|----
用户注册 | ✘ 
用户登录 | ✔ 
获取 Token | ✔ 

### 个人信息

功能 | 完成 
----|----
查看信息 | ✘ 
更新信息 | ✘ 
更新密码 | ✘ 
更新头像 | ✘ 

### 权限管理

角色 | 菜单 
----|----
超级管理员 | 所有菜单权限 
运营人员 | 首页、用户、促销、运营、内容 

功能 | 说明 | 完成 
----|----|----
权限管理 | 添加权限、删除权限、修改权限、以树形结构返回权限 | 
角色管理 | 添加角色、删除角色、更新角色、角色列表、获取角色权限、修改角色权限 | 
成员管理 | CRUD、为成员分配角色、获取成员角色、权限分配、获取权限列表 | 
