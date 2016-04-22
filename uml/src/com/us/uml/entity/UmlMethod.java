package com.us.uml.entity;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Table(name = "UML_METHOD")
public class UmlMethod {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="ret_type_id")
    private int retTypeId;

    @Column(name="ret_type_name")
    private String retTypeName;

    @Column(name="access_flag")
    private int accessFlag;

    @Column(name="abstract_flag")
    private int abstractFlag;

    @Column(name="static_flag")
    private int staticFlag;

    @Column(name="final_flag")
    private int finalFlag;
    
    @Transient
    private List<UmlMethodArg> umlMethodArgs;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id=id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }
    public int getRetTypeId() {
        return retTypeId;
    }
    public void setRetTypeId(int retTypeId) {
        this.retTypeId=retTypeId;
    }
    public String getRetTypeName() {
        return retTypeName;
    }
    public void setRetTypeName(String retTypeName) {
        this.retTypeName=retTypeName;
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
    public List<UmlMethodArg> getUmlMethodArgs() {
        return umlMethodArgs;
    }
    public void setUmlMethodArgs(List<UmlMethodArg> umlMethodArgs) {
        this.umlMethodArgs = umlMethodArgs;
    }
    @Override
    public String toString() {
        return "UmlMethod [id=" + id
                + ", name="
                + name
                + ", retTypeId="
                + retTypeId
                + ", retTypeName="
                + retTypeName
                + ", accessFlag="
                + accessFlag
                + ", abstractFlag="
                + abstractFlag
                + ", staticFlag="
                + staticFlag
                + ", finalFlag="
                + finalFlag
                + ", umlMethodArgs="
                + (umlMethodArgs == null || umlMethodArgs.size() == 0 ? "[]" : Arrays.toString(umlMethodArgs.toArray()))
                + "]";
    }
}