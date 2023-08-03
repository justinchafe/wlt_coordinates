package Wlt_Coordinates;
import javax.swing.JFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Wlt_Coordinates extends JFrame {

	public static int NUM_SLIDES;
	final static String BUTTON_MSG = "Click when finished";
	final static String CLEAR = "Clear";
	final static double LINE_DETECT = 0.5;
	final static String NOT_FINISHED_ERROR = "Please finish extending the line across the container.";
	final static String IMG_DIR = "images";
	final static String IMG_FILE = "settings/imagedata.csv";
	final static String FILE_NAME = "settings/xmlFootastic";

	final int BUTT_SIZE = 120;
	final static String MOD_END_POINTS = "Modify";
	final String MOD_RIGHT_POINTS = "modRight";
	final String MOD_LEFT_POINTS = "modLeft";
	final String MOD_TOP_POINTS = "modTop";
	final String MOD_BOTTOM_POINTS = "modBottom";
	final static String ADDRIGHT = "Add Right";
	final static String ADDLEFT = "Add Left";
	final static String ADDTOP = "Add Top";
	final static String ADDBOTTOM = "Add Bottom";
	final static String SNAPTOP = "Snap Top";
	final static String SNAPBOTTOM = "Snap Bottom";
	
	WltPanel panel;
	JPanel mainPanel, bottomPanel;
	JButton button1;
	JLabel label1;
	JLabel label2;
	JTextField tempC;
	JButton cont, modify, addTopLines, addBottomLines, modTopLines, modBottomLines, snapTop, snapBottom, clear, addEndPoint1, addEndPoint2, modEndPoints1, modEndPoints2, fill1, fill2;
	JComboBox sideList;	 

	public Wlt_Coordinates() {
		
		String s;
		setSize(1024, 768);
        	setTitle("Simple");
		setAlwaysOnTop(false);
		this.setDefaultCloseOperation(JFrame. DO_NOTHING_ON_CLOSE);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		//frame.setSize(new Dimension(436,800)); // SET THE SIZE OF THE WINDOW!!!
		//frame.setResizable(false);

		while ( (s = (String)JOptionPane.showInputDialog(this,"Enter the subject number: ")) != null && !(s.matches("\\d+")) ) {
			JOptionPane.showMessageDialog(this, "Please enter digits");
		}
	
		if (s == null) {
			System.exit(0);
		}
		mainPanel = new JPanel(new BorderLayout());
		bottomPanel = new JPanel();
		panel = new WltPanel(s);
		panel.setSize(1024, 768);
		panel.addMouseListener(panel);
		panel.addMouseMotionListener(panel);
		panel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		mainPanel.add(panel, BorderLayout.CENTER);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		mainPanel.setBackground(Color.white);
		
		//frame.getRootPane().setDefaultButton(button1); //see root pane (default button!)
		//frame.setContentPane(panel);
		addComponents();
		mainPanel.add(panel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		// what happens when user closes the JFrame.
		WindowListener windowListener = new WindowAdapter()
  			 {
  		 	// anonymous WindowAdapter class
   			public void windowClosing ( WindowEvent w )
     			 {
     					panel.writeOnClose();
     				        setVisible(false);
     					dispose();
     				 } // end windowClosing
   			};// end anonymous class
		this.addWindowListener( windowListener );
		
	
	}

	private void addComponents() {
		
		String[] items = {ADDRIGHT, ADDLEFT, ADDTOP, ADDBOTTOM, SNAPTOP, SNAPBOTTOM, MOD_END_POINTS};
		sideList = new JComboBox(items);
		sideList.setSelectedIndex(0);
		sideList.addActionListener(panel);
				

		cont = new JButton(Wlt_Coordinates.BUTTON_MSG);
		cont.setPreferredSize(new Dimension(175,50));
		bottomPanel.add(cont);
		cont.addActionListener(panel);
		clear = new JButton(CLEAR);
		clear.setPreferredSize(new Dimension(75,20));
		clear.addActionListener(panel);
		bottomPanel.add(clear);
		bottomPanel.add(sideList);
		
	}
  

	public static void main(String[] args) {

		Wlt_Coordinates wlt = new Wlt_Coordinates();
		wlt.setVisible(true);
	} 
}

