package br.ufrn.imd.FakeNewsDetector.dao;
import br.ufrn.imd.FakeNewsDetector.model.*;

import java.util.HashMap;

public class FakeNewsDAO {

  private HashMap<Integer, FakeNews> allFakeNews;
  private static FakeNewsDAO fakeNews;

  private FakeNewsDAO() {
    allFakeNews = new HashMap<Integer, FakeNews>();
  }

  public static FakeNewsDAO getInstance() {
    if(fakeNews == null) {
      fakeNews = new FakeNewsDAO();
    }
    return fakeNews;
  }

  public boolean add(FakeNews fakeNews) {
    //System.out.println(fakeNews.toString());
    allFakeNews.put(fakeNews.getId(), fakeNews);
    return true;
  }
}
