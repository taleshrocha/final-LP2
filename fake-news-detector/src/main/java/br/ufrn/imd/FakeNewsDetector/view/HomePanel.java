package br.ufrn.imd.FakeNewsDetector.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePanel extends JPanel implements ActionListener {
  private BoxLayout boxLayout;
  private JLabel helloMsg;

  public HomePanel() {
    boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
    helloMsg = new JLabel("Welcome to Fake News Detector!");

    // Setting layout;
    setLayout(boxLayout);

    add(helloMsg);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // if(e.getSource() == addButton) {
    // }
  }
}
