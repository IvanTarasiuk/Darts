package com.github.vanechka.Darts.listeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;

public class ActionListenerForStart implements ActionListener {
    private CardLayout cardLayout;
    private String panelNameToSwitchTo;
    private Container container;

    public ActionListenerForStart(
            CardLayout cardLayout,
            Container container,
            String panelNameToSwitchTo) {
        this.cardLayout = cardLayout;
        this.panelNameToSwitchTo = panelNameToSwitchTo;
        this.container = container;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cardLayout.show(container, panelNameToSwitchTo);
    }
}