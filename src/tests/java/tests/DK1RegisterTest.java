package tests;

import org.junit.Before;
import org.testng.annotations.Test;
import pages.FirstVisitPage;
import pages.RegisterPage;

import java.util.Arrays;
import java.util.List;

public class DK1RegisterTest extends BaseTest {
    private List<String> params;

    @Before
    public void before() {
        new RegisterPage(driver);
        new FirstVisitPage();

        //Dane wejsciowe
        String userName = "Testowe";
        String userEmail = "dorotatesterka@gmail.com";
        String userPassword = "Trello123";
        params = Arrays.asList(userName, userEmail, userPassword);
    }

     @Test
        public void addNewAccount() {
         firstVisitPage.clickRegister()
                 .addNewAccount(params)
                 .submitForm()
                 .get(driver);
        }
    }
