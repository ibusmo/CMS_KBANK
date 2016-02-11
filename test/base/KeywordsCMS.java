package base;

import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.Field;
import controller.Controller;
import log.LogCat;
import log.LogTag.logaction;
import log.LogTag.logelement;
import log.LogTag.logexestatus;
import log.LogTag.logoperation;
import log.LogTag.logsubtab;
import log.LogTag.logtab;
import testdata.CellTag.*;

public abstract class KeywordsCMS{

	protected Controller ctrl;
	protected LogCat logCat;
	
	protected logoperation logoperation;
	protected logtab logtab;
	protected logsubtab logsubtab;

	protected String workBookPath;
	protected String workSheetPath;
	protected int sizeOfData;
	protected int offsetRow;
	
	protected int imgIdx;
	
	// TO BE OVERRIDE
	protected boolean preExecute(){return true;};
	protected boolean posExecute(){return true;};
	
	protected boolean initKeywords(){
		this.logCat = ctrl.logCat;
		this.workBookPath = ctrl.pathVariable.getRelativeExcelPath();
		imgIdx = 0;
		return true;
	}
	
	public boolean executeTask(){
		
		return true;
	}

	public boolean execute() {
		
		if(initKeywords()==false)					return false;
		//if(loadData()==false) 						return false;
		
		sendToLogStart();
		if(preExecute()==false) 					{	takeCapture();	return false;	}
		
		String logDebug = "";
		
			
			try{
				
				executeTask();
				
			}catch(NoSuchElementException e){
				sendToLogCustom(logexestatus.FAIL, logaction.None, logDebug + " -NoSuchElementException");
				takeCapture();
				return false;
			}catch(UnreachableBrowserException e){
				sendToLogCustom(logexestatus.FAIL, logaction.None, logDebug + " -UnreachableBrowserException");
				takeCapture();
				return false;
			}catch(InvalidElementStateException e){
				sendToLogCustom(logexestatus.FAIL, logaction.None, logDebug + " -InvalidElementStateException");
				takeCapture();
				return false;
			}catch(NoAlertPresentException e){
				sendToLogCustom(logexestatus.FAIL, logaction.None, logDebug + " -NoAlertPresentException");
				takeCapture();
				//				return false;
			}catch(TimeoutException e){
				sendToLogCustom(logexestatus.FAIL, logaction.None, logDebug + " -TimeoutException");
				takeCapture();
				return false;
			}catch(UnhandledAlertException e){
				sendToLogCustom(logexestatus.FAIL, logaction.None, logDebug + " -UnhandledAlertException");
				takeCapture();
				//				return false;
			}catch(WebDriverException e){
				sendToLogCustom(logexestatus.FAIL, logaction.None, logDebug + " -WebDriverException");
				takeCapture();
				return false;
			}catch(NullPointerException e){
				sendToLogCustom(logexestatus.FAIL, logaction.None, logDebug + " -NullPointerException");
				takeCapture();
				return false;
			}catch(Exception e){
				sendToLogCustom(logexestatus.FAIL, logaction.None, logDebug + " -Exception");	
				takeCapture();			
			}

		if(posExecute()==false){
			takeCapture();
			return false;
		}
		sendToLogFinish();
		return true;
	}
	
	// DOM Elements
	
	protected void openBrowser(String url) {
		ctrl.driver.get(url);
		ctrl.driver.manage().window().maximize();
	}
	
	protected void type(fieldType fieldType, String fieldName, String value) {
		sendToLogCustom(logexestatus.PASS, logaction.Type, fieldType + " - " + fieldName + " - " + value);
		ctrl.type.auto(fieldType, fieldName, value);
	}
	
	protected void click(fieldType fieldType, String fieldName) {
		sendToLogCustom(logexestatus.PASS, logaction.Click, fieldType + " - " + fieldName);
		ctrl.button.auto(fieldType, fieldName);
	}
	
	protected void checkbox(fieldType fieldType, String fieldName) {
		sendToLogCustom(logexestatus.PASS, logaction.Checkbox, fieldType + " - " + fieldName);
		ctrl.checkBox.auto(fieldType, fieldName);
	}	
	
	protected void checkbox(fieldType fieldType, String fieldName, boolean check) {
		sendToLogCustom(logexestatus.PASS, logaction.Checkbox, fieldType + " - " + fieldName + " - " + check);
		ctrl.checkBox.auto(fieldType, fieldName, check);
	}	
	
	protected void radio(fieldType fieldType, String fieldName, String fieldValue) {
		sendToLogCustom(logexestatus.PASS, logaction.Radio, fieldType + " - " + fieldName + " - " + fieldValue);
		ctrl.radio.auto(fieldType, fieldName, fieldValue);
	}

