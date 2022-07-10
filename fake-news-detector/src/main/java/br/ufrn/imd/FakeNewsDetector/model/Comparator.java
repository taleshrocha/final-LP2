package br.ufrn.imd.FakeNewsDetector.model;

import br.ufrn.imd.FakeNewsDetector.control.DataBase;
import br.ufrn.imd.FakeNewsDetector.dao.*;
import br.ufrn.imd.FakeNewsDetector.model.*;
// import Java.lang.math.max;
import java.lang.Math;
import org.apache.commons.text.similarity.*;

public class Comparator {

  public double eval(ScrapedNews scrapedNews) {
    DataBase dataBase = DataBase.getInstance();
    FakeNewsDAO allFakeNews = dataBase.getFakeNews();

    double resultCosine = 0.0;
    double resultLeven = 0.0;
    //double resultJaro = 0.0;
    String scraped;
    String fake;

    CosineDistance cosineDistance = new CosineDistance();
    LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
    JaroWinklerDistance jaroWinklerDistance = new JaroWinklerDistance();

    for (Integer key : allFakeNews.keySet()) {
      scraped = scrapedNews.getProcessedContent();
      fake = allFakeNews.get(key).getProcessedContent();

      resultCosine += cosineDistance.apply(scraped, fake);

      resultLeven += (double)(1.0 - levenshteinDistance.apply(scraped, fake) /
        Math.max(scraped.length(), fake.length()));

      //resultJaro += 1.0 - jaroWinklerDistance.apply(scraped, fake);
    }

    resultCosine = (double)resultCosine / allFakeNews.size();
    resultLeven = (double)resultLeven / allFakeNews.size();
    //resultJaro = (double)resultJaro / allFakeNews.size();

    return (double)(resultLeven+resultCosine)/2;
  }
}
