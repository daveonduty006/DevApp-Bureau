import java.awt.*;
import javax.swing.*;

public class ProgressBarDemo {

	private JFrame frame;
	private JProgressBar bar;
	
	ProgressBarDemo() {
		
		frame= new JFrame();
		bar= new JProgressBar(0,100);
		
		bar.setValue(0);
		bar.setBounds(0, 0, 420, 50); // because no LayoutManager is being used
		bar.setStringPainted(true); // add a dynamic % to the progressBar
		bar.setFont(new Font("MV Boli", Font.BOLD, 25));
		bar.setForeground(Color.red);
		bar.setBackground(Color.black);
		
		frame.add(bar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setVisible(true);
		
		fill();
		
	}
	
	
	public void fill() {
		int counter= 0;
		while(counter <= 100) {
			bar.setValue(counter);
			try {
				Thread.sleep(50);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			counter += 1;
		}
		bar.setString("Done! :)");
	}
}
