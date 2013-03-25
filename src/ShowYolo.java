import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;




public class ShowYolo extends JFrame {

	private JPanel contentPane;
	JLabel lblNewLabel;
	private ArrayList<Crime> crimeList;
	private String s;
	Document document;
	
	//Lista med alla orter man kan välja mellan
	String[] areaNames = {"Välj stad...", "Bjuv", "Bromölla", "Burlöv", "Båstad", "Eslöv", "Helsingborg",
			"Hässleholm", "Höganäs", "Hörby", "Höör", "Klippan", "Kristianstad", "Kävlinge",
			"Landskrona", "Lomma", "Lund", "Malmö", "Osby", "Perstorp", "Simrishamn", "Sjöbo", "Skurup",
			"Staffanstorp", "Svalöv", "Svedala", "Tomelilla", "Trelleborg", "Vellinge", "Ystad",
			"Åstorps kommun", "Ängelholm", "Örkelljunga", "Östra Göinge"};
	
	//Bilder för bakgrunden
	ImageIcon bgSail = new ImageIcon(getClass().getResource("/pics/sailor moonklar.png"));
	ImageIcon bgBat = new ImageIcon(getClass().getResource("/pics/batman.png"));
	ImageIcon bgSailan = new ImageIcon(getClass().getResource("/pics/sailan.png"));
	ImageIcon bgBatteman = new ImageIcon(getClass().getResource("/pics/batteman.png"));
	
