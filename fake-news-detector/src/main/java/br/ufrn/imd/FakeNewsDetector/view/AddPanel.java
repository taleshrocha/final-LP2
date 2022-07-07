package br.ufrn.imd.FakeNewsDetector.view;

import br.ufrn.imd.FakeNewsDetector.model.*;
import br.ufrn.imd.FakeNewsDetector.control.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.table.*;

public class AddPanel extends JPanel implements ActionListener {

  private DataBase dataBase;
  private int id = 0;

  GridBagLayout gridBagLayout = new GridBagLayout();
  GridBagConstraints gridBagConstraints = new GridBagConstraints();
  String csvFilePath = "";
  List<String[]> allData;

  JLabel fileLabel = new JLabel("File:");
  JLabel filePathLabel = new JLabel("          ");
  JButton addCsvButton = new JButton("Add");
  JButton browseButton = new JButton("Browse");

  JTable table = new JTable();

  JScrollPane scrollPane = new JScrollPane(table);

  JLabel fakeNewsLabel = new JLabel("Scraped News Text: ");

  JTextField contentText = new JTextField();

  JButton addScrapedNewsButton = new JButton("Add");
  JButton cleanButton = new JButton("Clean");

  public AddPanel() {
    scrollPane.setVerticalScrollBarPolicy(
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    setLayout(gridBagLayout);

    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    add(fakeNewsLabel, gridBagConstraints);

    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 100;
    gridBagConstraints.ipady = 100;
    add(contentText, gridBagConstraints);

    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.ipady = 10;
    gridBagConstraints.gridwidth = 1;
    add(addScrapedNewsButton, gridBagConstraints);

    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 2;
    add(cleanButton, gridBagConstraints);

    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;  
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    add(fileLabel, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    add(filePathLabel, gridBagConstraints);

    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 3;
    add(browseButton, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 4;
    add(scrollPane, gridBagConstraints);

    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 5;
    add(addCsvButton, gridBagConstraints);

    addCsvButton.addActionListener(this);
    browseButton.addActionListener(this);
    addScrapedNewsButton.addActionListener(this);
    cleanButton.addActionListener(this);

    dataBase = DataBase.getInstance();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == browseButton) {
      JFileChooser fileChooser = new JFileChooser(
          FileSystemView.getFileSystemView().getHomeDirectory());

      int returnValue = fileChooser.showOpenDialog(null);

      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();

        csvFilePath = selectedFile.getAbsolutePath();

        filePathLabel.setText(csvFilePath);

        try {
          FileReader filereader = new FileReader(csvFilePath);
          CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

          CSVReader csvReader = new CSVReaderBuilder(filereader)
            .withCSVParser(parser)
            .build();
          allData = csvReader.readAll();

          String column[]={"id","hoax","link", "timestamp"};

          DefaultTableModel model = new DefaultTableModel(column, 0);

          for (String[] row : allData) {
            model.addRow(row);
          }

          table = new JTable(model);
          scrollPane.getViewport().add(table);
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }
      }
      // TODO else?
    }
    if (e.getSource() == addCsvButton) {
      try {
        FileReader filereader = new FileReader(csvFilePath);
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

        CSVReader csvReader = new CSVReaderBuilder(filereader)
          .withCSVParser(parser)
          .build();

        List<String[]> allData = csvReader.readAll();

        // TODO is printing two times!
        FakeNews fakeNews;
        for (String[] row : allData) {
          if (row[0] != "") {
            fakeNews = new FakeNews();
            fakeNews.setId(Integer.parseInt(row[0]));
            fakeNews.setContent(row[1]);
            fakeNews.setProcessedContent(fakeNews.processContent(row[1]));
            fakeNews.setLink(row[2]);
            fakeNews.setTimeStamp(row[3]);
            dataBase.addFakeNews(fakeNews);
          }
        }
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    if (e.getSource() == addScrapedNewsButton) {
      ScrapedNews scrapedNews = new ScrapedNews();

      // TODO: get the last id.
      // TODO: see if the text is the same as the last one.

      // Get the time stamp. The local time and date.
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      LocalDateTime now = LocalDateTime.now();
      scrapedNews.setTimeStamp(dtf.format(now));

      scrapedNews.setId(id++);
      scrapedNews.setContent(contentText.getText());
      scrapedNews.setProcessedContent(scrapedNews.processContent(contentText.getText()));

      Comparator comparator = new Comparator();
      scrapedNews.setTrustRating(comparator.eval(scrapedNews));

      dataBase.addScrapedNews(scrapedNews);
    }
    if (e.getSource() == cleanButton) {
      contentText.setText("");
    }
  }
}
