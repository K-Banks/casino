package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void playerInstaniatesCorrectly() throws Exception {
        Player testPlayer = new Player();
        assertTrue(testPlayer instanceof Player);
    }

    @Test
    public void playerNameValueIsAssigned() {
        Player testPlayer = new Player();
        testPlayer.setName("Bob");
        assertEquals("Bob", testPlayer.getName());
    }

    @Test
    public void playerMoneyValueIsAssigned() {
        Player testPlayer = new Player();
        assertEquals(100, testPlayer.getMoney());
    }

    @Test
    public void playerBetValueIsAssigned() {
        Player testPlayer = new Player();
        testPlayer.setCurrentBet(45);
        assertEquals(45, testPlayer.getCurrentBet());
    }
    @Test
    public void playerHasBetValueIsAssigned() {
        Player testPlayer = new Player();
        assertEquals(false, testPlayer.isHasBet());
    }
    @Test
    public void setHasBetChangesHasBetValue() {
        Player testPlayer = new Player();
        testPlayer.setHasBet(true);
        assertEquals(true, testPlayer.isHasBet());
    }

    @Test
    public void changeMoney_returnsNewMoneyTotal_55() {
        Player testPlayer = new Player();
        testPlayer.changeMoney(-45);
        assertEquals(55, testPlayer.getMoney());
    }
}