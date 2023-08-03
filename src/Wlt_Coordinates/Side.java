package Wlt_Coordinates;
//Sides class - July 12th 2010

import java.util.ArrayList;
import java.awt.geom.*;

//NOTE: DO NOT USE THIS CLASS IN THE FINAL VERSION AS IT IS REDUNDANT --> MOVE ARRAYS AND HIT DETECTION TO LineManager CLASS.  

public class Side {

	private final double ENDPOINT_DIST = 10.0;
	private int numLines; //number of lines we have added.
	ArrayList<Line2D> lineList; //we will add lines to this vector list.

	public Side() {
		numLines= 0;
		lineList = new ArrayList<Line2D>(numLines);
	}

	public Side(Line2D line) {
		numLines = 0;
		lineList = new ArrayList<Line2D>(numLines);
		lineList.add(numLines++, line);

	}

	public Line2D getLine() {
		if (numLines != 0) {
			return lineList.get(numLines-1);
		}else
			return null;
	}

	public void addLine(Line2D line) {
		lineList.add(numLines++, line);
		
	}

	
	//removes last line added
	public void removeLastLine() {
		if (numLines != 0) {
		lineList.remove(--numLines);
		}
	}

	public void clearAllLines() {
		numLines = 0;
		lineList.clear();
	}

	public int getNumLines() {
		return numLines;
	}
	
	public Line2D getLineAt(int index) {
		if (index <= numLines) {
			return lineList.get(index-1);
		}
		else 
			return null;
	}

	//Checks to see if x,y intesect any line in this Side.
	//returns the Line which it intersects OR  null if x,y doesn't intersect.
	public Line2D lineHitDetection(double x, double y) {
		double qo;		
		int i = 1;
		int k = 0;
		Line2D currentLine;
		
		while (k<numLines) {
			currentLine = lineList.get(k++);
			qo = currentLine.ptSegDist(x,y);
			if (qo <= Wlt_Coordinates.LINE_DETECT) {
				return currentLine;
			}
		
		}
		return null;
	}

	//returns true if the endpoint was detected. Returns zero if nothing is detected.
	public boolean endPointHitDetection(double x, double y) {
		Point2D e1;
		Point2D e2;
		int i = 1;
		int k = 0;
		Line2D currentLine;
		while (k<numLines) {
			currentLine = lineList.get(k++);
			e1 = new Point2D.Double(currentLine.getX1(), currentLine.getY1());
			System.out.println(e1.getX() + ", " + e1.getY());
			System.out.println(e1.distance(x,y));
			e2 = new Point2D.Double(currentLine.getX2(), currentLine.getY2());
			System.out.println(e2.getX() + ", " + e2.getY());
			System.out.println(e2.distance(x,y));
			if (e1.distance(x,y) < ENDPOINT_DIST || e2.distance(x,y) < ENDPOINT_DIST)  {
				return true;
			}
			
		}
		return false;
				
	}

	public boolean lastEndPointDetected(double x, double y) {
		Line2D currentLine;
		Point2D p;
		if (numLines > 0) {
			currentLine = lineList.get(numLines-1);		
			p = new Point2D.Double(currentLine.getX2(), currentLine.getY2());
			if (p.distance(x,y) < ENDPOINT_DIST) {
				return true;
			}else 
				return false;
		}else
			return false;	
	}

	public boolean firstEndPointDetected(double x, double y) {
		Line2D currentLine;
		Point2D p;
		if (numLines > 0) {
			currentLine = lineList.get(0);		
			p = new Point2D.Double(currentLine.getX1(), currentLine.getY1());
			if (p.distance(x,y) < ENDPOINT_DIST) {
				return true;
			}else 
				return false;
		}else
			return false;	
	}

	



	//checks to see if the endpoints match any in this side, if they do it updates the endpoints in the side accordingly
	//return false if endpoints are not found (or could not be updated).
	public boolean updateEndPoint(double x, double y, double newX, double newY) {
			
			if (!endPointHitDetection(x,y)) {
				System.out.println("did not find it??");
				return false;		
			}else {
				Point2D e1;
				Point2D e2;
				int i = 0;
				int k = 0;
				Line2D currentLine;
				while (k<numLines) {
					currentLine = lineList.get(k);
					e1 = new Point2D.Double(currentLine.getX1(), currentLine.getY1());
					e2 = new Point2D.Double(currentLine.getX2(), currentLine.getY2());
					if (e1.distance(x,y) < ENDPOINT_DIST) {
						if (k == 0) {
							currentLine.setLine(newX, newY, currentLine.getX2(), currentLine.getY2());
							return true;						
						}else {
							currentLine.setLine(newX, newY, currentLine.getX2(), currentLine.getY2());
							currentLine = lineList.get(k-1);
							currentLine.setLine(currentLine.getX1(), currentLine.getY1(), newX, newY);
							return true;
						}
					}else if (e2.distance(x,y) < ENDPOINT_DIST && k == numLines-1) {
							currentLine.setLine(currentLine.getX1(), currentLine.getY1(), newX, newY); //last line endpoint 2.
							return true;	
					}
					k++;
				}
			}
			return false;
	}
		
	

