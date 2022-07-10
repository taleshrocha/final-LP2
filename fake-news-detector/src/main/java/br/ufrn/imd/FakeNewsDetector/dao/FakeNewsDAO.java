package br.ufrn.imd.FakeNewsDetector.dao;
import br.ufrn.imd.FakeNewsDetector.model.*;
import java.io.*;
import java.util.HashMap;
import java.util.Set;

public class FakeNewsDAO {

  private HashMap<Integer, FakeNews> allFakeNews;
  private static FakeNewsDAO fakeNews;

  private FakeNewsDAO() {
    allFakeNews = new HashMap<Integer, FakeNews>();
    try {
      FileInputStream fis = new FileInputStream("fake-data.dat");
      ObjectInputStream ois = new ObjectInputStream(fis);
      try {
        allFakeNews = (HashMap<Integer, FakeNews>)ois.readObject();
      } catch (ClassNotFoundException cnfe) {
        cnfe.printStackTrace();
      }
      ois.close();
    } catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    System.out.println(allFakeNews);
  }

  public static FakeNewsDAO getInstance() {
    if (fakeNews == null)
      fakeNews = new FakeNewsDAO();

    return fakeNews;
  }

  public void add(FakeNews fakeNews) {
    System.out.println("\t----- FakeNewsDAO -----\t");
    System.out.println(fakeNews);
    allFakeNews.put(fakeNews.getProcessedContent().hashCode(), fakeNews);
  }

  public void remove(Integer key) {
    allFakeNews.remove(key);
    System.out.println("Removed" + key);
  }

  public Set<Integer> keySet() { return allFakeNews.keySet(); }

  public FakeNews get(Integer key) { return allFakeNews.get(key); }

  public int size() { return allFakeNews.size(); }

  public void save() {
    try {
      FileOutputStream fos = new FileOutputStream("fake-data.dat");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(allFakeNews);
    } catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    System.out.println("\t----- FakeNewsDAO -----\t");
    System.out.println("Saved data!");
  }
}
