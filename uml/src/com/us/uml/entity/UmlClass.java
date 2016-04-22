package com.us.uml.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name = "UML_CLASS")
public class UmlClass {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="type")
    private int type;

    @Column(name="name")
    private String name;

    @Column(name="pkg_name")
    private String pkgName;

    @Column(name="full_name")
    private String fullName;

    @Column(name="super_class")
    private String superClass;

    @Column(name="interfaces")
    private String interfaces;

    @Column(name="access_flag")
    private String accessFlag;
    
    @Column(name="other_modifier")
    private String otherModifier;

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
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type=type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }
    public String getPkgName() {
        return pkgName;
    }
    public void setPkgName(String pkgName) {
        this.pkgName=pkgName;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName=fullName;
    }
    public String getSuperClass() {
        return superClass;
    }
    public void setSuperClass(String superClass) {
        this.superClass=superClass;
    }
    public String getInterfaces() {
        return interfaces;
    }
    public void setInterfaces(String interfaces) {
        this.interfaces=interfaces;
    }
    public String getAccessFlag() {
        return accessFlag;
    }
    public void setAccessFlag(String accessFlag) {
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
    
    public String getOtherModifier() {
        return otherModifier;
    }
    public void setOtherModifier(String otherModifier) {
        this.otherModifier = otherModifier;
    }
    @Override
    public String toString() {
        return "UmlClass [id=" + id
                + ", type="
                + type
                + ", name="
                + name
                + ", pkgName="
                + pkgName
                + ", fullName="
                + fullName
                + ", superClass="
                + superClass
                + ", interfaces="
                + interfaces
                + ", accessFlag="
                + accessFlag
                + ", otherModifier="
                + otherModifier
                + ", abstractFlag="
                + abstractFlag
                + ", staticFlag="
                + staticFlag
                + ", finalFlag="
                + finalFlag
                + "]";
    }
}