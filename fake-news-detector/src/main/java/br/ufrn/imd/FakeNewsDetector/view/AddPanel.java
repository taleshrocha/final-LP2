package br.ufrn.imd.FakeNewsDetector.view;

import br.ufrn.imd.FakeNewsDetector.control.*;
import br.ufrn.imd.FakeNewsDetector.model.*;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.table.*;

public class AddPanel extends JPanel implements ActionListener {

  DataBase dataBase;

  int id = 0;
  String csvFilePath = "";
  List<String[]> allData;
  FakeNews fakeNews;
  Comparator comparator = new Comparator();

  // Layouts.
  GroupLayout groupLayout = new GroupLayout(this);

  // Labels.
  JLabel fileLabel = new JLabel("File:");
  JLabel filePathLabel = new JLabel("          ");
  JLabel scrapedNewsLabel = new JLabel("Scraped News Text: ");
  JLabel csvFileLabel = new JLabel("CSV file: ");

  // Buttons.
  JButton addCsvButton = new JButton("Add");
  JButton browseButton = new JButton("Browse");
  JButton addScrapedNewsButton = new JButton("Add");
  JButton cleanButton = new JButton("Clean");

  // Misc.
  JTable table = new JTable();
  JScrollPane tableScrollPane = new JScrollPane(table);
  JScrollPane scrapedScrollPane;
  JTextArea scrapedTextArea = new JTextArea();

  public AddPanel() {
    dataBase = DataBase.getInstance();

    // Setting up the scrapedTextArea area to break the lines.
    scrapedTextArea.setLineWrap(true);
    scrapedTextArea.setWrapStyleWord(true);

    // Creating a scroll pane to the text area.
    scrapedScrollPane = new JScrollPane(scrapedTextArea);

    // Setting up the scroll panes.
    tableScrollPane.setVerticalScrollBarPolicy(
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrapedScrollPane.setVerticalScrollBarPolicy(
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    // Setting up the layout.
    setLayout(groupLayout);
    groupLayout.setAutoCreateGaps(true);
    groupLayout.setAutoCreateContainerGaps(true);

    // Adding components.
    groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
          .addComponent(scrapedNewsLabel)
          .addComponent(scrapedScrollPane)
          .addGroup(groupLayout.createSequentialGroup()
            .addComponent(addScrapedNewsButton)
            .addComponent(cleanButton))
          .addComponent(csvFileLabel)
          .addComponent(tableScrollPane)
          .addGroup(groupLayout.createSequentialGroup()
            .addComponent(browseButton)
            .addComponent(addCsvButton)));

    groupLayout.setVerticalGroup(
        groupLayout.createSequentialGroup()
        .addComponent(scrapedNewsLabel)
        .addComponent(scrapedScrollPane)
        .addGroup(
          groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
          .addComponent(addScrapedNewsButton)
          .addComponent(cleanButton))
        .addComponent(csvFileLabel)
        .addComponent(tableScrollPane)
        .addGroup(
          groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
          .addComponent(browseButton)
          .addComponent(addCsvButton)));

    // Action listeners.
    addCsvButton.addActionListener(this);
    browseButton.addActionListener(this);
    addScrapedNewsButton.addActionListener(this);
    cleanButton.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == browseButton) {
      // Creates and opens the file chooser.
      JFileChooser fileChooser = new JFileChooser(
          FileSystemView.getFileSystemView().getHomeDirectory());
      int returnValue = fileChooser.showOpenDialog(null);

      if (returnValue == JFileChooser.APPROVE_OPTION) {
        // Gets the user selected file path.
        File selectedFile = fileChooser.getSelectedFile();
        csvFilePath = selectedFile.getAbsolutePath();

        filePathLabel.setText(csvFilePath);

        try {
          FileReader filereader = new FileReader(csvFilePath);
          CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

          CSVReader csvReader =
            new CSVReaderBuilder(filereader).withCSVParser(parser).build();

          // Gets all CSV rows in a List<String[]>
          allData = csvReader.readAll();

          String column[] = {"id", "hoax", "link", "timestamp"};

          DefaultTableModel model = new DefaultTableModel(column, 0);

          // Removes the CSV index line.
          allData.remove(0);

          for (String[] row : allData)
            model.addRow(row);

          // Creates the new table with all the CSV info in it.
          table = new JTable(model);
          tableScrollPane.getViewport().add(table);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      } else // TODO
        System.out.println("ERROR IN FILE CHOOSER.");
    }
    if (e.getSource() == addCsvButton) {
      try {
        FileReader filereader = new FileReader(csvFilePath);
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

        CSVReader csvReader =
          new CSVReaderBuilder(filereader).withCSVParser(parser).build();

        List<String[]> allData = csvReader.readAll();

        // Removes the CSV index line.
        allData.remove(0);

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
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    if (e.getSource() == addScrapedNewsButton) {
      ScrapedNews scrapedNews = new ScrapedNews();

      // TODO: get the last id.
      // TODO: see if the text is the same as the last one.

      // Get the time stamp. The local time and date.
      DateTimeFormatter dtf =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      LocalDateTime now = LocalDateTime.now();
      scrapedNews.setTimeStamp(dtf.format(now));

      scrapedNews.setId(id++);
      scrapedNews.setContent(scrapedTextArea.getText());
      scrapedNews.setProcessedContent(
          scrapedNews.processContent(scrapedTextArea.getText()));

      scrapedNews.setTrustRating(comparator.eval(scrapedNews));

      dataBase.addScrapedNews(scrapedNews);
    }
    if (e.getSource() == cleanButton) {
      scrapedTextArea.setText("");
    }
  }
}
