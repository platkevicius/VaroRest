package com.varorest.varorest.border.services;

import com.varorest.varorest.border.model.Border;
import com.varorest.varorest.border.repositories.BorderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class BorderServiceTest {

    private BorderRepository borderRepository;
    private BorderService borderService;

    @Before
    public void setUp() {
        borderRepository = Mockito.mock(BorderRepository.class);
        borderService = new BorderService(borderRepository);
    }

    @Test
    public void getBorderById() {
       // given
        Border expected = Border.builder().build();

       // when
        Mockito.when(borderRepository.findById(1L)).thenReturn(Optional.of(expected));

       // then
        assertEquals(expected, borderService.getBorderById(1L)
                                            .orElseThrow(() -> new EntityNotFoundException("")));
    }

}