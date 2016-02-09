package bond;

import org.apache.commons.lang3.ArrayUtils;

import base.KeywordsCMS;
import config.Field;
import controller.Controller;
import testdata.CellTag.fieldType;

public class BondScript extends KeywordsCMS {

	BondInput input;

	public BondScript(Controller ctrl, BondInput input) {
		super.ctrl = ctrl;
		super.logoperation 		= log.LogTag.logoperation.Bond;
		
		this.input = input; 
	}

	@Override
	public boolean executeTask() {
		
		super.logtab 			= log.LogTag.logtab.Collateral;
		
		switch(input.subtype){
		case Bond:
			create(11, 1);
			general();	
			infoBond("AAV");
			otherInfo(input.otherInfo, input.nonCRM, 1, 1, 0, 0);
			cost();
			break;
		case ConvertibleBond:
			create(11, 2);
			general();	
			infoBond("BAY13NA");
			otherInfo(input.otherInfo, input.nonCRM, 1, 1, 0, 0);
			cost();
			break;
		case Debenture:
			create(11, 3);
			general();	
			infoBond("BAY13NA");
			otherInfo(input.otherInfo, input.nonCRM, 1, 1, 0, 0);
			cost();
			break;
		case Financial:
			create(11, 4);
			general();	
			infoFinancial();
			otherInfo(input.otherInfo, input.nonCRM, 1, 1, 0, 0);
			costFinancial();
			break;
		default:
			break;
		}			
		expense(input.expense);

		super.logtab 			= log.LogTag.logtab.Owner;
		owner(input.cif);
		
		super.logtab 			= log.LogTag.logtab.SaveOK;
		input.collId = complete();
		
		return super.executeTask();
	}

	private void infoBond(String bond) {
		super.logsubtab 			= log.LogTag.logsubtab.Information;
		
		//Machine Information
		click(fieldType.linktext, "ข้อมูลตราสารหนี้");

		//Require Field
		if(input.informaiton.equals(Field.All) || input.informaiton.equals(Field.Require)){
			click(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table[1]/tbody/tr[1]/td[2]/img");
			popup(fieldType.name, "searchWord", 2);
			click(fieldType.value, "code");
			type(fieldType.name, "searchWord", bond);
			click(fieldType.xpath, "/html/body/form/table[1]/tbody/tr[3]/td/input[2]");
			click(fieldType.linktext, bond);
			popup(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table[1]/tbody/tr[1]/td[2]/img", 1);
			
			String startNum = getNum(7);
			type(fieldType.name, "startNumber", startNum);
			type(fieldType.name, "endNumber", "" + (Integer.parseInt(startNum) + 100));
			type(fieldType.name, "numUnit",  "100");

			click(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table[1]/tbody/tr[10]/td[2]/img");
			popup(fieldType.name, "searchType", 2);
			ctrl.dropdown.id("searchType", "C", "controlKey(this.value)");
			type(fieldType.name, "cifNo", input.cif[0]);
//			click(fieldType.xpath, "/html/body/form/table[1]/tbody/tr[7]/td/input[2]");
			jsExe("document.forms[0].btnSearch.value =  'search';");
			jsExe("document.forms[0].submit();");
			click(fieldType.linktext, input.cif[0]);
			popup(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table[1]/tbody/tr[10]/td[2]/img", 1);
			
			input.cif = ArrayUtils.removeElement(input.cif, input.cif[0]);
		}

		//All Field
		if(input.informaiton.equals(Field.All)){
			type(fieldType.name, "collDoclct", "OSE LAB");
			type(fieldType.name, "collRemarks", "สำนักงานกระทรวงเวทย์มนต์");
		}
		
		saveDraft();
		takeExecuteCapture(super.logsubtab.toString());		
	}
	
	private void infoFinancial() {
		super.logsubtab 			= log.LogTag.logsubtab.Information;
		
		//Machine Information
		click(fieldType.linktext, "ข้อมูลตั๋วเงินคลัง");

		//Require Field
		if(input.informaiton.equals(Field.All) || input.informaiton.equals(Field.Require)){
			type(fieldType.name, "billNo", getNum(7));
			date(fieldType.name, "issueDt", "yesterday");
		}

		//All Field
		if(input.informaiton.equals(Field.All)){
			date(fieldType.name, "receiveDt", "tomorrow");
			type(fieldType.name, "description", "OSE LAB");
			type(fieldType.name, "collDoclct", "OSE LAB");
			type(fieldType.name, "collRemarks", "สำนักงานกระทรวงเวทย์มนต์");
		}
		
		saveDraft();
		takeExecuteCapture(super.logsubtab.toString());		
	}
	
	private void cost() {
		super.logsubtab 			= log.LogTag.logsubtab.Cost;
		//มูลค่าหลักประกัน
		click(fieldType.linktext, "มูลค่าหลักประกัน");

		//Require Field
		if(input.cost.equals(Field.All) || input.cost.equals(Field.Require)){
			type(fieldType.name, "firstApprVal", getValueById("appraisalVal"));
			date(fieldType.name, "firstApprDt", getTextById("lb_approveDt"));

			dropdownSelectId("appraisalType", 2);
		}

		//All Field
		if(input.cost.equals(Field.All)){
			date(fieldType.name, "firstCreateApprDt", "today");
			date(fieldType.name, "appraisalDt", "today");
		}
			
		saveDraft();
		takeExecuteCapture(super.logsubtab.toString());		
	}	
	private void costFinancial() {
		super.logsubtab 			= log.LogTag.logsubtab.Cost;
		//มูลค่าหลักประกัน
		click(fieldType.linktext, "มูลค่าหลักประกัน");

		//Require Field
		if(input.cost.equals(Field.All) || input.cost.equals(Field.Require)){
			type(fieldType.name, "firstApprVal", getNum(7));
			date(fieldType.name, "firstApprDt", "yesterday");
			date(fieldType.name, "approveDt", "today");
			type(fieldType.name, "appraisalVal", getNum(7));

			dropdownSelectId("appraisalType", 2);
		}

		//All Field
		if(input.cost.equals(Field.All)){
			date(fieldType.name, "firstCreateApprDt", "today");
			date(fieldType.name, "appraisalDt", "today");
			date(fieldType.name, "nextAppraisalDt", "tomorrow");
		}
			
		saveDraft();
		takeExecuteCapture(super.logsubtab.toString());		
	}
	
}
