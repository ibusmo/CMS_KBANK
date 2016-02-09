package machine;
import base.KeywordsCMS;
import config.Field;
import controller.Controller;
import testdata.CellTag.fieldType;

public class MachineScript extends KeywordsCMS {

	MachineInput mInput;

	public MachineScript(Controller ctrl, MachineInput input) {
		super.ctrl = ctrl;
		super.logoperation 		= log.LogTag.logoperation.Machine;
		
		this.mInput = input; 
	}

	@Override
	public boolean executeTask() {
		
		super.logtab 			= log.LogTag.logtab.Collateral;
		create(6, 1);
		general();
		
		information();										//
		
		otherInfo(mInput.otherInfo, mInput.nonCRM, 1, 1, 0, 0);			
		
		valuateMethod(mInput.valMethod);
		
		cost();												//
		
		expense(mInput.expense);
		
		super.logtab 			= log.LogTag.logtab.Owner;
		owner(mInput.cif);
		
		super.logtab 			= log.LogTag.logtab.SaveOK;
		mInput.collId = complete();
		
		return super.executeTask();
	}
	
	private void information() {
		super.logsubtab 			= log.LogTag.logsubtab.Information;
		
		//Machine Information
		click(fieldType.linktext, "ข้อมูลเครื่องจักร");
		
		if(mInput.machineDefinition){
			/*
			 * เลขทะเบียนเครื่องจักร *	มี
			 * ประเภทสัญญา *		จำนอง
			 * เลขทะเบียนเครื่องจักร (เริ่มต้น) *
			 * เลขทะเบียนเครื่องจักร (สิ้นสุด)
			 */
			radio(fieldType.name, "machineIsDefMcode", "2");
			radio(fieldType.name, "machineContractType", "1");
			type(fieldType.name, "machineMcode1", getNum(10));
			type(fieldType.name, "machineMcode2", getNum(10));
		}else{
			/*
			 * เลขทะเบียนเครื่องจักร *	ไม่มี
			 * ประเภทสัญญา *		รอจำนอง
			 * รอจำนอง			รอจำนอง
			 * จำนองภายในวันที่ *
			 */
			radio(fieldType.name, "machineIsDefMcode", "2");
			radio(fieldType.name, "machineContractType", "2");
			radio(fieldType.name, "machineIsNeedDef", "1");
			date(fieldType.name, "machineRegisDt", "today");	
		}
		/*
		 * เลขทะเบียนเครื่องจักร *	ไม่มี
		 * ประเภทสัญญา *		รอจำนอง
		 * รอจำนอง			ไม่ต้องรอจำนอง
		 */
		//		radio(fieldType.name, "machineIsDefMcode", "2");
		//		radio(fieldType.name, "machineContractType", "2");
		//		radio(fieldType.name, "machineIsNeedDef", "2");
		
		//Require Field
		if(mInput.informaiton.equals(Field.All) || mInput.informaiton.equals(Field.Require)){
			dropdownXpath("//*[@id=\"content\"]/div/form/div/table/tbody/tr[7]/td[4]/input[1]", 2);
			dropdownXpath("//*[@id=\"content\"]/div/form/div/table/tbody/tr[8]/td[2]/input[1]", 2);
			dropdownXpath("//*[@id=\"content\"]/div/form/div/table/tbody/tr[8]/td[4]/input[1]", 2);
		}

		//All Field
		if(mInput.informaiton.equals(Field.All)){
			//Default Information
			click(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table/tbody/tr[4]/td[2]/img");
			popup(fieldType.name, "searchWord", 2);
			//		radio(fieldType.name, "searchType", "1");
			click(fieldType.xpath, "/html/body/form/table[1]/tbody/tr[1]/td[2]/input[2]");
			//		click(fieldType.xpath, "/html/body/form/table[1]/tbody/tr[1]/td[2]/input[3]");
			type(fieldType.name, "searchWord", "101");
			click(fieldType.xpath, "/html/body/form/table[1]/tbody/tr[3]/td/input[1]");
			click(fieldType.linktext, "101");
			popup(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table/tbody/tr[4]/td[2]/img", 1);
			dropdownXpath("//*[@id=\"content\"]/div/form/div/table/tbody/tr[4]/td[4]/input", 2);
			type(fieldType.name, "machineEngineCap", "10");
			dropdownXpath("//*[@id=\"content\"]/div/form/div/table/tbody/tr[5]/td[4]/input", 2);
			type(fieldType.name, "machineFactory", "BUSMO ENTPRZ");

			type(fieldType.name, "road", "ฝาจีบ");
			type(fieldType.name, "soi", "8");
			type(fieldType.name, "model", "CJ-7");
			type(fieldType.name, "brand", "BUSMO TRAIN");
			type(fieldType.name, "madeYear", "1999");
			dropdownXpath("//*[@id=\"content\"]/div/form/div/table/tbody/tr[11]/td[4]/input", 2);
			type(fieldType.name, "machineUsedStartYear", "2001");
			type(fieldType.name, "collDoclct", "REV3");
			type(fieldType.name, "collRemarks", "-");
		}
		
		save();
		takeExecuteCapture(super.logsubtab.toString());		
	}
	
	private void cost() {
		super.logsubtab 			= log.LogTag.logsubtab.Cost;
		//มูลค่าหลักประกัน
		click(fieldType.linktext, "มูลค่าหลักประกัน");

		//Require Field
		if(mInput.cost.equals(Field.All) || mInput.cost.equals(Field.Require)){
			type(fieldType.name, "firstApprVal", "1000000");
			date(fieldType.name, "firstApprDt", "today");
			
			date(fieldType.name, "approveDt", "today");
	
			type(fieldType.name, "appraisalVal", "1000000");
			type(fieldType.name, "machinePurchasePrice", "1000000");
			
			type(fieldType.name, "rulingCost", "300000");

			dropdownXpath("//*[@id=\"content\"]/div/form/div/table/tbody/tr[11]/td[2]/input", 2);
		}

		//All Field
		if(mInput.cost.equals(Field.All)){
			date(fieldType.name, "firstCreateApprDt", "today");
			date(fieldType.name, "appraisalDt", "today");

			type(fieldType.name, "machineReplaceCost", "500000");
		
			checkbox(fieldType.name, "isWantedMarket");
			checkbox(fieldType.name, "isFoundWhenAppr");
	
			type(fieldType.name, "usefulYear", "1");
			type(fieldType.name, "usefulMonth", "7");
			type(fieldType.name, "rmYear", "10");
			type(fieldType.name, "rmMonth", "5");
			type(fieldType.name, "depreciatePercent", "3");
			type(fieldType.name, "yearlyDepreciateVal", "100");
		}
			
		save();
		takeExecuteCapture(super.logsubtab.toString());		
	}
	
}
