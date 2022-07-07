package br.ufrn.imd.FakeNewsDetector.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;

public class MainFrame extends JFrame implements ActionListener {

  private static final long serialVersionUID = 1L;

  private BorderLayout borderLayout;
  private GridBagLayout gridBagLayout;
  private GridLayout gridLayout;
  private CardLayout cardLayout;

  private JPanel leftBar;
  private JPanel content;
  private JTable table;
  private JScrollPane scrollPane;
  private JButton homeButton;
  private JButton addButton;
  private JButton evalButton;

  private HomePanel homePanel;
  private EvalPanel evalPanel;

  public MainFrame() {
    setTitle("Fake News Detector");
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // Construct components.
    borderLayout = new BorderLayout(5, 5);
    gridLayout = new GridLayout(3, 1, 5, 5);
    cardLayout = new CardLayout();

    String data[][]={ {"101","Amit","670000"},    
                          {"102","Jai","780000"},    
                          {"101","Sachin","700000"}};    
    String column[]={"ID","NAME","SALARY"};       
    table = new JTable(data, column);

    scrollPane = new JScrollPane(table);

    scrollPane.setVerticalScrollBarPolicy(
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  

    leftBar = new JPanel();
    content = new JPanel();

    homePanel = new HomePanel();
    evalPanel = new EvalPanel();

    // Setting up the buttons.
    Image image;
    Image newImage;

    ImageIcon homeIcon = new ImageIcon("/home/tales/Documents/bti/2022.1/LP2/final-LP2/fake-news-detector/images/home.png");
    image = homeIcon.getImage();
    newImage = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
    homeIcon = new ImageIcon(newImage);

    ImageIcon addIcon = new ImageIcon("/home/tales/Documents/bti/2022.1/LP2/final-LP2/fake-news-detector/images/add.png");
    image = addIcon.getImage();
    newImage = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
    addIcon = new ImageIcon(newImage);

    ImageIcon evalIcon = new ImageIcon("/home/tales/Documents/bti/2022.1/LP2/final-LP2/fake-news-detector/images/eval.png");
    image = evalIcon.getImage();
    newImage = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
    evalIcon = new ImageIcon(newImage);

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
