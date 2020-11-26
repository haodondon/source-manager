package cn.app.source.async;

import cn.app.source.model.AccountDo;
import cn.app.source.service.AccountService;
import cn.app.source.util.AppException;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 千图网异步任务
 */
@Component
@Slf4j
public class Pic58Task {

    /**
     * 谷歌驱动地址
     */
    @Value("${config.chromeDriverUrl}")
    private String chromeDriverUrl;

    @Autowired
    private AccountService accountService;

    @Async
    public void qqLogin(AccountDo account) throws Exception {//Future用来判断代码是否完成


        this.qqAutoLogin(account);


    }

    public String qqAutoLogin(AccountDo account) throws Exception {

        log.info("-----------------------------------------------------------------");
        log.info("千图网qq异步登录开始执行,当前账号:{}",account.getAccountName());
        log.info("-----------------------------------------------------------------");

        System.setProperty("webdriver.chrome.driver", chromeDriverUrl);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        WebDriver driver = new ChromeDriver(options);

        try {

            driver.manage().window().maximize();
            driver.manage().window().setPosition(new Point(100, 50));
            driver.manage().deleteAllCookies();
            // 与浏览器同步非常重要，必须等待浏览器加载完毕
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            driver.get("https://www.58pic.com/login");

            Thread.sleep(1000);

            WebElement qqLoginLink = driver
                    .findElement(By.xpath("//*[@id='statis-qq']"));
            qqLoginLink.click();
            Thread.sleep(1000);
            // 获取当前页面句柄
            String handle = driver.getWindowHandle();
            // 获取所有页面的句柄，并循环判断不是当前的句柄 然后切换到子窗体
            for (String handles : driver.getWindowHandles()) {
                if (handles.equals(handle))
                    continue;
                driver.switchTo().window(handles);
            }

            // 由于登录输入框在frame中，还需要先切换进入frame，否则，也找不到输入框的
            driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='ptlogin_iframe']")));

            driver.findElement(By.xpath("//*[@id='switcher_plogin']")).click();
            driver.findElement(By.xpath("//*[@id='u']")).sendKeys(account.getAccountName());
            driver.findElement(By.xpath("//*[@id='p']")).sendKeys(account.getAccountPassWord());
            driver.findElement(By.xpath("//*[@id='login_button']")).click();
            try {
                driver.findElement(By.xpath("//*[@id='login_button']")).click();
            }catch (Exception e){}

            //由于我的账号没绑定手机，点登录后会有个提示，如果直接关闭，可能被判断为还没完成登录，没有会话，所以稍等片刻
            Thread.sleep(2000);

            //判断登录qq是否需要滑动
            try {

                //指向滑块窗口
                driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='tcaptcha_iframe']")));

                //鼠标事件
                Actions action = new Actions(driver);

                //滑动滑块到指定位置 -> tcaptcha_drag_thumb
                int i=170;
                try {
                    while (i < 230){

                        action.dragAndDropBy(driver.findElement(By.xpath("//*[@id='tcaptcha_drag_button']")), i, 0).perform();
                        Thread.sleep(100);
                        i++;

                    }
                }catch (Exception e){
                    log.info("滑块验证已完成");
                }

            }catch (Exception e){
                log.info("登录QQ无需滑动滑块");
            }

            Thread.sleep(2000);

            if (!driver.getTitle().contains("千图网")) {

                //停用该账号
                account.setAccountUse(BigDecimal.ROUND_CEILING);
                account.setAccountErrorInfo("该账号自动登录失败,请手动登录查看原因");
                this.accountService.updateById(account);
                log.info("-------------------------------------------------------------------------------------------");
                log.info("千图网qq自动登录失败,账号已被停用,账号:{}",account.getAccountName());
                log.info("-------------------------------------------------------------------------------------------");
                throw new AppException("账号登录失败");
            }

            Set<Cookie> cookies = driver.manage().getCookies();

            StringBuffer qtCookies = new StringBuffer();
            cookies.forEach(cookie -> {
                qtCookies.append(cookie.toString().substring(0,cookie.toString().indexOf(";") + BigDecimal.ROUND_CEILING));
            });

            account.setAccountLastLoginTime(LocalDateTime.now());
            account.setAccountToken(qtCookies.toString());

            this.accountService.updateById(account);

            return qtCookies.toString();

        }finally {

            //关闭浏览器窗口
            driver.close();

        }

    }

}
