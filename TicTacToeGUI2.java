import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI2 {
    private JButton[][] buttons;
    private JFrame frame;
    private int currentPlayer;
    private boolean gameOver;

    public TicTacToeGUI2() {
        buttons = new JButton[3][3];
        frame = new JFrame("Tic-Tac-Toe");
        currentPlayer = 1;
        gameOver = false;

        initializeGUI();
    }

    private void initializeGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                frame.add(buttons[i][j]);
            }
        }

        frame.setVisible(true);
    }

    private void makeMove(int row, int col) {
        if (!gameOver && buttons[row][col].getText().isEmpty()) {
            buttons[row][col].setText(currentPlayer == 1 ? "1" : "2");
            buttons[row][col].setEnabled(false);
            currentPlayer = (currentPlayer == 1) ? 2 : 1;

            if (checkWin(currentPlayer == 1 ? "X" : "O")) {
                JOptionPane.showMessageDialog(frame, "Player " + (currentPlayer == 1 ? "X" : "O") + " wins!");
                gameOver = true;
            } else if (checkDraw()) {
                JOptionPane.showMessageDialog(frame, "It's a draw!");
                gameOver = true;
            }
        }
    }

    private boolean checkWin(String symbol) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(symbol)
                    && buttons[i][1].getText().equals(symbol)
                    && buttons[i][2].getText().equals(symbol)) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (buttons[0][j].getText().equals(symbol)
                    && buttons[1][j].getText().equals(symbol)
                    && buttons[2][j].getText().equals(symbol)) {
                return true;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(symbol)
                && buttons[1][1].getText().equals(symbol)
                && buttons[2][2].getText().equals(symbol)) {
            return true;
        }
        if (buttons[0][2].getText().equals(symbol)
                && buttons[1][1].getText().equals(symbol)
                && buttons[2][0].getText().equals(symbol)) {
            return true;
        }

        return false;
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) {
            makeMove(row, col);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TicTacToeGUI2();
            }
        });
    }
}
