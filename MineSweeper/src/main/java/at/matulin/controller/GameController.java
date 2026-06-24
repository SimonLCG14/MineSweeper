package at.matulin.controller;

import at.matulin.model.Board;
import at.matulin.model.Cell;
import at.matulin.view.BoardPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;

public class GameController extends MouseAdapter {
    private final Board model;
    private final BoardPanel view;
    private boolean isGameOver = false;

    public GameController(Board model, BoardPanel view) {
        this.model = model;
        this.view = view;
    }

    public void handleRightClick(int row, int col) {
        if (this.isGameOver) return;

        Cell cell = this.model.getCellAt(row, col);

        if (cell != null && !cell.isUncovered()) {
            cell.setFlagged(!cell.isFlagged());

            this.view.updateView();
            this.checkWinCondition();
        }
    }

    public void handleLeftClick(int row, int col) {
        if (this.isGameOver) return;

        Cell cell = this.model.getCellAt(row, col);

        if (cell == null || cell.isUncovered() || cell.isFlagged()) {
            return;
        }

        if (cell.isBomb()) {
            this.isGameOver = true;

            for (Cell[] rowArray : this.model.getBoard()) {
                for (Cell currentCell : rowArray) {
                    if (currentCell.isBomb()) {
                        currentCell.setUncovered(true);
                    }
                }
            }

            JOptionPane.showMessageDialog(null, "Game over - You hit a bomb");
        }
        else {
            this.model.uncoverCell(row, col);
        }

        this.view.updateView();
        this.checkWinCondition();
    }

    private void checkWinCondition() {
        if (this.model.isGameFinished()) {
            this.isGameOver = true;

            for (Cell[] rowArray : this.model.getBoard()) {
                for (Cell cell : rowArray) {
                    if (cell.isBomb() && !cell.isFlagged()) {
                        cell.setFlagged(true);
                    }
                }
            }
            this.view.updateView();

            JOptionPane.showMessageDialog(null, "You won the game!");
        }
    }
}
