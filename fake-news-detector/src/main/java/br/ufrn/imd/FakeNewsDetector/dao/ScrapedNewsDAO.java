package br.ufrn.imd.FakeNewsDetector.dao;
import br.ufrn.imd.FakeNewsDetector.model.*;

import java.util.HashMap;
import java.util.Set;

public class ScrapedNewsDAO {

  private HashMap<Integer, ScrapedNews> allScrapedNews;
  private static ScrapedNewsDAO scrapedNews;

  private ScrapedNewsDAO() {
    allScrapedNews = new HashMap<Integer, ScrapedNews>();
  }

  public static ScrapedNewsDAO getInstance() {
    if(scrapedNews == null)
      scrapedNews = new ScrapedNewsDAO();

    return scrapedNews;
  }

  // TODO
  public boolean add(ScrapedNews scrapedNews) {
    System.out.println("\t----- ScrapedNewsDAO -----\t");
    System.out.println(scrapedNews);
    allScrapedNews.put(scrapedNews.getId(), scrapedNews);
    return true;
  }

  public Set<Integer> keySet() {
    return allScrapedNews.keySet();
  }

  public ScrapedNews get(Integer key) {
    return allScrapedNews.get(key);
  }

  public int size() {
    return allScrapedNews.size();
  }
}
