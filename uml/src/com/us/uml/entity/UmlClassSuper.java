package com.us.uml.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name = "UML_CLASS_SUPER")
public class UmlClassSuper {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@Column(name="c_id")
	private int cid;

	@Column(name="i_id")
	private int iid;

	@Column(name="type")
	private int type;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid=cid;
	}
	public int getIid() {
		return iid;
	}
	public void setIid(int iid) {
		this.iid=iid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type=type;
	}
    @Override
    public String toString() {
        return "UmlClassSuper [id=" + id
                + ", cid="
                + cid
                + ", iid="
                + iid
                + ", type="
                + type
                + "]";
    }
}