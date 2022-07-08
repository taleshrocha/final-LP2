package br.ufrn.imd.FakeNewsDetector.view;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener {

  private static final long serialVersionUID = 1L;

  // Buttons.
  JButton homeButton;
  JButton addButton;
  JButton evalButton;

  // Layouts.
  BorderLayout borderLayout = new BorderLayout(5, 5);
  GridLayout gridLayout = new GridLayout(3, 1, 5, 5);
  CardLayout cardLayout = new CardLayout();

  // Panels.
  JPanel leftBar = new JPanel();
  JPanel content = new JPanel();

  JPanel homePanel = new HomePanel();
  JPanel addPanel = new AddPanel();
  JPanel evalPanel = new EvalPanel();

  // Setting up the buttons icons.
  Image image, newImage;

  public MainFrame() {
    setTitle("Fake News Detector");
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    ImageIcon homeIcon = new ImageIcon(
        "/home/tales/Documents/bti/2022.1/LP2/final-LP2/fake-news-detector/images/home.png");
    image = homeIcon.getImage();
    newImage = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
    homeIcon = new ImageIcon(newImage);

    ImageIcon addIcon = new ImageIcon(
        "/home/tales/Documents/bti/2022.1/LP2/final-LP2/fake-news-detector/images/add.png");
    image = addIcon.getImage();
    newImage = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
    addIcon = new ImageIcon(newImage);

    ImageIcon evalIcon = new ImageIcon(
        "/home/tales/Documents/bti/2022.1/LP2/final-LP2/fake-news-detector/images/eval.png");
    image = evalIcon.getImage();
    newImage = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
    evalIcon = new ImageIcon(newImage);

    // Setting up the buttons.
    homeButton = new JButton(homeIcon);
    addButton = new JButton(addIcon);
    evalButton = new JButton(evalIcon);

    // Setting layouts.
    setLayout(borderLayout);
    leftBar.setLayout(gridLayout);
    content.setLayout(cardLayout);

    // Adding components.
    add(leftBar, BorderLayout.WEST);
    leftBar.setPreferredSize(new Dimension(250, 0));
    add(content, BorderLayout.CENTER);

    leftBar.add(homeButton);
    leftBar.add(addButton);
    leftBar.add(evalButton);

    content.add(homePanel, "homePanel");
    content.add(addPanel, "addPanel");
    content.add(evalPanel, "evalPanel");

    // Action listeners.
    homeButton.addActionListener(this);
    addButton.addActionListener(this);
    evalButton.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == homeButton) {
      cardLayout.show(content, "homePanel");
    }
    if (e.getSource() == addButton) {
      cardLayout.show(content, "addPanel");
    }
    if (e.getSource() == evalButton) {
      cardLayout.show(content, "evalPanel");
    }
  }

  public static void main(String[] args) {
    FlatDarkLaf.setup();

    MainFrame mainScreen = new MainFrame();
    mainScreen.setVisible(true);
  }
}
