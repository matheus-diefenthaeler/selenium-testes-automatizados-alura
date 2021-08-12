package br.com.alura.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

    private LoginPage loginPage;

    @BeforeEach
    public void beforeEach() {
        this.loginPage = new LoginPage();
    }

    @AfterEach
    public void afterEach() {
        this.loginPage.fechar();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        loginPage.preencheFormularioDeLogin("fulano", "pass");
        loginPage.efetuaLogin();

        Assertions.assertFalse(loginPage.isPaginaDeLogin());
        Assertions.assertEquals("fulano", loginPage.getNomeUsuarioLogado());

    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos() {
        loginPage.preencheFormularioDeLogin("invalido", "123");
        loginPage.efetuaLogin();

        Assertions.assertTrue(loginPage.isPaginaDeLoginComErro());
        Assertions.assertNull(loginPage.getNomeUsuarioLogado());
        Assertions.assertTrue(loginPage.contemTexto("Usuário e senha inválidos."));
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        loginPage.navegaParaPaginaDeLances();

        Assertions.assertTrue(loginPage.isPaginaDeLogin());
        Assertions.assertFalse(loginPage.contemTexto("Dados do Leilão"));
    }
}
