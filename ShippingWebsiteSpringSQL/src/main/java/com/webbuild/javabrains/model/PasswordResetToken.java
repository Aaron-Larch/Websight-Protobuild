package com.webbuild.javabrains.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PASSWORDRESETTOKEN") //Table Reference in database
public class PasswordResetToken {

	private static final int EXPIRATION = 60 * 24;

	@Id //identify primary key
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence") //Set Value to auto populate in database
	@SequenceGenerator(name = "id_Sequence", sequenceName = "Token_SEQ") //Declare Database Sequence you want to use
	private Long Id;
	private String token;
	private long userid;
	private Date expirydate;

	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_PersonID")
	private User user;

	public PasswordResetToken() {
		super();
	}

	public PasswordResetToken(final String token) {
		super();

		this.token = token;
		this.expirydate = calculateExpiryDate(EXPIRATION);
	}

	public PasswordResetToken(final String token, final User user) {
		super();

		this.token = token;
		this.userid = user.getId();
		this.user=user;
		this.expirydate = calculateExpiryDate(EXPIRATION);
	}

	//
	public Long getId() {
		return Id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(final String token) {
		this.token = token;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public Date getExpiryDate() {
		return expirydate;
	}

	public void setExpiryDate(final Date expiryDate) {
		this.expirydate = expiryDate;
	}

	private Date calculateExpiryDate(final int expiryTimeInMinutes) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}

	public void updateToken(final String token) {
		this.token = token;
		this.expirydate = calculateExpiryDate(EXPIRATION);
	}

	//

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expirydate == null) ? 0 : expirydate.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final PasswordResetToken other = (PasswordResetToken) obj;
		if (expirydate == null) {
			if (other.expirydate != null) {
				return false;
			}
		} else if (!expirydate.equals(other.expirydate)) {
			return false;
		}
		if (token == null) {
			if (other.token != null) {
				return false;
			}
		} else if (!token.equals(other.token)) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Token [String=").append(token).append("]").append("[Expires").append(expirydate).append("]");
		return builder.toString();
	}

}