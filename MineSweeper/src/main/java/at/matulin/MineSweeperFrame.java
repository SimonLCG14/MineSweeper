package at.matulin;

import javax.swing.*;

public class MineSweeperFrame extends JFrame {
    private static final int HEIGHT = 900;
    private static final int WIDTH = 600;

    public MineSweeperFrame(){
        this.setTitle("Minesweeper");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
