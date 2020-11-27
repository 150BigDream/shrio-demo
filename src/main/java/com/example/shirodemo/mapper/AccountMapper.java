package com.example.shirodemo.mapper;

import com.example.shirodemo.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zz
 * @since 2020-11-27
 */
public interface AccountMapper extends BaseMapper<Account> {
    @Select("select * from account where username=#{name}")
    Account findByUsername(String name);

}
