package machine;

import com.SystemBase;

import cms.BaseApplication;
import controller.Controller;
import customcomponent.InterruptTask;
import pools.XLS;
import pools.XLS.col;
import variable.PathVariable;

public class Machine extends BaseApplication {
	
	InterruptTask interrupt = new InterruptTask();
	
	MachineInput mInput;
	
	public Machine(PathVariable pathVariable) {
		this.pathVariable = pathVariable;
		super.system = SystemBase.CMS_KBANK;
		super.sheet = "machine";
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
		mInput = new MachineInput();
		
		//condition
		mInput.machineDefinition		= xls.getString(idx, 7).contains("ไม่") ? false : true;
		
		//DO NOT MOD
		mInput.informaiton				= fieldOption(xls.getString(idx, 8));
		mInput.otherInfo				= fieldOption(xls.getString(idx, 9));
		mInput.nonCRM					= nonRCM(xls.getString(idx, 10));		
		mInput.valMethod				= valMethod(xls.getString(idx, 11), 
													xls.getString(idx, 12), 
													xls.getString(idx, 13));
		mInput.cost						= fieldOption(xls.getString(idx, 14));
		
		//pre
		mInput.testcaseId 				= xls.getString(idx, 3);
		mInput.username 				= xls.getString(idx, 17);
		mInput.cif 						= getList(xls.getString(idx, 18));
		mInput.expense 					= getList(xls.getString(idx, 19));
		//pos
		mInput.collId					= "";
		
		xls.closeFile();
	}
	
	public void testScenario(){
		openBrowser(system);
		login(mInput.username, system);
		new MachineScript(ctrl, mInput).execute();
		logout();
		ctrl.disconnect();
	}	
	
	public void run(){
		int size = getSize(col.B, 1) + 1;
		for(int i=2; i<=size; i++){
			prepareData(i);
			intiTest(mInput.testcaseId);
			testScenario();	
		}
	}	
}
