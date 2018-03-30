package pageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
	private WebDriver myDriver;
	private String searchVerifyString = "bulundu";
	private String lastElementNameOfPagination = "nextBlock navigation";
	boolean isSearched = false;
	
	public MainPage(WebDriver inDriver)
	{
		this.myDriver = inDriver;
		
		PageFactory.initElements(myDriver, this);
	}
	
	WebElement searchResultText;
	WebElement popUpButton;
	WebElement currentPageInfo;
	
	@FindBy(xpath = "//*[@id=\'header\']/div/div/div[2]/div[2]/div[1]/div[1]/a[2]")
	WebElement loggedInName;
	
	@FindBy(id = "searchData")
	WebElement searchData;
	
	@FindBy(className = "searchBtn")
	WebElement searchBtn;
	
	@FindBy(css = "#view > ul > li:nth-child(3) > div > div > a")
	WebElement selectedProduct;
	
	List<WebElement> pagesOfSearchResult;
	
	private void setCurrentPageInfo()
	{
		WebDriverWait wait = new WebDriverWait(this.myDriver, 10);
		currentPageInfo = wait.until(ExpectedConditions.presenceOfElementLocated
	       (By.cssSelector("#contentListing > div > div > div.productArea > div.pagination > div > input")));
	}
	private void setPopUpButton()
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(this.myDriver, 10);
			popUpButton = wait.until(ExpectedConditions.presenceOfElementLocated
		       (By.cssSelector("body > div.seg-popup.seg-top-center.segFadeInUp.fancybox-segmentify.fancybox-segmentify-notification > span")));
		}
		catch(TimeoutException e)
		{
			System.out.println("Timeout Exception!");
		}
		
	}
	private void setSearchResultText()
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(this.myDriver, 10);
			searchResultText = wait.until(ExpectedConditions.presenceOfElementLocated
		       (By.cssSelector("#contentListing > div > div > div.productArea > section > div.header > div.resultText")));
		}
		catch(TimeoutException e)
		{
			System.out.println("TimeOut!");
		}
	}
	public void clickClosePopUp()
	{
		this.setPopUpButton();
		popUpButton.click();
	}
	public void clickSelectedProduct()
	{
		selectedProduct.click();
	}
	public void clickSearchPage(int inVal)
	{
		pagesOfSearchResult = this.myDriver.findElements(
				By.cssSelector("#contentListing > div > div > div.productArea > div.pagination > a"));
		int lastIndexOfPages = pagesOfSearchResult.size()-1;
		// verified paginations fist twelve elements
		// in future maybe other elements could be added to this function
		if(pagesOfSearchResult.get(0).getText().contains("1") &&
		   pagesOfSearchResult.get(lastIndexOfPages).getAttribute("class").contains(lastElementNameOfPagination))
		{
			for (WebElement webElement : pagesOfSearchResult) 
			{
				if(webElement.getText().contains(Integer.toString(inVal)))
				{
					webElement.click();
					break;
				}
			}
		}

	}
	public boolean isSearchPageTrue(int inVal)
	{
		this.setCurrentPageInfo();
		boolean retVal = false;
		if(currentPageInfo.getAttribute("value").contains(Integer.toString(inVal)))
		{
			retVal = true;
		}
		return retVal;
	}
	
	public boolean isLoggedIn()
	{
		boolean retVal = false;
		if(loggedInName.getText().contains("Levent Bilginer"))
		{
			retVal = true;
		}
		return retVal;
	}
	
	public void waitSeconds(int inVal)
	{
		myDriver.manage().timeouts().implicitlyWait(inVal, TimeUnit.SECONDS);
	}
	
	public void searchData(String inData) 
	{
		searchData.clear();
		searchData.sendKeys(inData);
		searchBtn.click();
	}
	
	public boolean isSearchDoneTrue(String inSearchKey)
	{
		this.setSearchResultText();
		String tmpResultText = searchResultText.getText().toLowerCase();
		if (tmpResultText.contains(inSearchKey) && 
		    tmpResultText.contains(searchVerifyString))
		{
			isSearched = true;
		}
		return isSearched;
	}
	
	
	
}
