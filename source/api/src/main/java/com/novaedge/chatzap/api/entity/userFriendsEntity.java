package com.novaedge.chatzap.api.entity;


import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;

@Entity
@Table(name = "CT_USER_FRIENDS")
public class userFriendsEntity {

    @EmbeddedId
    private UserFriendsId id;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    // Default constructor
    public userFriendsEntity() {}

    // Constructor
    public userFriendsEntity(UserFriendsId id, String status) {
        this.id = id;
        this.status = status;
    }

    // Getters and Setters
    public UserFriendsId getId() {
        return id;
    }

    public void setId(UserFriendsId id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "userFriendsEntity{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

