package automationFramework;

import org.testng.annotations.Test;

import pageObjects.*;

import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class TestCase {
    WebDriver driver;
    HomePage myHomePage;
    LoginPage myLoginPage;
    MainPage myMainPage;
    ProductPage myProductPage;
    MyAccountPage myAccountPage;
    String selectedProductDetails;
    
  @Test(priority = 1)
  public void loginPageTest() 
  {
  	myHomePage = new HomePage(driver);
	
  	myHomePage.ClickLogin();
  	
  	myHomePage.waitSeconds(10);
  	
  	myLoginPage = new LoginPage(driver);
  	
  	myLoginPage.waitSeconds(5);
  	
  	assertTrue(myLoginPage.isLoginScreen());
  	
  	myLoginPage.setEmailInput("kamivufoze@web2mailco.com");
  	
  	myLoginPage.setPasswordInput("24122412Aa");
  	
  	myLoginPage.clickLoginButton();
  	
  	myLoginPage.waitSeconds(10);
  	
  	myMainPage = new MainPage(driver);
  	
  	assertTrue(myMainPage.isLoggedIn());
  	
  }
  @Test (priority = 2)
  public void searchTest()
  {
	  String searchKeyword = "samsung";
	  int searchPageNum = 2;
	  myMainPage.searchData(searchKeyword);
	  
	  myMainPage.clickClosePopUp();
	  
	  assertTrue(myMainPage.isSearchDoneTrue(searchKeyword));
	  
	  myMainPage.clickSearchPage(searchPageNum);
	  
	  myMainPage.waitSeconds(5);
	  
	  assertTrue(myMainPage.isSearchPageTrue(searchPageNum));  
	  
	  myMainPage.clickSelectedProduct();
	  
	  myMainPage.waitSeconds(5);

  }
  @Test (priority = 3)
  public void productPageTest()
  {
	  myProductPage = new ProductPage(driver);
	  
	  myProductPage.selectItem();
	  
	  myProductPage.addFavouriteList();
	  
	  myProductPage.waitSeconds(2);
	  
	  selectedProductDetails = myProductPage.getSelectedProductDetais();
	  
	  myProductPage.goToFavsPage();
  }
  
  @Test (priority = 4)
  public void myAccountPageTest()
  {
	  int deleteProIndex = 0;
	  
	  myAccountPage = new MyAccountPage(driver);
	  
	  myAccountPage.clickFavButton();
	  
	  myAccountPage.waitSeconds(5);
	  
	  myAccountPage.selectListOfFavProducts();
	  
	  assertTrue(myAccountPage.isAnyProInListOfFavorites());
	  
	  deleteProIndex = myAccountPage.findProInFavorites(selectedProductDetails);
	  
	  System.out.println(myAccountPage.deleteProFromFavList(deleteProIndex));
	  
	  myAccountPage.waitSeconds(5);
	  
	  assertTrue(myAccountPage.findProInFavorites(selectedProductDetails) == 0);
	  
  }
  @BeforeTest
  public void beforeTest() 
  {
  	System.setProperty("webdriver.chrome.driver", "C:\\work\\chromedriver.exe");
	
    driver = new ChromeDriver();

    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    driver.get("https://www.n11.com/");
  }

  @AfterTest
  public void afterTest() 
  {

	  driver.close();
	  driver.quit();
  }

}
