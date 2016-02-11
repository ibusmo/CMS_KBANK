import accounting.Accounting;
import bond.Bond;
import equity.Equity;
import landbuilding.LandBuilding;
import machine.Machine;
import rentright.RentRigth;
import updater.SyncRepo;
import variable.PathVariable;

public class Main {

	public static void main(String[] args) {
		new SyncRepo().sync();
//		new Machine(getAssignedPath()).run();
//		new RentRigth(getAssignedPath()).run();
//		new Accounting(getAssignedPath()).run();
//		new Equity(getAssignedPath()).run();
//		new Bond(getAssignedPath()).run();
//		new LandBuilding(getAssignedPath()).run();
		return ;		
	}
	
	private static PathVariable getAssignedPath() {
		
		PathVariable pathVariable = new PathVariable();
		pathVariable.setCMSKBANKBaseURL("https://172.31.5.42:9443/CMS/");
		pathVariable.setOffsetPath("C:\\testdata\\CMS_KBANK");
		pathVariable.setExcelName("coll");
		pathVariable.setExcelType(".xls");
		
		return pathVariable;
	}
	
}
