package Wlt_Coordinates;
import java.io.*;

import org.w3c.dom.*;

import javax.xml.parsers.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.awt.geom.*;

public class XmlBuilder {

	Document doc;
	String fileName;

  
  //  public static void main (String args[]) {
    //    new DomXmlExample();
    //}

    public XmlBuilder(String fileName) {
		this.fileName = fileName;
        try {

            /////////////////////////////
            //Creating an empty XML Document

            //We need a Document
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            doc = docBuilder.newDocument();

            ////////////////////////
            //Creating the XML tree

            //create the root element and add it to the document
            Element root = doc.createElement("SlideInfo");
            doc.appendChild(root);
	    printXml();
	    Element root2 = doc.getElementById("SlideInfo");
	    

        } catch (Exception e) {
            System.out.println(e);
        }
    }

public void addNumSlides(int numSlides) {
	Element child = doc.createElement("NumSlides");
	child.appendChild(doc.createTextNode(java.lang.Integer.toString(numSlides)) );
	Element root = doc.getDocumentElement();
	root.appendChild(child);
}

public void addSlideElement(String slideNum) {
  try {
	
	 System.out.println("this is the slideNum: " + slideNum);
	 Element child = doc.createElement("Slide" + slideNum);
	//child.setAttribute("name", "value");
	 Element root = doc.getDocumentElement();
         root.appendChild(child);
                          

        } catch (Exception e) {
            System.out.println(e);
        }
}

/*
public void addLine(String whichLine, String slideNum, String e1, String e2, int n) {
  try {
	
	 
	 Element child = doc.createElement(whichLine);
	 child.appendChild(doc.createTextNode(e1 + ", " + e2));
	 //child.setAttribute("name", "value");
	//Node node = doc.getDocumentElement();
	NodeList list = doc.getElementsByTagName("Slide" + slideNum);
	if (list.getLength() == 1) {
		Element ele = (Element) list.item(0);
		ele.appendChild(child);
		printXml();
	}else
		System.out.println("some error?");
	

        } catch (Exception e) {
            System.out.println(e);
        }
}
*/

public void addSide(String whichSide, Side s, String slideNum) {
	int i, q;
	Line2D line;
	try {
		Element child = doc.createElement("Side" + whichSide);
		NodeList list = doc.getElementsByTagName("Slide" + slideNum);
		if (list.getLength() == 1) {
			Element ele = (Element) list.item(0);
			ele.appendChild(child);
			printXml();
		}else
			System.out.println("some error?");
		for (i = 1; (line = s.getLineAt(i)) != null; i++) {
			String lineData = line.getX1() + ", " +  line.getY1() + ", " + line.getX2() + ", " + line.getY2();
			addLine(Integer.toString(i), whichSide, slideNum, lineData);
			
		}
		list = list.item(0).getChildNodes();
		for (q = 0; q < list.getLength(); q++) {
			if (list.item(q).getNodeName().equals("Side" + whichSide)) {
				Element ele = (Element) list.item(q);
				ele.setAttribute("numLines", java.lang.Integer.toString(i-1));
				q = list.getLength();			
			}
		}
		printXml();


	}catch (Exception e) {
		System.out.println(e);
	}
}

public void addLine(String whichLine, String whichSide, String slideNum, String lineData) {
  try {
	
	 int i;
	 Element child = doc.createElement("L" + whichLine);
	 child.appendChild(doc.createTextNode(lineData));
	 //child.setAttribute("name", "value");
	//Node node = doc.getDocumentElement();
	NodeList list = doc.getElementsByTagName("Slide" + slideNum);
	//Node n = list.item(0);
	list = list.item(0).getChildNodes();
	for (i = 0; i < list.getLength(); i++) {
			System.out.println("the node name: " + list.item(i).getNodeName());
		if (list.item(i).getNodeName().equals("Side" + whichSide)) {
			Element ele = (Element) list.item(i);
			ele.appendChild(child);
			printXml();
			i = list.getLength();
		}
	}
	

        } catch (Exception e) {
            System.out.println(e);
        }
}






public void addImageElement(String slideNum, String imgName) {

  try {
	 
	 Element child = doc.createElement("img");
	 child.appendChild(doc.createTextNode(imgName));
	 //child.setAttribute("name", "value");
	//Node node = doc.getDocumentElement();
	NodeList list = doc.getElementsByTagName("Slide" + slideNum);
	if (list.getLength() == 1) {
		Element ele = (Element) list.item(0);
		ele.appendChild(child);
		
		printXml();
	}else
		System.out.println("Slide Not Found");
	
		
	 printXml();
         
                   

        } catch (Exception e) {
            System.out.println(e);
        }
}

public void addImageElement(String slideNum, String imgName, int imgWidth, int imgHeight) {

  try {
	 
	 Element child = doc.createElement("img");
	 child.appendChild(doc.createTextNode(imgName));
	 child.setAttribute("dimensions", java.lang.Integer.toString(imgWidth) + ", " + java.lang.Integer.toString(imgHeight));
	 //child.setAttribute("name", "value");
	//Node node = doc.getDocumentElement();
	NodeList list = doc.getElementsByTagName("Slide" + slideNum);
	if (list.getLength() == 1) {
		Element ele = (Element) list.item(0);
		ele.appendChild(child);
		
		printXml();
	}else
		System.out.println("Slide Not Found");
	
		
	 printXml();
         
                   

        } catch (Exception e) {
            System.out.println(e);
        }
}

public void addPanelDimensions(String slideNum, int width, int height) {

  try {
	 
	 Element mainChild = doc.createElement("panelDimensions");
	 Element child1 = doc.createElement("width");
	 child1.appendChild(doc.createTextNode(java.lang.Integer.toString(width)));
	 Element child2 = doc.createElement("height");
	 child2.appendChild(doc.createTextNode(java.lang.Integer.toString(height))); 	 
	 mainChild.appendChild(child1);
	 mainChild.appendChild(child2);
	NodeList list = doc.getElementsByTagName("Slide" + slideNum);
	if (list.getLength() == 1) {
		Element ele = (Element) list.item(0);
		ele.appendChild(mainChild);
		
		printXml();
	}else
		System.out.println("Slide Not Found");
         
	
	 printXml();
         
                   

        } catch (Exception e) {
            System.out.println(e);
        }
}



public void printXml() {
	try {

	TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = transfac.newTransformer();
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        trans.setOutputProperty(OutputKeys.INDENT, "yes");

            //create string from xml tree
        StringWriter sw = new StringWriter();
	 //PrintWriter out  = new PrintWriter(new BufferedWriter(new FileWriter(".xml")));
            StreamResult result = new StreamResult(sw); //holder for transform
            DOMSource source = new DOMSource(doc); //create our DOMSource using our Document (Passed as NODE)
            trans.transform(source, result); //pass our DOMSource and our holder for transform (Result interface implemented by StreamResult which is constructed from our StringWriter(Buffer))
          String xmlString = sw.toString();

            //print xml
            System.out.println("Here's the xml:\n\n" + xmlString);
 } catch (Exception e) {
            System.out.println(e);
        }
	//out.close();
}

public void writeXmlFile() {
   try {

	TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = transfac.newTransformer();
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no"); //from class OutputKeys!
        trans.setOutputProperty(OutputKeys.INDENT, "yes");

            //create string from xml tree
        
	 PrintWriter out  = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".xml")));
            StreamResult result = new StreamResult(out); //holder for transform
            DOMSource source = new DOMSource(doc); //create our DOMSource using our Document (Passed as NODE)
            trans.transform(source, result); //pass our DOMSource and our holder for transform (Result interface implemented by StreamResult which is constructed from our StringWriter(Buffer))
            out.close();

           
   } catch (Exception e) {
            System.out.println(e);
        }
}

}


