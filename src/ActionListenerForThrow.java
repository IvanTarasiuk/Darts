import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ActionListenerForThrow implements ActionListener {

    private final JPanel gamePanel;

    private final JLabel player1ScoreLabel;

    private final JLabel player2ScoreLabel;

    private final JButton send1Button;

    private final JButton send2Button;

    private final JTextField player1AnswerField;

    private final JTextField player2AnswerField;

    private final int CENTER_X = 690, CENTER_Y = 400, BIGGEST_RADIUS = 200, DART_RADIUS = 14, CORRECT_ANSWER_RADIUS = 60;

    private int counterOfThrows = 1;

    private final int MAX_THROWS = 10;

    private int player1Score = 0;

    private int player2Score = 0;

    private final String[] questions;

    private final String[] answers;

    private boolean[] answersMarker;


    public ActionListenerForThrow(
            JPanel gamePanel,
            JLabel player1ScoreLabel,
            JLabel player2ScoreLabel,
            String[] questions,
            String[] answers,
            boolean[] answersMarker,
            JButton send1Button,
            JButton send2Button,
            JTextField player1AnswerField,
            JTextField player2AnswerField
    ){
        this.gamePanel = gamePanel;
        this.player1ScoreLabel = player1ScoreLabel;
        this.player2ScoreLabel = player2ScoreLabel;
        this.questions = questions;
        this.answers = answers;
        this.answersMarker = answersMarker;
        this.send1Button = send1Button;
        this.send2Button = send2Button;
        this.player1AnswerField = player1AnswerField;
        this.player2AnswerField = player2AnswerField;
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

    public boolean isCorrectAnswer(String answer, int randomQuestion, String[] answers) {
        if(answer.equals(answers[randomQuestion])) {
            System.out.println("полученный текст = " + answer);
            System.out.println("Ответ = " + answers[randomQuestion]);
            System.out.print("Результат = ");
            System.out.println(answer.equals(answers[randomQuestion]));
            return true;
        }
        System.out.println("полученный текст = " + answer);
        System.out.println("Ответ = " + answers[randomQuestion]);
        System.out.print("Результат = ");
        System.out.println(answer.equals(answers[randomQuestion]));
        return false;
    }

    public String getAnswer(JTextField playerAnswerField) {
        return playerAnswerField.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Graphics2D g2 = (Graphics2D) gamePanel.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        boolean correctMarker = false;
        boolean correctMarker1 = false;
        boolean correctMarker2 = false;
        if (e.getSource() == send1Button) {
            g2.setColor(Color.YELLOW);
            String answer = getAnswer(player1AnswerField);
            for(int i = 0; i < questions.length; i++) {
                if(questions[i].equals("") && answersMarker[i]) {
                    answersMarker[i] = false;
                    correctMarker = isCorrectAnswer(answer, i, answers);
                    break;
                }
            }
            if (correctMarker) {
                int randomCoordX = CENTER_X - CORRECT_ANSWER_RADIUS + new Random().nextInt(CORRECT_ANSWER_RADIUS * 2);
                int randomCoordY = CENTER_Y - CORRECT_ANSWER_RADIUS + new Random().nextInt(CORRECT_ANSWER_RADIUS * 2);
                double distanceFromCenter = Math.pow(Math.pow((CENTER_X - randomCoordX - DART_RADIUS / 2), 2) + Math.pow((CENTER_Y - randomCoordY - DART_RADIUS / 2), 2), 0.5);
                player1Score += scoreIncrease(distanceFromCenter);
                player1ScoreLabel.setText("Score for Player 1: " + player1Score);
                g2.fillOval(randomCoordX, randomCoordY, DART_RADIUS, DART_RADIUS);
            } else {
                int randomCoordX = CENTER_X - BIGGEST_RADIUS + new Random().nextInt(BIGGEST_RADIUS * 2);
                int randomCoordY = CENTER_Y - BIGGEST_RADIUS + new Random().nextInt(BIGGEST_RADIUS * 2);
                double distanceFromCenter = Math.pow(Math.pow((CENTER_X - randomCoordX - DART_RADIUS / 2), 2) + Math.pow((CENTER_Y - randomCoordY - DART_RADIUS / 2), 2), 0.5);
                player1Score += scoreIncrease(distanceFromCenter);
                player1ScoreLabel.setText("Score for Player 1: " + player1Score);
                g2.fillOval(randomCoordX, randomCoordY, DART_RADIUS, DART_RADIUS);
            }
            counterOfThrows++;
            player1AnswerField.setText("");
            send1Button.setEnabled(false);
            send2Button.setEnabled(true);
        } else if (e.getSource() == send2Button) {
            g2.setColor(Color.BLUE);
            String answer = getAnswer(player2AnswerField);
            for(int i = 0; i < questions.length; i++) {
                if(questions[i].equalsIgnoreCase("") && answersMarker[i]) {
                    answersMarker[i] = false;
                    correctMarker1 = isCorrectAnswer(answer, i, answers);
                    break;
                }
            }
            if (correctMarker1) {
                int randomCoordX = CENTER_X - CORRECT_ANSWER_RADIUS + new Random().nextInt(CORRECT_ANSWER_RADIUS * 2);
                int randomCoordY = CENTER_Y - CORRECT_ANSWER_RADIUS + new Random().nextInt(CORRECT_ANSWER_RADIUS * 2);
                double distanceFromCenter = Math.pow(Math.pow((CENTER_X - randomCoordX - DART_RADIUS / 2), 2) + Math.pow((CENTER_Y - randomCoordY - DART_RADIUS / 2), 2), 0.5);
                player2Score += scoreIncrease(distanceFromCenter);
                player2ScoreLabel.setText("Score for Player 2: " + player2Score);
                g2.fillOval(randomCoordX, randomCoordY, DART_RADIUS, DART_RADIUS);
            } else {
                int randomCoordX = CENTER_X - BIGGEST_RADIUS + new Random().nextInt(BIGGEST_RADIUS * 2);
                int randomCoordY = CENTER_Y - BIGGEST_RADIUS + new Random().nextInt(BIGGEST_RADIUS * 2);
                double distanceFromCenter = Math.pow(Math.pow((CENTER_X - randomCoordX - DART_RADIUS / 2), 2) + Math.pow((CENTER_Y - randomCoordY - DART_RADIUS / 2), 2), 0.5);
                player2Score += scoreIncrease(distanceFromCenter);
                player2ScoreLabel.setText("Score for Player 2: " + player2Score);
                g2.fillOval(randomCoordX, randomCoordY, DART_RADIUS, DART_RADIUS);
            }
            counterOfThrows++;
            player2AnswerField.setText("");
            send2Button.setEnabled(false);
            send1Button.setEnabled(true);
        }
        if (counterOfThrows >= MAX_THROWS + 1 && e.getSource() == send2Button) {
            g2.setColor(Color.BLUE);
            String answer = getAnswer(player2AnswerField);
            for(int i = 0; i < questions.length; i++) {
                if(questions[i].equals("") && answersMarker[i]) {
                    answersMarker[i] = false;
                    correctMarker2 = isCorrectAnswer(answer, i, answers);
                    break;
                }
            }
            if (correctMarker2) {
                int randomCoordX = CENTER_X - CORRECT_ANSWER_RADIUS + new Random().nextInt(CORRECT_ANSWER_RADIUS * 2);
                int randomCoordY = CENTER_Y - CORRECT_ANSWER_RADIUS + new Random().nextInt(CORRECT_ANSWER_RADIUS * 2);
                double distanceFromCenter = Math.pow(Math.pow((CENTER_X - randomCoordX - DART_RADIUS / 2), 2) + Math.pow((CENTER_Y - randomCoordY - DART_RADIUS / 2), 2), 0.5);
                player2Score += scoreIncrease(distanceFromCenter);
                player2ScoreLabel.setText("Score for Player 2: " + player2Score);
                g2.fillOval(randomCoordX, randomCoordY, DART_RADIUS, DART_RADIUS);
            } else {
                int randomCoordX = CENTER_X - BIGGEST_RADIUS + new Random().nextInt(BIGGEST_RADIUS * 2);
                int randomCoordY = CENTER_Y - BIGGEST_RADIUS + new Random().nextInt(BIGGEST_RADIUS * 2);
                double distanceFromCenter = Math.pow(Math.pow((CENTER_X - randomCoordX - DART_RADIUS / 2), 2) + Math.pow((CENTER_Y - randomCoordY - DART_RADIUS / 2), 2), 0.5);
                player2Score += scoreIncrease(distanceFromCenter);
                player2ScoreLabel.setText("Score for Player 2: " + player2Score);
                g2.fillOval(randomCoordX, randomCoordY, DART_RADIUS, DART_RADIUS);
            }
            g2.setColor(Color.BLUE);
            player2AnswerField.setText("");
            send2Button.setEnabled(false);
            send1Button.setEnabled(false);
        }
    }
}