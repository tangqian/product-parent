package com.ofweek.live.core.modules.sys.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ofweek.live.core.common.utils.StringUtils;

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

    private static void killCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setDomain(".ofweek.com");
        response.addCookie(cookie);
    }



}
