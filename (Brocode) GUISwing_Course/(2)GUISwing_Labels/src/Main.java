import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class Main {

	public static void main(String[] args) {
		
		// JLabel= a GUI display area for a string of text, an image or both
		
		ImageIcon image= new ImageIcon("dude.png");
		Border border= BorderFactory.createLineBorder(Color.green, 3);
		
		JLabel label= new JLabel(); // creates a label
		label.setText("Bro, do you even code?"); // set text to the label
		label.setIcon(image); // set image to the label
		label.setHorizontalTextPosition(JLabel.CENTER); // set text in center (x axis) to label
		label.setVerticalTextPosition(JLabel.TOP); // set text above (y axis) to label
		label.setForeground(new Color(0x00B837)); // set font color with hex code to label
		label.setFont(new Font("MV Boli", Font.PLAIN, 20)); // set font to label
		label.setIconTextGap(-20); // set a gap between the text and image of the label
		label.setBackground(Color.black); // set background color of the label to constant black
		label.setOpaque(true); // !!! makes the background color visible on the label !!!
		label.setBorder(border); // set border to the label
		label.setVerticalAlignment(JLabel.CENTER); // set y position of icon+text within label
		label.setHorizontalAlignment(JLabel.CENTER); // set x position of icon+text within label
		//label.setBounds(0, 0, 1000, 1000); // set bounds to label within the frame's dimensions
		                                     // in this case the label is to the top left (0, 0, ...)
		                                     // also set the size of the label (..., 1000, 1000)
		//label.setBounds(100, 0, 1000, 1000); // in this case the label moved 100px on the x axis
		
		
		JFrame frame= new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1400,1100);
		//frame.setLayout(null); // overwrite the default LayoutManager behavior 
		frame.setVisible(true);		
		frame.add(label); // add the label to the frame
		//frame.pack(); // set the size of the frame to only fit the frame's content
		
	}

}
