import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestRunner extends Setup{

    String baseUrl = "https://opensource-demo.orangehrmlive.com/";

    @Test(priority = 1,testName = "TC001", description = "User can login successfully")

    public void doLogin(){
        driver.get(baseUrl);
        LoginPage loginPage= new LoginPage(driver);

        loginPage.doLogin("Admin","admin123");
    }

    @Test(priority = 2,testName = "TC002", description = "User can click on PIM")
    public void PIM() throws InterruptedException {
        // PIM menu click
        List<WebElement> PIMClick= driver.findElements(By.className("oxd-main-menu-item-wrapper"));
        PIMClick.get(1).click();

        Thread.sleep(2000);
        //Add button click
        List<WebElement> AddBtn= driver.findElements(By.className("oxd-button"));
        AddBtn.get(2).click();
    }

    @Test(priority = 3,testName = "TC003",description = "New User will be created")
    public void CreateUser() throws InterruptedException {
        List<WebElement> UserInfo=driver.findElements(By.className("oxd-input"));
        UserInfo.get(1).sendKeys("Rafsana");
        UserInfo.get(2).sendKeys("kabir");
        UserInfo.get(3).sendKeys("Samanta");

        Thread.sleep(2000);
        //toggle active
        List<WebElement> ToggleActive= driver.findElements(By.className("oxd-switch-input"));
        ToggleActive.get(0).click();

        //username password
        Thread.sleep(2000);
        List<WebElement> UserNamePass = driver.findElements(By.className("oxd-input"));
        UserNamePass.get(5).sendKeys("rafsana4");
        UserNamePass.get(6).sendKeys("Ss@12345");
        UserNamePass.get(7).sendKeys("Ss@12345");

        Thread.sleep(2000);
        //save button click
        List<WebElement> SaveBtn = driver.findElements(By.className("oxd-button"));
        SaveBtn.get(1).click();


        Thread.sleep(10000);

        List<WebElement> assertion1 = driver.findElements(By.className("oxd-text"));
        String actualresult1 = assertion1.get(12).getText();
        String expectedresult1 = "Rafsana Samanta";
        String actualresult2 = assertion1.get(13).getText();
        String expectedresult2 = "Personal Details";

        Assert.assertEquals(actualresult1,expectedresult1);
        Assert.assertEquals(actualresult2,expectedresult2);

        List<WebElement> PIMClick2= driver.findElements(By.className("oxd-main-menu-item-wrapper"));
        PIMClick2.get(1).click();



    }



    @Test(priority = 4,testName = "TC004",description = "Search")
    public void SearchUser() throws InterruptedException {
        List<WebElement> userNameSearch=driver.findElements(By.tagName("input"));
        userNameSearch.get(1).sendKeys("Rafsana Kabir Samanta");
        Thread.sleep(2000);
        List<WebElement> SearchBtn = driver.findElements(By.className("oxd-button"));
        SearchBtn.get(1).click();

        Thread.sleep(5000);

        List<WebElement> assertion2 = driver.findElements(By.className("oxd-text--span"));
        String actualResult = assertion2.get(11).getText();
        String expectedResult = "(1) Record Found";

        Assert.assertEquals(actualResult,expectedResult);

    }

}
