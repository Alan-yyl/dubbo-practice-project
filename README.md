# dubbo-practice-project
这是一个学习dubbo的项目

# 项目搭建步骤

## 暴露服务

>  将服务提供者注册到注册中心

### 服务消费者

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

2. 编写[consumer.xml](https://dubbo.apache.org/zh/docs/v2.7/user/quick-start/)文件

   ```
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans.xsd
   	   http://code.alibabatech.com/schema/dubbo http://code.alibabatech.com/schema/dubbo/dubbo.xsd">
   
       <!-- 消费方应用名，用于计算依赖关系，不是匹配条件，不要与提供方一样 -->
       <dubbo:application name="order-service-consumer"/>
   
       <!--  指定注册中心的位置-->
       <dubbo:registry address="zookeeper://"/>
   
       <!-- 订阅服务 -->
       <dubbo:reference id="userService" interface="org.alan.dubbo.service.UserService"/>
   </beans>
   ```

3. 在`order-service-consumer`中写一个启动类，测试服务消费者是否获取到服务

   ```
   import org.alan.dubbo.bean.UserAddress;
   import org.alan.dubbo.service.UserService;
   import org.springframework.context.support.ClassPathXmlApplicationContext;
   
   import java.util.List;
   import org.alan.dubbo.bean.UserAddress;
   
   /**
    * @Author: Alan
    * @Date: 2021/5/4 17:08
    */
   public class MainApplication {
       public static void main(String[] args) throws Exception {
           ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
           context.start();
           //获取远程服务
           UserService userService = (UserService) context.getBean("userService");
           //执行远程方法
           List<UserAddress> list = userService.getUserAddressList("test");
           for (UserAddress userAddress : list) {
               System.out.println(userAddress.getUserAddress());
           }
           // 按任意键退出
           System.in.read();
       }
   }
   ```

4. 此时应该确保服务提供者也正常启动，否则消费者是无法获取到服务的

   ![image-20210504173941751](/Users/alan/Library/Application Support/typora-user-images/image-20210504173941751.png)

   ![image-20210504174537400](/Users/alan/Library/Application Support/typora-user-images/image-20210504174537400.png)

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

