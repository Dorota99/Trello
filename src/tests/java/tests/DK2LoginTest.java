package tests;

import org.junit.Before;
import org.testng.annotations.Test;
import pages.FirstVisitPage;
import pages.LoginPage;

import java.util.Arrays;
import java.util.List;

public class DK2LoginTest extends BaseTest {
    private List<String> params;

    //ja bym zrobiła tak:
    @Before
    public void before() {
        new FirstVisitPage();
        new LoginPage(driver);

        //Dane wejsciowe
        String userEmail = "dorotatesterka@gmail.com";
        String userPassword = "Trello123";
        params = Arrays.asList(userEmail, userPassword);
    }

    @Test
    public void loginToAccount() {
        (((firstVisitPage.clickFirstLogin()).fillOutLoginData(params)).submitForm()).get(driver);
    }
}
