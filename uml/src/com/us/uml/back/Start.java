/* 
 * Copyright (c) 2016, S.F. Express Inc. All rights reserved.
 */

package com.us.uml.back;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.us.uml.dao.UmlMethodArgDao;
import com.us.uml.dao.impl.UmlMethodArgDaoImpl;
import com.us.uml.entity.UmlMethodArg;
import com.us.uml.service.TestService;

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

public class Start {

    public void run() throws IOException {
//        FileSystemXmlApplicationContext fsxa = new FileSystemXmlApplicationContext("../beans.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:beans.xml");
//        UmlMethodArgDao umlMethodArgDao = (UmlMethodArgDao) applicationContext.getBean(UmlMethodArgDaoImpl.class);
        TestService testService = applicationContext.getBean(TestService.class);
        testService.save();
        System.out.println();
    }
    
    public static void main(String[] args) throws IOException {
        new Start().run();
    }
}
