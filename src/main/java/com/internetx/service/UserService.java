package com.internetx.service;

import com.internetx.domain.User;
import com.internetx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        String sqlInsertUser = "INSERT INTO user (login, password, fname, lname, email) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlInsertUser, user.getLogin(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail());

        String sqlSelectUserId = "SELECT LAST_INSERT_ID()";
        int userId = jdbcTemplate.queryForObject(sqlSelectUserId, Integer.class);

        String sqlInsertRole = "INSERT INTO role (user_id, role_admin, role_develop, role_cctld, role_gtld, role_billing, role_registry, role_purchase_read, role_purchase_write, role_sale_write, role_sql) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlInsertRole, userId, user.getRole().isAdmin() ,
                user.getRole().isDevelop() , user.getRole().isCctld() ,
                user.getRole().isGtld() , user.getRole().isBilling() ,
                user.getRole().isRegistry(), user.getRole().isCanReadPurchases(),
                user.getRole().isCanWritePurchases() , user.getRole().isCanWriteSales() ,
                user.getRole().isCanExecuteSql() );
        return;
    }

    @Override
    public void update(User user) {
        String SQL = "UPDATE user SET login = ?, password = ?, fname = ?, lname = ?, email = ?  WHERE id = ?";
        jdbcTemplate.update(SQL, new Object[]{user.getLogin(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getId()});
        return;
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM user WHERE id=?", id);
    }

    @Override
    public User findById(int id) {
        try {
            User user = jdbcTemplate.queryForObject("SELECT * FROM user WHERE id=?", BeanPropertyRowMapper.newInstance(User.class), id);
            return user;
        }
        catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            User user = jdbcTemplate.queryForObject("SELECT * FROM user WHERE email=?", BeanPropertyRowMapper.newInstance(User.class), email);
            return user;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }


}
