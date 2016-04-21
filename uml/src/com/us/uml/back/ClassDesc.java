/* 
 * Copyright (c) 2016, S.F. Express Inc. All rights reserved.
 */

package com.us.uml.back;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 描述：
 * 
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2016年1月18日      Steven.Zhu         Create
 * ****************************************************************************
 * </pre>
 * @author Steven.Zhu
 * @since 
 */

public class ClassDesc {

    private String pkg;
    private Set<String> imports;
    private String comment;
    
    private String visibility;
    private List<String> otherModifiers;
    private String name;
    private List<String> extendCls;
    private String implInterface;
    
    private List<String> attrs;
    private List<String> methods;
    
    public String getPkg() {
        return pkg;
    }
    public void setPkg(String pkg) {
        this.pkg = pkg;
    }
    public Set<String> getImports() {
        return imports;
    }
    public void appendImport(String aImport) {
        if (imports == null) {
            imports = new LinkedHashSet<String>();
        }
        imports.add(aImport);
    }
    public String getComment() {
        return comment;
    }
    public void appendComment(String comment) {
        if (this.comment == null) {
            this.comment = "";
        }
        this.comment += comment;
    }
    public String getVisibility() {
        return visibility;
    }
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
    public List<String> getOtherModifiers() {
        return otherModifiers;
    }
    public void setOtherModifiers(List<String> otherModifiers) {
        this.otherModifiers = otherModifiers;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getExtendCls() {
        return extendCls;
    }
    public void setExtendCls(List<String> extendCls) {
        this.extendCls = extendCls;
    }
    public String getImplInterface() {
        return implInterface;
    }
    public void setImplInterface(String implInterface) {
        this.implInterface = implInterface;
    }
    public List<String> getAttrs() {
        return attrs;
    }
    
    public void addAttrs(String attr) {
        if (attrs == null) {
            attrs = new ArrayList<String>();
        }
        attrs.add(attr);
    }
    
    public List<String> getMethods() {
        return methods;
    }
    
    public void addMethod(String method) {
        if (methods == null) {
            methods = new ArrayList<String>();
        }
        methods.add(method);
    }
    @Override
    public String toString() {
        String s_attrs = null;
        if (attrs != null) {
            s_attrs = Arrays.toString(attrs.toArray());
        }
        return "ClassDesc [pkg=" + pkg
                + "\n imports="
                + (imports != null && imports.size() > 0 ? Arrays.toString(imports.toArray()) : "[]")
                + "\n comment="
                + comment
                + "\n visibility="
                + visibility
                + "\n otherModifiers="
                + otherModifiers
                + "\n name="
                + name
                + "\n extendCls="
                + extendCls
                + "\n implInterface="
                + implInterface
                + "\n attrs="
                + s_attrs
                + "\n methods="
                + methods
                + "]";
    }
}
