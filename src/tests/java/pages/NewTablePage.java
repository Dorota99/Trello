package pages;

import helpers.CustomLoadableComponent;
import helpers.PageLoad;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class NewTablePage extends CustomLoadableComponent<NewTablePage> {
    public WebDriver driver;

    private static final String newTableUrl = "https://trello.com/b/";

    /*@FindBy(css = registerButtonLocator)
    private static WebElement registerButton;
    @FindBy (css = firstLoginButtonLocator)
    private static WebElement firstLoginButton;*/

    public NewTablePage(){
        WebDriver driver = new ChromeDriver();
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void load() {
        driver.get(newTableUrl);
    }

    @Override
    protected void isLoaded() throws Error {
        PageLoad.validPageTitle(driver, "Trello", "");

        /*List<WebElement> elementList = new ArrayList<>();
        elementList.add(registerButton);
        elementList.add(firstLoginButton);*/
    }
}
