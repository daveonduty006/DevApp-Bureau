import java.awt.*;
import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		
		// LayoutManager= Defines the natural layout for components within a container
		
		// 3 common managers:
		
		// FlowLayout= A FlowLayout places components in a row, sized at their preferred size. 
		//             If the horizontal space in the container is too small, 
		//             the FlowLayout class uses the next available row.
		
		JFrame frame= new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // by default align is .CENTER
		
		JPanel panel= new JPanel();
		panel.setPreferredSize(new Dimension(100, 250));
		panel.setBackground(Color.lightGray);
		panel.setLayout(new FlowLayout()); // by default panels already use a FlowLayout
		
		//JButton button1= new JButton();
		panel.add(new JButton("1")); // shortcut method for creating components
		panel.add(new JButton("2"));
		panel.add(new JButton("3"));
		panel.add(new JButton("4"));
		panel.add(new JButton("5"));
		panel.add(new JButton("6"));
		panel.add(new JButton("7"));
		panel.add(new JButton("8"));
		panel.add(new JButton("9"));
		
		frame.add(panel);
		frame.setVisible(true); // placing this instruction at the end ensures component visibility
		
		
		
	}

}
