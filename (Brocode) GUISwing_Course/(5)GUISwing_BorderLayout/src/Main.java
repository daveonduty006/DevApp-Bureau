import java.awt.*;
import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		
		// LayoutManager= Defines the natural layout for components within a container
		
		// 3 common managers:
		
		// BorderLayout= A BorderLayout places components in five areas: 
		//   NORTH, SOUTH, WEST, EAST, CENTER.
		// All extra space is placed in the center area.
		
		JFrame frame= new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLayout(new BorderLayout(10, 10)); // add 10px margins (vgap/hgap) between panels
		frame.setVisible(true);
		
		JPanel panel1= new JPanel();
		JPanel panel2= new JPanel();
		JPanel panel3= new JPanel();
		JPanel panel4= new JPanel();
		JPanel panel5= new JPanel();
		
		panel1.setBackground(Color.red);
		panel2.setBackground(Color.green);
		panel3.setBackground(Color.yellow);
		panel4.setBackground(Color.magenta);
		panel5.setBackground(Color.blue);
		
		panel1.setPreferredSize(new Dimension(100, 100)); // n constraint nullifies custom height 
		panel2.setPreferredSize(new Dimension(100, 100)); // w constraint nullifies custom width
		panel3.setPreferredSize(new Dimension(100, 100)); // e constraint nullifies custom width
		panel4.setPreferredSize(new Dimension(100, 100)); // s constraint nullifies custom height
		panel5.setPreferredSize(new Dimension(100, 100));
		
		// -------- sub-panels ---------
		
		JPanel subpanel1= new JPanel();
		JPanel subpanel2= new JPanel();
		JPanel subpanel3= new JPanel();
		JPanel subpanel4= new JPanel();
		JPanel subpanel5= new JPanel();
		
		subpanel1.setBackground(Color.black);
		subpanel2.setBackground(Color.darkGray);
		subpanel3.setBackground(Color.gray);
		subpanel4.setBackground(Color.lightGray);
		subpanel5.setBackground(Color.white);
		
		panel5.setLayout(new BorderLayout()); // because panel5 will also be a container
		
		subpanel1.setPreferredSize(new Dimension(50, 50)); // n constraint nullifies custom height 
		subpanel2.setPreferredSize(new Dimension(50, 50)); // w constraint nullifies custom width
		subpanel3.setPreferredSize(new Dimension(50, 50)); // e constraint nullifies custom width
		subpanel4.setPreferredSize(new Dimension(50, 50)); // s constraint nullifies custom height
		subpanel5.setPreferredSize(new Dimension(50, 50));
		
		panel5.add(subpanel1, BorderLayout.NORTH);
		panel5.add(subpanel2, BorderLayout.SOUTH);
		panel5.add(subpanel3, BorderLayout.WEST);
		panel5.add(subpanel4, BorderLayout.EAST);
		panel5.add(subpanel5, BorderLayout.CENTER);
				
		//
		
		frame.add(panel1, BorderLayout.NORTH);
		frame.add(panel2, BorderLayout.WEST);
		frame.add(panel3, BorderLayout.EAST);
		frame.add(panel4, BorderLayout.SOUTH);
		frame.add(panel5, BorderLayout.CENTER);
		
	}

}
