package bond;

import com.SystemBase;

import cms.BaseApplication;
import controller.Controller;
import customcomponent.InterruptTask;
import pools.XLS;
import pools.XLS.col;
import variable.PathVariable;

public class Bond extends BaseApplication {
	
	InterruptTask interrupt = new InterruptTask();
	
	BondInput input;
	
	public Bond(PathVariable pathVariable) {
		this.pathVariable = pathVariable;
		super.system = SystemBase.CMS_KBANK;
		super.sheet = "bond";
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
		input = new BondInput();
		
		//condition
		String tempType = xls.getString(idx, 6);
		if(tempType.contains("พันธบัตร")){
			input.subtype = Subtype.Bond;
		}
		else if(tempType.contains("หุ้นกู้แปลงสภาพ")){
			input.subtype = Subtype.ConvertibleBond;
		}
		else if(tempType.contains("หุ้นกู้")){
			input.subtype = Subtype.Debenture;
		}
		else if(tempType.contains("ตั๋วเงินคลัง")){
			input.subtype = Subtype.Financial;
		}
		
		//DO NOT MOD
		input.informaiton			= fieldOption(xls.getString(idx, 7));
		input.otherInfo				= fieldOption(xls.getString(idx, 8));
		input.nonCRM				= nonRCM(xls.getString(idx, 9));	
		input.cost					= fieldOption(xls.getString(idx, 10));
		
		//pre
		input.testcaseId 			= xls.getString(idx, 3);
		input.username 				= xls.getString(idx, 13);
		input.cif 					= getList(xls.getString(idx, 14));
		input.expense 				= getList(xls.getString(idx, 15));
		//pos
		input.collId				= "";
		
		xls.closeFile();
	}

	public void testScenario(){
		openBrowser(system);
		login(input.username, system);
		new BondScript(ctrl, input).execute();
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
