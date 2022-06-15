/*
*Team information:
* Eisa Taherkalateh (etaherk1)
* Aeric Plitz (aplitz)
* Raghav Aggarwal (raggar13)
* Vansh Bagaria (vbagari1)
* William Owen (wowen1)
*/

/*
* File description: 
* This is the Mian function for all these classes in the project
* This function invokes the GUI class
*/

import javax.swing.*;
import java.awt.*;


public class Main {
	/*
	* initiallizing the main meathod
	*/
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
       			@Override
      	      		public void run() {
			//Calling the showGUI meathod form GUI class
			// to start the GUI window
      	          	GUI.showGUI();
   	         	}
	        });
	}
}
