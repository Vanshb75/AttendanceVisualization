/*
*Team information:
* Eisa Taherkalateh (etaherk1)
* Aeric Plitz (aplitz)
* Raghav Aggarwal (raggar13)
* Vansh Bagaria (vbagari1)
* William Owen (wowen1)
*
* File description: FileTool.java works as a load and save function for the whole project.
*/

//***************************************************

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class FileTool {	
	public static final String delimiter = ",";
	public static ArrayList<ArrayList<String>> fileContents; //fileContents[row][column]
	public static Student studentHead;
	
	public static void Read(File csvFile) //argument should be a string that is the file path
	{
		try
		{
			fileContents = new ArrayList<ArrayList<String>>();
			//file stuff
			File file = csvFile;//new File(csvFile);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			String[] tempArr;
			
			//reading the file till the eof (end of file)
			while((line = br.readLine()) != null)
			{
				tempArr = line.split(delimiter); //splits line into an array, by the delimiter
				fileContents.add(new ArrayList<String>(Arrays.asList(tempArr))); //add array, as arraylist, to file contents
			}
			System.out.println(fileContents);
				br.close(); //close reader
		} catch(IOException ioe) { //error reading
			ioe.printStackTrace();
		}
	}
	
	/*
	* function to load a roadster form the delected csv file
	*/
	
	public static void loadRoster(File csvFile)
	{
		Read(csvFile);
		try
		{
			if(fileContents != null) // enter if contents are not null
			{
				//saving the input in the respective variable
				studentHead = new Student(Integer.parseInt(getElement(0,0)),getElement(0,1),getElement(0,2),getElement(0,3),getElement(0,4),getElement(0,5));
				int row = 1;
				Student studentPointer = studentHead;
				while(row < fileContents.size())
				{
					//saving the input in the respective data structure
					studentPointer.next = new Student(Integer.parseInt(getElement(row,0)),getElement(row,1),getElement(row,2),getElement(row,3),getElement(row,4),getElement(row,5));
					studentPointer=studentPointer.next;
					row++;
				}
			}else {
				//no file contents
				System.out.println("File contains nothing");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*
	* Meathod to addAtendence from choosen csv file
	*/
	public static void addAttendance(File csvFile, String dateIn)
	{
		Read(csvFile);
		try
		{
			if(fileContents != null)	// enter if fileContents is not null
			{
				Student studentPointer = studentHead;
				int row;
				//reading till the end of file
				while(studentPointer != null)
				{
					row = 0;
					//adding data to data structure 
					studentPointer.addDate(dateIn);
					while(row < fileContents.size())
					{
						if(studentPointer.ASURITE.equals(getElement(row,0)))
						{
							System.out.println("Added " + getElement(row,0));
							studentPointer.addTime(Integer.parseInt(getElement(row,1)));
							fileContents.remove(row);
							row--;
						}
						row++;
					}
					studentPointer=studentPointer.next;
				}
			}else {
				//no file contents
				System.out.println("File contains nothing");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*
	* coverting the read dates into a array so that information
	* can be further processed
	*/
	public static Dates[] convertDatesToArray()
	{	// putting dates into array
		Student studentPointer = studentHead;
		ArrayList<Dates> dates = new ArrayList<Dates>();
		Date datePointer;
		boolean timeAdded;
		int row;
		//while students exist
		while(studentPointer != null)
		{
			row = 0;
			//look at student's attendance
			datePointer = studentPointer.attendanceHead;
			//while days exist in student's attendance
			while(datePointer != null)
			{
				timeAdded = false;
				//loop through dates already in dates arraylist
				for(int datesIndex = 0; datesIndex < dates.size(); datesIndex++)
				{
					//if date is already there, add time to associated arraylist
					if(dates.get(datesIndex).date.equals(datePointer.date))
					{
						dates.get(datesIndex).attendance.add(datePointer.time);
						timeAdded=true;
					}
				}
				//if date was never there, create new and add time to new arraylist
				if(!timeAdded)
				{
					dates.add(new Dates(datePointer.date));
					dates.get(dates.size()-1).attendance.add(datePointer.time);
				}
				//move to next date in student's attendance
				datePointer = datePointer.next;
			}
			//move to next student
			studentPointer = studentPointer.next;
		}
		//return plot compatible arraylist
		return (Dates[]) dates.toArray(new Dates[0]);
	}
	
	public static String getElement(int row, int column)
	{
		return fileContents.get(row).get(column);
	}
}
