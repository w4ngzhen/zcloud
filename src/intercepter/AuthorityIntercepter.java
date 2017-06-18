package intercepter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * Created by mee on 2017/6/12.
 * 所有action执行之前的用户登录状态拦截验证
 */
public class AuthorityIntercepter implements Interceptor{
    @Override
    public void destroy() {
        //System.out.println("拦截器被销毁");
    }

    @Override
    public void init() {
        //System.out.println("拦截器初始化");
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        String result;
        ActionContext ac = ActionContext.getContext();
        //System.out.println("action执行之前的 session 的大小：" + ac.getSession().size());
        //session的大小小于等于0，说明用户登录已经失效直接返回input视图
        if (ac.getSession().size() <= 0)
            return "input";
        result = actionInvocation.invoke();
        //System.out.println("action执行之后的 session 的大小:" + ac.getSession().size());
        return result;
    }
}
