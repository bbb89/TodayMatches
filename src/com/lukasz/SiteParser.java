package com.lukasz;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Łukasz on 02.06.2017.
 */
public class SiteParser {
    public static MatchList parse(String date) {
        MatchList matchList = new MatchList(date);
        String data = "";

        try {
            Document doc = Jsoup.connect("http://satkurier.pl/futbol-w-tv?date=" + date).get();
            data = doc.getElementById("ajaxContent").html();
        }catch (IOException e) {
            System.out.println(e);
        }

        String[] matches = data.split("<div style=\"font-size:16px;\">");
        for(String s : matches) {
            if(!s.contains("<li style=\"color:blue;\">")) continue;
            String league = "";
            String teamFirst = "";
            String teamSecond = "";
            String hour;
            List<String> channels = new ArrayList<>();
            int offset = 0;
            int pos1 = 0;
            int pos2 = 0;

            pos2 = s.indexOf("<br>");
            if(pos2 > 0) {
                league = s.substring(2, pos2-2);
            }

            for(int i = 0; i <= 1; i++) {
                pos1 = s.indexOf("<span style=\"font-weight: bold;font-size:16px;\">", offset) + "<span style=\"font-weight: bold;font-size:16px;\">".length();
                pos2 = s.indexOf("</span>", offset);
                offset = pos2 + "</span>".length();
                if(pos1 > 0 && pos2 > 0 && i == 0) teamFirst = s.substring(pos1, pos2);
                if(pos1 > 0 && pos2 > 0 && i == 1) teamSecond = s.substring(pos1, pos2);
            }

            hour = s.substring(offset+2, offset + 7);

            offset = 0;
            while(true) {
                try {
                    offset = s.indexOf("<li style=\"color:blue;\">", offset);
                    pos1 = s.indexOf("<div class=\"channel\">", offset) + "<div class=\"channel\">\n   ".length();
                    pos2 = s.indexOf("</div>", offset) - 3;
                    offset = pos2 + "</div>".length();
                    if(pos1 > 0 && pos2 > 0) {
                        channels.add(s.substring(pos1, pos2));
                    }
                }catch (StringIndexOutOfBoundsException e) {
                    break;
                }
            }
            Match match = new Match(league, teamFirst, teamSecond, channels, hour);
            matchList.addMatch(match);
        }

        SaveData.save(matchList);
        return matchList;
    }
}
