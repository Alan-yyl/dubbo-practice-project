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
