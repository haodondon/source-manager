package cn.app.source.job;

import cn.app.source.model.AccountDo;
import cn.app.source.service.AccountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *  @uthor: hdd
 *  @Date: 2020/11/20
 *  @Description: 各大素材网站QQ自动登录切换token
 */
@Component
@Slf4j
public class AutoQQLogin {

    /**
     * 谷歌驱动地址
     */
    @Value("${config.chromeDriverUrl}")
    private String chromeDriverUrl;

    @Autowired
    private AccountService accountService;

    /**
     * 千图网QQ自动登录切换token
     */
    @Scheduled(cron = "0 */100 * * * ?")
    public void qTuWang(){



    }

}
