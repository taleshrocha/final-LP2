package br.ufrn.imd.FakeNewsDetector.dao;

import java.util.HashMap;
import org.apache.commons.crypto.*;

import br.ufrn.imd.FakeNewsDetector.model.News;

public class NewsDAO {

  private HashMap<String, String> allNews;

  public NewsDAO() {
    allNews = new HashMap<String, String>();
  }

  public boolean addNews(String news) {
    return true;
  }
}
