package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TicTacToeView extends JPanel implements ActionListener {
    
    private static final long serialVersionUID = 1L;

    TicTacToeModel model;

    private JButton[][] squares;
    private JPanel squaresPanel;
    private JLabel resultLabel;

    public TicTacToeView(TicTacToeModel model) {

        this.model = model;

        int width = model.getWidth();

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        squares = new JButton[width][width];
        squaresPanel = new JPanel(new GridLayout(width,width));
        resultLabel = new JLabel();
        resultLabel.setName("ResultLabel");
        
        for (int row = 0; row < width; row++){
            
            for (int col = 0; col < width; col++){
                
                squares[row][col] = new JButton(); 
                squares[row][col].addActionListener(this);
                squares[row][col].setName("Square" + row + col);
                squares[row][col].setPreferredSize(new Dimension(64,64));
                squaresPanel.add(squares[row][col]);
                
            }
            
        }

        this.add(squaresPanel);
        this.add(resultLabel);
        
        resultLabel.setText("Welcome to Tic-Tac-Toe!");

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        /* Handle button clicks.  Extract the row and col values from the name
           of the button that was clicked, then make the mark in the grid using
           the Model's "makeMark()" method.  Finally, use the "updateSquares()"
           method to refresh the View.  If the game is over, show the result
           (from the Model's "getResult()" method) in the result label. */
        
        String name = ((JButton) event.getSource()).getName(); // Get button name
        
        int row = 0;
        int col = 0;
		int rowUniCode = name.codePointAt(6);
		int colUniCode = name.codePointAt(7);
		
		if (rowUniCode == 48) {
			row = 0;
		}
		if (rowUniCode == 49) {
			row = 1;
		}
		if (rowUniCode == 50) {
			row = 2;
		}
		if (colUniCode == 48) {
			col = 0;
		}
		if (colUniCode == 49) {
			col = 1;
		}
		if (colUniCode == 50) {
			col = 2;
		}
		//make mark
		model.makeMark(row, col);
		
		//update squares
		updateSquares();
		
		//Test to see if game has ended
		if (model.getResult() == TicTacToeModel.Result.X) {
			resultLabel.setText("X");
			model.isGameover();
		}
		if (model.getResult() == TicTacToeModel.Result.O) {
			resultLabel.setText("O");
			model.isGameover();
		}
		if (model.getResult() == TicTacToeModel.Result.TIE) {
			resultLabel.setText("TIE");
			model.isGameover();
		}

    }
        
    public void updateSquares() {

        /* Loop through all View buttons and (re)set the text of each button
           to reflect the grid contents (use the Model's "getMark()" method). */

        int width = model.getWidth();
        String mark = "";
        for(int i = 0; i < width; i++){
            for (int j = 0; j < width; j++){
                mark = (model.getMark(i, j)).toString();
                squares[i][j].setText(mark);
            }
        
        }
    }
        
    public void showResult(String message) {
        resultLabel.setText(message);
    }

}
