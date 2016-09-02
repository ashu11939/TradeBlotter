package team6.onlinetradeblotter.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the groupdynamic database table.
 * 
 */
@Entity
@NamedQuery(name="Groupdynamic.findAll", query="SELECT g FROM Groupdynamic g")
public class Groupdynamic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int SLNo;

	private String username;

	//bi-directional many-to-one association to Groupstatic
	@ManyToOne
	@JoinColumn(name="GroupID")
	private Groupstatic groupstatic;

	public Groupdynamic() {
	}

	public int getSLNo() {
		return this.SLNo;
	}

	public void setSLNo(int SLNo) {
		this.SLNo = SLNo;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Groupstatic getGroupstatic() {
		return this.groupstatic;
	}

	public void setGroupstatic(Groupstatic groupstatic) {
		this.groupstatic = groupstatic;
	}

}