package br.ufrn.imd.FakeNewsDetector.model;

import java.util.*;
import java.text.Normalizer;

public class FakeNews extends News implements ToString {
  private String processedContent;

  public String processContent(String content) {
    content = Normalizer.normalize(content, Normalizer.Form.NFD);
    content = content.replaceAll("[^\\x00-\\x7F]", "");
    content = content.replaceAll("\\b\\w{1,3}\\b\\s?", "");
    content = content.replaceAll("\\.+|\\!+|\\,+|\\;+|\\?+", "");
    content = content.toLowerCase();

    String aux;

    ArrayList<String> words = new ArrayList<>(Arrays.asList(content.split("\\s+")));
    Collections.sort(words);
    content = "";

    while (!words.isEmpty()) {
      if (content == "")
        content += words.get(0);
      else
        content += " " + words.get(0);

      aux = words.get(0);
      words.remove(0);

      while (words.contains(aux)) {
        words.remove(words.indexOf(aux));
      }
    }

    return content;
  }

  public void setProcessedContent(String processedContent) {
    this.processedContent = processedContent;
  }

  public String getProcessedContent() {
    return processedContent;
  }

  public String toString() {
    String str = "Id:\t" + this.getId() + "\n" 
      + "Content:\t" + this.getContent() + "\n"
      + "Time Stamp:\t" + this.getTimeStamp();
    return str;
  }
}
