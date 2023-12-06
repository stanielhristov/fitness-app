//package com.example.individualprojectstaniel.config;
//
//import org.aopalliance.intercept.MethodInvocation;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
//import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
//import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
//import org.springframework.security.core.Authentication;
//
//@Configuration
//public class RoleHierarchyConfig extends DefaultMethodSecurityExpressionHandler {
//
//    private final RoleHierarchy roleHierarchy;
//
//    public RoleHierarchyConfig(RoleHierarchy roleHierarchy) {
//        this.roleHierarchy = roleHierarchy;
//    }
//
//    @Override
//    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
//        RoleHierarchyConfig root = new RoleHierarchyConfig(roleHierarchy);
//        root.setRoleHierarchy(roleHierarchy);
//        return root.createSecurityExpressionRoot(authentication, invocation);
//    }
//
////    @Bean
////    public SecurityExpressionHandler<FilterInvocation> customWebSecurityExpressionHandler() {
////        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
////        expressionHandler.setRoleHierarchy(roleHierarchy());
////        return expressionHandler;
////    }
//}
