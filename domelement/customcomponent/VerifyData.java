package customcomponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import controller.EngineController;
import testdata.CellTag.fieldType;
import testdata.DataElementObj;

public class VerifyData{	
	
	WebDriver driver;
	WaitFor waitFor;
	
	public VerifyData(EngineController comDriver) {
		driver = comDriver.wde.getDriver();
		waitFor = comDriver.waitFor;
	}
	
	public boolean runVerify(fieldType fieldType, String fieldName, String fieldValue) {
		switch(fieldType){
		case id:
			break;
		case name:
			break;
		case xpath:
			break;
		case linktext:
			break;

		case urlContains:
			return urlContains(fieldValue);
		case urlMatches:
			return urlMatches(fieldValue);
			
		case textContainsById:
			return textContainsById(fieldName, fieldValue);
		case textContainsByLinkText:
			return textContainsByLinkText(fieldName, fieldValue);
		case textContainsByName:
			return textContainsByName(fieldName, fieldValue);
		case textContainsByXpath:
			return textContainsByXpath(fieldName, fieldValue);
			
		case textMatchesById:
			return textMatchesById(fieldName, fieldValue);
		case textMatchesByLinkText:
			return textMatchesByLinkText(fieldName, fieldValue);
		case textMatchesByName:
			return textMatchesByName(fieldName, fieldValue);
		case textMatchesByXpath:
			return textMatchesByXpath(fieldName, fieldValue);
			
		case valueContainsById:
			return valueContainsById(fieldName, fieldValue);
		case valueContainsByLinkText:
			return valueContainsByLinkText(fieldName, fieldValue);
		case valueContainsByName:
			return valueContainsByName(fieldName, fieldValue);
		case valueContainsByXpath:
			return valueContainsByXpath(fieldName, fieldValue);
			
		case valueMatchesById:
			return valueMatchesById(fieldName, fieldValue);
		case valueMatchesByLinkText:
			return valueMatchesByLinkText(fieldName, fieldValue);
		case valueMatchesByName:
			return valueMatchesByName(fieldName, fieldValue);
		case valueMatchesByXpath:
			return valueMatchesByXpath(fieldName, fieldValue);
			
		default:
			break;
		}
		return false;
	}
	
	public boolean runVerify(DataElementObj obj) {
		switch(obj.fieldType){
		case id:
			break;
		case name:
			break;
		case xpath:
			break;
		case linktext:
			break;

		case urlContains:
			return urlContains(obj.fieldValue);
		case urlMatches:
			return urlMatches(obj.fieldValue);
			
		case textContainsById:
			return textContainsById(obj.fieldName, obj.fieldValue);
		case textContainsByLinkText:
			return textContainsByLinkText(obj.fieldName, obj.fieldValue);
		case textContainsByName:
			return textContainsByName(obj.fieldName, obj.fieldValue);
		case textContainsByXpath:
			return textContainsByXpath(obj.fieldName, obj.fieldValue);
			
		case textMatchesById:
			return textMatchesById(obj.fieldName, obj.fieldValue);
		case textMatchesByLinkText:
			return textMatchesByLinkText(obj.fieldName, obj.fieldValue);
		case textMatchesByName:
			return textMatchesByName(obj.fieldName, obj.fieldValue);
		case textMatchesByXpath:
			return textMatchesByXpath(obj.fieldName, obj.fieldValue);
			
		case valueContainsById:
			return valueContainsById(obj.fieldName, obj.fieldValue);
		case valueContainsByLinkText:
			return valueContainsByLinkText(obj.fieldName, obj.fieldValue);
		case valueContainsByName:
			return valueContainsByName(obj.fieldName, obj.fieldValue);
		case valueContainsByXpath:
			return valueContainsByXpath(obj.fieldName, obj.fieldValue);
			
		case valueMatchesById:
			return valueMatchesById(obj.fieldName, obj.fieldValue);
		case valueMatchesByLinkText:
			return valueMatchesByLinkText(obj.fieldName, obj.fieldValue);
		case valueMatchesByName:
			return valueMatchesByName(obj.fieldName, obj.fieldValue);
		case valueMatchesByXpath:
			return valueMatchesByXpath(obj.fieldName, obj.fieldValue);
			
		default:
			break;
		}
		return false;
	}
	
//	Get properties section
	
