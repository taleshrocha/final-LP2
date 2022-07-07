//package br.ufrn.imd.FakeNewsDetector.view;
//
//import br.ufrn.imd.FakeNewsDetector.model.*;
//import br.ufrn.imd.FakeNewsDetector.control.*;
//
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileNotFoundException;
//import java.util.List;
//import com.opencsv.CSVReader;
//import com.opencsv.CSVReaderBuilder;
//import com.opencsv.CSVParser;
//import com.opencsv.CSVParserBuilder;
//
//import java.awt.Container;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//
//import javax.swing.JButton;
//import javax.swing.JFormattedTextField;
//import javax.swing.JInternalFrame;
//import javax.swing.JLabel;
//import javax.swing.JTextField;
//import javax.swing.JFileChooser;
//import javax.swing.filechooser.FileSystemView;
//
//public class AddNewsCsvFile extends JInternalFrame implements ActionListener {
//
//  private static final long serialVersionUID = 1L;
//
//  private DataBase dataBase;
//
//  JLabel filePathLabel = new JLabel("CSV file path: ");
//
//  JTextField filePathText = new JTextField();
//
//  JButton addButton = new JButton("Add");
//  JButton cleanButton = new JButton("Clean");
//
//  public AddNewsCsvFile(String str)  {
//    super(str,false,true);
//
//    setTitle(str);
//    setSize(370,210);
//
//    Container container = this.getContentPane();
//    container.setLayout(null);
//
//    filePathLabel.setBounds(10,10,100,30);
//    filePathText.setBounds(77,10,280,25);
//
//    addButton.setBounds(50,140,100,30);
//    cleanButton.setBounds(230,140,100,30);
//
//    container.add(filePathLabel);
//    container.add(filePathText);
//
//    container.add(addButton);
//    container.add(cleanButton);
//
//    addButton.addActionListener(this);
//    cleanButton.addActionListener(this);
//
//
//    JFileChooser fileChooser = new JFileChooser(
//        FileSystemView.getFileSystemView().getHomeDirectory());
//
//    dataBase = DataBase.getInstance();
//
//    int returnValue = fileChooser.showOpenDialog(null);
//
//    if (returnValue == JFileChooser.APPROVE_OPTION) {
//      File selectedFile = fileChooser.getSelectedFile();
//
//      String csvFilePath = selectedFile.getAbsolutePath();
//
//      try {
//        FileReader filereader = new FileReader(csvFilePath);
//        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
//
//        CSVReader csvReader = new CSVReaderBuilder(filereader)
//          .withCSVParser(parser)
//          .build();
//
//        List<String[]> allData = csvReader.readAll();
//
//
//        for (String[] row : allData) {
//          if (row[0] != "") {
//            FakeNews fakeNews = new FakeNews();
//            fakeNews.setId(Integer.parseInt(row[0]));
//            fakeNews.setContent(row[1]);
//            fakeNews.setProcessedContent(fakeNews.processContent(row[1]));
//            fakeNews.setLink(row[2]);
//            fakeNews.setTimeStamp(row[3]);
//            dataBase.addFakeNews(fakeNews);
//          }
//          //System.out.println(fakeNews);
//        }
//      }
//      catch (Exception e) {
//        e.printStackTrace();
//      }
//    }
//  }
//
//@Override
//public void actionPerformed(ActionEvent e) {
//  if(e.getSource() == addButton) {
//  }
//  if(e.getSource() == cleanButton) {
//    filePathText.setText("");
//  }
//}
//
//
//}
//
//
//
//
//
//
//
//package br.ufrn.imd.FakeNewsDetector.view;
//
//import br.ufrn.imd.FakeNewsDetector.model.*;
//import br.ufrn.imd.FakeNewsDetector.control.*;
//
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileNotFoundException;
//import java.util.List;
//import java.time.format.DateTimeFormatter;
//import java.time.LocalDateTime;
//
//import com.opencsv.CSVReader;
//import com.opencsv.CSVReaderBuilder;
//import com.opencsv.CSVParser;
//import com.opencsv.CSVParserBuilder;
//
//import java.awt.Container;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//
//import javax.swing.JButton;
//import javax.swing.JFormattedTextField;
//import javax.swing.JInternalFrame;
//import javax.swing.JLabel;
//import javax.swing.JTextField;
//import javax.swing.JFileChooser;
//import javax.swing.filechooser.FileSystemView;
//import javax.swing.text.*;
//
//
//public class AddNewsScraped extends JPanel implements ActionListener {
//
//  private static final long serialVersionUID = 1L;
//
//  private int id = 0;
//
//  private DataBase dataBase;
//
//  JLabel filePathLabel = new JLabel("Scraped News Text: ");
//
//  JTextField contentText = new JTextField();
//  //contentText.setColumns(20);
//  //contentText.setLineWrap(true);
//  //contentText.setRows(5);
//  //contentText.setWrapStyleWord(true);
//  //contentText.setEditable(false);
//
//  JButton addButton = new JButton("Add");
//  JButton cleanButton = new JButton("Clean");
//
//  public AddNewsScraped(String str)  {
//    super(str,false,true);
//
//    setTitle(str);
//    setSize(400,300);
//
//    Container container = this.getContentPane();
//    container.setLayout(null);
//
//    filePathLabel.setBounds(10,10,100,30);
//    contentText.setBounds(50,50,280,60);
//
//    addButton.setBounds(50,140,100,30);
//    cleanButton.setBounds(230,140,100,30);
//
//    container.add(filePathLabel);
//    container.add(contentText);
//
//    container.add(addButton);
//    container.add(cleanButton);
//
//    addButton.addActionListener(this);
//    cleanButton.addActionListener(this);
//
//
//    JFileChooser fileChooser = new JFileChooser(
//        FileSystemView.getFileSystemView().getHomeDirectory());
//
//    dataBase = DataBase.getInstance();
//  }
//
//@Override
//public void actionPerformed(ActionEvent e) {
//  if(e.getSource() == addButton) {
//    ScrapedNews scrapedNews = new ScrapedNews();
//
//    // TODO: get the last id.
//    // TODO: see if the text is the same as the last one.
//
//    // Get the time stamp. The local time and date.
//    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    LocalDateTime now = LocalDateTime.now();
//    scrapedNews.setTimeStamp(dtf.format(now));
//
//    scrapedNews.setId(id++);
//    scrapedNews.setContent(contentText.getText());
//    scrapedNews.setProcessedContent(scrapedNews.processContent(contentText.getText()));
//
//    Comparator comparator = new Comparator();
//    scrapedNews.setTrustRating(comparator.eval(scrapedNews));
//
//    dataBase.addScrapedNews(scrapedNews);
//  }
//  if(e.getSource() == cleanButton) {
//    contentText.setText("");
//  }
//}
//
//
//}
