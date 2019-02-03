package com.scarlatti.decision;

import com.scarlatti.cards.Card;
import org.junit.Test;

import static com.scarlatti.cards.CardRank.*;
import static com.scarlatti.cards.CardSuit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Alessandro Scarlatti
 * @since Saturday, 2/2/2019
 */
public class SimpleDecisionDemo {

    @Test
    public void decisionToString() {
        SimpleBooleanDecision decisionTrue = new SimpleBooleanDecision(true, "Card is red.");
        SimpleBooleanDecision decisionFalse = new SimpleBooleanDecision(false, "Card is red.");

        System.out.println(decisionTrue);
        System.out.println(decisionFalse);
    }

    @Test
    public void useDecisionInAnIfStatement() {
        Card card = new Card(ACE, SPADES);

        SimpleBooleanDecision decision;

        if (card.getRank() == ACE) {
            decision = new SimpleBooleanDecision(true, "Card is an Ace.");
        } else {
            decision = new SimpleBooleanDecision(false, "Card is an Ace.");
        }

        System.out.println(decision);

        SimpleBooleanDecision expectedDecision = new SimpleBooleanDecision(true, "Card is an Ace.");

        assertEquals(expectedDecision, decision);
    }

    @Test
    public void usageWithoutDefiningDescriptionMultipleTimes() {
        Card card = new Card(ACE, SPADES);

        BooleanDecision cardIsAnAce = new BooleanDecision("Card is an Ace.");
        System.out.println("Defined:");
        System.out.println(cardIsAnAce);

        if (card.getRank() == ACE) {
            cardIsAnAce.decide(true);
        } else {
            cardIsAnAce.decide(false);
        }

        System.out.println("Decided:");
        System.out.println(cardIsAnAce);

        assertTrue(cardIsAnAce.getDecision());
    }

    @Test
    public void usageWithAssume() {
        Card card = new Card(TWO, CLUBS);

        BooleanDecision cardIsAnAce = new BooleanDecision("Card is an Ace.");
        System.out.println("Defined:");
        System.out.println(cardIsAnAce);

        cardIsAnAce.assume(false);

        System.out.println("Assumed:");
        System.out.println(cardIsAnAce);

        if (card.getRank() == ACE)
            cardIsAnAce.decide(true);

        System.out.println("Decided:");
        System.out.println(cardIsAnAce);

        assertFalse(cardIsAnAce.getDecision());
    }

    @Test
    public void dynamiteLogic() {
        Card card = new Card(SIX, HEARTS);

        // if the card is black or a 5, you blow up!
        BooleanDecision youBlowUp = BooleanDecision.assume(false, "You blow up!");
        BooleanDecision cardIsBlack = BooleanDecision.assume(false, "Card is black.");
        BooleanDecision cardIsAFive = BooleanDecision.assume(false, "Card is a Five.");

        if (card.getSuit() == CLUBS || card.getSuit() == SPADES)
            cardIsBlack.decide(true);

        if (card.getRank() == FIVE)
            cardIsAFive.decide(true);

        if (cardIsBlack.getDecision() || cardIsAFive.getDecision())
            youBlowUp.decide(true);

        System.out.println(youBlowUp);
        System.out.println(cardIsBlack);
        System.out.println(cardIsAFive);

        assertFalse(youBlowUp.getDecision());
    }

    @Test
    public void dynamiteLogicWithProofThatADecisionWasMade() {
        Card card = new Card(FIVE, HEARTS);

        // if the card is black or a 5, you blow up!
        BooleanDecision youBlowUp = BooleanDecision.assume(false, "You blow up!");
        BooleanDecision cardIsBlack = BooleanDecision.assume(false, "Card is black.");
        BooleanDecision cardIsAFive = BooleanDecision.assume(false, "Card is a Five.");

        if (card.getSuit() == CLUBS || card.getSuit() == SPADES)
            cardIsBlack.decide(true);
        else
            cardIsBlack.decide(false);

        if (card.getRank() == FIVE)
            cardIsAFive.decide(true);
        else
            cardIsAFive.decide(false);

        if (cardIsBlack.getDecision() || cardIsAFive.getDecision())
            youBlowUp.decide(true);
        else
            youBlowUp.decide(false);

        System.out.println(youBlowUp);
        System.out.println(cardIsBlack);
        System.out.println(cardIsAFive);

        assertFalse(youBlowUp.getDecision());
    }

    @Test
    public void dynamiteLogicWithDecisionSyntax() {
        Card card = new Card(FIVE, HEARTS);

        // if the card is black or a 5, you blow up!
        BooleanDecision youBlowUp = BooleanDecision.assume(false, "You blow up!");
        BooleanDecision cardIsBlack = BooleanDecision.assume(false, "Card is black.");
        BooleanDecision cardIsAFive = BooleanDecision.assume(false, "Card is a Five.");

        cardIsBlack.decide(card.getSuit() == CLUBS || card.getSuit() == SPADES);
        cardIsAFive.decide(card.getRank() == FIVE);
        youBlowUp.decide(cardIsAFive.getDecision() || cardIsBlack.getDecision());

        System.out.println(youBlowUp);
        System.out.println(cardIsBlack);
        System.out.println(cardIsAFive);

        assertTrue(youBlowUp.getDecision());
    }
}
