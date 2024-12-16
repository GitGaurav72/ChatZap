package com.novaedge.chatzap.api.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="CT_CONVERSATION")
public class conversationEntity {
	
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "Conv_id_Sequence")
    @SequenceGenerator(name = "Conv_id_Sequence", sequenceName = "CONV_SEQ")
    @Column(name="CONVERSATION_ID")
    private Long conversationId;
    
    @Column(name="NAME")
    private String name;
    
    @Column(name="IS_GROUP")
    private boolean isGroup;
    
    @CreationTimestamp
    @Column(name="CREATED_AT")
    private Date createdAt;
    
    @Column(name="UPDATED_AT")
    private Date updatedAt;
    
    
    public conversationEntity() {}

    // Constructor
    public conversationEntity(Long conversationId, String name, boolean isGroup, Date createdAt, Date updatedAt) {
        this.conversationId = conversationId;
        this.name = name;
        this.isGroup = isGroup;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean isGroup) {
        this.isGroup = isGroup;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "conversationId='" + conversationId + '\'' +
                ", name='" + name + '\'' +
                ", isGroup=" + isGroup +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

