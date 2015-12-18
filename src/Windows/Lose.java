package Windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Lose extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JLabel background;
	private boolean isStar;
	private JButton newGameButton;
	
	public Lose(boolean isStar,Score score,RecordList highscores)
	{
		super("2048");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		this.isStar=isStar;
		
		if(!isStar){
         background = new JLabel(new ImageIcon("Images\\loserBackground.png"));
         newGameButton = new JButton(new ImageIcon("Images\\newGame.png"));
		}
		else{
		 background = new JLabel(new ImageIcon("Images\\loserBackgroundStar.png"));
		 newGameButton = new JButton(new ImageIcon("Images\\newGameStar.png"));
		}
		add(background);
		newGameButton.setBorder(BorderFactory.createEmptyBorder());
		newGameButton.setContentAreaFilled(false);
		background.setLayout(new FlowLayout());
		background.add(newGameButton);
		newGameButton.addActionListener(this);
		ImageIcon newImage;
		this.setResizable(false);
		this.setSize(476, 570);
		this.pack();
		this.setVisible(true);
		this.requestFocusInWindow();
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(newGameButton)){
			new Frame(isStar);
		this.setVisible(false);
		this.setFocusable(false);
		}
		
	}

}
