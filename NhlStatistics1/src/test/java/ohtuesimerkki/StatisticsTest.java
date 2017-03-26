/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Samu
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12)); //16
            players.add(new Player("Lemieux", "PIT", 45, 54)); //99
            players.add(new Player("Kurri",   "EDM", 37, 53)); // 90
            players.add(new Player("Yzerman", "DET", 42, 56)); // 98
            players.add(new Player("Gretzky", "EDM", 35, 89)); // 124
 
            return players;
        }
    };
 
    Statistics stats;
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void loytaaPelaajan() {
        String nimi = "Semenko";
        Player palautus = stats.search(nimi);
        assertEquals(palautus.getName(), nimi);
    }
    
    @Test
    public void eiLoydaPelaajaa() {
        String nimi = "abc";
        boolean oliko = false;
        Player palautus = stats.search(nimi);
        if (palautus == null) {
            oliko = true;
        }
        assertTrue(oliko);
    }
    
    @Test
    public void loytaaTiimin() {
        String tiimi = "EDM";
        String s = "Semenko";
        String k = "Kurri";
        String g = "Gretzky";
        List<Player> pelaajat = stats.team(tiimi);
        boolean oliko = true;
        for (Player p : pelaajat) {
            if (!(p.getName().matches(s) || p.getName().matches(k) || p.getName().matches(g))) {
                oliko = false;
                break;
            }
        }
        assertTrue(oliko);
    }
    
    @Test
    public void parhaatPelaajatYksi() {
        String s = "Semenko";
        String k = "Kurri";
        String g = "Gretzky";
        List<Player> pelaajat = stats.topScorers(0);
        boolean oliko = true;
        int montako = 0;
        for (Player p : pelaajat) {
            if (!(p.getName().matches(g))) {
                oliko = false;
                break;
            } else {
                montako++;
            }
        }
        if (montako != 1) {
            oliko = false;
        }
        assertTrue(oliko);
    }
    
    @Test
    public void parhaatPelaajatKaksi() {
        String s = "Semenko";
        String k = "Kurri";
        String g = "Gretzky";
        String l = "Lemieux";
        List<Player> pelaajat = stats.topScorers(1);
        boolean oliko = true;
        int montako = 0;
        for (Player p : pelaajat) {
            if (!(p.getName().matches(g)|| p.getName().matches(l))) {
                oliko = false;
                break;
            } else {
              montako++;  
            }
        }
        if (montako != 2) {
            oliko = false;
        }
        assertTrue(oliko);
    }
}
