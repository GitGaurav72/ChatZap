package com.novaedge.chatzap.api.model;

public class UserModel {
	
		private Long id;
		
		private String firstname;
		
		private String lastname;

	    private String username;

	   
	    private String email;

	    
	    private byte[] profilePicture;

	    
	    private String status;


		public UserModel(Long id, String firstname, String lastname, String username, String email, byte[] profilePicture, String status) {
			super();
			this.id=id;
			this.firstname=firstname;
			this.lastname = lastname;
			this.username = username;
			this.email = email;
			this.profilePicture = profilePicture;
			this.status = status;
		}


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


		public UserModel() {
			super();
			// TODO Auto-generated constructor stub
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
	    
	    
}
