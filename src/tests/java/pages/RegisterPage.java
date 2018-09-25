package pages;

import helpers.CustomLoadableComponent;
import helpers.PageLoad;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class RegisterPage extends CustomLoadableComponent<RegisterPage> {
    private WebDriver driver;

    private static final String registerUrl = "https://trello.com/signup?returnUrl=%2F";
    private static final String nameFieldLocator = "user";
    private static final String loginEmailFieldLocator = "email";
    private static final String passwordFieldLocator = "password";
    private static final String logInButtonLocator = "input#signup";

    @FindBy(id = nameFieldLocator)
    private WebElement nameField;
    @FindBy(id = loginEmailFieldLocator)
    private WebElement loginEmailField;
    @FindBy (id = passwordFieldLocator)
    private WebElement passwordField;
    @FindBy (css = logInButtonLocator)
    private WebElement logInButton;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void load() {
        driver.get(registerUrl);
    }

    @Override
    protected void isLoaded() throws Error {
        PageLoad.validPageTitle(driver, "Stwórz konto Trello", "");

        List<WebElement> elementList = new ArrayList<>();
        elementList.add(loginEmailField);
        elementList.add(passwordField);
        elementList.add(logInButton);
}

        public RegisterPage addNewAccount(List<String> parametersList) {
        System.out.println("INFO: Proba wypelnienia formularza tworzenia noweg konta");
        int paramNeedNumber = 3;
        if(parametersList.size() != paramNeedNumber) { //wyświetlił się błąd który nie był spodziewany
            throw new Error("ERROR: addNewAccount() potrzebuje "+paramNeedNumber+" parametrow wyslano "+parametersList.size());
        }

        if(!parametersList.get(0).equals("")) {
            setName(parametersList.get(0));
        }
        if(!parametersList.get(1).equals("")) {
            setLogin(parametersList.get(1));
        }
        if(!parametersList.get(2).equals("")) {
            setPassword(parametersList.get(2));
        }

        return this;
    }
    private void setName (String name) {
        System.out.println("INFO: Ustawienie pola 'Nazwa ' na "+name);
        PageLoad.setInput(nameField,name);
    }

    private void setLogin(String userEmail) {
        System.out.println("INFO: Ustawienie pola 'Email' na "+userEmail);
        PageLoad.setInput(loginEmailField,userEmail);
    }

    private void setPassword(String userPassword) {
        System.out.println("INFO: Ustawienie pola 'Hasło' na "+userPassword);
        PageLoad.setInput(passwordField,userPassword);
    }

    public NewTablePage submitForm(){
        System.out.println("INFO: Proba zapisania formularza");
        logInButton.click();
        PageLoad.checkActionSuccess(driver);

        return new NewTablePage();
    }

}
