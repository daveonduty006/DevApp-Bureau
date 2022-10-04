import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MyFrame extends JFrame implements ActionListener {

	private JButton button;
	private JTextField textField;
	
	MyFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		button= new JButton("Submit"); 
		button.addActionListener(this);
		
		textField= new JTextField();
		textField.setPreferredSize(new Dimension(250,40));
		textField.setFont(new Font("Consolas", Font.PLAIN, 35));
		textField.setForeground(new Color(0x00FF00)); // hexcode for bright green
		textField.setBackground(Color.black);
		textField.setCaretColor(Color.white); // the caret is the vertical line indicating where the characters will be inserted
		textField.setText("username");
		
		this.add(button);
		this.add(textField);
		this.pack();
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) {
			System.out.println("Welcome "+textField.getText());
			button.setEnabled(false); // makes the button unclickable after being clicked once
			textField.setEditable(false); // makes the text inside textField uneditable after the button is clicked once
		}
		
	}
	
}
