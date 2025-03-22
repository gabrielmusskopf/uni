package br.com.gabrielgmusskopf.unisinos.comando;

import br.com.gabrielgmusskopf.unisinos.dominio.execao.UsuarioException;
import br.com.gabrielgmusskopf.unisinos.infra.repositorio.cliente.UsuarioRepositorio;
import br.com.gabrielgmusskopf.unisinos.util.Mocked;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastrarUsuarioComandoTest {

    @InjectMocks
    private CadastrarUsuarioComando testado;

    @Mock
    private UsuarioRepositorio usuarioRepositorio;

    @Test
    void nao_deve_cadastrar_quando_ja_existe(){
        var nome = "Edu";
        when(usuarioRepositorio.buscarPorNome(nome)).thenReturn(Optional.of(Mocked.usuario()));

       var err =  assertThrows(UsuarioException.class, () -> testado.cadastrar("Edu"));

       assertEquals("Nome já cadastrado", err.getMessage());
    }

    @Test
    void deve_cadastrar_quando_ja_existe(){
        var nome = "Edu";
        when(usuarioRepositorio.buscarPorNome(nome)).thenReturn(Optional.empty());

        var usuario = testado.cadastrar("Edu");

        assertEquals(nome, usuario.getNome());
        verify(usuarioRepositorio).salvar(usuario);
    }

}
