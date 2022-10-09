import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MyFrame extends JFrame implements ActionListener {
	
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenu editMenu;
	JMenu helpMenu;
	JMenuItem loadItem;
	JMenuItem saveItem;
	JMenuItem exitItem;
	ImageIcon loadIcon;
	ImageIcon saveIcon;
	ImageIcon exitIcon;
	
	MyFrame() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		loadIcon= new ImageIcon(MyFrame.class.getResource("folder.png"));
		saveIcon= new ImageIcon(MyFrame.class.getResource("diskette.png"));
		exitIcon= new ImageIcon(MyFrame.class.getResource("door.png"));
		
		menuBar= new JMenuBar();
		fileMenu= new JMenu("File");
		editMenu= new JMenu("Edit");
		helpMenu= new JMenu("Help");
		
		loadItem= new JMenuItem("Load");
	    saveItem= new JMenuItem("Save");
		exitItem= new JMenuItem("Exit");
		
		loadItem.addActionListener(this);
		saveItem.addActionListener(this);
		exitItem.addActionListener(this);
		
		loadItem.setIcon(loadIcon);
		saveItem.setIcon(saveIcon);
		exitItem.setIcon(exitIcon);
		
		fileMenu.setMnemonic(KeyEvent.VK_F); // alt + f for file
		editMenu.setMnemonic(KeyEvent.VK_E); // alt + e for edit
		helpMenu.setMnemonic(KeyEvent.VK_H); // alt + h for help
		loadItem.setMnemonic(KeyEvent.VK_L); // l for load
		saveItem.setMnemonic(KeyEvent.VK_S); // s for save
		exitItem.setMnemonic(KeyEvent.VK_E); // e for exit
		
		fileMenu.add(loadItem);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);
		this.setJMenuBar(menuBar);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loadItem) {
			JFileChooser fileChooser= new JFileChooser();
			fileChooser.setCurrentDirectory(new File(".")); // set current directory to project folder
			int response= fileChooser.showOpenDialog(null); // select file to open
			if(response == JFileChooser.APPROVE_OPTION) {
				File file= new File(fileChooser.getSelectedFile().getAbsolutePath());
				System.out.println(file);	
			}
		}else if(e.getSource() == saveItem) {
			System.out.println("*beep boop* you saved a file");	
		}else if(e.getSource() == exitItem) {
			System.exit(0);
		}
		
	}

}