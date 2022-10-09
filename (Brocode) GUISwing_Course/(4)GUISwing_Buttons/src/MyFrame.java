import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MyFrame extends JFrame implements ActionListener {
	
	public static JButton button;
	public static JLabel label;

	MyFrame(){		
		
		ImageIcon icon= new ImageIcon("backhand-index-pointing-left.png");
		ImageIcon icon2= new ImageIcon("shock.png");
		
		label= new JLabel();
		label.setIcon(icon2);
		label.setBounds(150, 250, 150, 150);
		label.setVisible(false);
	
		button= new JButton();
		button.setBounds(100, 100, 250, 150);
		button.addActionListener(this);
		//button.addActionListener(e -> System.out.println("hi")); // with a lambda expression
		button.setText("I'm a button!");
		button.setFocusable(false); // get rid of the box around the text of the button
		button.setIcon(icon);
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setVerticalTextPosition(JButton.BOTTOM);
		button.setFont(new Font("Comic Sans", Font.BOLD, 25));
		button.setIconTextGap(-5);
		button.setForeground(Color.cyan);
		button.setBackground(Color.lightGray);
		button.setBorder(BorderFactory.createEtchedBorder());
		//button.setEnabled(false); // disable the button 
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null); 
		this.setSize(500,500);
		this.setVisible(true);
		this.add(button);
		this.add(label);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) {
			//System.out.println("hi"); // print hi in the console after clicking
			//button.setEnabled(false); // disable the button after one-click
			label.setVisible(true);
		}
	}
	
}
