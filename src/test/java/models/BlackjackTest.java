package models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BlackjackTest {

    @Test
    public void newBlackjack_instantiatesCorrectly_true() throws Exception {
        Blackjack testBlackjack = new Blackjack();
        assertEquals(true, testBlackjack instanceof Blackjack);
    }

    @Test
    public void getDeck_returnsBlackjackDeck_ArrayList() {
        Blackjack testBlackjack = new Blackjack();
        List<String> expected = new ArrayList<>();
        assertEquals(expected, testBlackjack.getDeck());
    }

    @Test
    public void getDealerHand_returnsDealerHand_ArrayList() {
        Blackjack testBlackjack = new Blackjack();
        List<String> expected = new ArrayList<>();
        assertEquals(expected, testBlackjack.getDealerHand());
    }

    @Test
    public void getPlayerHand_returnsPlayerHand_ArrayList() {
        Blackjack testBlackjack = new Blackjack();
        List<String> expected = new ArrayList<>();
        assertEquals(expected, testBlackjack.getPlayerHand());
    }

    @Test
    public void getSecondHand_returnsPlayerSecondHand_ArrayList() {
        Blackjack testBlackjack = new Blackjack();
        List<String> expected = new ArrayList<>();
        assertEquals(expected, testBlackjack.getSecondHand());
    }

    @Test
    public void setDeck_populatesDeckWithUniqueCardValues_Ah() {
        Blackjack testBlackjack = new Blackjack();
        testBlackjack.setDeck();
        String expected = "A-H";
        assertEquals("A-H", testBlackjack.getDeck().get(0));
    }

    @Test
    public void generateRandomNumber_generatesRandomNumberCorrectly_true() {
        Blackjack testBlackjack = new Blackjack();
        testBlackjack.setDeck();
        assertEquals(true, testBlackjack.generateRandomNumber() instanceof Integer);
    }

    @Test
    public void dealCard_addsCardToHand_false() {
        Blackjack testBlackjack = new Blackjack();
        testBlackjack.setDeck();
        Integer testNumber = testBlackjack.generateRandomNumber();
        testBlackjack.dealCard(testNumber, "dealer");
        assertEquals(false, testBlackjack.getDealerHand().isEmpty());
    }

    @Test
    public void dealCard_removesCardFromDeck_false() {
        Blackjack testBlackjack = new Blackjack();
        testBlackjack.setDeck();
        testBlackjack.dealCard(0, "dealer");
        assertEquals(false, testBlackjack.getDeck().contains("A-H"));
    }


    @Test
    public void evaluateHand_calculatesHandValue_5() {
        Blackjack testBlackjack = new Blackjack();
        testBlackjack.setDeck();
        testBlackjack.dealCard(1, "Player");
        testBlackjack.dealCard(2, "Player");
        Integer expected = 6;
        assertEquals(expected, testBlackjack.evaluateHand("Player"));

    }
    @Test
    public void evaluateHand_calculatesAceValueHigh_13() {
        Blackjack testBlackjack = new Blackjack();
        testBlackjack.setDeck();
        testBlackjack.dealCard(0, "Player");
        testBlackjack.dealCard(1, "Player");
        Integer expected = 14;
        assertEquals(expected, testBlackjack.evaluateHand("Player"));

    }
    @Test
    public void evaluateHand_calculatesAceValueLow_13() {
        Blackjack testBlackjack = new Blackjack();
        testBlackjack.setDeck();
        testBlackjack.dealCard(0, "Player");
        testBlackjack.dealCard(1, "Player");
        testBlackjack.dealCard(9, "Player");
        Integer expected = 14;
        assertEquals(expected, testBlackjack.evaluateHand("Player"));

    }

    @Test
    public void checkBlackjack_returnsTrueIfHandIsBlackjack_true() {
        Blackjack testBlackjack = new Blackjack();
        testBlackjack.setDeck();
        testBlackjack.dealCard(0, "Player");
        testBlackjack.dealCard(9, "Player");
        assertEquals(true, testBlackjack.checkBlackjack("Player"));
    }

    @Test
    public void checkBlackjack_returnsFalseIfHandIsNotBlackjack_false() {
        Blackjack testBlackjack = new Blackjack();
        testBlackjack.setDeck();
        testBlackjack.dealCard(1, "Player");
        testBlackjack.dealCard(9, "Player");
        assertEquals(false, testBlackjack.checkBlackjack("Player"));
    }

    @Test
    public void checkWin_returnsWinWhenPlayerBeatsDealer_win() {
        Blackjack testBlackjack = new Blackjack();
        testBlackjack.setDeck();
        testBlackjack.dealCard(10, "Player");
        testBlackjack.dealCard(9, "Player");
        testBlackjack.dealCard(8, "dealer");
        testBlackjack.dealCard(9, "dealer");
        assertEquals("win", testBlackjack.checkWin("Player"));
    }

    @Test
    public void checkWin_returnsLoseWhenDealerBeatsPlayer_lose() {
        Blackjack testBlackjack = new Blackjack();
        testBlackjack.setDeck();
        testBlackjack.dealCard(8, "Player");
        testBlackjack.dealCard(9, "Player");
        testBlackjack.dealCard(10, "dealer");
        testBlackjack.dealCard(9, "dealer");
        assertEquals("lose", testBlackjack.checkWin("Player"));
    }

    @Test
    public void checkWin_returnsDrawWhenPlayerHandEqualsDealerHand_draw() {
        Blackjack testBlackjack = new Blackjack();
        testBlackjack.setDeck();
        testBlackjack.dealCard(10, "Player");
        testBlackjack.dealCard(9, "Player");
        testBlackjack.dealCard(10, "dealer");
        testBlackjack.dealCard(9, "dealer");
        assertEquals("draw", testBlackjack.checkWin("Player"));
    }

    @Test
    public void checkBust_returnsTrueWhenPlayerBusts_true() {
        Blackjack testBlackjack = new Blackjack();
        testBlackjack.setDeck();
        testBlackjack.dealCard(10, "Player");
        testBlackjack.dealCard(9, "Player");
        testBlackjack.dealCard(5, "Player");
        assertEquals(true, testBlackjack.checkBust("Player"));
    }

    @Test
    public void checkPair_returnsTrueIfPlayerHandIsPair_true() {
        Blackjack testBlackjack = new Blackjack();
        testBlackjack.setDeck();
        testBlackjack.dealCard(0, "Player");
        testBlackjack.dealCard(12, "Player");
        assertEquals(true, testBlackjack.checkPair());
    }

    @Test
    public void checkPair_returnsFalseIfPlayerHandIsPair_false() {
        Blackjack testBlackjack = new Blackjack();
        testBlackjack.setDeck();
        testBlackjack.dealCard(0, "Player");
        testBlackjack.dealCard(11, "Player");
        assertEquals(false, testBlackjack.checkPair());
    }

    @Test
    public void splitHand_addsCardToSecondHand_true() {
        Blackjack testBlackjack = new Blackjack();
        testBlackjack.setDeck();
        testBlackjack.dealCard(0, "Player");
        testBlackjack.dealCard(12, "Player");
        testBlackjack.splitHand();
        assertEquals("A-H", testBlackjack.getSecondHand().get(0));
    }

    @Test
    public void splitHand_removesCardFromPlayerHand_false() {
        Blackjack testBlackjack = new Blackjack();
        testBlackjack.setDeck();
        testBlackjack.dealCard(0, "Player");
        testBlackjack.dealCard(12, "Player");
        testBlackjack.splitHand();
        assertEquals(false, testBlackjack.getPlayerHand().contains("A-H"));
    }

}