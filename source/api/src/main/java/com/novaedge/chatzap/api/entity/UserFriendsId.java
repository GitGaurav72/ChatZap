package com.novaedge.chatzap.api.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserFriendsId implements Serializable {

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "FRIEND_ID")
    private Long friendId;

    // Default constructor
    public UserFriendsId() {}

    // Constructor
    public UserFriendsId(Long userId, Long friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    // equals() and hashCode() (required for composite keys)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFriendsId that = (UserFriendsId) o;
        return userId.equals(that.userId) && friendId.equals(that.friendId);
    }

    @Override
    public int hashCode() {
        return 31 * userId.hashCode() + friendId.hashCode();
    }
}

