package com.fitch.mb.rest.dao;

import com.fitch.mb.rest.dto.ApiResponse;
import com.fitch.mb.rest.dto.CRCard;
import com.fitch.mb.rest.dto.CRCards;
import com.fitch.mb.rest.util.OracleConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hliu on 2016/7/6.
 */
@Component
public class MBOracleDaoImpl implements MBOracleDao {

    @Autowired
    OracleConfiguration oracleConfiguration;



}
