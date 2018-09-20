package edu.jsu.mcis;

public class TicTacToeView {

    private TicTacToeModel model;
    
    /* CONSTRUCTOR */
	
    public TicTacToeView(TicTacToeModel model) {
        
        this.model = model;
        
    }
	
    public void viewModel() {
        
        /* Print the board to the console (see examples) */
        
        int width = model.getWidth();
        System.out.println();
        System.out.print("  ");
        for(int i =0; i < width; i++){
            System.out.print(i);
            if(i == width - 1){
                System.out.println();
            }
        }
        System.out.println();
        for(int i =0; i < width; i++){
            System.out.print(i + " ");
            for(int j = 0; j < width; j++){
                System.out.print(model.getMark(i, j));
            }
            System.out.println();
        }
        System.out.println();

    }

    public void showNextMovePrompt() {

        /* Display a prompt for the player's next move (see examples) */

        boolean turn = model.isXTurn();
        if(turn){
            System.out.println("Player 1 (X) Move:");
        }
        else{
            System.out.println("Player 2 (O) Move:");
        }
        System.out.println("Enter the row and column numbers, separated by a space: ");

    }

    public void showInputError() {

        /* Display an error if input is invalid (see examples) */

        System.out.println("Input Error");

    }

    public void showResult(String r) {

        /* Display final winner */

        System.out.println(r + "!");

    }
	
}