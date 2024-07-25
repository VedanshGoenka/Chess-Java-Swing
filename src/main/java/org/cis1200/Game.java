package org.cis1200;

import org.cis1200.pieces.*;
import org.cis1200.ui.MovePiece;
import org.cis1200.ui.SeePossibilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the main game logic and GUI for the chess game.
 */
public class Game implements Runnable {

    final JPanel chessPanel = new JPanel(); // Panel for the chessboard
    final JPanel statusPanel = new JPanel(); // Panel for game status
    final JLabel status = new JLabel("Running"); // Status label
    Board board = new Board(); // The game board
    MovePiece[][] blueSquares = new MovePiece[8][8]; // Array for highlighting possible moves
    Map<String, List<ImageIcon>> images = new HashMap<>(); // Map for storing piece images

    /**
     * Main method to start the game.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game()); // Start the game on the Event Dispatch Thread
    }

    public Board getBoard() {
        return board; // Returns the current board
    }

    public MovePiece[][] getBlueSquares() {
        return blueSquares; // Returns the array of blue squares
    }

    public void setBlueSquares(MovePiece[][] blueSquares) {
        this.blueSquares = blueSquares; // Sets the array of blue squares
    }

    @Override
    public void run() {
        try {
            loadImages(); // Load piece images
        } catch (IOException e) {
            System.out.println("Cannot Load Images"); // Error message if images fail to load
            e.printStackTrace();
            return; // Exit if images cannot be loaded
        }

        final JFrame frame = new JFrame("Chess"); // Create the main game window
        frame.setLayout(new BorderLayout());
        frame.setSize(new Dimension(800, 600));

        chessPanel.setPreferredSize(new Dimension(600, 600)); // Set chess panel size
        chessPanel.setLayout(new GridLayout(8, 8)); // 8x8 grid layout for chessboard
        updateBoard(); // Update the board display

        frame.add(chessPanel, BorderLayout.WEST); // Add chess panel to the frame

        statusPanel.setPreferredSize(new Dimension(200, 600)); // Set status panel size
        frame.add(statusPanel, BorderLayout.EAST); // Add status panel to the frame
        statusPanel.add(status); // Add status label to the status panel

        frame.pack(); // Adjust frame size to fit components
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit on close
        frame.setVisible(true); // Make the frame visible
    }

    /**
     * Loads images for the chess pieces from the specified file paths.
     *
     * @throws IOException If an error occurs while loading images.
     */
    private void loadImages() throws IOException {
        List<ImageIcon> icons = new ArrayList<>(); // List to hold piece icons
        icons.add(new ImageIcon(ImageIO.read(new File("files/white/bishop.png"))
                .getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        icons.add(new ImageIcon(ImageIO.read(new File("files/black/bishop.png"))
                .getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        images.put(Bishop.class.getSimpleName(), icons); // Store bishop images

        icons = new ArrayList<>();
        icons.add(new ImageIcon(ImageIO.read(new File("files/white/king.png"))
                .getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        icons.add(new ImageIcon(ImageIO.read(new File("files/black/king.png"))
                .getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        images.put(King.class.getSimpleName(), icons); // Store king images

        icons = new ArrayList<>();
        icons.add(new ImageIcon(ImageIO.read(new File("files/white/knight.png"))
                .getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        icons.add(new ImageIcon(ImageIO.read(new File("files/black/knight.png"))
                .getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        images.put(Knight.class.getSimpleName(), icons); // Store knight images

        icons = new ArrayList<>();
        icons.add(new ImageIcon(ImageIO.read(new File("files/white/pawn.png"))
                .getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        icons.add(new ImageIcon(ImageIO.read(new File("files/black/pawn.png"))
                .getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        images.put(Pawn.class.getSimpleName(), icons); // Store pawn images

        icons = new ArrayList<>();
        icons.add(new ImageIcon(ImageIO.read(new File("files/white/queen.png"))
                .getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        icons.add(new ImageIcon(ImageIO.read(new File("files/black/queen.png"))
                .getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        images.put(Queen.class.getSimpleName(), icons); // Store queen images

        icons = new ArrayList<>();
        icons.add(new ImageIcon(ImageIO.read(new File("files/white/rook.png"))
                .getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        icons.add(new ImageIcon(ImageIO.read(new File("files/black/rook.png"))
                .getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        images.put(Rook.class.getSimpleName(), icons); // Store rook images
    }

    /**
     * Updates the chessboard display based on the current state of the game.
     */
    public void updateBoard() {
        chessPanel.removeAll(); // Clear the chess panel
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                final JPanel square = new JPanel(); // Create a new square panel
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Set border
                if ((x + y) % 2 == 0) {
                    square.setBackground(Color.lightGray); // Light square
                } else {
                    square.setBackground(Color.darkGray); // Dark square
                }

                Piece p = board.getPieces()[y][x]; // Get the piece at the current position

                if (p != null && p.isWhite()) {
                    ImageIcon i = images.get(p.getClass().getSimpleName()).get(0); // Get white piece image
                    JLabel j = new JLabel(i); // Create label for the image
                    if (board.isWhiteTurn()) {
                        square.addMouseListener(new SeePossibilities(p, this)); // Add mouse listener for white turn
                    }
                    square.add(j); // Add image label to the square
                }

                if (p != null && !p.isWhite()) {
                    ImageIcon i = images.get(p.getClass().getSimpleName()).get(1); // Get black piece image
                    JLabel j = new JLabel(i); // Create label for the image
                    if (!board.isWhiteTurn()) {
                        square.addMouseListener(new SeePossibilities(p, this)); // Add mouse listener for black turn
                    }
                    square.add(j); // Add image label to the square
                }

                if (blueSquares[y][x] != null) {
                    square.setBackground(Color.blue); // Highlight possible move squares
                    System.out.print(y + ":" + x); // Debug output for highlighted squares
                    square.addMouseListener(blueSquares[y][x]); // Add mouse listener for blue squares
                }

                chessPanel.add(square); // Add the square to the chess panel
            }
        }
        chessPanel.revalidate(); // Revalidate the chess panel
        chessPanel.repaint(); // Repaint the chess panel
    }

    /**
     * Advances the game to the next turn, updating the status and board.
     */
    public void nextTurn() {
        board.setWhiteTurn(!board.isWhiteTurn()); // Switch turns
        if (board.isCheckMated()) {
            status.setText("Checkmate!"); // Update status for checkmate
        } else if (board.amIChecked()) {
            status.setText("Check!"); // Update status for check
        } else {
            status.setText("Running!"); // Update status for ongoing game
        }
        updateBoard(); // Update the board display
    }
}