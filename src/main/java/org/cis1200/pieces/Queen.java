package org.cis1200.pieces;

import org.cis1200.Board;

/**
 * Represents a Queen chess piece.
 */
public class Queen extends Piece {

    /**
     * Constructs a Queen at the specified position and color.
     *
     * @param x       The x-coordinate of the Queen's position.
     * @param y       The y-coordinate of the Queen's position.
     * @param isWhite True if the Queen is white, false if black.
     */
    public Queen(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    /**
     * Calculates all possible locations the Queen can move to on the board.
     *
     * @param board The current state of the board.
     * @return A 2D boolean array representing possible move locations.
     */
    @Override
    public boolean[][] allLocations(Board board) {
        boolean[][] output = new boolean[8][8];
        int x = getX();
        int y = getY();
        Piece[][] pieces = board.getPieces();

        // Check vertical and horizontal moves (Rook-like behavior)
        for (int cy = y - 1; cy >= 0; cy--) {
            if (pieces[cy][x] == null) {
                output[cy][x] = true; // Empty square
            } else {
                output[cy][x] = pieces[cy][x].isWhite() != this.isWhite(); // Opponent's piece
                break;
            }
        }
        for (int cy = y + 1; cy < 8; cy++) {
            if (pieces[cy][x] == null) {
                output[cy][x] = true; // Empty square
            } else {
                output[cy][x] = pieces[cy][x].isWhite() != this.isWhite(); // Opponent's piece
                break;
            }
        }
        for (int rx = x - 1; rx >= 0; rx--) {
            if (pieces[y][rx] == null) {
                output[y][rx] = true; // Empty square
            } else {
                output[y][rx] = pieces[y][rx].isWhite() != this.isWhite(); // Opponent's piece
                break;
            }
        }
        for (int rx = x + 1; rx < 8; rx++) {
            if (pieces[y][rx] == null) {
                output[y][rx] = true; // Empty square
            } else {
                output[y][rx] = pieces[y][rx].isWhite() != this.isWhite(); // Opponent's piece
                break;
            }
        }

        // Check diagonal moves (Bishop-like behavior)
        for (int cy = y - 1, rx = x - 1; cy >= 0 && rx >= 0; cy--, rx--) {
            if (pieces[cy][rx] == null) {
                output[cy][rx] = true; // Empty square
            } else {
                output[cy][rx] = pieces[cy][rx].isWhite() != this.isWhite(); // Opponent's piece
                break;
            }
        }
        for (int cy = y - 1, rx = x + 1; cy >= 0 && rx < 8; cy--, rx++) {
            if (pieces[cy][rx] == null) {
                output[cy][rx] = true; // Empty square
            } else {
                output[cy][rx] = pieces[cy][rx].isWhite() != this.isWhite(); // Opponent's piece
                break;
            }
        }
        for (int cy = y + 1, rx = x - 1; cy < 8 && rx >= 0; cy++, rx--) {
            if (pieces[cy][rx] == null) {
                output[cy][rx] = true; // Empty square
            } else {
                output[cy][rx] = pieces[cy][rx].isWhite() != this.isWhite(); // Opponent's piece
                break;
            }
        }
        for (int cy = y + 1, rx = x + 1; cy < 8 && rx < 8; cy++, rx++) {
            if (pieces[cy][rx] == null) {
                output[cy][rx] = true; // Empty square
            } else {
                output[cy][rx] = pieces[cy][rx].isWhite() != this.isWhite(); // Opponent's piece
                break;
            }
        }

        return output;
    }
}