import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		
		// JOptionPane= pop up a standard dialog box that prompts
	    //              users for a value or informs them of something
		
		/////////////////////////////////////////////////////////

		/*
		JOptionPane.showMessageDialog(
			null,"This is some useless info","MessageDialogBox",
			JOptionPane.PLAIN_MESSAGE);
		*/
		/*
		JOptionPane.showMessageDialog(
				null,"Here is more useless info","MessageDialogBox",
				JOptionPane.INFORMATION_MESSAGE);
		*/
		/*
		JOptionPane.showMessageDialog(
				null,"Really?","MessageDialogBox",
				JOptionPane.QUESTION_MESSAGE);
		*/
		/*
		JOptionPane.showMessageDialog(
				null,"Your computer has A VIRUS!!!","MessageDialogBox",
				JOptionPane.WARNING_MESSAGE);
		*/
		/*
		JOptionPane.showMessageDialog(
				null,"Call tech support now OR ELSE!!!","MessageDialogBox",
				JOptionPane.ERROR_MESSAGE);
		*/      
		
		/////////////////////////////////////////////////////////
		
		/*
		int answer= JOptionPane.showConfirmDialog(
					null, "bro do you even code?", "ConfirmDialogBox", 
					JOptionPane.YES_NO_CANCEL_OPTION);
		JOptionPane.showMessageDialog(
		null, answer, "Your answer", JOptionPane.PLAIN_MESSAGE);		
		//  Yes       returns  0
		//  No        returns  1
		//  Cancel    returns  2
		//  X-Exiting returns -1 
		*/
		
		/////////////////////////////////////////////////////////
		
		/*
		String name= JOptionPane.showInputDialog(
					 "What is your name?");
		JOptionPane.showMessageDialog(
		null, name, "Your name", JOptionPane.PLAIN_MESSAGE);
		*/
		
		/////////////////////////////////////////////////////////
		
		String[] responses= {"No, you're awesome!",
				             "Thank you!",
				             "*blush*"};
		ImageIcon icon= new ImageIcon("smile.png");
		
		JOptionPane.showOptionDialog(
		null, "You are awesome!", "Secret message", 
		JOptionPane.YES_NO_CANCEL_OPTION, 
		JOptionPane.INFORMATION_MESSAGE, icon, responses, 0);
		
	}

}
