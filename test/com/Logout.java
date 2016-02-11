package com;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.remote.UnreachableBrowserException;

import base.KeywordsCMS;
import controller.Controller;
import log.LogTag.logaction;
import log.LogTag.logexestatus;

public class Logout extends KeywordsCMS {

	public Logout(Controller ctrl) {
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.Logout;
		super.logtab 			= log.LogTag.logtab.None;
		super.logsubtab 		= log.LogTag.logsubtab.None;
	}
	
	@Override
	public boolean execute() {
		initKeywords();
		sendToLogStart();

		try{
			String btnLogOut = "ออกจากระบบ";
			ctrl.button.linkText(btnLogOut);
			ctrl.alertHandle.execute();
			sendToLogCustom(logexestatus.PASS, logaction.Comfirm, "Yes");
		}catch (TimeoutException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, " -TimeoutException");
			return false;
		}catch (InvalidElementStateException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, " -InvalidElementStateException");
			return false;			
		}catch(UnhandledAlertException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, " -UnhandledAlertException");
			return false;					
		}catch(UnreachableBrowserException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, " -UnreachableBrowserException");
			return false;				
		}catch(NoSuchWindowException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, " -NoSuchWindowException");
			return false;		
		}catch(Exception e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, " -Exception");
			return false;				
		}
		
		sendToLogFinish();
		return true;
	}
	
}
