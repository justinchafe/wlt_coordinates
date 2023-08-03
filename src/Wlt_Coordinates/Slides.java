package Wlt_Coordinates;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

public class Slides {

	final int SLIDE_FIELDS = 2; //number of fields of slide information
        Object [][] slides;
	final int DEFAULT_MAX_SLIDES = 5; //the default maximum number of slides
        int currentSlide; // the current slide 
        private int slideCount; //how many slides have been loaded
        private int max_Slides; //the maximum number of slides               

	public Slides() {
		max_Slides = DEFAULT_MAX_SLIDES; //defaut setting
		slides = new Object[max_Slides][SLIDE_FIELDS];
		currentSlide = 1;
		slideCount = 0;
	}


//add a single slide.
	public boolean addSlide(String image) {
		JFrame frame = new JFrame("warning");
        	if (slideCount < max_Slides) {
			try {
    				slides[slideCount][0] = ImageIO.read(new File(image));
				slides[slideCount][1] = image;	
			} catch (IOException e) {
				JOptionPane.showMessageDialog(frame,
    				"Error Loading Image!",
   				"",
    				JOptionPane.WARNING_MESSAGE);
			}	
	
			slideCount++;
        		return true;

        	}else {
       			/* throw new Exception {
                	System.out.err("max Slides reached!");
               		 return false;
        		*/
		}
		return false;
	}

/*
Method to add multiple slides from a file.  Checks for the toal number of images to load first.  File Format:
filename:String, LineOne.x1, LineOne.y1, LineOne.x2, LineOne.y2, LineTwo.x1, LineTwo.y1, LineTwo.x1, LineTwo.x2.

*/
	public boolean loadFromFile(String filename) {
		BufferedReader in;
		FileReader file;
		StringTokenizer tokens;
		int i;
		Line2D lineOne, lineTwo;
		lineOne = new Line2D.Double();
		lineTwo = new Line2D.Double();
		double x1,y1,x2,y2;
		String line, image, sNum;
	
			try {
				in = new BufferedReader(new FileReader(filename));
				line = null;
				int totalSlides = 1;
				while ((line=in.readLine()) != null){
					totalSlides++;
					line = null;
				}
				System.out.println("TOTAL SLIDES: " + totalSlides);
				if (totalSlides - 1  > 0) {
					max_Slides = totalSlides-1;
					slides = new Object[max_Slides][SLIDE_FIELDS];
					in = new BufferedReader(new FileReader(filename));
					line = null;		
					while ((line = in.readLine()) != null) {
						tokens = new StringTokenizer(line, ",");
						System.out.println(tokens.countTokens());
						image = tokens.nextToken();
						System.out.println(image);
						addSlide(image);
						System.out.println("MAX SLIDES: " + max_Slides);
						line = null;
					}//end while
						
					in.close();
					return true;
				}else
					return false;
			}catch (IOException e) {
                                System.out.println("file exception:" + e);
				return false;
		 
			}

					
	}

	public BufferedImage getCurrentImage() {
		return (BufferedImage) slides[currentSlide-1][0];
	}

	public String getCurrentImageName() {
		return (String) slides[currentSlide-1][1];
	}


	public boolean advanceSlide() {
		if (currentSlide < max_Slides) {
			currentSlide++;
			return true;
		}else {
			System.out.println("Max Slides Reached!");
			return false;
		}
	}

	public int getCurrentSlideNum() {
		return currentSlide;
	}

	public String getActualSlideNum() {
		return (String) slides[currentSlide -1][3];
	}

	public boolean isMax() {
		return currentSlide >= max_Slides; 
	}

	
	public int getMax() {
		return max_Slides;
	}

//This can be somewhat expensive...arrayCopy...use wisely!
	public void increaseMax(int newMax) {
		if (newMax > max_Slides) {
			max_Slides = newMax;
			//must resize array!
			Object [][] resizedSlides = new Object[max_Slides][SLIDE_FIELDS];
			System.arraycopy(slides, 0, resizedSlides, 0, max_Slides-1);
			slides = resizedSlides;
		
		}else
			//throw error
			System.out.println("Error:  Current Max is greater than new max");
	}

/*
For Testing remove later!
public static void main(String[] args) {
Slides s = new Slides(1);
Line2D lineOne = new Line2D.Double(99.0, 118.0, 192.0, 275.0);
Line2D lineTwo = new Line2D.Double(238.0, 38.0, 329.0, 198.0);
s.addSlide("wlt3.jpg", lineOne, lineTwo);
System.out.println(s.getCurrentSlideNum());
System.out.println(s.getCurrentImage().toString());
System.out.println(s.getCurrentL1().getX1());
System.out.println(s.getCurrentL2().getY1());
System.out.println(s.getMax());
s.increaseMax(2);
System.out.println(s.getMax());
s.addSlide("wlt3.jpg", lineOne, lineTwo);
s.advanceSlide();
System.out.println("Current Slide Number: " + s.getCurrentSlideNum());
System.out.println(s.getCurrentImage().toString());


}

**/



}//end class
