package br.ufrn.imd.FakeNewsDetector.model;

public abstract class News {
  private int id;
  private String content;
  private String link;
  private String timeStamp;

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getLink() {
    return link;
  }

  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }

  public String getTimeStamp() {
    return timeStamp;
  }
}
