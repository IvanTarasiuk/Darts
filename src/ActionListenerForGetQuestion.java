import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class ActionListenerForGetQuestion implements ActionListener {

    private final String[] questions;

    private final String[] answers;

    private final JLabel player1QuestionLabel;

    private final JLabel player2QuestionLabel;

    private final JButton get1Question;

    private final JButton get2Question;

    private final JTextField player1AnswerField;

    private final JTextField player2AnswerField;

    private boolean[] answersMarker;


    public ActionListenerForGetQuestion(
            String[] questions,
            JButton get1Question,
            JButton get2Question,
            JTextField player1AnswerField,
            JTextField player2AnswerField,
            String[] answers,
            JLabel player1QuestionLabel,
            JLabel player2QuestionLabel,
            boolean[] answersMarker
    ) {
        this.questions = questions;
        this.get1Question = get1Question;
        this.get2Question = get2Question;
        this.player1AnswerField = player1AnswerField;
        this.player2AnswerField = player2AnswerField;
        this.answers = answers;
        this.player1QuestionLabel = player1QuestionLabel;
        this.player2QuestionLabel = player2QuestionLabel;
        this.answersMarker = answersMarker;
    }


    public int getRandomQuestion(JLabel playerQuestionLabel) {
        int randomQuestion = new Random().nextInt(questions.length);
        if(questions[randomQuestion] != "") {
            playerQuestionLabel.setText("<html><div style='width: 350px; text-align: justify;'>" + questions[randomQuestion] + "<br></div></html>");
            questions[randomQuestion] = "";
        } else{
            while(questions[randomQuestion] == "") {
                randomQuestion = new Random().nextInt(questions.length);
            }
            playerQuestionLabel.setText("<html><div style='width: 350px; text-align: justify;'>" + questions[randomQuestion] + "<br></div></html>");
            questions[randomQuestion] = "";
        }
        return randomQuestion;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == get1Question) {
            getRandomQuestion(player1QuestionLabel);
            get2Question.setEnabled(true);
            get1Question.setEnabled(false);
        } else if(e.getSource() == get2Question) {
            getRandomQuestion(player2QuestionLabel);
            get1Question.setEnabled(true);
            get2Question.setEnabled(false);
        }
    }
}