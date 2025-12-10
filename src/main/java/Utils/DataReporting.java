package Utils;
import org.apache.poi.xssf.usermodel.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataReporting {
	
	private String path;
	private XSSFWorkbook wb;
	private XSSFSheet sheet;
	
	public DataReporting(String path, String sheetname) throws IOException {
		
		this.path = path;
		FileInputStream fis = new FileInputStream(path);
		wb = new XSSFWorkbook(fis);
	    sheet = wb.getSheetAt(0);
	    fis.close();
	}
	
	public XSSFSheet getsheet() {
		return sheet;
	}
	
	
	public void writecell(int row, int col, String value) throws IOException {
		XSSFRow sheetRow = sheet.getRow(row);
	    if(sheetRow == null) {
	        sheetRow = sheet.createRow(row);
	    }

	    XSSFCell cell = sheetRow.getCell(col);
	    if(cell == null) {
	        cell = sheetRow.createCell(col);
	    }

	    cell.setCellValue(value);

	    FileOutputStream fos = new FileOutputStream(path);
	    wb.write(fos);
	    fos.close();
	}
	
	public void close() throws IOException {
		wb.close();
	}	
}