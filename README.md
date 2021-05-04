# dubbo-practice-project
这是一个学习dubbo的项目

# 项目搭建步骤

## 暴露服务

>  将服务提供者注册到注册中心

### 服务消费者

1. 导入dubbo依赖\操作zookeeper的客户端（curator）

### 服务提供者

1. 导入dubbo依赖和curator客户端依赖

   ```xml
   <dependency>
     <groupId>com.alibaba</groupId>
     <artifactId>dubbo</artifactId>
     <version>2.6.2</version>
   </dependency>
   
   <dependency>
     <groupId>org.apache.curator</groupId>
     <artifactId>curator-framework</artifactId>
     <version>4.2.0</version>
   </dependency>
   ```

2. 编写[provider.xml](https://dubbo.apache.org/zh/docs/v2.7/user/quick-start/)文件

   > 我使用dubbo 2.6.2，schema按照下面的方式配置不会报错

   ````
   <beans xmlns="http://www.springframework.org/schema/beans"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
   xsi:schemaLocation="http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans.xsd
   http://code.alibabatech.com/schema/dubbo http://code.alibabatech.com/schema/dubbo/dubbo.xsd">
   
   <!--    1、指定当前服务的名字-->
   <dubbo:application name="user-service-provider"/>
   <!--    2、指定注册中心的位置-->
   <dubbo:registry address="zookeeper://"/>
   <!--    3、指定通信协议-->
   <dubbo:protocol name="dubbo" port="20880"/>
   <!--    4、暴露服务 ref:指定服务的实现对象-->
   <dubbo:service interface="org.alan.dubbo.service.UserService" ref="userServiceImp"/>
   <!--    5、服务的实现-->
   <bean id="userServiceImp" class="org.alan.dubbo.service.impl.UserServiceImpl"/>
   </beans>
   ````

3. 在`user-service-provider`中写一个启动类，测试服务提供者是否注册进zookeeper中

   ```
   import org.springframework.context.support.ClassPathXmlApplicationContext;
   
   import java.io.IOException;
   
   /**
    * @Author: Alan
    * @Date: 2021/5/3 23:30
    */
   public class MainApplication {
       public static void main(String[] args) throws IOException {
           ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
           context.start();
           System.in.read(); // 按任意键退出
       }
   }
   ```

4. 登陆zookeeper管理界面，查看服务是否成功注册。如下图 ，表示成功注册

   ![image-20210504164646978](/Users/alan/Library/Application Support/typora-user-images/image-20210504164646978.png)

![image-20210504164818674](/Users/alan/Library/Application Support/typora-user-images/image-20210504164818674.png)