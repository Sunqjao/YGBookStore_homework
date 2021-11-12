package com.sun.interceptor;

import com.sun.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器处理");

        HttpSession session = request.getSession();
        User user=(User) session.getAttribute("user");
        if(request.getRequestURI().contains("toInformationModification")||request.getRequestURI().contains("updateInformation")){

            System.out.println(user);
            if(user==null||user.getId()==null){
                request.getRequestDispatcher("/WEB-INF/jsp/information.jsp").forward(request,response);
                System.out.println("修改信息被拦截");
                return false;
            }
        }
        if(request.getRequestURI().contains("toRecharge")||request.getRequestURI().contains("recharge")){

            System.out.println(user);
            if(user==null||user.getId()==null){
                request.getRequestDispatcher("/WEB-INF/jsp/information.jsp").forward(request,response);
                System.out.println("修改信息被拦截");
                return false;
            }
        }
        if(request.getRequestURI().contains("addToCart")){
            System.out.println(user);
            if(user==null||user.getId()==null){
                request.getRequestDispatcher("/WEB-INF/jsp/addFail.jsp").forward(request,response);
                System.out.println("加入购物车拦截");
                return false;
            }
        }
        if(request.getRequestURI().contains("payFor")){
            System.out.println(user);
            if(user==null||user.getId()==null||user.getAddress()==null||user.getAddress().equals("")
                    ||user.getPhone()==null||user.getPhone().equals("")){
                request.setAttribute("message","请完善信息");
                request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request,response);
                System.out.println("支付拦截");
                return false;
            }
            System.out.println("--------------");
            if(Float.parseFloat(request.getParameter("sum"))>user.getMoney()){
                request.getRequestDispatcher("/WEB-INF/jsp/recharge.jsp").forward(request,response);
                System.out.println("支付拦截余额不足");
                return false;
            }
        }
        return true;
    }
}
