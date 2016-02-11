package landbuilding;

import config.Field;
import config.ValuateMethod;

public class LandBuildingInput {
	//condition
	public Subtype 	subtype;
	public Field 	landInfo;
	public BdField 	buildingInfo;
	public int 		buildingComplete;
	public int 		buildingNotYet;
	public Field 	otherInfo;
	public boolean 	nonCRM;
	public ValuateMethod valMethod;
	public Field 	cost;
	public Partial 	partial;
	//pre
	public String 	testcaseId;
	public String 	username;
	public String[] cif;
	public String[] expense;
	//pos
	public String 	collId;
}
