package org.cis1200;

import org.cis1200.pieces.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the chessboard and manages the game state, including piece positions,
 * turn management, and game rules such as check and checkmate.
 */
public class Board {

    private final List<Piece> whitePieces = new ArrayList<>(); // List of white pieces
    private final List<Piece> blackPieces = new ArrayList<>(); // List of black pieces
    private Piece[][] pieces = new Piece[8][8]; // 2D array representing the board
    private boolean isWhiteTurn = true; // Indicates if it's white's turn
    private Piece lastMoved = null; // The last moved piece
    private boolean doubleMove = false; // Indicates if a double move has occurred

    /**
     * Initializes the board with the starting positions of all pieces.
     */
    public Board() {
        Piece temp = new King(4, 7, true);
        whitePieces.add(temp);
        pieces[7][4] = temp;
        for (int i = 0; i < pieces[6].length; i++) {
            temp = new Pawn(i, 6, true);
            whitePieces.add(temp);
            pieces[6][i] = temp;
        }
        temp = new Rook(0, 7, true);
        whitePieces.add(temp);
        pieces[7][0] = temp;
        temp = new Knight(1, 7, true);
        whitePieces.add(temp);
        pieces[7][1] = temp;
        temp = new Bishop(2, 7, true);
        whitePieces.add(temp);
        pieces[7][2] = temp;
        temp = new Queen(3, 7, true);
        whitePieces.add(temp);
        pieces[7][3] = temp;
        temp = new Bishop(5, 7, true);
        whitePieces.add(temp);
        pieces[7][5] = temp;
        temp = new Knight(6, 7, true);
        whitePieces.add(temp);
        pieces[7][6] = temp;
        temp = new Rook(7, 7, true);
        whitePieces.add(temp);
        pieces[7][7] = temp;

        temp = new King(4, 0, false);
        blackPieces.add(temp);
        pieces[0][4] = temp;
        for (int i = 0; i < pieces[1].length; i++) {
            temp = new Pawn(i, 1, false);
            blackPieces.add(temp);
            pieces[1][i] = temp;
        }
        temp = new Rook(0, 0, false);
        blackPieces.add(temp);
        pieces[0][0] = temp;
        temp = new Knight(1, 0, false);
        blackPieces.add(temp);
        pieces[0][1] = temp;
        temp = new Bishop(2, 0, false);
        blackPieces.add(temp);
        pieces[0][2] = temp;
        temp = new Queen(3, 0, false);
        blackPieces.add(temp);
        pieces[0][3] = temp;
        temp = new Bishop(5, 0, false);
        blackPieces.add(temp);
        pieces[0][5] = temp;
        temp = new Knight(6, 0, false);
        blackPieces.add(temp);
        pieces[0][6] = temp;
        temp = new Rook(7, 0, false);
        blackPieces.add(temp);
        pieces[0][7] = temp;
    }

    public List<Piece> getWhitePieces() {
        return whitePieces; // Returns the list of white pieces
    }

    public List<Piece> getBlackPieces() {
        return blackPieces; // Returns the list of black pieces
    }

    public Piece[][] getPieces() {
        return pieces; // Returns the current state of the board
    }

    public void setPieces(Piece[][] pieces) {
        this.pieces = pieces; // Sets the board state to the given pieces
    }

    public Piece getLastMoved() {
        return lastMoved; // Returns the last moved piece
    }

    public void setLastMoved(Piece lastMoved) {
        this.lastMoved = lastMoved; // Sets the last moved piece
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn; // Returns true if it's white's turn
    }

    public void setWhiteTurn(boolean whiteTurn) {
        isWhiteTurn = whiteTurn; // Sets the turn to the specified color
    }

    public boolean isDoubleMove() {
        return doubleMove; // Returns true if a double move has occurred
    }

    public void setDoubleMove(boolean doubleMove) {
        this.doubleMove = doubleMove; // Sets the double move status
    }

    /**
     * Checks if the current player can castle.
     *
     * @return An array indicating if white and black can castle.
     */
    public boolean[] canCastle() {
        boolean[] output = new boolean[2];
        if (isWhiteTurn) {
            King king = (King) whitePieces.get(0);
            if (king.isHasMoved()) {
                return output; // Cannot castle if the king has moved
            }
            if (pieces[7][0] instanceof Rook) {
                if (!((Rook) pieces[7][0]).isHasMoved()) {
                    output[0] = true; // Check for queenside castling
                    for (int i = 1; i < 4; i++) {
                        if (pieces[7][i] != null) {
                            output[0] = false; // Cannot castle if pieces are in the way
                            break;
                        }
                    }
                }
                if (!((Rook) pieces[7][7]).isHasMoved()) {
                    output[1] = true; // Check for kingside castling
                    for (int i = 6; i > 4; i--) {
                        if (pieces[7][i] != null) {
                            output[1] = false; // Cannot castle if pieces are in the way
                            break;
                        }
                    }
                }
            }
        } else {
            King king = (King) blackPieces.get(0);
            if (king.isHasMoved()) {
                return output; // Cannot castle if the king has moved
            }
            if (pieces[0][0] instanceof Rook) {
                if (!((Rook) pieces[0][0]).isHasMoved()) {
                    output[0] = true; // Check for queenside castling
                    for (int i = 1; i < 4; i++) {
                        if (pieces[0][i] != null) {
                            output[0] = false; // Cannot castle if pieces are in the way
                            break;
                        }
                    }
                }
                if (!((Rook) pieces[0][7]).isHasMoved()) {
                    output[1] = true; // Check for kingside castling
                    for (int i = 6; i > 4; i--) {
                        if (pieces[0][i] != null) {
                            output[1] = false; // Cannot castle if pieces are in the way
                            break;
                        }
                    }
                }
            }
        }
        return output; // Returns the castling eligibility
    }

