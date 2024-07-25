# CIS 1200 Game Project README

**PennKey:** vedanshg  
**Date:** December 11th, 2022

## Core Concepts

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. **Complex Game Logic - Chess**  
  The check functionality was implemented using a method in the `Board` class that iterates through
  pieces to identify potential threats to the king. This approach allowed for modifying the list of
  allowed moves, preventing pieces from moving into check or leaving the king vulnerable. When a
  check occurs, the algorithm re-evaluates moves, filtering for those that resolve the check.

  Checkmate is implemented by exhausting all possible moves for both players, effectively halting
  the game state. The movement state of the king and rook is tracked using boolean variables,
  which determine castling eligibility.

  En passant was a bit tricky, but I managed to implement it by adding an additional entry to the
  allowed moves when conditions are met. When this move is selected, an alternative move path is
  executed using a "ghost piece" mechanism to capture the pawn.

  2. **2D Arrays**  
  The game uses two primary 2D arrays: `Piece[][]` for the board state and `boolean[][]` for legal
  move matrices. This choice is appropriate due to the fixed size of the chessboard and the
  simplicity of representing spatial relationships. The board state is accessed through a `getBoard()`
  method, allowing the game state to be passed around efficiently.

  3. **Subtyping/Inheritance**  
  The implementation leverages dynamic dispatch to reduce complexity. The abstract `Piece` class
  serves as a parent for all chess piece types. This allows for type-specific logic to be
  implemented while maintaining a common interface. It was particularly useful for special moves
  like en passant, castling, and check detection.

  4. **Collections**  
  Collections were used for managing both game state and UI elements. `ArrayLists` store active
  pieces, allowing for efficient addition and removal during captures. For UI, a 2D array of
  `MovePiece` objects represents possible moves when a piece is selected. A `HashMap` was used for
  image management, keying each piece type to its corresponding image icons. This simplified
  asset loading and unloading.

## Your Implementation

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  **Piece:** Abstract base class for all chess pieces. Manages position, color, and defines the
    interface for move generation.
  
  **Bishop, King, Knight, Pawn, Queen, Rook:** Subclasses of `Piece`, each implementing piece-specific
    move generation logic. Notable implementations include `Pawn`'s en passant and double-move,
    and `King` and `Rook` tracking movement state for castling eligibility.
  
  **Board:** Manages game state, including piece positions and turn management. Implements core game
    logic such as move validation, check detection, and castling eligibility. The `canMove` method
    simulates moves to validate their legality without altering the game state.
  
  **Game:** Serves as the main controller, managing the UI and game flow. Responsible for asset
    loading, board rendering, and turn progression.
  
  **MovePiece:** Implements the logic for executing moves, including special cases like en passant
    and castling.
  
  **SeePossibilities:** Handles the UI logic for displaying possible moves when a piece is selected,
    including special cases for castling.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

  Implementing chess proved more challenging than anticipated. Coordinating between event
  listeners, core game logic, and UI components required multiple iterations. Debugging movement
  logic was time-consuming, often involving subtle issues like off-by-one errors or incorrect
  comparison operators. Implementing advanced chess rules such as check, en passant, and castling
  required careful consideration and multiple revisions. The use of `Swing` for GUI development
  presented a learning curve, necessitating frequent reference to documentation and examples.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

  The design achieves a commendable separation between game logic and UI components, which proved
  invaluable during debugging. State encapsulation is generally well-implemented, with most game
  state managed within the `Board` class and piece-specific state contained within respective
  subclasses.

  Potential refactoring opportunities include:
  1. Further abstraction of movement logic, particularly for pieces like `Rook` and `Queen`, where
     similar directional logic is repeated. A parameterized approach using direction vectors could
     reduce code duplication.
  2. Enhancement of the GUI to provide more features and improve user experience.
  3. Implementation of a more robust state management system to simplify undo/redo functionality
     and potentially support game saving/loading.

  While I'm generally satisfied with the current implementation, these refinements could enhance
  code maintainability and expand game features.

## External Resources

- Cite any external resources (images, tutorials, etc.) that you may have used while implementing your game.
  - [Java Swing Library Documentation](https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html)
  - [Chess Rules Reference](https://en.wikipedia.org/wiki/Rules_of_chess)
  - [Chess Piece Images](https://github.com/GiorgioMegrelli/chess.com-boards-and-pieces/tree/master/pieces/3d_chesskid)
  - StackOverflow for Java-specific issues (e.g., static method references in non-static contexts)
  - IntelliJ IDEA for code analysis and refactoring suggestions
  - [Concurrency issue resolution](https://rollbar.com/blog/java-concurrentmodificationexception/)
  - Special thanks to @looklotsofpeople for additional support

P.S. If any part of this README seems slightly disjointed, it's because I may have been
burning the midnight oil a bit. Poor time management.
