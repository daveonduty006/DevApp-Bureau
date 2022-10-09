import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MyFrame extends JFrame implements ActionListener {
	
	private JRadioButton pizzaButton;
	private JRadioButton hamburgerButton;
	private JRadioButton hotdogButton;
	private ImageIcon pizzaIcon;
	private ImageIcon hamburgerIcon;
	private ImageIcon hotdogIcon;
	
	MyFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		pizzaIcon= new ImageIcon("pizza.png");
		hamburgerIcon= new ImageIcon("hamburger.png");
		hotdogIcon= new ImageIcon("hotdog.png");
		
		pizzaButton= new JRadioButton("pizza");
		pizzaButton.setFocusable(false);
		hamburgerButton= new JRadioButton("hamburger");
		hamburgerButton.setFocusable(false);
		hotdogButton= new JRadioButton("hotdog");
		hotdogButton.setFocusable(false);
		
		ButtonGroup group= new ButtonGroup();
		group.add(pizzaButton);
		group.add(hamburgerButton);
		group.add(hotdogButton);
		
		pizzaButton.addActionListener(this);
		hamburgerButton.addActionListener(this);
		hotdogButton.addActionListener(this);
		
		pizzaButton.setIcon(pizzaIcon);
		hamburgerButton.setIcon(hamburgerIcon);
		hotdogButton.setIcon(hotdogIcon);
		
		this.add(pizzaButton);
		this.add(hamburgerButton);
		this.add(hotdogButton);
		this.pack();
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == pizzaButton) {
			System.out.println("You ordered pizza");
		}else if(e.getSource() == hamburgerButton) {
			System.out.println("You ordered a hamburger");
		}else if(e.getSource() == hotdogButton) {
			System.out.println("You ordered a hotdog");
		}
		
	}

}