	//Bilder till knappen
	ImageIcon batman1 = new ImageIcon(getClass().getResource("/pics/batmanbuttontest1.png"));
	ImageIcon sailor1 = new ImageIcon(getClass().getResource("/pics/sailormoonknapptest1.png"));
	private JLabel lblNewLabel_1;
	private JComboBox comboBox;
	private JLabel label;
	private JLabel lblNewLabel_2;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowYolo frame = new ShowYolo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShowYolo() {
		//En arrayList som vi fyller med Crime
		crimeList = new ArrayList<Crime>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 419, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(Color.YELLOW);
		lblNewLabel_1.setBounds(12, 360, 163, 27);
		contentPane.add(lblNewLabel_1);
		
		mySailorButt mySailorButt_ = new mySailorButt(sailor1, this);
		mySailorButt_.setBounds(305, 326, 63, 61);
		contentPane.add(mySailorButt_);
		
		
		label = new JLabel("");
		label.setForeground(Color.YELLOW);
		label.setBounds(215, 153, 153, 61);
		contentPane.add(label);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setForeground(Color.YELLOW);
		lblNewLabel_2.setBounds(215, 211, 153, 61);
		contentPane.add(lblNewLabel_2);
		
		
		comboBox = new JComboBox(areaNames);
		comboBox.setForeground(Color.YELLOW);
		comboBox.setBackground(Color.BLACK);
		comboBox.setBounds(245, 13, 106, 27);
		contentPane.add(comboBox);
		
		//ComboBoxen där man väljer sin ort och kod för att hämta information för den orten
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				getInfo(comboBox.getSelectedItem().toString());//Hämta det valda namnet från comboboxen
				
				if(crimeList.isEmpty()){    //Om det inte finns några Crime så visas det att platsen är säker
					label.setText("Det här är en säker plats");
					lblNewLabel_2.setText("");
				}else{
					//Hämta description och printa ut i JLabel
					StringTokenizer st = crimeList.get(0).getCrimeDescription();
					String s ="<html>";  //Eftersom det är en JLabel måste det vara html för att man ska kunna byta rad
					while (st.hasMoreElements()) {
						s=s+st.nextElement()+"<br>"; //Byt rad med <br>
					}
					s=s+"</html>";
					//Hämta location och gör samma sak som med description
					StringTokenizer st2 = crimeList.get(0).getCrimeLocation();
					String s2 ="<html>";
					while(st2.hasMoreElements()){
						s2=s2+st2.nextElement()+"<br>";
					}
					s2=s2+"</html>";
					
					
					//s är description och s2 är location
					label.setText(s2);
					lblNewLabel_2.setText(s);
				}
			}	
		});
		
		
		lblNewLabel = new JLabel(bgBat);
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.setBounds(0, 0, 400, 400);
		contentPane.add(lblNewLabel);
		
		Timer timer = new Timer();
		MyTask t = new MyTask(); //Den inre klassen nedan
		//Update every minute
		timer.schedule(t, 0, 60000);
		
	}


	public void createParser() {
		//parsea parsea parsea
		 URL url;
		try {
			url = new  URL("http://www.polisen.se/Skane/Aktuellt/RSS/Lokal-RSS---Handelser/Lokala-RSS-listor1/Handelser-RSS---Skane/?feed=rss");
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
			xml = xml.substring(start); //det var lite skräp i b�rjan st�da det
			//System.out.println("XML IGEN p� en rad...------------------------");
			//System.out.println(xml);
         DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = null;
            
             
                 builder = null;
                 builder = builderFactory.newDocumentBuilder();
                 document = builder.parse(new InputSource(new StringReader(xml)));
               //tid att H�mta ut saker ur xml........
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
	}
	//annars?
	//(ﾉಥ益ಥ）ﾉ﻿ ┻━┻
	public String getTitle(String cityName){
		String s = "<html>";
		NodeList rootElement = document.getElementsByTagName("title");
		for(int i = 1; i < rootElement.getLength(); i ++){
			Element element = (Element) rootElement.item(i);
			NodeList list = element.getChildNodes();
			if(list.item(0).getNodeValue().indexOf("Sammanfattning") > -1){
				System.out.println("Här var det sammanfattning");
			} else {
				if(list.item(0).getNodeValue().indexOf(cityName) > -1){
				s = s+list.item(0).getNodeValue()+"<br/>";
				//System.out.println(s);
				
				}
			}
		}
			System.out.println(s);
			if(s == ""){
				s="Det här är en säker plats";
			}
			return s+"</html>";
	}
	
	public String getInfo(String cityName){
		crimeList.clear();
		String s2 = "<html>";
		NodeList rootElement = document.getElementsByTagName("title");
		NodeList rootElement2 = document.getElementsByTagName("description");
		for(int i = 1; i < rootElement.getLength(); i ++){
			Element element = (Element) rootElement.item(i);
			Element element2 = (Element) rootElement2.item(i);
			NodeList list = element.getChildNodes();
			NodeList list2 = element2.getChildNodes();
			if(list.item(0).getNodeValue().indexOf("Sammanfattning") > -1){
				//System.out.println("Här var det sammanfattning");
			} else {
				if(list.item(0).getNodeValue().indexOf(cityName) > -1){
					Crime c = new Crime();
					c.setLocation(list.item(0).getNodeValue());
					c.setDescrption(list2.item(0).getNodeValue());
					crimeList.add(c);
					s2 = s2+list2.item(0).getNodeValue()+"<br/>";
					//System.out.println(s2);
				} else {
				}
			}
			
		}
			return s2+"</html>";
	}

	public void setBackGround(String string) {
		// TODO Auto-generated method stub
		if (string == "moon"){
			lblNewLabel.setIcon(bgSail);
			
		}else{
			lblNewLabel.setIcon(bgBat);
			
		}
		System.out.println(string);
		
	}
		
		
		private class MyTask extends TimerTask {
			public void run() {
			createParser();
			System.out.println("New Parse");
			
			//Create a calendar
			Calendar c = Calendar.getInstance();
			
			//Get the hour and minte and print it out like a clock (HH:MM)
			lblNewLabel_1.setText("Senast uppdaterat: "+c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE));
			
			
			//Gör samma sak som i actionPreformed för comboboxen, dvs skriv i JLabel informationen från xml-en
			comboBox.getSelectedItem().toString();
			
			if(comboBox.getSelectedItem().toString().equals("Välj stad...")){
				label.setText("");
			}else{
				if(crimeList.isEmpty()){  
					label.setText("Det här är en säker plats");
					lblNewLabel_2.setText("");
				}else{
				
					StringTokenizer st = crimeList.get(0).getCrimeDescription();
					String s ="<html>";
					
					while (st.hasMoreElements()) {
						s=s+st.nextElement()+"<br>"; 
					}
					s=s+"</html>";
				
					StringTokenizer st2 = crimeList.get(0).getCrimeLocation();
					String s2 ="<html>";
					
					while(st2.hasMoreElements()){
						s2=s2+st2.nextElement()+"<br>";
					}
					s2=s2+"</html>";
				
				
					label.setText(s2);
					lblNewLabel_2.setText(s);
					}
				}
			}
		}
}
