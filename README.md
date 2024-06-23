# sc_example
Spring Cloud 使用例子

## 使用示例

### 项目运行
1、cd sc_example  
2、mvn install  
3、下载 zipkin-server-2.23.19-exec.jar  
4、执行 java -jar zipkin-server-2.23.19-exec.jar  
5、cd sc_example  
6、依次运行各个子工程目录下的 start.sh/start.cmd 脚本  
7、项目启动完毕

### 项目停止
1、依次运行各个子工程目录下的 kill.sh/kill.cmd 脚本  
2、停止 zipkin-server-2.23.19-exec.jar

## URL
- 访问 Eureka http://localhost:28081 admin/admin
- 通过 Zuul 访问 My-Wbe http://localhost:28101/my-web/echo?s=1
- 通过 Zuul 和 My-Wbe-Bridge 访问 My-Web http://localhost:28101/my-web-bridge/echo?s=1
- 访问 ZipKin http://localhost:9411
