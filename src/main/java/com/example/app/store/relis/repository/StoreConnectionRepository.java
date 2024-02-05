package com.example.app.store.relis.repository;

import com.example.app.store.relis.model.GameStore;
import jakarta.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class StoreConnectionRepository {
    Connection con;

    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC&allowPublicKeyRetrieval=true","root","root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Transactional
    public void deleteStore(Long id) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("delete from store where (?)");
        preparedStatement.setLong(1, id);
    }

    @Transactional
    public void save (String name, int price, String genre) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO store (name, price, genre) VALUES (?, ?, ?)");
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, price);
        preparedStatement.setString(3, genre);
    }

    public List<GameStore> getListGameStore() throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM store");
        ResultSet res =  preparedStatement.getResultSet();
        List<GameStore> result = new ArrayList<>();
        while (res.next()) {
            GameStore gameStore = new GameStore();
            gameStore.setName(res.getString(1));
            gameStore.setPrice(res.getInt(2));
            gameStore.setGenre(res.getString(3));
            result.add(gameStore);
        }
        return result;
    }
}
