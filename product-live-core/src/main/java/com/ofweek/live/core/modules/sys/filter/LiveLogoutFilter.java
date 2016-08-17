package com.ofweek.live.core.modules.sys.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import com.ofweek.live.core.modules.sys.utils.WebUtils;

/**
 * @author tangqian
 *
 */
public class LiveLogoutFilter extends LogoutFilter {

	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        String redirectUrl = getRedirectUrl(request, response, subject);
        
        try {
            if(response instanceof HttpServletResponse){
            	WebUtils.removeOfweekCookie((HttpServletResponse)response);
            }
            subject.logout();
        } catch (SessionException ise) {
        }
        issueRedirect(request, response, redirectUrl);
        return false;		
	}

	
	
}
