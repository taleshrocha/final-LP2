package br.ufrn.imd.FakeNewsDetector.dao;
import br.ufrn.imd.FakeNewsDetector.model.*;

import java.util.HashMap;
import java.util.Set;

public class FakeNewsDAO {

  private HashMap<Integer, FakeNews> allFakeNews;
  private static FakeNewsDAO fakeNews;

  private FakeNewsDAO() {
    allFakeNews = new HashMap<Integer, FakeNews>();
  }

  public static FakeNewsDAO getInstance() {
    if(fakeNews == null)
      fakeNews = new FakeNewsDAO();

    return fakeNews;
  }

  // TODO
  public boolean add(FakeNews fakeNews) {
    System.out.println(fakeNews);

    //System.out.println(fakeNews.getProcessedContent().hashCode());
    allFakeNews.put(fakeNews.getId(), fakeNews);
    return true;
  }

  public Set<Integer> keySet() {
    return allFakeNews.keySet();
  }

  public FakeNews get(Integer key) {
    return allFakeNews.get(key);
  }

  public int size() {
    return allFakeNews.size();
  }
}
