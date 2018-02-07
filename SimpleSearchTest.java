package org.fasttrackit.search;

import org.apache.http.impl.client.EntityEnclosingRequestWrapper;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleSearchTest {

    @Test
    public void SimpleSearchWithOneKeyword() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nelu\\Desktop\\drivere\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://fasttrackit.org/selenium-test/");
        //driver.findElement(By.id("search")).sendKeys("vase"+ Keys.ENTER);
        // driver.findElement(By.name("q")).sendKeys("vase"+ Keys.ENTER);
        // driver.findElement(By.className("input-text")).sendKeys("vase");
        String keyword= "vase";
        driver.findElement(By.tagName("input")).sendKeys(keyword+ Keys.ENTER);
        System.out.println("Pressed Enter in search field");
        List<WebElement> productNames = driver.findElements(By.cssSelector(".product-name a"));
        System.out.println("stored"+ productNames.size()+"product names from search results");

        for (WebElement productName : productNames) {
            assertThat("Some of the product names do not contain the searched keyword.", productName.getText(), containsString(keyword.toUpperCase()));
        }
    }

    @Test
    public void SpecialPriceDisplayAfterSimpleSearch() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nelu\\Desktop\\drivere\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://fasttrackit.org/selenium-test/");
        //driver.findElement(By.id("search")).sendKeys("vase"+ Keys.ENTER);
        // driver.findElement(By.name("q")).sendKeys("vase"+ Keys.ENTER);
        // driver.findElement(By.className("input-text")).sendKeys("vase");
        driver.findElement(By.tagName("input")).sendKeys("vase" + Keys.ENTER);
        String oldPrice= driver.findElement(By.xpath("//div[@class='product-info' and ./ descendant::*[@class='product-name' and ./a[text()='Modern Murray Ceramic Vase']]]//p[@class='old-price']//span[@class='price']")).getText();
        String specialPrice= driver.findElement(By.xpath("//p[@class='special-price']/span[@class='price']")).getText();


    }
}
