package com.softuni.mobilele.services.init;

import com.softuni.mobilele.services.role.UserRoleService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataBaseInitServiceImpl implements DataBaseInitService {


    @Override
    public void dbInit() {
    }

    @Override
    public boolean isDbInit() {
        return true;
    }
}