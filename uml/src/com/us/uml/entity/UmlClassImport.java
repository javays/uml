package com.us.uml.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name = "UML_CLASS_IMPORT")
public class UmlClassImport {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@Column(name="c_id")
	private int cid;

	@Column(name="cls_import")
	private String clsImport;


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
	public String getClsImport() {
		return clsImport;
	}
	public void setClsImport(String clsImport) {
		this.clsImport=clsImport;
	}
    @Override
    public String toString() {
        return "UmlClassImport [id=" + id
                + ", cid="
                + cid
                + ", clsImport="
                + clsImport
                + "]";
    }
}