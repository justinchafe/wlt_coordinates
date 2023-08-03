package Wlt_Coordinates;


import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;
//import javax.imageio.*;
import java.awt.image.*;
//import javax.swing.ImageIcon;
//import java.io.*;



public class WltPanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener {

	final double LINE_DETECT = 2.5;
	final int BUTT_SIZE = 120;
	final String MOD_END_POINTS = "modEndPoints";
	final String MOD_RIGHT_POINTS = "modRight";
	final String MOD_LEFT_POINTS = "modLeft";
	final String MOD_TOP_POINTS = "modTop";
	final String MOD_BOTTOM_POINTS = "modBottom";
	final String ADDRIGHT = "addRight";
	final String ADDLEFT = "addLeft";
	final String ADDTOP = "addTop";
	final String ADDBOTTOM = "addBottom";
	final String SNAPTOP = "snapTop";
	final String SNAPBOTTOM = "snapBottom";
	

	double x1, y1, x2, y2;
	BufferedImage img;
	JPanel bottomPanel, rightMainPanel;
	Line2D lineOne, lineTwo, userLine;
	int modEndPoints;
	double x,y, oldX, oldY;
	boolean lineOneSelected, drawLine, init, hasPainted, reset, changeEndPoint, hasDragged, resSet;
	Slides mySlides;
	JButton cont, modify, addTopLines, addBottomLines, modTopLines, modBottomLines, snapTop, snapBottom, clear, addEndPoint1, addEndPoint2, modEndPoints1, modEndPoints2, fill1, fill2;
	JLabel cordLabel, a,b;
	Point2D ratio;

	
	
	//Participant p;
	XmlBuilder xml;
	LineManager l;
	GeneralPath path;
	

	public WltPanel(String ParticipantNum) {

		
	
		ratio = new Point2D.Double(0,0);
		xml = new XmlBuilder(Wlt_Coordinates.FILE_NAME + "_" + ParticipantNum);
		l = new LineManager(1);
		hasPainted = reset = hasDragged = false;
		changeEndPoint = false; 
		oldX = oldY = 0;
		this.setLayout(new BorderLayout());
		//bottomPanel = new JPanel();
		//rightMainPanel = new JPanel(new GridLayout(0, 1));
		modEndPoints = 0;
		//addComponents();
		//this.add(rightMainPanel, BorderLayout.LINE_END);
		//this.add(bottomPanel, BorderLayout.PAGE_END);
		//first = 0;
		//lineOne = new Line2D.Double(181, 213.0, 351.0, 503.0);
		//lineTwo = new Line2D.Double(437.0, 70.0, 602.0, 359.0);
		//System.out.println("Line One toString(): " + lineOne.toString());
		this.setSize(1024,1024);
		this.setBounds(1024,1024,1024,1024);
		mySlides = new Slides();
		//mySlides.addSlide("wlt3_image1.jpg", lineOne, lineTwo);
		//mySlides.addSlide("wlt3_image2.jpg", lineOne, lineTwo);

		//String imgPath = System.getProperty("user.dir") + System.getProperty("file.separator") + "imagedata.csv"; //edited 2023

        System.out.println(Wlt_Coordinates.IMG_FILE);
		if (!mySlides.loadFromFile(Wlt_Coordinates.IMG_FILE)) {
				JFrame frame = new JFrame("warning");
				JOptionPane.showMessageDialog(frame,
    				"No Images to Load!",
   				"",
    				JOptionPane.WARNING_MESSAGE);
				System.exit(0);
	
		}
		Wlt_Coordinates.NUM_SLIDES = mySlides.getMax();
		this.setPreferredSize(new Dimension(1024,1024));
				
		//System.out.println(mySlides.getCurrentImage().toString());
		//System.out.println(mySlides.getCurrentL1().getX1());
		//System.out.println(mySlides.getCurrentL1().getY1());
		System.out.println(mySlides.getCurrentSlideNum());
		resSet = false;

		
		
		







		//p.addImageData(mySlides.getCurrentSlideNum(), mySlides.getCurrentL1(), mySlides.getCurrentL2());

		//img = mySlides.getCurrentImage();
		//init = true;
		//lineOneSelected = false;
		drawLine = false;


		//x1 = x2 = y1 = y2 = 0;
		//userLine = new Line2D.Double(0,0,0,0);

		//label = new JLabel(new ImageIcon(mySlides.getCurrentImage()));
		//this.add(label);

	}

