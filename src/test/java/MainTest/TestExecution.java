package MainTest;
import java.io.IOException;

import com.microsoft.playwright.Page;
import Utils.DataReporting;
import Utils.ObjectFactory;
import Pages.HomePage;
import Pages.MembershipPage;
import Pages.PremiumPage;
public class TestExecution {

	public static void main(String[]args)throws InterruptedException, IOException {
	
		ObjectFactory factory = new ObjectFactory();
		Page page = factory.page();
		page.setDefaultTimeout(120000);
		page.navigate("https://stag-app.joinditto.in/fq");
		DataReporting excel = new DataReporting("C:\\Users\\user\\eclipse-workspace\\DittoPlayWrightProject\\target\\Datafiles\\Dittofile.xlsx","sheet3");
		HomePage hpage = new HomePage(page);
		hpage.Activeone();
		MembershipPage mpage = new MembershipPage(page,excel);

		mpage.Membersvalidating("25","515122");
		PremiumPage pmp = new PremiumPage(page,excel);
		pmp.Premiumvalidation();
		
		factory.teardown(); }
		
	}

