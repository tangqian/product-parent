package com.ofweek.live.core.modules.sys.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ofweek.live.core.common.utils.StringUtils;
import org.apache.shiro.session.SessionException;

public class WebUtils {
	
    private static String COOKIE_OFWEEK_AK = "www_ofweek_ak";
    
    private static String COOKIE_OFWEEK_MEMBER = "www_ofweekmember";
    
    
    public static void removeOfweekCookie(HttpServletResponse response) {
        killCookie(response, COOKIE_OFWEEK_MEMBER);
        killCookie(response, COOKIE_OFWEEK_AK);
    }
    
    public static String getAccountFromCookie(HttpServletRequest request) {
        String account = "";
        String ofweekCookie = getCookieValue(request, COOKIE_OFWEEK_MEMBER);
        if (StringUtils.isNotBlank(ofweekCookie)) {
        	account = ofweekCookie.split("NPofweek")[0].toString();
            try {
            	account = URLDecoder.decode(account, "UTF-8");
            } catch (UnsupportedEncodingException e) {
            }
        }
        return account;
    }
    
    public static String getAkFromCookie(HttpServletRequest request) {
        return getCookieValue(request, COOKIE_OFWEEK_AK);
    }

    public static String getUrl(HttpServletRequest request){
        StringBuffer sb = request.getRequestURL();
        System.out.println(sb.toString());
        if(request.getQueryString() != null){
            sb.append("?").append(request.getQueryString());
        }
        return sb.toString();
    }
    
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

    /**
     * 用户退出通用方法
     * @param response
     */
    public static void logout(ServletResponse response){
        try {
            if(response instanceof HttpServletResponse){
                WebUtils.removeOfweekCookie((HttpServletResponse)response);
            }
            UserUtils.getSubject().logout();
        } catch (SessionException ise) {
        }
    }

    private static void killCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setDomain(".ofweek.com");
        response.addCookie(cookie);
    }

    /**
     * <从request对象中获取用户IP地址>
     *
     * @param request
     * @return IP地址
     */
    public static String getIp(HttpServletRequest request) {
        String forwards = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(forwards) || "unknown".equalsIgnoreCase(forwards)) {
            forwards = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(forwards) || "unknown".equalsIgnoreCase(forwards)) {
            forwards = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(forwards) || "unknown".equalsIgnoreCase(forwards)) {
            forwards = request.getRemoteAddr();
        }
        if (StringUtils.isBlank(forwards) || "unknown".equalsIgnoreCase(forwards)) {
            forwards = request.getHeader("X-Real-IP");
        }
        if (forwards != null && forwards.trim().length() > 0) {
            int index = forwards.indexOf(',');
            return (index != -1) ? forwards.substring(0, index) : forwards;
        }
        return forwards;
    }

    public static String getCallback(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        if (StringUtils.isBlank(value)) {
            value = (String) request.getAttribute(name);
        }
        return value;
    }
}
