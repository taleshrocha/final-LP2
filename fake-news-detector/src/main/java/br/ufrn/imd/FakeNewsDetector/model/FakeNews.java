package br.ufrn.imd.FakeNewsDetector.model;

import java.util.*;
import java.text.Normalizer;
import java.io.*;
import java.io.Serializable;

public class FakeNews extends News implements ToString, Serializable {
  private String processedContent;
  private static final long serialVersionUID = 1L;

  public String processContent(String content) {
    content = Normalizer.normalize(content, Normalizer.Form.NFD);
    content = content.replaceAll("[^\\x00-\\x7F]", "");
    content = content.replaceAll("\\b\\w{1,3}\\b\\s?", "");
    //content = content.replaceAll("\\.+|\\!+|\\,+|\\;+|\\?+|\\(+|\\)+|\\-+", "");
    content = content.replaceAll("[^a-zA-Z0-9\\s]", "");
    content = content.toLowerCase();

    String aux;

    ArrayList<String> words = new ArrayList<>(Arrays.asList(content.split("\\s+")));
    Collections.sort(words);
    content = "";

    // Remove repeated strings
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

  public void setProcessedContent(String content) {
    this.processedContent = processContent(content);
  }

  public String getProcessedContent() {
    return processedContent;
  }

  public String toString() {
    String str = 
      "\n--------------------------------------------------\n"
      + "Id:" + this.getId() + "\n"
      + "Time Stamp: " + this.getTimeStamp() + "\n"
      + "Link: " + this.getLink() + "\n"
      + "Content:\n" + this.getContent() + "\n"
      + "Processed Content:\n" + this.getProcessedContent() + "\n"
      + "\n--------------------------------------------------\n";
    return str;
  }
}
