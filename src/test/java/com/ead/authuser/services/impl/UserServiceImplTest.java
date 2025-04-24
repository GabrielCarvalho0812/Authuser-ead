package com.ead.authuser.services.impl;

import com.ead.authuser.exceptions.NotFoundException;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.repositories.UserRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    UserRepository userRepository;


    @Test
    void deveRetornarUmaListafindAll() {
        //ARRANGE (preparação)
        UserModel userModel = new UserModel();
        userModel.setUserId(UUID.randomUUID());
        userModel.setUsername("username");

        List<UserModel> userModelList = new ArrayList<>();
        userModelList.add(userModel);

        when(userRepository.findAll()).thenReturn(userModelList);

        //ACT (ação)
        // EXECUÇÃO: chama o metodo que esta sendo testatdo (findAll() no service)
        List<UserModel> userModelList1 = userServiceImpl.findAll();


        //ASSERT (afirmação)
        // VERIFICAÇÃO: Aqui verificamos se o resultado do metodo é o esperado
        assertEquals(userModelList, userModelList1);
        assertEquals(userModelList.size(), userModelList1.size());
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testFindById_UserExists() {
        // Inicializando as variáveis diretamente dentro do teste
        UUID userId = UUID.randomUUID();
        UserModel user = new UserModel();
        user.setUserId(userId);
        user.setUsername("Gabriel");


        when(userRepository.findById(userId)).thenReturn(Optional.of(user));


        Optional<UserModel> result = userServiceImpl.findById(userId);

        // Verificando se o retorno é o esperado
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    public void testFindById_UserNotFound() {
        // Inicializando as variáveis diretamente dentro do teste
        UUID userId = UUID.randomUUID();

        // Configurando o mock para retornar um Optional vazio (usuário não encontrado)
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Verificando se a exceção NotFoundException é lançada
        NotFoundException thrown;
        thrown = assertThrows(NotFoundException.class, () -> {
            userServiceImpl.findById(userId);
        });

        // Verificando a mensagem da exceção
        assertEquals("Error: User not found", thrown.getMessage());
    }


}