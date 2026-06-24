package at.matulin.model;

public class Cell {
    private boolean isFlagged;
    private boolean isBomb;
    private int bombsNearby = 0;
    private boolean isUncovered;

    public Cell(boolean isBomb){
        this.setFlagged(false);
        this.setUncovered(false);
        this.setBomb(isBomb);
    }

    public int getBombsNearby() {
        return bombsNearby;
    }

    public void setBombsNearby(int bombsNearby) {
        this.bombsNearby = bombsNearby;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public boolean isUncovered() {
        return isUncovered;
    }

    public void setUncovered(boolean uncovered) {
        isUncovered = uncovered;
    }

    public boolean isBomb() {
        return isBomb;
    }

    private void setBomb(boolean bomb) {
        isBomb = bomb;
    }
}
