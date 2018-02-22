package org.fasttrackit.search;

import org.fasttrackit.AppConfig;
import org.fasttrackit.TestBase;
import org.fasttrackit.webviews.Header;
import org.fasttrackit.webviews.ProductsGrid;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)




public class SimpleSearchTest extends TestBase{

    private String keyword;
    public SimpleSearchTest(String keyword){
        this.keyword= keyword;


    }
    @Parameterized.Parameters
    public static List<String> inputData(){
        return Arrays.asList("vase", "camera");
    }

    @Test
    public void SimpleSearchWithOneKeyword() {

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

        String keyword = "vase";
        Header header = PageFactory.initElements(driver, Header.class);
        header.search(keyword);
        ProductsGrid productsGrid = PageFactory.initElements(driver, ProductsGrid.class);

        System.out.println("stored" + productsGrid.getProductNames().size() + "product names from search results");

        for (WebElement productName : productsGrid.getProductNames()) {
            assertThat("Some of the product names do not contain the searched keyword.", productName.getText(), containsString(keyword.toUpperCase()));
            String oldPrice = driver.findElement(By.xpath("//div[@class='product-info' and ./ descendant::*[@class='product-name' and ./a[text()='Modern Murray Ceramic Vase']]]//p[@class='old-price']//span[@class='price']")).getText();
            String specialPrice = driver.findElement(By.xpath("//p[@class='special-price']/span[@class='price']")).getText();


        }

    }}
