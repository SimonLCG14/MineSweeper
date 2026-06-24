package at.matulin.view;

import at.matulin.controller.GameController;
import at.matulin.model.Board;
import at.matulin.model.Cell;
import at.matulin.model.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardPanel extends JPanel {
    private final Board model;
    private final JButton[][] buttons;

    private final ImageIcon oneTile;
    private final ImageIcon twoTile;
    private final ImageIcon threeTile;
    private final ImageIcon fourTile;
    private final ImageIcon fiveTile;
    private final ImageIcon sixTile;
    private final ImageIcon sevenTile;
    private final ImageIcon eightTile;

    private final ImageIcon coveredTile;
    private final ImageIcon flagTile;
    private final ImageIcon mineTile;
    private final ImageIcon uncoveredTile;

    private GameController controller;

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public BoardPanel(Board model) {
        this.model = model;
        this.setLayout(new GridLayout(Constants.SIZE, Constants.SIZE));
        this.buttons = new JButton[Constants.SIZE][Constants.SIZE];

        for (int i = 0; i < Constants.SIZE; i++) {
            for (int j = 0; j < Constants.SIZE; j++) {
                JButton btn = new JButton();
                btn.setFocusPainted(false);
                btn.setMargin(new Insets(0, 0, 0, 0));
                btn.setContentAreaFilled(false);
                btn.setBorderPainted(false);

                buttons[i][j] = btn;

                final int row = i;
                final int col = j;

                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (controller == null) return;

                        if (SwingUtilities.isLeftMouseButton(e)) {
                            controller.handleLeftClick(row, col);
                        }
                        else if (SwingUtilities.isRightMouseButton(e)) {
                            controller.handleRightClick(row, col);
                        }
                    }
                });

                this.add(btn);
            }
        }

        this.oneTile = loadAndScaleIcon("1-tile.png");
        this.twoTile = loadAndScaleIcon("2-tile.png");
        this.threeTile = loadAndScaleIcon("3-tile.png");
        this.fourTile = loadAndScaleIcon("4-tile.png");
        this.fiveTile = loadAndScaleIcon("5-tile.png");
        this.sixTile = loadAndScaleIcon("6-tile.png");
        this.sevenTile = loadAndScaleIcon("7-tile.png");
        this.eightTile = loadAndScaleIcon("8-tile.png");

        this.flagTile = loadAndScaleIcon("flag-tile.png");
        this.coveredTile = loadAndScaleIcon("covered-tile.png");
        this.mineTile = loadAndScaleIcon("mine-tile.png");
        this.uncoveredTile = loadAndScaleIcon("uncovered-tile.png");

        updateView();
    }

    public void updateView() {
        Cell[][] currentBoard = model.getBoard();

        for (int i = 0; i < currentBoard.length; i++) {
            for (int j = 0; j < currentBoard[i].length; j++) {
                JButton btn = buttons[i][j];
                Cell cell = currentBoard[i][j];

                btn.setIcon(this.coveredTile);

                if (cell.isFlagged()) {
                    btn.setIcon(this.flagTile);
                }
                else if (cell.isUncovered()) {
                    if (cell.isBomb()) {
                        btn.setIcon(this.mineTile);
                    } else {
                        btn.setIcon(this.uncoveredTile);

                        int amountOfBombsNearby = cell.getBombsNearby();

                        if (cell.getBombsNearby() > 0) {
                            switch(amountOfBombsNearby){
                                case 1:
                                    btn.setIcon(this.oneTile);
                                    break;

                                case 2:
                                    btn.setIcon(this.twoTile);
                                    break;

                                case 3:
                                    btn.setIcon(this.threeTile);
                                    break;

                                case 4:
                                    btn.setIcon(this.fourTile);
                                    break;

                                case 5:
                                    btn.setIcon(this.fiveTile);
                                    break;

                                case 6:
                                    btn.setIcon(this.sixTile);
                                    break;

                                case 7:
                                    btn.setIcon(this.sevenTile);
                                    break;

                                case 8:
                                    btn.setIcon(this.eightTile);
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }

    private ImageIcon loadAndScaleIcon(String filename) {
        try {
            java.net.URL imgURL = getClass().getResource("/" + filename);

            if (imgURL == null) {
                System.err.println("Konnte Bild nicht finden: " + filename);
                return new ImageIcon();
            }

            ImageIcon originalIcon = new ImageIcon(imgURL);

            Image scaledImage = originalIcon.getImage().getScaledInstance(54, 54, Image.SCALE_SMOOTH);

            return new ImageIcon(scaledImage);

        } catch (Exception e) {
            System.err.println("Fehler beim Laden von " + filename);
            return new ImageIcon();
        }
    }
}
