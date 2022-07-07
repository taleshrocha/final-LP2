package br.ufrn.imd.FakeNewsDetector.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import br.ufrn.imd.FakeNewsDetector.model.*;

public class EvalPanel extends JPanel implements ActionListener, ChangeListener {
  BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);

  Comparator comparator = new Comparator();
  JSlider trustRatingSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 25);

  JLabel blah = new JLabel(String.valueOf(trustRatingSlider.getValue()));
  JLabel fakeNewsLabel = new JLabel("Fake News:");
  JLabel nonFakeNewsLabel = new JLabel("Non Fake News:");

  NewsPanel fakeNewsPanel = new NewsPanel();

  public EvalPanel() {
    trustRatingSlider.setMinorTickSpacing(1);
    trustRatingSlider.setMajorTickSpacing(10);
    trustRatingSlider.setPaintTicks(true);
    trustRatingSlider.setPaintLabels(true);

    // Setting layout;
    setLayout(boxLayout);

    add(trustRatingSlider);
    add(fakeNewsLabel);
    add(fakeNewsPanel);
    add(nonFakeNewsLabel);
    //add(nonFakeNewsPanel);
    add(blah);

    trustRatingSlider.addChangeListener(this);
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider)e.getSource();
    if (!source.getValueIsAdjusting()) {
      blah.setText(String.valueOf(trustRatingSlider.getValue()));
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    //if (e.getSource() == addButton) {
    //}
  }

}
