package helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PageLoad {
    public static void setInput(WebElement element, String text){

        element.clear();
        element.sendKeys(text);
    }

    public static void validPageTitle(WebDriver driver, String expectedTitle, String actualTitle) {
        actualTitle = driver.getTitle();
        if(!actualTitle.contains(expectedTitle)){
            throw new Error("ERROR: Niepoprawy atrybut HTML Title, spodziewany: '"+expectedTitle+"' otrzymany: '"+actualTitle+"'");
        }
    }


    public static void checkActionSuccess(WebDriver driver) {

        if(PageLoad.gritterIsVisible(driver, "gritter-error")) { //wyświetlił się błąd który nie był spodziewany
            throw new Error("ERROR: Wystapił niespodziewany błąd (gritter-error)");
        }

        if(PageLoad.gritterIsVisible(driver, "gritter-success")) { //komunikat o sukcesie
            System.out.println("INFO: Wyświetlił się komunikat o sukcesie akcji (gritter-success)");
            driver.findElement(By.className("gritter-close")).click();
        }
        else {
            if(errorContainerIsVisible(driver)){
                throw new Error("ERROR: Wystapily bledy przy walidacji formularza");
            }else {
                throw new Error("ERROR: Nie wyświetlil się spodziewany komunikat o sukcesie akcji (gritter-success)");
            }
        }

        System.out.println("INFO: Po wykonaniu akcji nie stwierdzono bledu");
    }
//misc raczej do wywalenia:

    public static boolean elementIsClickable(WebDriver driver, WebElement element, Integer timeoutInSeconds) {
        try {
            new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(element));
        } catch (WebDriverException ex) {
            System.out.println("WARNING: Wystapil blad w elemencie: '" + element);
            return false;
        }

        return true;
    }

    public static Object elementAreClickable(WebDriver driver, List<WebElement> elementsList, Integer timeoutInSeconds, String calledForm) {
        for (WebElement element : elementsList) {
            try {
                new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(element));
            } catch (WebDriverException ex) {
                System.out.println("WARNING: Wystapil blad w elemencie: '" + element + " na formularzu: " + calledForm);
                return false;
            }
        }
        return true;
    }

    public static boolean errorContainerIsVisible(WebDriver driver) {
        try {
            WebElement alertContainer = driver.findElement(By.cssSelector("div[class*='alert-danger']"));
            if (alertContainer.isDisplayed()) {
                System.out.println("INFO: Wyswietlil sie komunikat bledu 'alert alert-danger':\n" + alertContainer.getText());
                return true;
            } else {
                return false;
            }
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public static boolean gritterIsVisible(WebDriver driver, String gritterType) {
        try {
            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(("div[class$='" + gritterType + "']"))));
            WebElement alertContainer = driver.findElement(By.cssSelector(("div[class$='" + gritterType + "']")));
            if (alertContainer.isDisplayed()) {
                System.out.println("INFO:'" + gritterType + "'\n----------------\n" + alertContainer.getText() + "\n----------------");
                Actions build = new Actions(driver);
                build.moveToElement(alertContainer).build().perform();
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
        return false;
    }

    }











