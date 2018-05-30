package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void playerInstaniatesCorrectly() throws Exception {
        Player testPlayer = new Player("Bob");
        assertTrue(testPlayer instanceof Player);
    }

    @Test
    public void playerNameValueIsAssigned() {
        Player testPlayer = new Player("Bob");
        assertEquals("Bob", testPlayer.getName());
    }

    @Test
    public void playerMoneyValueIsAssigned() {
        Player testPlayer = new Player("Bob");
        assertEquals(100, testPlayer.getMoney());
    }

    @Test
    public void changeMoney_returnsNewMoneyTotal_55() {
        Player testPlayer = new Player("Bob");
        testPlayer.changeMoney(-45);
        assertEquals(55, testPlayer.getMoney());
    }
}