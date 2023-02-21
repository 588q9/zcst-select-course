

基于RestTemplate+quartz+springboot的 自动化定时抢课系统

netty提供异步支持，利用nio的特性以便能够及时发送选课请求

通过定时任务实现24小时不断进行选课操作以便能够选中所需的空闲的课程
## 使用方法

直接通过java命令运行jar包

用户数据要写在 application-privateinfo.yml,该文件要自己创建并放入类目录中,并且使用时要将application.yml的active字段改为prod

application-privateinfo.yml 格式

```yaml
user:
    #如果没有引号会注入错误
    password: "XXX"
    student-number: "xxxx"
    course-list:
        - {kcmc: "1",kch_id: "",jxb_ids: "",jxb_id: ""}
        - {kcmc: "1",kch_id: "",jxb_ids: "",jxb_id: ""}
```

注意application.yml中cron-expression是要选课时间的cron表达式


# **本项目仅用于学习和交流，以及各种充满正能量的目的，请勿滥用**


