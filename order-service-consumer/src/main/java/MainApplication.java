import org.alan.dubbo.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

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
        List hello = userService.getUserAddressList("test");
        System.out.println(hello);
        // 按任意键退出
        System.in.read();
    }
}
