package com.us.uml.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name = "UML_METHOD_ARG")
public class UmlMethodArg {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@Column(name="m_id")
	private int mid;

	@Column(name="type_name")
	private String typeName;

	@Column(name="type_full_name")
	private String typeFullName;

	@Column(name="arg_name")
	private String argName;

	@Column(name="sort")
	private int sort;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public int getMid() {
        return mid;
    }
    public void setMid(int mid) {
        this.mid = mid;
    }
    public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName=typeName;
	}
	public String getTypeFullName() {
		return typeFullName;
	}
	public void setTypeFullName(String typeFullName) {
		this.typeFullName=typeFullName;
	}
	public String getArgName() {
		return argName;
	}
	public void setArgName(String argName) {
		this.argName=argName;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort=sort;
	}
}