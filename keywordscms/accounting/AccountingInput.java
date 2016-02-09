package accounting;

import config.Field;

public class AccountingInput {
	//condition
	public Subtype 	subtype;			
	//
	public Field 	informaiton;		//บันทึกข้อมูลสิทธิการเช่า
	public Field 	otherInfo;			//ข้อมูลสนับสนุน ธปท.
	public boolean 	nonCRM;				//Non-CRM
	//pre
	public String 	testcaseId;
	public String 	username;
	public String[] cif;
	public String[] expense;
	public String 	accno;
	//pos
	public String 	collId;
}
