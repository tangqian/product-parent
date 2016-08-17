/**
 * @Date 2015年11月18日 下午5:06:31
 * @author tangq
 * @version V1.0
 * 
 */
package com.ofweek.live.core.modules.rpc;

import java.util.List;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.cookie.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ofweek.live.core.common.config.LiveEnv;
import com.ofweek.live.core.common.utils.FastJsonUtils;
import com.ofweek.live.core.common.utils.NewHttpClient;
import com.ofweek.live.core.common.utils.NewHttpClient.FullResponse;
import com.ofweek.live.core.modules.rpc.dto.UserDto;
import com.ofweek.live.core.modules.sys.utils.AccessKeyUtils;

/**
 * 调用ofweek相关接口
 */
public class InvokeOfweek {

	private static final Logger logger = LoggerFactory.getLogger(InvokeOfweek.class);
	
	private static final String token = "ofweek";
	
	public static UserDto login(String account, String password, ServletResponse httpResponse) {
		String ak = AccessKeyUtils.encode(account, account, token);
		String url = String.format("%s?ak=%s&nonce=%s&account=%s&password=%s", LiveEnv.Ofweek.getLoginUrl(), ak, account, account,
				password);
		FullResponse response = NewHttpClient.getAll(url, true);

		UserDto userDto = null;
		try {
			userDto = FastJsonUtils.parseObject(response.getBody(), UserDto.class);
			
			List<Cookie> cookies = response.getCookies();
            if (cookies != null && httpResponse != null && httpResponse instanceof HttpServletResponse) {
				HttpServletResponse httpServletResponse = (HttpServletResponse) httpResponse;
                for (Cookie cookie : cookies) {
                    logger.warn("接收cookie: " + cookie);
                    javax.servlet.http.Cookie reponseCookie = new javax.servlet.http.Cookie(cookie.getName(), cookie.getValue());
                    reponseCookie.setDomain(cookie.getDomain());
                    if (cookie.getExpiryDate() == null) {
                        reponseCookie.setMaxAge(-1);
                    } else {
                        reponseCookie.setMaxAge((int) (cookie.getExpiryDate().getTime() - System.currentTimeMillis()));
                    }
                    reponseCookie.setPath(cookie.getPath());
                    httpServletResponse.addCookie(reponseCookie);
                }
            }

		} catch (Exception e) {
			logger.error("登录资讯网失败,response={}", response.getBody());
			//logger.error("失败原因：", e);
		}
		return userDto;
	}

	public static UserDto getUser(String account) {
		String ak = AccessKeyUtils.encode(account, account, token);
		String url = String.format("%s?ak=%s&nonce=%s&account=%s", LiveEnv.Ofweek.getQueryUrl(), ak, account, account);
		String result = NewHttpClient.get(url, true);
		
		UserDto userDto = null;
		try {
			if(result != null && !result.equals("{}\n"))
				userDto = FastJsonUtils.parseObject(result, UserDto.class);
		} catch (Exception e) {
			logger.error("查询资讯网观众数据失败,response={}", result);
			logger.error("失败原因：", e);
		}
		System.out.println(userDto);
		return userDto;
	}
	
	public static void main(String[] args) {
		System.setProperty("config.path", "D:/workspace/product-live-parent/product-live-webapp/src/main/config/");
		//getUser("tangqian9140");
		
		login("tangqian9140", "123456", null);
	}
}