	protected void alert(){
		try{	
			sendToLogCustom(logexestatus.PASS, logaction.Comfirm, "Alert appeared");
			ctrl.alertHandle.execute();	
		}catch(NoAlertPresentException e){
			sendToLogCustom(logexestatus.PASS, logaction.Comfirm, "Alert not appeared");
		}
	}
	
	protected void jsExe(String jsExe) {
		sendToLogCustom(logexestatus.PASS, logaction.JSExe, "Javascript executed");
		ctrl.jsExecute.forceExe(jsExe);
	}
	
	protected void date(fieldType fieldType, String fieldName, String dateStr) {
		sendToLogCustom(logexestatus.PASS, logaction.Date, fieldType + " - " + fieldName + " - " + dateStr);
		ctrl.datePicker.auto(fieldType, fieldName, dateStr);
	}
	
	//Dropdown
	protected void dropdownXpath(String selectField, int selectIndex){
		sendToLogCustom(logexestatus.PASS, logaction.Dropdown, "xpath = " + selectField + " - " + selectIndex);
		ctrl.dropdown.robotByXpath(selectField, selectIndex);
	}	
	protected void dropdownSelectId(String selectField, int selectIndex){
		sendToLogCustom(logexestatus.PASS, logaction.Dropdown, "id = " + selectField + " - " + selectIndex);
		ctrl.dropdown.robotSelectId(selectField, selectIndex);
	}	
	protected void dropdownSelectId(String selectField, int selectIndex, int idx){
		sendToLogCustom(logexestatus.PASS, logaction.Dropdown, "id = " + selectField + " - " + selectIndex + " - " + idx);
		ctrl.dropdown.robotSelectId(selectField, selectIndex, idx);
	}
	protected void dropdownSelectName(String selectField, int selectIndex){
		sendToLogCustom(logexestatus.PASS, logaction.Dropdown, "name = " + selectField + " - " + selectIndex);
		ctrl.dropdown.robotSelectName(selectField, selectIndex);
	}	
	protected void dropdownSelectName(String selectField, int selectIndex, int idx){
		sendToLogCustom(logexestatus.PASS, logaction.Dropdown, "name = " + selectField + " - " + selectIndex + " - " + idx);
		ctrl.dropdown.robotSelectName(selectField, selectIndex, idx);
	}
	
	protected boolean verify(fieldType fieldType, String fieldName, String dateStr) {
		if(ctrl.verifyData.runVerify(fieldType, fieldName, dateStr)){
			sendToLogCustom(logexestatus.PASS, logaction.Verify, fieldType + " - " + fieldName + " - " + dateStr);
			return true;
		}
		else{
			sendToLogCustom(logexestatus.FAIL, logaction.Verify, fieldType + " - " + fieldName + " - " + dateStr);
			takeCapture();
			return false;
		}
	}	

	protected String getTextByXpath(String fieldName) {
		String str = ctrl.verifyData.getTextByXpath(fieldName);
		sendToLogCustom(logexestatus.PASS, logaction.Verify, fieldName + " - " + str);
		return str;
	}		
	protected String getValueByXpath(String fieldName) {
		String str = ctrl.verifyData.getValueByXpath(fieldName);
		sendToLogCustom(logexestatus.PASS, logaction.Verify, fieldName + " - " + str);
		return str;
	}
	protected String getTextById(String fieldName) {
		String str = ctrl.verifyData.getTextById(fieldName);
		sendToLogCustom(logexestatus.PASS, logaction.Verify, fieldName + " - " + str);
		return str;
	}		
	protected String getValueById(String fieldName) {
		String str = ctrl.verifyData.getValueById(fieldName);
		sendToLogCustom(logexestatus.PASS, logaction.Verify, fieldName + " - " + str);
		return str;
	}
	protected String getTextByName(String fieldName) {
		String str = ctrl.verifyData.getTextByName(fieldName);
		sendToLogCustom(logexestatus.PASS, logaction.Verify, fieldName + " - " + str);
		return str;
	}		
	protected String getValueByName(String fieldName) {
		String str = ctrl.verifyData.getValueByName(fieldName);
		sendToLogCustom(logexestatus.PASS, logaction.Verify, fieldName + " - " + str);
		return str;
	}

