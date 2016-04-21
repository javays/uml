/* 
 * Copyright (c) 2016, S.F. Express Inc. All rights reserved.
 */

package com.us.uml.back;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;


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
    
    
    
    public final static String REG_CLS = "^(public|private|protected)?(.*)?\\s+(class|interface)\\s+(\\w+)(\\s+extends\\s+\\w+(,\\s*\\w+)*)?(\\s+implements\\s+\\w+)?\\s*(\\{)?\\s*$";
    public final static String REG_ATTRS = "^(public|private|protected)?(.*)?(\\s+\\w+)(\\s+\\w+\\s*)=.*$";
    public final static String REG_METHODS = "^(public|private|protected)?(.*?)\\w+\\s*\\((\\w+\\s+\\w+)?(\\s*,\\s*\\w+\\s+\\w+)*\\).*([\\{|;])?\\s*$";
    
    public final static Pattern PATTERN_CLS = Pattern.compile(REG_CLS);
    public final static Pattern PATTERN_ATTRS = Pattern.compile(REG_ATTRS);
    
    public static ClassDesc parse(String filePath) {
        BufferedReader br = null;
        try {
            File file = new File(filePath);
            br = new BufferedReader(new FileReader(file));
            
            boolean comment = false;
            boolean clsBodyStart = false;
            boolean methodStart = false;
            int leftBrace = 0;
            
            ClassDesc classDesc = new ClassDesc();
            String line = null;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                
                if (comment) {
                    if (line.endsWith("*/")) {
                        comment = false;
                    } 
                    continue;  
                } 
                
                if (line.startsWith("/*")) {
                    if (!line.endsWith("*/")) {
                        comment = true;
                    }
                    continue;
                }
                
                if (line.startsWith("//")) {
                    continue;
                }
                
                if (line.startsWith(REG_PKG)) {
                    classDesc.setPkg(line);
                } else if (line.startsWith(REG_IMPORT)) {
                    classDesc.appendImport(line);
                } else if (!methodStart && line.matches(REG_ATTRS)) {
                    Matcher m = PATTERN_ATTRS.matcher(line);
                    m.find();
//                    String[] attr = new String[1];
//                    attr[0] = line;
//                    attr[1] = m.group(3);
                    classDesc.addAttrs(line);
                } else if (clsBodyStart && line.matches(REG_METHODS) && !methodStart) {
                    methodStart = true;
                    
                    String tmp = line.trim();
                    if (tmp.endsWith("{")) {
                        tmp = tmp.substring(0, tmp.length()-1);
                    }
                    tmp += ";";
                    classDesc.addMethod(tmp);
                } else {
                    if (! clsBodyStart) {
                        Matcher m = PATTERN_CLS.matcher(line);
                        if (m.find()) {
                            clsBodyStart = true;
                            
                            classDesc.setVisibility(m.group(1));
                            
                            String otherModifiers = m.group(2);
                            if (otherModifiers != null && !(otherModifiers = otherModifiers.trim()).equals("")) {
                                classDesc.setOtherModifiers(Arrays.asList(otherModifiers.split("\\s+")));
                            }
                            
                            classDesc.setName(m.group(4));
                            
                            String _extends = m.group(5);
                            if (_extends != null) {
                                _extends = _extends.replace("extends", "");
                                List<String> supers = Arrays.asList(_extends.trim().split("\\s*,\\s*"));
                                classDesc.setExtendCls(supers);
                            }
                            
                            String _impl = m.group(7);
                            if (_impl != null) {
                                _impl = _impl.replace("implements", "").trim();
                                classDesc.setImplInterface(_impl);
                            }
                        }
                    }
                }
                
                if (methodStart) {
                    leftBrace += leftBraceNum(line);
                    
                    if (leftBrace == 0) {
                        methodStart = false;
                    }
                }
            }
            
            return classDesc;
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
        ClassDesc classDesc = parse("E:/develop/workspace/spring/src/org/springframework/context/ApplicationContext.java");
        System.out.println(classDesc);
        
        String line = "public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {";
        String line2 = "public interface ClassFileParser extends xfdsa {";
        
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
