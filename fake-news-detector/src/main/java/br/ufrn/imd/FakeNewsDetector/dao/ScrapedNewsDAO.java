package br.ufrn.imd.FakeNewsDetector.dao;
import br.ufrn.imd.FakeNewsDetector.model.*;
import java.util.HashMap;
import java.util.Set;
import java.io.*;

public class ScrapedNewsDAO {

  private HashMap<Integer, ScrapedNews> allScrapedNews;
  private static ScrapedNewsDAO scrapedNews;

  private ScrapedNewsDAO() {
    allScrapedNews = new HashMap<Integer, ScrapedNews>();
    try {
      FileInputStream fis = new FileInputStream("scraped-data.dat");
      ObjectInputStream ois = new ObjectInputStream(fis);
      try {
        allScrapedNews = (HashMap<Integer, ScrapedNews>)ois.readObject();
      } catch (ClassNotFoundException cnfe) {
        cnfe.printStackTrace();
      }
      ois.close();
    } catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    System.out.println(allScrapedNews);
  }

  public static ScrapedNewsDAO getInstance() {
    if (scrapedNews == null)
      scrapedNews = new ScrapedNewsDAO();

    return scrapedNews;
  }

  public void add(ScrapedNews scrapedNews) {
    System.out.println("\t----- ScrapedNewsDAO -----\t");
    System.out.println(scrapedNews);
    allScrapedNews.put(scrapedNews.getProcessedContent().hashCode(),
        scrapedNews);
  }

  public void remove(Integer key) {
    allScrapedNews.remove(key);
  }

  public Set<Integer> keySet() { return allScrapedNews.keySet(); }

  public ScrapedNews get(Integer key) { return allScrapedNews.get(key); }

  public int size() { return allScrapedNews.size(); }

  public void save() {
    try {
      FileOutputStream fos = new FileOutputStream("scraped-data.dat");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(allScrapedNews);
    } catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    System.out.println("\t----- ScrapedNewsDAO -----\t");
    System.out.println("Saved data!");
  }
}