	public void addComponents() {

		fill1 = new JButton("");
		fill2 = new JButton("");
		cont = new JButton(Wlt_Coordinates.BUTTON_MSG);
		cont.setPreferredSize(new Dimension(250,50));
		bottomPanel.add(cont);
		cont.addActionListener(this);
		clear = new JButton("clear");
		clear.addActionListener(this);
		bottomPanel.add(clear);	
			
		cordLabel = new JLabel();
		cordLabel.setOpaque(true);
		cordLabel.setBackground(Color.red);
		
		bottomPanel.add(cordLabel);
		
		
		//initLabels:
		
		//l1EndOne = new JLabel();
		//l1EndTwo = new JLabel();
		modify = new JButton(MOD_END_POINTS);
		modify.addActionListener(this); //NEW!!!
		
		//modEndPoints1 = new JButton(MOD_RIGHT_POINTS);
		//modEndPoints1.addActionListener(this);
		//modEndPoints2 = new JButton(MOD_LEFT_POINTS);
		//modEndPoints2.addActionListener(this);
		
		//line1 = new JButton("Line One");
		//line1.addActionListener(this);
		addEndPoint1 = new JButton(ADDRIGHT);
		addEndPoint1.addActionListener(this);
		addEndPoint2 = new JButton(ADDLEFT);		
		addEndPoint2.addActionListener(this);
		//l1end1 = new JButton("l1_End1");
		//l1end1.addActionListener(this);
		//l1end2 = new JButton("l1_End2");
		//l1end2.addActionListener(this);
		
		//line2 = new JButton("Line Two");
		//line2.addActionListener(this);
		//l2end1 = new JButton("l2_End1");
		//l2end1.addActionListener(this);
		//l2end2 = new JButton("l2_End2");
		//l2end2.addActionListener(this);

		addTopLines = new JButton(ADDTOP);
		addTopLines.addActionListener(this);
		//modTopLines = new JButton(MOD_TOP_POINTS);
		//modTopLines.addActionListener(this);
		snapTop = new JButton(SNAPTOP);
		snapTop.addActionListener(this);

		addBottomLines = new JButton(ADDBOTTOM);
		addBottomLines.addActionListener(this);
		//modBottomLines = new JButton(MOD_BOTTOM_POINTS);
		//modBottomLines.addActionListener(this);
		snapBottom = new JButton(SNAPBOTTOM);
		snapBottom.addActionListener(this);
	
		a = new JLabel("L1_End1: " + l.lineRight.getX1() + ", " + l.lineRight.getY1() + " |  L1_End2: " + l.lineRight.getX2() + ", " + l.lineRight.getY2() + " || " );
		b = new JLabel("L2_End1: " + l.lineLeft.getX1() + ", " + l.lineLeft.getY1() + " |  L1_End2: " + l.lineLeft.getX2() + ", " + l.lineLeft.getY2() );

		a.setOpaque(true);
		a.setBackground(Color.red);
		b.setOpaque(true);
		b.setBackground(Color.red);

		addTopLines.setPreferredSize(new Dimension(BUTT_SIZE,10));
		addBottomLines.setPreferredSize(new Dimension(BUTT_SIZE,10));
		addEndPoint1.setPreferredSize(new Dimension(BUTT_SIZE,10));	
		addEndPoint2.setPreferredSize(new Dimension(BUTT_SIZE,10));
		//modBottomLines.setPreferredSize(new Dimension(BUTT_SIZE,10));
		//modTopLines.setPreferredSize(new Dimension(BUTT_SIZE,10));
		//modEndPoints1.setPreferredSize(new Dimension(BUTT_SIZE,10));
		//modEndPoints2.setPreferredSize(new Dimension(BUTT_SIZE,10));
		snapTop.setPreferredSize(new Dimension(BUTT_SIZE,10));
		snapBottom.setPreferredSize(new Dimension(BUTT_SIZE,10));
		modify.setPreferredSize(new Dimension(BUTT_SIZE,10));
		//fill1.setPreferredSize(new Dimension(BUTT_SIZE,10));
		//fill2.setPreferredSize(new Dimension(BUTT_SIZE,10));		
		
		//rightMainPanel.add(line1);
		
		rightMainPanel.add(addEndPoint1);
		//rightMainPanel.add(modEndPoints1);
		//rightMainPanel.add(fill1);
		//rightMainPanel.add(line2);
		
		rightMainPanel.add(addEndPoint2);
		//rightMainPanel.add(modEndPoints2);
		//rightMainPanel.add(fill2);
		
		rightMainPanel.add(addTopLines);
		//rightMainPanel.add(modTopLines);
		

		rightMainPanel.add(addBottomLines);
		rightMainPanel.add(snapTop);
		//rightMainPanel.add(modBottomLines);
		rightMainPanel.add(snapBottom);
		rightMainPanel.add(modify);

		bottomPanel.add(a);
		bottomPanel.add(b);
		


	}

