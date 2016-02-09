package machine;

import config.Field;
import config.ValuateMethod;

public class MachineInput {
	//condition
	public boolean 	machineDefinition;	//มีทะเบียนหรือไม่
	public Field 	informaiton;		//ข้อมูลเครื่องจักร
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
