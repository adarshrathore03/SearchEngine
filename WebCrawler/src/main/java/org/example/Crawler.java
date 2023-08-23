package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class Crawler {
    HashSet<String> urlSet;
    int maxDepth = 2;
    Crawler(){
        urlSet = new HashSet<String>();
    }

    public void getPageTextsAndLinks(String url,int depth){
        if(urlSet.contains(url)){
            return;
        }
        if(depth>=maxDepth){
            return;
        }
        if(urlSet.add(url)){
            System.out.println(url);
        }
        depth++;
        try {

            if (url == null || url.isEmpty()) {
                System.out.println("URL is null or empty");
            } else {
                // Your Jsoup.connect() code here
                Document document = Jsoup.connect(url).timeout(5000).get();

                //indexer work will start here
                Indexer indexer = new Indexer(document,url);
                System.out.println(document.title());
                Elements availableLinksOnPage = document.select("a[href]");

                for (Element currentLink : availableLinksOnPage) {
                    String currentUrl = currentLink.attr("abs:href");
                    //System.out.println("Current URL: " + currentUrl);
                    if(currentUrl!=null)
                    getPageTextsAndLinks(currentUrl, depth);
                }
            }

        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Crawler crawler = new Crawler();
        crawler.getPageTextsAndLinks("https://www.javatpoint.com",0);
    }
}