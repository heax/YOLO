import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;




public class ShowYolo extends JFrame {

	private JPanel contentPane;
	private ArrayList<Crime> crimeList;
	private String s;
	Document document;
	String[] areaNames = {"Välj stad...", "Bjuv", "Bromölla", "Burlöv", "Båstad", "Eslöv", "Helsingborg",
			"Hässleholm", "Höganäs", "Hörby", "Höör", "Klippan", "Kristianstad", "Kävlinge",
			"Landskrona", "Lomma", "Lund", "Malmö", "Osby", "Perstorp", "Simrishamn", "Sjöbo", "Skurup",
			"Staffanstorp", "Svalöv", "Svedala", "Tomelilla", "Trelleborg", "Vellinge", "Ystad",
			"Åstorps kommun", "Ängelholm", "Örkelljunga", "Östra Göinge"};
	
	ImageIcon batman1 = new ImageIcon(getClass().getResource("/pics/batmanbuttontest1.png"));
	ImageIcon sailor1 = new ImageIcon(getClass().getResource("/pics/sailormoonknapptest1.png"));

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
		crimeList = new ArrayList<Crime>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 419, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		MyBatButt myBatButt = new MyBatButt(batman1);
		myBatButt.setBounds(207, 303, 85, 84);
		contentPane.add(myBatButt);
		
		mySailorButt mySailorButt_ = new mySailorButt(sailor1);
		mySailorButt_.setBounds(304, 303, 85, 84);
		contentPane.add(mySailorButt_);
		
		final JLabel label = new JLabel("");
		label.setBounds(118, 150, 250, 27);
		contentPane.add(label);
		
		final JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(153, 216, 150, 27);
		contentPane.add(lblNewLabel_2);
		
		final JComboBox comboBox = new JComboBox(areaNames);
		comboBox.setForeground(Color.YELLOW);
		comboBox.setBackground(Color.BLACK);
		comboBox.setBounds(206, 28, 106, 27);
		contentPane.add(comboBox);
		//comboBox.setEditable(true);
		//comboBox.setOpaque(false);
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				//textPane.setText(getTitle(comboBox.getSelectedItem().toString()));
				System.out.println(comboBox.getSelectedItem().toString());
				getInfo(comboBox.getSelectedItem().toString());
				//label.setText(getTitle(comboBox.getSelectedItem().toString()));
				if(crimeList.isEmpty()){
					label.setText("Det här är en säker plats");
				}else{
				label.setText(crimeList.get(0).getLocation());
				lblNewLabel_2.setText(crimeList.get(0).getDescrption());
				}
			}	
		});
		
		
		createParser();
		//textPane.setText(getTitle());
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Hedvig\\Desktop\\liten battis.png"));
		lblNewLabel_1.setBounds(185, 0, 150, 84);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Hedvig\\Documents\\GitHub\\YOLO\\src\\pics\\sailor moon klar.png"));
		lblNewLabel.setBounds(0, 0, 400, 400);
		contentPane.add(lblNewLabel);
		
		
		
		
	}


	public void createParser() {
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
			System.out.println("XML IGEN p� en rad...------------------------");
			System.out.println(xml);
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
				System.out.println(s);
				
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
				System.out.println("Här var det sammanfattning");
			} else {
				if(list.item(0).getNodeValue().indexOf(cityName) > -1){
					Crime c = new Crime();
					c.setLocation(list.item(0).getNodeValue());
					c.setDescrption(list2.item(0).getNodeValue());
					crimeList.add(c);
					s2 = s2+list2.item(0).getNodeValue()+"<br/>";
					System.out.println(s2);
				} else {
				}
			}
			
		}
			return s2+"</html>";
	}
}
