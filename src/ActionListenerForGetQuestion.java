import javax.swing.*;
import java.awt.*;
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

    private final JTextField player1TextField;

    private final JTextField player2TextField;

    public ActionListenerForGetQuestion(
            String[] questions,
            JButton get1Question,
            JButton get2Question,
            JTextField player1TextField,
            JTextField player2TextField,
            String[] answers,
            JLabel player1QuestionLabel,
            JLabel player2QuestionLabel
    ) {
        this.questions = questions;
        this.get1Question = get1Question;
        this.get2Question = get2Question;
        this.player1TextField = player1TextField;
        this.player2TextField = player2TextField;
        this.answers = answers;
        this.player1QuestionLabel = player1QuestionLabel;
        this.player2QuestionLabel = player2QuestionLabel;
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

    public boolean isCorrectAnswer(String answer, int randomQuestion, String[] answers) {
        if(answer.equals(answers[randomQuestion])) {
            System.out.println("полученный текст = 1 " + answer);
            System.out.println("Ответ = " + answers[randomQuestion]);
            System.out.print("Результат = ");
            System.out.println(answer.equals(answers[randomQuestion]));
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == get1Question) {
            int randomQuestion = getRandomQuestion(player1QuestionLabel);
            String answer = player1TextField.getText();
            isCorrectAnswer(answer, randomQuestion, answers);
        } else if(e.getSource() == get2Question) {
            int randomQuestion = getRandomQuestion(player2QuestionLabel);
            String answer = player2TextField.getText();
            isCorrectAnswer(answer, randomQuestion, answers);
        }
    }
}