/*
*Team information:
* Eisa Taherkalateh (etaherk1)
* Aeric Plitz (aplitz)
* Raghav Aggarwal (raggar13)
* Vansh Bagaria (vbagari1)
* William Owen (wowen1)
* 
* File description: Each student is a linked list, and correspond with each row in the jtable
*/
public class Student {
	public int ASUID;
	public String firstName;
	public String lastName;
	public String programPlan;
	public String academicLevel;
	public String ASURITE;
	public Date attendanceHead;
	private Date attendancePointer;
	public Student next;
	
	public Student(int ASUIDin, String firstNameIn, String lastNameIn, String programPlanIn, String academicLevelIn, String ASURITEin)
	{
		ASUID=ASUIDin;
		firstName=firstNameIn;
		lastName=lastNameIn;
		programPlan=programPlanIn;
		academicLevel=academicLevelIn;
		ASURITE=ASURITEin;
	}
	/*
	 * when a new date is loaded from add attendance, it is added to each student here
	 * Args:
	 * String dateIn
	 */
	public void addDate(String dateIn)
	{
		boolean overwrite=false;
		//make new date
		Date newDate = new Date(dateIn);
		//if no date head exists OR date head has same date, make new one and return
		if(attendanceHead == null || attendanceHead.date.equals(dateIn))
		{
			attendanceHead = new Date(dateIn);	
			attendancePointer = attendanceHead;
			return;
		}
		//if a date does exist, search for either the tail(to create a new date) OR the same date(to overwrite)
		attendancePointer = attendanceHead;
		while(attendancePointer.next!=null && overwrite == false)
		{
			//if same date
			if(attendancePointer.next.date.equals(dateIn))
			{
				overwrite = true;
			}
			
		}
		//if overwriting, preserve the next link
		if(overwrite)
		{
			newDate.next = attendancePointer.next.next;
		}
		//make the next date the new date
		attendancePointer.next=newDate;
		attendancePointer = attendancePointer.next;
	}
	
	/*
	 * this assumes addDate has been executed already. re-execute for each new date. if it has not, and this is called, null errors can occur.
	 * it adds the time to previously added date
	 */
	public void addTime(int timeIn)
	{
		attendancePointer.AddTime(timeIn);
	}
}
