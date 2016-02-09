package rentright;

import com.SystemBase;

import cms.BaseApplication;
import controller.Controller;
import customcomponent.InterruptTask;
import pools.XLS;
import pools.XLS.col;
import variable.PathVariable;

public class RentRigth extends BaseApplication {
	
	InterruptTask interrupt = new InterruptTask();
	
	RentRightInput input;
	
	public RentRigth(PathVariable pathVariable) {
		this.pathVariable = pathVariable;
		super.system = SystemBase.CMS_KBANK;
		super.sheet = "rentright";
		super.dir = sheet + "\\";
	}	
	
	public void intiTest(String testCaseId){
		pathVariable.setDirectory(dir + testCaseId);
		pathVariable.setLogName(testCaseId);
		pathVariable.setLogType(".log");
		super.ctrl = new Controller(pathVariable);
		super.runableFlag = true;
	}
	
	public void prepareData(int idx){
		XLS xls = new XLS(pathVariable.getRelativeExcelPath(), sheet);
		xls.openFile();
		input = new RentRightInput();
		
		//condition
		input.subtype				= xls.getString(idx, 6).contains("ดิน") ? Subtype.LandUsable : Subtype.Building;
		input.contract				= xls.getString(idx, 8).contains("ไม่") ? false : true;
		
		//DO NOT MOD
		input.informaiton			= fieldOption(xls.getString(idx, 7));
		input.otherInfo				= fieldOption(xls.getString(idx, 9));
		input.nonCRM					= nonRCM(xls.getString(idx, 10));		
		input.valMethod				= valMethod(xls.getString(idx, 11), 
													xls.getString(idx, 12), 
													xls.getString(idx, 13));		
		input.cost					= fieldOption(xls.getString(idx, 14));
		
		//pre
		input.testcaseId 			= xls.getString(idx, 3);
		input.username 				= xls.getString(idx, 17);
		input.cif 					= getList(xls.getString(idx, 18));
		input.expense 				= getList(xls.getString(idx, 19));
		//pos
		input.collId				= "";
		
		xls.closeFile();
	}

	public void testScenario(){
		openBrowser(system);
		login(input.username, system);
		new RentRigthScript(ctrl, input).execute();
		logout();
		ctrl.disconnect();
	}	
	
	public void run(){
		int start = 2;
		int size = getSize(col.B, 1);
		int max = size + start;
		System.out.println("size = " + size);
		for(int i=start; i<max; i++){
			prepareData(i);
			intiTest(input.testcaseId);
			testScenario();	
		}
	}	
}
