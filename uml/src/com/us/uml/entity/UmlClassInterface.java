package com.us.uml.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name = "UML_CLASS_INTERFACE")
public class UmlClassInterface {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="c_id")
    private int cid;

    @Column(name="i_id")
    private int iid;


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
    @Override
    public String toString() {
        return "UmlClassInterface [id=" + id
                + ", cid="
                + cid
                + ", iid="
                + iid
                + "]";
    }
}