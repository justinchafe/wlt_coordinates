package Wlt_Coordinates;
import java.awt.geom.*;

public class LineManager {

	public final int RIGHT_SIDE = 1;
	public final int LEFT_SIDE = 2;
	public final int TOP_SIDE = 3;
	public final int BOTTOM_SIDE = 4;

	final double TOLERANCE = 0.05;
	final boolean DEBUG_ON = true;
	final int LINE_EXT = 2;
	private Line2D userLine;
	public Line2D lineLeft;
	public Line2D lineRight;
	public Line2D lineTop; //Top
	public Line2D lineBottom; //Bottom (Break into portion??))
	private Side right;
	private Side left;
	private Side top;
	private Side bottom;
	public int currentSide;
	private int first;

	public boolean isUserLineDrawn, isL1Set, isL2Set, isC1Set, isC2Set, l1EndOneSet, l1EndTwoSet, l2EndOneSet, l2EndTwoSet;
	private Point2D l1EndOne, l1EndTwo, l2EndOne, l2EndTwo;
	
	
//LineOne should be taken as the left most line if the bottle was standing upright
//LineTwo should be taken as the right most line if the bottle was standing upright
//CapOne is essentially the bottom of the bottle if the bottle was standing upright, with start marked from the bottom of LineOne
//CapTwo is essentially the top of the bottle if the bottle was standing upright, witht start marked from the top of LineTWo

	public LineManager(int currentSide) {
		
		/*this.lineOne = lineOne;
		this.lineTwo = lineTwo;
		capOne = new Line2D.Double(lineOne.getX2(), lineOne.getY2(), lineTwo.getX2(), lineTwo.getY2());
		capTwo = new Line2D.Double(lineOne.getX1(), lineOne.getY1(), lineTwo.getX1(), lineTwo.getY1());
		//MODEL THE BOTTLE!!!
		//set caps HERE!
		*/
		userLine = new Line2D.Double(0,0,0,0);
		first = 0;
		isUserLineDrawn = false;
		//isL1Set = isL2Set = isC1Set = isC2Set = false;
		//l1EndOneSet = l1EndTwoSet = l2EndOneSet = l2EndTwoSet = false;
	
		this.currentSide = currentSide;
		right = new Side();
		left = new Side();
		top = new Side();
		bottom = new Side();
		
		lineRight = new Line2D.Double(0,0,0,0);
		lineLeft = new Line2D.Double(0,0,0,0);
		lineTop = new Line2D.Double(0,0,0,0);
		lineBottom = new Line2D.Double(0,0,0,0);
		//capOne = new Line2D.Double(0,0,0,0);
		//capTwo = new Line2D.Double(0,0,0,0);
	
		
	}

	public Side getLeftSide() {
		return left;
	}

	public Side getRightSide() {
		return right;
	}

	public Side getTopSide() {
		return top;
	}

	public Side getBottomSide() {
		return bottom;
	}

	public boolean topSideIsEmpty() {
		if (top.getNumLines() == 0) {
			System.out.println("EMPTY TOP");
			return true;
		}else 
			return false;
	}
	
	public boolean bottomSideIsEmpty() {
		if (bottom.getNumLines() == 0) {
			System.out.println("EMPTY BOTTOM");
			return true;
		}else 
			return false;
	}

	public boolean rightSideIsEmpty() {
		if (right.getNumLines() == 0) {
			System.out.println("EMPTY RIGHT");
			return true;
		}else 
			return false;
	}

	public boolean leftSideIsEmpty() {
		if (left.getNumLines() == 0) {
			System.out.println("EMPTY LEFT");
			return true;
		}else 
			return false;
		
	}

	public int rightSideNumLines() {
		return right.getNumLines();
	}

	public int topSideNumLines() {
		return top.getNumLines();
	}

	public int bottomSideNumLines() {
		return bottom.getNumLines();
	}

	public int leftSideNumLines() {
		return left.getNumLines();
	}

	public void addLineRight() {
		right.addLine(new Line2D.Double(lineRight.getX1(), lineRight.getY1(), lineRight.getX2(), lineRight.getY2()));
	}
	
	public void addLineLeft() {
		left.addLine(new Line2D.Double(lineLeft.getX1(), lineLeft.getY1(), lineLeft.getX2(), lineLeft.getY2()));
		
	}

