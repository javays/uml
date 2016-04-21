package com.us.uml.vo;


public class UmlMethod {

    private int    id;

    private String name;

    private int    retTypeId;

    private String retTypeName;

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

    public int getRetTypeId() {
        return retTypeId;
    }

    public void setRetTypeId(int retTypeId) {
        this.retTypeId = retTypeId;
    }

    public String getRetTypeName() {
        return retTypeName;
    }

    public void setRetTypeName(String retTypeName) {
        this.retTypeName = retTypeName;
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