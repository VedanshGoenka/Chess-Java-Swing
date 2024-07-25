package org.cis1200.ui;

import org.cis1200.Game;
import org.cis1200.pieces.King;
import org.cis1200.pieces.Pawn;
import org.cis1200.pieces.Piece;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Handles the movement of chess pieces in the game.
 */
public class MovePiece implements MouseListener {

    private final Piece p; // The piece to be moved
    private final Game g; // The game instance
    private final int x; // Target x-coordinate
    private final int y; // Target y-coordinate

    /**
     * Constructs a MovePiece instance.
     *
     * @param p The piece to be moved.
     * @param g The game instance.
     * @param x The target x-coordinate.
     * @param y The target y-coordinate.
     */
    public MovePiece(Piece p, Game g, int x, int y) {
        this.p = p;
        this.g = g;
        this.x = x;
        this.y = y;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Handle pawn movement
        if (p instanceof Pawn && Math.abs(p.getX() - x) == 1 &&
                g.getBoard().getPieces()[y][x] == null) {
            g.getBoard().move(g.getBoard().getLastMoved(), x, y);
        }
        // Handle king movement
        if (p instanceof King) {
            if (Math.abs(p.getX() - x) == 2) {
                g.getBoard().move(g.getBoard().getPieces()[p.getY()][7], 5, p.getY());
            }
            if (Math.abs(p.getX() - x) == 3) {
                g.getBoard().move(g.getBoard().getPieces()[p.getY()][0], 3, p.getY());
            }
        }
        // Move the king or the piece to the target position
        if (p instanceof King && Math.abs(p.getX() - x) == 3) {
            g.getBoard().move(p, x + 1, y);
        } else {
            g.getBoard().move(p, x, y);
        }
        // Reset blue squares and proceed to the next turn
        g.setBlueSquares(new MovePiece[8][8]);
        g.nextTurn();
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