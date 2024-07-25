package org.cis1200.pieces;

import org.cis1200.Board;

/**
 * Represents a Pawn chess piece.
 */
public class Pawn extends Piece {

    /**
     * Constructs a Pawn at the specified position and color.
     *
     * @param x       The x-coordinate of the Pawn's position.
     * @param y       The y-coordinate of the Pawn's position.
     * @param isWhite True if the Pawn is white, false if black.
     */
    public Pawn(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    /**
     * Calculates all possible locations the Pawn can move to on the board.
     *
     * @param board The current state of the board.
     * @return A 2D boolean array representing possible move locations.
     */
    @Override
    public boolean[][] allLocations(Board board) {
        boolean[][] output = new boolean[8][8]; // Initialize a 2D array for possible move locations
        int x = getX(); // Get the current x-coordinate of the Pawn
        int y = getY(); // Get the current y-coordinate of the Pawn

        if (isWhite()) { // Logic for white Pawn movement
            // Check for double move from starting position
            if (y == 6) {
                if (board.getPieces()[y - 1][x] == null && board.getPieces()[y - 2][x] == null) {
                    output[y - 2][x] = true; // Two squares forward
                }
            }
            // Check for single move forward
            if (board.getPieces()[y - 1][x] == null) {
                output[y - 1][x] = true; // One square forward
            }
            // Check for capture left
            if (x != 0 && board.getPieces()[y - 1][x - 1] != null &&
                    board.getPieces()[y - 1][x - 1].isWhite() != this.isWhite()) {
                output[y - 1][x - 1] = true; // Capture left
            }
            // Check for capture right
            if (x != 7 && board.getPieces()[y - 1][x + 1] != null &&
                    board.getPieces()[y - 1][x + 1].isWhite() != this.isWhite()) {
                output[y - 1][x + 1] = true; // Capture right
            }
            // Check for en passant
            if (board.isDoubleMove()) {
                Piece lastMoved = board.getLastMoved();
                if (Math.abs(lastMoved.getX() - x) == 1 && lastMoved.getY() == y) {
                    output[lastMoved.getY() - 1][lastMoved.getX()] = true; // En passant capture
                }
            }

        } else { // Logic for black Pawn movement
            // Check for double move from starting position
            if (y == 1) {
                if (board.getPieces()[y + 1][x] == null && board.getPieces()[y + 2][x] == null) {
                    output[y + 2][x] = true; // Two squares forward
                }
            }
            // Check for single move forward
            if (board.getPieces()[y + 1][x] == null) {
                output[y + 1][x] = true; // One square forward
            }
            // Check for capture left
            if (x != 0 && board.getPieces()[y + 1][x - 1] != null &&
                    board.getPieces()[y + 1][x - 1].isWhite() != this.isWhite()) {
                output[y + 1][x - 1] = true; // Capture left
            }
            // Check for capture right
            if (x != 7 && board.getPieces()[y + 1][x + 1] != null &&
                    board.getPieces()[y + 1][x + 1].isWhite() != this.isWhite()) {
                output[y + 1][x + 1] = true; // Capture right
            }
            // Check for en passant
            if (board.isDoubleMove()) {
                Piece lastMoved = board.getLastMoved();
                if (Math.abs(lastMoved.getX() - x) == 1 && lastMoved.getY() == y) {
                    output[lastMoved.getY() + 1][lastMoved.getX()] = true; // En passant capture
                }
            }
        }
        return output; // Return the array of possible move locations
    }
}