	public void addLineTop() {
		top.addLine(new Line2D.Double(lineTop.getX1(), lineTop.getY1(), lineTop.getX2(), lineTop.getY2()));
	}

	public void addLineBottom() {
		bottom.addLine(new Line2D.Double(lineBottom.getX1(), lineBottom.getY1(), lineBottom.getX2(), lineBottom.getY2()));
	}
	
	public void setWhichSide(int l) {
		currentSide = l;	
	}
	
	public int getWhichSide() {
		return currentSide;
	}

	
	
	public Line2D getUserLine() {
		return userLine;
	}
	//updateSideRight(x1,..)
		
	 //sideOne.addFirstLine(new Line2D.double(x,y,x1,y1)
		//if (there is more than one line)
			//update the first endpoint of the second line to the endpoint of the first line
			//i.e (getLineAt(2)).setLine(getLineAt(1).X, getLineAt(1).y, getLineAt(2).X, getLineAt(2).y)
		
	/*	
	public void setCaps() {
		if (isL1Set && isL2Set) {
			//the endpoint o
			capOne.setLine(lineOne.getX1(), lineOne.getY1(), lineTwo.getX1(), lineTwo.getY1());
			isC1Set = true;
			capTwo.setLine(lineOne.getX2(), lineOne.getY2(), lineTwo.getX2(), lineTwo.getY2());
			isC2Set = true;
		}else
			return;
	}

	public void updateCaps() {
		if (isC1Set && isC2Set) {
			setCapOne();
			setCapTwo();
		}else if(isC1Set) {
			setCapOne();
		}else if(isC2Set) {
			setCapTwo();
		}
	}

	public void setCapOne() {
		if (isL1Set && isL2Set) {
			capOne.setLine(lineOne.getX1(), lineOne.getY1(), lineTwo.getX1(), lineTwo.getY1());
			isC1Set = true;
		}
	}

	public void setCapTwo() {
		if (isL1Set && isL2Set) {
			capTwo.setLine(lineOne.getX2(), lineOne.getY2(), lineTwo.getX2(), lineTwo.getY2());
			isC2Set = true;
		}
	}
*/
/*
	public Line2D getCapOne() {

		return capOne;
	}

	public Line2D getCapTwo() {
		return capTwo;
	}
*/
	/*private void setCaps() {
		if (lineOne.getY2() > lineTwo.getY2()) {
			capOne = new Line2D.Double(lineOne.getX2(), lineOne.getY2(), lineTwo.getX2(), lineTwo.getY2());
		}else
			capOne = new Line2D.Double(lineTwo.getX2(), lineTwo.getY2(), lineOne.getX2(), lineTwo.getY2());
		
		if (lineOne.getY1() > lineTwo.getY1()) {
			capTwo = new Line2D.Double(lineOne.getX1(), lineOne.getY1(), lineTwo.getX1(), lineTwo.getY1());
		}else
			capTwo = new Line2D.Double(lineTwo.getX1(), lineTwo.getY1(), lineOne.getX1(), lineOne.getY1());

	}
*/

	//Set new ENDPOINTS:
	public void setUserLine(double x2, double y2) {
		userLine.setLine(userLine.getX1(), userLine.getY1(), x2, y2);
	}

	public void setUserLine(double x1, double y1, double x2, double y2) {
		userLine.setLine(x1,y1,x2,y2);		
	}

	public void resetAllLines() {
			//lineLeft = new Line2D.Double(0,0,0,0);
			//lineRight = new Line2D.Double(0,0,0,0);	
			//capOne = new Line2D.Double(0,0,0,0);
			//capTwo = new Line2D.Double(0,0,0,0);
			right.clearAllLines();
			left.clearAllLines();
			top.clearAllLines();
			bottom.clearAllLines();
			lineLeft = new Line2D.Double(0,0,0,0);
			lineRight = new Line2D.Double(0,0,0,0);
			lineTop = new Line2D.Double(0,0,0,0);
			lineBottom = new Line2D.Double(0,0,0,0);
			resetUserLine();
			
	}


	public void resetUserLine() {
		userLine.setLine(0,0,0,0);
		first = 0;
		isUserLineDrawn = false;
	}

	

	public double getAngle() {
		
		return ( Math.atan2(userLine.getY1() - userLine.getY2(), userLine.getX2() - userLine.getX1()) ) * (180/Math.PI);
	}

	public boolean isUserLineDrawn() {
		return isUserLineDrawn;
	}

