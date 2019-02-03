package com.scarlatti.decision;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author Alessandro Scarlatti
 * @since Saturday, 2/2/2019
 */
public class ComplexDecisionDemo {

    private enum BookDecisionType {
        CleanBook,
        DirtyBook,
        NotABook
    }

    @Test
    public void makeHandAndFootBookDecision() {
        BooleanDecision hasAtLeastSevenCards = new BooleanDecision("Has at least 7 cards.");
        BooleanDecision allCardsSameNumber = new BooleanDecision("All cards same number.");
        BooleanDecision nonWildCardsSameNumber = new BooleanDecision("All non-wild cards same number.");
        BooleanDecision allCardsWild = new BooleanDecision("All cards are wild.");
        BooleanDecision fewerWildCardsThanNonWildCards = new BooleanDecision("Fewer wild cards than non-wild cards.");

        List<Integer> cards = Arrays.asList(4, 4, 4, 4, 5, 6, 5);

        hasAtLeastSevenCards.decide(cards.size() >= 7);

        // decide "all cards same number"
        Set<Integer> uniqueNumbers = new HashSet<>(cards);
        allCardsSameNumber.decide(uniqueNumbers.size() <= 1);

        // decide non-wild cards same number
        List<Integer> nonWildNumbers = cards
            .stream()
            .filter(number -> !number.equals(1) && !number.equals(2))
            .collect(toList());

        Set<Integer> uniqueNonWildNumbers = new HashSet<>(nonWildNumbers);
        nonWildCardsSameNumber.decide(uniqueNonWildNumbers.size() <= 1);

        long countWildCards = cards
            .stream()
            .filter(number -> number.equals(1) || number.equals(2))
            .count();

        allCardsWild.decide(countWildCards == cards.size());

        fewerWildCardsThanNonWildCards.decide(nonWildNumbers.size() < cards.size());

        BookDecisionType bookDecisionType = null;

        if (hasAtLeastSevenCards.getDecision()) {
            if (allCardsSameNumber.getDecision()) {
                if (allCardsWild.getDecision()) {
                    bookDecisionType = BookDecisionType.NotABook;
                } else {
                    bookDecisionType = BookDecisionType.CleanBook;
                }
            } else {
                if (nonWildCardsSameNumber.getDecision()) {
                    if (fewerWildCardsThanNonWildCards.getDecision()) {
                        bookDecisionType = BookDecisionType.DirtyBook;
                    }
                } else {
                    bookDecisionType = BookDecisionType.NotABook;
                }
            }
        } else {
            bookDecisionType = BookDecisionType.NotABook;
        }

        System.out.println(hasAtLeastSevenCards);
        System.out.println(allCardsSameNumber);
        System.out.println(nonWildCardsSameNumber);
        System.out.println(allCardsWild);
        System.out.println(fewerWildCardsThanNonWildCards);

        assertNotNull(bookDecisionType);
        assertEquals(BookDecisionType.NotABook, bookDecisionType);
    }

    public static class HandAndFootBookDecision {
        DecisionTreeDecision decision = new DecisionTreeDecision(
            new BooleanDecision("Has at least 7 cards."),
            new BooleanDecision("All cards same number."),
            null
        );
    }

    public static class DecisionTreeDecision {

        private BooleanDecision decision;
        private BooleanDecision decisionOnTrue;
        private BooleanDecision decisionOnFalse;

        public DecisionTreeDecision(BooleanDecision decision, BooleanDecision decisionOnTrue, BooleanDecision decisionOnFalse) {
            this.decision = decision;
            this.decisionOnTrue = decisionOnTrue;
            this.decisionOnFalse = decisionOnFalse;
        }
    }
}
