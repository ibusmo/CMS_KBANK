package pools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class XLS {
	
	String WorkBookPath;
	String WorkSheetPath;
	
	FileInputStream fileInputStream;
	HSSFWorkbook workbook;
	HSSFSheet worksheet;
	
	public XLS(String WorkBookPath, String WorkSheetPath){
		this.WorkBookPath = WorkBookPath;
		this.WorkSheetPath = WorkSheetPath;
	}

	public boolean openFile() {
		try {
			fileInputStream = new FileInputStream(WorkBookPath);
			workbook = new HSSFWorkbook(fileInputStream);
			worksheet = workbook.getSheet(WorkSheetPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean closeFile() {
		try {
			workbook.close();
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String getString(col colTag, int rowTag) {
		int col = getColumnMap(colTag);
		int row = getRowMap(rowTag);
		HSSFRow rowTmp = worksheet.getRow(row);		
		HSSFCell cellTmp = rowTmp.getCell(col);
		String data;
		try{
			data = cellTmp==null ? "null" : cellTmp.getRichStringCellValue().toString();
		}catch(IllegalStateException e){
			data = cellTmp==null ? "null" : cellTmp.toString();
		}
		data = data=="" ? "null" : data;
		return data;
	}
	
	public String getString(int colTag, int rowTag) {
		int col = getColumnMap(colTag);
		int row = getRowMap(rowTag);
		HSSFRow rowTmp = worksheet.getRow(row);		
		HSSFCell cellTmp = rowTmp.getCell(col);
		String data;
		try{
			data = cellTmp==null ? "null" : cellTmp.getRichStringCellValue().toString();
		}catch(IllegalStateException e){
			data = cellTmp==null ? "null" : cellTmp.toString();
		}
		data = data=="" ? "null" : data;
		return data;
	}
	
	private int getColumnMap(col colTag) {	
		char colChar = colTag.toString().charAt(0);
		return colChar>='A' && colChar<='Z' ? colChar-'A' : colChar-'a';
	}

	private int getColumnMap(int col) {
		return col-1;
	}
	
	private int getRowMap(int row) {
		return row-1;
	}
	
	public enum col{
		A, B, C, D, E, F, G, 
		H, I, J, K, L, M, N, O, P, 
		Q, R, S, T, U, V, 
		W, X, Y, Z
	}
	
}

