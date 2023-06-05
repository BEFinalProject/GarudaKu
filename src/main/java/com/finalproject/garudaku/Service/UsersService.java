package com.finalproject.garudaku.Service;

import com.finalproject.garudaku.Model.UsersEntity;
import com.finalproject.garudaku.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService {
    @Autowired
    private UsersRepository R;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<UsersEntity> getAll(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return R.findAll(pageable);
    }
    public UsersEntity getById(UUID id_user){
        return R.findById(id_user).get();
    }

    public UsersEntity updateUser(UsersEntity param) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        UsersEntity userExist =  R.findById(param.getUid_user()).get();
        userExist.setUsername(param.getUsername());
        userExist.setEmail(param.getEmail());
        userExist.setPassword(param.getPassword());
        userExist.setGender(param.getGender());
        userExist.setBirth_date(param.getBirth_date());
        userExist.setFull_name(param.getFull_name());
        userExist.setPhoto_profile(param.getPhoto_profile());
        userExist.setTelephone(param.getTelephone());
        userExist.setStatus_active(param.getStatus_active());
        userExist.setModifiedAt(currentDateTime);
        return R.save(userExist);
    }

    public UsersEntity addUsers(UsersEntity param) {
        Optional<UsersEntity> userExist = R.findById(param.getUid_user());
        if (userExist.isPresent()) {
            throw new RuntimeException("User ID " + param.getUid_user() + " Sudah Ada");
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        param.setUid_user(generateUUID());
        param.setCreatedAt(currentDateTime);
        param.setPassword(passwordEncoder.encode(param.getPassword()));

        return R.save(param);

    }

//    public List<UsersEntity> addMultipleUsers(List<UsersEntity> param) {
//        List<UsersEntity> list = new ArrayList<>();
//
//        for(UsersEntity user : param){
//            Optional<UsersEntity> userExsist = R.findById(user.getUid_user());
//            if(userExsist.isPresent()){
//                throw new RuntimeException("User ID " +user.getUid_user() + " Sudah Ada");
//            }
//            else{
//                user.setPassword(passwordEncoder.encode(user.getPassword()));
//                list.add(R.save(user));
//            }
//        }
//        return list;
//    }


    public UsersEntity delUser(UUID param){
        UsersEntity delete = R.findById(param).get();
        R.deleteById(param);
        return delete;
    }

    private UUID generateUUID() {
        return UUID.randomUUID();
    }
}
