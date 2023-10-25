import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerForSend implements ActionListener {

    private JTextField input;

    private String playerAnswer;

    public ActionListenerForSend(
            JTextField input,
            String playerAnswer
    ) {
        this.input = input;
        this.playerAnswer = playerAnswer;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String text = input.getText();
        playerAnswer = text;
        System.out.print("полученный текст: ");
        System.out.println(text);
        input.setText("");
    }
}