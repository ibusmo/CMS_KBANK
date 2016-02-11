package customcomponent;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import controller.EngineController;
import log.LogCat;
import testdata.CellTag.fieldType;
import testdata.DataElementObj;

public class Popup {
	
	private WebDriver driver;
	private LogCat logCat;
	
	public Popup(EngineController comDriver, LogCat logCat) {
		driver = comDriver.wde.getDriver();
		this.logCat = logCat;
	}
	
	public WebDriver RunPopup(DataElementObj obj) {
//		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXX RunPopup");
		return auto(obj.fieldType, obj.fieldName, (int) Math.round(Double.parseDouble(obj.fieldValue)));
	}
	
	public WebDriver auto(fieldType filedType, String fieldName, int nWindows) {
//		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXX auto");
		switch(filedType){
		case id:
			return byid(fieldName, nWindows);
		case name:
//			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXX filedType");
			return byname(fieldName, nWindows);
		case xpath:
			return byxpath(fieldName, nWindows);
		case linktext:
			return bylinktext(fieldName, nWindows);
		default:
			break;
		}
		return null;
	}

	public WebDriver byxpath(String xpath, int numPopup) {
		//Wait for POPUP
		int timeCount = 1;
		do {
			driver.getWindowHandles();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				logCat.sendToLog("[POPUP]\t" + "Wait for popup " + " Thread Error");	
				e.printStackTrace();			
			}
			timeCount++;
			if (timeCount > 300) {
				// break;
				return null;
			}
		} while (driver.getWindowHandles().size() != numPopup);
		
