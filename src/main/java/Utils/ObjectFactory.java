package Utils;
import java.util.Arrays;

import com.microsoft.playwright.*;
public class ObjectFactory {
	private static ThreadLocal <Playwright> playWrighttl = new ThreadLocal<>();
	private static ThreadLocal <Browser> browsertl = new ThreadLocal<>();
	private static ThreadLocal <BrowserContext> browsercontexttl= new ThreadLocal<>();
	private static ThreadLocal <Page> pagetl = new ThreadLocal<>();
	
	public ObjectFactory(){
		playWrighttl.set(Playwright.create()); 
		browsertl.set(playWrighttl.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(Arrays.asList("--start-maximized"))));
	    browsercontexttl.set(browsertl.get().newContext(new Browser.NewContextOptions().setViewportSize(null)));
		pagetl.set(browsercontexttl.get().newPage()); 
	}
	
	public Page page() {
		return pagetl.get();
	}
	
	
	public void teardown() {
		browsertl.get().close();
		playWrighttl.get().close();
	}
}
