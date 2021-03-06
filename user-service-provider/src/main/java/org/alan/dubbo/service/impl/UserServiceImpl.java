package org.alan.dubbo.service.impl;

import org.alan.dubbo.bean.UserAddress;
import org.alan.dubbo.service.OrderService;
import org.alan.dubbo.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Alan
 * @Date: 2021/5/3 22:12
 */
public class UserServiceImpl implements UserService{
    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        UserAddress address1 = new UserAddress(1, "北京市昌平区宏福科技园综合楼3层", "1", "李老师", "010-56253825", "Y");
        UserAddress address2 = new UserAddress(2, "深圳市宝安区西部硅谷大厦B座3层（深圳分校）", "1", "王老师", "010-56253825", "N");

        return Arrays.asList(address1,address2);
    }
}
