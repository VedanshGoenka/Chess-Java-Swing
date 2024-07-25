package org.cis1200.pieces;

import org.cis1200.Board;

/**
 * Represents a King chess piece.
 */
public class King extends Piece {

    private boolean hasMoved = false;

    /**
     * Constructs a King at the specified position and color.
     *
     * @param x       The x-coordinate of the King's position.
     * @param y       The y-coordinate of the King's position.
     * @param isWhite True if the King is white, false if black.
     */
    public King(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    /**
     * Calculates all possible locations the King can move to on the board.
     *
     * @param board The current state of the board.
     * @return A 2D boolean array representing possible move locations.
     */
    @Override
    public boolean[][] allLocations(Board board) {
        boolean[][] output = new boolean[8][8];
        for (int ry = 0; ry < 8; ry++) {
            for (int cx = 0; cx < 8; cx++) {
                // Check if the move is within one square in any direction
                if ((Math.abs(getY() - ry) <= 1 && Math.abs(getX() - cx) <= 1)) {
                    if (board.getPieces()[ry][cx] == null) {
                        output[ry][cx] = true; // Empty square
                    } else if (board.getPieces()[ry][cx].isWhite() != this.isWhite()) {
                        output[ry][cx] = true; // Opponent's piece
                    }
                }
            }
        }
        output[getY()][getX()] = false; // The King's current position is not a valid move
        return output;
    }

    /**
     * Checks if the King has moved.
     *
     * @return True if the King has moved, false otherwise.
     */
    public boolean isHasMoved() {
        return hasMoved;
    }

    /**
     * Sets the moved state of the King.
     *
     * @param hasMoved True if the King has moved, false otherwise.
     */
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}