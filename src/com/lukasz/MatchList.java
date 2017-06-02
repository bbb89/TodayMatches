package com.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Łukasz on 02.06.2017.
 */
public class MatchList implements Serializable {
    private List<Match> matchList;
    private String date;
    static final long serialVersionUID = 42L;

    public String getDate() {
        return date;
    }

    public MatchList(String date) {
        this.matchList = new ArrayList<>();
        this.date = date;
    }

    public void addMatch(Match match) {
        matchList.add(match);
    }

    public List<Match> getMatchList() {
        return new ArrayList<>(matchList);
    }

    @Override
    public String toString() {
        String s = "";
        ListIterator<Match> iterator = matchList.listIterator();
        while(iterator.hasNext()) {
            s += iterator.next() + "\n";
            s += "-------------------------------------\n";

        }
        return s;
    }
}
