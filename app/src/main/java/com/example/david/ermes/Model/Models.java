package com.example.david.ermes.Model;

import com.example.david.ermes.Presenter.Match;
import com.example.david.ermes.Presenter.Sport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nicol on 17/10/2017.
 */

public class Models {
    public static class _User {
        public String idFavSport;
        public String city;

        public _User() {}

        public _User(String idFavSport, String city) {
            this.idFavSport = idFavSport;
            this.city = city;
        }
    }

    public static class _Match {
        public Date date;
        public String location;
        public String owner;
        public boolean isPublic;
        public String idSport;
        public int maxPlayers;
        public int numGuests;
        public List<String> missingStuff;

        public _Match() {}

        public _Match(String idOwner, Date date, String location, boolean isPublic,
                      String idSport, int maxPlayers, int numGuests, List<String> missingStuff) {
            this.date = date;
            this.location = location;
            this.owner = idOwner;
            this.isPublic = isPublic;
            this.idSport = idSport;
            this.maxPlayers = maxPlayers;
            this.numGuests = numGuests;
            this.missingStuff = missingStuff;
        }

        public Match convertToMatch() {
            return new Match(this.owner, this.location, this.date, this.isPublic,
                    this.idSport, this.maxPlayers, this.numGuests, this.missingStuff);
        }

        public static List<Match> convertToMatchList(List<Models._Match> list) {
            List<Match> matches = new ArrayList<>();
            for (Models._Match m : list) {
                matches.add(m.convertToMatch());
            }
            return matches;
        }
    }

    public static class _Sport {
        public String name;
        public int numPlayers;

        public _Sport() {}

        public _Sport(String name, int numPlayers) {
            this.name = name;
            this.numPlayers = numPlayers;
        }

        public Sport convertToSport() { return new Sport(this.name, this.numPlayers); }

        public static List<Sport> convertToSportList(List<Models._Sport> list) {
            List<Sport> sports = new ArrayList<>();
            for (Models._Sport s : list) {
                sports.add(s.convertToSport());
            }
            return sports;
        }
    }
}
