import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MyFrame extends JFrame implements KeyListener {
	
	JLabel label;
	ImageIcon icon;
	
	MyFrame() { 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setLayout(null);
		
		this.addKeyListener(this);
		
		icon= new ImageIcon(MyFrame.class.getResource("rocket.png"));
		
		label= new JLabel();
		label.setBounds(0,0,100,100);
		//label.setBackground(Color.red);
		//label.setOpaque(true);
		label.setIcon(icon);
		
		this.getContentPane().setBackground(Color.black);
		this.add(label);
		this.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// keyTyped= invoked when a key is typed. Uses KeyChar, char output
		switch(e.getKeyChar()) {
			case 'a':
				label.setLocation( label.getX()-10, label.getY() );
				break;
			case 'w':
				label.setLocation( label.getX(), label.getY()-10 );
				break;
			case 's':
				label.setLocation( label.getX(), label.getY()+10 );
				break;
			case 'd':
				label.setLocation( label.getX()+10, label.getY() );
				break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// keyPressed= invoked when a physical key is pressed down. Uses KeyCode, int output
		switch(e.getKeyCode()) {
		case 37:
			label.setLocation( label.getX()-10, label.getY() );
			break;
		case 38:
			label.setLocation( label.getX(), label.getY()-10 );
			break;
		case 39:
			label.setLocation( label.getX()+10, label.getY() );
			break;
		case 40:
			label.setLocation( label.getX(), label.getY()+10 );
			break;
	}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// keyReleased= invoked whenever a button is released
		//System.out.println("You released key char: "+ e.getKeyChar());
		//System.out.println("You released key code: "+ e.getKeyCode());
	}

}
