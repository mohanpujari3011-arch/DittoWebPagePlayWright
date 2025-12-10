package Pages;

import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import Utils.DataReporting;

public class MembershipPage  {
	
DataReporting excel;
XSSFSheet sheet;	
Page page;
	
public MembershipPage(Page page,DataReporting excel) {
	
this.page = page;
this.excel = excel;
this.sheet = excel.getsheet();
}
public void Membersvalidating(String age, String pincode) throws InterruptedException, IOException {
	
	
	Locator members = page.locator("//div[contains(@class,'mantine-Chip-root')]");

	int membersoptionscount = members.count();
	System.out.println("Total options are "+ membersoptionscount);
	excel.writecell(1, 1, String.valueOf(membersoptionscount));
	
	
	for(int i=0;i<membersoptionscount;i++) {
		int j =i+1;
		System.out.println(members.nth(i).innerText());
		String membersoption=members.nth(i).innerText();
		excel.writecell(2, j, membersoption);
		
	}
	
	String self = members.nth(0).innerText();
	
	if(self.contains("Male")&& self.contains("Female")) {
		System.out.println("Self option contains Male & Female ");
		excel.writecell(3, 1, self);
	}
	else {
		System.out.print("Self not contains male & female");
	}
	
	
	page.locator("//div[contains(@class,'mantine-Chip-root')][1]").click();
	page.locator("//span[text()='Next step']").click();
	page.locator("//input[@placeholder='Your age']").fill(age);
	page.locator("//input[@placeholder='Enter your pin code']").fill(pincode);
	page.locator("//div[@name='planType']").first().click();
	page.locator("//span[text()='Calculate Premium']").first().click();
	}
}

