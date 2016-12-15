/**
 * @Date 2015年11月18日 下午5:06:31
 * @author tangq
 * @version V1.0
 */
package com.ofweek.live.core.modules.rpc;

import com.ofweek.live.core.common.config.LiveEnv;
import com.ofweek.live.core.common.utils.FastJsonUtils;
import com.ofweek.live.core.common.utils.NewHttpClient;
import com.ofweek.live.core.common.utils.NewHttpClient.FullResponse;
import com.ofweek.live.core.modules.audience.entity.Audience;
import com.ofweek.live.core.modules.rpc.dto.OfweekResponseDto;
import com.ofweek.live.core.modules.rpc.dto.UserDto;
import com.ofweek.live.core.modules.sys.enums.SexEnum;
import com.ofweek.live.core.modules.sys.utils.AccessKeyUtils;
import org.apache.http.cookie.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调用ofweek相关接口
 */
public class InvokeOfweek {

    private static final Logger logger = LoggerFactory.getLogger(InvokeOfweek.class);

    private static final String token = "ofweek";

    /**
     * ofweek更新会员信息接口
     */
    public static final String URL_OFWEEK_UPDATEUSER = "url.ofweek.updateUser";

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
            if (result != null && !result.equals("{}\n"))
                userDto = FastJsonUtils.parseObject(result, UserDto.class);
        } catch (Exception e) {
            logger.error("查询资讯网观众数据失败,response={}", result);
            logger.error("失败原因：", e);
        }
        System.out.println(userDto);
        return userDto;
    }

    /**
     * 向ofweek更新会员信息
     *
     * @param audience
     * @return
     */
    public static OfweekResponseDto updateUser(Audience audience) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userid", audience.getAccount());
        params.put("companyName", audience.getCompany());// 公司
        params.put("job", audience.getJob());// 职位
        params.put("department", audience.getDepartment());// 部门
        params.put("name", audience.getName());
        params.put("telePhone", audience.getMobilePhone());
        params.put("tel", audience.getTelephone());

        params.put("sex", SexEnum.toOfweek(audience.getSex()));
        if ("zh_CN".equals(audience.getCountry())) {
            params.put("areatype", 0);
        } else {
            params.put("areatype", 1);
        }
        params.put("province", audience.getProvince());
        params.put("address", audience.getAddress());

        String ret = NewHttpClient.post(LiveEnv.getConfig(URL_OFWEEK_UPDATEUSER), params, true);

        OfweekResponseDto dto = null;
        if (ret == null) {
            logger.error("调用ofweek会员信息更新接口失败！");
        } else {
            try {
                dto = FastJsonUtils.parseObject(ret.trim(), OfweekResponseDto.class);
            } catch (Exception e) {
                logger.error("json解析ofweek会员信息更新接口返回结果失败, responseText={}", ret);
            }
        }
        return dto;
    }

    public static void main(String[] args) {
        System.setProperty("config.path", "D:/workspace/product-live-parent/product-live-webapp/src/main/config/");
        getUser("tangqian9140");

        //login("tangqian9140", "123456", null);
    }
}
