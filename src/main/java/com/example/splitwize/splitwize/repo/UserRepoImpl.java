package com.example.splitwize.splitwize.repo;


import com.example.splitwize.splitwize.data.UserRegiData;
import com.example.splitwize.splitwize.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@PropertySource(value = {"classpath:query.properties"})
public class UserRepoImpl implements UserRepo {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private UserDataRepository userDataRepository;

    @Override
    public UserRegiData addUserDetails(UserRegiData userRegiData) {
      /*  List<UserRegiData> list=new ArrayList<UserRegiData>();

        list= userDataRepository.findAll();
        return list;*/
        return userDataRepository.save(userRegiData);
    }

    @Override
    public Optional<UserRegiData> getUserDetails(int id) {

       return userDataRepository.findById(id);


    }


}
