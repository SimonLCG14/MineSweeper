package at.matulin;

import at.matulin.model.Board;
import at.matulin.view.MineSweeperFrame;

import java.util.Scanner;

public class Main {
    static void main() {
        Board board = new Board(30);
        new MineSweeperFrame(board);
    }
}
