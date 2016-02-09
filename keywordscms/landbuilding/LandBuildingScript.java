package landbuilding;
import base.KeywordsCMS;
import config.Field;
import controller.Controller;
import testdata.CellTag.fieldType;

public class LandBuildingScript extends KeywordsCMS {

	LandBuildingInput input;

	public LandBuildingScript(Controller ctrl, LandBuildingInput input) {
		super.ctrl = ctrl;
		super.logoperation 		= log.LogTag.logoperation.LandBuilding;
		
		this.input = input; 
	}

	@Override
	public boolean executeTask() {
		
		super.logtab 			= log.LogTag.logtab.Collateral;
		create(3, 1);
		general();
		
//		information();
		buildingInfo();
		
//		otherInfo(input.otherInfo, input.nonCRM, 1, 1, 2, 2);			
//		
//		valuateMethod(input.valMethod);
//		
//		cost();												
//		
//		expense(input.expense);
//		
//		super.logtab 			= log.LogTag.logtab.Owner;
//		owner(input.cif);
//		
//		super.logtab 			= log.LogTag.logtab.SaveOK;
//		input.collId = complete();
//		
		return super.executeTask();
	}

	private void buildingInfo() {
		super.logsubtab 			= log.LogTag.logsubtab.BuildingInfo;
		//Building Information
		click(fieldType.linktext, "ข้อมูลสิ่งปลูกสร้าง");
		
		if(input.buildingInfo.equals(BdField.Search)){
			
		}else{	//All, Require
			//Enter Menu
			click(fieldType.value, "เพิ่มสิ่งปลูกสร้าง");
			click(fieldType.value, "เพิ่มสิ่งปลูกสร้าง");
			
			//Part 1
			boolean isComplete = true;
			if(isComplete){
				System.out.println("COMPLETE");
				//Require Field
				if(input.buildingInfo.equals(BdField.All) || input.buildingInfo.equals(BdField.Require)){
					type(fieldType.name, "buildingNo", getNum(10));
					dropdownSelectId("bldCategoryCd", 2, 1);
					radio(fieldType.name, "bldLandCondition", "1");
					type(fieldType.name, "bldBuildingId", getNum(3));
					type(fieldType.name, "noOfBuilding", getNum(1));
					type(fieldType.name, "realBldArea", getNum(2));
					type(fieldType.name, "bldFloor", getNum(1));
					type(fieldType.name, "bldVal", getNum(7));
				}	
				//All Field
				if(input.buildingInfo.equals(BdField.All)){
					type(fieldType.name, "projectFinanceName", "Build N - " + getNum(3));
					type(fieldType.name, "bldDecorCondition", "Gothic");
				}
			}else{
				System.out.println("NOT COMPLETE");
				//Require Field
				if(input.buildingInfo.equals(BdField.All) || input.buildingInfo.equals(BdField.Require)){
					dropdownSelectId("bldCategoryCd", 2, 1);
					radio(fieldType.name, "bldLandCondition", "2");
					type(fieldType.name, "realBldArea", getNum(2));
					type(fieldType.name, "bldFloor", getNum(1));
					type(fieldType.name, "bldVal", getNum(7));
				}	
				//All Field
				if(input.buildingInfo.equals(BdField.All)){
					type(fieldType.name, "buildingNo", getNum(10));
					type(fieldType.name, "projectFinanceName", "Build N - " + getNum(3));
					type(fieldType.name, "bldPercentBuilding", getNum(2));
					type(fieldType.name, "bldBuildingId", getNum(3));
					type(fieldType.name, "noOfBuilding", getNum(1));
					type(fieldType.name, "bldDecorCondition", "Gothic");
				}
				
			}
			//Part 2
			//Require Field
			if(input.buildingInfo.equals(BdField.All) || input.buildingInfo.equals(BdField.Require)){
				dropdownSelectId("bldProvinceCd", 7, 1);
				dropdownSelectId("bldAmphurCd", 2, 1);
				dropdownSelectId("bldTambolCd", 2, 1);				
			}
			//All Field
			if(input.buildingInfo.equals(BdField.All)){
				type(fieldType.name, "road", "สวนมะม่วง");
				type(fieldType.name, "soi", "สวนมะพร้าว");
				type(fieldType.name, "bldGrpNo", getNum(1));
				
				click(fieldType.xpath, "//*[@id=\"tbl_buildingInfo\"]/tbody/tr[6]/td[2]/img");
				popup(fieldType.name, "searchWord", 2);
				radio(fieldType.name, "searchType", "1");
				type(fieldType.name, "searchWord", "HA12-0062");
				click(fieldType.value, "ค้นหา");
				click(fieldType.linktext, "HA12-0062");
				popup(fieldType.xpath, "//*[@id=\"tbl_buildingInfo\"]/tbody/tr[6]/td[2]/img", 1);
			}
			//Part 3
			//Require Field
			if(input.buildingInfo.equals(BdField.All) || input.buildingInfo.equals(BdField.Require)){
				dropdownSelectId("bldLandOwnershipCd", 7, 1);
				dropdownSelectId("bldWall", 2, 1);
				dropdownSelectId("roof", 2, 1);				
			}
			//All Field
			if(input.buildingInfo.equals(BdField.All)){
				dropdownSelectId("bldDevDummyCd", 7, 1);
				dropdownSelectId("isDecoratedCd", 2, 1);
				dropdownSelectId("floor", 2, 1);
				dropdownSelectId("roofStructure", 2, 1);				
				
				type(fieldType.name, "bldAgeYear", getNum(1));
				type(fieldType.name, "bldAgeMonth", getNum(1));
				type(fieldType.name, "bldStructure", "โครงไม้ไผ่");
				type(fieldType.name, "otherBldLeft", getNum(1));
				type(fieldType.name, "otherBldRight", getNum(1));
				type(fieldType.name, "otherBldFront", getNum(1));
				type(fieldType.name, "otherBldBack", getNum(1));
				
				click(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table[3]/tbody/tr[9]/td[2]/img");
				popup(fieldType.name, "searchWord", 2);
				click(fieldType.xpath, "//*[@id=\"testForm\"]/table[1]/tbody/tr[1]/td[2]/input[2]");
				type(fieldType.name, "searchWord", "1032");
				click(fieldType.value, "ค้นหา");
				click(fieldType.linktext, "1032");
				popup(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table[3]/tbody/tr[9]/td[2]/img", 1);

				type(fieldType.name, "occupancyOther", "Clinic - " + getNum(1));
			}
		}
		
		
		
//		saveDraft();
		takeExecuteCapture(super.logsubtab.toString());		
	}
	
	private void cost() {
		super.logsubtab 			= log.LogTag.logsubtab.Cost;
		//มูลค่าหลักประกัน
		click(fieldType.linktext, "มูลค่าหลักประกัน");

		//Require Field
		if(input.cost.equals(Field.All) || input.cost.equals(Field.Require)){
			type(fieldType.name, "firstApprVal", "1000000");
			date(fieldType.name, "firstApprDt", "today");
			
			date(fieldType.name, "approveDt", "today");
	
			type(fieldType.name, "appraisalVal", "1000000");
			type(fieldType.name, "machinePurchasePrice", "1000000");
			
			type(fieldType.name, "rulingCost", "300000");

			dropdownXpath("//*[@id=\"content\"]/div/form/div/table/tbody/tr[11]/td[2]/input", 2);
		}

		//All Field
		if(input.cost.equals(Field.All)){
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
			
		saveDraft();
		takeExecuteCapture(super.logsubtab.toString());		
	}
	
}
