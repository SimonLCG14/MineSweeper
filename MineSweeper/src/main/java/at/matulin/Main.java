package at.matulin;

import at.matulin.model.Board;
import at.matulin.view.MineSweeperFrame;

public class Main {
    static void main() {
        Board board = new Board(20);

        new MineSweeperFrame(board);
    }
}
