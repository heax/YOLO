import java.io.*;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
 
public class Test
 
        {
    static Document document; //Allt i en static klass m�ste vara static s� om du skriver metoder ange de static det kan du ta bort n�r du flyttar till annan klass.
 
                public static void main (String[] args) throws Exception{
                        //Denna raden fattar en URL
                	 URL url = new  URL("http://www.polisen.se/Skane/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Handelser-RSS---Skane/?feed=rss");
                     InputStream is = url.openStream(); //H�r beh�ver vi en str�m av tecken
                     InputStreamReader isr = new InputStreamReader(is); //Denna frst�r sig p� radslut etc
                     BufferedReader reader = new BufferedReader(isr); //Denna klass kan buffra datan
                     String line = reader.readLine(); //L�s f�rsta radan
                     String xml=""; //h�r kommer alla rader
                     while (line != null) { //L�s s� l�nge det finns rader
                             //System.out.println(line);
                             xml = xml + line+"\n"; //l�gg till i xml-str�ngen
                             //H�r l�ses varje rad s� kan ni spara det i en String eller StringBuffer
                             line = reader.readLine(); //l�s n�sta rad
                     }
         			int start = xml.indexOf("<?xml version=");
         			xml = xml.substring(start); //det var lite skr�p i b�rjan st�da det
         			System.out.println("XML IGEN p� en rad...------------------------");
         			System.out.println(xml);
                     DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                     DocumentBuilder builder = null;
                        
                         try {
                             builder = null;
                             builder = builderFactory.newDocumentBuilder();
                             document = builder.parse(new InputSource(new StringReader(xml)));
                           //tid att H�mta ut saker ur xml........
                             } catch (Exception e) {
                                 e.printStackTrace();
                             }
                         Node node = null;
             			String s = "";
             			NodeList rootElement = document.getElementsByTagName("title");
             			for(int i = 1; i < rootElement.getLength(); i ++){
             			Element element = (Element) rootElement.item(i);
             			NodeList list = element.getChildNodes();
             			s = s+list.item(0).getNodeValue()+"\n";
             			}
             			System.out.println(s);
             			
             }
                
           
        }     