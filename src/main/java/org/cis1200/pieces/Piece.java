package org.cis1200.pieces;

import org.cis1200.Board;

public abstract class Piece {

    private int x; // The x-coordinate of the piece
    private int y; // The y-coordinate of the piece
    private boolean isWhite; // Indicates if the piece is white

    /**
     * Constructs a Piece at the specified position and color.
     *
     * @param x       The x-coordinate of the piece.
     * @param y       The y-coordinate of the piece.
     * @param isWhite True if the piece is white, false if black.
     */
    protected Piece(int x, int y, boolean isWhite) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
    }

    public int getX() {
        return x; // Returns the x-coordinate
    }

    public void setX(int x) {
        this.x = x; // Sets the x-coordinate
    }

    public int getY() {
        return y; // Returns the y-coordinate
    }

    public void setY(int y) {
        this.y = y; // Sets the y-coordinate
    }

    /**
     * Abstract method to get all possible locations for the piece.
     *
     * @param board The current game board.
     * @return A 2D boolean array representing possible locations.
     */
    public abstract boolean[][] allLocations(Board board);

    /**
     * Calculates all possible locations the piece can move to.
     *
     * @param board The current game board.
     * @return A 2D boolean array of possible move locations.
     */
    public boolean[][] allPossibleLocations(Board board) {
        boolean[][] locations = allLocations(board); // Get initial possible locations
        for (int y = 0; y < locations.length; y++) {
            for (int x = 0; x < locations[y].length; x++) {
                if (locations[y][x]) {
                    locations[y][x] = board.canMove(this, x, y); // Validate move with the board
                }
            }
        }
        return locations; // Return validated possible locations
    }

    public boolean isWhite() {
        return isWhite; // Returns if the piece is white
    }
}