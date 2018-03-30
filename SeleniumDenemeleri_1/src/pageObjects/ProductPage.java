package pageObjects;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
public class ProductPage {

	WebDriver myDriver;
	public ProductPage(WebDriver inDriver)
	{
		this.myDriver = inDriver;
	}
	
	@FindBy(css = "#skuArea > fieldset > select")
	WebElement colorSelector;
	
	WebElement wishListButton;
	
	WebElement favsPageButton;
	
	WebElement selectedProduct;
	
	private void setSelectedProduct()
	{
		WebDriverWait wait = new WebDriverWait(this.myDriver, 10);
		selectedProduct = wait.until(ExpectedConditions.presenceOfElementLocated
	       (By.cssSelector("#contentProDetail > div > div.proDetailArea > div.proDetail > div.proNameHolder > div > h1")));
	}
	private void setWishListButton()
	{
		WebDriverWait wait = new WebDriverWait(this.myDriver, 10);
		wishListButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#addToFavouriteWishListBtn")));
	}
	private void setFavsPageButton()
	{
		WebDriverWait wait = new WebDriverWait(this.myDriver, 10);
		favsPageButton = wait.until(ExpectedConditions.presenceOfElementLocated
	       (By.cssSelector("#header > div > div > div.hMedMenu > div.customMenu > "
	   			+ "div.myAccountHolder.customMenuItem.hasMenu > div.myAccountMenu.hOpenMenu > div > a:nth-child(2)")));
	}
	public boolean selectItem()
	{
		boolean retVal = false;
		boolean isSkuAreaExist = this.myDriver.findElements(By.cssSelector("#skuArea")).size() > 1;		
		if(isSkuAreaExist)
		{
			List<WebElement> selectOptions;
			Select colors = new Select(colorSelector);
			selectOptions = colors.getAllSelectedOptions();
			if(selectOptions.size() != 0)
			{
				selectOptions.get(1).click();
				retVal = true;
			}
		}
		return retVal;
	}
	public void addFavouriteList()
	{	
		this.setWishListButton();
		this.clickHiddenButton(wishListButton);
	}
	private void clickHiddenButton(WebElement element)
	{
		
		JavascriptExecutor js = (JavascriptExecutor)this.myDriver;
		js.executeScript("arguments[0].click();", element);
		
	}
	public void goToFavsPage()
	{
		this.setFavsPageButton();
		this.clickHiddenButton(favsPageButton);
	}
	public String getSelectedProductDetais()
	{
		this.setSelectedProduct();
		return selectedProduct.getText();	
	}
	public void waitSeconds(int inVal)
	{
		myDriver.manage().timeouts().implicitlyWait(inVal, TimeUnit.SECONDS);
	}
}