	private boolean snapTop() {
		Line2D topLine, leftLine;		
		if (!l.topSideIsEmpty()) {
				topLine = l.getTopSide().getLineAt(l.topSideNumLines());
			if (!l.leftSideIsEmpty()) {
				leftLine = l.getLeftSide().getLineAt(1);
				l.getTopSide().getLineAt(l.topSideNumLines()).setLine(topLine.getX1(), topLine.getY1(), leftLine.getX1(), leftLine.getY1());
				return true;
			}
				return false;
		}else
			return false;
	}

		private boolean snapBottom() {
		Line2D bottomLine, leftLine;		
		if (!l.bottomSideIsEmpty()) {
				bottomLine = l.getBottomSide().getLineAt(l.bottomSideNumLines());
			if (!l.leftSideIsEmpty()) {
				leftLine = l.getLeftSide().getLineAt(l.leftSideNumLines());
				l.getBottomSide().getLineAt(l.bottomSideNumLines()).setLine(bottomLine.getX1(), bottomLine.getY1(), leftLine.getX2(), leftLine.getY2());
				return true;
			}
				return false;
		}else
			return false;
	}


	public void updateCordLabels() {
		a.setText("x: " + x + ", y: " + y);  
		b.setText("currentX: " + this.getWidth() + ", currentY: " + this.getHeight());
	}

