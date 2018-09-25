package helpers;
import org.openqa.selenium.WebDriver;

public abstract class CustomLoadableComponent<T extends CustomLoadableComponent<T>>{

public T get(WebDriver driver) {
       try {
    isLoaded();
            System.out.println("INFO: Udało się prawidlowo zaladowac " + getClass().getSimpleName() + "(" + driver.getCurrentUrl() + ")");

            return (T) this;
        } catch (Error e) {
    load();
        }

    isLoaded();
    System.out.println("INFO: Udało się prawidlowo zaladowac " + getClass().getSimpleName() + "(" + driver.getCurrentUrl() + ")");

        return (T) this;
    }

protected abstract void load();
protected abstract void isLoaded ();
}
