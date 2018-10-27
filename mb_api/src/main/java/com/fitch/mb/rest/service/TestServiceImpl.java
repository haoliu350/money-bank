package com.fitch.mb.rest.service;

import com.fitch.mb.rest.dto.ApiResponse;
import com.fitch.mb.rest.dto.TestResponse;
import com.fitch.mb.rest.exception.IdNotFoundCustomException;
import com.fitch.mb.rest.util.OracleConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by hliu on 2016/7/5.
 */

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    OracleConfiguration oracleConfiguration;

    public ApiResponse testServiceException(int id, String name, String desc) {
        if(id == 0 ){
            throw new IdNotFoundCustomException("User Id is 0, not able to apply.");
        } else{
            TestResponse t =  new TestResponse();
            t.setId(id);
            t.setName(name);
            t.setDescription(desc);
            return t;
        }
    }

    @Override
    public String getSysdate() {

        String result = "";
        try {
            Connection conn = oracleConfiguration.dataSource().getConnection();

            String sql ="select sysdate from dual";
            Statement stm = conn.createStatement();
            ResultSet resultSet = stm.executeQuery(sql);
            while (resultSet.next()){
                result = resultSet.getString("SYSDATE");
            }
            stm.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
