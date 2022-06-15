/*
*Team information:
* Eisa Taherkalateh (etaherk1)
* Aeric Plitz (aplitz)
* Raghav Aggarwal (raggar13)
* Vansh Bagaria (vbagari1)
* William Owen (wowen1)
*
* file description: Date, but with everyone's attendance in one arraylist
*/
import java.util.*;

/*
* This is a data structure for storing dates
* Store the date and attendence for students
*/

class Dates
{
	//date, but with everyone's attendance in one arraylist
    String date;
	
	//ArrayList for attendence
    ArrayList<Integer> attendance = new ArrayList<Integer>();
	
	//custructor for saving the date input entered by user
	Dates(String dateIn)
	{
		date = dateIn;
	}
}

