package com.scarlatti.decision;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author Alessandro Scarlatti
 * @since Saturday, 2/2/2019
 */
public class BooleanDecision {
    private Boolean decision;
    private String assertionDescription;
    private DecisionState decisionState = DecisionState.DEFINED;

    public BooleanDecision(String assertionDescription) {
        this.assertionDescription = assertionDescription;
    }

    public static BooleanDecision assume(boolean decision, String description) {
        BooleanDecision decisionObj = new BooleanDecision(description);
        decisionObj.assume(decision);
        return decisionObj;
    }

    public void assume(boolean decision) {
        if (decisionState != DecisionState.DEFINED)
            throw new IllegalStateException("Decision already assumed: " + this);

        this.decision = decision;
        this.decisionState = DecisionState.ASSUMED;
    }

    public void decide(boolean decision) {
        if (decisionState == DecisionState.DECIDED)
            throw new IllegalStateException("Decision already made: " + this);

        this.decision = decision;
        this.decisionState = DecisionState.DECIDED;
    }

    public void decide(Supplier<Boolean> decisionSupplier) {
        if (decisionState == DecisionState.DECIDED)
            throw new IllegalStateException("Decision already made: " + this);

        Boolean decision = decisionSupplier.get();
        if (decision == null)
            throw new IllegalStateException("Decision may not be null.");

        this.decision = decision;
        this.decisionState = DecisionState.DECIDED;
    }

    @Override
    public String toString() {
        return "BooleanDecision{" +
            "decision=" + decision +
            ", assertionDescription='" + assertionDescription + '\'' +
            ", decisionState=" + decisionState +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooleanDecision that = (BooleanDecision) o;
        return Objects.equals(decision, that.decision) &&
            Objects.equals(assertionDescription, that.assertionDescription) &&
            decisionState == that.decisionState;
    }

    @Override
    public int hashCode() {

        return Objects.hash(decision, assertionDescription, decisionState);
    }

    public boolean getDecision() {
        if (decisionState == DecisionState.DEFINED)
            throw new IllegalStateException("No choice assumed or decided for decision: " + this);
        return decision;
    }

    public String getAssertionDescription() {
        return assertionDescription;
    }

    public DecisionState getDecisionState() {
        return decisionState;
    }
}
