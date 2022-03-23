package com.ljz.loverspace.config;

import com.ljz.loverspace.service.UserService;
import com.ljz.loverspace.shiro.MyRealm;
import com.ljz.loverspace.shiro.ShiroAuthFilter;
import com.ljz.loverspace.shiro.ShiroPermissionsAuthorizationFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.Map;

@Configuration
public class ShiroConfig {

    @Lazy
    @Autowired
    UserService userService;

    @Bean
    public Realm realm() {
        return new MyRealm();
    }
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        // 关闭 ShiroDAO 功能
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        // 不需要将 Shiro Session 中的东西存到任何地方（包括 Http Session 中）
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
        creator.setUsePrefix(true);
        return creator;
    }
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        factoryBean.setSecurityManager(securityManager);

        //添加filter，factoryBean.getFilters()获取到的是引用，可直接添加值
        factoryBean.getFilters().put("jwt", new ShiroAuthFilter());
        factoryBean.getFilters().put("customPerms", new ShiroPermissionsAuthorizationFilter());

        /**
         * factoryBean.getFilterChainDefinitionMap();默认是size=0的LinkedHashMap
         */
        Map<String, String> definitionMap = factoryBean.getFilterChainDefinitionMap();

        /**
         * definitionMap是一个LinkedHashMap，是一个链表结构
         * put的顺序很重要，当/open/**匹配到请求url后，将不再检查后面的规则
         * 官方将这种原则称为 FIRST MATCH WINS
         * https://waylau.gitbooks.io/apache-shiro-1-2-x-reference/content/III.%20Web%20Applications/10.%20Web.html
         */
        definitionMap.put("/api/open/**", "anon");
        definitionMap.put("/api/user/login", "anon");
        definitionMap.put("/api/user/register", "anon");


        /**
         * 由于禁用了session存储，shiro不会存储用户的认证状态，所以在接口授权之前要先认证用户，不然CustomPermissionsAuthorizationFilter不知道用户是谁
         * 实际项目中可以将这些接口权限规则放到数据库中去
         */

        // 前面的规则都没匹配到，最后添加一条规则，所有的接口都要经过com.example.shirojwt.filter.JwtFilter这个过滤器验证
        definitionMap.put("/api/**", "jwt");

        factoryBean.setFilterChainDefinitionMap(definitionMap);

        return factoryBean;
    }


}

