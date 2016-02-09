package equity;

import org.apache.commons.lang3.ArrayUtils;

import base.KeywordsCMS;
import config.Field;
import controller.Controller;
import testdata.CellTag.fieldType;

public class EquityScript extends KeywordsCMS {

	EquityInput input;

	public EquityScript(Controller ctrl, EquityInput input) {
		super.ctrl = ctrl;
		super.logoperation 		= log.LogTag.logoperation.Equity;
		
		this.input = input; 
	}

	@Override
	public boolean executeTask() {
		
		super.logtab 			= log.LogTag.logtab.Collateral;
		
		switch(input.subtype){
		case CommonStock:
			create(10, 1);
			break;
		case ComStockBook:
			create(10, 2);
			break;
		case DividendBook:
			create(10, 3);
			break;
		case DebentureBook:
			create(10, 4);
			break;
		case PreferredStock:
			create(10, 5);
			break;
		default:
			break;
		}			
		general();	
		infoCommonStock();
		otherInfo(input.otherInfo, input.nonCRM, 1, 1, 0, 0);
		cost();
		expense(input.expense);

		super.logtab 			= log.LogTag.logtab.Owner;
		owner(input.cif);
		
		super.logtab 			= log.LogTag.logtab.SaveOK;
		input.collId = complete();
		
		return super.executeTask();
	}

	private void infoCommonStock() {
		super.logsubtab 			= log.LogTag.logsubtab.Information;
		
		//Machine Information
		click(fieldType.linktext, "ข้อมูลตราสารทุน");

		//Require Field
		if(input.informaiton.equals(Field.All) || input.informaiton.equals(Field.Require)){
			click(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table[1]/tbody[1]/tr[1]/td[2]/img");
			popup(fieldType.name, "searchWord", 2);
			click(fieldType.value, "code");
			type(fieldType.name, "searchWord", "AAV");
			click(fieldType.xpath, "/html/body/form/table[1]/tbody/tr[3]/td/input[2]");
			click(fieldType.linktext, "AAV");
			popup(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table[1]/tbody[1]/tr[1]/td[2]/img", 1);

			type(fieldType.name, "stockCertno", getNum(10));
			type(fieldType.name, "numUnit", getNum(3));

			click(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table[1]/tbody[2]/tr[3]/td[2]/img");
			popup(fieldType.name, "searchType", 2);
			ctrl.dropdown.id("searchType", "C", "controlKey(this.value)");
			type(fieldType.name, "cifNo", input.cif[0]);
			jsExe("document.forms[0].btnSearch.value =  'search';");
			jsExe("document.forms[0].submit();");
			click(fieldType.linktext, input.cif[0]);
			popup(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table[1]/tbody[2]/tr[3]/td[2]/img", 1);
			
			input.cif = ArrayUtils.removeElement(input.cif, input.cif[0]);
		}

		//All Field
		if(input.informaiton.equals(Field.All)){
			type(fieldType.name, "stockBeneficiary", "นาย สัญญา ไม่จริง");
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
			type(fieldType.name, "firstApprVal", ctrl.verifyData.getValueById("appraisalVal"));
			date(fieldType.name, "firstApprDt", ctrl.verifyData.getTextById("lb_approveDt"));

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
	
}