	private void setRatios() {
		/*Image img = mySlides.getCurrentImage();
		ratio.setLocation(x/img.getWidth(null), y/img.getHeight(null));
		System.out.println("x: " + ratio.getX() + ", y: " + ratio.getY());
		*/
		
	}
	private void scaleLine(Line2D line) {
		/*line.setLine(line.getX1()*ratio.getX(), line.getY1()*ratio.getY(), line.getX2()*ratio.getX(), line.getY2()*ratio.getY());
		System.out.println("HERE ARE SCALED POINTS IN SCALELINE, x: + " + line.getX1() + ", y: " +  line.getY1());
		*/	
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		x = this.getWidth(); 
		y = this.getHeight();
		//setRatios();
				
		//drawing
		//	label = new JLabel(new ImageIcon(mySlides.getCurrentImage()));
		
		// g.drawImage(mySlides.getCurrentImage(), 0, 0, null);
		Image img = mySlides.getCurrentImage();
		System.out.println("Image Width: " + img.getWidth(null) + ", Image Height: " + img.getHeight(null));
		g.drawImage(mySlides.getCurrentImage(), this.getWidth()/2 - mySlides.getCurrentImage().getWidth()/2, this.getHeight()/2 - mySlides.getCurrentImage().getHeight()/2, null);	   	
		//g.drawImage(mySlides.getCurrentImage(), getWidth()/2 - 366, 0, null); 		
		if (!hasPainted) {
			//p.addStartTime(System.currentTimeMillis());
			//p.addSlideNum(mySlides.getActualSlideNum());
			hasPainted = true;
		}
	
		Graphics2D g2d = (Graphics2D) g;


		if (drawLine) {
						
			if (!l.rightSideIsEmpty()) {
				int i;
				Line2D line, z;
				path = new GeneralPath();
				g2d.setColor(Color.ORANGE);
				for (i=1; (line = l.getRightSide().getLineAt(i)) != null; i++) {
					System.out.println("linedata time: " + line.getX1() + ", " +  line.getY2());
					path.append(line, true);
					g2d.fill(new Ellipse2D.Double(line.getX1(), line.getY1(), 7.0, 7.0));
					if (l.getRightSide().getLineAt(i+1) == null) {
						g2d.fill(new Ellipse2D.Double(line.getX2(), line.getY2(), 7.0, 7.0));
						break;					
					}					
										
				}
				g2d.setColor(Color.BLACK);
				g2d.draw(path);	
				g2d.drawString("Side1_E1", (float)l.getRightSide().getLineAt(1).getX1(), (float) l.getRightSide().getLineAt(1).getY1());
				g2d.drawString("Side1_E2", (float)l.getRightSide().getLineAt(l.rightSideNumLines()).getX2(), (float) l.getRightSide().getLineAt(l.rightSideNumLines()).getY2());		
				
	
			}	 
			
			 if (!l.leftSideIsEmpty()) {
				int i;
				Line2D line;
				path = new GeneralPath();
				
				g2d.setColor(Color.ORANGE);
				for (i=1; (line = l.getLeftSide().getLineAt(i)) != null; i++) {
					path.append(line, true);
					g2d.fill(new Ellipse2D.Double(line.getX1(), line.getY1(), 7.0, 7.0));
					if (l.getLeftSide().getLineAt(i+1) == null) {
						g2d.fill(new Ellipse2D.Double(line.getX2(), line.getY2(), 7.0, 7.0));
						break;					
					}					
				}
				g2d.setColor(Color.BLACK);
				g2d.draw(path);		
				g2d.drawString("Side2_E1", (float)l.getLeftSide().getLineAt(1).getX1(), (float) l.getLeftSide().getLineAt(1).getY1());
				g2d.drawString("Side2_E2", (float)l.getLeftSide().getLineAt(l.leftSideNumLines()).getX2(), (float) l.getLeftSide().getLineAt(l.leftSideNumLines()).getY2());
			}
			
			if (!l.topSideIsEmpty()) {
				int i;
				Line2D line;
				path = new GeneralPath();
				
				g2d.setColor(Color.ORANGE);
				for (i=1; (line = l.getTopSide().getLineAt(i)) != null; i++) {
					path.append(line, true);
					g2d.fill(new Ellipse2D.Double(line.getX1(), line.getY1(), 7.0, 7.0));
					if (l.getTopSide().getLineAt(i+1) == null) {
						g2d.fill(new Ellipse2D.Double(line.getX2(), line.getY2(), 7.0, 7.0));
						break;					
					}					
				}
				g2d.setColor(Color.BLACK);
				g2d.draw(path);			
			}
				
			if (!l.bottomSideIsEmpty()) {
				int i;
				Line2D line;
				path = new GeneralPath();
				
				g2d.setColor(Color.ORANGE);
				for (i=1; (line = l.getBottomSide().getLineAt(i)) != null; i++) {
					path.append(line, true);
					g2d.fill(new Ellipse2D.Double(line.getX1(), line.getY1(), 7.0, 7.0));
					if (l.getBottomSide().getLineAt(i+1) == null) {
						g2d.fill(new Ellipse2D.Double(line.getX2(), line.getY2(), 7.0, 7.0));
						break;					
					}					
				}
				g2d.setColor(Color.BLACK);
				g2d.draw(path);			;
			}

			//if (l.isC1Set) {
				//g2d.draw(l.getCapOne());
			//}

			//if (l.isC2Set) {
				//g2d.draw(l.getCapTwo());
				
			//}

			//g2d.draw(new Line2D.Double(x1,y1,x2,y1));
		}

		if (reset) {
			g.dispose();
			g2d.dispose();
			l.resetAllLines();
			modEndPoints = 0;
			reset = false;
			super.repaint();
		}
		
		//updateCordLabels();
			
			
	}

	public void mousePressed(MouseEvent e) {
		//check endpoint, check line.
		
		}
			

	public void mouseReleased(MouseEvent e) {
		hasDragged = false;
	}	

