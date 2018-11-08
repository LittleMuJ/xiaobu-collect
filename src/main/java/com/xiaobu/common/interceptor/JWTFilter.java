package com.xiaobu.common.interceptor;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiaobu.common.util.JwtManager;

public class JWTFilter extends BasicHttpAuthenticationFilter{

	private Logger logger = LoggerFactory.getLogger(JWTFilter.class);
    /**
     * 这里我们详细说明下为什么最终返回的都是true，即允许访问
     * 例如我们提供一个地址 GET /article
     * 登入用户和游客看到的内容是不同的
     * 如果在这里返回了false，请求会被直接拦截，用户看不到任何东西
     * 所以我们在这里返回true，Controller中可以通过 subject.isAuthenticated() 来判断用户是否登入
     * 如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 @RequiresAuthentication 注解即可
     * 但是这样做有一个缺点，就是不能够对GET,POST等请求进行分别过滤鉴权(因为我们重写了官方的方法)，但实际上对应用影响不大
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
           // try {
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                //token验证错误跳转到登录页面
                //responselogin(request,response);
                e.getMessage();
            }
            // } catch (Exception e) {
              //  throw new AuthenticationException(e.getMessage());
                //token验证错误跳转到登录页面
                //responselogin(request,response);
          //  }
        }

        return true;
    }

    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Token字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {

        HttpServletRequest req = (HttpServletRequest) request;
        String aut = req.getHeader("cookie");
        Enumeration<String> eumer=req.getHeaderNames();
        Cookie[] token = req.getCookies();

        String authorization=null;
        if(token != null && token.length>0){
            for(Cookie cookie:token){
                if(cookie.getName().equals("Token")){
                    authorization  = cookie.getValue();
                }
            }
        }
        return authorization != null;
    }
	
	@Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token=null;
        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies != null && cookies.length>0){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("Token")){
                    token  = cookie.getValue();
                }
            }
        }
        JWTToken jwttoken = new JWTToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(jwttoken);
        // 如果没有抛出异常则代表登入成功，返回true
        String requestURI = httpServletRequest.getRequestURI();
        return true;
    }
	
	/**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }


    /**
     * 将非法请求跳转到 /401
     */
    private void responselogin(ServletRequest req, ServletResponse resp) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            Cookie newCookie=new Cookie("Token",null); //假如要删除名称为username的Cookie

            newCookie.setMaxAge(0); //立即删除型

            newCookie.setPath("/"); //项目所有目录均有效，这句很关键，否则不敢保证删除

            httpServletResponse.addCookie(newCookie); //重新写入，将覆盖之前的
            httpServletResponse.sendRedirect("/");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

}
