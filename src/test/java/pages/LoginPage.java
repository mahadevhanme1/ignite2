package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	public static WebDriver d;
	
	public LoginPage(WebDriver driver)
	{
		LoginPage.d = driver;
	}
	
	@FindBy(xpath=".//h1") WebElement page;
	@FindBy(xpath=".//a[contains(@href,'dramabooks')]/button") WebElement dramaButton;
	@FindBy(xpath=".//div[contains(@class,'computer')]//a/h5[contains(text(),'The Lost')]") WebElement book;

	
	public void clickDramaButton()
	{
		waitForVisibility(dramaButton);
		dramaButton.click();
	}

	private void waitForVisibility(WebElement element) throws Error{
		new WebDriverWait(d, 60)
				.until(ExpectedConditions.visibilityOf(element));
	}

	public void clickBook()
	{
		waitForVisibility(book);
		book.click();
	}
	
	public String getApplicationTitle()
	{
		waitForVisibility(page);
		String t = page.getText();
		return t;
	}

}
