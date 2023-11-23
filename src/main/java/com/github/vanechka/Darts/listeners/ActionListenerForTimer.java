package com.github.vanechka.Darts.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ActionListenerForTimer implements ActionListener {

    private JLabel timerLabelForPlayer1;

    private JLabel timerLabelForPlayer2;

    private final JButton get1Question;

    private final JButton get2Question;

    private final JButton send1Button;

    private final JButton send2Button;

    private int seconds = 0;

    private boolean timerRunning = false;

    Thread timerThread = new Thread();

    public ActionListenerForTimer(
            JLabel timerLabelForPlayer1,
            JLabel timerLabelForPlayer2,
            JButton get1Question,
            JButton get2Question,
            JButton send1Button,
            JButton send2Button
    ) {
        this.timerLabelForPlayer1 = timerLabelForPlayer1;
        this.timerLabelForPlayer2 = timerLabelForPlayer2;
        this.get1Question = get1Question;
        this.get2Question = get2Question;
        this.send1Button = send1Button;
        this.send2Button = send2Button;
    }

    public void setTimer(JLabel timerLabel) {
        timerThread = new Thread(new Runnable() {
            public void run() {
                timerRunning = true;
                while (!Thread.interrupted() && timerRunning) {
                    try {
                        Thread.sleep(1000);
                        seconds++;
                        int minutes = seconds / 60;
                        int remainingSeconds = seconds % 60;
                        timerLabel.setText("Затраченное время: " + minutes + ":" + String.format("%02d", remainingSeconds));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        timerThread.start();
    }

    public void stopTimer(Thread timerThread, JLabel timerLabel) {
        timerRunning = false;
        timerThread.interrupt();
        seconds = 0;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == get1Question) {
            setTimer(timerLabelForPlayer1);
        } else if(e.getSource() == get2Question) {
            setTimer(timerLabelForPlayer2);
        } else if(e.getSource() == send1Button) {
            stopTimer(timerThread, timerLabelForPlayer1);
        } else if(e.getSource() == send2Button) {
            stopTimer(timerThread, timerLabelForPlayer2);
        }
        timerLabelForPlayer1.setVisible(true);
        timerLabelForPlayer2.setVisible(true);
    }
}