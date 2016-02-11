package landbuilding;

import com.SystemBase;

import cms.BaseApplication;
import controller.Controller;
import customcomponent.InterruptTask;
import pools.XLS;
import pools.XLS.col;
import variable.PathVariable;

public class LandBuilding extends BaseApplication {
	
	InterruptTask interrupt = new InterruptTask();
	
	LandBuildingInput input;
	
	public LandBuilding(PathVariable pathVariable) {
		this.pathVariable = pathVariable;
		super.system = SystemBase.CMS_KBANK;
		super.sheet = "landbuilding";
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
		input = new LandBuildingInput();
		
		//condition
		String subtypeStr				= xls.getString(idx, 6);
		if(subtypeStr.contains("โฉนด")){
			input.subtype = Subtype.Chanode;	
		}
		else if(subtypeStr.contains("ตราจอง")){
			input.subtype = Subtype.Trachong;
		}
		else if(subtypeStr.contains("นส.3ก")){
			input.subtype = Subtype.NS3A;
		}
		else if(subtypeStr.contains("นส.3ข")){
			input.subtype = Subtype.NS3B;
		}
		else if(subtypeStr.contains("นส.3")){
			input.subtype = Subtype.NS3;
		}
		else if(subtypeStr.contains("สค.1")){
			input.subtype = Subtype.SC1;
		}
		else if(subtypeStr.contains("ภบท.5")){
			input.subtype = Subtype.PBT5;
		}

		//DO NOT MOD
		input.landInfo					= fieldOption(xls.getString(idx, 7));
		String buildingInfoStr			= xls.getString(idx, 8);
		if(buildingInfoStr.contains("ค้นหา")){
			input.buildingInfo  		= BdField.Search;
		}
		else if(buildingInfoStr.contains("Require")){
			input.buildingInfo  		= BdField.Require;
		}
		else{
			input.buildingInfo  		= BdField.All;
		}

		input.buildingNotYet = 0;
		input.buildingComplete = 0;
		String buildingStatusStr		= xls.getString(idx, 9);
		if(buildingStatusStr.contains("1")){
			if(buildingStatusStr.contains("สร้างไม่เสร็จ")){
				input.buildingNotYet = 1;
			}
			else if(buildingStatusStr.contains("สร้างเสร็จแล้ว")){
				input.buildingComplete = 1;
			}
		}
		else if(buildingStatusStr.contains("2")){
			if(buildingStatusStr.contains("ทั้งหมดยังสร้างไม่เสร็จ")){
				input.buildingNotYet = 2;
			}
			else if(buildingStatusStr.contains("ทั้งหมดสร้างเสร็จแล้ว")){
				input.buildingComplete = 2;
			}
			else if(buildingStatusStr.contains("สร้างเสร็จแล้วและยังสร้างไม่เสร็จ")){
				input.buildingNotYet = 1;
				input.buildingComplete = 1;
			}			
		}

		input.otherInfo					= fieldOption(xls.getString(idx, 10));
		input.nonCRM					= nonRCM(xls.getString(idx, 11));		
		input.valMethod					= valMethod(xls.getString(idx, 12), 
													xls.getString(idx, 13), 
													xls.getString(idx, 14));
		input.cost						= fieldOption(xls.getString(idx, 15));
		String partialStr 				= xls.getString(idx, 17);
		if(partialStr.contains("เลือกไม่ใช่")){
			input.partial = Partial.No;
		}
		else if(partialStr.contains("เลือกใช่แบบบรรยายสัดส่วนของผู้ถือกรรมสิทธิ์ และระบุตำแหน่งที่ตั้ง")){
			input.partial = Partial.WithOwnerWithPlace;			
		}
		else if(partialStr.contains("เลือกใช่แบบบรรยายสัดส่วนของผู้ถือกรรมสิทธิ์ และไม่ระบุตำแหน่งที่ตั้ง")){
			input.partial = Partial.WithOwnerNoPlace;			
		}
		else if(partialStr.contains("เลือกใช่แบบไม่บรรยายสัดส่วนของผู้ถือกรรมสิทธิ์ และไม่ระบุตำแหน่งที่ตั้ง")){
			input.partial = Partial.NoOwnerNoPlace;						
		}
		
		//pre
		input.testcaseId 				= xls.getString(idx, 3);
		input.username 					= xls.getString(idx, 19);
		input.cif 						= getList(xls.getString(idx, 20));
		input.expense 					= getList(xls.getString(idx, 21));
		//pos
		input.collId					= "";
		
		xls.closeFile();
	}
	
	public void testScenario(){
		openBrowser(system);
		login(input.username, system);
		new LandBuildingScript(ctrl, input).execute();
		logout();
		ctrl.disconnect();
	}	
	
	public void run(){
		int start = 3;
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
