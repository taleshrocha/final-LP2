package br.ufrn.imd.FakeNewsDetector.control;

import br.ufrn.imd.FakeNewsDetector.dao.*;
import br.ufrn.imd.FakeNewsDetector.model.*;

import java.util.ArrayList;

public class DataBase {
  private FakeNewsDAO fakeNewsDAO;
  //private ScrapedNewsDAO fakeNews;
  private static DataBase dataBase ;

  private DataBase() {
    fakeNewsDAO = FakeNewsDAO.getInstance();
  }

  public static DataBase getInstance() {
    if(dataBase == null) {
      dataBase = new DataBase();
    }
    return dataBase;
  }

  public void addFakeNews(FakeNews fakeNews) {
    fakeNewsDAO.add(fakeNews);
  }

}
