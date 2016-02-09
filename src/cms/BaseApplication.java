package cms;

import com.Login;
import com.Logout;
import com.OpenBrowser;
import com.SystemBase;

import config.Field;
import config.ValuateMethod;
import controller.Controller;
import pools.XLS;
import pools.XLS.col;
import variable.PathVariable;

public class BaseApplication {

	protected Controller ctrl;
	protected SystemBase system;
	protected PathVariable pathVariable;
	protected boolean runableFlag;
	protected String dir;
	protected String sheet;
	
	protected boolean openBrowser(SystemBase system) {
		if (runableFlag)
			runableFlag = new OpenBrowser(ctrl, system).execute();
		return runableFlag;
	}
	
	protected boolean login(String username, SystemBase system) {
		if (runableFlag)
			runableFlag = new Login(ctrl, username, "testuser", system).execute();
		return runableFlag;
	}
	
	protected boolean logout() {
		if (runableFlag)
			runableFlag = new Logout(ctrl).execute();
		return runableFlag;
	}
	
	protected int getSize(col column, int row){
		XLS xls = new XLS(pathVariable.getRelativeExcelPath(), sheet);
		xls.openFile();
		int size = (int) Double.parseDouble(xls.getString(column, row));
		xls.closeFile();
		return size;
	}
	
	protected ValuateMethod valMethod(String v1, String v2, String v3) {
		ValuateMethod valMethod = null;
		if(v1.contains("เลือกใช่") && v2.contains("เลือกใช่") && v3.contains("เลือกใช่")){
			valMethod			= ValuateMethod.InternalSecondary_YYY;			
		}
		else if(v1.contains("เลือกใช่") && v2.contains("เลือกใช่") && v3.contains("เลือกไม่ใช่")){
			valMethod			= ValuateMethod.InternalPrimary_YYN;			
		}
		else if(v1.contains("เลือกใช่") && v2.contains("เลือกไม่ใช่")){
			valMethod			= ValuateMethod.InternalAll_YNO;			
		}
		else if(v1.contains("เลือกไม่ใช่")){
			valMethod			= ValuateMethod.External_NOO;			
		}
		return valMethod;
	}
	
	protected Field fieldOption(String str){
		return str.toLowerCase().contains("require") ? Field.Require : Field.All;
	}
	
	protected boolean nonRCM(String str) {
		return str.contains("ไม่") ? false : true;
	}
	
	protected String[] getList(String str) {
		return str.split(",", -1);
	}

	
}
