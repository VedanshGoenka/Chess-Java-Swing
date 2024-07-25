package org.cis1200.pieces;

import org.cis1200.Board;

/**
 * Represents a Rook chess piece.
 */
public class Rook extends Piece {

    private boolean hasMoved = false;

    /**
     * Constructs a Rook at the specified position and color.
     *
     * @param x       The x-coordinate of the Rook's position.
     * @param y       The y-coordinate of the Rook's position.
     * @param isWhite True if the Rook is white, false if black.
     */
    public Rook(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    /**
     * Calculates all possible locations the Rook can move to on the board.
     *
     * @param board The current state of the board.
     * @return A 2D boolean array representing possible move locations.
     */
    @Override
    public boolean[][] allLocations(Board board) {
        boolean[][] output = new boolean[8][8]; // Initialize a 2D array for possible move locations
        int x = getX(); // Get the current x-coordinate of the Rook
        int y = getY(); // Get the current y-coordinate of the Rook
        Piece[][] pieces = board.getPieces(); // Get the current state of the board pieces

        // Check vertical moves upwards
        for (int cy = y - 1; cy >= 0; cy--) {
            if (pieces[cy][x] == null) {
                output[cy][x] = true; // Empty square
            } else {
                output[cy][x] = pieces[cy][x].isWhite() != this.isWhite(); // Opponent's piece
                break; // Stop if a piece is encountered
            }
        }

        // Check vertical moves downwards
        for (int cy = y + 1; cy < 8; cy++) {
            if (pieces[cy][x] == null) {
                output[cy][x] = true; // Empty square
            } else {
                output[cy][x] = pieces[cy][x].isWhite() != this.isWhite(); // Opponent's piece
                break; // Stop if a piece is encountered
            }
        }

        // Check horizontal moves to the left
        for (int rx = x - 1; rx >= 0; rx--) {
            if (pieces[y][rx] == null) {
                output[y][rx] = true; // Empty square
            } else {
                output[y][rx] = pieces[y][rx].isWhite() != this.isWhite(); // Opponent's piece
                break; // Stop if a piece is encountered
            }
        }

        // Check horizontal moves to the right
        for (int rx = x + 1; rx < 8; rx++) {
            if (pieces[y][rx] == null) {
                output[y][rx] = true; // Empty square
            } else {
                output[y][rx] = pieces[y][rx].isWhite() != this.isWhite(); // Opponent's piece
                break; // Stop if a piece is encountered
            }
        }

        return output; // Return the array of possible move locations
    }

    /**
     * Checks if the Rook has moved.
     *
     * @return True if the Rook has moved, false otherwise.
     */
    public boolean isHasMoved() {
        return hasMoved;
    }

    /**
     * Sets the moved state of the Rook.
     *
     * @param hasMoved True if the Rook has moved, false otherwise.
     */
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}