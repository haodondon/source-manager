import cn.app.source.util.AppException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LoginAuto {

    public static void main(String[] args) throws Exception {

        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
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
            driver.findElement(By.xpath("//*[@id='u']")).sendKeys("***********");
            driver.findElement(By.xpath("//*[@id='p']")).sendKeys("**********");
            driver.findElement(By.xpath("//*[@id='login_button']")).click();
            try {
                driver.findElement(By.xpath("//*[@id='login_button']")).click();
            } catch (Exception e) {
            }

            //由于我的账号没绑定手机，点登录后会有个提示，如果直接关闭，可能被判断为还没完成登录，没有会话，所以稍等片刻
            Thread.sleep(2000);

            //判断登录qq是否需要滑动
            try {

                //指向滑块窗口
                driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='tcaptcha_iframe']")));

                //鼠标事件
                Actions action = new Actions(driver);

                //滑动滑块到指定位置 -> tcaptcha_drag_thumb
                int i = 170;
                try {
                    while (i < 230) {

                        action.dragAndDropBy(driver.findElement(By.xpath("//*[@id='tcaptcha_drag_button']")), i, 0).perform();
                        Thread.sleep(100);
                        i++;

                    }
                } catch (Exception e) {
                }

            } catch (Exception e) {
            }

            Thread.sleep(2000);
            driver.get("https://dl.58pic.com/37147089.html");
            System.out.println();
            DFS(0, 0,driver);

        } finally {}
    }

    private static int MAX_INDEX = 4;// or 5;

    private static int TOP = 26;

    private static int count = 0;

    private static char[] s = new char[MAX_INDEX];

    public static void DFS(int now, int index,WebDriver driver) {

        for (int i = now; i < TOP; i++) {
            s[index] = (char) ('a' + i);
            if (index == MAX_INDEX - 1) {
                show(s,driver);
                count++;
            } else {
                DFS(i + 1, index + 1,driver);
            }
        }

    }

    private static void show(char[] s,WebDriver driver) {
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i]);
        }
        driver.findElement(By.cssSelector(".risk-prompt2-code2")).sendKeys(new String(s));
        driver.findElement(By.cssSelector(".risk-prompt-btn")).click();

    }

}