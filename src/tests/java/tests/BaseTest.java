package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import pages.FirstVisitPage;

public class BaseTest {
    WebDriver driver;
    public FirstVisitPage firstVisitPage;

    @BeforeMethod
    public void before (){
        System.setProperty("webdriver.chrome.driver","C:\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        new FirstVisitPage();

    }
    /*@AfterMethod
    public void after(){
        driver.close();
    }*/
}

