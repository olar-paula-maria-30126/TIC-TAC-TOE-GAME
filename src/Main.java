import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main { // The main class with the entry point
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe game = new TicTacToe(); // Create the game window
            game.setVisible(true); // Display the game window
        });
    }
}

class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3]; // 3x3 grid of buttons
    private char currentPlayer = 'X'; // Keeps track of the current player (X or O)

    public TicTacToe() {
        setTitle("Tic Tac Toe"); // Set the window title
        setSize(400, 400); // Set the window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the program when the window is closed
        setLayout(new GridLayout(3, 3)); // Create a 3x3 grid layout for the buttons

        // Initialize the buttons and add them to the grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 40)); // Set font size and style
                buttons[i][j].addActionListener(this); // Add action listener for button clicks
                add(buttons[i][j]); // Add button to the window
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        // If the button is already clicked, do nothing
        if (!clickedButton.getText().equals("")) {
            return;
        }

        // Mark the button with the current player's symbol (X or O)
        clickedButton.setText(String.valueOf(currentPlayer));

        // Check if there's a winner
        if (checkWinner()) {
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!"); // Display winner message
            resetGame(); // Reset the game board
            return;
        }

        // Switch to the next player
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private boolean checkWinner() {
        // Check rows and columns for a winning line
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText()) &&
                    !buttons[i][0].getText().equals("")) {
                return true;
            }

            if (buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[1][i].getText().equals(buttons[2][i].getText()) &&
                    !buttons[0][i].getText().equals("")) {
                return true;
            }
        }

        // Check diagonals for a winning line
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().equals("")) {
            return true;
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().equals("")) {
            return true;
        }

        return false; // No winner yet
    }

    private void resetGame() {
        // Clear all buttons and reset the current player to 'X'
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(""); // Clear button text
            }
        }
        currentPlayer = 'X'; // Reset to player X
    }
}
