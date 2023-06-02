package com.food.service;

import com.food.service.model.Kitchen;
import com.food.service.repository.KitchenRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class KitchenTest {

    @Autowired
    private KitchenRepository kitchenRepository;

    @BeforeEach
    public void antesDeCadaMetodo() {
        System.out.println("Início");
    }

    @AfterEach
    public void depoisDeCadaMetodo() {
        System.out.println("Fim");
    }

    @BeforeAll
    public static void antesDeTodos() {
        System.out.println("Antes de todos uma única vez");
    }

    @AfterAll
    public static void depoisDeTodos() {
        System.out.println("Depois de todos uma única vez");
    }

    @Test
        // Teste para persistencia de Kitchen (Cozinha)
    void testPersistKitchen() {
        // --> Cenário
        Kitchen kitchen = new Kitchen();
        kitchen.setName("Boliviana");

        // --> Ação
        kitchenRepository.save(kitchen);

        // --> Validação
        assertNotNull(kitchen);
        assertNotNull(kitchen.getId());
    }

    @Test // Validando se exceção é lançada ao setar dado nullo
    public void testExceptionKitchen() {
        Kitchen kitchen = new Kitchen();
        kitchen.setName(null);

        try {
            kitchenRepository.save(kitchen);
            Assertions.fail("Deveria dar erro");
        } catch (Exception e) {
            assertThat(e.getClass()).isEqualTo(ConstraintViolationException.class);
        }
    }

    @Test // Validando se exceção é lançada ao tentar excluir Kitchen em uso
    public void testExcludeKitchenInUse() {

        try {
            kitchenRepository.deleteById(1L);
            Assertions.fail("Deveria dar erro");
        } catch (Exception e) {
            assertThat(e.getClass()).isEqualTo(DataIntegrityViolationException.class);
        }
    }

    @Test // Validando se exceção é lançada ao tentar excluir Kitchen inexistente
    public void testExcludeKitchenNonExistent() {

        assertThrows(EmptyResultDataAccessException.class,
                () -> kitchenRepository.deleteById(111L));
    }
}
