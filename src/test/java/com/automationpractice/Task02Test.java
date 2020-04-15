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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class Task02Test {
    private final String MAIN_PAGE_URL ="http://automationpractice.com";

    @Test
    public void myTestChrome(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        chromeDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        final Wait<WebDriver> wait = new WebDriverWait(chromeDriver, 5, 1000);

        chromeDriver.navigate().to(MAIN_PAGE_URL);
        chromeDriver.findElement(By.id("search_query_top")).sendKeys("Blouse");
        chromeDriver.findElement(By.cssSelector("#searchbox button")).click();
        chromeDriver.findElement(By.cssSelector("#list a")).click();
        chromeDriver.findElement(By.cssSelector("#center_column a.ajax_add_to_cart_button")).click();
        chromeDriver.findElement(By.cssSelector("#layer_cart a")).click();
        chromeDriver.findElement(By.cssSelector("#cart_summary tr.first_item a.cart_quantity_up")).click();

        //try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#cart_summary tr.first_item td.cart_total span"),"54"));
        Assert.assertEquals(54D, Double.parseDouble(chromeDriver.findElement(By.cssSelector("#cart_summary tr.first_item td.cart_total span")).getText().substring(1)), 0.0001);

        Assert.assertEquals(54D, Double.parseDouble(chromeDriver.findElement(By.id("total_product")).getText().substring(1)), 0.0001);
        Assert.assertEquals(2D, Double.parseDouble(chromeDriver.findElement(By.id("total_shipping")).getText().substring(1)), 0.0001);
        Assert.assertEquals(56D, Double.parseDouble(chromeDriver.findElement(By.id("total_price_without_tax")).getText().substring(1)), 0.0001);
        Assert.assertEquals(56D, Double.parseDouble(chromeDriver.findElement(By.id("total_price")).getText().substring(1)), 0.0001);

        chromeDriver.findElement(By.cssSelector("#cart_summary tr.first_item td.cart_delete a")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#center_column p.alert-warning")));
        Assert.assertTrue(chromeDriver.findElement(By.cssSelector("#center_column p.alert-warning")).getText().equalsIgnoreCase("Your shopping cart is empty."));
    }
}
