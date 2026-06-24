package at.matulin;

import at.matulin.model.Board;
import at.matulin.view.MineSweeperFrame;

import java.util.Scanner;

public class Main {
    static void main() {
        Board board = new Board(10);

        board.printBoard();

        Scanner sc = new Scanner(System.in);

        System.out.println();

        int x = sc.nextInt();
        int y = sc.nextInt();

        board.uncoverCell(x, y);

        System.out.println();

        board.printBoard();

    }
}
