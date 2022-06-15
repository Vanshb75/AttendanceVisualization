
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
