package Windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Winner extends JFrame implements ActionListener {
	
	private boolean isWinner;
	private JLabel background;
	private JButton continueButton;
	
	
	public Winner(boolean isStar,Score score,RecordList highscores)
	{
		super("2048");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		if(!isStar)
        background = new JLabel(new ImageIcon("Images\\winnerBackground.png"));
		else
			background = new JLabel(new ImageIcon("Images\\WinnerStar.png"));	
		add(background);
		this.setResizable(false);
		this.setSize(476, 570);
		this.pack();
		this.setVisible(true);
		this.requestFocusInWindow();
		if(!isStar)
		continueButton=new JButton(new ImageIcon("Images\\continue.png"));
		else
			continueButton=new JButton(new ImageIcon("Images\\continueStar.png"));
		continueButton.setBorder(BorderFactory.createEmptyBorder());
		continueButton.setContentAreaFilled(false);
		background.setLayout(new FlowLayout());
		background.add(continueButton);
		continueButton.addActionListener(this);
		ImageIcon newImage;
		if(!isStar)
		newImage=new ImageIcon("Images\\addNameImage.png");
		else
			newImage=new ImageIcon("Images\\newImageStar.png");
		String askForName = (String)JOptionPane.showInputDialog(
                this,
                "Type your name to enter the wall of fame\n",
                "",
                JOptionPane.PLAIN_MESSAGE,
                newImage,
                null,
                "Player Name Here");
		Record rec;
		
		if(askForName!=null && askForName.length()>0)
		{
			
			rec=new Record(score.getScore(), askForName);
			highscores.addRecord(rec);
			
		}
		//else
			//setLabel("You dont have a name?");
			
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(continueButton))
			this.setVisible(false);
	}

}
