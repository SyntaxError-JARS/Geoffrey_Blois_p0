package com.revature.bank_appilcation.util;

import com.revature.bank_application.daos.UserAccountDao;
import com.revature.bank_application.models.UserAccountData;
import com.revature.bank_application.services.UserAccountServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserAccountTests {

    UserAccountServices sut;
    UserAccountDao mockUserDao;


    @BeforeEach
    public void testPrep(){
        mockUserDao = mock(UserAccountDao.class);
        sut = new UserAccountServices(mockUserDao);
    }

    @Test
    public void test_create_givenValidUser(){
        UserAccountData userAccountData = new UserAccountData("pie","pie","pie","pie","pie");

        when(mockUserDao.create(userAccountData)).thenReturn(userAccountData);

        boolean actualUser = sut.registerAccount(userAccountData);

    }



}


