/* 
 * Copyright (c) 2016, S.F. Express Inc. All rights reserved.
 */

package com.us.uml.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.us.uml.entity.UmlClass;
import com.us.uml.entity.UmlClassImport;
import com.us.uml.entity.UmlClassInterface;
import com.us.uml.entity.UmlClassSuper;
import com.us.uml.entity.UmlField;
import com.us.uml.entity.UmlMethod;


/**
 * 描述：
 * 
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2016年4月21日      Steven.Zhu         Create
 * ****************************************************************************
 * </pre>
 * @author Steven.Zhu
 * @since 
 */

public class UmlClassDesc {

    private UmlClass umlClass;
    private List<UmlClassImport> umlClassImports;
    private List<UmlClassInterface> umlClassInterfaces;
    private List<UmlClassSuper> umlClassSupers;
    private List<UmlField> umlFields;
    private List<UmlMethod> umlMethods;
    
    public UmlClass getUmlClass() {
        return umlClass;
    }
    public void setUmlClass(UmlClass umlClass) {
        this.umlClass = umlClass;
    }
    public List<UmlClassImport> getUmlClassImports() {
        return umlClassImports;
    }
    public void setUmlClassImports(List<UmlClassImport> umlClassImports) {
        this.umlClassImports = umlClassImports;
    }
    public List<UmlClassInterface> getUmlClassInterfaces() {
        return umlClassInterfaces;
    }
    public void setUmlClassInterfaces(List<UmlClassInterface> umlClassInterfaces) {
        this.umlClassInterfaces = umlClassInterfaces;
    }
    public List<UmlClassSuper> getUmlClassSupers() {
        return umlClassSupers;
    }
    public void setUmlClassSupers(List<UmlClassSuper> umlClassSupers) {
        this.umlClassSupers = umlClassSupers;
    }
    public List<UmlField> getUmlFields() {
        return umlFields;
    }
    public void setUmlFields(List<UmlField> umlFields) {
        this.umlFields = umlFields;
    }
    public List<UmlMethod> getUmlMethods() {
        return umlMethods;
    }
    public void setUmlMethods(List<UmlMethod> umlMethods) {
        this.umlMethods = umlMethods;
    }
    
    public void addClassImport(UmlClassImport classImport) {
        if(umlClassImports == null) {
            umlClassImports = new ArrayList<UmlClassImport>();
        }
        umlClassImports.add(classImport);
    }
    
    public void addClassInterface(UmlClassInterface umlClassInterface) {
        if(umlClassInterfaces == null) {
            umlClassInterfaces = new ArrayList<UmlClassInterface>();
        }
        umlClassInterfaces.add(umlClassInterface);
    }
    
    public void addClassSuper(UmlClassSuper umlClassSuper) {
        if(umlClassSupers == null) {
            umlClassSupers = new ArrayList<UmlClassSuper>();
        }
        umlClassSupers.add(umlClassSuper);
    }
    
    public void addField(UmlField umlField) {
        if(umlFields == null) {
            umlFields = new ArrayList<UmlField>();
        }
        umlFields.add(umlField);
    }
    
    public void addMethod(UmlMethod umlMethod) {
        if(umlMethods == null) {
            umlMethods = new ArrayList<UmlMethod>();
        }
        umlMethods.add(umlMethod);
    }
    
    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("UmlClassDesc [umlClass=");
        stringBuffer.append(umlClass).append(", \n");
        stringBuffer.append("umlClassImports=\n");
        stringBuffer.append("[");
        if (umlClassImports != null && umlClassImports.size() > 0) {
            for (UmlClassImport umlClassImport : umlClassImports) {
                stringBuffer.append(umlClassImport).append("\n");
            }
        }
        stringBuffer.append("]\n");
        
        stringBuffer.append("umlClassInterfaces=\n");
        stringBuffer.append("[");
        if (umlClassInterfaces != null && umlClassInterfaces.size() > 0) {
            for (UmlClassInterface umlClassInterface : umlClassInterfaces) {
                stringBuffer.append(umlClassInterface).append("\n");
            }
        }
        stringBuffer.append("]\n");
        
        stringBuffer.append("umlClassSupers=\n");
        stringBuffer.append("[");
        if (!(umlClassSupers == null || umlClassSupers.size() == 0)) {
            for (UmlClassSuper umlClassSuper : umlClassSupers) {
                stringBuffer.append(umlClassSuper).append("\n");
            }
        }
        stringBuffer.append("]\n");
        
        stringBuffer.append("umlFields=");
        stringBuffer.append("[");
        if (!(umlFields == null || umlFields.size() == 0)) {
            for (UmlField umlField : umlFields) {
                stringBuffer.append(umlField).append("\n");
            }
        }
        stringBuffer.append("]\n");
        
        stringBuffer.append("umlMethods=\n");
        stringBuffer.append("[");
        if (!(umlMethods == null || umlMethods.size() == 0)) {
            for (UmlMethod umlMethod : umlMethods) {
                stringBuffer.append(umlMethod).append("\n");
            }
        }
        stringBuffer.append("]\n");
        
        return stringBuffer.toString();
    }
    
    
}
