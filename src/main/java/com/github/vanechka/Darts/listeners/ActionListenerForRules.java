package com.github.vanechka.Darts.listeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerForRules implements ActionListener {

    private JFrame rulesFrame = new JFrame();

    private JLabel rulesLabel = new JLabel();

    private final int RULES_WIDTH = 750;

    private final int RULES_HEIGHT = 180;

    public ActionListenerForRules() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        rulesFrame.setTitle("Rules");
        rulesFrame.setLayout(new FlowLayout());
        String rules = "<html>1. В игре учавствуют 2 игрока <br>" +
                "2. За один раунд каждый игрок бросает дротик один раз <br>" +
                "3. Для того, чтобы бросить дротик нужно ответить на вопрос (нажать на кнопку получить вопрос и кинуть дротик) <br>" +
                "4. Старайтесь отвечать правильно и как можно быстрее, так дротик полетит точнее <br>" +
                "5. Если вы ответили неверно, то дротик полетит в случайное место и может вовсе не попасть в мишень <br>" +
                "6. Игра длится 10 раундов (по 10 бросков для каждого из игроков) <br>" +
                "7. Побеждает тот, кто набрал большее количество очков к концу 10 раунда <br>" +
                "Удачной игры! <br> </html>";
        rulesLabel.setText(rules);
        rulesFrame.setBounds(500, 300, RULES_WIDTH, RULES_HEIGHT);
        rulesFrame.add(rulesLabel, BorderLayout.CENTER);
        rulesFrame.setVisible(true);
    }
}