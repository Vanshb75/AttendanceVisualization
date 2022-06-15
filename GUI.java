import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//****************************************************

import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GUI extends JFrame {
    static JLabel l;
    static JFileChooser fc;
    static JFrame frame = new JFrame("CSE360 Final Project");
    static JTable table = new JTable();
    static DefaultTableModel model = new DefaultTableModel();
    static JScrollPane pane = new JScrollPane(table);
    
    //Create the GUI menu bar
    public JMenuBar createMenuBar() {
        //initializing the menus
        JMenuBar menuBar;
        JMenu fileMenu;
        JMenuItem load,save,addAtt,plot;
        JComboBox addAttMonth,addAttDay;
        JButton aboutMenu;
        String teamInfo = "Eisa Taherkalateh ASURITE:etaherk1\nRaghav Aggarwal ASURITE: raggar13\nAeric Plitz ASURITE: aplitz\n" +
                "Vansh Rakesh Bagaria ASURITE: vbagari1\n" +"William Owen ASURITE: wowen1";
        String[] months;
        //String month,day,date;
        JRadioButtonMenuItem cbMenuItem;

        l = new JLabel("");
        GUI gui = new GUI();

        //Menu bar that will include the menus
        menuBar = new JMenuBar();

        //Creating the file menu
        fileMenu = new JMenu("       File       ");
        fileMenu.setBorder(BorderFactory.createLineBorder(Color.black,1));

	//About menu with team information
        aboutMenu = new JButton("      About      ");
        aboutMenu.setBorder(BorderFactory.createLineBorder(Color.black,1));
        aboutMenu.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, teamInfo,"Team information",JOptionPane.PLAIN_MESSAGE);

            }
        });

        menuBar.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        //Creating the load part of the menu
        load = new JMenuItem ("Load a Roaster");
        load.setBorder(BorderFactory.createLineBorder(Color.black,1));
        load.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                fc = new JFileChooser();
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fc.getSelectedFile();
                    FileTool.loadRoster(selectedFile);
                    //data added to jtable
                    
                    updateJTable();
                    l.setText(selectedFile.getAbsolutePath());
                    l.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        //Creating the save part of the menu
        save = new JMenuItem ("Save");
        save.setBorder(BorderFactory.createLineBorder(Color.black,1));
        save.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                l.setText("Saving a file...");
              //SAVE HERE
                fc = new JFileChooser();
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fc.getSelectedFile();
                    exportToCSV(selectedFile);
                    //data added to jtable
                }
            }
        });

        //Creating the attendance part of the menu
        addAtt = new JMenuItem ("Add Attendance");
        addAtt.setBorder(BorderFactory.createLineBorder(Color.black,1));
        addAtt.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
            	AddAttendanceFrame.createFrame();
            }
        });

        //Creating the plotting part of the menu
        plot = new JMenuItem ("Plot Data");
        plot.setBorder(BorderFactory.createLineBorder(Color.black,1));
        plot.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                l.setText("Plotting the data");
                plotData plot = new plotData(FileTool.convertDatesToArray());
                plot.setSize(800,400);
                plot.setLocationRelativeTo(null);
                plot.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                plot.setVisible(true);
                
            }
        });

	//Adding all the menuItems to fileMenu
        fileMenu.add(load);
        fileMenu.add(addAtt);
        fileMenu.add(save);
        fileMenu.add(plot);

	//Finalizing the menuBar 
        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);

        return menuBar;
    }

    //Setup and draw the gui panel
    public static void showGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        GUI gui = new GUI();;
        frame.setJMenuBar(gui.createMenuBar());
        frame.add(pane);
        //frame.add(l);
        

        frame.pack();
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.setLayout(new FlowLayout());
    }
    
    //Update the JTable with the information provided
    public static void updateJTable() {
      	model.setRowCount(0);
      	ArrayList<String> columns = new ArrayList<String>();
      	columns.add("ID");
      	columns.add("First Name");
      	columns.add("Last Name");
      	columns.add("Program and Plan");
      	columns.add("Academic Level");
      	columns.add("ASURITE");
      	
      	table.setModel(model);
      	
      	table.setBackground(Color.white);
      	table.setForeground(Color.black);
      	table.setSelectionBackground(Color.red);
      	table.setGridColor(Color.black);
      	table.setSelectionForeground(Color.white);
      	table.setFont(new Font("Tahoma", Font.PLAIN,17));
      	table.setRowHeight(30);
      	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      	table.setAutoCreateRowSorter(true);
      	
      	
      	pane.setForeground(Color.red);
      	pane.setBackground(Color.WHITE);
      	//pane.setBounds(100, 100, 757, 610);
      	//frame.getContentPane().add(pane);
      	
      	int column=6;
      	Student studentPointer = FileTool.studentHead;
      	Date datePointer = studentPointer.attendanceHead;
      	while(datePointer!=null)
      	{
      		columns.add(datePointer.date);
      		datePointer=datePointer.next;
      		column++;
      	}
      	model.setColumnIdentifiers(columns.toArray());
      	Object[] row = new Object[column];

		while(studentPointer != null)
		{
			// change textID to ClassTools::ID and for rest
            row[0] = studentPointer.ASUID;
			row[1] = studentPointer.firstName;
			row[2] = studentPointer.lastName;
			row[3] = studentPointer.programPlan;
			row[4] = studentPointer.academicLevel;
			row[5] = studentPointer.ASURITE;
			
			datePointer = studentPointer.attendanceHead;
			column = 6;
			while(datePointer != null)
			{
				row[column] = datePointer.time;
				datePointer = datePointer.next;
				column++;
			}
			
			
			model.addRow(row);
			
			studentPointer=studentPointer.next;
		}
		model.fireTableDataChanged();
    }

    //Save the information to a csv file
    public static boolean exportToCSV(File pathToExportTo) {
        try {

            TableModel model = table.getModel();
            FileWriter csv = new FileWriter(pathToExportTo);
            
            for (int i = 0; i < model.getColumnCount(); i++) {
                csv.write(model.getColumnName(i) + ",");
            }

            csv.write("\n");

            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    csv.write(model.getValueAt(i, j).toString() + ",");
                }
                csv.write("\n");
            }

            csv.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
