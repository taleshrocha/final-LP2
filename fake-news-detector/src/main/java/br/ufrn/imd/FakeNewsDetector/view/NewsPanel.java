//package br.ufrn.imd.FakeNewsDetector.view;
//
//import br.ufrn.imd.fakenewsdetector.control.*;
//import br.ufrn.imd.fakenewsdetector.dao.*;
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//import javax.swing.table.*;
//
//public class NewsPanel extends JPanel implements ActionListener {
//  private DataBase dataBase;
//
//  }
//
//  @Override
//  public void actionPerformed(ActionEvent e) {
//    if (e.getSource() == load) {
//      ScrapedNewsDAO allScrapedNews = dataBase.getScrapedNews();
//
//      model.setRowCount(0);
//
//      for (Integer key : allScrapedNews.keySet()) {
//        row[0] = String.valueOf(allScrapedNews.get(key).getId());
//        row[1] = allScrapedNews.get(key).getContent();
//        row[2] = allScrapedNews.get(key).getLink();
//        row[3] = allScrapedNews.get(key).getTimeStamp();
//        row[4] = String.valueOf(allScrapedNews.get(key).getTrustRating());
//        System.out.println(allScrapedNews.get(key).getTrustRating());
//        model.addRow(row);
//      }
//
//      table = new JTable(model);
//      scrollPane.getViewport().add(table);
//    }
//  }
//}
