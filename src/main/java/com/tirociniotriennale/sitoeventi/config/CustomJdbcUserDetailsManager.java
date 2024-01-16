/*package com.tirociniotriennale.sitoeventi.config;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class CustomJdbcUserDetailsManager extends JdbcUserDetailsManager {

    public CustomJdbcUserDetailsManager(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected List<UserDetails> loadUsersByUsername(String username) {
        String customQuery = "select username, password, enabled from utente where username = ?";
        return getJdbcTemplate().query(customQuery, new CustomUserRowMapper(), username);
    }

    public class CustomUserRowMapper implements RowMapper<UserDetails> {
        @Override
        public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            String userName = rs.getString("username");
            String password = rs.getString("password");
            boolean enabled = true;
            boolean accLocked = false;
            boolean accExpired = false;
            boolean credsExpired = false;

            return new User(userName, password, enabled, !accExpired, !credsExpired, !accLocked,
                    AuthorityUtils.NO_AUTHORITIES);
        }
    }

}
*/
