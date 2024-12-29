package com.ead.authuser.services.impl;

import com.ead.authuser.models.UserModel;
import com.ead.authuser.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    UserRepository userRepository;


    @Test
    void findAll() {
        //SETUP: Prepara o ambiente de teste
        UserModel userModel = new UserModel();
        userModel.setUserId(UUID.randomUUID());
        userModel.setUsername("username");

        List<UserModel> userModelList = new ArrayList<>();
        userModelList.add(userModel);

        when(userRepository.findAll()).thenReturn(userModelList);

        // EXECUÇÃO: chama o metodo que esta sendo testatdo (findAll() no service)
        List<UserModel> userModelList1 = userServiceImpl.findAll();


        // VERIFICAÇÃO: Aqui verificamos se o resultado do metodo é o esperado
        Assertions.assertEquals(userModelList, userModelList1);
        Assertions.assertEquals(userModelList.size(), userModelList1.size());
        Mockito.
                verify(userRepository, Mockito.times(1)).findAll();
        Mockito.
                verifyNoMoreInteractions(userRepository);
    }

    @Test
    void deveRetornarFindById(){


        UUID userId = UUID.randomUUID();
        UserModel userModel = new UserModel();
        userModel.setUserId(userId);

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(userModel));

        userServiceImpl.findById(userId);

        Mockito.
                verify(userRepository, Mockito.times(1)).findById(userId);
        Mockito.
                verify(userRepository, Mockito.times(1)).findById(userId);
        Mockito.
                verifyNoMoreInteractions(userRepository);

    }
}