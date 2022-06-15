/*
*Team information:
* Eisa Taherkalateh (etaherk1)
* Aeric Plitz (aplitz)
* Raghav Aggarwal (raggar13)
* Vansh Bagaria (vbagari1)
* William Owen (wowen1)
*
* File description:
* This is a linked list node
* Each node stores a date and associated time for the student that has the linked list
*/

public class Date {
	public String date;
	public int time;
	public Date next;
	//create date with associated name
	public Date(String dateIn)
	{
		date = dateIn;
		time = 0;
	}
	//add time when called
	public void AddTime(int timeIn)
	{
		time+= timeIn;
	}
}
