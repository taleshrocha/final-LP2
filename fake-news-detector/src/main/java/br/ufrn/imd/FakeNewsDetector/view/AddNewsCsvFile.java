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

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class AddNewsCsvFile extends JInternalFrame implements ActionListener {

  private static final long serialVersionUID = 1L;

  private DataBase dataBase;

  JLabel filePathLabel = new JLabel("CSV file path: ");

  JTextField filePathText = new JTextField();

  JButton addButton = new JButton("Add");
  JButton cleanButton = new JButton("Clean");

  public AddNewsCsvFile(String str)  {
    super(str,false,true);

    setTitle(str);
    setSize(370,210);

    Container container = this.getContentPane();
    container.setLayout(null);

    filePathLabel.setBounds(10,10,100,30);
    filePathText.setBounds(77,10,280,25);

    addButton.setBounds(50,140,100,30);
    cleanButton.setBounds(230,140,100,30);

    container.add(filePathLabel);
    container.add(filePathText);

    container.add(addButton);
    container.add(cleanButton);

    addButton.addActionListener(this);
    cleanButton.addActionListener(this);


    JFileChooser fileChooser = new JFileChooser(
        FileSystemView.getFileSystemView().getHomeDirectory());

    dataBase = DataBase.getInstance();

    int returnValue = fileChooser.showOpenDialog(null);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();

      String csvFilePath = selectedFile.getAbsolutePath();

      try {
        FileReader filereader = new FileReader(csvFilePath);
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

        CSVReader csvReader = new CSVReaderBuilder(filereader)
          .withCSVParser(parser)
          .build();

        List<String[]> allData = csvReader.readAll();

        FakeNews fakeNews = new FakeNews();

        for (String[] row : allData) {
          if (row[0] != "") {
            fakeNews.setId(Integer.parseInt(row[0]));
            fakeNews.setContent(row[1]);
            fakeNews.setProcessedContent(fakeNews.processContent(row[1]));
            fakeNews.setTimeStamp(row[2]);
            dataBase.addFakeNews(fakeNews);
          }
        }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

@Override
public void actionPerformed(ActionEvent e) {
  if(e.getSource() == addButton) {
  }
  if(e.getSource() == cleanButton) {
    filePathText.setText("");
  }
}


}
