package com.river.ms.ribbon;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LDAPAuth {
    private static final String LDAP_BASE_DN = "dc=enn,dc=com";
    private static final String LDAP_USER_BASE_DN = "cn=users," + LDAP_BASE_DN;
    // TDS测试环境的ip为 10.37.144.166
    // private static final String LDAP_URL = "ldap://10.37.144.166:389";
    // TDS生产环境的域名为 tds01.xinaogroup.com, tds02.xinaogroup.com
	// 2015-12-01根据纪红建议，更改为tds02.xinaogroup.com
    private static final String LDAP_URL = "ldap://tds02.xinaogroup.com:389";
    private static final String LDAP_BIND_USER = "uid=wpsbind,"
        + LDAP_USER_BASE_DN;
    private static final String LDAP_USER_PASSWORD = "Passw0rd";
    private static final Logger LOG = LoggerFactory.getLogger(LDAPAuth.class);

    /**
     * 
     * 单点认证，非sso： 根据登录帐号或员工号和密码到ldap上做验证
     * 
     * @param userInfo
     * @param password
     * @return
     */
    public static AuthResult authenticate(String userInfo, String password) {
        AuthResult result = AuthResult.E_UNKOWN;
        LdapContext ctx = null;
        String userDN = null;
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, LDAP_URL);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, LDAP_BIND_USER);
        env.put(Context.SECURITY_CREDENTIALS, LDAP_USER_PASSWORD);
        String[] returnAttr = { "uid", "employeenumber" };
        SearchControls constraints = new SearchControls();
        constraints.setSearchScope(SearchControls.ONELEVEL_SCOPE);
        constraints.setReturningAttributes(returnAttr);
        try {
            ctx = new InitialLdapContext(env, null);
            NamingEnumeration<SearchResult> answer = ctx.search(
                    LDAP_USER_BASE_DN, "(&(objectclass=inetOrgPerson)(|(uid="
                        + userInfo + ")(employeenumber=" + userInfo + ")))",
                    constraints);// 根据登录帐号或员工号查询
            if (answer.hasMoreElements()) {
                SearchResult sr = (SearchResult) answer.next();
                Attributes attrs = sr.getAttributes();
                if (attrs != null) {
                    Attribute attr = attrs.get("uid");
                    if (attr != null) {
                        userDN = (String) attr.get();
                    }
                    if (userDN != null && userDN.length() > 0) { // 验证用户密码
                        userDN = "uid=" + (String) attrs.get("uid").get() + ","
                            + LDAP_USER_BASE_DN;
                        ctx.removeFromEnvironment(Context.SECURITY_PRINCIPAL);
                        ctx.removeFromEnvironment(Context.SECURITY_CREDENTIALS);
                        ctx.addToEnvironment(Context.SECURITY_PRINCIPAL, userDN);
                        ctx.addToEnvironment(Context.SECURITY_CREDENTIALS,
                                password);
                        ctx.reconnect(null);
                        result = AuthResult.SUCCESS; // 成功
                    } else {
                        result = AuthResult.E_USERNAME; // 账号为空，返回用户不存在
                    }
                }
            } else {
                result = AuthResult.E_USERNAME;
                LOG.warn(userInfo + " does not exist in TDS.");
            }
        } catch (NamingException e) {
            result = getErrorType(e);
        } finally {
            try {
                if (ctx != null) {
                    ctx.close();
                }
            } catch (NamingException e) {
                LOG.warn("Ldap fail to close!", e);
            }
        }
        return result;
    }

    /**
     * sso认证： 根据登录帐号或员工号到ldap上做验证
     * 
     * @param userInfo
     * @return
     */
    public static AuthResult authenticate(String userInfo) {
        AuthResult result = AuthResult.E_UNKOWN;
        LdapContext ctx = null;
        String userDN = null;
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, LDAP_URL);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, LDAP_BIND_USER);
        env.put(Context.SECURITY_CREDENTIALS, LDAP_USER_PASSWORD);
        String[] returnAttr = { "uid", "employeenumber" };
        SearchControls constraints = new SearchControls();
        constraints.setSearchScope(SearchControls.ONELEVEL_SCOPE);
        constraints.setReturningAttributes(returnAttr);
        try {
            ctx = new InitialLdapContext(env, null);
            NamingEnumeration<SearchResult> answer = ctx.search(
                    LDAP_USER_BASE_DN, "(&(objectclass=inetOrgPerson)(|(uid="
                        + userInfo + ")(employeenumber=" + userInfo + ")))",
                    constraints);// 根据登录帐号或员工号查询
            if (answer.hasMoreElements()) {
                SearchResult sr = (SearchResult) answer.next();
                Attributes attrs = sr.getAttributes();
                if (attrs != null) {
                    Attribute attr = attrs.get("uid");
                    if (attr != null) {
                        userDN = (String) attr.get();
                    }
                    if (userDN != null && userDN.length() > 0) { // 验证用户密码
                        userDN = "uid=" + (String) attrs.get("uid").get() + ","
                            + LDAP_USER_BASE_DN;
                        ctx.reconnect(null);
                        result = AuthResult.SUCCESS; // 成功
                    } else {
                        result = AuthResult.E_USERNAME; // 账号为空，返回用户不存在
                    }
                }
            } else {
                result = AuthResult.E_USERNAME;
                LOG.warn(userInfo + " does not exist in TDS.");
            }
        } catch (NamingException e) {
            result = getErrorType(e);
        } finally {
            if (ctx != null) {
                try {
                    ctx.close();
                } catch (NamingException e) {
                    LOG.warn("Ldap close error!", e);
                }
            }
        }
        return result;
    }

    private static AuthResult getErrorType(NamingException e) {
        AuthResult result = AuthResult.E_UNKOWN;
        String msg = e.getMessage();
        if (msg.indexOf("error code 49") > -1
            || msg.indexOf("error code 48") > -1) {
            result = AuthResult.E_PASSWORD; // 用户名密码不正确
        }
        // else if (msg.indexOf("error code 53") > -1
        // || msg.indexOf("error code 50") > -1
        // || msg.indexOf("error code 19") > -1) {
        // result = -3; // 密码不符合复杂度要求
        // }
        return result;
    }

    public enum AuthResult {
        /** 认证成功 */
        SUCCESS(1),
        /** 认证失败, 用户不存在 */
        E_USERNAME(-1),
        /** 认证失败, 密码错误 */
        E_PASSWORD(-2),
        /** 认证失败, 未知原因 */
        E_UNKOWN(0);
        private final int value;

        AuthResult(int value) {
            this.value = value;
        }

        public static AuthResult valueOf(int value) {
            switch (value) {
                case 1:
                    return SUCCESS;
                default:
                    return null;
            }
        }

        public int value() {
            return this.value;
        }
    }
    // public static void main(String[] args) {
    // System.out.println(LdapAuth.authenticate("e_songhy", "123456"));
    // System.out.println(LdapAuth.authenticate("10034715", "lixiang123"));
    // }
}
/*1.单点登录
开发come登陆认证功能，关键代码如下：
/////////////// 1.获取用户ITCODE ///////////////
String userItcode = request.getHeader("iv-user");
/////////////// 2.用户验证并将用户信息写入session ///////////////
if(userItcode != null){
dc.setAttribute(dc.SESSION, Constant.SESSION_KEY_LOGIN_DATE, loginDateStr);
}
/////////////// 3.重定向到相应页面 ///////////////
response.sendRedirect("/timesheet/pages/mainframe/main-frame.jsp"); */
