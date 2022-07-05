package br.ufrn.imd.FakeNewsDetector.model;

import br.ufrn.imd.FakeNewsDetector.model.*;
import br.ufrn.imd.FakeNewsDetector.dao.*;
import br.ufrn.imd.FakeNewsDetector.control.DataBase;

import org.apache.commons.text.similarity.CosineDistance;

public class Comparator {

  public double eval(ScrapedNews scrapedNews) {
    DataBase dataBase = DataBase.getInstance();
    FakeNewsDAO allFakeNews = dataBase.getFakeNews();

    double result = 0.0;
    CosineDistance cosineDistance = new CosineDistance();

    for (Integer key: allFakeNews.keySet()) {
      result += cosineDistance.apply(scrapedNews.getProcessedContent(), allFakeNews
          .get(key)
          .getProcessedContent());
    }
    result = result/allFakeNews.size();

    return result;
  }
}
