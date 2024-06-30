import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BinaryPuzzleGame extends JFrame {


    private JLabel[] stateLabels = new JLabel[4];

    private JLabel rewardLabel;

    private JButton[] interactionButtons = new JButton[4];
    //Poprawna kolejność                      V
    private final String correctSequence = "2130";

    private StringBuilder userInput = new StringBuilder();

    public BinaryPuzzleGame() {
        setTitle("Binary Puzzle Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        JPanel statePanel = new JPanel();
        statePanel.setLayout(new FlowLayout());
        for (int i = 0; i < 4; i++) {
            stateLabels[i] = new JLabel("0");
            stateLabels[i].setPreferredSize(new Dimension(50, 50));
            stateLabels[i].setOpaque(true);
            stateLabels[i].setBackground(Color.LIGHT_GRAY);
            stateLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
            statePanel.add(stateLabels[i]);
        }
        add(statePanel, BorderLayout.NORTH);
        rewardLabel = new JLabel("Solve the puzzle!");
        rewardLabel.setPreferredSize(new Dimension(200, 50));
        rewardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(rewardLabel, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        for (int i = 0; i < 4; i++) {
            final int index = i;
            interactionButtons[i] = new JButton(String.valueOf(i));
            interactionButtons[i].setPreferredSize(new Dimension(50, 50));
            interactionButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleButtonClick(index);
                }
            });
            buttonPanel.add(interactionButtons[i]);
        }
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleButtonClick(int index) {
        userInput.append(index);
        updateStateLabels(index);

        if (userInput.length() == correctSequence.length()) {
            if (userInput.toString().equals(correctSequence)) {
                JOptionPane.showMessageDialog(this, "Congratz, you won");
                resetGame(true);
            } else {
                JOptionPane.showMessageDialog(this, "Nope, try again");
                resetGame(false);
            }
        }
    }

    private void updateStateLabels(int index) {
        stateLabels[index].setText("1");
        stateLabels[index].setBackground(Color.GREEN);
    }

    private void resetGame(boolean won) {
        userInput.setLength(0);
        for (JLabel label : stateLabels) {
            label.setText("0");
            label.setBackground(Color.LIGHT_GRAY);
        }
        if (won) {
            rewardLabel.setText("You win!");
        } else {
            rewardLabel.setText("Solve the puzzle!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BinaryPuzzleGame().setVisible(true);
            }
        });
    }
}
