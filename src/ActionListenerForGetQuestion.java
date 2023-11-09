import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class ActionListenerForGetQuestion implements ActionListener {

    private final String[] questions;

    private final JLabel player1QuestionLabel;

    private final JLabel player2QuestionLabel;

    private final JButton get1Question;

    private final JButton get2Question;

    private final JButton send1Button;

    private final JButton send2Button;

    public ActionListenerForGetQuestion(
            String[] questions,
            JButton get1Question,
            JButton get2Question,
            JLabel player1QuestionLabel,
            JLabel player2QuestionLabel,
            JButton send1Button,
            JButton send2Button
    ) {
        this.questions = questions;
        this.get1Question = get1Question;
        this.get2Question = get2Question;
        this.player1QuestionLabel = player1QuestionLabel;
        this.player2QuestionLabel = player2QuestionLabel;
        this.send1Button = send1Button;
        this.send2Button = send2Button;
    }


    public int getRandomQuestion(JLabel playerQuestionLabel) {
        int randomQuestion = new Random().nextInt(questions.length);
        int markerQuestions = 0;
        for (int i = 0; i < questions.length; ++i) {
            if(questions[i].equals("")){
                markerQuestions += 1;
            }
        }
        if (markerQuestions == 20) {
            get1Question.setEnabled(false);
            get2Question.setEnabled(false);
            send1Button.setEnabled(false);
            send2Button.setEnabled(false);
        } else {
            if(!questions[randomQuestion].equals("")) {
                playerQuestionLabel.setText("<html><div style='width: 350px; text-align: justify;'>" + questions[randomQuestion] + "<br></div></html>");
                questions[randomQuestion] = "";
            } else{
                while(questions[randomQuestion].equals("")) {
                    randomQuestion = new Random().nextInt(questions.length);
                }
                playerQuestionLabel.setText("<html><div style='width: 350px; text-align: justify;'>" + questions[randomQuestion] + "<br></div></html>");
                questions[randomQuestion] = "";
            }
            return randomQuestion;
        }
        return -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == get1Question) {
            if(getRandomQuestion(player1QuestionLabel) != -1) {
                get2Question.setEnabled(true);
                get1Question.setEnabled(false);
            }
        } else if(e.getSource() == get2Question) {
            if(getRandomQuestion(player2QuestionLabel) != -1) {
                get1Question.setEnabled(true);
                get2Question.setEnabled(false);
            }
        }
    }
}