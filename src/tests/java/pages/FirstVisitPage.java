package pages;

import helpers.CustomLoadableComponent;
import helpers.PageLoad;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class FirstVisitPage extends CustomLoadableComponent<FirstVisitPage> {
    public WebDriver driver;

    private static final String firstVisitUrl = "https://trello.com/";
    private static final String registerButtonLocator = "a[href='/signup?returnUrl=%2F']";
    private static final String firstLoginButtonLocator = "a[href='/login?returnUrl=%2F']";

    @FindBy (css = registerButtonLocator)
    private static WebElement registerButton;
    @FindBy (css = firstLoginButtonLocator)
    private static WebElement firstLoginButton;

    public FirstVisitPage(){
        WebDriver driver = new ChromeDriver();
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void load() {

        driver.get(firstVisitUrl);
    }

    @Override
    protected void isLoaded() throws Error {
        PageLoad.validPageTitle(driver, "Witaj ponownie na Trello!", "");

        List<WebElement> elementList = new ArrayList<>();
        elementList.add(registerButton);
        elementList.add(firstLoginButton);
    }

    public RegisterPage clickRegister(){
        System.out.println("INFO: Sprawdzam czy przycisk 'Zarejestruj się' jest aktywny");
        PageLoad.elementIsClickable(driver, registerButton,10);
        System.out.println("INFO: Proba kliknięcia w przycisk 'Zarejestruj się'");
        registerButton.click();
        PageLoad.checkActionSuccess(driver);

        return new RegisterPage(driver);
    }
    public LoginPage clickFirstLogin(){
        System.out.println("INFO: Proba kliknięcia w przycisk 'Zaloguj się'");
        firstLoginButton.click();
        PageLoad.checkActionSuccess(driver);

        return new LoginPage(driver);
    }
}

