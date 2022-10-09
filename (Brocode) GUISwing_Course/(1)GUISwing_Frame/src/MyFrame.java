import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

	MyFrame() {
		
		this.setTitle("JFrame title goes here"); // sets frame's title
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of program if frame is 
		                                                     // corner-closed
		this.setResizable(false); //prevents the frame from being resized by the user
		this.setSize(420,420); // sets the frame's x and y dimensions
		this.setVisible(true); // makes frame visible
		
		ImageIcon image= new ImageIcon("logo.png"); // creates an ImageIcon
		this.setIconImage(image.getImage()); // changes icon of frame
		
		//this.getContentPane().setBackground(Color.GREEN); // changes color of background to 
		                                                    // default green
		this.getContentPane().setBackground(new Color(123,50,250)); // creates custom color via RGB
	}
	
}
