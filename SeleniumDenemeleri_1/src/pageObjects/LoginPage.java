package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	private WebDriver myDriver;
	private String loginAreaTitleText = "Üye";
	
	public LoginPage(WebDriver inDriver)
	{
		this.myDriver = inDriver;
		
		PageFactory.initElements(myDriver, this);
	}
	
	@FindBy(xpath = "//*[@id=\'loginContainer\']/div/div[1]/h2")
	WebElement loginAreaTitle;
	
	@FindBy(id = "email")
	WebElement emailInput;
	
	@FindBy(name = "password")
	WebElement passwordInput;
	
	@FindBy(id = "loginButton")
	WebElement loginButton;
	
	
	
	
	public boolean isLoginScreen()
	{
		if (loginAreaTitle.getText().contains(loginAreaTitleText))
		{
			return true;
		}
		return false;
	}

	public void waitSeconds(int inVal)
	{
		myDriver.manage().timeouts().implicitlyWait(inVal, TimeUnit.SECONDS);
	}
	
	public void clickLoginButton()
	{
		loginButton.click();
	}
	public void setEmailInput(String inVal)
	{
		emailInput.clear();
		emailInput.sendKeys(inVal);
	}
	public void setPasswordInput(String inVal)
	{
		passwordInput.clear();
		passwordInput.sendKeys(inVal);
	}

}

