package br.ufrn.imd.FakeNewsDetector.view;

import br.ufrn.imd.FakeNewsDetector.control.*;
import br.ufrn.imd.FakeNewsDetector.dao.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

public class NewsPanel extends JPanel implements ActionListener {
  private DataBase dataBase;

  GridLayout griLayout = new GridLayout(1, 2, 5, 5);
  JLabel helloMsg = new JLabel("Welcome to Fake News Detector!");

    ImageIcon trashIcon = new ImageIcon("/home/tales/Documents/bti/2022.1/LP2/final-LP2/fake-news-detector/images/trash.png");
    Image image = trashIcon.getImage(); Image newImage = image.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);

    JTable table = new JTable();
    JScrollPane scrollPane = new JScrollPane(table);
    JButton load = new JButton("Load");
    String row[]={"id","hoax","link", "timestamp"};
    String column[]={"id","hoax","link", "timestamp"};
    DefaultTableModel model = new DefaultTableModel(column, 0);

  public NewsPanel() {
    trashIcon = new ImageIcon(newImage);

    // Setting layout;
    setLayout(griLayout);

    // Setting up the buttons.
    JButton trashButton = new JButton(trashIcon);

    dataBase = DataBase.getInstance();

    ScrapedNewsDAO allScrapedNews = dataBase.getScrapedNews();

    for (Integer key: allScrapedNews.keySet()) {
      row[0] = String.valueOf(allScrapedNews.get(key).getId());
      row[1] = allScrapedNews.get(key).getContent();
      row[2] = allScrapedNews.get(key).getLink();
      row[3] = allScrapedNews.get(key).getTimeStamp();
      model.addRow(row);
    }

    table = new JTable(model);
    scrollPane.getViewport().add(table);

    //add(helloMsg);
    //add(trashButton);
    add(load);
    add(scrollPane);

    load.addActionListener(this);
  }

@Override
public void actionPerformed(ActionEvent e) {

  if(e.getSource() == load) {
    ScrapedNewsDAO allScrapedNews = dataBase.getScrapedNews();

    model.setRowCount(0);

    for (Integer key: allScrapedNews.keySet()) {
      row[0] = String.valueOf(allScrapedNews.get(key).getId());
      row[1] = allScrapedNews.get(key).getContent();
      row[2] = allScrapedNews.get(key).getLink();
      row[3] = allScrapedNews.get(key).getTimeStamp();
      model.addRow(row);
    }

    table = new JTable(model);
    scrollPane.getViewport().add(table);
  }
}
}
