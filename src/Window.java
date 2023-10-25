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

    private final String playerAnswer = "";

    private final int BUTTON_WIDTH = 150;

    private final int SEND_BUTTON_WIDTH = 80;

    private final int TEXT_FIELD_WIDTH = 200;

    private final int LABEL_WIDTH = 650;

    private final int BUTTON_HEIGHT = 20;

    private final int LABEL_HEIGHT = 150;

    private final double SCREEN_INCREASE_FACTOR_0_2 = 0.2;

    private final double SCREEN_INCREASE_FACTOR_0_35 = 0.35;

    private final double SCREEN_INCREASE_FACTOR_0_5 = 0.5;

    private final double SCREEN_INCREASE_FACTOR_0_57 = 0.57;

    private final double SCREEN_INCREASE_FACTOR_0_7 = 0.7;

    private final double SCREEN_INCREASE_FACTOR_0_9 = 0.9;

    private final double SCREEN_INCREASE_FACTOR_0_99 = 0.99;

    public Window() {
        super("Darts");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new GridLayout(3, 3));
        setSize(SCREEN_SIZE.width, SCREEN_SIZE.height);
        Dimension panelSize = new Dimension(1920, 1080);
        setBounds(0, 0, SCREEN_SIZE.width, SCREEN_SIZE.height);
        panel.setPreferredSize(panelSize);

        JButton startButton = new JButton("Start the game!");
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
        menuButton.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_57), (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_99), 150, 20);
        gamePanel.add(menuButton);
        menuButton.addActionListener(new ActionListenerForStart(cardLayout, cardPanel, "menu"));
        settingsButton.addActionListener(new ActionListenerForStart(cardLayout, cardPanel, "settings"));
        JButton menuButton_1 = new JButton("Back to menu");
        settingsPanel.add(menuButton_1, BorderLayout.NORTH);
        menuButton_1.addActionListener(new ActionListenerForStart(cardLayout, cardPanel, "menu"));

        //размещение счёта
        JLabel player1ScoreLabel = new JLabel("Score for Player 1: 0");
        player1ScoreLabel.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_9), (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_5), BUTTON_WIDTH, BUTTON_HEIGHT);
        gamePanel.add(player1ScoreLabel);
        JLabel player2ScoreLabel = new JLabel("Score for Player 2: 0");
        player2ScoreLabel.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_35), (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_5), BUTTON_WIDTH, BUTTON_HEIGHT);
        gamePanel.add(player2ScoreLabel);

        //заполнение масива вопросов и ответов + добавление лейбла для отображения вопросов
        String[] questions = {"Данные, предназначенные для управления конкретными компонентами системы обработки информации в целях реализации определенного алгоритма",
                "Совокупность программ системы обработки информации и программных документов, необходимых для эксплуатации этих программ",
                "Научная и практическая деятельность по созданию программ",
                "Программа, являющаяся частью другой программы и удовлетворяющая требованиям языка программирования к структуре программы",
                "Искусственный язык, предназначенный для выражения алгоритмов",
                "Язык программирования, используемый для первичного представления программы",
                "Язык программирования, предназначенный для представления программ в форме, позволяющей выполнять ее непосредственно техническими средствами обработки информации. Примечание. Для выполнения программы на машинном языке не требуется применение трансляторов, компиляторов и интерпретаторов",
                "Язык программирования, понятия и структура которого удобны для восприятия человеком",
                "Язык программирования для выражения определений. Примечание. В качестве такого языка часто выступает язык описания данных",
                "Язык программирования, в котором действия над данными выражаются в терминах последовательностей команд",
                "Язык программирования, в котором действия над данными выражаются в виде обращений к функциональным процедурам",
                "Программа или техническое средство, выполняющие трансляцию программы. Примечание. На транслятор обычно возлагаются функции диагностики ошибок, формирования словарей идентификаторов, выдачи для печати текстов программ и т.д.",
                "Программа или техническое средство, выполняющие компиляцию.",
                "Программа или техническое средство, выполняющие ассемблирование",
                "Программа или техническое средство, выполняющие интерпретацию. Примечание. Большинство интерпретаторов осуществляют интерпретацию программы путем последовательной интерпретации ее предложений",
                "Организованная совокупность программ или частей этих программ, а также, возможно, информации, относящейся к их использованию. Примечание.Библиотека программ часто называется в соответствии с природой содержащихся в ней элементов",
                "Метод построения программ, использующий только иерархически вложенные конструкции каждая из которых имеет единственную точка входа и единственную точку выхода. Примечание. В структурном программировании используются три вида структур, связанных с передачей управления: последовательная, условного перехода и циклическая Метод построения программ как совокупностей объектов и классов объектов, которые могут вызывать друг друга для выбора и выполнения операций.",
                "Парадигма разработки программных систем, в которой приложения состоят из объектов.",
                "Метод построения программ как совокупности логических правил с предварительно определенными алгоритмами для обработки входных данных программы в соответствии с ее правилами",
                "Формализованное представление требований, предъявляемых к программе, которые должны быть удовлетворены при ее разработке, а также описание задачи, условия и эффекта действия без указания способа его достижения",
                "Трансляция программы с языка высокого уровня в форму, близкую к программе на машинном языке"};

        String[] answers = {"Программа",
                "Программное обеспечение",
                "Программирование",
                "Подпрограмма",
                "Алгоритмический язык",
                "Исходный язык",
                "Машинный язык",
                "Язык высокого уровня",
                "Декларативный язык",
                "Процедурный язык",
                "Функциональный язык",
                "Транслятор",
                "Компилятор",
                "Ассемблер",
                "Интерпретатор",
                "Библиотека программ",
                "Структурное программирование",
                "Объектно-ориентированное программирование",
                "Логическое программирование",
                "Спецификация программы",
                "Компиляция"
        };
        JLabel player1QuestionLabel = new JLabel("");
        player1QuestionLabel.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_9), (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_9), LABEL_WIDTH, LABEL_HEIGHT);
        gamePanel.add(player1QuestionLabel);
        JLabel player2QuestionLabel = new JLabel("");
        player2QuestionLabel.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_35), (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_9), LABEL_WIDTH, LABEL_HEIGHT);
        gamePanel.add(player2QuestionLabel);

        //добавление текстового поля для ввода ответов
        JTextField player1AnswerField = new JTextField();
        player1AnswerField.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_9), (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_7), TEXT_FIELD_WIDTH, BUTTON_HEIGHT);
        gamePanel.add(player1AnswerField);
        JTextField player2AnswerField = new JTextField();
        player2AnswerField.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_35), (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_7), TEXT_FIELD_WIDTH, BUTTON_HEIGHT);
        gamePanel.add(player2AnswerField);

        //добавление кнопки для получения вопроса и привязка слушателя
        JButton get1Question = new JButton("Получить вопрос");
        get1Question.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_9), (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_7 - BUTTON_HEIGHT), BUTTON_WIDTH, BUTTON_HEIGHT);
        JButton get2Question = new JButton("Получить вопрос");
        get2Question.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_35), (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_7 - BUTTON_HEIGHT), BUTTON_WIDTH, BUTTON_HEIGHT);
        get1Question.addActionListener(new ActionListenerForGetQuestion(questions, get1Question, get2Question, player1AnswerField, player2AnswerField, answers, player1QuestionLabel, player2QuestionLabel));
        get2Question.addActionListener(new ActionListenerForGetQuestion(questions, get1Question, get2Question, player1AnswerField, player2AnswerField, answers, player1QuestionLabel, player2QuestionLabel));
        gamePanel.add(get1Question);
        gamePanel.add(get2Question);

        //добавление кнопок отправки ответа и привязывание слушателя
        JButton send1Button = new JButton("Отправить");
        send1Button.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_9 + TEXT_FIELD_WIDTH), (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_7), SEND_BUTTON_WIDTH, BUTTON_HEIGHT);
        send1Button.addActionListener(new ActionListenerForSend(player1AnswerField, playerAnswer));
        gamePanel.add(send1Button);
        JButton send2Button = new JButton("Отправить");
        send2Button.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_35 + TEXT_FIELD_WIDTH), (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_7), SEND_BUTTON_WIDTH, BUTTON_HEIGHT);
        send2Button.addActionListener(new ActionListenerForSend(player2AnswerField, playerAnswer));
        gamePanel.add(send2Button);

        // Добавляем кнопки броска дротиков для каждого игрока
        JButton player1Button = new JButton("Player 1 throw");
        player1Button.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_9), (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_2), BUTTON_WIDTH, BUTTON_HEIGHT);
        JButton player2Button = new JButton("Player 2 throw");
        player2Button.setBounds((int) (SCREEN_WIDTH - SCREEN_WIDTH * SCREEN_INCREASE_FACTOR_0_35), (int) (SCREEN_HEIGHT - SCREEN_HEIGHT * SCREEN_INCREASE_FACTOR_0_2), BUTTON_WIDTH, BUTTON_HEIGHT);
        player1Button.addActionListener(new ActionListenerForThrow(player1Button, player2Button, gamePanel, player1ScoreLabel, player2ScoreLabel));
        player2Button.addActionListener(new ActionListenerForThrow(player1Button, player2Button, gamePanel, player1ScoreLabel, player2ScoreLabel));
        gamePanel.add(player1Button);
        gamePanel.add(player2Button);
        player2Button.setEnabled(false);
    }
}