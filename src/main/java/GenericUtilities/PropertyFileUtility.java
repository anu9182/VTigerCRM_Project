package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * @author Anusha This class contains all the reusable methods from seleniumlibrary
 */
public class PropertyFileUtility {
	/**
	 * This is a method for fetching the data from propery file
	 * @param key
	 * @return
	 * @throws IOException
	 */
public String fetchDataFromPropFile(String key) throws IOException {
	
	//convert physical file to java obj file
	FileInputStream fis=new FileInputStream("./src/test/resources/Vtiger.properties");
	//create obj of properties file
	Properties p=new Properties();
	//load all key and values
	p.load(fis);
	String value=p.getProperty(key);
	return value;
	}
/**
 * This method is used to write or store the updated data into the propery file
 * @param key
 * @param value
 * @throws IOException
 */

public void writeDataToPropFile(String key,String value) throws IOException {
	
	FileInputStream fis=new FileInputStream("./src/test/resources/Vtiger.properties");
	Properties p=new Properties();
	p.load(fis);
	//write key and values in properties
	p.put(key, value);
	//once the property file open than close it that is the reason we are using fos
	//write data to file
	//is this convert to java obj to physical obj
	FileOutputStream fos=new FileOutputStream("./src/test/resources/Vtiger.properties");
	//this is used to save the changes made to the properties file
    p.store(fos, "Updated");
}


}