	public String getValueById(String field){
		waitFor.id(field);
		return driver.findElement(By.id(field)).getAttribute("value");
	}
	
	public String getTextById(String field){
		waitFor.id(field);
		return driver.findElement(By.id(field)).getText();
	}

	public String getValueByName(String field){
		waitFor.name(field);
		return driver.findElement(By.name(field)).getAttribute("value");
	}

	public String getTextByName(String field){
		waitFor.name(field);
		return driver.findElement(By.name(field)).getText();
	}
	
	public String getValueByLinkText(String field){
		waitFor.linkText(field);
		return driver.findElement(By.linkText(field)).getAttribute("value");
	}
	
	public String getTextByLinkText(String field){
		waitFor.linkText(field);
		return driver.findElement(By.linkText(field)).getText();
	}

	public String getValueByXpath(String field){
		waitFor.xpath(field);
		return driver.findElement(By.xpath(field)).getAttribute("value");
	}

	public String getTextByXpath(String field){
		waitFor.xpath(field);
		return driver.findElement(By.xpath(field)).getText();
	}
	
	//size
	public int getSizeById(String field){
		waitFor.id(field);
		return driver.findElements(By.id(field)).size();
	}
	public int getSizeByName(String field){
		waitFor.name(field);
		return driver.findElements(By.name(field)).size();
	}
	public int getSizeByLinkText(String field){
		waitFor.linkText(field);
		return driver.findElements(By.linkText(field)).size();
	}
	public int getSizeByXpath(String field){
		waitFor.xpath(field);
		return driver.findElements(By.xpath(field)).size();
	}
	
	public String getURL(){
		return driver.getCurrentUrl();
	}

//	Verify section	
//	url
	public boolean urlMatches(String url){
		return getURL().matches(url);		
	}
	public boolean urlContains(String url){
		return getURL().contains(url);		
	}
	
//	text
//	Matches
	public boolean textMatchesById(String field, String text){
		return getTextById(field).matches(text);	
	}
	public boolean textMatchesByName(String field, String text){
		return getTextByName(field).matches(text);	
	}
	public boolean textMatchesByXpath(String field, String text){
		return getTextByXpath(field).matches(text);	
	}
	public boolean textMatchesByLinkText(String field, String text){
		return getTextByLinkText(field).matches(text);	
	}
//	Contains
	public boolean textContainsById(String field, String text){
		return getTextById(field).contains(text);	
	}
	public boolean textContainsByName(String field, String text){
		return getTextByName(field).contains(text);	
	}
	public boolean textContainsByXpath(String field, String text){
		return getTextByXpath(field).contains(text);	
	}
	public boolean textContainsByLinkText(String field, String text){
		return getTextByLinkText(field).contains(text);	
	}
	
//	value
//	Matches
	public boolean valueMatchesById(String field, String text){
		return getValueById(field).matches(text);	
	}
	public boolean valueMatchesByName(String field, String text){
		return getValueByName(field).matches(text);	
	}
	public boolean valueMatchesByXpath(String field, String text){
		return getValueByXpath(field).matches(text);	
	}
	public boolean valueMatchesByLinkText(String field, String text){
		return getValueByLinkText(field).matches(text);	
	}
//	Contains
	public boolean valueContainsById(String field, String text){
		return getValueById(field).contains(text);	
	}
	public boolean valueContainsByName(String field, String text){
		return getValueByName(field).contains(text);	
	}
	public boolean valueContainsByXpath(String field, String text){
		return getValueByXpath(field).contains(text);	
	}
	public boolean valueContainsByLinkText(String field, String text){
		return getValueByLinkText(field).contains(text);	
	}
	
}
