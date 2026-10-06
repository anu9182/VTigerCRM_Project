package GenericUtilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 * @author Anusha This is a reusable class to work with json file
 */

public class JSONFileUtility {
/**
 * This is a reusable method to fetch data from json file
 * @return 
 * @throws ParseException 
 * @throws IOException 
 * @throws FileNotFoundException 
 */
	public String FetchTheDataFromJson(String key) throws FileNotFoundException, IOException, ParseException {
		JSONParser parse = new JSONParser();
		Object obj=parse.parse(new FileReader("./src/test/resources/JSONCMData.json"));
		JSONObject jsobj = (JSONObject) obj;
		String data=jsobj.get(key).toString();
		return data;
	}
}
