package intercepter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * Created by mee on 2017/6/12.
 */
public class AuthorityIntercepter implements Interceptor{
    @Override
    public void destroy() {
        System.out.println("拦截器被销毁");
    }

    @Override
    public void init() {
        System.out.println("拦截器开始工作");
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        String result = "input";
        ActionContext ac = ActionContext.getContext();
        System.out.println("session map的大小：" + ac.getSession().size());
        //result = actionInvocation.invoke();
        System.out.println("action已经执行");
        return result;
    }
}
