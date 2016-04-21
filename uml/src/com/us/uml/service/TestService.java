/* 
 * Copyright (c) 2016, S.F. Express Inc. All rights reserved.
 */

package com.us.uml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.us.uml.dao.UmlMethodArgDao;
import com.us.uml.entity.UmlMethodArg;

/**
 * 描述：
 * 
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2016年4月20日      Steven.Zhu         Create
 * ****************************************************************************
 * </pre>
 * @author Steven.Zhu
 * @since 
 */
@Service
public class TestService {

    @Autowired
    private UmlMethodArgDao umlMethodArgDao;
    
    public void save() {
        UmlMethodArg umlMethodArg = new UmlMethodArg();
        umlMethodArg.setMid(1);
        umlMethodArg.setTypeFullName("fdasfda");
        umlMethodArg.setArgName("fdasfdsa");
        umlMethodArg.setTypeName("fdfds");
        
        umlMethodArgDao.save(umlMethodArg);
    }
}
