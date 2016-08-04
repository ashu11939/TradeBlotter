package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQuery(name="Messaging.findAll", query="SELECT m FROM Messaging m")
public class Messaging implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int SLNo;

	private String message;

	private String queue;

	private String receiver;

	private String sender;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	public Messaging() {
	}

	public int getSLNo() {
		return this.SLNo;
	}

	public void setSLNo(int SLNo) {
		this.SLNo = SLNo;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getQueue() {
		return this.queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}