    /**
     * Checks if the current player's king is in check.
     *
     * @return true if the king is in check, false otherwise.
     */
    public boolean amIChecked() {
        if (isWhiteTurn) {
            int kx = whitePieces.get(0).getX();
            int ky = whitePieces.get(0).getY();
            for (Piece p : blackPieces) {
                if (p.allLocations(this)[ky][kx]) {
                    return true; // King is in check
                }
            }
        } else {
            int kx = blackPieces.get(0).getX();
            int ky = blackPieces.get(0).getY();
            for (Piece p : whitePieces) {
                if (p.allLocations(this)[ky][kx]) {
                    return true; // King is in check
                }
            }
        }
        return false; // King is not in check
    }

    /**
     * Checks if the current player is checkmated.
     *
     * @return true if the player is checkmated, false otherwise.
     */
    public boolean isCheckMated() {
        if (isWhiteTurn) {
            for (Piece p : whitePieces) {
                boolean[][] b = p.allPossibleLocations(this);
                for (boolean[] booleans : b) {
                    for (boolean aBoolean : booleans) {
                        if (aBoolean) {
                            return false; // There are available moves
                        }
                    }
                }
            }

        } else {
            for (Piece p : blackPieces) {
                boolean[][] b = p.allPossibleLocations(this);
                for (boolean[] booleans : b) {
                    for (boolean aBoolean : booleans) {
                        if (aBoolean) {
                            return false; // There are available moves
                        }
                    }
                }
            }

        }
        return true; // No available moves, player is checkmated
    }

    /**
     * Validates if a piece can move to a specified location without putting the king in check.
     *
     * @param p  The piece to move.
     * @param rx The target x-coordinate.
     * @param cy The target y-coordinate.
     * @return true if the move is valid, false otherwise.
     */
    public boolean canMove(Piece p, int rx, int cy) {
        Piece[][] copy = new Piece[pieces.length][pieces[0].length];
        for (int y = 0; y < pieces.length; y++) {
            System.arraycopy(pieces[y], 0, copy[y], 0, pieces[y].length);
        }
        Piece cachedPiece = pieces[cy][rx];

        if (cachedPiece != null) {
            if (isWhiteTurn) {
                blackPieces.remove(cachedPiece); // Remove captured piece
            } else {
                whitePieces.remove(cachedPiece); // Remove captured piece
            }
        }
        pieces[cy][rx] = p; // Move the piece
        int oldX = p.getX();
        int oldY = p.getY();
        pieces[oldY][oldX] = null; // Clear the old position
        p.setX(rx);
        p.setY(cy);
        boolean canMove = !amIChecked(); // Check if the move puts the king in check
        p.setX(oldX);
        p.setY(oldY);
        pieces = copy; // Restore the board state
        if (cachedPiece != null) {
            if (isWhiteTurn) {
                blackPieces.add(cachedPiece); // Restore captured piece
            } else {
                whitePieces.add(cachedPiece); // Restore captured piece
            }
        }
        return canMove; // Return the validity of the move
    }

    /**
     * Moves a piece to a specified location and updates the game state accordingly.
     *
     * @param p  The piece to move.
     * @param rx The target x-coordinate.
     * @param cy The target y-coordinate.
     */
    public void move(Piece p, int rx, int cy) {
        Piece cachedPiece = pieces[cy][rx];
        if (cachedPiece != null) {
            if (isWhiteTurn) {
                blackPieces.remove(cachedPiece); // Remove captured piece
            } else {
                whitePieces.remove(cachedPiece); // Remove captured piece
            }
        }
        if (p instanceof Pawn) {
            doubleMove = Math.abs(p.getY() - cy) == 2; // Check for double move
        } else {
            doubleMove = false; // Reset double move status
        }
        pieces[cy][rx] = p; // Move the piece
        pieces[p.getY()][p.getX()] = null; // Clear the old position
        p.setX(rx);
        p.setY(cy);
        lastMoved = p; // Update the last moved piece
        if (p instanceof King) {
            ((King) p).setHasMoved(true); // Mark king as moved
        }
        if (p instanceof Rook) {
            ((Rook) p).setHasMoved(true); // Mark rook as moved
        }
    }
}