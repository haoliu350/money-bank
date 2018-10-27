package com.fitch.mb.rest.dao;

import com.fitch.mb.rest.dto.ApiResponse;
import com.fitch.mb.rest.dto.CRCard;
import com.fitch.mb.rest.dto.CRCards;
import com.fitch.mb.rest.util.OracleConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

/**
 * Created by hliu on 2016/7/6.
 */
@Component
public class CardsOracleDaoImpl implements CardsOracleDao {

    private final String INSERT_CARD = "{call insert_one_cr_card(?,?,?,?)}";
    private final String DELETE_CARD = "{call delete_card_by_id(?)}";
    private final String UPDATE_CARD = "{call update_card_by_id(?,?,?,?,?)}";

    @Autowired
    OracleConfiguration oracleConfiguration;

    @Override
    public ApiResponse getAllCards() {
        CRCards result =  new CRCards();
        List<CRCard> crCardList =  new ArrayList<CRCard>();
        try {
            Connection conn = oracleConfiguration.dataSource().getConnection();
            //String sql ="SELECT 1 FROM DUAL";
            String sql ="select * from CLASHROYAL_CARDS";
            Statement stm = conn.createStatement();
            ResultSet resultSet = stm.executeQuery(sql);
            while(resultSet.next()){
                CRCard card = new CRCard();
                card.setId(resultSet.getInt("CARD_ID"));
                card.setName(resultSet.getString("CARD_NAME"));
                card.setCost(resultSet.getInt("CARD_ELIXIR_COST"));
                card.setRarity(resultSet.getString("CARD_RARITY"));
                card.setType(resultSet.getString("CARD_TYPE"));
                crCardList.add(card);
                //System.out.println(card);
            }
            stm.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result.setCrCardList(crCardList);
        return result;
    }

    @Override
    public ApiResponse getCardById(int id) {
        Connection conn = null;
        Statement stm = null;
        String sql ="select * from CLASHROYAL_CARDS where CARD_ID=" + id;
        CRCards result =  new CRCards();
        List<CRCard> crCardList =  new ArrayList<CRCard>();

        try {
            conn = oracleConfiguration.dataSource().getConnection();
            stm = conn.createStatement();
            ResultSet resultSet = stm.executeQuery(sql);
            while(resultSet.next()){
                CRCard card = new CRCard();
                card.setId(resultSet.getInt("CARD_ID"));
                card.setName(resultSet.getString("CARD_NAME"));
                card.setCost(resultSet.getInt("CARD_ELIXIR_COST"));
                card.setRarity(resultSet.getString("CARD_RARITY"));
                card.setType(resultSet.getString("CARD_TYPE"));
                crCardList.add(card);
                //System.out.println(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(stm != null){
                try {
                    stm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        result.setCrCardList(crCardList);
        return result;
    }

    @Override
    public ApiResponse getCardByName(String name) {
        Connection conn = null;
        Statement stm = null;
        String sql ="select * from CLASHROYAL_CARDS where CARD_NAME='" + name + "'";
        CRCards result =  new CRCards();
        List<CRCard> crCardList =  new ArrayList<CRCard>();

        try {
            conn = oracleConfiguration.dataSource().getConnection();
            stm = conn.createStatement();
            ResultSet resultSet = stm.executeQuery(sql);
            while(resultSet.next()){
                CRCard card = new CRCard();
                card.setId(resultSet.getInt("CARD_ID"));
                card.setName(resultSet.getString("CARD_NAME"));
                card.setCost(resultSet.getInt("CARD_ELIXIR_COST"));
                card.setRarity(resultSet.getString("CARD_RARITY"));
                card.setType(resultSet.getString("CARD_TYPE"));
                crCardList.add(card);
                //System.out.println(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(stm != null){
                try {
                    stm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        result.setCrCardList(crCardList);
        return result;
    }

    @Override
    public boolean insertCard(String name, int cost, String rarity, String type) {
        Connection conn = null;
        CallableStatement callableStatement = null;

        try {
            conn = oracleConfiguration.dataSource().getConnection();
            callableStatement = conn.prepareCall(INSERT_CARD);
            callableStatement.setString(1, name);
            callableStatement.setInt(2, cost);
            callableStatement.setString(3, rarity);
            callableStatement.setString(4, type);

            callableStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if(callableStatement != null){
                try {
                    callableStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean deleteCardById(int id) {
        Connection conn = null;
        CallableStatement callableStatement = null;

        try {
            conn = oracleConfiguration.dataSource().getConnection();
            callableStatement = conn.prepareCall(DELETE_CARD);
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if(callableStatement != null){
                try {
                    callableStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Integer> getIds() {
        Connection conn = null;
        Statement stm = null;
        List<Integer> idList = new ArrayList<Integer>();
        try {
            conn = oracleConfiguration.dataSource().getConnection();
            String sql ="select CARD_ID from CLASHROYAL_CARDS";
             stm = conn.createStatement();
            ResultSet resultSet = stm.executeQuery(sql);
            while(resultSet.next()){
                idList.add(resultSet.getInt("CARD_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(null != stm){
                try {
                    stm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null != conn){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return idList;
    }

    @Override
    public boolean updateCard(CRCard c) {
        Connection conn = null;
        CallableStatement callableStatement = null;

        try {
            conn = oracleConfiguration.dataSource().getConnection();
            callableStatement = conn.prepareCall(UPDATE_CARD);
            callableStatement.setInt(1, c.getId());
            callableStatement.setString(2, c.getName());
            callableStatement.setInt(3, c.getCost());
            callableStatement.setString(4, c.getRarity());
            callableStatement.setString(5, c.getType());

            callableStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if(callableStatement != null){
                try {
                    callableStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
