package com.novaedge.chatzap.api.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="CT_USER")
public class userEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    @Column(name="ID")
    private Long id;
    
    @Column(name="FIRST_NAME")
    private String firstname;
    
    @Column(name="LAST_NAME")
    private String lastname;

    @Column(name="USER_NAME")
    private String username;

    @Column(name="EMAIL")
    private String email;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="PROFILE_PICTURE")
    private byte[] profilePicture;

    @Column(name="STATUS")
    private String status;

    @CreationTimestamp
    @Column(name="CREATED_AT")
    private Date createdAt;

    @Column(name="UPDATED_AT")
    private Date updatedAt;

//    // One-to-Many relationship with Messages as sender
//    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<messageEntity> sentMessages;
//
//    // One-to-Many relationship with Messages as receiver
//    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<messageEntity> receivedMessages;
//
//    // Many-to-Many relationship with Conversation through ConversationParticipants
//    @ManyToMany
//    @JoinTable(
//        name = "ConversationParticipants",
//        joinColumns = @JoinColumn(name = "user_id"),
//        inverseJoinColumns = @JoinColumn(name = "conversation_id")
//    )
//    private Set<conversationEntity> conversations;
//    
    
    // Default constructor
    public userEntity() {}

    // Constructor
    public userEntity(Long id, String firstname, String lastname, String username, String email, String password, byte[] profilePicture, String status,
                      Date createdAt, Date updatedAt) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

//    public List<messageEntity> getSentMessages() {
//        return sentMessages;
//    }
//
//    public void setSentMessages(List<messageEntity> sentMessages) {
//        this.sentMessages = sentMessages;
//    }
//
//    public List<messageEntity> getReceivedMessages() {
//        return receivedMessages;
//    }
//
//    public void setReceivedMessages(List<messageEntity> receivedMessages) {
//        this.receivedMessages = receivedMessages;
//    }
//
//    public Set<conversationEntity> getConversations() {
//        return conversations;
//    }
//
//    public void setConversations(Set<conversationEntity> conversations) {
//        this.conversations = conversations;
//    }

}

