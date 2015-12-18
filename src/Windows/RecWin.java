package Windows;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
public class RecWin extends JFrame implements ActionListener{
	

		private static final long serialVersionUID = 1L;
		private static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 14);
		private static final Font HEADER_FONT = new Font("Arial", Font.PLAIN, 18);
		private JLabel background;
		private RecordList highscores;
		private JTable scoresTable;
		private JButton continueB;
		
		public RecWin(boolean isStars,RecordList highs)
		{
			super("2048");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setFocusable(false);
			setLayout(new GridBagLayout());
			if(!isStars)
	        background = new JLabel(new ImageIcon("Images\\recordTable.png"));
			else
				 background = new JLabel(new ImageIcon("Images\\recordTableStar.png"));
			add(background);
			background.setLayout(new FlowLayout());
			highscores=highs;
			this.setSize(476, 570);
			this.setResizable(false);
			this.setVisible(true);
			
			GridBagConstraints viewConstraints = new GridBagConstraints();
			viewConstraints.anchor = GridBagConstraints.NORTHEAST;
			//viewConstraints.insets = new Insets(20, 0, 0, 30);
			viewConstraints.insets = new Insets(0, 0,0, 0);
			// buttons left panel
			JPanel leftPanel = new JPanel();
			leftPanel.setOpaque(false);
			leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
			add(leftPanel, viewConstraints);
			
			// highscores panel (right)
			JPanel rightPanel = new JPanel();
			rightPanel.setOpaque(false);		// set transparent panel
			rightPanel.setLayout(new GridBagLayout());
			GridBagConstraints rightConstraints = new GridBagConstraints();
			rightConstraints.insets = new Insets(0, 0, 20, 0);
			
			
			rightConstraints.insets = new Insets(0, 0, 10, 0);
			scoresTable=new JTable();
			
			// data table UI configuration (to be transparent, not focusable, etc.
			scoresTable.setShowHorizontalLines(false);
			scoresTable.setShowVerticalLines(false);
			scoresTable.setFocusable(false);
			scoresTable.setRowSelectionAllowed(false);
			scoresTable.getTableHeader().setFont(HEADER_FONT);
			
			if(!isStars)
				scoresTable.getTableHeader().setBackground(new Color(253,234,120));
				else
					scoresTable.getTableHeader().setBackground(new Color(200,207,228));
			//scoresTable.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
			scoresTable.setFont(TEXT_FONT);
			scoresTable.setFocusable(false);
			scoresTable.getTableHeader().setReorderingAllowed(false);
			scoresTable.setFillsViewportHeight(true);
			scoresTable.getTableHeader().setOpaque(false);
			scoresTable.setRowHeight(30);
			scoresTable.setRowMargin(20);
			
			if(!isStars)
			scoresTable.setBackground(new Color(253,234,105));
			else
				scoresTable.setBackground(new Color(200,207,228));
			//scoresTable.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
			
			scoresTable.setModel(highscores);
			scoresTable.getColumnModel().getColumn(0).setMinWidth(200);
			scoresTable.getColumnModel().getColumn(1).setMinWidth(100);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			scoresTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
			scoresTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
			
			
			JLabel helpImage=new JLabel(new ImageIcon("Images\\tt.png"));
			JLabel helpImage2=new JLabel(new ImageIcon("Images\\tt.png"));
			JLabel helpImage3=new JLabel(new ImageIcon("Images\\tt.png"));
			JLabel helpImage4=new JLabel(new ImageIcon("Images\\tt2.png"));
			
			background.add(helpImage);
			background.add(helpImage2);
			background.add(helpImage3);
			
			
			JPanel tableContainer = new JPanel();
			tableContainer.setLayout(new BorderLayout());
			background.add(scoresTable);
			scoresTable.repaint();
			scoresTable.setVisible(true);
			
			
			// add right panel to the view
        	rightConstraints.gridy =1;
        	rightConstraints.gridx=1;
        	background.add(scoresTable.getTableHeader(), BorderLayout.PAGE_START);
        	background.add(scoresTable, BorderLayout.PAGE_START);
        	background.add(tableContainer);
			
			rightPanel.add(tableContainer, rightConstraints);
			
			viewConstraints.weighty = 1.0;	// workaround for placing the table in the top of the view instead of in the center
			add(rightPanel, viewConstraints);
			continueB=new JButton(new ImageIcon("Images\\continueRec.png"));
			continueB.setBorder(BorderFactory.createEmptyBorder());
			continueB.setContentAreaFilled(false);
			continueB.addActionListener(this);
			background.add(helpImage4);
			background.add(continueB);
			/*Insets insets = background.getInsets();

			background.setBounds(150 + insets.left, 15 + insets.top,
		              50,20);*/
			

			
			
		
		}
		public void setHighscores(RecordList highs) {
			highscores = highs;
			scoresTable.setModel(highs);
			// update columns width
			
		}
		public void paintComponent(Graphics g) {
		   // scale the background image and paint it in the view's center
			Image scaledImage = new ImageIcon("Images\\recordTable.png").getImage();
			int x = (this.getWidth() - scaledImage.getWidth(null)) / 2;
		    int y = (this.getHeight() - scaledImage.getHeight(null)) / 2;
		    g.drawImage(scaledImage, x, y, null);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(continueB))
			{
				this.setVisible(false);
			}
			
		}
		
		

	}


