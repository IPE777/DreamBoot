package com.wangyuhang.dream_boot.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wangyuhang.dream_boot.common.Result;
import com.wangyuhang.dream_boot.entity.Customer;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface CustomerService extends IService<Customer> {

    Result selectAllCustomer(Integer start, Integer end);

    Result selectById(Integer id);

    Result selectByName(String name);

    Result addCustomer(Customer customer);

    Result updateCustomer(Customer customer);

    Result deleteById(Integer id);

    Boolean existCustomer(String username);

    Customer findByUserName(String username);

    Result selectMyContent(Integer start, Integer end,Integer customerId);
}
