package at.matulin.model;

import java.util.Random;

public class Board {
    private final int SIZE = 16;

    private Cell[][] board;

    public Board(int bombSpawningChancePercentage){
        if(bombSpawningChancePercentage > 100){
            bombSpawningChancePercentage = 100;
        }
        else if(bombSpawningChancePercentage < 0){
            bombSpawningChancePercentage = 0;
        }

        this.initializeBoard(bombSpawningChancePercentage);
    }

    public Cell[][] getBoard(){
        return this.board.clone();
    }

    public Cell getCellAt(int x, int y){
        if(x >= SIZE || x < 0 || y >= SIZE || y < 0){
            return null;
        }

        return this.board[x][y];
    }

    public int getRemainingBombs(){
        int remainingBombs = 0;

        for (Cell[] cells : this.board) {
            for (Cell cell : cells) {
                if (cell.isBomb() && !cell.isFlagged()) {
                    remainingBombs++;
                }
            }
        }

        return remainingBombs;
    }

    private void initializeBoard(int spawningChance){
        this.board = new Cell[SIZE][SIZE];

        Random random = new Random();

        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j < this.board[i].length; j++){
                int rndNumber = random.nextInt(100);

                boolean generateBomb = rndNumber < spawningChance;

                this.board[i][j] = new Cell(generateBomb);
            }
        }

        this.calculateBombsNearby();
    }

    private void calculateBombsNearby(){
        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j < this.board[i].length; j++){
                int amountOfBombs = -1;

                if(!this.board[i][j].isBomb()){
                    amountOfBombs = 0;

                    boolean hasRightBound = i + 1 < SIZE;
                    boolean hasLeftBound = i - 1 >= 0;

                    boolean hasLowerBound = j + 1 < SIZE;
                    boolean hasUpperBound = j - 1 >= 0;

                    if(hasUpperBound){
                        if(this.board[i][j - 1].isBomb()){
                            amountOfBombs++;
                        }
                    }

                    if(hasLowerBound){
                        if(this.board[i][j + 1].isBomb()){
                            amountOfBombs++;
                        }
                    }

                    if(hasRightBound){
                        if(this.board[i + 1][j].isBomb()){
                            amountOfBombs++;
                        }

                        if(hasUpperBound){
                            if(this.board[i + 1][j - 1].isBomb()){
                                amountOfBombs++;
                            }
                        }

                        if(hasLowerBound){
                            if(this.board[i + 1][j + 1].isBomb()){
                                amountOfBombs++;
                            }
                        }
                    }

                    if(hasLeftBound){
                        if(this.board[i - 1][j].isBomb()){
                            amountOfBombs++;
                        }

                        if(hasUpperBound){
                            if(this.board[i - 1][j - 1].isBomb()){
                                amountOfBombs++;
                            }
                        }

                        if(hasLowerBound){
                            if(this.board[i - 1][j + 1].isBomb()){
                                amountOfBombs++;
                            }
                        }
                    }
                }

                this.board[i][j].setBombsNearby(amountOfBombs);
            }
        }
    }

    public void uncoverCell(int x, int y) {
        boolean isOutOfBounds = x >= SIZE || x < 0 || y >= SIZE || y < 0;

        if(isOutOfBounds){
            return;
        }

        boolean isBomb = this.board[x][y].isBomb();
        boolean hasNearByBombs = this.board[x][y].getBombsNearby() > 0;
        boolean isAlreadyUncovered = this.board[x][y].isUncovered();

        if(isBomb || hasNearByBombs || isAlreadyUncovered){
            return;
        }

        this.board[x][y].setUncovered(true);

        this.uncoverCell(x + 1, y + 1);
        this.uncoverCell(x + 1, y - 1);
        this.uncoverCell(x - 1, y + 1);
        this.uncoverCell(x - 1, y - 1);
        this.uncoverCell(x, y + 1);
        this.uncoverCell(x, y - 1);
        this.uncoverCell(x + 1, y);
        this.uncoverCell(x - 1, y);
    }

    public void printBoard(){
        for (Cell[] cells : this.board) {
            for (Cell cell : cells) {
                if(cell.isBomb()){
                    System.out.print("| B       |");
                }
                else{
                    System.out.printf("| %d %b |", cell.getBombsNearby(), cell.isUncovered());
                }
            }

            System.out.println();
        }
    }

    public boolean isGameFinished(){
        for (Cell[] cells : this.board) {
            for (Cell cell : cells) {
                if(cell.isBomb() && !cell.isFlagged()){
                    return false;
                }
            }
        }

        return true;
    }
}
