package Pages;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import Utils.DataReporting;

public class PremiumPage {
	
	Page page;
	DataReporting excel;
	XSSFSheet sheet;	
	
	public PremiumPage(Page page,DataReporting excel) {
		this.page = page;
		this.excel = excel;
		this.sheet= excel.getsheet();
	}
	
	public void Premiumvalidation() throws InterruptedException, IOException {
    Locator CoverageAmounts = page.locator("//div[contains(@class,'mantine-Slider-root')]").first();
	
   
	System.out.print("Coverage amounts are "+CoverageAmounts.innerText());
	
	excel.writecell(4, 1, String.valueOf(CoverageAmounts.innerText()));
	
	Locator RecommendedAddons = page.locator("//div[contains(@id,'panel-addons')]");
	
	page.locator("//span[contains(@class, 'mantine-Accordion-chevron')]").nth(3).click();
	
	Locator otherAddons = page.locator("//div[contains(@class, 'Accordion-content')]").nth(3);
	
	System.out.print("Addons are " + RecommendedAddons.innerText());
	excel.writecell(5, 1, "Addons are "+String.valueOf(RecommendedAddons.innerText()));
	System.out.println(otherAddons.innerText());
	excel.writecell(6, 1, String.valueOf(otherAddons.innerText()));
	String totalPremium = page.locator("//span[contains(text(),'Total Premium')]/following-sibling::span").innerText();
	System.out.println("Total premium "+totalPremium);
	excel.writecell(7, 1, String.valueOf(totalPremium));
	
	
	String[] Addons= {"Claim Protect","Super Credit","Annual Health Checkup","Chronic Care","Chronic Management Program (OPD)"};
	
	for(int i =0;i<Addons.length;i++)
	{ 
		int j = i+1;  
		String DynamicAddon= "//input[@name='"+Addons[i]+"']";
		
		page.locator(DynamicAddon).first().click();
		
		
		if(Addons[i]=="Chronic Care" || Addons[i]== "Chronic Management Program (OPD)") {
			
			page.locator("//input[@type='checkbox' and contains(@class,'mantine-Checkbox-input')]").nth(10).click();
			  
			if(Addons[i]=="Chronic Care") {	
			String [] dieases= {"Diabetes","Hypertension","Asthma"};
			
			for(int m=0;m<dieases.length;m++) {
			   String diesesButton ="//span[normalize-space()='"+dieases[m]+"']";
			   page.locator(diesesButton).click();
			}   
			   	   
		}
			page.locator("//span[text()='Confirm']").click();	
		}
		
		String PremiumAfterAddon = page.locator("//span[contains(text(),'Total Premium')]/following-sibling::span").innerText();
		
		if(totalPremium.equals(PremiumAfterAddon)) {
			System.out.println("Total premium is same even after adding addon");
		}
		else {
			System.out.println("Total premium is changed after adding addon "+PremiumAfterAddon);
			excel.writecell(8, j, PremiumAfterAddon);
		}
      }
	
	}
}
