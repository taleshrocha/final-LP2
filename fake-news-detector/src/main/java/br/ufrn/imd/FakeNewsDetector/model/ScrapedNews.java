package br.ufrn.imd.FakeNewsDetector.model;

public class ScrapedNews extends FakeNews implements ToString {
  private double trustRating;

  public void setTrustRating(double trustRating) {
    this.trustRating = trustRating;
  }

  public double getTrustRating() {
    return trustRating;
  }

  public String toString() {
    String str = 
      "\n--------------------------------------------------\n"
      + "Id:" + this.getId() + "\n"
      + "Time Stamp:" + this.getTimeStamp() + "\n"
      + "Content:\n" + this.getContent() + "\n"
      + "Processed Content:\n" + this.getProcessedContent() + "\n"
      //+ "Trust Rating" + this.getTrustRating() + "\n"
      + "\n--------------------------------------------------\n";
    return str;
  }
}
