package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	private WebDriver myDriver;
	
	public HomePage(WebDriver inDriver)
	{
		this.myDriver = inDriver;
		
		PageFactory.initElements(myDriver, this);
	}
	
	@FindBy(linkText = "Giriş Yap")
	WebElement loginHypTxt;
	
	
	public void ClickLogin()
	{
		loginHypTxt.click();
	}
	
	public void waitSeconds(int inVal)
	{
		myDriver.manage().timeouts().implicitlyWait(inVal, TimeUnit.SECONDS);
	}
}