	protected boolean popup(fieldType fieldType, String fieldName, int nWindows) {
			WebDriver popup = ctrl.popup.auto(fieldType, fieldName, nWindows);
			if (popup != null) {
				sendToLogCustom(logexestatus.PASS, logaction.Popup, fieldType + " - " + fieldName + " - " + nWindows);
			} else {
				sendToLogCustom(logexestatus.FAIL, logaction.Popup, fieldType + " - " + fieldName + " - " + nWindows);
				takeCapture();
				return false;
			}
		return true;
	}
	
	protected boolean save() {
		sendToLogCustom(logexestatus.PASS, logaction.Save, "Save Javascript executed");
		ctrl.jsExecute.forceExe("selectPage('doSave')");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}	
	
	protected boolean saveDraft() {
		sendToLogCustom(logexestatus.PASS, logaction.SaveDraft, "SaveDraft Javascript executed");
		ctrl.jsExecute.forceExe("selectPage('doSaveDraft')");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}	
	
	protected boolean saveOK() {
		sendToLogCustom(logexestatus.PASS, logaction.SaveOK, "SaveOK Javascript executed");
		ctrl.jsExecute.forceExe("selectPage('ok')");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	protected boolean searchCIF() {
		sendToLogCustom(logexestatus.PASS, logaction.searchCIF, "Search CIF Javascript executed");
		ctrl.jsExecute.forceExe("selectPage('doPreSearch')");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}	
	
	protected boolean addExpense() {
		sendToLogCustom(logexestatus.PASS, logaction.addExpense, "Add Expense Javascript executed");
		ctrl.jsExecute.forceExe("showExpenseAdd();");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	protected void otherInfo(Field otherInfo, boolean nonCRM, int... selectIdx) {
		logsubtab 			= log.LogTag.logsubtab.OtherInfo;
		//ข้อมูลนับสนุน ธปท.
		click(fieldType.linktext, "ข้อมูลสนับสนุน ธปท.");

		//Require Field
		if(otherInfo.equals(Field.All) || otherInfo.equals(Field.Require)){
			dropdownXpath("//*[@id=\"botCdDiv\"]/input[2]", selectIdx[0]);
			dropdownXpath("//*[@id=\"subbotCdDiv\"]/input[2]",  selectIdx[1]);
			
			ctrl.waitFor.wait = new WebDriverWait(ctrl.driver, 2);
			
			//May the force be with you. 3
			String selectField = "//*[@id=\"content\"]/div/form/div/table/tbody/tr[3]/td[2]/input";
			try{
				//Not a checkbox
				if(!ctrl.driver.findElement(By.xpath(selectField)).getAttribute("type").contains("checkbox")){
					dropdownXpath(selectField, selectIdx[2]);
				}
				else{	
					sendToLogCustom(logexestatus.PASS, logaction.Dropdown, selectField + " - " +  selectIdx[2] 
							+ " - " + "The force not be here 3 1 -Not a chekbox");			
				}
			}catch(Exception e){
				sendToLogCustom(logexestatus.PASS, logaction.Dropdown, selectField + " - " +  selectIdx[2] 
						+ " - " + "The force not be here 3 1 -Exception");				
			}
			selectField = "//*[@id=\"content\"]/div/form/div/table/tbody/tr[3]/td[2]/div/input";
			try{
				//Not a checkbox
				if(!ctrl.driver.findElement(By.xpath(selectField)).getAttribute("type").contains("checkbox")){
					dropdownXpath(selectField, selectIdx[2]);
				}
				else{			
					sendToLogCustom(logexestatus.PASS, logaction.Dropdown, selectField + " - " +  selectIdx[2] 
							+ " - " + "The force not be here 3 2 div -Not a chekbox");		
				}
			}catch(Exception e){
				sendToLogCustom(logexestatus.PASS, logaction.Dropdown, selectField + " - " +  selectIdx[2] 
						+ " - " + "The force not be here 3 2 div -Exception");				
			}
				
			
			//May the force be with you. 4
			selectField = "//*[@id=\"content\"]/div/form/div/table/tbody/tr[4]/td[2]/input";
			try{
				//Not a checkbox
				if(!ctrl.driver.findElement(By.xpath(selectField)).getAttribute("type").contains("checkbox")){
					dropdownXpath(selectField, selectIdx[3]);
				}
				else{			
					sendToLogCustom(logexestatus.PASS, logaction.Dropdown, selectField + " - " +  selectIdx[3] 
							+ " - " + "The force not be here 4 1 -Not a checkbox");			
				}
			}catch(Exception e){
				sendToLogCustom(logexestatus.PASS, logaction.Dropdown, selectField + " - " +  selectIdx[3] 
						+ " - " + "The force not be here 4 1 -Exception");					
			}
			selectField = "//*[@id=\"content\"]/div/form/div/table/tbody/tr[4]/td[2]/div/input[2]";
			try{
				//Not a checkbox
				if(!ctrl.driver.findElement(By.xpath(selectField)).getAttribute("type").contains("checkbox")){
					dropdownXpath(selectField, selectIdx[3]);
				}
				else{			
					sendToLogCustom(logexestatus.PASS, logaction.Dropdown, selectField + " - " +  selectIdx[3] 
							+ " - " + "The force not be here 4 2 div -Not a checkbox");		
				}
			}catch(Exception e){
				sendToLogCustom(logexestatus.PASS, logaction.Dropdown, selectField + " - " +  selectIdx[3] 
						+ " - " + "The force not be here 4 2 div -Exception");					
			}
			
			ctrl.waitFor.wait = new WebDriverWait(ctrl.driver, 10);
		}

		//Non-CRM
		if(nonCRM){
			checkbox(fieldType.name ,"isNoncrm", true);
		}else{
			checkbox(fieldType.name ,"isNoncrm", false);			
		}
		
		saveDraft();
		takeExecuteCapture(logsubtab.toString());	
	}
	
	protected void valuateMethod(config.ValuateMethod valMethod) {
		logsubtab 			= log.LogTag.logsubtab.ValuateMethod;
		//การประเมินราคา
		click(fieldType.linktext, "การประเมินราคา");
		
		switch(valMethod){
			//		บันทึกข้อมูลการประเมินราคา-เลือกประเมินราคาในประเทศ
			//		บันทึกข้อมูลการประเมินราคา-เลือกประเมินหลักประกันรวม
			//		บันทึกข้อมูลการประเมินราคา-เลือกหลักประกันเป็นตัวหลัก
			
			// N - -
			case External_NOO:
				radio(fieldType.name, "isDomesticAppraisal", "2");
				
				type(fieldType.name, "overSeaAppraisal", "BUSMO ENTPRZ");
				type(fieldType.name, "foreignApprovalDept", "สำนักทรัพย์สินไทย");
				break;
				
			// Y N -
			case InternalAll_YNO:
				radio(fieldType.name, "isDomesticAppraisal", "1");
				radio(fieldType.name, "isConsolidate", "2");
				
				dropdownXpath("//*[@id=\"trApprId\"]/td[2]/input", 4);
				radio(fieldType.name, "approvalBy", "1");
				dropdownXpath("//*[@id=\"divInbound1_sel\"]/input", 2);
				dropdownXpath("//*[@id=\"trInbound5\"]/td[2]/input[2]", 2);
				break;
			
			// Y Y N
			case InternalPrimary_YYN:
				radio(fieldType.name, "isDomesticAppraisal", "1");
				radio(fieldType.name, "isConsolidate", "1");
				radio(fieldType.name, "isMain", "2");
				
				click(fieldType.xpath, "//*[@id=\"divMainColl\"]/img");
				popup(fieldType.name, "searchCollId", 2);
				dropdownXpath("/html/body/form/table[1]/tbody/tr[1]/td[2]/input", 1);
				type(fieldType.name, "searchCollId", "");
				click(fieldType.xpath, "/html/body/form/table[1]/tbody/tr[89]/td/input[1]");
				//
				popup(fieldType.xpath, "//*[@id=\"divMainColl\"]/img", 1);
				
				dropdownXpath("//*[@id=\"trApprId\"]/td[2]/input", 4);
				radio(fieldType.name, "approvalBy", "1");
				dropdownXpath("//*[@id=\"divInbound1_sel\"]/input", 2);
				dropdownXpath("//*[@id=\"trInbound5\"]/td[2]/input[2]", 2);
				break;
			
			// Y Y Y
			case InternalSecondary_YYY:
				radio(fieldType.name, "isDomesticAppraisal", "1");
				radio(fieldType.name, "isConsolidate", "1");
				radio(fieldType.name, "isMain", "1");
				
				dropdownXpath("//*[@id=\"trApprId\"]/td[2]/input", 4);
				radio(fieldType.name, "approvalBy", "1");
				dropdownXpath("//*[@id=\"divInbound1_sel\"]/input", 2);
				dropdownXpath("//*[@id=\"trInbound5\"]/td[2]/input[2]", 2);
				break;
				
			default:
				break;
		}
			
		saveDraft();
		takeExecuteCapture(logsubtab.toString());
	}
	
	protected void expense(String[] expenseList) {
		logsubtab 			= log.LogTag.logsubtab.Expense;
		//ค่าใช้จ่ายหลักประกัน
		click(fieldType.linktext, "ค่าใช้จ่ายหลักประกัน");
		int idx = 2;
		for(String expense : expenseList){
			if(expenseList != null && expense != null && !expense.contains("no")){
				addExpense();
				date(fieldType.name, "addExpenseDt", "today");
				dropdownXpath("//*[@id=\"tbl_collExpenseAdd\"]/tbody/tr[1]/td[4]/input[2]", idx++);
				type(fieldType.name, "addExpenseAmount", expense);
				checkbox(fieldType.name, "addIsPayment");
				click(fieldType.xpath, "//*[@id=\"trButton\"]/td[4]/input[1]");
			}
		}
		saveDraft();		
		takeExecuteCapture(logsubtab.toString());
	}
	
	protected void owner(String[] cifList) {
		logsubtab 			= log.LogTag.logsubtab.CIF;
		//ผู้ถือกรรมสิทธิ์
		click(fieldType.linktext, "ผู้ถือกรรมสิทธิ์");
		
		for(String cif : cifList){
			if(cif != null && !cif.contains("no")){
				searchCIF();
				dropdownXpath("//*[@id=\"content\"]/div/form/div/table/tbody/tr[1]/td/input", 2);
				alert();
				type(fieldType.name, "searchCifNo", cif);
				click(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table/tbody/tr[7]/td/input[2]");
				checkbox(fieldType.name, "arrCif");
				click(fieldType.id, "btnAdd");
			}
		}
	
		takeExecuteCapture(logsubtab.toString());
		save();
	}
	
	protected void create(int collType, int subType) {
		logsubtab 			= log.LogTag.logsubtab.Create;
		click(fieldType.linktext, "ข้อมูลหลักประกัน");
		click(fieldType.linktext, "บันทึกหลักประกัน");		
		dropdownSelectId("sel_colType", collType, 1);		
		dropdownSelectId("sel_colSubType", subType);
		//		dropdown("//*[@id=\"content\"]/div/form/div/table/tbody/tr[1]/td[2]/input[1]", collType);
		//		dropdown("//*[@id=\"sel_colSubTypeDiv\"]/input[1]", subType);
		takeExecuteCapture(logsubtab.toString());	
		click(fieldType.value, "สร้างหลักประกันใหม่");
		//		click(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table/tbody/tr[3]/td/input");
	}
	
	protected String complete() {
		logsubtab 			= log.LogTag.logsubtab.SaveOK;
		takeExecuteCapture(logsubtab.toString());
		String collId = getTextByXpath("//*[@id=\"content\"]/div/form/table/tbody/tr[1]/td[2]");
		saveOK();
		takeExecuteCapture(logsubtab.toString());
		return collId;
	}
	
	protected void general() {
		logsubtab 			= log.LogTag.logsubtab.General;
		takeExecuteCapture(logsubtab.toString());
	}
	
	// Helper
	
	protected String getNum(int point){
		  Random ran = new Random();
		  int low = (int) Math.pow(10, point-1);
		  int high = (int) Math.pow(10, point)-low;
		  int tmp = ran.nextInt(high) + low;
		  return ""+tmp;
	}

	// Log
	
	protected void sendToLogStart() {
		sendToLogCustom(logexestatus.START, logaction.None, null);
	}

	protected void sendToLogFinish() {
		sendToLogCustom(logexestatus.FINISH, logaction.None, null);
	}
	
	protected void sendToLogCustom(logexestatus logexestatus, logaction logaction, String str) {
		logCat.sendToLog(logexestatus, logoperation, logtab, logsubtab, logelement.None,
				logaction, str);
	}
	
	protected void takeCapture(){
		try{
			ctrl.screenCapture.saveShotImage(ctrl.pathVariable.getRelativeLog() + "_" + "crash" + ".jpg");
		}catch(UnreachableBrowserException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Capture, "crash connot capture");	
		}
		sendToLogCustom(logexestatus.PASS, logaction.Capture, "crash");
	}		
	
	protected void takeCapture(String step){
		try{
			ctrl.screenCapture.saveShotImage(ctrl.pathVariable.getRelativeLog() + "_" + step + ".jpg");		
		}catch(UnreachableBrowserException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Capture, "crash connot capture");	
		}
		sendToLogCustom(logexestatus.PASS, logaction.Capture, step);
	}	
	
	protected void takeExecuteCapture(String step){
		step = ++imgIdx + "_" + step;
		try{
			ctrl.screenCapture.saveShotImage(ctrl.pathVariable.getRelativeLog() + "_" + step + ".jpg");
		}catch(UnreachableBrowserException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Capture, "crash connot capture");	
		}
		sendToLogCustom(logexestatus.PASS, logaction.Capture, step);
	}
	
}
