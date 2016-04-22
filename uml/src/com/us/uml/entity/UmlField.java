package com.us.uml.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name = "UML_FIELD")
public class UmlField {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="c_id")
    private int cid;

    @Column(name="name")
    private String name;

    @Column(name="type_id")
    private int typeId;

    @Column(name="type_name")
    private String typeName;

    @Column(name="access_flag")
    private int accessFlag;

    @Column(name="abstract_flag")
    private int abstractFlag;

    @Column(name="static_flag")
    private int staticFlag;

    @Column(name="final_flag")
    private int finalFlag;


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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }
    public int getTypeId() {
        return typeId;
    }
    public void setTypeId(int typeId) {
        this.typeId=typeId;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName=typeName;
    }
    public int getAccessFlag() {
        return accessFlag;
    }
    public void setAccessFlag(int accessFlag) {
        this.accessFlag=accessFlag;
    }
    public int getAbstractFlag() {
        return abstractFlag;
    }
    public void setAbstractFlag(int abstractFlag) {
        this.abstractFlag=abstractFlag;
    }
    public int getStaticFlag() {
        return staticFlag;
    }
    public void setStaticFlag(int staticFlag) {
        this.staticFlag=staticFlag;
    }
    public int getFinalFlag() {
        return finalFlag;
    }
    public void setFinalFlag(int finalFlag) {
        this.finalFlag=finalFlag;
    }
    @Override
    public String toString() {
        return "UmlField [id=" + id
                + ", cid="
                + cid
                + ", name="
                + name
                + ", typeId="
                + typeId
                + ", typeName="
                + typeName
                + ", accessFlag="
                + accessFlag
                + ", abstractFlag="
                + abstractFlag
                + ", staticFlag="
                + staticFlag
                + ", finalFlag="
                + finalFlag
                + "]";
    }
}