package Windows;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Frame extends JFrame implements KeyListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel background;
	private JButton newGameButton;
	private JButton recordTableButton;
	private JButton designButton;
	private JLabel nextImage;
	private Board board;
	private RecordList highscores;
	private boolean isStar;
	
	
	public Frame(boolean isStar) {
		super("2048");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		this.isStar=isStar;
		
		background = new JLabel(new ImageIcon("Images\\background.png"));
		this.setResizable(false);
		add(background);
		background.setLayout(new FlowLayout());
		newGameButton = new JButton(new ImageIcon("Images\\newGame.png"));
		recordTableButton = new JButton(new ImageIcon("Images\\recordTableButton.png"));
		designButton = new JButton(new ImageIcon("Images\\changeDesign.png"));
		newGameButton.setBorder(BorderFactory.createEmptyBorder());
		newGameButton.setContentAreaFilled(false);
		recordTableButton.setBorder(BorderFactory.createEmptyBorder());
		recordTableButton.setContentAreaFilled(false);
		designButton.setBorder(BorderFactory.createEmptyBorder());
		designButton.setContentAreaFilled(false);
		background.add(newGameButton);
		background.add(recordTableButton);
		background.add(designButton);
		newGameButton.addActionListener(this);
		recordTableButton.addActionListener(this);
		designButton.addActionListener(this);
		this.addKeyListener(this);
		board=new Board(highscores,this);
		this.pack();
		nextImage=new JLabel(new ImageIcon("images\\next2.png"));
		background.add(board.getBoard());
		background.add(nextImage);
		background.add(board.getScoreLabel());
		if(isStar!=this.board.getChangedDesign()){
			this.board.ChaneDesign();
		
		
			background.setIcon(new ImageIcon("Images\\Background2.png"));
			newGameButton.setIcon(new ImageIcon("Images\\newGameStar.png"));
			recordTableButton.setIcon(new ImageIcon("Images\\recordTableStarButton.png"));
			designButton.setIcon(new ImageIcon("Images\\changeDesignStar.png"));
			nextImage.setIcon(new ImageIcon("Images\\next2Star.png"));
			this.board.getScoreLabel().setText("Stars collected:"+this.board.getScore().getScore());
		
		}
		
		//board.ChaneDesign();
		
		this.setSize(476, 570);
		this.pack();
		this.setVisible(true);
		this.requestFocusInWindow();
		

	}

	
	public void keyPressed(KeyEvent e) 
	{
			board.keyPressed(e);
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(newGameButton)) {
			new Frame(isStar);
			//frame.requestFocusInWindow();
			this.setVisible(false);
			this.setFocusable(false);
			board.getBoard().requestFocusInWindow();
			board.getBoard().setFocusable(true);
		}
		if (e.getSource().equals(recordTableButton)) {
			board.showHighscores();
			this.requestFocusInWindow();

		}
		if (e.getSource().equals(designButton)) {
			this.board.ChaneDesign();
			this.requestFocusInWindow();
			
			if(this.board.getChangedDesign())
			{
				background.setIcon(new ImageIcon("Images\\Background2.png"));
				newGameButton.setIcon(new ImageIcon("Images\\newGameStar.png"));
				recordTableButton.setIcon(new ImageIcon("Images\\recordTableStarButton.png"));
				designButton.setIcon(new ImageIcon("Images\\changeDesignStar.png"));
				nextImage.setIcon(new ImageIcon("Images\\next2Star.png"));
				this.board.getScoreLabel().setText("Stars collected:"+this.board.getScore().getScore());
			}
			else{
				background.setIcon(new ImageIcon("Images\\Background.png"));
				newGameButton.setIcon(new ImageIcon("Images\\newGame.png"));
				recordTableButton.setIcon(new ImageIcon("Images\\recordTableButton.png"));
				designButton.setIcon(new ImageIcon("Images\\changeDesign.png"));
				nextImage.setIcon(new ImageIcon("Images\\next2.png"));
				this.board.getScoreLabel().setText("Banana Score: " + this.board.getScore().getScore());
				
			}
			

		}

	}

	public void addListener(KeyListener k)
    {
        this.addKeyListener(k);
    }
	public static void main(String args[]) {
		Frame frame = new Frame(false);
		//frame.addKeyListener(frame);
		
	
}
}
