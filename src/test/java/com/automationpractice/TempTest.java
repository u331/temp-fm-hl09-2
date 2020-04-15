package com.automationpractice;

//Lesson 9
//Home work 2
//1. Открыть главную страницу automationpractice.com
//2. В поле поиска ввести “Blouse” и выполнить поиск
//3. Переключится на режим просмотра ‘List”
//4. Добавить товар в корзину
//5. В секции Summary увеличить количество товаров на 1
//6. Проверить что значения Total для товара , Total products, Total
//      shipping , Total всех товаров , Tax и TOTAL общий отображается корректно
//7. Удалить товар из корзины
//8. Проверить что корзина пустая

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TempTest {
//    private final String MAIN_PAGE_URL ="http://automationpractice.com";
    private final String MAIN_PAGE_URL ="https://www.crazydomains.com.au/";

    @Test
    public void myTestChrome(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--headless");
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, co);
        WebDriver chromeDriver = new ChromeDriver(co);
        chromeDriver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        chromeDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        final Wait<WebDriver> wait = new WebDriverWait(chromeDriver, 5, 1000);

        chromeDriver.navigate().to(MAIN_PAGE_URL);
        chromeDriver.findElement(By.xpath("//div [contains(@class,'dropdownBox')]")).click();
//        String rrr = chromeDriver.findElement(By.xpath("//div[contains(@class,'menuItem')]/div[contains(@class,'menuItemContent')]")).getText();
        String rrr = chromeDriver.findElements(By.xpath("//div[contains(@class,'menuItem')]/div[contains(@class,'menuItemContent')]"))
                .get(2).getText();

        System.out.println(rrr);



        //try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#center_column p.alert-warning")));
        Assert.assertTrue(true);
    }
}
