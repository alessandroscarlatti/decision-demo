package com.scarlatti.decision;

import java.util.Objects;

/**
 * @author Alessandro Scarlatti
 * @since Saturday, 2/2/2019
 */
public class SimpleBooleanDecision {

    private boolean decision;
    private String description;

    public SimpleBooleanDecision(boolean decision, String description) {
        this.decision = decision;
        this.description = description;
    }

    @Override
    public String toString() {
        return "SimpleBooleanDecision{" +
            "decision=" + decision +
            ", description='" + description + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleBooleanDecision that = (SimpleBooleanDecision) o;
        return decision == that.decision &&
            Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(decision, description);
    }

    public boolean getDecision() {
        return decision;
    }

    public String getDescription() {
        return description;
    }
}
