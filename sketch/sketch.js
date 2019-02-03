const CLEAN_BOOK = "CLEAN_BOOK";
const DIRTY_BOOK = "DIRTY_BOOK";
const NOT_A_BOOK = "NOT_A_BOOK";

// this is a description of what HAPPENED.
// it is NOT a description of what COULD happen or WOULD happen IF.
isHandAndFootBook = {
    description: "Has at least 7 cards.",
    decision: true,
    yes: {
        description: "All cards same number.",
        decision: false,
        yes: {
            description: "All non-wild cards same number.",
            decision: false,
            yes: {
                // more choices...
            },
            no: NOT_A_BOOK
        },
        no: {
            // some more choices...
            // but why should we list them here if they are not going to be made!!!
        }
    },
    no: NOT_A_BOOK
};