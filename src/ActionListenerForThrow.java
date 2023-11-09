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
        if(answer.equalsIgnoreCase(answers[randomQuestion])) {
            System.out.println("полученный текст = " + answer);
            System.out.println("Ответ = " + answers[randomQuestion]);
            System.out.print("Результат = ");
            System.out.println(answer.equalsIgnoreCase(answers[randomQuestion]));
            return true;
        }
        System.out.println("полученный текст = " + answer);
        System.out.println("Ответ = " + answers[randomQuestion]);
        System.out.print("Результат = ");
        System.out.println(answer.equalsIgnoreCase(answers[randomQuestion]));
        return false;
    }

    public String getAnswer(JTextField playerAnswerField) {
        return playerAnswerField.getText();
    }

    public void throwDart(boolean correctMarker, JTextField playerAnswerField, JLabel playerScoreLabel, JButton sendButtonFalse, JButton sendButtonTrue, int playerTurn){
        Graphics2D g2 = (Graphics2D) gamePanel.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int playerScore;
        String textForLabel = "Score for player ";
        if(playerTurn == 1){
            g2.setColor(Color.YELLOW);
            textForLabel += "1: ";
            playerScore = player1Score;
        } else {
            g2.setColor(Color.BLUE);
            textForLabel += "2: ";
            playerScore = player2Score;
        }
        String answer = getAnswer(playerAnswerField);
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
            playerScore += scoreIncrease(distanceFromCenter);
            playerScoreLabel.setText(textForLabel + playerScore);
            g2.fillOval(randomCoordX, randomCoordY, DART_RADIUS, DART_RADIUS);
        } else {
            int randomCoordX = CENTER_X - BIGGEST_RADIUS + new Random().nextInt(BIGGEST_RADIUS * 2);
            int randomCoordY = CENTER_Y - BIGGEST_RADIUS + new Random().nextInt(BIGGEST_RADIUS * 2);
            double distanceFromCenter = Math.pow(Math.pow((CENTER_X - randomCoordX - DART_RADIUS / 2), 2) + Math.pow((CENTER_Y - randomCoordY - DART_RADIUS / 2), 2), 0.5);
            playerScore += scoreIncrease(distanceFromCenter);
            playerScoreLabel.setText(textForLabel + playerScore);
            g2.fillOval(randomCoordX, randomCoordY, DART_RADIUS, DART_RADIUS);
        }
        counterOfThrows++;
        playerAnswerField.setText("");
        sendButtonFalse.setEnabled(false);
        sendButtonTrue.setEnabled(true);
        if(playerTurn == 1){
            player1Score = playerScore;
        } else {
            player2Score = playerScore;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Graphics2D g2 = (Graphics2D) gamePanel.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        boolean correctMarker = false;
        if (e.getSource() == send1Button) {
            throwDart(correctMarker, player1AnswerField, player1ScoreLabel, send1Button, send2Button, 1);
        } else if (e.getSource() == send2Button) {
            throwDart(correctMarker, player2AnswerField, player2ScoreLabel, send2Button, send1Button, 2);
        }
        if (counterOfThrows >= MAX_THROWS + 1 && e.getSource() == send2Button) {
            throwDart(correctMarker, player2AnswerField, player2ScoreLabel, send2Button, send1Button, 2);

        }
    }
}