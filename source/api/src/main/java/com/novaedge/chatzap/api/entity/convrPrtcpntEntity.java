package com.novaedge.chatzap.api.entity;

import java.util.Date;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="CT_Convr_PrtcpntEntity")
public class convrPrtcpntEntity {
	
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "ConvPartcpnt_id_Sequence")
    @SequenceGenerator(name = "ConvPartcpnt_id_Sequence", sequenceName = "CONV_PARTCPNT_SEQ")
    @Column(name="ID")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="CONVERSATION_ID", nullable=false)
    private conversationEntity conversation;
    
    @ManyToOne
    @JoinColumn(name="USER_ID", nullable=false)
    private userEntity user;
    
    @CreationTimestamp
    @Column(name="JOINED_AT")
    private Date joinedAt;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public conversationEntity getConversation() {
        return conversation;
    }

    public void setConversation(conversationEntity conversation) {
        this.conversation = conversation;
    }

    public userEntity getUser() {
        return user;
    }

    public void setUser(userEntity user) {
        this.user = user;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Date joinedAt) {
        this.joinedAt = joinedAt;
    }

    // Default constructor
    public convrPrtcpntEntity() {}

    // Constructor
    public convrPrtcpntEntity(Long id, conversationEntity conversation, userEntity user, Date joinedAt) {
        this.id = id;
        this.conversation = conversation;
        this.user = user;
        this.joinedAt = joinedAt;
    }
}
