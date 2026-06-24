package at.matulin.view;

import at.matulin.controller.GameController;
import at.matulin.model.Board;
import at.matulin.model.Constants;

import javax.swing.*;
import java.awt.*;

public class MineSweeperFrame extends JFrame {
    private static final int HEIGHT = Constants.WINDOW_HEIGHT;
    private static final int WIDTH = Constants.WINDOW_WIDTH;

    public MineSweeperFrame(Board board){
        this.setTitle("Minesweeper");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBackground(new Color(157, 155, 155));
        titlePanel.setBounds(0, 0, WIDTH, 100);

        JLabel titleLabel = new JLabel("Minesweeper");
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        BoardPanel boardPanel = new BoardPanel(board);
        boardPanel.setBounds(0, 100, WIDTH, Constants.WINDOW_HEIGHT - 100);

        GameController controller = new GameController(board, boardPanel);
        boardPanel.setController(controller);


        titlePanel.add(titleLabel);
        this.setVisible(true);
        this.add(titlePanel);
        this.add(boardPanel);

    }
}
