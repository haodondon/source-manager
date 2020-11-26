package cn.app.source.remote;

import cn.app.source.async.Pic58Task;
import cn.app.source.model.AccountDo;
import cn.app.source.util.AppException;
import cn.app.source.util.BaseUtil;
import com.alipay.api.domain.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 *  @author: hdd
 *  @Date: 2020/11/22
 *  @Description: 千图网下载
 */
@Slf4j
@Component
public class Pic58Download {

    @Autowired
    private Pic58Task pic58Task;

    /**
     * 校验账号是否可用  J_NvcCaptchaWrap
     */
    public String checkAccountValid(List<AccountDo> accounts,String downloadUrl) throws Exception {

        downloadUrl = "https://dl.58pic.com/" + BaseUtil.getRegExNum(downloadUrl.substring(downloadUrl.lastIndexOf("/"), downloadUrl.lastIndexOf("."))) + ".html";

        int count = 1;

        for (AccountDo account : accounts) {

            Document sourceHtml = this.download(account.getAccountToken(), downloadUrl);

            //校验该账号是否需要登录验证
            if (sourceHtml.select(".login-model").size() > 0) {

                //判断是否需要滑动验证
                if(sourceHtml.select("#J_NvcCaptchaWrap") .size() > 0){

                    //如果当前是最后一个账号，则开始滑块验证
                    if(count == accounts.size()){
                        //如果需要滑动验证,唤醒登录程序，滑块校验

                    }else{

                    }

                }else{

                    //如果不需要滑动验证,直接获取下载地址返回
                    List<Element> downBtn = sourceHtml.select(".text-orange-b");
                    downloadUrl = downBtn.get(0).attr("href");
                    if(downloadUrl.startsWith("https")){
                        return downloadUrl;
                    }

                }

            }else{

                log.info("-------------------------------------------------------------------------------------------");
                log.info("账号:{}，千图网登录失效",account.getAccountName());
                log.info("-------------------------------------------------------------------------------------------");

                //如果当前是最后一个账号，则开始登录操作
                if(count == accounts.size()){


                    //唤醒登录程序
                    String token = this.pic58Task.qqAutoLogin(account);

                    //重新拉取下载
                    sourceHtml = this.download(token, downloadUrl);
                    List<Element> downBtn = sourceHtml.select(".text-orange-b");
                    downloadUrl = downBtn.get(0).attr("href");
                    if(downloadUrl.startsWith("https")){
                        return downloadUrl;
                    }else{

                        log.info("------------------------------------------------------------------------------");
                        log.info("千图网登录二次下载失败,当前账号:{}",account.getAccountName());
                        log.info("------------------------------------------------------------------------------");
                        throw new AppException("当前通道正在维护");

                    }


                }else{

                    //如果不是最后一个账号,异步通知开始登录操作
                    this.pic58Task.qqLogin(account);

                }

            }

            count ++;

        }

        log.error("------------------------------------------------------------------------------------");
        log.error("千图网所有账号异常,请尽快查看");
        log.error("------------------------------------------------------------------------------------");
        throw new AppException("当前通道正在维护");

    }

    public static List<Header> getHeader(String cookie) {
        List<Header> headerList = new ArrayList<>();
        if (cookie != null) {
            headerList.add(new BasicHeader("Cookie", cookie));
        }
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9," +
                "image/webp,image/apng,*/*;q=0.8"));
        headerList.add(new BasicHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36"));
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate"));
        headerList.add(new BasicHeader(HttpHeaders.CACHE_CONTROL, "max-age=0"));
        headerList.add(new BasicHeader(HttpHeaders.CONNECTION, "keep-alive"));
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4,ja;q=0.2," +
                "de;q=0.2"));
        return headerList;
    }

    /**
     * 下载
     * @param token
     * @param downloadUrl
     * @return
     * @throws Exception
     */
    private Document download(String token,String downloadUrl) throws Exception{

        //构造自定义Header信息
        List<Header> headerList = getHeader(token);
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultHeaders(headerList).build();
        HttpGet httpGet = new HttpGet(downloadUrl);
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        return Jsoup.parse(EntityUtils.toString(httpResponse.getEntity(), "UTF-8"));

    }

}
