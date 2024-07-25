package org.cis1200.ui;

import org.cis1200.Game;
import org.cis1200.pieces.King;
import org.cis1200.pieces.Piece;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Displays possible moves for a selected chess piece.
 */
public class SeePossibilities implements MouseListener {

    private final Piece p; // The selected piece
    private final Game g; // The game instance

    /**
     * Constructs a SeePossibilities instance.
     *
     * @param p The selected piece.
     * @param g The game instance.
     */
    public SeePossibilities(Piece p, Game g) {
        this.p = p;
        this.g = g;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Create a 2D array to hold possible move squares
        MovePiece[][] bs = new MovePiece[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                // Check if the piece can move to the specified location
                if (p.allPossibleLocations(g.getBoard())[y][x]) {
                    bs[y][x] = new MovePiece(p, g, x, y);
                }
            }
        }
        // Handle castling for the King
        if (p instanceof King) {
            boolean[] castling = g.getBoard().canCastle();
            if (castling[0]) {
                bs[p.getY()][p.getX() - 3] = new MovePiece(p, g, p.getX() - 3, p.getY());
            }
            if (castling[1]) {
                bs[p.getY()][p.getX() + 2] = new MovePiece(p, g, p.getX() + 2, p.getY());
            }
        }
        // Set the blue squares for possible moves and update the board
        g.setBlueSquares(bs);
        g.updateBoard();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // No action needed on mouse pressed
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // No action needed on mouse released
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // No action needed on mouse entered
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // No action needed on mouse exited
    }
}