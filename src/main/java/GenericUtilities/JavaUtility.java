package GenericUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
/**
 * @author Anusha this is a reusable class used for java utilities
 */
public class JavaUtility {
/**
 * This is a reusable method for fetching current date
 * @return
 */
	public String fetchCurrentDate() {
		Date date=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String currentdate = sim.format(date);
		return currentdate;
	}
	/**
	 * this is a reusable method for fetching the date after given no of days
	 * @param days
	 * @return
	 */
	public String fetchDateAfterGivenNoOfDays(int days) {
		Date date=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		sim.format(date);
		Calendar cal=sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String date_giveDays = sim.format(cal.getTime());
		return date_giveDays;
	}
	/**
	 * this is a reusable method for generating the random numbers
	 * @return
	 */
	public int generateRandomNumber() {
		Random r=new Random();
		int num = r.nextInt(1000);
		return num;
		}
		
}

