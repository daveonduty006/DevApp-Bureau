import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class SliderDemo implements ChangeListener {

	private JFrame frame;
	private JPanel panel;
	private JLabel label;
	private JSlider slider;
	
	SliderDemo() {
		
		frame= new JFrame("Slider Demo");
		panel= new JPanel();
		label= new JLabel();
		slider= new JSlider(0,100,50);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		slider.setPreferredSize(new Dimension(400,200));
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(10);
		slider.setPaintTrack(true);
		slider.setMajorTickSpacing(25);
		slider.setPaintLabels(true);
		slider.setFont(new Font("MV Boli", Font.PLAIN, 15));
		slider.setOrientation(SwingConstants.VERTICAL); // makes the slider vertical instead of default horizontal
		
		label.setText("°C = "+slider.getValue());
		label.setFont(new Font("MV Boli", Font.PLAIN, 25));
		
		slider.addChangeListener(this);
		
		panel.add(slider);
		panel.add(label);
		frame.add(panel);
		frame.setSize(420,420);
		frame.setVisible(true);
		
	}
	
	
	@Override
	public void stateChanged(ChangeEvent e) {
		label.setText("°C = "+slider.getValue());
		
	}
	

}
