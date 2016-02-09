package equity;

import config.Field;

public class EquityInput {
	//condition
	public Subtype 	subtype;			
	//
	public Field 	informaiton;		//บันทึกข้อมูลสิทธิการเช่า
	public Field 	otherInfo;			//ข้อมูลสนับสนุน ธปท.
	public boolean 	nonCRM;				//Non-CRM
	public Field 	cost;				//บันทึกค่ามูลค่าหลักปะรกัน
	//pre
	public String 	testcaseId;
	public String 	username;
	public String[] cif;
	public String[] expense;
	//pos
	public String 	collId;
}
