
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

/*
* Initializing class AddAttendanceFrame
* this class is to support the add addtendence
* adding the frame for date selector so that user can input the name of the new col
* aswell giving the user to search a cvs file for getting attendence information from
*/
public class AddAttendanceFrame {
	static JFileChooser fc;
    	//static JLabel l;
	/*
	* function to setup up a frame for add attendence
	*/
	public static void createFrame()
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
		 //creating a new frame which takes the name of the new col to be added
                JFrame frame = new JFrame("Date Selector");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    
		 //creating a new panel 
                JPanel panel = new JPanel();
		    //adding layout to that panel on the y axis
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
		    //creating a new panel
                JPanel inputpanel = new JPanel();
                inputpanel.setLayout(new FlowLayout());
                
		//creating name for the window as "Input Date"
		//so user knows that they need to enter the "Input Date"
                JLabel inputLabel = new JLabel("Input Date");
                
		// Setting up the text field/ input part in the frame
		// so that user can type the name of the new col field
                JTextField input = new JTextField(20);
                input.setText("");
                input.addFocusListener(new FocusListener() {
                    public void focusGained(FocusEvent e) {
                    }

                    public void focusLost(FocusEvent e) {
                      
                    }
                });
                    
		//creating a new J button for selcting a file
		//from which we will get the add attendence information
                JButton button = new JButton("Select File");
                button.addActionListener(new ActionListener () {
                    public void actionPerformed(ActionEvent e) {
			    
			    //saving the file information from file chooser in fc
                        fc = new JFileChooser();
                        int result = fc.showOpenDialog(null);
			    
			    //if the final project if approved to be runned
                        if (result == JFileChooser.APPROVE_OPTION) {
				
				//creating a new variable to store file information
                            File selectedFile = fc.getSelectedFile();
				
				//creating a string for dates
                            String date = input.getText();
				
				//referencing to filetools.java
				//calling the meathod addAttendence and passing the parameters to it
                            FileTool.addAttendance(selectedFile, date);
				
				//Showing the dialoge box for all the missing enteries
                            JOptionPane.showMessageDialog(frame, FileTool.fileContents,"Mismatched entries",JOptionPane.PLAIN_MESSAGE);
				
				//also updating the table
                            GUI.updateJTable();
                           // l.setText(selectedFile.getAbsolutePath());
                        }
                    }
                });
                
                
                //adding all the objects created in the class the the required pane
                inputpanel.add(inputLabel);
                inputpanel.add(input);
                inputpanel.add(button);
                panel.add(inputpanel);
                //frame.add(l);
		    
		//adding all the objects created in the class the the required frame
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(false);
                input.requestFocus();
            }
        });
    }
}
