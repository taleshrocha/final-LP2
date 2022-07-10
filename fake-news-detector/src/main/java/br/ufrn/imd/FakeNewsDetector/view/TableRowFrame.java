package br.ufrn.imd.FakeNewsDetector.view;

import br.ufrn.imd.FakeNewsDetector.control.*;
import br.ufrn.imd.FakeNewsDetector.dao.*;
import br.ufrn.imd.FakeNewsDetector.model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TableRowFrame extends JFrame implements ActionListener {

  private static final long serialVersionUID = 1L;

  private DataBase dataBase;

  // Buttons.
  JButton okButton = new JButton("OK");
  JButton removeButton = new JButton("Remove");

  // Labels
  JLabel linkLabel;
  JLabel timeStampLabel;

  JTextArea hoaxTextArea;
  JScrollPane hoaxScrollPane;
  JPanel dataPanel = new JPanel();
  JPanel buttonPanel = new JPanel();

  // Layouts.
  BorderLayout borderLayout = new BorderLayout(5, 5);
  BoxLayout boxLayout = new BoxLayout(dataPanel, BoxLayout.Y_AXIS);
  BoxLayout boxLayout2 = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);

  Object newsDAO;
  String columns[];
  int row;

  public TableRowFrame(int row, String columns[], Object newsDAO) {
    setTitle("Value at: " + row);
    setSize(500, 250);
    setLocationRelativeTo(null);
    setResizable(false);
    this.setVisible(true);
    this.newsDAO = newsDAO;
    this.row = row;
    this.columns = columns;

    dataBase = DataBase.getInstance();

    // Setting layouts.
    setLayout(borderLayout);
    dataPanel.setLayout(boxLayout);
    buttonPanel.setLayout(boxLayout2);

    hoaxTextArea = new JTextArea(columns[0]);

    // Setting up the scrapedTextArea area to break the lines.
    hoaxTextArea.setLineWrap(true);
    hoaxTextArea.setWrapStyleWord(true);
    hoaxTextArea.setEditable(false);

    // Creating a scroll pane to the text area.
    hoaxScrollPane = new JScrollPane(hoaxTextArea);

    // Setting up the scroll panes.
    hoaxScrollPane.setVerticalScrollBarPolicy(
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    linkLabel = new JLabel(columns[1]);
    linkLabel.setHorizontalAlignment(SwingConstants.CENTER);
    timeStampLabel = new JLabel(columns[2]);
    timeStampLabel.setHorizontalAlignment(SwingConstants.CENTER);

    // Adding components.
    add(dataPanel, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);
    dataPanel.add(hoaxScrollPane);
    dataPanel.add(linkLabel);
    dataPanel.add(timeStampLabel);
    buttonPanel.add(removeButton);
    buttonPanel.add(okButton);

    // Action listeners.
    okButton.addActionListener(this);
    removeButton.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == okButton) {
      dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    if (e.getSource() == removeButton) {
      System.out.println("REMOVE!");
      if (newsDAO.getClass().getName() ==
          "br.ufrn.imd.FakeNewsDetector.dao.FakeNewsDAO") {
        FakeNews fakeNews = new FakeNews();
        fakeNews.setProcessedContent(fakeNews.processContent(columns[0]));

        ((FakeNewsDAO)newsDAO).remove(fakeNews.getProcessedContent().hashCode());
      } else if (newsDAO.getClass().getName() ==
          "br.ufrn.imd.FakeNewsDetector.dao.ScrapedNewsDAO") {

        ScrapedNews scrapedNews = new ScrapedNews();
        scrapedNews.setProcessedContent(scrapedNews.processContent(columns[0]));
        ((ScrapedNewsDAO)newsDAO).remove(scrapedNews.getProcessedContent().hashCode());
          }
    }
    dataBase.save();
    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
  }
}
