package edu.jsu.mcis;

public class TicTacToeModel {
    
    private static final int DEFAULT_WIDTH = 3;
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a tie,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("Tie"), 
        NONE("none");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    private Mark[][] grid; /* Game grid */
    private boolean xTurn; /* True if X is current player */
    private int width;     /* Size of game grid */
    
    /* DEFAULT CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        /* No arguments (call main constructor; use default size) */
        
        this(DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create grid (width x width) as a 2D Mark array */

        grid = new TicTacToeModel.Mark[width][width];

        /* Initialize grid by filling every square with empty marks */

        for(int i = 0; i < width; i++){
            for(int j = 0; j < width; j++){
                grid[i][j] = Mark.EMPTY;
            }
        }
        
    }
	
    public boolean makeMark(int row, int col) {
        
        /* Place the current player's mark in the square at the specified
           location, but only if the location is valid and if the square is
           empty! */
        
        boolean marked = false;
        boolean validate = isValidSquare(row, col);
        if (validate){
            marked = isSquareMarked(row, col);
        }
        if(marked){
            return false;
        }

        if (validate){
            if (grid[row][col] != Mark.EMPTY){
                return false;
            }
            else{
                if(xTurn){
                    grid[row][col] = Mark.X;
                    xTurn = false;
                }
                else{
                    grid[row][col] = Mark.O;
                    xTurn = true;
                }
                return true;
            }
        }
        else{
            return false;
        }
        
    }
	
    private boolean isValidSquare(int row, int col) {
        
        /* Return true if specified location is within grid bounds */
        if (row < 0 || col < 0){
            return false;
        }
        if (row < width && col < width){
            return true;
        }
        else{
            return false;
        }
        
        
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return true if square at specified location is marked */
        
        if(grid[row][col] != Mark.EMPTY ){
            return true;
        }
        else{
            return false;
        }
            
    }
	
    public Mark getMark(int row, int col) {
        
        /* Return mark from the square at the specified location */
        
        return grid[row][col];
            
    }
	
    public Result getResult() {
        
        /* Use isMarkWin() to see if X or O is the winner, if the game is a
           tie, or if the game is not over, and return the corresponding Result
           value */
        
        boolean xWin = isMarkWin(Mark.X);
        if(xWin){
            return Result.X;
        }
        boolean oWin = isMarkWin(Mark.O);
        if(oWin){
            return Result.O;
        }
        boolean tie = isTie();
        if(tie){
            return Result.TIE;
        }
        else{
            return Result.NONE;
        }

    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
        boolean win = false;
        
        /* Checks horizontally */
        for(int i = 0; i < width; i++){
            win = true;
            for(int j = 0; j < width; j++){
                if(grid[i][j] != mark){
                    win = false;
                }
            }
            if(win){
                break;
            }
        }
        if(win){
            return true;
        }
        /* Checks vertically */
        for( int i = 0; i < width; i++){
            win = true;
            for( int j = 0; j < width; j++){
                if(grid[j][i] != mark){
                    win = false;
                }
            }
            if(win){
                break;
            }
        }
        if(win){
            return true;
        }
        /* Checks diagonally */
        win = true;
        for( int i = 0; i < width; i++){
            
            if(grid[i][i] != mark){
                win = false;
            }
        }
        if(win){
            return true;
        }
        win = true;
        int j = 0;
        for(int i = width - 1; i >= 0; i--){
            
            if (grid[i][j] != mark){
                win = false;
            }
            j++;
        }
        if(win){
            return true;
        }
        else{
            return false;
        }

    }
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */
        boolean tie = true;
        if(isMarkWin(Mark.X)){
            tie = false;
        }
        if(isMarkWin(Mark.O)){
            tie = false;
        }
        if(!tie){
            return false;
        }
        for(int i = 0; i < width; i++){
            for (int j = 0; j < width; j++){
                if(grid[i][j] == Mark.EMPTY){
                    tie = false;
                }
            }
        }
        if(tie){ return true;}
        else{ return false;}
        
    }

    public boolean isGameover() {
        
        /* Return true if the game is over */
        
        return Result.NONE != getResult();
        
    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth() {
        
        /* Getter for width */
        
        return width;
        
    }
    
}