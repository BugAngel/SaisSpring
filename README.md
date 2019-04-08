# SaisSpring
SpringBoot实现留学信息管理与分析系统。 项目基础为本人寒假时项目[SaisTP5](https://github.com/BugAngel/SaisTP5)，本项目中改用springboot框架并添加论坛功能。  
sais.sql为数据库文件，要求mysql版本最低为5.7  

## 展示地址：  
[前台地址](http://39.105.77.133:8083)  
[后台地址](http://39.105.77.133:8083/admin/login/login)   
测试账号HanLi 密码123456  
  
## 实现功能： 
##### 系统：
1、提供用户的登录注册与退出功能  
2、提供智能推荐功能，可由访问院校详情次数改变学校权重，进而更改系统首页幻灯片介绍的学校   
3、提供学校简略信息列表和详细信息界面   
4、使用拦截器，未登录不能访问论坛与院校详情，访问这些界面会直接跳转到登录界面   
5、提供头像上传，修改个人信息，修改密码功能     
6、提供介绍与帮助界面   

##### 论坛：
1、可以进行发帖，转发，评论操作  
2、可以对帖子进行收藏点赞   
3、可以多重转发  
4、提供关注与取消关注功能   
5、提供好友主页，可以查看发帖人的信息与历史发帖   
6、提供个人主页，可以查看自己的发帖、评论、点赞、收藏、关注等信息  

##### 后台：
1、提供管理员的登录与退出功能  
2、提供修改密码功能  
3、可对投诉信息进行查看删除操作  
4、可对用户信息进行查看删除禁言操作  
5、可对幻灯片进行查看修改删除操作   
6、使用拦截器，未登录只能访问登录界面

  
### 模块介绍  
sais:父模块  
sais-base:基础模块，用于提供底层工具与依赖  
sais-entity:实体模块，用于提供数据库对应实体与web操作需要的实体  
sais-mapper:映射模块，提供mybatis操作的接口  
sais-service:服务模块，提供服务  
sais-web:web服务模块，admin对应后台模块，college对应系统展示部分，microblog对应论坛部分，common为公共部分  
 
#
#### 参考网站

[SpringBoot入门（IDEA篇）（三）](https://www.cnblogs.com/zmfx/p/9020514.html)  
[springboot配置文件 application.yml注意事项（Failed to load property source from location 'classpath:/applica）](https://blog.csdn.net/linjy520/article/details/79455842)  
[SpringBoot整合Mybatis完整详细版](https://blog.csdn.net/iku5200/article/details/82856621)  
[Java读取 Mysql的 datetime类型](https://blog.csdn.net/w690333243/article/details/76565998)  
[java向MySQL插入当前时间的几种方式](https://blog.csdn.net/qq_39809458/article/details/82771351)  
[MySQL中datetime和timestamp的区别及使用](https://www.cnblogs.com/mxwz/p/7520309.html)  
[springboot解决utf8mb4类型连接](https://blog.csdn.net/u013013170/article/details/79209444)  
[mybatis常用jdbcType数据类型以及对应的JavaType](https://www.cnblogs.com/yucongblog/p/7388648.html)  
[com.mysql.jdbc.Driver 和 com.mysql.cj.jdbc.Driver的区别 serverTimezone设定](https://blog.csdn.net/superdangbo/article/details/78732700)  
[[Mybatis] 读取数据库时间出现时间差](https://www.jianshu.com/p/70f4d748a7fb)  
[MONGODB数据到MYSQL数据库的迁移步骤](https://www.cnblogs.com/xingyunfashi/p/8796107.html)  
[spring-boot2.0 thymeleaf bootstrap 整合示例](https://blog.csdn.net/u013506207/article/details/82354970)  
[Group: Webjars Bower](https://mvnrepository.com/artifact/org.webjars.bower)  
[SpringBoot：整合Lombok](https://blog.csdn.net/u011976388/article/details/85239750)  
[IDEA SpringBoot多模块项目搭建详细过程](https://blog.csdn.net/zcf980/article/details/83040029)  
[IDEA中SQL语句提示SQL Dialect is Not Configured](https://blog.csdn.net/xiongchun11/article/details/78202018/)  
[springboot工程pom的两种配置方式](https://www.cnblogs.com/hujunzheng/p/7146274.html)  
[Maven学习 (六) 搭建多模块企业级项目](http://www.cnblogs.com/quanyongan/archive/2013/05/28/3103243.html)  
[Failed to configure a DataSource](https://blog.csdn.net/u010448530/article/details/80840828)  
[spring boot多环境多文件配置profiles和no profiles are currently active错误问题。](https://blog.csdn.net/qq_36368721/article/details/83542882)  
[Windows下安装Redis服务](https://www.cnblogs.com/jaign/articles/7920588.html)  
[使用Jedis操作redis](https://www.cnblogs.com/relucent/p/4203190.html)  
[22.Spring-Boot中Spring Session介绍](https://blog.csdn.net/niugang0920/article/details/79644842)  
[SpringBoot跳转页面详解+thymeleaf](https://blog.csdn.net/jintingbo/article/details/81633615)  
[Spring boot——logback 基础使用篇（一）](https://www.cnblogs.com/lixuwu/p/5804793.html)  
[页面引用bootstrap报错Bootstrap's JavaScript requires jQuery](https://blog.csdn.net/liuchang__/article/details/71403194)  
[SpringBoot2.x过后static下的静态资源无法访问](https://blog.csdn.net/wenxingchen/article/details/84139845)  
[使用 SpringBoot + kaptcha 生成、校对 验证码](https://blog.csdn.net/larger5/article/details/79522105)  
[使用@Autowired注解警告Field injection is not recommended](https://blog.csdn.net/zhangjingao/article/details/81094529)  
[记一次SpringBoot整合thymeleaf layout模板的经历](https://blog.csdn.net/dingse/article/details/80509208)  
[fastjson 的使用总结](https://www.cnblogs.com/dmego/p/9033080.html)  
[SpringBoot 获取当前登录用户IP](https://www.cnblogs.com/mr-wuxiansheng/p/7773121.html)  
[使用SpringBoot的关于页面跳转的问题](https://www.cnblogs.com/mr-wuxiansheng/p/7749549.html)  
[springboot拦截器判断是否登录](https://blog.csdn.net/zyp1376308302/article/details/81257510)  
[The type WebMvcConfigurerAdapter is deprecated springboot拦截器](https://www.cnblogs.com/bigorang/p/9010306.html)  
[关于thymeleaf+layout布局的使用方式](https://www.jianshu.com/p/3b5ebc545a99)  
[mybatis批量删除（逻辑删除）](https://www.cnblogs.com/lr393993507/p/5937596.html)  
[精通SpringBoot——第十二篇：分页查询功能的实现](https://yq.aliyun.com/articles/629124)  
[springboot整合pagehelper实现分页](https://blog.csdn.net/qq_34021712/article/details/80159601)  
[SpringMVC注解@RequestParam全面解析](https://www.cnblogs.com/likaileek/p/7218252.html)   
[Springboot下thymeleaf的使用](https://www.jianshu.com/p/a836bdd9dbd4)  
[springboot+thymeleaf模板的一个问题，搞了一天才搞定。](https://blog.51cto.com/cnn237111/1968163)  
[springboot使用Freemarker继承](https://blog.csdn.net/liuyinxinall/article/details/71159929)  
[Spring Boot使用模板freemarker【从零开始学Spring Boot(转)](https://www.cnblogs.com/jpfss/p/8309996.html)  
[SpringBoot 实现文件上传，图片上传并显示功能](https://blog.csdn.net/qq_38762237/article/details/81282444)  
[FreeMarker 获取页面request、session](https://blog.csdn.net/feiyu8607/article/details/6557159)  
[简单在线论坛](https://github.com/MQPearth/EasyBBS)  
[ubuntu16.04下安装mysql详细步骤](https://blog.csdn.net/itxiaolong3/article/details/77905923)  
[Java将数组用固定分隔符拼接成字符串](https://blog.csdn.net/huanghanqian/article/details/86361386)  
[springboot 项目打包部署后设置上传文件访问的绝对路径](https://www.cnblogs.com/kingsonfu/p/9941101.html)  
[Spring Boot Maven Plugin打包异常及三种解决方法：Unable to find main class](https://www.cnblogs.com/thinking-better/p/7827368.html)  
[Springboot 打包跳过测试](https://blog.csdn.net/suoyasong/article/details/82978834)  
[IDEA maven 多模块打包问题总结](https://www.jianshu.com/p/37c6688c4fcb)  
[SpringBoot多Module打包无法找到类](https://segmentfault.com/q/1010000011151233)  
[SpringBoot 多模块项目实践（附打包方法）](https://www.jianshu.com/p/59ceea4f029d)  
[SpringBoot session超时的问题](https://www.cnblogs.com/ergexy/p/9684933.html)  
[Freemarker 运算符](https://blog.csdn.net/u014656173/article/details/76577338)  
[freemarker获取url中的参数](https://blog.csdn.net/shijiedemuguang/article/details/61935312)  
[mybatis中能不能一个配置信息里面写多条sql语句](https://blog.csdn.net/Abracadabra__/article/details/84381545)  
[如何使用分页插件](https://pagehelper.github.io/docs/howtouse/)  
[FastJson对于JSON格式字符串、JSON对象及JavaBean之间的相互转换](https://www.cnblogs.com/cdf-opensource-007/p/7106018.html)  
[对map集合进行排序](https://www.cnblogs.com/liujinhong/p/6113183.html)  
[前端获取数据库的datetime类型为一串数字（时间戳）](https://blog.csdn.net/qq_36908841/article/details/81666084)  

