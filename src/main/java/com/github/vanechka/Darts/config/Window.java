package com.github.vanechka.Darts.config;

import com.github.vanechka.Darts.database.DBConnection;
import com.github.vanechka.Darts.draw.DartsBoardPanel;
import com.github.vanechka.Darts.actions.*;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

    private JPanel panel = new JPanel();

    public DartsBoardPanel gamePanel = new DartsBoardPanel();

    private JPanel settingsPanel = new JPanel();

    private CardLayout cardLayout = new CardLayout();

    private JPanel cardPanel = new JPanel(cardLayout);

    private final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    private final int SCREEN_WIDTH = SCREEN_SIZE.width;

    private final int SCREEN_HEIGHT = SCREEN_SIZE.height;

    private final int BUTTON_WIDTH = 150;

    private final int TEXT_FIELD_WIDTH = 200;

    private final int LABEL_WIDTH = 650;

    private final int BUTTON_HEIGHT = 20;

    private final int LABEL_HEIGHT = 150;

    private final double SCREEN_INCREASE_FACTOR_0_35 = 0.35;

    private final double SCREEN_INCREASE_FACTOR_0_5 = 0.5;

    private final double SCREEN_INCREASE_FACTOR_0_57 = 0.57;

    private final double SCREEN_INCREASE_FACTOR_0_7 = 0.7;

    private final double SCREEN_INCREASE_FACTOR_0_9 = 0.9;

    private final double SCREEN_INCREASE_FACTOR_0_99 = 0.99;

    private JLabel timerLabelForPlayer1;

    private JLabel timerLabelForPlayer2;



    public Window() {
        super("Darts");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new GridLayout(3, 3));
        setSize(SCREEN_SIZE.width, SCREEN_SIZE.height);
        Dimension panelSize = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
        setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        panel.setPreferredSize(panelSize);

        JButton startButton = new JButton("Start the game!");
        startButton.addActionListener(new ActionListenerForRules());
        JButton settingsButton = new JButton("Settings");

        //сетка для того, чтобы кнопка была посередине
        panel.add(new JPanel());
        panel.add(new JPanel());
        panel.add(settingsButton);
        panel.add(new JPanel());
        panel.add(startButton);
        panel.add(new JPanel());
        panel.add(new JPanel());
        panel.add(new JPanel());
        panel.add(new JPanel());

        //настройка панели
        cardPanel.add(panel, "menu");
        cardPanel.add(gamePanel, "game");
        cardPanel.add(settingsPanel, "settings");
        panel.setBackground(Color.LIGHT_GRAY);
        gamePanel.setBackground(Color.LIGHT_GRAY);
        gamePanel.setLayout(null);
        settingsPanel.setBackground(Color.LIGHT_GRAY);

        add(cardPanel);

        //переключение панелей
        startButton.addActionListener(new ActionListenerForStart(cardLayout, cardPanel, "game"));
        JButton menuButton = new JButton("Back to menu");
        JButton rulesButton = new JButton("Rules");
        rulesButton.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_57 - BUTTON_WIDTH), (int) (SCREEN_HEIGHT -
                SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_99), BUTTON_WIDTH, BUTTON_HEIGHT);
        rulesButton.addActionListener(new ActionListenerForRules());
        gamePanel.add(rulesButton);
        menuButton.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_57), (int) (SCREEN_HEIGHT -
                SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_99), BUTTON_WIDTH, BUTTON_HEIGHT);
        gamePanel.add(menuButton);
        menuButton.addActionListener(new ActionListenerForStart(cardLayout, cardPanel, "menu"));
        settingsButton.addActionListener(new ActionListenerForStart(cardLayout, cardPanel, "settings"));
        JButton menuButton_1 = new JButton("Back to menu");
        settingsPanel.add(menuButton_1, BorderLayout.NORTH);
        menuButton_1.addActionListener(new ActionListenerForStart(cardLayout, cardPanel, "menu"));

        //размещение счёта
        JLabel player1ScoreLabel = new JLabel("Score for Player 1: 0");
        player1ScoreLabel.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_9),
                (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_5), BUTTON_WIDTH, BUTTON_HEIGHT);
        gamePanel.add(player1ScoreLabel);
        JLabel player2ScoreLabel = new JLabel("Score for Player 2: 0");
        player2ScoreLabel.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_35),
                (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_5), BUTTON_WIDTH, BUTTON_HEIGHT);
        gamePanel.add(player2ScoreLabel);
        JLabel player1QuestionLabel = new JLabel("");
        player1QuestionLabel.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_9),
                (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_9), LABEL_WIDTH, LABEL_HEIGHT);
        gamePanel.add(player1QuestionLabel);
        JLabel player2QuestionLabel = new JLabel("");
        player2QuestionLabel.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_35),
                (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_9), LABEL_WIDTH, LABEL_HEIGHT);
        gamePanel.add(player2QuestionLabel);

        //добавление текстового поля для ввода ответов
        JTextField player1AnswerField = new JTextField();
        player1AnswerField.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_9),
                (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_7), TEXT_FIELD_WIDTH, BUTTON_HEIGHT);
        gamePanel.add(player1AnswerField);
        JTextField player2AnswerField = new JTextField();
        player2AnswerField.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_35),
                (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_7), TEXT_FIELD_WIDTH, BUTTON_HEIGHT);
        gamePanel.add(player2AnswerField);


        timerLabelForPlayer1 = new JLabel("Затраченное время: 0:00");
        timerLabelForPlayer1.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_9),
                (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_57 + BUTTON_HEIGHT),
                LABEL_WIDTH, BUTTON_HEIGHT);
        timerLabelForPlayer2 = new JLabel("Затраченное время: 0:00");
        timerLabelForPlayer2.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_35),
                (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_57 + BUTTON_HEIGHT),
                LABEL_WIDTH, BUTTON_HEIGHT);

        //добавление кнопки для получения вопроса и привязка слушателя
        JButton get1Question = new JButton("Получить вопрос");
        get1Question.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_9),
                (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_7 - BUTTON_HEIGHT),
                BUTTON_WIDTH, BUTTON_HEIGHT);
        JButton get2Question = new JButton("Получить вопрос");
        get2Question.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_35),
                (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_7 - BUTTON_HEIGHT),
                BUTTON_WIDTH, BUTTON_HEIGHT);

        // Добавляем кнопки броска дротиков для каждого игрока
        JButton send1Button = new JButton("Кинуть дротик");
        send1Button.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_9),
                (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_7 + BUTTON_HEIGHT),
                BUTTON_WIDTH, BUTTON_HEIGHT);
        JButton send2Button = new JButton("Кинуть дротик");
        send2Button.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_35),
                (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_7 + BUTTON_HEIGHT),
                BUTTON_WIDTH, BUTTON_HEIGHT);
        send2Button.addActionListener(new ActionListenerForThrow(gamePanel, player1ScoreLabel, player2ScoreLabel,
                send1Button, send2Button, player1AnswerField, player2AnswerField,
                timerLabelForPlayer1, timerLabelForPlayer2, get1Question));
        send1Button.addActionListener(new ActionListenerForThrow(gamePanel, player1ScoreLabel, player2ScoreLabel,
                send1Button, send2Button, player1AnswerField, player2AnswerField,
                timerLabelForPlayer1, timerLabelForPlayer2, get1Question));
        send2Button.setEnabled(false);


        ActionListenerForTimer actionListenerForTimerPlayer1 = new ActionListenerForTimer(timerLabelForPlayer1,
                timerLabelForPlayer2, get1Question, get2Question, send1Button, send2Button);
        ActionListenerForTimer actionListenerForTimerPlayer2 = new ActionListenerForTimer(timerLabelForPlayer1,
                timerLabelForPlayer2, get1Question, get2Question, send1Button, send2Button);
        get1Question.addActionListener(actionListenerForTimerPlayer1);
        get2Question.addActionListener(actionListenerForTimerPlayer2);
        send1Button.addActionListener(actionListenerForTimerPlayer1);
        send2Button.addActionListener(actionListenerForTimerPlayer2);
        gamePanel.add(send1Button);
        gamePanel.add(send2Button);

        get1Question.addActionListener(new ActionListenerForGetQuestion(get1Question, get2Question,
                player1QuestionLabel, player2QuestionLabel, send1Button, send2Button));
        get2Question.addActionListener(new ActionListenerForGetQuestion(get1Question, get2Question,
                player1QuestionLabel, player2QuestionLabel, send1Button, send2Button));
        gamePanel.add(get1Question);
        gamePanel.add(get2Question);
        gamePanel.add(timerLabelForPlayer1);
        gamePanel.add(timerLabelForPlayer2);
        get2Question.setEnabled(false);
    }
}