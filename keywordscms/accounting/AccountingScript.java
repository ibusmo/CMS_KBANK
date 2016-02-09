package accounting;
import base.KeywordsCMS;
import config.Field;
import controller.Controller;
import testdata.CellTag.fieldType;

public class AccountingScript extends KeywordsCMS {

	AccountingInput input;

	public AccountingScript(Controller ctrl, AccountingInput input) {
		super.ctrl = ctrl;
		super.logoperation 		= log.LogTag.logoperation.Accounting;
		
		this.input = input; 
	}

	@Override
	public boolean executeTask() {
		
		super.logtab 			= log.LogTag.logtab.Collateral;
		switch(input.subtype){
			case Acc:
				create(9, 1);
				general();	
				informationACC();
				break;
				
			case Slipt:
				create(9, 2);
				general();	
				informationSlipt();
				break;
				
			case CashOther:
				create(9, 3);
				general();	
				informationCashOther();
				break;
				
			case Bank:
				create(9, 4);
				general();	
				informationBank();
				break;
				
			default:
				break;
		}			
		otherInfo(input.otherInfo, input.nonCRM, 1, 1, 0, 0);			
		expense(input.expense);

		super.logtab 			= log.LogTag.logtab.Owner;
		owner(input.cif);
		
		super.logtab 			= log.LogTag.logtab.SaveOK;
		input.collId = complete();
		
		return super.executeTask();
	}

	private void informationBank() {
		super.logsubtab 			= log.LogTag.logsubtab.Information;
		
		//Machine Information
		click(fieldType.linktext, "ข้อมูลธนบัตร");
		//Require Field
		if(input.informaiton.equals(Field.All) || input.informaiton.equals(Field.Require)){
			type(fieldType.name, "appraisalVal", getNum(7));
		}

		//All Field
		if(input.informaiton.equals(Field.All)){
			date(fieldType.name, "firstCreateApprDt", "today");
			type(fieldType.name, "collDoclct", "OSE LAB");
			type(fieldType.name, "collRemarks", "สำนักงานประกันภัย");
		}
		
		saveDraft();
		takeExecuteCapture(super.logsubtab.toString());		
	}
	
	private void informationCashOther() {
		super.logsubtab 			= log.LogTag.logsubtab.Information;
		
		//Machine Information
		click(fieldType.linktext, "ข้อมูลเงินสด/บัญชีเงินฝากอื่นๆ");
		//Require Field
		if(input.informaiton.equals(Field.All) || input.informaiton.equals(Field.Require)){
			type(fieldType.name, "appraisalVal", getNum(7));
		}

		//All Field
		if(input.informaiton.equals(Field.All)){
			date(fieldType.name, "firstCreateApprDt", "today");
			type(fieldType.name, "otherAcctNo", getNum(10));
			type(fieldType.name, "otherAcctName", "OSE LAB - " + getNum(5));
			type(fieldType.name, "collDoclct", "OSE LAB");
			type(fieldType.name, "collRemarks", "สำนักงานประกันภัย");
		}
		
		saveDraft();
		takeExecuteCapture(super.logsubtab.toString());		
	}
	
	private void informationSlipt() {
		super.logsubtab 			= log.LogTag.logsubtab.Information;
		
		//Machine Information
		click(fieldType.linktext, "ข้อมูลบัตรเงินฝาก");
		//Require Field
		if(input.informaiton.equals(Field.All) || input.informaiton.equals(Field.Require)){
			type(fieldType.name, "depositDoc", getNum(9));
			type(fieldType.name, "appraisalVal", getNum(7));
		}

		//All Field
		if(input.informaiton.equals(Field.All)){
			date(fieldType.name, "firstCreateApprDt", "today");
			type(fieldType.name, "collDoclct", "OSE LAB");
			type(fieldType.name, "collRemarks", "สำนักงานประกันภัย");

			click(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table[1]/tbody/tr[2]/td[4]/img");
			popup(fieldType.name, "searchWord", 2);
			//			radio(fieldType.name, "searchType", "2");
			click(fieldType.value, "code");
			type(fieldType.name, "searchWord", "18");
			click(fieldType.xpath, "/html/body/form/table[1]/tbody/tr[3]/td/input[2]");
			click(fieldType.linktext, "18");
			popup(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table[1]/tbody/tr[2]/td[4]/img", 1);
		}
		
		saveDraft();
		takeExecuteCapture(super.logsubtab.toString());		
	}

	private void informationACC() {
		super.logsubtab 			= log.LogTag.logsubtab.Information;
		
		//Machine Information
		click(fieldType.linktext, "ข้อมูลบัญชีเงินฝาก");

		//Require Field
		if(input.informaiton.equals(Field.All) || input.informaiton.equals(Field.Require)){
			click(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table[1]/tbody/tr[2]/td[2]/img");
			popup(fieldType.name, "cifNo", 2);
			radio(fieldType.name, "searchAcctType", "2");
			type(fieldType.name, "cidNo", input.accno);
			click(fieldType.xpath, "/html/body/form/table[1]/tbody/tr[3]/td/input[2]");
			click(fieldType.linktext, input.accno);
			popup(fieldType.xpath, "//*[@id=\"content\"]/div/form/div/table[1]/tbody/tr[2]/td[2]/img", 1);
		}

		//All Field
		if(input.informaiton.equals(Field.All)){
			date(fieldType.name, "firstCreateApprDt", "today");
			// dropdown("//*[@id=\"reasonCdDiv\"]/input", 22);
			type(fieldType.name, "collDoclct", "OSE LAB");
			type(fieldType.name, "collRemarks", "สำนักงานกระทรวงเวทย์มนต์");
		}
		
		saveDraft();
		takeExecuteCapture(super.logsubtab.toString());		
	}
	
}
