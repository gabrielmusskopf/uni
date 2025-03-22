package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.dominio.Estoque;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.estoque.EstoqueRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AbastecerEstoqueComandoTest {

    @InjectMocks
    private AbastecerEstoqueComando testado;

    @Mock
    private EstoqueRepositorio estoqueRepositorio;

    @Test
    void deve_abastecer_estoque(){
       var estoque = new Estoque();
        when(estoqueRepositorio.salvar(estoque)).thenReturn(estoque);

        testado.abastecer(estoque, "pao", "ovo");

        var ingredientes = estoque.getIngredientes();
        assertEquals(Set.of("pao", "ovo"), ingredientes.keySet());
        assertEquals(1, ingredientes.get("pao"));
        assertEquals(1, ingredientes.get("ovo"));
    }

}
