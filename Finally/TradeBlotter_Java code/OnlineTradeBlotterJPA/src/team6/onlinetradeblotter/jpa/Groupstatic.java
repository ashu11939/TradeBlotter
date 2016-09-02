package team6.onlinetradeblotter.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the groupstatic database table.
 * 
 */
@Entity
@Table(name="groupstatic")
@NamedQuery(name="Groupstatic.findAll", query="SELECT g FROM Groupstatic g")
public class Groupstatic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int groupID;

	private String createdBy;

	private String groupName;

	//bi-directional many-to-one association to Groupdynamic
	@OneToMany(mappedBy="groupstatic")
	private List<Groupdynamic> groupdynamics;

	public Groupstatic() {
	}

	public int getGroupID() {
		return this.groupID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<Groupdynamic> getGroupdynamics() {
		return this.groupdynamics;
	}

	public void setGroupdynamics(List<Groupdynamic> groupdynamics) {
		this.groupdynamics = groupdynamics;
	}

	public Groupdynamic addGroupdynamic(Groupdynamic groupdynamic) {
		getGroupdynamics().add(groupdynamic);
		groupdynamic.setGroupstatic(this);

		return groupdynamic;
	}

	
	public Groupdynamic removeGroupdynamic(Groupdynamic groupdynamic) {
		getGroupdynamics().remove(groupdynamic);
		groupdynamic.setGroupstatic(null);

		return groupdynamic;
	}

}