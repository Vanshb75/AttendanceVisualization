
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
