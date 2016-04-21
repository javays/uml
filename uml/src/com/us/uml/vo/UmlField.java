package com.us.uml.vo;


public class UmlField {

    private int    id;

    private int    cId;

    private String name;

    private int    typeId;

    private String typeName;

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

    public int getCId() {
        return cId;
    }

    public void setCId(int cId) {
        this.cId = cId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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