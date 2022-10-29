package com.example.rgtask.config;

import com.example.rgtask.shiro.JwtFilter;
import com.example.rgtask.shiro.JwtRealm;
import org.apache.shiro.mgt.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

//    /**
//     * 禁用session, 不保存用户登录状态。保证每次请求都重新认证
//     */
//    @Bean
//    protected SessionStorageEvaluator sessionStorageEvaluator() {
//        DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
//        sessionStorageEvaluator.setSessionStorageEnabled(false);
//        return sessionStorageEvaluator;
//    }
//    //告诉shiro不创建内置的session
//    @Bean
//    public SubjectFactory subjectFactory(){
//        return new JwtDefaultSubjectFactory();
//    }
    @Bean
    public FilterRegistrationBean corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        // 允许cookies跨域
        config.setAllowCredentials(true);
        // #允许向该服务器提交请求的URI，*表示全部允许，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
        config.addAllowedOriginPattern("*");
        // #允许访问的头信息,*表示全部
        config.addAllowedHeader("*");
        // 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        config.setMaxAge(18000L);
        // 允许提交请求的方法，*表示全部允许
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);

        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        // 设置监听器的优先级
        bean.setOrder(0);

        return bean;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, JwtFilter jwtFilter) {

        Logger logger = LoggerFactory.getLogger(getClass());

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的".jsp"页面 或 "/login" 映射
        shiroFilterFactoryBean.setLoginUrl("/notLogin");
        // 设置无权限时跳转的 url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");

        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("jwtFilter", jwtFilter);
        shiroFilterFactoryBean.setFilters(filters);

//        shiroFilterFactoryBean.setLoginUrl("/login");


        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        //游客，开发权限
//        filterChainDefinitionMap.put("/guest/**", "anon");
//        //用户，需要角色权限 “user”
//        filterChainDefinitionMap.put("/user/**", "roles[user]");
//        //管理员，需要角色权限 “admin”
//        filterChainDefinitionMap.put("/admin/**", "roles[admin]");
//        //开放登陆接口
//        filterChainDefinitionMap.put("/login", "anon");

        //开放swagger接口
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        //开放activiti接口
        filterChainDefinitionMap.put("/static/modeler.html", "anon");


        // 根据需要配置需要拦截的url

        filterChainDefinitionMap.put("/api/sys/smsConfAliyun/**", "jwtFilter");// 阿里云短信测试
        filterChainDefinitionMap.put("/api/sys/smsConfTencent/**", "jwtFilter");// 腾讯云短信测试
        filterChainDefinitionMap.put("/api/sys/smsTemplateManagement/**", "jwtFilter");// 配置分页测试

        filterChainDefinitionMap.put("/api/login", "anon");// 开放登录接口
        filterChainDefinitionMap.put("/api/cas", "anon");// 开放cas登录接口
        filterChainDefinitionMap.put("/api/token/refresh", "anon");// 开放token刷新接口

        filterChainDefinitionMap.put("/api/user/**", "jwtFilter");
        filterChainDefinitionMap.put("/api/asd","jwtFilter");
        filterChainDefinitionMap.put("/api/aaa","jwtFilter");
//        filterChainDefinitionMap.put("/user/**","jwtFilter");

        //其余接口一律拦截
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        filterChainDefinitionMap.put("/**", "anon");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        logger.info("执行顺序 : " + filterChainDefinitionMap);

        logger.info("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor
                = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    /**
     * 注入 securityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(jwtRealm());

        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);


        return securityManager;
    }

    /**
     * 自定义身份认证 realm;
     * <p>
     * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm，
     * 否则会影响 CustomRealm类 中其他类的依赖注入
     */
    @Bean
    public JwtRealm jwtRealm() {
        return new JwtRealm();
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }
}
