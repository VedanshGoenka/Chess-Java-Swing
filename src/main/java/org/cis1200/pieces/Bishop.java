package org.cis1200.pieces;

import org.cis1200.Board;

/**
 * Represents a Bishop chess piece.
 */
public class Bishop extends Piece {

    /**
     * Constructs a Bishop at the specified position and color.
     *
     * @param x       The x-coordinate of the Bishop's position.
     * @param y       The y-coordinate of the Bishop's position.
     * @param isWhite True if the Bishop is white, false if black.
     */
    public Bishop(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    /**
     * Calculates all possible locations the Bishop can move to on the board.
     *
     * @param board The current state of the board.
     * @return A 2D boolean array representing possible move locations.
     */
    @Override
    public boolean[][] allLocations(Board board) {
        boolean[][] output = new boolean[8][8];
        Piece[][] pieces = board.getPieces();
        int cy = getY() - 1;
        int rx = getX() - 1;

        // Check diagonal moves to the top-left
        while (rx >= 0 && cy >= 0) {
            if (pieces[cy][rx] == null) {
                output[cy][rx] = true;
            } else {
                output[cy][rx] = pieces[cy][rx].isWhite() != this.isWhite();
                break;
            }
            cy--;
            rx--;
        }

        cy = getY() + 1;
        rx = getX() - 1;

        // Check diagonal moves to the bottom-left
        while (rx >= 0 && cy < 8) {
            if (pieces[cy][rx] == null) {
                output[cy][rx] = true;
            } else {
                output[cy][rx] = pieces[cy][rx].isWhite() != this.isWhite();
                break;
            }
            cy++;
            rx--;
        }

        cy = getY() - 1;
        rx = getX() + 1;

        // Check diagonal moves to the top-right
        while (rx < 8 && cy >= 0) {
            if (pieces[cy][rx] == null) {
                output[cy][rx] = true;
            } else {
                output[cy][rx] = pieces[cy][rx].isWhite() != this.isWhite();
                break;
            }
            cy--;
            rx++;
        }

        cy = getY() + 1;
        rx = getX() + 1;

        // Check diagonal moves to the bottom-right
        while (rx < 8 && cy < 8) {
            if (pieces[cy][rx] == null) {
                output[cy][rx] = true;
            } else {
                output[cy][rx] = pieces[cy][rx].isWhite() != this.isWhite();
                break;
            }
            cy++;
            rx++;
        }
        return output;
    }
}