		timeCount = 1;
		do {
			try {
				Thread.sleep(100);
				//SELECT POPUP
				for (String winHandle : driver.getWindowHandles()) {
					WebDriver popup = null;
					popup = driver.switchTo().window(winHandle);
					try {
						if (popup.findElement(By.xpath(xpath)).isDisplayed()) {
							logCat.sendToLog("[SELECT]\t" + driver.getCurrentUrl());
							return popup;
						}
					} catch (NoSuchElementException e) {
//						logCat.sendToLog("GET POPUP CRASH !!! -NoSuchElementException");
					} catch (UnreachableBrowserException e) {
						logCat.sendToLog("GET POPUP CRASH !!! -UnreachableBrowserException");
					}
				}
			} catch (InterruptedException e) {
				logCat.sendToLog("[POPUP]\t" + "Wait for popup " + " Thread Error -InterruptedException");	
				e.printStackTrace();			
			} catch (UnreachableBrowserException e) {
				logCat.sendToLog("[POPUP]\t" + "Wait for popup " + " Thread Error -UnreachableBrowserException");	
				e.printStackTrace();			
			}
			timeCount++;
			if (timeCount > 300) {
				// break;
				logCat.sendToLog("[POPUP]\t" + "Verify Element CRASH !!!");
				return null;
			}
		} while (true);
	}

	public WebDriver byname(String name, int numPopup) {
//		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXX byname");
		//Wait for POPUP
		int timeCount = 1;
		do {
			driver.getWindowHandles();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				logCat.sendToLog("[POPUP]\t" + "Wait for popup " + " Thread Error");	
				e.printStackTrace();			
			}
			timeCount++;
			if (timeCount > 300) {
				// break;
				return null;
			}
		} while (driver.getWindowHandles().size() != numPopup);

		timeCount = 1;
		do {
			try {
				Thread.sleep(100);
				//SELECT POPUP
				for (String winHandle : driver.getWindowHandles()) {
					WebDriver popup = null;
					popup = driver.switchTo().window(winHandle);
					try {
						if (popup.findElement(By.name(name)).isDisplayed()) {
							logCat.sendToLog("[SELECT]\t" + driver.getCurrentUrl());
							return popup;
						}
					}catch (NoSuchElementException e) {
//						logCat.sendToLog("GET POPUP CRASH !!! -NoSuchElementException");
					} catch (UnreachableBrowserException e) {
						logCat.sendToLog("GET POPUP CRASH !!! -UnreachableBrowserException");
					}
				}
			} catch (InterruptedException e) {
				logCat.sendToLog("[POPUP]\t" + "Wait for popup " + " Thread Error -InterruptedException");	
				e.printStackTrace();			
			} catch (UnreachableBrowserException e) {
				logCat.sendToLog("[POPUP]\t" + "Wait for popup " + " Thread Error -UnreachableBrowserException");	
				e.printStackTrace();			
			}
			timeCount++;
			if (timeCount > 300) {
				// break;
				logCat.sendToLog("[POPUP]\t" + "Verify Element CRASH !!!");
				return null;
			}
		} while (true);
	}
	

	public WebDriver byid(String id, int numPopup) {
//		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXX byname");
		//Wait for POPUP
		int timeCount = 1;
		do {
			driver.getWindowHandles();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				logCat.sendToLog("[POPUP]\t" + "Wait for popup " + " Thread Error");	
				e.printStackTrace();			
			}
			timeCount++;
			if (timeCount > 300) {
				// break;
				return null;
			}
		} while (driver.getWindowHandles().size() != numPopup);
		
		timeCount = 1;
		do {
			try {
				Thread.sleep(100);
				//SELECT POPUP
				for (String winHandle : driver.getWindowHandles()) {
					WebDriver popup = null;
					popup = driver.switchTo().window(winHandle);
					try {
						if (popup.findElement(By.id(id)).isDisplayed()) {
							logCat.sendToLog("[SELECT]\t" + driver.getCurrentUrl());
							return popup;
						}
					} catch (NoSuchElementException e) {
//						logCat.sendToLog("GET POPUP CRASH !!! -NoSuchElementException");
					} catch (UnreachableBrowserException e) {
						logCat.sendToLog("GET POPUP CRASH !!! -UnreachableBrowserException");
					}
				}
			} catch (InterruptedException e) {
				logCat.sendToLog("[POPUP]\t" + "Wait for popup " + " Thread Error -InterruptedException");	
				e.printStackTrace();			
			} catch (UnreachableBrowserException e) {
				logCat.sendToLog("[POPUP]\t" + "Wait for popup " + " Thread Error -UnreachableBrowserException");	
				e.printStackTrace();			
			}
			timeCount++;
			if (timeCount > 300) {
				// break;
				logCat.sendToLog("[POPUP]\t" + "Verify Element CRASH !!!");
				return null;
			}
		} while (true);
	}
	
	public WebDriver bylinktext(String linktext, int numPopup) {
//		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXX byname");
		//Wait for POPUP
		int timeCount = 1;
		do {
			driver.getWindowHandles();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				logCat.sendToLog("[POPUP]\t" + "Wait for popup " + " Thread Error");	
				e.printStackTrace();			
			}
			timeCount++;
			if (timeCount > 300) {
				// break;
				return null;
			}
		} while (driver.getWindowHandles().size() != numPopup);
		
		timeCount = 1;
		do {
			try {
				Thread.sleep(100);
				//SELECT POPUP
				for (String winHandle : driver.getWindowHandles()) {
					WebDriver popup = null;
					popup = driver.switchTo().window(winHandle);
					try {
						if (popup.findElement(By.linkText(linktext)).isDisplayed()) {
							logCat.sendToLog("[SELECT]\t" + driver.getCurrentUrl());
							return popup;
						}
					} catch (NoSuchElementException e) {
//						logCat.sendToLog("GET POPUP CRASH !!! -NoSuchElementException");
					} catch (UnreachableBrowserException e) {
						logCat.sendToLog("GET POPUP CRASH !!! -UnreachableBrowserException");
					}
				}
			} catch (InterruptedException e) {
				logCat.sendToLog("[POPUP]\t" + "Wait for popup " + " Thread Error -InterruptedException");	
				e.printStackTrace();			
			} catch (UnreachableBrowserException e) {
				logCat.sendToLog("[POPUP]\t" + "Wait for popup " + " Thread Error -UnreachableBrowserException");	
				e.printStackTrace();			
			}
			timeCount++;
			if (timeCount > 300) {
				// break;
				logCat.sendToLog("[POPUP]\t" + "Verify Element CRASH !!!");
				return null;
			}
		} while (true);
	}
}
