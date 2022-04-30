package cz.nss.onegram.user.model.enums;

public enum FollowState {
    PENDING("pending"),
    REJECTED("rejected"),
    ACCEPTED("accepted");

    private final String state;

    FollowState(String state) {
        this.state = state;
    }
}
