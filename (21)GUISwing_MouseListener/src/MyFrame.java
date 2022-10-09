import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MyFrame extends JFrame implements MouseListener {
	
	JLabel label;
	ImageIcon smile;
	ImageIcon nervous;
	ImageIcon pain;
	ImageIcon dizzy;
	
	MyFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setSize(500,500);
		this.setLayout(new FlowLayout());
		
		smile= new ImageIcon(MyFrame.class.getResource("smile.png"));
		nervous= new ImageIcon(MyFrame.class.getResource("nervous.png"));
		pain= new ImageIcon(MyFrame.class.getResource("pain.png"));
		dizzy= new ImageIcon(MyFrame.class.getResource("dizzy.png"));
		
		label= new JLabel();
		label.setIcon(smile);
		//label.setBounds(0, 0, 100, 100);
		//label.setBackground(Color.red);
		//label.setOpaque(true);
	
		label.addMouseListener(this);
		
		this.add(label);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// mouseClicked= invoked when the mouse button has been clicked (pressed and released) on a component
		//System.out.println("You clicked the mouse");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// mousePressed= invoked when a mouse button has been pressed on a component
		//System.out.println("You pressed the mouse");
		//label.setBackground(Color.yellow);
		label.setIcon(pain);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// mouseReleased= invoked when a mouse button has been released on a component
		//System.out.println("You released the mouse");
		//label.setBackground(Color.green);
		label.setIcon(dizzy);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// mouseEntered= invoked when the mouse enters a component
		//System.out.println("You entered the component with the mouse");
		//label.setBackground(Color.blue);
		label.setIcon(nervous);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// mouseExited= invoked when the mouse exits a component
		//System.out.println("You exited the component with the mouse");3
		//label.setBackground(Color.red);
		label.setIcon(smile);
	}

}
