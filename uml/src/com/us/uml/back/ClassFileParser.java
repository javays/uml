/* 
 * Copyright (c) 2016, S.F. Express Inc. All rights reserved.
 */

package com.us.uml.back;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.us.uml.entity.UmlClass;
import com.us.uml.entity.UmlClassImport;
import com.us.uml.entity.UmlField;
import com.us.uml.entity.UmlMethod;
import com.us.uml.vo.ClassDesc;
import com.us.uml.vo.UmlClassDesc;


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

public class ClassFileParser {
    
    public final static String PREFIX_PKG = "package ";
    public final static String PREFIX_IMPORT = "import ";
    
    public final static String REG_CLS = "^(public|private|protected)?(.*)?\\s+(class|interface)\\s+(\\w+)"
            + "(\\s+extends\\s+\\w+(,\\s*\\w+)*)?"
            + "(\\s+implements\\s+\\w+(,\\s*\\w+)*)?\\s*(\\{)?\\s*$";
    public final static String REG_ATTRS = "^(public|private|protected)?(.*)?(\\s+\\w+)(\\s+\\w+\\s*)(=)?.*;$";
    public final static String REG_METHODS = "^(public|private|protected)?(.*?)[\\w\\d_\\[\\]]+"
            + "\\s*\\(([\\w\\d_\\[\\]]+\\s+[\\w\\d_\\[\\]]+)?(\\s*,\\s*[\\w\\d_\\[\\]]+\\s+[\\w\\d_\\[\\]]+)*\\).*([\\{|;])?\\s*$";
    
    public final static Pattern PATTERN_CLS = Pattern.compile(REG_CLS);
    public final static Pattern PATTERN_ATTRS = Pattern.compile(REG_ATTRS);
    
    public static UmlClassDesc parse(String filePath) {
        BufferedReader br = null;
        try {
            File file = new File(filePath);
            br = new BufferedReader(new FileReader(file));
            
            boolean staticBlock = false;
            boolean multiComment = false;
            boolean multiLinePckStart = false;
            boolean multiLineImportStart = false;
            boolean multiLineClassStart = false;
            boolean multiLineCode = false;
            boolean classBodyStart = false;
            boolean methodBodyStart = false;
            int leftBrace = 0;
            
            UmlClassDesc umlClassDesc = new UmlClassDesc();
            UmlClass umlClass = new UmlClass();
            umlClassDesc.setUmlClass(umlClass);
            
            String line = null;
            StringBuffer multiLineBuffer = null;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if ("".equals(line)) {
                    continue;
                }
                
                if (multiComment) {
                    if (line.endsWith("*/")) {
                        multiComment = false;
                    } 
                    continue;  
                } 
                
                if (line.startsWith("/*")) {
                    if (!line.endsWith("*/")) {
                        multiComment = true;
                    }
                    continue;
                }
                
                if (line.startsWith("//")) {
                    continue;
                }
                
                if (line.startsWith(PREFIX_PKG)) {
                    if (line.endsWith(";")) {
                        umlClass.setPkgName(line.substring(PREFIX_PKG.length(), line.length()-1).trim());
                    } else {
                        multiLinePckStart = true;
                        multiLineBuffer = new StringBuffer();
                        multiLineBuffer.append(line);
                    }
                    
                    continue;
                } 
                
                if (multiLinePckStart) {
                    multiLineBuffer.append(" ").append(line);
                    if (line.endsWith(";")) {
                        umlClass.setPkgName(line.substring(PREFIX_PKG.length(), line.length()-1).trim());
                        multiLinePckStart = false;
                    }
                    continue;
                }
                
                if (line.startsWith(PREFIX_IMPORT)) {
                    if (line.endsWith(";")) {
                        umlClassDesc.addClassImport(getClassImport(line));
                    } else {
                        multiLineImportStart = true;
                        multiLineBuffer = new StringBuffer();
                        multiLineBuffer.append(line);
                    }
                    
                    continue;
                }
                
                if (multiLineImportStart) {
                    multiLineBuffer.append(" ").append(line);
                    if (line.endsWith(";")) {
                        umlClassDesc.addClassImport(getClassImport(line));
                        multiLineImportStart = false;
                    }
                    continue;
                }
                
                if (multiLineClassStart) {
                    multiLineBuffer.append(" ").append(line);
                    if (line.endsWith("{")) {
                        fillUmlClass(multiLineBuffer.toString(), umlClass);
                        multiLineClassStart = false;
                        classBodyStart = true;
                    }
                    continue;
                }
                
                if (!line.startsWith(PREFIX_IMPORT) && !classBodyStart) {
                    if (!line.endsWith("{")) {
                        multiLineClassStart = true;
                        
                        multiLineBuffer = new StringBuffer();
                        multiLineBuffer.append(line);
                    } else {
                        fillUmlClass(line, umlClass);
                        classBodyStart = true;
                    }
                    continue;
                }
                
                if (classBodyStart) {
                    if(methodBodyStart || staticBlock) {
                        leftBrace += leftBraceNum(line);
                        if (leftBrace == 0) {
                            methodBodyStart = false;
                            staticBlock = false;
                        }
                        
                        continue;
                    }
                    
                    if (!multiLineCode && !(line.endsWith("{") || line.endsWith(";"))) {
                        multiLineBuffer = new StringBuffer();
                        multiLineBuffer.append(line);
                        multiLineCode = true;
                        continue;
                    } 
                    
                    if (multiLineCode) {
                        multiLineBuffer.append(" ").append(line);
                        if (line.endsWith("{") || line.endsWith(";")) {
                            multiLineCode = false;
                            line = multiLineBuffer.toString();
                        } else {
                            continue;
                        }
                    }
                    
                    if(isStaticBlock(line)) {
                        staticBlock = true;
                        leftBrace = 1;
                    } else if (isFieldDeclare(line)) {
                        Matcher m = PATTERN_ATTRS.matcher(line);
                        m.find();
                        
                        UmlField umlField = new UmlField();
                        umlField.setName(line.trim());
                        umlClassDesc.addField(umlField);
                    } else if (line.matches(REG_METHODS)) {
                        String tmp = line.trim();
                        
                        if (!tmp.endsWith(";")) {
                            methodBodyStart = true;
                            leftBrace = 1;
                        }
                        
                        tmp = tmp.substring(0, tmp.length()-1);
                        
                        UmlMethod umlMethod = new UmlMethod();
                        umlMethod.setName(tmp);
                        umlClassDesc.addMethod(umlMethod);
                    } else {
                        throw new RuntimeException("not supported, line=" + line);
                    }
                    
                }
                
            }
            
            return umlClassDesc;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return null;
    }
    
