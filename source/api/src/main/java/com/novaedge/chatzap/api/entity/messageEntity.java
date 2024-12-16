package com.novaedge.chatzap.api.entity;

import java.sql.Date;
import java.time.LocalDateTime;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CT_MESSAGE")
public class messageEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "Msg_id_Sequence")
    @SequenceGenerator(name = "Msg_id_Sequence", sequenceName = "MSG_SEQ")
    @Column(name="MESSAGE_ID")
    private Long messageId;
    
//    @ManyToOne
//    @JoinColumn(name="SENDER_ID", nullable=false)
// //   @JsonIgnore // Ignore the full object during serialization
//    @JsonBackReference("sentMessages")
//    private userEntity sender;
//    
//    @ManyToOne
//    @JoinColumn(name="RECEIVER_ID", nullable=false)
//   // @JsonIgnore // Ignore the full object during serialization
//    @JsonBackReference("receivedMessages")
//    private userEntity receiver;
//    
//    @ManyToOne
//    @JoinColumn(name="CONVERSATION_ID", nullable=false)
//    @JsonBackReference
//    private conversationEntity conversation;
    
    @Column(name="SENDER_ID")
    private Long sender;
 
    @Column(name="RECEIVER_ID")
    private Long receiver;
    
    @Column(name="CONVERSATION_ID")
    private Long conversation;
    
    
    @Column(name="CONTENT")
    private String content;
    
    @CreationTimestamp
    @Column(name="TIMESTAMP")
    private Date timestamp;
    
    @Column(name="IS_READ")
    private boolean isRead;
    
    // Default constructor
    public messageEntity() {}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Long getSender() {
		return sender;
	}

	public void setSender(Long sender) {
		this.sender = sender;
	}

	public Long getReceiver() {
		return receiver;
	}

	public void setReceiver(Long receiver) {
		this.receiver = receiver;
	}

	public Long getConversation() {
		return conversation;
	}

	public void setConversation(Long conversation) {
		this.conversation = conversation;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public messageEntity(Long messageId, Long sender, Long receiver, Long conversation, String content,
			Date timestamp, boolean isRead) {
		super();
		this.messageId = messageId;
		this.sender = sender;
		this.receiver = receiver;
		this.conversation = conversation;
		this.content = content;
		this.timestamp = timestamp;
		this.isRead = isRead;
	}

	@Override
	public String toString() {
		return "messageEntity [messageId=" + messageId + ", sender=" + sender + ", receiver=" + receiver
				+ ", conversation=" + conversation + ", content=" + content + ", timestamp=" + timestamp + ", isRead="
				+ isRead + "]";
	}

	
	
	
	
    // Constructor
//    public messageEntity(Long messageId, userEntity sender, userEntity receiver, conversationEntity conversation, String content, LocalDateTime timestamp, boolean isRead) {
//        this.messageId = messageId;
//        this.sender = sender;
//        this.receiver = receiver;
//        this.conversation = conversation;
//        this.content = content;
//        this.timestamp = timestamp;
//        this.isRead = isRead;
//    }
//
//    // Getters and Setters
//    public Long getMessageId() {
//        return messageId;
//    }
//
//    public void setMessageId(Long messageId) {
//        this.messageId = messageId;
//    }
//
//    public userEntity getSender() {
//        return sender;
//    }
//
//    public void setSender(userEntity sender) {
//        this.sender = sender;
//    }
//
//    public userEntity getReceiver() {
//        return receiver;
//    }
//
//    public void setReceiver(userEntity receiver) {
//        this.receiver = receiver;
//    }
//
//    public conversationEntity getConversation() {
//        return conversation;
//    }
//
//    public void setConversation(conversationEntity conversation) {
//        this.conversation = conversation;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public LocalDateTime getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(LocalDateTime timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    public boolean isRead() {
//        return isRead;
//    }
//
//    public void setRead(boolean isRead) {
//        this.isRead = isRead;
//    }
//
//    // Custom getter for senderId
//    public Long getSenderId() {
//        return sender != null ? sender.getId() : null;
//    }
//
//    // Custom getter for receiverId
//    public Long getReceiverId() {
//        return receiver != null ? receiver.getId() : null;
//    }
//
//    @Override
//    public String toString() {
//        return "Message{" +
//                "messageId='" + messageId + '\'' +
//                ", senderId='" + getSenderId() + '\'' +
//                ", receiverId='" + getReceiverId() + '\'' +
//                ", conversationId='" + conversation.getConversationId() + '\'' +
//                ", content='" + content + '\'' +
//                ", timestamp=" + timestamp +
//                ", isRead=" + isRead +
//                '}';
//    }
    
    
    
}
