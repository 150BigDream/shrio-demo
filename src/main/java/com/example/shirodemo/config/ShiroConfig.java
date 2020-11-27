package com.example.shirodemo.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.example.shirodemo.realm.AccountRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Hashtable;
import java.util.Map;
@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean，过滤器工厂，Shiro 的基本运行机制是开发者定制规则，Shiro 去执行，具体的执行操作就是由 ShiroFilterFactoryBean 创建的一个个 Filter 对象来完成。
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        //权限设置
        Map<String,String> map=new Hashtable<>();
        map.put("/main","authc");
        map.put("/manager","perms[manager]");
        map.put("/admin","roles[admin]");
        factoryBean.setFilterChainDefinitionMap(map);
        //设置登陆页面
        factoryBean.setLoginUrl("/login");
        //未授权页面
        factoryBean.setUnauthorizedUrl("/unauth");
        return factoryBean;
    }


    //@Qualifier("accountRealm")从容器里面找这个对象
    //DefaultWebSecurityManager，安全管理器，开发者自定义的 Realm 需要注入到 DefaultWebSecurityManager 进行管理才能生效。
    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("accountRealm") AccountRealm accountRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(accountRealm);
        return manager;
    }

    //注入IOC容器，产生一个名字叫accountRealm（方法名）的bean对象
    @Bean
    public AccountRealm accountRealm(){
        return new AccountRealm();
    }

    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