    private static UmlClassImport getClassImport(String line) {
        UmlClassImport umlClassImport = new UmlClassImport();
        umlClassImport.setClsImport(line.substring(PREFIX_IMPORT.length(), line.length()-1).trim());
        return umlClassImport;
    }
    
    private static void fillUmlClass(String line, UmlClass umlClass) {
        if(umlClass == null) {
            umlClass = new UmlClass();
        }
        
        Matcher m = PATTERN_CLS.matcher(line);
        if (m.find()) {
            umlClass.setAccessFlag(m.group(1));
            String otherModifiers = m.group(2);
            if (otherModifiers != null && !(otherModifiers = otherModifiers.trim()).equals("")) {
                umlClass.setOtherModifier(otherModifiers.trim());
            }
            
            umlClass.setName(m.group(4));
            
            String _extends = m.group(5);
            if (_extends != null) {
                _extends = _extends.replace("extends", "");
                umlClass.setSuperClass(_extends.trim());
            }
            
            String _impl = m.group(7);
            if (_impl != null) {
                _impl = _impl.replace("implements", "").trim();
                umlClass.setInterfaces(_impl);
            }
        }
    }
    
    private static boolean isStaticBlock(String line) {
        String[] strs = line.split("\\s+", 2);
        return strs.length == 2 && "static".equals(strs[0]) && "{".equals(strs[1]);
    }
    
    private static boolean isFieldDeclare(String line) {
        if (line.contains("=")) {
            return true;
        } else {
            return !line.contains("(");
        }
    }
    
    private static int leftBraceNum(String line) {
        if (line == null || line.equals("")) {
            return 0;
        }
        
        int leftBracket = 0;
        
        int singleQuote = 0;
        int doubleQuote = 0;
        boolean preIsBackslash = false;
        boolean preIsSlash = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"' && !preIsBackslash && singleQuote == 0) {
                if (doubleQuote > 0) {
                    doubleQuote --;
                } else {
                    doubleQuote ++;
                }
            } else if (c == '\'' && !preIsBackslash && doubleQuote == 0) {
                if (singleQuote > 0) {
                    singleQuote --;
                } else {
                    singleQuote ++;
                }
            } else {
                if (singleQuote > 0 || doubleQuote > 0) {
                    if (c == '\\' && !preIsBackslash) {
                        preIsBackslash = true;
                    } else {
                        if (preIsBackslash) {
                            preIsBackslash = false;
                        }
                    }
                } else if (c == '/') {
                    if (preIsSlash) {
                        preIsSlash = false;
                        break;
                    } else {
                        preIsSlash = true;
                    }
                } else if(c == '{'){
                    leftBracket ++;
                } else if(c == '}'){
                    leftBracket --;
                }
            }
        }
        
        return leftBracket;
    }
    
    /**
     * {
     * 
     * }
     */
    public static void main(String[] args) throws IOException {
        UmlClassDesc classDesc = parse("E:/develop/workspace/spring/src/org/springframework/context/support/AbstractApplicationContext.java");
        System.out.println(classDesc);
        String str = "public Object getBean(String name, Object[] args) throws BeansException {";
        
        String line = "public static Field field = new Field()";
        String line2 = "public void add() throws Exception;";
        
        String pattern = "^public.*?(?!=).*?";
        System.out.println(line.matches(pattern));
        System.out.println(line2.matches(pattern));
        
        
        
        
       /* Matcher m = PATTERN_CLS.matcher(line);
        if (m.find()) {
            
            classDesc.setVisibility();
            
            String otherModifiers = m.group(2);
            if (otherModifiers != null && !(otherModifiers = otherModifiers.trim()).equals("")) {
                classDesc.setOtherModifiers(Arrays.asList(otherModifiers.split("//s+")));
            }
            
            classDesc.setName(m.group(3));
            
            String _extends = m.group(4);
            if (_extends != null) {
                _extends = _extends.replace("extends", "");
                List<String> supers = Arrays.asList(_extends.trim().split("/\s*,\\s*"));
                classDesc.setExtendCls(supers);
            }
            
            String _impl = m.group(6);
            if (_impl != null) {
                _impl = _impl.replace("implements", "").trim();
                classDesc.setImplInterface(_impl);
            }
        }*/
        
        /*Matcher m = PATTERN_CLS.matcher(line2);
        if (m.find()) {
            
            System.out.println(m.group(1));
            System.out.println(m.group(2));
            System.out.println(m.group(3));
            System.out.println(m.group(4));
            System.out.println(m.group(5));
            System.out.println(m.group(6));
            System.out.println(m.group(7));
        }*/
    }
}
