package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

 /**
  * @author Anusha 
  * This is a resable class to work with property file
  */
public class ExcelFileUtility {

	Workbook wb=null;
	
     /**
      * This method is used for fetching data from Excel file
      * @param sheetname
      * @param rowindex
      * @param cellindex
      * @return
      * @throws EncryptedDocumentException
      * @throws IOException
      */
	public String fetchDataFromExcel(String sheetname,int rowindex,int cellindex) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/VtigerTestData.xlsx");
		wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetname).getRow(rowindex).getCell(cellindex).toString();
		return data;
	}
	/**
	 * 
	 * This method is used write back the data into the ExcelFile
	 * @param sheetname
	 * @param rowindex
	 * @param cellindex
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void WriteBackDataToExcel_ExistingRow(String sheetname,int rowindex,int cellindex,String data) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/VtigerTestData.xlsx");
		wb = WorkbookFactory.create(fis);
		 Cell c = wb.getSheet(sheetname).getRow(rowindex).createCell(cellindex);
		 c.setCellValue(data);
		 FileOutputStream fos=new FileOutputStream("./src/test/resources/Vtiger.properties");
		 wb.write(fos);
	}
	/**
	 * This
	 * @param sheetname
	 * @param rowindex
	 * @param cellindex
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	
	public void WriteBackDataToExcel_NewRow(String sheetname,int rowindex,int cellindex,String data) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/VtigerTestData.xlsx");
		wb = WorkbookFactory.create(fis);
		 Cell c = wb.getSheet(sheetname).createRow(rowindex).createCell(cellindex);
		 c.setCellValue(data);
		 FileOutputStream fos=new FileOutputStream("./src/test/resources/Vtiger.properties");
		 wb.write(fos);
	}
	
	
	/**
	 * This is a reusable method for using to close the property file
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	
	public void closeTheExcelFile() throws EncryptedDocumentException, IOException {
	wb.close();
		}
	
	
}
