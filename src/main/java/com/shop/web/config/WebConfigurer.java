package com.shop.web.config;

import com.shop.web.interceptor.AdminInterceptor;
import com.shop.web.interceptor.ShopInterceptor;
import com.shop.web.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry){
        //只有已登录user账户才能访问的地址
        List<String>userExUrl = new ArrayList<>();
        userExUrl.add("http://localhost/:5173/shangchengshouye");
        userExUrl.add("http://localhost/:5173/gouwuche");
        userExUrl.add("http://localhost/:5173/dingdan");
        userExUrl.add("http://localhost/:5173/gerenzhongxin");
        userExUrl.add("http://localhost/:5173/fenleixiangqing/*");
        userExUrl.add("http://localhost/:5173/sousuojieguo/*");
        userExUrl.add("http://localhost/:5173/shangpinxiangqing/*");
        userExUrl.add("http://localhost/:5173/dingdanxiangqing/*");
        userExUrl.add("http://localhost/:5173/pay/*");
        userExUrl.add("http://localhost/:5173/gouwuchedingdan/*");
        userExUrl.add("http://localhost/:5173/gouwuchefukuan/*");
        userExUrl.add("http://localhost/:5173/dingdanxiangqingye/*");
        userExUrl.add("http://localhost/:5173/pinglun/*");



        //只有已登录shop账户才能访问的地址
        List<String>shopExUrl = new ArrayList<>();
        shopExUrl.add("http://localhost:5173/shop");
        shopExUrl.add("http://localhost:5173/shopinfo");
        shopExUrl.add("http://localhost:5173/shoplogin");
        shopExUrl.add("http://localhost:5173/shopregister");
        shopExUrl.add("http://localhost:5173/goodscreate");
        shopExUrl.add("http://localhost:5173/goodsdetail");
        shopExUrl.add("http://localhost:5173/goodsinfo");
        shopExUrl.add("http://localhost:5173/orderinfo");
        shopExUrl.add("http://localhost:5173/notice");


        //只有已登录admin账户才能访问的地址
        List<String>adminExUrl = new ArrayList<>();
        adminExUrl.add("http://localhost/:5173/guanli");
        adminExUrl.add("http://localhost/:5173/xitongshouye");
        adminExUrl.add("http://localhost/:5173/shangpin");
        adminExUrl.add("http://localhost/:5173/sousuo/*");
        adminExUrl.add("http://localhost/:5173/guanlishangpinxiangqing/*");
        adminExUrl.add("http://localhost/:5173/yonghuxinxi");
        adminExUrl.add("http://localhost/:5173/gonggao");
        adminExUrl.add("http://localhost/:5173/guanlidingdan");
        adminExUrl.add("http://localhost/:5173/guanlishangjia");
        adminExUrl.add("http://localhost/:5173/guanlipinglun");
        adminExUrl.add("http://localhost/:5173/shezhileibie");
        adminExUrl.add("http://localhost/:5173/xinzengshangpin");
        adminExUrl.add("http://localhost/:5173/leibiexiangqing/*");
        adminExUrl.add("http://localhost/:5173/fabugonggao");






        registry.addInterceptor(new UserInterceptor()).addPathPatterns(userExUrl);
        registry.addInterceptor(new ShopInterceptor()).addPathPatterns(shopExUrl);
         registry.addInterceptor(new AdminInterceptor()).addPathPatterns(adminExUrl);
    }
}
