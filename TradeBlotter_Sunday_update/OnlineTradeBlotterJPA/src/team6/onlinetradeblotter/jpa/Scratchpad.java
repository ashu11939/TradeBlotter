package team6.onlinetradeblotter.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the scratchpad database table.
 * 
 */
@Entity
@Table(name = "scratchpad")
@NamedQuery(name="Scratchpad.findAll", query="SELECT s FROM Scratchpad s")
public class Scratchpad implements Serializable {
	private static final long serialVersionUID = 1L;

	private String notes;
	@Id
	private String userName;
	
	public Scratchpad(){
		
	}
	public Scratchpad(String userName, String note) {
		this.userName = userName;
		this.notes = note ;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}