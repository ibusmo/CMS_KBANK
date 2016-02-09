package rentright;
import base.KeywordsCMS;
import config.Field;
import controller.Controller;
import testdata.CellTag.fieldType;

public class RentRigthScript extends KeywordsCMS {

	RentRightInput input;

	public RentRigthScript(Controller ctrl, RentRightInput input) {
		super.ctrl = ctrl;
		super.logoperation 		= log.LogTag.logoperation.RentRigth;
		
		this.input = input; 
	}

	@Override
	public boolean executeTask() {
		
		super.logtab 			= log.LogTag.logtab.Collateral;
		switch(input.subtype){
			case Building:
				create(7, 1);
				general();
				informationBuilding();
				break;
			case LandUsable:
				create(7, 2);
				general();				
				informationLandUsable();
				break;
			default:
				break;
		}
		otherInfo(input.otherInfo, input.nonCRM, 1, 1, 2, 0);			
		valuateMethod(input.valMethod);
		cost();
		expense(input.expense);

		super.logtab 			= log.LogTag.logtab.Owner;
		owner(input.cif);
		
		super.logtab 			= log.LogTag.logtab.SaveOK;
		input.collId = complete();
		
		return super.executeTask();
	}
	
	private void informationBuilding() {
		super.logsubtab 			= log.LogTag.logsubtab.Information;
		
		//Machine Information
		click(fieldType.linktext, "ข้อมูลสิทธิการเช่า");

		//Require Field
		if(input.informaiton.equals(Field.All) || input.informaiton.equals(Field.Require)){
			dropdownXpath("//*[@id=\"content\"]/div/form/div/table[1]/tbody/tr[1]/td[2]/input", 2);
			dropdownXpath("//*[@id=\"content\"]/div/form/div/table[1]/tbody/tr[1]/td[4]/input", 2);
			dropdownXpath("//*[@id=\"content\"]/div/form/div/table[2]/tbody/tr[1]/td[2]/input", 2);
		}

		//All Field
		if(input.informaiton.equals(Field.All)){
			dropdownXpath("//*[@id=\"content\"]/div/form/div/table[1]/tbody/tr[2]/td[2]/input", 2);
			type(fieldType.name, "rightoverLeaseLender", "สามเณร อานันท์");
			type(fieldType.name, "collDoclct", "S.H.E.I.L.D.");
			type(fieldType.name, "collRemarks", "สิทธิ์คุ้มครอง");

			type(fieldType.name, "docBldName", "S.H.E.I.L.D." + getNum(3));
			type(fieldType.name, "bldFloor", getNum(1));
			type(fieldType.name, "docBldNo", getNum(3));
			type(fieldType.name, "landGrpNo", getNum(2));
			dropdownXpath("//*[@id=\"content\"]/div/form/div/table[2]/tbody/tr[4]/td[2]/input[1]", 2);
			dropdownXpath("//*[@id=\"content\"]/div/form/div/table[2]/tbody/tr[5]/td[2]/input[1]", 2);
			dropdownXpath("//*[@id=\"content\"]/div/form/div/table[2]/tbody/tr[5]/td[4]/input[1]", 2);
			type(fieldType.name, "road", "Silom");
			type(fieldType.name, "soi", "Si");			

			click(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table[3]/tbody/tr[1]/td[2]/img");
			popup(fieldType.name, "searchWord", 2);
			type(fieldType.name, "searchWord", "HA12-0062");
			click(fieldType.xpath, "/html/body/form/table[1]/tbody/tr[3]/td/input[1]");
			click(fieldType.linktext, "HA12-0062");			
			popup(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table[3]/tbody/tr[1]/td[2]/img", 1);
		}
		
		if(input.contract){
			//Require Field
			if(input.informaiton.equals(Field.All) || input.informaiton.equals(Field.Require)){
				radio(fieldType.name, "rightoverIsContract", "1");
				type(fieldType.name, "rightoverLeaseRegisNo", "DocNo" + getNum(3));
				date(fieldType.name, "rightoverLeaseStartDt", "yesterday");
				date(fieldType.name, "rightoverLeaseEndDt", "tomorrow");
			}

			//All Field
			if(input.informaiton.equals(Field.All)){
				type(fieldType.name, "rightoverLeaseBorrower", "omsap");
				date(fieldType.name, "rightoverLeaseRegisDt", "today");
				type(fieldType.name, "rightoverLeaseCondition", "uptou");
				type(fieldType.name, "rightoverLeaseYear", getNum(2));
				type(fieldType.name, "rightoverLeaseMonth", getNum(1));
				radio(fieldType.name, "rightoverIsContinueLease", "1");
			}
		}else{
			//Require Field
			if(input.informaiton.equals(Field.All) || input.informaiton.equals(Field.Require)){
				radio(fieldType.name, "rightoverIsContract", "2");
			}
			
			//All Field
			if(input.informaiton.equals(Field.All)){
				
			}
		}
		
		save();
		takeExecuteCapture(super.logsubtab.toString());		
	}
	
	private void informationLandUsable() {
		super.logsubtab 			= log.LogTag.logsubtab.Information;
		
		//Machine Information
		click(fieldType.linktext, "ข้อมูลสิทธิการเช่า");

		//Require Field
		if(input.informaiton.equals(Field.All) || input.informaiton.equals(Field.Require)){
			dropdownXpath("//*[@id=\"content\"]/div/form/div/table[1]/tbody/tr[1]/td[2]/input", 2);
			dropdownXpath("//*[@id=\"content\"]/div/form/div/table[1]/tbody/tr[1]/td[4]/input", 2);
			dropdownXpath("//*[@id=\"content\"]/div/form/div/table[2]/tbody/tr[1]/td[2]/input", 2);
		}

		//All Field
		if(input.informaiton.equals(Field.All)){
			dropdownXpath("//*[@id=\"content\"]/div/form/div/table[1]/tbody/tr[2]/td[2]/input", 2);
			type(fieldType.name, "rightoverLeaseLender", "สามเณร อานันท์");
			type(fieldType.name, "collDoclct", "S.H.E.I.L.D.");
			type(fieldType.name, "collRemarks", "สิทธิ์คุ้มครอง");

			type(fieldType.name, "docBldName", "S.H.E.I.L.D." + getNum(3));
			type(fieldType.name, "bldFloor", getNum(1));
			type(fieldType.name, "docBldNo", getNum(3));
			type(fieldType.name, "landGrpNo", getNum(2));
			dropdownXpath("//*[@id=\"content\"]/div/form/div/table[2]/tbody/tr[4]/td[2]/input[1]", 2);
			dropdownXpath("//*[@id=\"content\"]/div/form/div/table[2]/tbody/tr[5]/td[2]/input[1]", 2);
			dropdownXpath("//*[@id=\"content\"]/div/form/div/table[2]/tbody/tr[5]/td[4]/input[1]", 2);
			type(fieldType.name, "road", "Silom");
			type(fieldType.name, "soi", "Si");			

			click(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table[3]/tbody/tr[1]/td[2]/img");
			popup(fieldType.name, "searchWord", 2);
			type(fieldType.name, "searchWord", "HA12-0062");
			click(fieldType.xpath, "/html/body/form/table[1]/tbody/tr[3]/td/input[1]");
			click(fieldType.linktext, "HA12-0062");			
			popup(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table[3]/tbody/tr[1]/td[2]/img", 1);
		}
		
		if(input.contract){
			//Require Field
			if(input.informaiton.equals(Field.All) || input.informaiton.equals(Field.Require)){
				radio(fieldType.name, "rightoverIsContract", "1");
				type(fieldType.name, "rightoverLeaseRegisNo", "DocNo" + getNum(3));
				date(fieldType.name, "rightoverLeaseStartDt", "yesterday");
				date(fieldType.name, "rightoverLeaseEndDt", "tomorrow");
			}

			//All Field
			if(input.informaiton.equals(Field.All)){
				type(fieldType.name, "rightoverLeaseBorrower", "omsap");
				date(fieldType.name, "rightoverLeaseRegisDt", "today");
				type(fieldType.name, "rightoverLeaseCondition", "uptou");
				type(fieldType.name, "rightoverLeaseYear", getNum(2));
				type(fieldType.name, "rightoverLeaseMonth", getNum(1));
				radio(fieldType.name, "rightoverIsContinueLease", "1");
			}
		}else{
			//Require Field
			if(input.informaiton.equals(Field.All) || input.informaiton.equals(Field.Require)){
				radio(fieldType.name, "rightoverIsContract", "2");
			}
			
			//All Field
			if(input.informaiton.equals(Field.All)){
				
			}
		}
		
		save();
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
			
			dropdownXpath("//*[@id=\"content\"]/div/form/div/table/tbody/tr[8]/td[2]/input", 2);
		}

		//All Field
		if(input.cost.equals(Field.All)){
			date(fieldType.name, "firstCreateApprDt", "today");
			date(fieldType.name, "appraisalDt", "today");

			type(fieldType.name, "marketPrice", "500000");
			type(fieldType.name, "capitalPrice", "500000");
		}
			
		save();
		takeExecuteCapture(super.logsubtab.toString());		
	}
	
}
