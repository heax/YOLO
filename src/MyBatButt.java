import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class MyBatButt extends JButton implements ActionListener {
	
	private boolean batmanChosen = false;
	
	ImageIcon batman1 = new ImageIcon(getClass().getResource("/pics/batmanbuttontest1.png"));
	ImageIcon batman2 = new ImageIcon(getClass().getResource("/pics/batmanbuttontest2.png"));
	ImageIcon batman3 = new ImageIcon(getClass().getResource("/pics/batmanbuttontest3.png"));
	ImageIcon batman4 = new ImageIcon(getClass().getResource("/pics/batmanbuttontest4.png"));

	public MyBatButt() {
		// TODO Auto-generated constructor stub
	}

	public MyBatButt(Icon icon) {
		super(icon);
		this.addActionListener(this);
		// TODO Auto-generated constructor stub
	}

	public MyBatButt(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public MyBatButt(Action a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	public MyBatButt(String text, Icon icon) {
		super(text, icon);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		batmanChosen = !batmanChosen;
		System.out.println("Batman: "+batmanChosen);
		
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		if(batmanChosen == true){
			setIcon(batman4);
			setRolloverIcon(batman3);
			setPressedIcon(batman2);
		}else{
			setIcon(batman1);
			setRolloverIcon(batman3);
			setPressedIcon(batman2);
		}
	}

}
