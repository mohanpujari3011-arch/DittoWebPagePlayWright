package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {
Page page;	

public HomePage(Page page){
	this.page = page; }

public void Activeone() throws InterruptedException  {
	page.locator("//span[@class='MuiBox-root jss178']").click();
	
	int times = 3;
	Locator element = page.locator("//span[contains(text(),'Next')]");

	for(int i=0;i<times;i++) {
		element.click();
	}
	
	page.locator("//span[contains(text(),'Continue')]").click();
    page.waitForTimeout(10000);
    
	}
}
