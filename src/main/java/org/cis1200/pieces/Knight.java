package org.cis1200.pieces;

import org.cis1200.Board;

/**
 * Represents a Knight chess piece.
 */
public class Knight extends Piece {

    /**
     * Constructs a Knight at the specified position and color.
     *
     * @param x       The x-coordinate of the Knight's position.
     * @param y       The y-coordinate of the Knight's position.
     * @param isWhite True if the Knight is white, false if black.
     */
    public Knight(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    /**
     * Calculates all possible locations the Knight can move to on the board.
     *
     * @param board The current state of the board.
     * @return A 2D boolean array representing possible move locations.
     */
    @Override
    public boolean[][] allLocations(Board board) {
        boolean[][] output = new boolean[8][8];
        for (int ry = 0; ry < 8; ry++) {
            for (int cx = 0; cx < 8; cx++) {
                // Check for Knight's L-shaped moves
                if ((Math.abs(getY() - ry) == 2 && Math.abs(getX() - cx) == 1) ||
                    (Math.abs(getY() - ry) == 1 && Math.abs(getX() - cx) == 2)) {
                    if (board.getPieces()[ry][cx] == null) {
                        output[ry][cx] = true; // Empty square
                    } else if (board.getPieces()[ry][cx].isWhite() != this.isWhite()) {
                        output[ry][cx] = true; // Opponent's piece
                    }
                }
            }
        }
        return output;
    }
}