package br.ufrn.imd.FakeNewsDetector.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

public class MainScreen extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;

  JDesktopPane desktopPane = new JDesktopPane();

  JMenuBar menuBar = new JMenuBar();

  JMenu addMenu = new JMenu("Add News");
  JMenu helpMenu = new JMenu("Help");

  JMenuItem scrapedItem = new JMenuItem("Scraped", 83);
  JMenuItem csvFileItem = new JMenuItem("CSV File", 67);

  JMenuItem aboutItem = new JMenuItem("About", 65);
  JMenuItem quitItem = new JMenuItem("Quit", 81);

  public MainScreen() {
    setTitle("Fake News Detector");
    setSize(900,700);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    Container container = this.getContentPane();
    container.setLayout(new BorderLayout());

    setJMenuBar(menuBar);

    menuBar.add(addMenu);
    menuBar.add(helpMenu);

    addMenu.add(scrapedItem);
    addMenu.add(csvFileItem);

    helpMenu.add(aboutItem);
    helpMenu.add(quitItem);

    container.add(BorderLayout.CENTER, desktopPane);

    // -- Events --

    // Add News Menu
    scrapedItem.addActionListener(this);
    csvFileItem.addActionListener(this);

    // Help Menu
    aboutItem.addActionListener(this);
    quitItem.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    //if (e.getSource() == scrapedItem){
    //  //TelaCliente tlCliente = new TelaCliente("Clientes");
    //  //desktopPane.add(tlCliente);
    //  //tlCliente.setVisible(true);
    //}
    if (e.getSource() == csvFileItem){
      AddNewsCsvFile addNewsCsvFile = new AddNewsCsvFile("Add CSV file");
      desktopPane.add(addNewsCsvFile);
      addNewsCsvFile.setVisible(true);
    }
    if (e.getSource() == quitItem) {
      System.exit(0);
    }
  }

  public static void main(String[] args) {
    FlatDarkLaf.setup();

    MainScreen mainScreen = new MainScreen();
    mainScreen.setVisible(true);
  }
}