	public static void main(String[] args) {
		Line2D l1 = new Line2D.Double(1,2,4,4);
		Line2D l2 = new Line2D.Double(4,4,7,7);
		Line2D l3 = new Line2D.Double(7,7,10,10);
		Side s = new Side();
		s.addLine(l1);
		System.out.println("Num Lines after adding one: " + s.getNumLines());
		s.addLine(l2);
		System.out.println("Num Lines after adding another: "  + s.getNumLines());
		s.removeLastLine();
		System.out.println("Num Lines after removing Line: " + s.getNumLines());
		s.removeLastLine();
		System.out.println("Num Lines after removing Line: " + s.getNumLines());
		s.removeLastLine();
		System.out.println("Num Lines after removing Line: " + s.getNumLines());
		s.addLine(l3);
		System.out.println("Num Lines after adding a Line: " + s.getNumLines());
		s.addLine(l1);
		s.clearAllLines();
		System.out.println("All Lines Cleared: " + s.getNumLines());
		s.addLine(l1);
		s.addLine(l2);
		s.addLine(l3);
		System.out.println("Num Lines: " + s.getNumLines());
		//s.clearAllLines();
		Line2D l4;// = s.lineHitDetection(1,2);
		/*
		if (l4 != null) {
			System.out.println("Line data: " + l4.toString() + " Get X1: " + l4.getX1() + ", Get Y1: " + l4.getY1() + ", Get X2: " + l4.getX2() + ", Get Y2: " + l4.getY2());
		} 
		l4 = s.lineHitDetection(6,6);
		if (l4 != null) {
			System.out.println("Line data: " + l4.toString() + " Get X1: " + l4.getX1() + ", Get Y1: " + l4.getY1() + ", Get X2: " + l4.getX2() + ", Get Y2: " + l4.getY2());
		}
		l4 = s.lineHitDetection(9,9);
		 if (l4 != null) {
				System.out.println("Line data: " + l4.toString() + " Get X1: " + l4.getX1() + ", Get Y1: " + l4.getY1() + ", Get X2: " + l4.getX2() + ", Get Y2: " + l4.getY2());
		}
		*/
//		System.out.println(s.endPointHitDetection(2,2));
//		System.out.println(s.endPointHitDetection(7,7));
		System.out.println(s.updateEndPoint(1,1,6,7));
		l4 = s.getLineAt(1);
		System.out.println("Line data: " + l4.toString() + " Get X1: " + l4.getX1() + ", Get Y1: " + l4.getY1() + ", Get X2: " + l4.getX2() + ", Get Y2: " + l4.getY2());
		l4 = s.getLineAt(2);		
		System.out.println("Line data: " + l4.toString() + " Get X1: " + l4.getX1() + ", Get Y1: " + l4.getY1() + ", Get X2: " + l4.getX2() + ", Get Y2: " + l4.getY2());	
		l4 = s.getLineAt(3);		
		System.out.println("Line data: " + l4.toString() + " Get X1: " + l4.getX1() + ", Get Y1: " + l4.getY1() + ", Get X2: " + l4.getX2() + ", Get Y2: " + l4.getY2());	
		
		
	}

}	
	
//NOT GOING TO USE THIS APPROACH, LEAVING FOR CLEANUP LATER:
/*
public boolean lineHitDetection(double x, double y) {
		Point2D.Double point;
		double qo, ax, ay, bx, by;
		int i = 1;
		int k = 0;
		Line2D currentLine;
		
		while (k<numLines) {
			currentLine = lineList.get(k++);
			qo = currentLine.ptSegDist(x,y);
			if (qo <= Wlt.LINE_DETECT) {
				ax = currentLine.ptSegDist(x+i, y);
				ay = currentLine.ptSegDist(x, y+i);
				bx = currentLine.ptSegDist(x -i, y);
				by = currentLine.ptSegDist(x, y-i);
				//setStartPoint(x,y,qo,ax,ay,bx,by,currentLine);
				return true;

			}
		
		}
		return false;
}
/*
			

		

			/*
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
*/



