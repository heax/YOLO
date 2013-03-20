import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class mySailorButt extends JButton implements ActionListener {
	
	private boolean sailorChosen = false;
	
	ImageIcon sailor1 = new ImageIcon(getClass().getResource("/pics/sailormoonknapptest1.png"));
	ImageIcon sailor2 = new ImageIcon(getClass().getResource("/pics/sailormoonknapptest2.png"));
	ImageIcon sailor3 = new ImageIcon(getClass().getResource("/pics/sailormoonknapptest3.png"));
	ImageIcon sailor4 = new ImageIcon(getClass().getResource("/pics/sailormoonknapptest4.png"));
	
	ImageIcon batman1 = new ImageIcon(getClass().getResource("/pics/batmanbuttontest1.png"));
	ImageIcon batman2 = new ImageIcon(getClass().getResource("/pics/batmanbuttontest2.png"));
	ImageIcon batman3 = new ImageIcon(getClass().getResource("/pics/batmanbuttontest3.png"));
	ImageIcon batman4 = new ImageIcon(getClass().getResource("/pics/batmanbuttontest4.png"));

	public mySailorButt() {
	}

	public mySailorButt(Icon icon) {
		super(icon);
		
		
		this.addActionListener(this);
		// TODO Auto-generated constructor stub
	}

	public mySailorButt(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public mySailorButt(Action a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	public mySailorButt(String text, Icon icon) {
		super(text, icon);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(sailorChosen == true){
			setIcon(batman1);
			setRolloverIcon(batman2);
			setPressedIcon(batman3);
		}else{
			setIcon(sailor1);
			setRolloverIcon(sailor3);
			setPressedIcon(sailor2);
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		sailorChosen = !sailorChosen;
		System.out.println("Sailor moon: "+sailorChosen);
	}



}
