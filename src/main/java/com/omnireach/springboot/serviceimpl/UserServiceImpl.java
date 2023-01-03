package com.omnireach.springboot.serviceimpl;

import com.omnireach.springboot.customexception.EmployeeNotFoundException;
import com.omnireach.springboot.entity.UserEntity;
import com.omnireach.springboot.repository.UserRepository;
import com.omnireach.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;
    @Override
    public UserEntity validateAndSave(UserEntity userEntity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UserEntity>> validate = validator.validate(userEntity);
        if(validate.size()>0){
            System.err.println("UserData are Invalid");
        }
        else{
            System.out.println("UserData is Valid");
        }
        return this.repository.save(userEntity);
    }

    @Override
    public List<UserEntity> validateAndFindAll() {
        System.out.println("Running validateAndFindAll Method in Serviceimpl");
        return repository.findAll();
    }

    @Override
    public UserEntity get(Integer id) throws EmployeeNotFoundException {
        Optional<UserEntity> byId = repository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        throw new EmployeeNotFoundException("Could not find employee with Id"+id);
    }

    @Override
    public void delete(Integer id) {
        Long countById = repository.countById(id);
        if(countById==null || countById==0){
            throw new EmployeeNotFoundException("Could not find employee with Id"+id);
        }
        repository.deleteById(id);
    }
}
