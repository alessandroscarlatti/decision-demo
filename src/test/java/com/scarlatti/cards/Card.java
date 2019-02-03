package com.scarlatti.cards;

/**
 * @author Alessandro Scarlatti
 * @since Saturday, 2/2/2019
 */
public class Card {
    private CardRank rank;
    private CardSuit suit;

    public Card(CardRank cardRank, CardSuit cardSuit) {
        this.rank = cardRank;
        this.suit = cardSuit;
    }

    public CardRank getRank() {
        return rank;
    }

    public CardSuit getSuit() {
        return suit;
    }
}
