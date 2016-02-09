package log;

public class LogTag {
	public enum logexestatus{
		INIT,
		TEAR,
		PASS,
		FAIL,
		START,
		FINISH,
		None
	}
	public enum logoperation{
		Config, None, Login, Logout, OpenBrowser, 		
		Machine, RentRigth, Accounting, Equity, Bond, LandBuilding
		
	}
	public enum logtab{
		None, Collateral, Owner, SaveOK
		
	} 
	public enum logsubtab{
		None, Create, Information, General, OtherInfo, ValuateMethod, Cost, Expense, CIF, SaveOK, BuildingInfo
	} 
	public enum logelement{
		None
	}
	public enum logaction{
		Start,
		Stop,
		Fill_login,
		Fill_again,
		Comfirm,
		Click,
		Radio,
		Dropdown,
		Type,
		Save,
		Get,
		Date,
		Popup,
		Check,
		Attach,
		SendWork,
		None, 
		Checkbox, 
		LoadData, 
		DropdownNoText, 
		Draft, 
		OK, 
		DropdownRobot, 
		Wait, 
		Verify, SaveDraft, GotoApp, JSExe, Documents, Capture, SaveOK, searchCIF, addExpense
	}
}
