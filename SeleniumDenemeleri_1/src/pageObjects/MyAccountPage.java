package pageObjects;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class MyAccountPage {
	WebDriver myDriver;
	StringBuilder xpathOfElem =new StringBuilder( "//*[@id='view']/ul/li[1]/div/div[2]/a ");
	StringBuilder xpathOfDeleteElem =new StringBuilder( "//*[@id='view']/ul/li[1]/div/div[3]/span ");
	
	public MyAccountPage(WebDriver inDriver)
	{
		this.myDriver = inDriver;
	}
	
	WebElement myFavProductsBtn;
	WebElement deleteConfirmBtn;
	List<WebElement> listOfFavProducts;
	
	public void clickFavButton()
	{
		this.setFavsPageButton();
		myFavProductsBtn.click();
	}
	public void deleteConfirmClick()
	{
		this.setdeleteConfirmBtn();
		deleteConfirmBtn.click();
	}
	private void setdeleteConfirmBtn()
	{
		WebDriverWait wait = new WebDriverWait(this.myDriver, 10);
		deleteConfirmBtn = wait.until(ExpectedConditions.presenceOfElementLocated
	       (By.cssSelector("body > div.lightBox > div > div > span")));
	}
	private void setFavsPageButton()
	{
		WebDriverWait wait = new WebDriverWait(this.myDriver, 10);
		myFavProductsBtn = wait.until(ExpectedConditions.presenceOfElementLocated
	       (By.cssSelector("#myAccount > div.accContent > ul > li.wishGroupListItem.favorites > div > a")));
	}
	public void selectListOfFavProducts()
	{	
		WebDriverWait wait = new WebDriverWait(this.myDriver, 10);
		listOfFavProducts = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"view\"]/ul/li")));
	}
	public boolean isAnyProInListOfFavorites()
	{
		boolean retVal = false;
		if(listOfFavProducts.size()>0)
		{
			retVal = true;
		}	
		return retVal;
	}
	//Returns index of Product in Favorites List
	public int findProInFavorites(String inSearchElem)
	{
		int retVal = 0;		
		for(int i=1;i<=listOfFavProducts.size();++i)
		{
			xpathOfElem.setCharAt(22,Integer.toString(i).charAt(0));
			if(inSearchElem.equals(this.myDriver.findElement
			  (By.xpath(xpathOfElem.toString())).getAttribute("title").toString()))
			{
				retVal = i;
			}
		}
		return retVal;
	}
	public void deleteProFromFavList(int inDeleteIndex)
	{
		xpathOfDeleteElem.setCharAt(22,Integer.toString(inDeleteIndex).charAt(0));
		this.myDriver.findElement(By.xpath(xpathOfDeleteElem.toString())).click();	
	}
	private void clickHiddenButton(WebElement element)
	{	
		JavascriptExecutor js = (JavascriptExecutor)this.myDriver;
		js.executeScript("arguments[0].click();", element);
		
	}
	public void waitSeconds(int inVal)
	{
		myDriver.manage().timeouts().implicitlyWait(inVal, TimeUnit.SECONDS);
	}
}
