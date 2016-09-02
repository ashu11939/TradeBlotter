package team6.onlinetradeblotter.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the messages database table.
 * 
 */
@Entity
@Table(name="messages_new")
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
	
	public Message(String groupName,String message,String sender, Date date){
		this.groupName=groupName;
		this.message=message;
		this.sender=sender;
		this.time=date;
	}
	
	private static final long serialVersionUID = 1L;

	@Id
	private int SLNo;

	private String message;

	private String sender;
	
	private String groupName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	public Message() {
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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}