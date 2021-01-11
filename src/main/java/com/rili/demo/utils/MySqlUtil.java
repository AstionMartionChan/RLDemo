package com.rili.demo.utils;

import com.rili.demo.entity.CustomerEntity;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Leo_Chan on 2018/2/2.
 */
public class MySqlUtil {
    /* 数据库连接 */
    private static Connection connection;
    private static Properties prop;
    /**
     * 连接数据库
     */
    static {
        try {
            prop = new Properties();
            InputStream resourceAsStream = MySqlUtil.class.getResourceAsStream("/systemConfig.properties");
            prop.load(resourceAsStream);
            connection = getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String url = prop.getProperty("jdbc.url").trim();
        String username = prop.getProperty("jdbc.username").trim();
        String password = prop.getProperty("jdbc.password").trim();
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public static void insertRLCustomer(CustomerEntity entity) throws SQLException {
        String sql = "insert into tb_rili_customer (`customer_id`,`customer_name`,`customer_type`" +
                ",`customer_org_name`,`whole_machine_id`,`jy_machine_name`,`jy_customer_code`," +
                "`jy_machine_code`,`jy_customer_name`,`service_level`,`mine_level`,`business_level`" +
                ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, entity.getCustomerId());
        preparedStatement.setString(2, entity.getCustomerName());
        preparedStatement.setInt(3, entity.getCustomerType());
        preparedStatement.setString(4, entity.getCustomerOrgName());
        preparedStatement.setString(5, entity.getWholeMachineId());
        preparedStatement.setString(6, entity.getJyMachineName());
        preparedStatement.setString(7, entity.getJyCustomerCode());
        preparedStatement.setString(8, entity.getJyMachineCode());
        preparedStatement.setString(9, entity.getJyCustomerName());
        preparedStatement.setInt(10, entity.getServiceLevel());
        preparedStatement.setInt(11, entity.getMineLevel());
        preparedStatement.setInt(12, entity.getBusinessLevel());

        preparedStatement.execute();
        preparedStatement.close();
    }

    public static void insert(Map<String, Object> params) throws SQLException {
        String sql = "insert into t_sku_info (title,image_url,price,specifications) values(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, params.get("title").toString());
        preparedStatement.setString(2, params.get("imgUrl").toString());
        preparedStatement.setString(3, params.get("price").toString());
        preparedStatement.setString(4, params.get("commodityParams").toString());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public static void insertCodeMatcher(Map<String, Object> params) throws SQLException {
        String sql = "insert into tb_app_spider_context (platform_type,search_type,field_type,app_name,other_context) values(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, params.get("platform_type").toString());
        preparedStatement.setString(2, params.get("search_type").toString());
        preparedStatement.setString(3, params.get("field_type") != null ? params.get("field_type").toString() : "");
        preparedStatement.setString(4, params.get("app_name").toString());
        preparedStatement.setString(5, params.get("other_context") != null ? params.get("other_context").toString() : "");
        preparedStatement.execute();
        preparedStatement.close();
    }

    public static void insertBl(Map<String, Object> params) throws SQLException {
        String sql = "insert into t_bl_sku_info (sku_id,sku_name,sku_price,sku_img_url,catalog_name) values(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, params.get("sku_id").toString());
        preparedStatement.setString(2, params.get("sku_name").toString());
        preparedStatement.setString(3, params.get("sku_price").toString());
        preparedStatement.setString(4, params.get("sku_img_url").toString());
        preparedStatement.setString(5, params.get("catalog_name").toString());
        preparedStatement.execute();
        preparedStatement.close();
    }


    public static void close() {
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