	public void mouseEntered(MouseEvent e) {
		mouseStuff(e);
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		
		oldX = e.getX();
		oldY = e.getY();
		
		if (!(modEndPoints > 0) ) {

		switch (l.getWhichSide()) {


		case 1:
					
			if (l.rightSideIsEmpty()) {
				if (l.lineRight.getX1() == 0.0 && l.lineRight.getY1() == 0.0) {
					l.lineRight.setLine(e.getX(), e.getY(), e.getX(), e.getY());
					
				}else  {
					l.lineRight.setLine(l.lineRight.getX1(), l.lineRight.getY1(), e.getX(), e.getY());
					System.out.println("LINE DATA, X1: " + l.lineRight.getX1() + "Y1: " + l.lineRight.getY1() + "X2: " + l.lineRight.getX2() + "Y2: " + l.lineRight.getY2());
					l.addLineRight();
												
				}
				
			}else {
				l.lineRight.setLine(l.getRightSide().getLineAt(l.getRightSide().getNumLines()).getX2(),l.getRightSide().getLineAt(l.getRightSide().getNumLines()).getY2(), e.getX(), e.getY());
				l.addLineRight();
				if (!l.bottomSideIsEmpty()) {
					l.getBottomSide().getLineAt(1).setLine(e.getX(), e.getY(), l.getBottomSide().getLineAt(1).getX2(), l.getBottomSide().getLineAt(1).getY2());
				} 
				
								
			}
			
			drawLine = true;
			mouseMoved(e);			
			super.repaint();
			break;

		case 2:	
			if (l.leftSideIsEmpty()) {
					if (l.lineLeft.getX1() == 0.0 && l.lineLeft.getY1() == 0.0) {
					l.lineLeft.setLine(e.getX(), e.getY(), e.getX(), e.getY());
					
				}else  {
					l.lineLeft.setLine(l.lineLeft.getX1(), l.lineLeft.getY1(), e.getX(), e.getY());
					l.addLineLeft();
												
				}
				
			}else {
				l.lineLeft.setLine(l.getLeftSide().getLineAt(l.getLeftSide().getNumLines()).getX2(),l.getLeftSide().getLineAt(l.getLeftSide().getNumLines()).getY2(), e.getX(), e.getY());
				l.addLineLeft();
				
								
			}
			
			drawLine = true;
			mouseMoved(e);			
			super.repaint();
			break;


		case 3:
			if (l.topSideIsEmpty()) {
					if (l.lineTop.getX1() == 0.0 && l.lineTop.getY1() == 0.0 && l.getRightSide().getLineAt(1) != null) {
						l.lineTop.setLine(l.getRightSide().getLineAt(1).getX1(), l.getRightSide().getLineAt(1).getY1(), l.getRightSide().getLineAt(1).getX1(), l.getRightSide().getLineAt(1).getY1());
						mouseClicked(e);
				}else  {
					l.lineTop.setLine(l.lineTop.getX1(), l.lineTop.getY1(), e.getX(), e.getY());
					l.addLineTop();
												
				}
				
			}else {
				l.lineTop.setLine(l.getTopSide().getLineAt(l.getTopSide().getNumLines()).getX2(),l.getTopSide().getLineAt(l.getTopSide().getNumLines()).getY2(), e.getX(), e.getY());
				l.addLineTop();
								
			}
			
			drawLine = true;
			mouseMoved(e);			
			super.repaint();
			break;
		

		case 4:
			
			if (l.bottomSideIsEmpty()) {
					if (l.lineBottom.getX1() == 0.0 && l.lineBottom.getY1() == 0.0 && l.getRightSide().getLineAt(1) != null) {
						l.lineBottom.setLine(l.getRightSide().getLineAt(l.rightSideNumLines()).getX2(), l.getRightSide().getLineAt(l.rightSideNumLines()).getY2(), l.getRightSide().getLineAt(l.rightSideNumLines()).getX2(), l.getRightSide().getLineAt(l.rightSideNumLines()).getY2());
						mouseClicked(e);
				}else  {
					
						l.lineBottom.setLine(l.lineBottom.getX1(), l.lineBottom.getY1(), e.getX(), e.getY());
						l.addLineBottom();
												
					}	
				
			}else {
				l.lineBottom.setLine(l.getBottomSide().getLineAt(l.getBottomSide().getNumLines()).getX2(),l.getBottomSide().getLineAt(l.getBottomSide().getNumLines()).getY2(), e.getX(), e.getY());
				l.addLineBottom();
								
			}
			
			drawLine = true;
			mouseMoved(e);			
			super.repaint();
			break;
								
	}
		super.repaint();
}		
			
		
	
}


	private void mouseStuff(MouseEvent e) {
		//cordLabel.setText(oldX + ", " + oldY);
		
	}

