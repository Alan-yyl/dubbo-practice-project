package org.alan.dubbo.service;

import org.alan.dubbo.bean.UserAddress;

import java.util.List;


/**
 * 用户服务
 */
public interface UserService {
	
	/**
	 * 按照用户id返回所有的收货地址
	 * @param userId
	 * @return
	 */
	public List<UserAddress> getUserAddressList(String userId);

}
