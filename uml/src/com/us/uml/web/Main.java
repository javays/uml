/* 
 * Copyright (c) 2016, S.F. Express Inc. All rights reserved.
 */

package com.us.uml.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 描述：
 * 
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2016年4月19日      Steven.Zhu         Create
 * ****************************************************************************
 * </pre>
 * @author Steven.Zhu
 * @since 
 */

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("E:/xx");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        int p_count = 0;
        
        int v = 0;
        
        StringBuffer col_1 = new StringBuffer();
        StringBuffer col_2 = new StringBuffer();
        while ((line = br.readLine()) != null) {
            if (line.startsWith("P")) {    // 285
                p_count ++;
//                System.out.println(line);   
            } else {
                String[] strs = line.split("\\s+", 2);
                col_1.append("'" + strs[0] + "', ");
                col_2.append("'" + strs[1] + "', ");
                
                System.out.printf("insert into tt_new_old_dept values('%s', '%s');\n", strs[0], strs[1]);
                
                /*if (!strs[0].equals("E"+strs[1])) {
                    System.out.println(strs[0] + " not equal " + strs[1]);
                }*/
                
                v++;
            }
        }
        System.out.println(p_count);
        System.out.println("-----------------------------------------");
        
        System.out.println(v);
        
//        System.out.println(col_1.toString());
//        
//        System.out.println("-----------------------------------------");
//        
//        System.out.println(col_2.toString());
        
        br.close();
    }
}
