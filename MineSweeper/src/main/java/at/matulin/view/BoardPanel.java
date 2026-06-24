package at.matulin.view;

import at.matulin.model.Board;
import at.matulin.model.Cell;
import at.matulin.model.Constants;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private final Board model;

    public BoardPanel(Board model) {
        this.model = model;
        this.setLayout(new GridLayout(Constants.SIZE, Constants.SIZE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Cell[][] currentBoard = model.getBoard();

        for (int i = 0; i < currentBoard.length; i++) {
            for (int j = 0; j < currentBoard[i].length; j++) {
                JButton btn = new JButton();

                Cell cell = currentBoard[i][j];

                if(cell.isFlagged()){
                    btn.setBackground(new Color(0, 0, 255));
                }
                else if(cell.isUncovered()){
                    btn.setBackground(new Color(0, 255, 0));
                }
                else if(cell.isBomb()){
                    btn.setBackground(new Color(255, 0, 0));
                }

                this.add(btn);
            }
        }
    }
}
