import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ActionListenerForThrow implements ActionListener {

    private final JButton player1Button;

    private final JButton player2Button;

    private final JPanel gamePanel;

    private final JLabel player1ScoreLabel;

    private final JLabel player2ScoreLabel;

//    private final JLabel player1QuestionLabel;
//
//    private final JLabel player2QuestionLabel;
//
//    private final JTextField player1TextField;
//
//    private final JTextField player2TextField;

    private final int CENTER_X = 690, CENTER_Y = 400, BIGGEST_RADIUS = 200, DART_RADIUS = 14;

//    private final String[] questions;
//
//    private final String[] answers;

    private int counterOfThrows = 1;

    private final int MAX_THROWS = 10;

    private int player1Score = 0;

    private int player2Score = 0;

    private final String HTML = "<html><body style='width: %1spx'>%1s";

    public ActionListenerForThrow(
            JButton player1Button,
            JButton player2Button,
            JPanel gamePanel,
            JLabel player1ScoreLabel,
            JLabel player2ScoreLabel
//            String[] questions,
//            String[] answers,
//            JLabel player1QuestionLabel,
//            JLabel player2QuestionLabel,
//            JTextField player1TextField,
//            JTextField player2TextField
    ){
        this.player1Button = player1Button;
        this.player2Button = player2Button;
        this.gamePanel = gamePanel;
        this.player1ScoreLabel = player1ScoreLabel;
        this.player2ScoreLabel = player2ScoreLabel;
//        this.questions = questions;
//        this.answers = answers;
//        this.player1QuestionLabel = player1QuestionLabel;
//        this.player2QuestionLabel = player2QuestionLabel;
//        this.player1TextField = player1TextField;
//        this.player2TextField = player2TextField;
    }


    public int scoreIncrease(double distanceFromCenter) {
        if (distanceFromCenter <= 20) {
            return 10;
        } else if (distanceFromCenter <= 40) {
            return 9;
        } else if (distanceFromCenter <= 60) {
            return 8;
        } else if (distanceFromCenter <= 80) {
            return 7;
        } else if (distanceFromCenter <= 100) {
            return 6;
        } else if (distanceFromCenter <= 120) {
            return 5;
        } else if (distanceFromCenter <= 140) {
            return 4;
        } else if (distanceFromCenter <= 160) {
            return 3;
        } else if (distanceFromCenter <= 180) {
            return 2;
        } else {
            return 0;
        }
    }

//    public int getRandomQuestion(JLabel playerQuestionLabel) {
//        int randomQuestion = new Random().nextInt(questions.length);
//        if(questions[randomQuestion] != "") {
//            playerQuestionLabel.setText("<html><div style='width: 350px; text-align: justify;'>" + questions[randomQuestion] + "<br></div></html>");
//            questions[randomQuestion] = "";
//        } else{
//            while(questions[randomQuestion] == "") {
//                randomQuestion = new Random().nextInt(questions.length);
//            }
//            playerQuestionLabel.setText("<html><div style='width: 350px; text-align: justify;'>" + questions[randomQuestion] + "<br></div></html>");
//            questions[randomQuestion] = "";
//        }
//        return randomQuestion;
//    }

//    public boolean isCorrectAnswer(String answer, int randomQuestion, String[] answers) {
//        if(answer.equals(answers[randomQuestion])) {
//            System.out.println("полученный текст = " + answer);
//            System.out.println("Ответ = " + answers[randomQuestion]);
//            System.out.print("Результат = ");
//            System.out.println(answer.equals(answers[randomQuestion]));
//            return true;
//        }
//        return false;
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Graphics2D g2 = (Graphics2D) gamePanel.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int randomCoordX = CENTER_X - BIGGEST_RADIUS + new Random().nextInt(BIGGEST_RADIUS * 2);
        int randomCoordY = CENTER_Y - BIGGEST_RADIUS + new Random().nextInt(BIGGEST_RADIUS * 2);
        double distanceFromCenter = Math.pow(Math.pow((CENTER_X - randomCoordX - DART_RADIUS / 2), 2) + Math.pow((CENTER_Y - randomCoordY - DART_RADIUS / 2), 2), 0.5);
        if (e.getSource() == player1Button) {
            g2.setColor(Color.YELLOW);
            counterOfThrows++;
            player2Button.setEnabled(true);
            player1Button.setEnabled(false);
            player1Score += scoreIncrease(distanceFromCenter);
            player1ScoreLabel.setText("Score for Player 1: " + player1Score);
//            int randomQuestion = getRandomQuestion(player1QuestionLabel);
//            String answer = player1TextField.getText();
//            isCorrectAnswer(answer, randomQuestion, answers);
        } else if (e.getSource() == player2Button) {
            g2.setColor(Color.BLUE);
            counterOfThrows++;
            player1Button.setEnabled(true);
            player2Button.setEnabled(false);
            player2Score += scoreIncrease(distanceFromCenter);
            player2ScoreLabel.setText("Score for Player 2: " + player2Score);
//            int randomQuestion = getRandomQuestion(player2QuestionLabel);
//            String answer = player2TextField.getText();
//            isCorrectAnswer(answer, randomQuestion, answers);
        }
        g2.fillOval(randomCoordX, randomCoordY, DART_RADIUS, DART_RADIUS);
        if (counterOfThrows >= MAX_THROWS + 1 && e.getSource() == player2Button) {
            g2.setColor(Color.BLUE);
            g2.fillOval(randomCoordX, randomCoordY, DART_RADIUS, DART_RADIUS);
            player2Score += scoreIncrease(distanceFromCenter);
            player2ScoreLabel.setText("Score for Player 2: " + player2Score);
//            int randomQuestion = getRandomQuestion(player2QuestionLabel);
//            String answer = player2TextField.getText();
//            isCorrectAnswer(answer, randomQuestion, answers);
            player1Button.setEnabled(false);
            player2Button.setEnabled(false);
        }
    }
}