	public void updateLines(Line2D lineLeft, Line2D lineRight) {
		this.lineLeft = lineLeft;
		this.lineRight = lineRight;
		//capOne.setLine(lineRight.getX2(), lineRight.getY2(), lineRight.getX2(), lineRight.getY2());
		//capTwo.setLine(lineLeft.getX1(), lineLeft.getY1(), lineLeft.getX1(), lineLeft.getY1());
		
	}
	
	/**
	**This method will set the start point.  It is a helper method used for moving the start point of the userLine to a point on the line.  
	**It is needed so that we can detect "hits" that are within a specfied hit detection range which may not be "on the line".  Thus it checks
	**the nearest path to the line in question, finds the intersection point and sets the userLine start coordinates to the intersection point.
	**/

/*
	private void setStartPoint(double x, double y, double qo, double ax, double ay, double bx, double by, Line2D line) {
		Point2D.Double point;		
		if (qo < (0.0+TOLERANCE) || qo > (0.0-TOLERANCE)) {
			;
			
		}else if (ax < ay && ax < bx && ax < by) {
				//decrease until we hit WLT.LINE_DETECT
				System.out.println("ax is the closest, value: " + ax);
				System.out.println("value of qo: " + qo);
				userLine.setLine(x,y,x + qo*2,y);
				point = getIntersectionPoint(line, userLine);
				if (point != null ) {
					x = point.getX();
					y = point.getY();
				} 
				else {
					userLine.setLine(x+qo,y,x+qo,y);
				}
				//userLine.setLine(point.getX(), point.getY(), point.getX(), point.getY());
				System.out.println("new value of x coordinate: " + x);
				System.out.println("recheck distance: " + line.ptSegDist(x,y));
				
						
		}else if (ay < ax && ay < bx && ay < by) {
				//decrease until we hit WLT.LINE_DETECT
				System.out.println("ay is the closest, value: " + ax);
				System.out.println("value of qo: " + qo);
				//GET INTERSECTION POINT THIS IS THE NEW COORDINATES!!
				userLine.setLine(x,y,x,y + qo*2);
				point = getIntersectionPoint(line, userLine);
				if (point != null ) {
					x = point.getX();
					y = point.getY();
				} 
				else {
					userLine.setLine(x,y+qo,x,y+qo);
				}
				//userLine.setLine(point.getX(), point.getY(), point.getX(), point.getY());
				System.out.println("new value of y coordinate: " + y);
				System.out.println("recheck distance: " + line.ptSegDist(x,y));

		}else if (bx < ax && bx < ay && bx < by) {
				System.out.println("bx is the closest, value: " + bx);
				System.out.println("value of qo: " + qo);
				userLine.setLine(x,y,x - qo*2,y);
				point = getIntersectionPoint(line, userLine);
				if (point != null ) {
					x = point.getX();
					y = point.getY();
				} 
				else {
					userLine.setLine(x-qo,y,x-qo,y);
				}
						
				System.out.println("new value of x coordinate: " + x);
				System.out.println("recheck distance: " + line.ptSegDist(x,y));

		}else if (by < ax && by < ay && by < bx) {
				System.out.println("by is the closest, value: " + by);
				System.out.println("value of qo: " + qo);
				userLine.setLine(x,y,x,y - qo*2);
				point = getIntersectionPoint(line, userLine);
				if (point != null) {
					x = point.getX();
					y = point.getY();
				}
				else {
					userLine.setLine(x,y-qo,x,y-qo);
				}
				System.out.println("new value of y coordinate: " + y);
				System.out.println("recheck distance: " + line.ptSegDist(x,y));
		}else {	
				System.out.println("your logic is flawed");
		}
										
	userLine.setLine(x,y,x,y);
		
		
	}
	
	/**
	Check to see if the x,y coordinates fall on a line.  If the user has never clicked on either line and we are within the correct distance, then...we mark that we clicked 
	this line first.  Otherwise, we check to see if we have clicked close enough to the opposite line.  If so we set the userLine to the appropriate coordiantes. 
	-retruns True if we have never clicked on a line and we are close enough to one & updates userLine
	-retruns True if we have clicked on a line and are close enough to the opposite line & updates userLine
	-returns False if we are not near enough to a line
	*
	**/	

/* 
	public boolean lineHitDetection(double x, double y) {
		Point2D.Double point;
		double qo, ax, ay, bx, by;
		int i = 1;

		switch (first) {

			case 0:
				qo = lineOne.ptSegDist(x,y);
				if (qo <= Wlt.LINE_DETECT) {
					ax = lineOne.ptSegDist(x+i, y);
					ay = lineOne.ptSegDist(x, y+i);
					bx = lineOne.ptSegDist(x -i, y);
					by = lineOne.ptSegDist(x, y-i);
					setStartPoint(x,y,qo,ax,ay,bx,by,lineOne);
					first = 1;
					return true;
				}

				qo = lineTwo.ptSegDist(x,y);
				if (qo <= Wlt.LINE_DETECT) {
					ax = lineTwo.ptSegDist(x+i, y);
					ay = lineTwo.ptSegDist(x, y+i);
					bx = lineTwo.ptSegDist(x -i, y);
					by = lineTwo.ptSegDist(x, y-i);
					setStartPoint(x,y,qo,ax,ay,bx,by,lineTwo);								
					first = 2;
					return true;
				}	
				
				qo = capOne.ptSegDist(x,y);
				if (qo <= Wlt.LINE_DETECT) {
					ax = capOne.ptSegDist(x+i, y);
					ay = capOne.ptSegDist(x, y+i);
					bx = capOne.ptSegDist(x -i, y);
					by = capOne.ptSegDist(x, y-i);
					setStartPoint(x,y,qo,ax,ay,bx,by,capOne);
					first = 3;
					return true;
				}

				qo = capTwo.ptSegDist(x,y);
				if (qo <= Wlt.LINE_DETECT) {
					ax = capTwo.ptSegDist(x+i, y);
					ay = capTwo.ptSegDist(x, y+i);
					bx = capTwo.ptSegDist(x -i, y);
					by = capTwo.ptSegDist(x, y-i);
					setStartPoint(x,y,qo,ax,ay,bx,by,capTwo);
					first = 4;
					return true;
				}
				
				break;
	
			case 1:
			
				if (lineTwo.ptSegDist(x,y) <= Wlt.LINE_DETECT) {
					userLine.setLine(userLine.getX1(),userLine.getY1(),x,y); //May not be needed!!!!
					System.out.println("ARGHHHHH: " + userLine.getX1() + ", " + userLine.getX2());
					extendLine(); //SHOULD RETURN x,y coordinates!!!
					point = getIntersectionPoint(lineTwo, userLine);
					if (point != null) {
						setUserLine(point.getX(), point.getY());
					}
					showAngleDebugInfo(); //DEBUGGING
					isUserLineDrawn = true;					
					return true;		
	
				}else if (capOne.ptSegDist(x,y) <= Wlt.LINE_DETECT) {
						userLine.setLine(userLine.getX1(),userLine.getY1(),x,y); 
						System.out.println("ARGHHHHH: " + userLine.getX1() + ", " + userLine.getX2());
						extendLine(); //SHOULD RETURN x,y coordinates!!!
						point = getIntersectionPoint(capOne, userLine);
						if (point != null) {
							setUserLine(point.getX(), point.getY());
						}
						showAngleDebugInfo(); 
						isUserLineDrawn = true;					
						return true;	

				}else if (capTwo.ptSegDist(x,y) <= Wlt.LINE_DETECT) {
						userLine.setLine(userLine.getX1(),userLine.getY1(),x,y); 
						System.out.println("ARGHHHHH: " + userLine.getX1() + ", " + userLine.getX2());
						extendLine(); //SHOULD RETURN x,y coordinates!!!
						point = getIntersectionPoint(capTwo, userLine);
						if (point != null) {
							setUserLine(point.getX(), point.getY());
						}
						showAngleDebugInfo(); 
						isUserLineDrawn = true;					
						return true;	
				}		
						

					/**
				***CODE TO MAKE THE START POINT MOVEABLE (GLOBAL BOOLEAN FLAG TO TURN ON?)
				else if (lineOne.ptSegDist(x,y) <= Wlt.LINE_DETECT) {
						qo = lineOne.ptSegDist(x,y);
					 	ax = lineOne.ptSegDist(x+i, y);
						ay = lineOne.ptSegDist(x, y+i);
						bx = lineOne.ptSegDist(x -i, y);
						by = lineOne.ptSegDist(x, y-i);
						double tx2, ty2;
						tx2 = userLine.getX2();
						ty2 = userLine.getY2();
						setStartPoint(x,y,qo,ax,ay,bx,by,lineOne);
						setUserLine(tx2,ty2);
						return true;
				} 
			 	break;	
	
			case 2:
				
				if (lineOne.ptSegDist(x,y) <= Wlt.LINE_DETECT) {
					userLine.setLine(userLine.getX1(),userLine.getY1(), x, y);
					System.out.println("ARGHHHHH: " + userLine.getX1() + ", " + userLine.getX2());
					extendLine();
					point = getIntersectionPoint(lineOne, userLine);
					if (point != null) {
						setUserLine(point.getX(), point.getY());
					}
					showAngleDebugInfo(); 			
					isUserLineDrawn = true;					
					return true;

				}else if (capOne.ptSegDist(x,y) <= Wlt.LINE_DETECT) {
						userLine.setLine(userLine.getX1(),userLine.getY1(),x,y); 
						System.out.println("ARGHHHHH: " + userLine.getX1() + ", " + userLine.getX2());
						extendLine(); //SHOULD RETURN x,y coordinates!!!
						point = getIntersectionPoint(capOne, userLine);
						if (point != null) {
							setUserLine(point.getX(), point.getY());
						}
						showAngleDebugInfo(); 
						isUserLineDrawn = true;					
						return true;	

				}else if (capTwo.ptSegDist(x,y) <= Wlt.LINE_DETECT) {
						userLine.setLine(userLine.getX1(),userLine.getY1(),x,y); 
						System.out.println("ARGHHHHH: " + userLine.getX1() + ", " + userLine.getX2());
						extendLine(); //SHOULD RETURN x,y coordinates!!!
						point = getIntersectionPoint(capTwo, userLine);
						if (point != null) {
							setUserLine(point.getX(), point.getY());
						}
						showAngleDebugInfo(); 
						isUserLineDrawn = true;					
						return true;	
				}
				
				break;

			case 3:
				if (lineOne.ptSegDist(x,y) <= Wlt.LINE_DETECT) {
					userLine.setLine(userLine.getX1(),userLine.getY1(), x, y);
					System.out.println("ARGHHHHH: " + userLine.getX1() + ", " + userLine.getX2());
					extendLine();
					point = getIntersectionPoint(lineOne, userLine);
					if (point != null) {
						setUserLine(point.getX(), point.getY());
					}
					showAngleDebugInfo(); 		
					isUserLineDrawn = true;					
					return true;

				}else if (lineTwo.ptSegDist(x,y) <= Wlt.LINE_DETECT) {
						userLine.setLine(userLine.getX1(),userLine.getY1(),x,y); 
						System.out.println("ARGHHHHH: " + userLine.getX1() + ", " + userLine.getX2());
						extendLine(); //SHOULD RETURN x,y coordinates!!!
						point = getIntersectionPoint(lineTwo, userLine);
						if (point != null) {
							setUserLine(point.getX(), point.getY());
						}
						showAngleDebugInfo(); 
						isUserLineDrawn = true;					
						return true;	

				}else if (capTwo.ptSegDist(x,y) <= Wlt.LINE_DETECT) {
						userLine.setLine(userLine.getX1(),userLine.getY1(),x,y); 
						System.out.println("ARGHHHHH: " + userLine.getX1() + ", " + userLine.getX2());
						extendLine(); //SHOULD RETURN x,y coordinates!!!
						point = getIntersectionPoint(capTwo, userLine);
						if (point != null) {
							setUserLine(point.getX(), point.getY());
						}
						showAngleDebugInfo(); 
						isUserLineDrawn = true;					
						return true;	
				}		
				break;
		
			case 4:
				if (lineOne.ptSegDist(x,y) <= Wlt.LINE_DETECT) {
					userLine.setLine(userLine.getX1(),userLine.getY1(), x, y);
					System.out.println("ARGHHHHH: " + userLine.getX1() + ", " + userLine.getX2());
					extendLine();
					point = getIntersectionPoint(lineOne, userLine);
					if (point != null) {
						setUserLine(point.getX(), point.getY());
					}
					showAngleDebugInfo(); 			
					isUserLineDrawn = true;					
					return true;

				}else if (lineTwo.ptSegDist(x,y) <= Wlt.LINE_DETECT) {
						userLine.setLine(userLine.getX1(),userLine.getY1(),x,y); 
						System.out.println("ARGHHHHH: " + userLine.getX1() + ", " + userLine.getX2());
						extendLine(); //SHOULD RETURN x,y coordinates!!!
						point = getIntersectionPoint(lineTwo, userLine);
						if (point != null) {
							setUserLine(point.getX(), point.getY());
						}
						showAngleDebugInfo(); 
						isUserLineDrawn = true;					
						return true;	

				}else if (capOne.ptSegDist(x,y) <= Wlt.LINE_DETECT) {
						userLine.setLine(userLine.getX1(),userLine.getY1(),x,y); 
						System.out.println("ARGHHHHH: " + userLine.getX1() + ", " + userLine.getX2());
						extendLine(); //SHOULD RETURN x,y coordinates!!!
						point = getIntersectionPoint(capOne, userLine);
						if (point != null) {
							setUserLine(point.getX(), point.getY());
						}
						showAngleDebugInfo(); 
						isUserLineDrawn = true;					
						return true;	
				}	
				break;

			default:
				return false;

		}//end switch
		return false;
	}

	public boolean intersectionCheck(double x, double y) {
		Point2D.Double point;
		switch (first) {

			case 0: 
				return false;
				
			
			case 1:
				userLine.setLine(userLine.getX1(), userLine.getY1(),x, y);			
				if (userLine.intersectsLine(lineTwo)) {
					point = getIntersectionPoint(lineTwo, userLine);
					if (point != null) {
						userLine.setLine(userLine.getX1(), userLine.getY1(), point.getX(), point.getY());
					}else {
					}
					isUserLineDrawn = true;	
					return true;
					
				
				}else if (userLine.intersectsLine(capOne)) {
					point = getIntersectionPoint(capOne, userLine);
					if (point != null) {
						userLine.setLine(userLine.getX1(), userLine.getY1(), point.getX(), point.getY());
					}else {
					}
					isUserLineDrawn = true;	
					return true;
					
				}else if (userLine.intersectsLine(capTwo)) {
					point = getIntersectionPoint(capTwo, userLine);
					if (point != null) {
						userLine.setLine(userLine.getX1(), userLine.getY1(), point.getX(), point.getY());
					}else {
					}
					isUserLineDrawn = true;	
					return true;
					
				}else
					break;	
				

			case 2:
				userLine.setLine(userLine.getX1(), userLine.getY1(), x, y);
				if (userLine.intersectsLine(lineOne)) {
					point = getIntersectionPoint(lineOne, userLine);
					if (point != null) {
						userLine.setLine(userLine.getX1(), userLine.getY1(), point.getX(), point.getY());
					}else {
					}
					isUserLineDrawn = true;
					return true;
				
				}else if (userLine.intersectsLine(capOne)) {
					point = getIntersectionPoint(capOne, userLine);
					if (point != null) {
						userLine.setLine(userLine.getX1(), userLine.getY1(), point.getX(), point.getY());
					}else {
					}
					isUserLineDrawn = true;	
					return true;

				}else if (userLine.intersectsLine(capTwo)) {
					point = getIntersectionPoint(capTwo, userLine);
					if (point != null) {
						userLine.setLine(userLine.getX1(), userLine.getY1(), point.getX(), point.getY());
					}else {
					}
					isUserLineDrawn = true;	
					return true;
				}else			
					break;
			case 3:

				userLine.setLine(userLine.getX1(), userLine.getY1(), x, y);
				if (userLine.intersectsLine(lineOne)) {
					point = getIntersectionPoint(lineOne, userLine);
					if (point != null) {
						userLine.setLine(userLine.getX1(), userLine.getY1(), point.getX(), point.getY());
					}else {
					}
					isUserLineDrawn = true;
					return true;
				
				}else if (userLine.intersectsLine(lineTwo)) {
					point = getIntersectionPoint(lineTwo, userLine);
					if (point != null) {
						userLine.setLine(userLine.getX1(), userLine.getY1(), point.getX(), point.getY());
					}else {
					}
					isUserLineDrawn = true;	
					return true;

				}else if (userLine.intersectsLine(capTwo)) {
					point = getIntersectionPoint(capTwo, userLine);
					if (point != null) {
						userLine.setLine(userLine.getX1(), userLine.getY1(), point.getX(), point.getY());
					}else {
					}
					isUserLineDrawn = true;	
					return true;
				}else			
					break;

			case 4:
				
				userLine.setLine(userLine.getX1(), userLine.getY1(), x, y);
				if (userLine.intersectsLine(lineOne)) {
					point = getIntersectionPoint(lineOne, userLine);
					if (point != null) {
						userLine.setLine(userLine.getX1(), userLine.getY1(), point.getX(), point.getY());
					}else {
					}
					isUserLineDrawn = true;
					return true;
				
				}else if (userLine.intersectsLine(lineTwo)) {
					point = getIntersectionPoint(lineTwo, userLine);
					if (point != null) {
						userLine.setLine(userLine.getX1(), userLine.getY1(), point.getX(), point.getY());
					}else {
					}
					isUserLineDrawn = true;	
					return true;

				}else if (userLine.intersectsLine(capOne)) {
					point = getIntersectionPoint(capOne, userLine);
					if (point != null) {
						userLine.setLine(userLine.getX1(), userLine.getY1(), point.getX(), point.getY());
					}else {
					}
					isUserLineDrawn = true;	
					return true;
				}else			
					break;

			default:
				isUserLineDrawn = false;
				return false;
			
		}
		isUserLineDrawn = false;
		return false;
			
	}

	public int getFirst() {
		return first;
	}

	private void extendLine() {
		double x, y, len, factor;
		Point2D p;
		x = userLine.getX2() - userLine.getX1();
		y = userLine.getY2() - userLine.getY1();
		double angle = Math.atan2(userLine.getY1() - userLine.getY2(), userLine.getX2() - userLine.getX1());
		len = Math.sqrt(x*x + y*y);
		System.out.println("LEN: " + len);
		factor = len*LINE_EXT;
		System.out.println("Factor: " + factor);
		len = factor / len;
		System.out.println("New LEN: " + len);
		x = userLine.getX1() + (userLine.getX2() - userLine.getX1()) * len;
		y = userLine.getY1() + (userLine.getY2() - userLine.getY1()) * len;
		System.out.println("OLD X2: " + userLine.getX2());
		System.out.println("OLD Y2: " + userLine.getY2());		
		System.out.println("NEW X2: " + x);
		System.out.println("NEW Y2: " + y);
		setUserLine(x,y);
	
		//setUserLine( (Math.sin(angle) * (length*2)) + userLine.getX1(), (Math.cos(angle) * (length*2)) + userLine.getY1() );

	}
	
	


	private Point2D.Double getIntersectionPoint(Line2D line1, Line2D line2) {
   		 if (! line1.intersectsLine(line2) ) return null;
     			
			double px = line1.getX1(),
            		py = line1.getY1(),
            		rx = line1.getX2()-px,
            		ry = line1.getY2()-py;

      			double qx = line2.getX1(),
            		qy = line2.getY1(),
            		sx = line2.getX2()-qx,
            		sy = line2.getY2()-qy;
 
     			double det = sx*ry - sy*rx;
      			if (det == 0) {
        			return null;
      			} else {
        			double z = (sx*(qy-py)+sy*(px-qx))/det;
        			if (z==0 ||  z==1) 
					return null;  // intersection at end point!
      				return new Point2D.Double((px+z*rx),(py+z*ry));
     			 }

 	} 

	private void showAngleDebugInfo() {
		if (DEBUG_ON) {
		System.out.println((Math.atan2(userLine.getY1() - userLine.getY2(), userLine.getX2() - userLine.getX1()))*(180/Math.PI));
		System.out.println(getAngle());
		}
	}
/*
	public static void main(String[] args) {
		LineManager l = new LineManager(new Line2D.Double(181, 213.0, 351.0, 503.0), new Line2D.Double(437.0, 70.0, 602.0, 359.0));
		System.out.println(l.getUserLine().getX1() + ", " + l.getUserLine().getY1());
		System.out.println(l.getLineTwo().getX1() + ", " + l.getLineTwo().getY1());
		double x, y;
		x = 181;
		y = 213.0;
		System.out.println(l.lineHitDetection(x,y));
		x = 500.0;
		y = 381.0;
		System.out.println("First: " + l.getFirst());
		System.out.println(l.getUserLine().getX1() + ", " + l.getUserLine().getY1());
		System.out.println(l.intersectionCheck(x,y));
		System.out.println(l.getUserLine().getX1() + ", " + l.getUserLine().getY1() + ", " + l.getUserLine().getX2() + ", " + l.getUserLine().getY2());
		


	
	
	}
*/
}

