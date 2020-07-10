package com.example.splitwize.splitwize.repo;

import com.example.splitwize.splitwize.data.UserRegiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@PropertySource(value = {"classpath:query.properties"})
public class UserRepoImpl implements UserRepo{
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${save.user.details}")
    public String userDataSave;


    @Override
    public int addUserDetails(UserRegiData userRegiData, String token, String id, String hashResult) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        mapSqlParameterSource.addValue("name", userRegiData.getName());
        mapSqlParameterSource.addValue("email", userRegiData.getEmail());
        mapSqlParameterSource.addValue("phoneNumber", userRegiData.getPhoneNumber());
        mapSqlParameterSource.addValue("password", hashResult);
        mapSqlParameterSource.addValue("token", token);
        int rowCount = jdbcTemplate.update(this.userDataSave, mapSqlParameterSource);
        return rowCount;
    }
}
