package br.ufrn.imd.FakeNewsDetector.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ErrorFrame extends JFrame implements ActionListener {

  private static final long serialVersionUID = 1L;

  // Buttons.
  JButton okButton = new JButton("OK");

  // Labels
  JLabel errMsgLabel;

  // Layouts.
  BorderLayout borderLayout = new BorderLayout(5, 5);

  public ErrorFrame(String errTitle, String errMsg) {
    setTitle("ERROR: " + errTitle);
    setSize(500, 250);
    setLocationRelativeTo(null);
    setResizable(false);
    this.setVisible(true);


    // Setting layouts.
    setLayout(borderLayout);

    errMsgLabel = new JLabel(errMsg);
    errMsgLabel.setHorizontalAlignment(SwingConstants.CENTER);

    // Adding components.
    add(errMsgLabel, BorderLayout.CENTER);
    add(okButton, BorderLayout.SOUTH);

    // Action listeners.
    okButton.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == okButton) {
      dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
  }
}
