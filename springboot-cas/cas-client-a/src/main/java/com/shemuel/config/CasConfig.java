package com.shemuel.config;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: dengshaoxiang
 * @Date: 2019/7/29 14:10
 * @Description:
 */
@Configuration
public class CasConfig {
    @Value("${cas.server-url-prefix}")
    private String casServerUrl;
    @Value("${cas.client-host-url}")
    private String casServer;
    @Value("${cas.server-login-url}")
    private String casLoginUrl;

    /**
     * @description: 登录成功后cas server会重定向到之前要访问的页面并携带ticket,该ticket需要重新进行校验认证
     *  以下过滤器负责对Ticket的校验工作，必须启用它
     * @param
     * @date: 2019/7/29 14:51
     * @author: dengshaoxiang
     */
    @Bean
    public FilterRegistrationBean ValidationFilterRegistrationBean() {
        FilterRegistrationBean validationFilterRegistrationBean = new FilterRegistrationBean();
        validationFilterRegistrationBean.setFilter(new Cas30ProxyReceivingTicketValidationFilter());
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("casServerUrlPrefix", casServerUrl);
        initParameters.put("serverName", casServer);
        validationFilterRegistrationBean.setInitParameters(initParameters);
        validationFilterRegistrationBean.setOrder(3);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");// 设置匹配的url
        validationFilterRegistrationBean.setUrlPatterns(urlPatterns);
        return validationFilterRegistrationBean;
    }

    // 该过滤器负责用户的认证过程
    @Bean
    public FilterRegistrationBean authenticationFilterRegistrationBean() {
        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
        authenticationFilter.setFilter(new AuthenticationFilter());
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("casServerLoginUrl", casLoginUrl);
        initParameters.put("serverName", casServer);
        // 配置取消拦截
        // initParameters.put("excludePaths", excludePaths);
        authenticationFilter.setInitParameters(initParameters);
        authenticationFilter.setOrder(4);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");// 设置匹配的url
        authenticationFilter.setUrlPatterns(urlPatterns);
        return authenticationFilter;
    }

    /**
     * @description: 该过滤器负责http请求的包裹,
     *      比如允许开发者通过HttpServletRequest的getRemoteUser()方法获得SSO登录用户的登录名，可选配置
     * @param
     * @date: 2019/7/29 14:55
     * @author: dengshaoxiang
     */
    @Bean
    public FilterRegistrationBean httpServletRequestWrapperFilter() {
        FilterRegistrationBean httpServletRequestWrapperFilter = new FilterRegistrationBean();
        httpServletRequestWrapperFilter.setFilter(new HttpServletRequestWrapperFilter());
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");// 设置匹配的url
        httpServletRequestWrapperFilter.setUrlPatterns(urlPatterns);
        return  httpServletRequestWrapperFilter;
    }

    /**
     * @description: 该过滤器使得开发者可以通过org.jasig.cas.client.util.AssertionHolder来获取用户的登录名。
     * 比如AssertionHolder.getAssertion().getPrincipal().getName()
     * @param
     * @date: 2019/7/29 14:58
     * @author: dengshaoxiang
     */
    @Bean
    public FilterRegistrationBean assertionThreadLocalFilter () {
        FilterRegistrationBean assertionThreadLocalFilter = new FilterRegistrationBean(new AssertionThreadLocalFilter());
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");// 设置匹配的url
        assertionThreadLocalFilter.setUrlPatterns(urlPatterns);
        return assertionThreadLocalFilter;
    }
}