	public void mouseDragged(MouseEvent e) {
		if (modEndPoints > 0) {
			if (!hasDragged) {
				oldX = e.getX();
				oldY = e.getY();
				hasDragged = true;
			}
			
			l.getBottomSide().updateEndPoint((double) oldX, (double) oldY, (double) e.getX(), (double) e.getY());
			l.getRightSide().updateEndPoint((double) oldX, (double) oldY, (double) e.getX(), (double) e.getY()); 
			l.getTopSide().updateEndPoint((double) oldX, (double) oldY, (double) e.getX(), (double) e.getY());
			l.getLeftSide().updateEndPoint((double) oldX, (double) oldY, (double) e.getX(), (double) e.getY());
			
			oldX = e.getX();
			oldY = e.getY();
			drawLine = true;
			mouseMoved(e);
			super.repaint();
	}
		/****THIS IS THE OLD WAY, CONSTRICTS WHICH POINTS YOU CAN MODIFY, MAY BE USEFUL BUT IS NOT FULLY COMPLETE (END POINT CHECKS ON OVERLAPPING TOP/BOTTOM)
		if (modEndPoints == l.RIGHT_SIDE) {

			if (!hasDragged) {
			oldX = e.getX();
			oldY = e.getY();
			hasDragged = true;
			}

			System.out.println(l.getRightSide().endPointHitDetection(e.getX(), e.getY()));
			l.getRightSide().updateEndPoint((double)oldX, (double)oldY, (double) e.getX(),(double) e.getY());
			if (!l.topSideIsEmpty()) {
				l.getTopSide().updateEndPoint((double) oldX, (double)oldY, (double) e.getX(), (double) e.getY());
			  //if the hit point is on the first coordinate update the top line
			}

			if (!l.bottomSideIsEmpty()) {
				l.getBottomSide().updateEndPoint((double) oldX, (double)oldY, (double) e.getX(), (double) e.getY());;//if the hit point is on the last coodinate update the bottom line
			}

			oldX = e.getX();
			oldY = e.getY();
			drawLine = true;
			mouseMoved(e);			
			super.repaint();
			

		}else if (modEndPoints == l.LEFT_SIDE) {
			if (!hasDragged) {
				oldX = e.getX();
				oldY = e.getY();
				hasDragged = true;
			}
			
			l.getLeftSide().updateEndPoint((double) oldX, (double) oldY, (double) e.getX(), (double) e.getY());
			
			
			oldX = e.getX();
			oldY = e.getY();
			drawLine = true;
			mouseMoved(e);
			super.repaint();

		
		
		}else if (modEndPoints ==  l.TOP_SIDE) {
			if (!hasDragged) {
				oldX = e.getX();
				oldY = e.getY();
				hasDragged = true;
			}
			
			l.getTopSide().updateEndPoint((double) oldX, (double) oldY, (double) e.getX(), (double) e.getY());
			//l.getRightSide().updateEndPoint(l.getRightSide().getLineAt(1).getX1(), l.getRightSide().getLineAt(1).getY1(), (double) e.getX(), (double) e.getY()); //if we have updated 1st coord!
			
			oldX = e.getX();
			oldY = e.getY();
			drawLine = true;
			mouseMoved(e);
			super.repaint();
		
	} else if (modEndPoints ==  l.BOTTOM_SIDE) {
			if (!hasDragged) {
				oldX = e.getX();
				oldY = e.getY();
				hasDragged = true;
			}
			
			l.getBottomSide().updateEndPoint((double) oldX, (double) oldY, (double) e.getX(), (double) e.getY());
			System.out.println(l.getRightSide().lastEndPointDetected(oldX,oldY));
			if (l.getRightSide().lastEndPointDetected(oldX,oldY)) {
				l.getRightSide().updateEndPoint(l.getRightSide().getLineAt(l.rightSideNumLines()).getX2(), l.getRightSide().getLineAt(l.rightSideNumLines()).getY2(), (double) e.getX(), (double) e.getY());
			}			
			
			
			oldX = e.getX();
			oldY = e.getY();
			drawLine = true;
			mouseMoved(e);
			super.repaint();
	}
*/
}	

	
	public void mouseMoved(MouseEvent e) {

		//cordLabel.setText(oldX + ", " + oldY);		
		mouseStuff(e);
	} 

	public void writeOnClose() {
		xml.addNumSlides(mySlides.getCurrentSlideNum()-1
);
		xml.writeXmlFile();	
	}

