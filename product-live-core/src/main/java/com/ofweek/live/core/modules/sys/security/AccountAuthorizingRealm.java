package com.ofweek.live.core.modules.sys.security;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;

import com.ofweek.live.core.modules.audience.entity.Audience;
import com.ofweek.live.core.modules.audience.service.AudienceService;
import com.ofweek.live.core.modules.rpc.InvokeOfweek;
import com.ofweek.live.core.modules.rpc.dto.UserDto;
import com.ofweek.live.core.modules.sys.security.SystemAuthorizingRealm.Principal;

/**
 * @author tangqian
 *
 */
public class AccountAuthorizingRealm extends AuthorizingRealm {
	
	@Resource
	private AudienceService audienceService;
	
	/**
	 * 设定密码校验的Hash算法与迭代次数
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		setCredentialsMatcher(new AccountCredentialsMatcher());
	}
	

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		AccountToken accountToken = (AccountToken) token;
		UserDto userDto = InvokeOfweek.getUser((String)accountToken.getPrincipal());
		if (userDto != null) {
			Audience audience = audienceService.save(userDto);
			return new SimpleAccount(new Principal(audience.getUser(), false), token.getPrincipal(), getName());
			//UserUtils.getSubject().login(new UsernamePasswordToken("test", "123456".toCharArray()));
		}
		return null;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 添加用户权限
		info.addStringPermission("user");
		return info;
	}

	private class AccountCredentialsMatcher implements CredentialsMatcher {

		@Override
		public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
			if( token != null && info != null){
				Principal principal = (Principal) getAvailablePrincipal(info.getPrincipals());
				if(token.getPrincipal().equals(principal.getAccount())){
					return true;
				}
			}
			return false;
		}
		
	    protected Object getAvailablePrincipal(PrincipalCollection principals) {
	        Object primary = null;
	        if (!CollectionUtils.isEmpty(principals)) {
	            @SuppressWarnings("rawtypes")
				Collection thisPrincipals = principals.fromRealm(getName());
	            if (!CollectionUtils.isEmpty(thisPrincipals)) {
	                primary = thisPrincipals.iterator().next();
	            } else {
	                //no principals attributed to this particular realm.  Fall back to the 'master' primary:
	                primary = principals.getPrimaryPrincipal();
	            }
	        }

	        return primary;
	    }
		
	}

}
