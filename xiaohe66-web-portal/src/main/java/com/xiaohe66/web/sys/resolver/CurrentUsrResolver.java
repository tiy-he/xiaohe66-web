package com.xiaohe66.web.sys.resolver;

import com.xiaohe66.web.code.org.helper.UserHelper;
import com.xiaohe66.web.code.org.po.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 *
 * 自动注入参数
 *
 * @author xiaohe
 * @time 17-08-04 004
 * @deprecated 改用静态方法获取用户，该类废弃
 * @see UserHelper
 */
@Deprecated
public class CurrentUsrResolver implements HandlerMethodArgumentResolver{

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //判断参数的类型，返回true表示注入
        parameter.getParameterType().equals(User.class);
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //这个类返回的值即注入的值
        return null;
    }
}
