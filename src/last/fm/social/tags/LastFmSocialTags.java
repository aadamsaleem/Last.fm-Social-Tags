/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package last.fm.social.tags;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Aadam
 */
public class LastFmSocialTags {

    /**
     * @param args the command line arguments
     */
//    private static String userAgent = "tst";
//    private static String user = "ajansen7"; //api user
//    private static String password = "insc547";
//    private static String apiKey = "97ba6abdd8d2e34796a2a91085f79cc4"; // api key
//    private static String apiSecret = "e44c3cd28d05ce7646d7c208d49cbf2b";   // api secret
    public static void main(String[] args) {
        // TODO code application logic here

        String filename, artist, track, tag, url;
        File folder = new File("C:\\Users\\Aadam\\Documents\\NetBeansProjects\\Lyrics Crawler\\Lyrics");
        File[] listOfFiles = folder.listFiles();
        Document doc;
        Element wrapper, article, section;

        for (int i = 56487; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                filename = listOfFiles[i].getName();
                System.out.println("File#" + i + " " + filename);
                artist = filename.substring(0, filename.indexOf('-') - 1);
                track = filename.substring(filename.indexOf('-') + 2, filename.indexOf(".txt"));

                url = "http://www.last.fm/music/" + artist.replace(" ", "+") + "/_/" + track.replace(" ", "+") + "/+tags";
                try {
                    tag = null;
                    doc = Jsoup.connect(url).get();
                    if (!doc.select("div.page-wrapper").isEmpty()) {
                        wrapper = doc.select("div.page-wrapper").get(0);
                        if (!wrapper.select("article.content").isEmpty()) {
                            article = wrapper.select("article.content").get(0);
                            if (!article.select("section").isEmpty()) {
                                section = article.select("section").get(0);
                                if (!section.select("section.global-tags").isEmpty()) {
                                    String meta = section.select("section.global-tags").get(0).select("meta").get(0).toString();
                                    tag = meta.substring(meta.indexOf("content=\"") + 9, meta.indexOf("\" />"));
                                }
                                System.out.println("" + tag);
                                if (tag != null) {
//                                    try {
//                                        // write to a file
//                                        File lyricsfile = new File("C:\\Users\\Aadam\\Documents\\NetBeansProjects\\Last.fm Social Tags\\Tags\\" + filename);
//                                        FileOutputStream fos = new FileOutputStream(lyricsfile);
//                                        OutputStreamWriter osw = new OutputStreamWriter(fos);
//                                        try (Writer w = new BufferedWriter(osw)) {
//                                            w.write(tag);
//                                        }
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(LastFmSocialTags.class.getName()).log(Level.SEVERE, null, ex);
                    continue;
                }
            }
        }
    }
}
