package it.firegloves.dao.impl;


import it.firegloves.domain.MempoiColumn;
import it.firegloves.exception.MempoiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBMempoiDAO implements it.firegloves.dao.MempoiDAO {

    private static final Logger logger = LoggerFactory.getLogger(DBMempoiDAO.class);

    private static DBMempoiDAO instance = new DBMempoiDAO();

    private DBMempoiDAO() {
    }

    public static DBMempoiDAO getInstance() {
        return instance;
    }


    @Override
    public ResultSet executeExportQuery(PreparedStatement prepStmt) {
        try {
            logger.debug("EXECUTING EXPORT QUERY: " + prepStmt.toString());
            return prepStmt.executeQuery();
        } catch (SQLException e) {
            throw new MempoiException(e);
        }
    }



    @Override
    public List<MempoiColumn> readMetadata(ResultSet rs) {

        if (null == rs) {
            throw new MempoiException("NULL ResultSet!");
        }

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            List<MempoiColumn> columnList = new ArrayList<>();

            int columnsNumber = rsmd.getColumnCount();
            for (int i = 1; i <= columnsNumber; i++) {
                columnList.add(new MempoiColumn(rsmd.getColumnType(i), rsmd.getColumnLabel(i)));
            }

            return columnList;

        } catch (SQLException e) {
            throw new MempoiException(e);
        }
    }

}