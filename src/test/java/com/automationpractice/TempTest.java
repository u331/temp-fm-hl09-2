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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TempTest {
//    private final String MAIN_PAGE_URL ="http://automationpractice.com";
//    private final String MAIN_PAGE_URL ="https://www.crazydomains.com.au/";
    private final String MAIN_PAGE_URL ="https://www.crazydomains.co.nz//";

    private ArrayList<String> getExpectedRegionList() {
        ArrayList<String> expectedRegions = new ArrayList<String>();
        expectedRegions.add("Global");
        expectedRegions.add("Australia");
        expectedRegions.add("Europe");
        expectedRegions.add("Hong Kong");
        expectedRegions.add("India");
        expectedRegions.add("Indonesia");
        expectedRegions.add("Malaysia");
        expectedRegions.add("Philippines");
        expectedRegions.add("Singapore");
        expectedRegions.add("UAE");
        expectedRegions.add("New Zealand");
        return expectedRegions;
    }

    @Test
    public void myTestChrome(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
//        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--headless");
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, co);
        WebDriver chromeDriver = new ChromeDriver(co);
        chromeDriver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        final Wait<WebDriver> wait = new WebDriverWait(chromeDriver, 5, 1000);

        chromeDriver.navigate().to(MAIN_PAGE_URL);
        WebElement button = chromeDriver.findElement(By.xpath("//div[contains(@class,'dropdownBox')]"));
        JavascriptExecutor executor = (JavascriptExecutor)chromeDriver;

        System.out.println( "scriptsInHead: " +  chromeDriver.findElements(By.cssSelector("head > script")).size() );
        System.out.println( "head script[src]: " +  chromeDriver.findElements(By.cssSelector("head script[src]")).size() );
        for(int i = 0; i < chromeDriver.findElements(By.cssSelector("head script[src]")).size(); i++  ){
            System.out.println( i + " : " + chromeDriver.findElements(By.cssSelector("head script[src]")).get(i).getAttribute("src"));
        }
        System.out.println( "<script>(96): " +  chromeDriver.findElement(By.cssSelector("head > script:nth-child(96)")).getAttribute("src") );

        //        try {Thread.sleep(120000);} catch (InterruptedException e) {e.printStackTrace();}

        System.out.println("//div[contains(@class,'dropdownBox')].getText(): " + button.getText());
        System.out.println("//div[contains(@class,'dropdownBox')]_.getAttribute(onclick: " + button.getAttribute("onclick"));
        System.out.println("//div[contains(@class,'dropdownBox')]_.getAttribute(onchange: " + button.getAttribute("onchange"));

//        executor.executeScript("arguments[0].click();", chromeDriver.findElement(By.xpath("//div[contains(@class,'dropdownBox')]")));
        executor.executeScript("show_dropdown_onclick(document.getElementsByClassName('dropdownBox'))");
//        chromeDriver.findElement(By.xpath("//div[contains(@class,'dropdownBox')]")).click();

//        try {Thread.sleep(120000);} catch (InterruptedException e) {e.printStackTrace();}

        System.out.println("//div[contains(@class,'dropdownBox')].getText(): " + button.getText());
        System.out.println("//div[contains(@class,'dropdownBox')]_.getAttribute(onclick: " + button.getAttribute("onclick"));
        System.out.println("//div[contains(@class,'dropdownBox')]_.getAttribute(onchange: " + button.getAttribute("onchange"));

        try{
            new WebDriverWait(chromeDriver, 15).until(ExpectedConditions
                    .visibilityOf( chromeDriver.findElement(By.xpath("//div[contains(@class,'menuItem')]/div[contains(@class,'menuItemContent')]")) ));
        }catch (Exception e){

            System.out.println("//div[contains(@class,'dropdownBox')].getText(): " + button.getText());
            System.out.println("//div[contains(@class,'dropdownBox')]_.getAttribute(onclick: " + button.getAttribute("onclick"));
            System.out.println("//div[contains(@class,'dropdownBox')]_.getAttribute(onchange: " + button.getAttribute("onchange"));

            Assert.fail("Timeoouutt");
        }
        ArrayList<String> regions = new ArrayList<String>();
        for (WebElement aREGIONS_DROP_DOWN_ITEMS_LIST : chromeDriver.findElements(By.xpath("//div[contains(@class,'menuItem')]/div[contains(@class,'menuItemContent')]"))) {
            regions.add(aREGIONS_DROP_DOWN_ITEMS_LIST.getText());
        }
        Assert.assertEquals( regions ,getExpectedRegionList());
    }
}
