package com.us.uml.vo;

public class UmlClass {

    private int    id;

    private String name;

    private String pkgName;

    private String fullName;

    private int    superClassId;

    private int    accessFlag;

    private int    abstractFlag;

    private int    staticFlag;

    private int    finalFlag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getSuperClassId() {
        return superClassId;
    }

    public void setSuperClassId(int superClassId) {
        this.superClassId = superClassId;
    }

    public int getAccessFlag() {
        return accessFlag;
    }

    public void setAccessFlag(int accessFlag) {
        this.accessFlag = accessFlag;
    }

    public int getAbstractFlag() {
        return abstractFlag;
    }

    public void setAbstractFlag(int abstractFlag) {
        this.abstractFlag = abstractFlag;
    }

    public int getStaticFlag() {
        return staticFlag;
    }

    public void setStaticFlag(int staticFlag) {
        this.staticFlag = staticFlag;
    }

    public int getFinalFlag() {
        return finalFlag;
    }

    public void setFinalFlag(int finalFlag) {
        this.finalFlag = finalFlag;
    }
}