	public void actionPerformed(ActionEvent event) {
		

		
		if (event.getActionCommand() == Wlt_Coordinates.BUTTON_MSG) {
							
				xml.addSlideElement(java.lang.Integer.toString(mySlides.getCurrentSlideNum()));
				xml.addPanelDimensions(java.lang.Integer.toString(mySlides.getCurrentSlideNum()), this.getWidth(), this.getHeight());
				xml.addImageElement(java.lang.Integer.toString(mySlides.getCurrentSlideNum()), mySlides.getCurrentImageName(), mySlides.getCurrentImage().getWidth(null), mySlides.getCurrentImage().getHeight(null) );
				xml.addSide("Right", l.getRightSide(), java.lang.Integer.toString(mySlides.getCurrentSlideNum() ) );
				xml.addSide("Left", l.getLeftSide(), java.lang.Integer.toString(mySlides.getCurrentSlideNum() ) );
				xml.addSide("Top", l.getTopSide(), java.lang.Integer.toString(mySlides.getCurrentSlideNum() ) );
				xml.addSide("Bottom", l.getBottomSide(), java.lang.Integer.toString(mySlides.getCurrentSlideNum() ) );
				xml.writeXmlFile();
				l.resetAllLines();
			
				if (mySlides.advanceSlide()) {
					
					super.repaint();
					Runtime r = Runtime.getRuntime();
					r.gc();

				}else {
					xml.addNumSlides(mySlides.getCurrentSlideNum());
					xml.writeXmlFile();
					System.exit(0);

				}
		}else if (event.getActionCommand() == Wlt_Coordinates.CLEAR) {
			reset = true;
			super.repaint();
		}else {
			System.out.println("EVENT PARAM: " + event.paramString());
			JComboBox cb = (JComboBox)event.getSource();
	      		String name = (String)cb.getSelectedItem();	
			if (name == Wlt_Coordinates.ADDRIGHT) {
				modEndPoints = 0;
				l.setWhichSide(l.RIGHT_SIDE);
				super.repaint();
			}else if (name == Wlt_Coordinates.ADDLEFT ) {
				modEndPoints = 0;
				l.setWhichSide(l.LEFT_SIDE);
				super.repaint();
			}else if (name == Wlt_Coordinates.ADDTOP) {
				modEndPoints = 0;
				l.setWhichSide(l.TOP_SIDE);
				super.repaint();
			}else if (name == Wlt_Coordinates.ADDBOTTOM) {
				modEndPoints = 0;
				l.setWhichSide(l.BOTTOM_SIDE);
				super.repaint();
			}else if (name == Wlt_Coordinates.MOD_END_POINTS) {
				 modEndPoints = 1;
			}else if (name == Wlt_Coordinates.SNAPTOP) {
				 modEndPoints = 0;
				 snapTop();
				 super.repaint();
			}else if (name == Wlt_Coordinates.SNAPBOTTOM) {
				 modEndPoints = 0;
				 snapBottom();
				super.repaint();
			}
		}		
	/*
					
		}else if (event.getActionCommand() == ADDRIGHT) {
				modEndPoints = 0;
				l.setWhichSide(l.RIGHT_SIDE);
		}else if (event.getActionCommand() == ADDLEFT) {
				modEndPoints = 0;
				l.setWhichSide(l.LEFT_SIDE);	
		}else if (event.getActionCommand() == MOD_END_POINTS){
				modEndPoints = 1;			
		}else if (event.getActionCommand() == SNAPTOP) {
				snapTop();
				super.repaint();
		}else if (event.getActionCommand() == SNAPBOTTOM) {
				snapBottom();
				super.repaint();
		}else if (event.getActionCommand() == MOD_RIGHT_POINTS){
				modEndPoints = 1;			
		}else if (event.getActionCommand() == MOD_LEFT_POINTS){
				modEndPoints = 2;
		}else if (event.getActionCommand() == ADDTOP) {
				modEndPoints = 0;
				l.setWhichSide(l.TOP_SIDE);
		}else if (event.getActionCommand() == ADDBOTTOM) {
				modEndPoints = 0;
				l.setWhichSide(l.BOTTOM_SIDE);
		}else if (event.getActionCommand() == MOD_TOP_POINTS) {
				modEndPoints = 3;
		}else if (event.getActionCommand() == MOD_BOTTOM_POINTS) {
				modEndPoints = 4;
		*/
		
		
	
}


	
	




}


