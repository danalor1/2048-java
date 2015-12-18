package Windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.io.EOFException;
import java.io.File;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Board implements KeyListener, ActionListener {

	private Square[][] boardPieces = new Square[4][4];
	private JPanel board;
	private Timer t;
	private JLabel scoreLabel;
	private Score score;
	private boolean changeDesign;
	private Winner win;
	private RecordList highscores;
	private Frame main;
	private boolean isWinner;

	public Board(RecordList highscores, Frame main) {
		Dimension boardSize = new Dimension(300, 300);
		board = new JPanel(new GridLayout(4, 4)); // was (0,4);
		board.setBorder(new LineBorder(Color.BLACK));
		this.main = main;
		isWinner = false;
		board.setPreferredSize(boardSize);
		board.setBounds(0, 0, boardSize.width, boardSize.height);
		for (int i = 0; i < boardPieces.length; i = i + 1)
			for (int j = 0; j < boardPieces.length; j = j + 1) {
				Square button = new Square();
				button.setImage("Images\\white.png");
				button.setBackground(Color.WHITE);
				boardPieces[i][j] = button;
				boardPieces[i][j].setValue(0);

			}

		this.placeNewCell();
		this.placeNewCell();
		for (int i = 0; i < boardPieces.length; i = i + 1)
			for (int j = 0; j < boardPieces.length; j = j + 1) {
				board.add(boardPieces[i][j]);
			}
		score = new Score();
		scoreLabel = new JLabel();
		if (!changeDesign)
			scoreLabel.setText("Banana Score: " + score.getScore());
		else
			scoreLabel.setText("Stars collected:" + score.getScore());
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setFont(scoreLabel.getFont().deriveFont(30f));
		t = new Timer(10, this);
		board.setSize(476, 570);
		board.setVisible(true);
		board.requestFocusInWindow();
		// win = false;
		changeDesign = false;
		this.loadHighscores();
	}

	public void ChaneDesign() {
		this.changeDesign = !changeDesign;
		for (int i = 0; i < boardPieces.length; i = i + 1)
			for (int j = 0; j < boardPieces.length; j = j + 1) {
				boardPieces[i][j].resetImage(changeDesign);
			}
	}

	public boolean getChangedDesign() {
		return this.changeDesign;
	}

	public JLabel getScoreLabel() {
		return this.scoreLabel;
	}

	public boolean isBoardFull() {
		boolean flag = true;
		for (int i = 0; i < boardPieces.length && flag; i = i + 1)
			for (int j = 0; j < boardPieces.length && flag; j = j + 1)
				if (boardPieces[i][j].getValue() == 0)
					flag = false;
		return flag;
	}

	public Score getScore() {
		return this.score;
	}

	public void placeNewCell() {

		int randomRow = (int) Math.floor(Math.random() * 4);
		int randomCol = (int) Math.floor(Math.random() * 4);
		while (boardPieces[randomRow][randomCol].getValue() != 0
				&& !isBoardFull()) {
			randomRow = (int) Math.floor(Math.random() * 4);
			randomCol = (int) Math.floor(Math.random() * 4);
		}
		if (!isBoardFull()) {
			int chance = (int) Math.floor(Math.random() * 4);
			if (chance > 0) {
				if (!changeDesign)
					boardPieces[randomRow][randomCol].setImage("Images\\2.png");
				else
					boardPieces[randomRow][randomCol]
							.setImage("Images\\2star.png");
				boardPieces[randomRow][randomCol].setValue(2);

			} else {
				if (!changeDesign)
					boardPieces[randomRow][randomCol].setImage("Images\\4.png");
				else
					boardPieces[randomRow][randomCol]
							.setImage("Images\\2star.png");
				boardPieces[randomRow][randomCol].setValue(4);
			}
		}
		if (!isMovesLeft()) {
			Lose loser = new Lose(changeDesign, this.score, highscores);
			loser.setVisible(true);
			this.saveHighscores();
			main.setVisible(false);
		}
	}

	public boolean isMovesLeft() {
		if (!isBoardFull())
			return true;
		else {
			for (int i = 0; i < 4; i = i + 1) {
				Square notEmpty = null;
				Square notEmpty2 = null;
				for (int j = 0; j < 4; j = j + 1) {

					if (boardPieces[i][j].getValue() != 0) {
						if (notEmpty == null)
							notEmpty = boardPieces[i][j];
						if (notEmpty2 == null)
							notEmpty2 = boardPieces[j][i];
						else if (boardPieces[i][j].getValue() == notEmpty
								.getValue()
								|| boardPieces[j][i].getValue() == notEmpty2
										.getValue())
							return true;
						notEmpty = boardPieces[i][j];
						notEmpty2 = boardPieces[j][i];
					}

				}
			}

		}
		return false;

	}

	public JPanel getBoard() {
		return this.board;
	}

	private void saveHighscores() {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(
					"highScore");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					fileOutputStream);
			objectOutputStream.writeObject(highscores.getRecordTable());
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	private void loadHighscores() {
		try {
			File highscoresFile = new File("highScore");
			ObjectInputStream objectInputStream = new ObjectInputStream(
					new FileInputStream(highscoresFile));
			highscores = new RecordList(
					((List<Record>) objectInputStream.readObject()));
			objectInputStream.close();
		} catch (EOFException fileNotFoundException) {
			highscores = new RecordList();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			highscores = new RecordList();
		}
	}

	public void showHighscores() {
		highscores.sort();
		new RecWin(this.changeDesign, highscores);

	}

	public boolean mergeLeft() {
		boolean flag = false;
		for (int i = 0; i < 4; i = i + 1) {
			Square notEmpty = null;
			for (int j = 0; j < 4; j = j + 1) {

				if (boardPieces[i][j].getValue() != 0) {
					if (notEmpty == null)
						notEmpty = boardPieces[i][j];
					else if (boardPieces[i][j].getValue() == notEmpty
							.getValue()) {
						notEmpty.setValue(notEmpty.getValue() * 2);
						flag = true;
						score.updateScore(notEmpty.getValue());
						if (!isWinner && notEmpty.getValue() == 2048) {
							win = new Winner(changeDesign, this.score,
									highscores);
							this.saveHighscores();
							isWinner = true;
						}
						if (!changeDesign)
							scoreLabel.setText("Banana Score: "
									+ score.getScore());
						else
							scoreLabel.setText("Stars collected:"
									+ score.getScore());
						boardPieces[i][j].setValue(0);
						notEmpty.setChanged(true);
						notEmpty.resetImage(changeDesign);
						boardPieces[i][j].resetImage(changeDesign);
						notEmpty = null;
						this.board.repaint();
					} else
						notEmpty = boardPieces[i][j];
				}
			}
		}
		return flag;
	}

	public boolean moveLeft() {
		boolean flag = false;
		for (int i = 0; i < 4; i = i + 1) {
			int Empty = 0;
			for (int j = 0; j < 4; j = j + 1) {

				if (boardPieces[i][j].getValue() != 0) {
					boardPieces[i][Empty]
							.setValue(boardPieces[i][j].getValue());
					if (j != Empty)
						boardPieces[i][j].setValue(0);
					boardPieces[i][Empty].resetImage(changeDesign);
					boardPieces[i][j].resetImage(changeDesign);
					if (j != Empty)
						flag = true;
					Empty = Empty + 1;

				}
			}
		}
		this.board.repaint();
		return flag;
	}

	public boolean mergeRight() {
		boolean flag = false;
		for (int i = 0; i < 4; i = i + 1) {
			Square notEmpty = null;
			for (int j = 3; j >= 0; j = j - 1) {

				if (boardPieces[i][j].getValue() != 0) {
					if (notEmpty == null)
						notEmpty = boardPieces[i][j];
					else if (boardPieces[i][j].getValue() == notEmpty
							.getValue()) {
						notEmpty.setValue(notEmpty.getValue() * 2);
						score.updateScore(notEmpty.getValue());
						if (!isWinner && notEmpty.getValue() == 2048) {
							win = new Winner(changeDesign, this.score,
									highscores);
							this.saveHighscores();
							isWinner = true;
						}
						if (!changeDesign)
							scoreLabel.setText("Banana Score: "
									+ score.getScore());
						else
							scoreLabel.setText("Stars collected:"
									+ score.getScore());
						boardPieces[i][j].setValue(0);
						notEmpty.setChanged(true);
						notEmpty.resetImage(changeDesign);
						boardPieces[i][j].resetImage(changeDesign);
						notEmpty = null;
						this.board.repaint();
					} else
						notEmpty = boardPieces[i][j];
				}
			}
		}
		return flag;
	}

	public boolean moveRight() {
		boolean flag = false;
		for (int i = 0; i < 4; i = i + 1) {
			int Empty = 3;
			for (int j = 3; j >= 0; j = j - 1) {

				if (boardPieces[i][j].getValue() != 0) {
					boardPieces[i][Empty]
							.setValue(boardPieces[i][j].getValue());
					if (j != Empty)
						boardPieces[i][j].setValue(0);
					boardPieces[i][Empty].resetImage(changeDesign);
					boardPieces[i][j].resetImage(changeDesign);
					if (j != Empty)
						flag = true;
					Empty = Empty - 1;

				}
			}
		}
		this.board.repaint();
		return flag;
	}

	public boolean mergeUp() {
		boolean flag = false;
		for (int i = 0; i < 4; i = i + 1) {
			Square notEmpty = null;
			for (int j = 0; j < 4; j = j + 1) {

				if (boardPieces[j][i].getValue() != 0) {
					if (notEmpty == null)
						notEmpty = boardPieces[j][i];
					else if (boardPieces[j][i].getValue() == notEmpty
							.getValue()) {
						notEmpty.setValue(notEmpty.getValue() * 2);
						score.updateScore(notEmpty.getValue());
						if (!isWinner && notEmpty.getValue() == 2048) {
							win = new Winner(changeDesign, this.score,
									highscores);
							this.saveHighscores();
							isWinner = true;
						}
						if (!changeDesign)
							scoreLabel.setText("Banana Score: "
									+ score.getScore());
						else
							scoreLabel.setText("Stars collected:"
									+ score.getScore());
						boardPieces[j][i].setValue(0);
						notEmpty.setChanged(true);
						notEmpty.resetImage(changeDesign);
						boardPieces[j][i].resetImage(changeDesign);
						notEmpty = null;
						flag = true;
						this.board.repaint();
					} else
						notEmpty = boardPieces[j][i];
				}
			}
		}
		return flag;
	}

	public boolean moveUp() {
		boolean flag = false;
		for (int i = 0; i < 4; i = i + 1) {
			int Empty = 0;
			for (int j = 0; j < 4; j = j + 1) {

				if (boardPieces[j][i].getValue() != 0) {
					boardPieces[Empty][i]
							.setValue(boardPieces[j][i].getValue());
					if (j != Empty)
						boardPieces[j][i].setValue(0);
					boardPieces[Empty][i].resetImage(changeDesign);
					boardPieces[j][i].resetImage(changeDesign);
					if (j != Empty)
						flag = true;
					Empty = Empty + 1;

				}
			}
		}
		this.board.repaint();
		return flag;
	}

	public boolean mergeDown() {
		boolean flag = false;
		for (int i = 0; i < 4; i = i + 1) {
			Square notEmpty = null;
			for (int j = 3; j >= 0; j = j - 1) {

				if (boardPieces[j][i].getValue() != 0) {
					if (notEmpty == null)
						notEmpty = boardPieces[j][i];
					else if (boardPieces[j][i].getValue() == notEmpty
							.getValue()) {
						notEmpty.setValue(notEmpty.getValue() * 2);
						this.score.updateScore(notEmpty.getValue());
						if (!isWinner && notEmpty.getValue() == 2048) {
							win = new Winner(changeDesign, this.score,
									highscores);
							this.saveHighscores();
							isWinner = true;
						}

						if (!changeDesign)
							scoreLabel.setText("Banana Score: "
									+ score.getScore());
						else
							scoreLabel.setText("Stars collected:"
									+ score.getScore());
						boardPieces[j][i].setValue(0);
						notEmpty.setChanged(true);
						notEmpty.resetImage(changeDesign);
						boardPieces[j][i].resetImage(changeDesign);
						notEmpty = null;
						flag = true;
						this.board.repaint();
					} else
						notEmpty = boardPieces[j][i];
				}
			}
		}
		return flag;
	}

	public boolean moveDown() {
		boolean flag = false;
		for (int i = 0; i < 4; i = i + 1) {
			int Empty = 3;
			for (int j = 3; j >= 0; j = j - 1) {

				if (boardPieces[j][i].getValue() != 0) {
					boardPieces[Empty][i]
							.setValue(boardPieces[j][i].getValue());
					if (j != Empty)
						boardPieces[j][i].setValue(0);
					boardPieces[Empty][i].resetImage(changeDesign);
					boardPieces[j][i].resetImage(changeDesign);
					if (j != Empty)
						flag = true;
					Empty = Empty - 1;

				}
			}
		}
		this.board.repaint();
		return flag;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			boolean merged = this.mergeLeft();
			boolean moved = this.moveLeft();
			if (merged || moved)
				this.placeNewCell();
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			boolean merged = this.mergeRight();
			boolean moved = this.moveRight();
			if (merged || moved)
				this.placeNewCell();

		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			boolean merged = this.mergeUp();
			boolean moved = this.moveUp();
			if (merged || moved)
				this.placeNewCell();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			boolean merged = this.mergeDown();
			boolean moved = this.moveDown();
			if (merged || moved)
				this.placeNewCell();
		}
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
		// TODO Auto-generated method stub
		if (e.getSource() == t) {

		}
	}

}
