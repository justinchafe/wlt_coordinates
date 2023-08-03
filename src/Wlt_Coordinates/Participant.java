package Wlt_Coordinates;
//Participant class

import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;

public class Participant {

	final static int NUM_DATA_FIELDS = 6;
	String[][] responseData;
	BufferedWriter outputStream; //or print writer here?
	String path;
	File f;
	String subjectNum;
	double startTime;
	double endTime;
	int currentDataSet, dataField;
	XmlBuilder xml;


	public Participant(String subjectNum) {
				
		this.subjectNum = subjectNum;
		path = System.getProperty("user.dir") + System.getProperty("file.separator") + subjectNum + ".csv";
		createFile();
		responseData = new String[Wlt_Coordinates.NUM_SLIDES][NUM_DATA_FIELDS];
		
		currentDataSet = 0;
		xml = new XmlBuilder(subjectNum);
		//dataField = 0; //>>> prob not needed anymore!!!
	
	}

	

	private boolean createFile() {
		File f = new File(path);
		String s;
		JFrame frame = new JFrame("dialog");

		try {
			if (f.exists()) {
				int n = JOptionPane.showConfirmDialog(frame,"Warning! file already exists, would you like to overwrite previous data?",
    				"Overwrite",
    				JOptionPane.YES_NO_OPTION);
		
				if (n == JOptionPane.YES_OPTION) {
					f.delete();
					f.createNewFile();
				}else if (n == JOptionPane.NO_OPTION) {
					System.exit(0);
				}else if (n == JOptionPane.CLOSED_OPTION) {
					System.exit(0);
				}
				
			}else {
				if (f.createNewFile()) {
					;
				}else
					; //exists, handle append or overwrite
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame,
    			"File IO Error",
   			"File Error",
    			JOptionPane.WARNING_MESSAGE);

		}	


		return true;
	}


	public void writeXmlFile() {
		xml.writeXmlFile();
	}

	public boolean writeDataToFile() {
		xml.writeXmlFile();
		BufferedWriter outputStream = null;
		int i,q;
		q = 0;
		try {
			outputStream = new BufferedWriter(new FileWriter(path, true));
			for (i = NUM_DATA_FIELDS-1; i > 0; i--) {

				outputStream.write(responseData[currentDataSet][q++] + ", ");
			}
			outputStream.write(responseData[currentDataSet][q]);
			outputStream.newLine(); //move to the next line
			outputStream.close();
			advanceDataSet();
			return true;			
				

		}catch (IOException e) {
			System.out.println("File IO Error");
			return false;
		}

		finally {
			if (outputStream != null ) {
				//outputStream.close();
				return true;
			}
		}
	
		
	}


	public void advanceDataSet() {
		if (currentDataSet < Wlt_Coordinates.NUM_SLIDES) {
			currentDataSet++;
		}
			
	}


	public void addSide(Side s, int whichSide) {
		int i;
		Line2D line;
		for (i = 1; (line = s.getLineAt(i)) != null; i++) {
			responseData[currentDataSet][whichSide] = responseData[currentDataSet][whichSide] + line.getX1() + ", " + line.getY1() + ", " + line.getX2() + ", " + line.getY2();
			if (i+1 != s.getNumLines()) {
				responseData[currentDataSet][whichSide] = responseData[currentDataSet][whichSide] + ": ";
			}
				
				
		}
	}



	public void addSlideNum(String slideNum) {
		responseData[currentDataSet][5] = slideNum;
		xml.addSlideElement(slideNum);
	}

	public void addImageName(String imgName, String slideNum) {
		responseData[currentDataSet][0] = imgName;
		xml.addImageElement(slideNum, imgName);
		xml.writeXmlFile();
	}

	public void addSide(int whichSide, Side s, String slideNum) {
		if (whichSide == 1) {
			xml.addSide("Right", s, slideNum);
		}else if (whichSide == 2) {
			xml.addSide("Left", s, slideNum);
		}else
			System.out.println("ERROR: non-valid side");
	}


	public void addXmlLineData(String slideNum, int whichSide, Side s) {
		
	 	//xml.addLine("L1", slideNum, responseData[currentDataSet][1], responseData[currentDataSet][2]);
		//System.out.println("HERE IS THE LINE INFO: " + responseData[currentDataSet][1] + ", " + responseData[currentDataSet][2]);
		//xml.addLine("L2", slideNum, responseData[currentDataSet][3], responseData[currentDataSet][4]);
		
	}

	//TODO!!!
	public void addXmlLineData(Side side, String whichSide, String slideNum) {
		//for each line = s.getLineAt(i) {
		//		xml.addLine(whichSide, slideNum, + line.getX1() + line.getY1() + line.getX2() + line.getY2()); 
			
	} 
	

	/*public void addImageData(int slideNum, Line2D l1, Line2D l2) {
		responseData[currentDataSet][0] = java.lang.Integer.toString(slideNum);
		responseData[currentDataSet][1] = java.lang.Double.toString(l1.getX1()) + "| " + java.lang.Double.toString(l1.getY1()) + " | " + java.lang.Double.toString(l1.getX2()) + "| " + 		java.lang.Double.toString(l1.getY2());
		responseData[currentDataSet][2] = java.lang.Double.toString(l2.getX1()) + "| " + java.lang.Double.toString(l2.getY1()) + "| " + java.lang.Double.toString(l2.getX2()) + "| " + 			java.lang.Double.toString(l2.getY2());
		responseData[currentDataSet][3] = subjectNum;
		responseData[currentDataSet][4] = java.lang.Double.toString(startTime);
		writeDataToFile();
		advanceDataSet(); //REMOVE LATER!
	}
	*/	


	/*
	public String getName() {
	}

	public String getCode() {
	}

	public double getStartTime() {
	}

	public double getEndTime() {
	}
	*/

}//end class



