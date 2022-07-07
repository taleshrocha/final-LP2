package br.ufrn.imd.FakeNewsDetector.dao;
import br.ufrn.imd.FakeNewsDetector.model.*;

import java.util.HashMap;

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
}
