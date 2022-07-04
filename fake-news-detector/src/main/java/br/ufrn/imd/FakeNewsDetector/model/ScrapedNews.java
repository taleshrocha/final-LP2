package br.ufrn.imd.FakeNewsDetector.model;

public class ScrapedNews extends News implements ToString {
  private String processedContent;
  private double trustRating;

  public void setProcessedContent(String processedContent) {
    this.processedContent = processedContent;
  }

  public String getProcessedContent() {
    return processedContent;
  }

  public String toString() {
    return "";
  }
}
