import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MyFrame extends JFrame implements ActionListener {
	
	JButton button;
	JLabel label;
	
	MyFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		button= new JButton("Pick a color");
		button.addActionListener(this);
		
		label= new JLabel();
		label.setBackground(Color.white);
		label.setText("This is some text");
		label.setFont(new Font("MV Boli", Font.PLAIN, 100));
		label.setOpaque(true);
		
		this.add(button);
		this.add(label); 
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) {
			Color color= JColorChooser.showDialog(null, "Pick a color", Color.black);
			label.setForeground(color);
		}
		
	}

}
