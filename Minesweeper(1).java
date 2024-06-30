import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Minesweeper extends JFrame {

    private static final int ROWS = 4;
    private static final int COLS = 3;
    private static final int NUM_MINES = 3;

    private JButton[][] buttons = new JButton[ROWS][COLS];
    private boolean[][] mines = new boolean[ROWS][COLS];
    private JLabel gameState;
    private boolean gameOver = false;
    private int easterEggStep = 0;

    public Minesweeper() {
        setTitle("Minesweeper");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        
        gameState = new JLabel(":)", SwingConstants.CENTER);
        gameState.setFont(new Font("Arial", Font.BOLD, 24));
        add(gameState, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(ROWS, COLS));
        add(panel, BorderLayout.CENTER);


        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 20));
                final int r = row;
                final int c = col;
                buttons[row][col].addActionListener(e -> handleButtonClick(r, c));
                panel.add(buttons[row][col]);
            }
        }
        placeMines();

        setVisible(true);
    }

    private void placeMines() {
        Random rand = new Random();
        int minesPlaced = 0;
        while (minesPlaced < NUM_MINES) {
            int row = rand.nextInt(ROWS);
            int col = rand.nextInt(COLS);
            if (!mines[row][col]) {
                mines[row][col] = true;
                minesPlaced++;
            }
        }
    }

    private void handleButtonClick(int row, int col) {
        if (gameOver) return;

        if (mines[row][col]) {
            buttons[row][col].setText("X");
            buttons[row][col].setBackground(Color.RED);
            gameState.setText("xD");
            gameOver = true;
            revealAllMines();
        } else {
            buttons[row][col].setText("");
            buttons[row][col].setEnabled(false);
            gameState.setText("BD");
            checkEasterEgg(row, col);
        }
    }

    private void revealAllMines() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (mines[row][col]) {
                    buttons[row][col].setText("X");
                    buttons[row][col].setBackground(Color.RED);
                }
            }
        }
    }

    private void checkEasterEgg(int row, int col) {
        // Sekwencja Easter Egg: naciśnięcie (0,0) -> (0,1) -> (0,2) w tej kolejności
        if (easterEggStep == 0 && row == 0 && col == 0) {
            easterEggStep++;
        } else if (easterEggStep == 1 && row == 0 && col == 1) {
            easterEggStep++;
        } else if (easterEggStep == 2 && row == 0 && col == 2) {
            JOptionPane.showMessageDialog(this, "Boom!", "Easter Egg", JOptionPane.INFORMATION_MESSAGE);
            easterEggStep = 0;
        } else {
            easterEggStep = 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Minesweeper::new);
    }
}
