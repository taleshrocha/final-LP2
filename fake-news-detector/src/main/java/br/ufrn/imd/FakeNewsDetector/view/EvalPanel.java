// TODO upload scrapad news trustrate when new csvs are added
package br.ufrn.imd.FakeNewsDetector.view;

import br.ufrn.imd.FakeNewsDetector.control.*;
import br.ufrn.imd.FakeNewsDetector.dao.*;
import br.ufrn.imd.FakeNewsDetector.model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class EvalPanel extends JPanel implements ChangeListener {

  private DataBase dataBase;
  private ScrapedNewsDAO allScrapedNews;

  Comparator comparator = new Comparator();
  String row[] = {"id", "hoax", "link", "timestamp", "trustrate"};
  String column[] = {"id", "hoax", "link", "timestamp", "trustrate"};

  // Layouts.
  GroupLayout groupLayout = new GroupLayout(this);

  // Misc.
  JSlider trustRatingSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 25);
  JTable table = new JTable();
  JScrollPane scrollPane = new JScrollPane(table);
  DefaultTableModel model = new DefaultTableModel(column, 0);

  // Labels.
  JLabel blah = new JLabel(String.valueOf(trustRatingSlider.getValue()));

  public EvalPanel() {
    dataBase = DataBase.getInstance();
    allScrapedNews = dataBase.getScrapedNews();

    // Setting up the layout.
    setLayout(groupLayout);
    groupLayout.setAutoCreateGaps(true);
    groupLayout.setAutoCreateContainerGaps(true);

    // Setting up the table.
    table = new JTable(model);
    scrollPane.getViewport().add(table);

    // Setting up the slider.
    trustRatingSlider.setMinorTickSpacing(1);
    trustRatingSlider.setMajorTickSpacing(10);
    trustRatingSlider.setPaintTicks(true);
    trustRatingSlider.setPaintLabels(true);

    // Adding components.
    groupLayout.setHorizontalGroup(
        groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addComponent(trustRatingSlider)
        .addComponent(table));

    groupLayout.setVerticalGroup(
        groupLayout.createSequentialGroup()
        .addComponent(trustRatingSlider)
        .addComponent(table));

    trustRatingSlider.addChangeListener(this);
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider)e.getSource();
    if (!source.getValueIsAdjusting()) {
      blah.setText(String.valueOf(trustRatingSlider.getValue()));
      model.setRowCount(0);

      for (Integer key : allScrapedNews.keySet()) {
        if (allScrapedNews.get(key).getTrustRating() >= trustRatingSlider.getValue()) {
          row[0] = String.valueOf(allScrapedNews.get(key).getId());
          row[1] = allScrapedNews.get(key).getContent();
          row[2] = allScrapedNews.get(key).getLink();
          row[3] = allScrapedNews.get(key).getTimeStamp();
          row[4] = String.valueOf(allScrapedNews.get(key).getTrustRating());
          model.addRow(row);
        }
      }

      table = new JTable(model);
      scrollPane.getViewport().add(table);
    }
  }
}
