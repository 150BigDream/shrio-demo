package com.example.shirodemo.service.impl;

import com.example.shirodemo.entity.Account;
import com.example.shirodemo.mapper.AccountMapper;
import com.example.shirodemo.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zz
 * @since 2020-11-27
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {


}
