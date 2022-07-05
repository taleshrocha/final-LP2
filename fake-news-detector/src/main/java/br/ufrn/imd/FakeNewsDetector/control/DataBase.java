package br.ufrn.imd.FakeNewsDetector.control;

import br.ufrn.imd.FakeNewsDetector.dao.*;
import br.ufrn.imd.FakeNewsDetector.model.*;

import java.util.ArrayList;

public class DataBase {
  private FakeNewsDAO fakeNewsDAO;
  private ScrapedNewsDAO scrapedNewsDAO;
  private static DataBase dataBase ;

  private DataBase() {
    fakeNewsDAO = FakeNewsDAO.getInstance();
    scrapedNewsDAO = ScrapedNewsDAO.getInstance();
  }

  public static DataBase getInstance() {
    if(dataBase == null)
      dataBase = new DataBase();

    return dataBase;
  }

  public void addFakeNews(FakeNews fakeNews) {
    fakeNewsDAO.add(fakeNews);
  }

  public void addScrapedNews(ScrapedNews scrapedNews) {
    scrapedNewsDAO.add(scrapedNews);
  }

  //public void printFakeNews() {
  //  fakeNewsDAO.print();
  //}

  public FakeNewsDAO getFakeNews() {
    return fakeNewsDAO;
  }

}
