/*
This is a GUI based minesweeper game that looks exactly one that supplied with Windows OS.
Developed by: P K Subudhi
Contact Mail: maiktopksubudhi@gmail.com
WhatsApp: +91-8895174939
Website: www.pksubudhi.com
Tweeter: @Pksubudhi
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;
class MineSweeper {

	public static void main(String args[]) {
		Grid g = new Grid();
		BombGrid b = new BombGrid(g);
	}
}

class BombGrid implements ActionListener {
	private Grid g;
	private boolean[][] bombGrid;
	private int[][] countGrid;
	private JButton[][] buttonGrid;
	JFrame gridContainer;
	int totalNonBombs=0, clickCount=0;
	public BombGrid(Grid g) {
		this.g = g;
		bombGrid = g.getBombGrid();
		countGrid = g.getCountGrid();

		buttonGrid = new JButton[g.getNumRows()][g.getNumColumns()];
		totalNonBombs = (g.getNumRows() + g.getNumColumns()) - g.getNumBombs();
		int i, j;
		gridContainer = new JFrame();

		JButton btn;
		gridContainer.setTitle("My Game 1.0");
		gridContainer.setLocation(300,100);
		
		for(i = 0; i < bombGrid.length; i++) {
			for(j = 0; j < bombGrid[i].length; j++) {
				btn = new JButton();
				buttonGrid[i][j] = btn;
				btn.addActionListener(this);
				gridContainer.add(btn);	
			}
		}
		gridContainer.setLayout(new GridLayout(g.getNumRows(), g.getNumColumns())); 
		gridContainer.setSize(500,500);
		gridContainer.setVisible(true);   
	}
	public void actionPerformed(ActionEvent e) {
		int i, j;
		JButton source = (JButton)e.getSource();
		for(i = 0; i < bombGrid.length; i++) {
			for(j = 0; j < bombGrid[i].length; j++) {
				if(buttonGrid[i][j] == source) {
					if(bombGrid[i][j] == true) {
						revealAll();
			int result = JOptionPane.showConfirmDialog (null, "You Lost this Game!\nDo you want to play it again?","Oops!", JOptionPane.YES_NO_OPTION);
						if(result == JOptionPane.YES_OPTION) {
							gridContainer.dispose();
							new BombGrid(new Grid());
						}
						else {
							System.exit(0);
						}
					}
					else {
						if(countGrid[i][j] != 0) {
							source.setText(countGrid[i][j]+"");
							source.setEnabled(false);
						}
						else {
							source.setText("");
							source.setEnabled(false);
						}
						clickCount++;
						if(clickCount == totalNonBombs) {
							JOptionPane.showInternalMessageDialog(null, "You Won!","Congratulation", JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}
					}
				}
			}
		}
	}
	private void revealAll() {
		int i, j;
		for(i = 0; i < bombGrid.length; i++) {
			for(j = 0; j < bombGrid[i].length; j++) {
				if(bombGrid[i][j] == true) {
					buttonGrid[i][j].setText("*");
					buttonGrid[i][j].setFont(new Font("Arial", Font.BOLD, 20));
					buttonGrid[i][j].setUI(new MetalButtonUI() {
    						protected Color getDisabledTextColor() {
        							return Color.RED;
    						}
					});
				}
				else {
					if(countGrid[i][j] != 0) buttonGrid[i][j].setText(countGrid[i][j]+"");
					if(countGrid[i][j] == 1) {
						buttonGrid[i][j].setUI(new MetalButtonUI() {
    							protected Color getDisabledTextColor() {
        							return Color.BLUE;
    							}
						});
					}
					if(countGrid[i][j] == 2) {
						buttonGrid[i][j].setUI(new MetalButtonUI() {
    							protected Color getDisabledTextColor() {
        							return Color.PINK;
    							}
						});
					}
					if(countGrid[i][j] == 3) {
						buttonGrid[i][j].setUI(new MetalButtonUI() {
    							protected Color getDisabledTextColor() {
        							return Color.GREEN;
    							}
						});
					}
					if(countGrid[i][j] > 3) {
						buttonGrid[i][j].setUI(new MetalButtonUI() {
    							protected Color getDisabledTextColor() {
        							return Color.MAGENTA;
    							}
						});
					}
				}
				buttonGrid[i][j].setEnabled(false);
			}
		}
	}
}
