import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class cal extends JFrame implements ActionListener {

    JLabel display;
    double oldValue = 0;
    String operator = "";

    public cal() {

        setTitle("Cal");
        setSize(100, 55);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);

        display = new JLabel();
        display.setBounds(20, 20, 340, 80);
        display.setOpaque(true);
        display.setBackground(Color.WHITE);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 30));
        add(display);

        String[] btnText = {
                "7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "x",
                ".", "0", "=", "/",
                "Clear"
        };

        int x = 20;
        int y = 130;

        for (int i = 0; i < btnText.length; i++) {

            JButton btn = new JButton(btnText[i]);

            if (btnText[i].equals("Clear")) {
                btn.setBounds(20, 430, 340, 60);
            } else {
                btn.setBounds(x, y, 70, 70);

                x += 90;

                if ((i + 1) % 4 == 0) {
                    x = 20;
                    y += 90;
                }
            }

            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(this);

            if ("+-x/".contains(btnText[i])) {
                btn.setBackground(Color.YELLOW);
            } else if (btnText[i].equals("Clear")) {
                btn.setBackground(Color.RED);
            } else {
                btn.setBackground(Color.WHITE);
            }

            add(btn);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String text = ((JButton) e.getSource()).getText();

        if ("0123456789".contains(text)) {
            display.setText(display.getText() + text);
        }

        else if (text.equals(".")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        }

        else if (text.equals("+") || text.equals("-")
                || text.equals("x") || text.equals("/")) {

            if (display.getText().isEmpty())
                return;

            oldValue = Double.parseDouble(display.getText());
            operator = text;
            display.setText("");
        }

        else if (text.equals("=")) {

            if (display.getText().isEmpty())
                return;

            double newValue = Double.parseDouble(display.getText());
            double result = 0;

            switch (operator) {

                case "+":
                    result = oldValue + newValue;
                    break;

                case "-":
                    result = oldValue - newValue;
                    break;

                case "x":
                    result = oldValue * newValue;
                    break;

                case "/":
                    if (newValue == 0) {
                        display.setText("not defined");
                        return;
                    }
                    result = oldValue / newValue;
                    break;
            }

            if (result == (long) result) {
                display.setText(String.valueOf((long) result));
            } else {
                display.setText(String.valueOf(result));
            }
        }

        else if (text.equals("Clear")) {
            display.setText("");
            oldValue = 0;
            operator = "";
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}