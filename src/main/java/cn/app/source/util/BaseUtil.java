package cn.app.source.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  @author: hdd
 *  @Date: 2020/11/22
 *  @Description: 工具类
 */
public final class BaseUtil {

    /**
     * 获取当前登录的用户ID
     * @return
     */
    public static String getCurrentUserId(){
       return (String) ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession().getAttribute("userId");
    }

    /**
     * 正则提取字符串中的数字
     * @author : 郝东东
     * @date   : 2020-02-12 21:50:47
     */
    public static String getRegExNum(String str){
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

}
