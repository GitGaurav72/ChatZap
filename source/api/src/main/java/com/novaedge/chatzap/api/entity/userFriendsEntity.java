package com.novaedge.chatzap.api.entity;


import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "CT_USER_FRIENDS")
public class userFriendsEntity implements Serializable {
	
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "USR_FRND_SEQ")
    @SequenceGenerator(name = "USR_FRND_SEQ", sequenceName = "USER_FRIENDS_SEQ")
    @Column(name="ID")
    private Long id;

//    @EmbeddedId
    private UserFriendsId usrFrndId;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserFriendsId getUsrFrndId() {
		return usrFrndId;
	}

	public void setUsrFrndId(UserFriendsId usrFrndId) {
		this.usrFrndId = usrFrndId;
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

	public userFriendsEntity(Long id, UserFriendsId usrFrndId, String status, Date createdAt) {
		super();
		this.id = id;
		this.usrFrndId = usrFrndId;
		this.status = status;
		this.createdAt = createdAt;
	}

	public userFriendsEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "userFriendsEntity [id=" + id + ", usrFrndId=" + usrFrndId + ", status=" + status + ", createdAt="
				+ createdAt + "]";
	}

   
}

