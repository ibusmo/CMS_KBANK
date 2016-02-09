package rentright;

import config.Field;
import config.ValuateMethod;

public class RentRightInput {
	//condition
	public Subtype 	subtype;	//สิทธิการเช่าสิ่งปลูกสร้าง	สิทธิเหนือที่ดินและสิทธิเก็บกิน
	public boolean 	contract;			//เลือกทำสัญญา	เลือกไม่ได้ทำสัญญา
	//
	public Field 	informaiton;		//บันทึกข้อมูลสิทธิการเช่า
	public Field 	otherInfo;			//ข้อมูลสนับสนุน ธปท.
	public boolean 	nonCRM;				//Non-CRM
	public ValuateMethod valMethod;		//วิธีการประเมินราคา
	public Field 	cost;				//บันทึกค่ามูลค่าหลักปะรกัน
	//pre
	public String 	testcaseId;
	public String 	username;
	public String[] cif;
	public String[] expense;
	//pos
	public String 	collId;
}
