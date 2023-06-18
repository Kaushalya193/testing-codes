import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonMatrix1 extends Frame implements ActionListener {
    private Button[][] buttons;
    private int currentPlayer;
    private boolean gameEnded;

    public ButtonMatrix1() {
        buttons = new Button[3][3];
        currentPlayer = 1;
        gameEnded = false;

        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button();
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        setTitle("Button Matrix Game");
        setSize(300, 300);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameEnded)
            return;

        Button selectedButton = (Button) e.getSource();
        int row = -1, col = -1;

        // Find the button's position in the matrix
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == selectedButton) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        if (buttons[row][col].getLabel().equals("")) {
            buttons[row][col].setLabel(String.valueOf(currentPlayer));

            if (checkWin(row, col)) {
                showMessageDialog("Player " + currentPlayer + " wins!");
                gameEnded = true;
            } else if (checkDraw()) {
                showMessageDialog("It's a draw!");
                gameEnded = true;
            } else {
                currentPlayer = (currentPlayer == 1) ? 2 : 1;
            }
        }
    }

    private boolean checkWin(int row, int col) {
        // Check rows
        if (buttons[row][0].getLabel().equals(buttons[row][1].getLabel()) && buttons[row][0].getLabel().equals(buttons[row][2].getLabel()))
            return true;

        // Check columns
        if (buttons[0][col].getLabel().equals(buttons[1][col].getLabel()) && buttons[0][col].getLabel().equals(buttons[2][col].getLabel()))
            return true;

        // Check diagonals
        if (row == col && buttons[0][0].getLabel().equals(buttons[1][1].getLabel()) && buttons[0][0].getLabel().equals(buttons[2][2].getLabel()))
            return true;

        if (row + col == 2 && buttons[0][2].getLabel().equals(buttons[1][1].getLabel()) && buttons[0][2].getLabel().equals(buttons[2][0].getLabel()))
            return true;

        return false;
    }

    private boolean checkDraw() {
        // Check if all buttons are labeled
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getLabel().equals(""))
                    return false;
            }
        }
        return true;
    }

    private void showMessageDialog(String message) {
        Frame parent = new Frame();
        MessageBox messageBox = new MessageBox(parent, message);
        messageBox.setVisible(true);
    }

    public static void main(String[] args) {
        new ButtonMatrix();
    }
}

class MessageBox extends Dialog {
    public MessageBox(Frame parent, String message) {
        super(parent, "Game Over", true);
        setLayout(new FlowLayout());
        setSize(200, 100);
        setLocationRelativeTo(parent);

        Label label = new Label(message);
        Button closeButton = new Button("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(label);
        add(closeButton);
    }
}
