package br.ufrn.imd.FakeNewsDetector.view;

import br.ufrn.imd.FakeNewsDetector.control.*;
import br.ufrn.imd.FakeNewsDetector.dao.*;
import br.ufrn.imd.FakeNewsDetector.model.*;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class HomePanel extends JPanel implements ActionListener {
  private DataBase dataBase;
  private FakeNewsDAO allFakeNews;
  private ScrapedNewsDAO allScrapedNews;

  String row[] = {"hoax", "link", "timestamp"};
  String column[] = {"hoax", "link", "timestamp"};

  // Layout.
  BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);

  // Buttons.
  JButton loadButton = new JButton("Reload data");

  // Labels.
  JLabel fakeNewsLabel = new JLabel("Loaded fake news:");
  JLabel scrapedNewsLabel = new JLabel("Loaded scraped news:");

  // Tables.
  JTable fakeNewsTable;
  JTable scrapedNewsTable = new JTable();

  // Scroll panes.
  JScrollPane fakeNewsScrollPane = new JScrollPane(fakeNewsTable);
  JScrollPane scrapedNewsScrollPane = new JScrollPane(scrapedNewsTable);

  // Table models.
  DefaultTableModel fakeNewsModel = new DefaultTableModel(column, 0);
  DefaultTableModel scrapedNewsModel = new DefaultTableModel(column, 0);

  MouseAdapter mouseAdapter;

  public HomePanel() {

    dataBase = DataBase.getInstance();
    allFakeNews = dataBase.getFakeNews();
    allScrapedNews = dataBase.getScrapedNews();

    mouseAdapter = new MouseAdapter() {
      public void mouseClicked(MouseEvent me) {
        if (me.getClickCount() == 2) {
          JTable table = (JTable)me.getSource();
          int row = table.getSelectedRow();
          String[] values = {
            (String)table.getValueAt(row, 0),
            (String)table.getValueAt(row, 1),
            (String)table.getValueAt(row, 2)
          };
          TableRowFrame tableRowFrame;
          if (me.getSource() == fakeNewsTable)
            tableRowFrame = new TableRowFrame(row, values, allFakeNews);
          else
            tableRowFrame = new TableRowFrame(row, values, allScrapedNews);
        }
      }
    };

    // Setting up the scroll panes.
    fakeNewsScrollPane.setVerticalScrollBarPolicy(
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    // Setting layout;
    setLayout(boxLayout);

    // Loading the fake news data into the table.
    fakeNewsModel.setRowCount(0);

    try {
      for (Integer key : allFakeNews.keySet()) {
        // row[0] = String.valueOf(allFakeNews.get(key).getId());
        row[0] = allFakeNews.get(key).getContent();
        row[1] = allFakeNews.get(key).getLink();
        row[2] = allFakeNews.get(key).getTimeStamp();
        fakeNewsModel.addRow(row);
      }
    } catch (NullPointerException npe) {
      npe.printStackTrace();
    }

    fakeNewsTable = new JTable(fakeNewsModel) {
      public boolean editCellAt(int row, int column, java.util.EventObject e) {
        return false;
      }
    };
    fakeNewsScrollPane.getViewport().add(fakeNewsTable);
    fakeNewsTable.setFocusable(false);
    fakeNewsTable.addMouseListener(mouseAdapter);

    // Loading the scraped news data into the table.
    scrapedNewsModel.setRowCount(0);

    try {
      for (Integer key : allScrapedNews.keySet()) {
        // row[0] = String.valueOf(allScrapedNews.get(key).getId());
        row[0] = allScrapedNews.get(key).getContent();
        row[1] = allScrapedNews.get(key).getLink();
        row[2] = allScrapedNews.get(key).getTimeStamp();
        scrapedNewsModel.addRow(row);
      }
    } catch (NullPointerException npe) {
      npe.printStackTrace();
    }

    scrapedNewsTable = new JTable(scrapedNewsModel) {
      public boolean editCellAt(int row, int column, java.util.EventObject e) {
        return false;
      }
    };
    scrapedNewsScrollPane.getViewport().add(scrapedNewsTable);
    scrapedNewsTable.setFocusable(false);
    scrapedNewsTable.addMouseListener(mouseAdapter);


    // Adding components.
    add(loadButton);
    add(fakeNewsLabel);
    add(fakeNewsScrollPane);
    add(scrapedNewsLabel);
    add(scrapedNewsScrollPane);

    // Action listeners.
    loadButton.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == loadButton) {
      // Loading the fake news data into the table.
      fakeNewsModel.setRowCount(0);

      try {
        for (Integer key : allFakeNews.keySet()) {
          // row[0] = String.valueOf(allFakeNews.get(key).getId());
          row[0] = allFakeNews.get(key).getContent();
          row[1] = allFakeNews.get(key).getLink();
          row[2] = allFakeNews.get(key).getTimeStamp();
          fakeNewsModel.addRow(row);
        }
      } catch (NullPointerException npe) {
        npe.printStackTrace();
      }

      fakeNewsTable = new JTable(fakeNewsModel) {
        public boolean editCellAt(int row, int column, java.util.EventObject e) {
          return false;
        }
      };
      fakeNewsScrollPane.getViewport().add(fakeNewsTable);
      fakeNewsTable.setFocusable(false);
      fakeNewsTable.addMouseListener(mouseAdapter);

      // Loading the scraped news data into the table.
      scrapedNewsModel.setRowCount(0);

      try {
        for (Integer key : allScrapedNews.keySet()) {
          // row[0] = String.valueOf(allScrapedNews.get(key).getId());
          row[0] = allScrapedNews.get(key).getContent();
          row[1] = allScrapedNews.get(key).getLink();
          row[2] = allScrapedNews.get(key).getTimeStamp();
          scrapedNewsModel.addRow(row);
        }
      } catch (NullPointerException npe) {
        npe.printStackTrace();
      }

      scrapedNewsTable = new JTable(scrapedNewsModel) {
        public boolean editCellAt(int row, int column, java.util.EventObject e) {
          return false;
        }
      };
      scrapedNewsScrollPane.getViewport().add(scrapedNewsTable);
      scrapedNewsTable.setFocusable(false);
      scrapedNewsTable.addMouseListener(mouseAdapter);
    }
  }
}
