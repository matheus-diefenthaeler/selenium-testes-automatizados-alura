package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LeiloesTest {

    private LeiloesPage leiloesPage;

    @AfterEach
    public void afterEach() {
        this.leiloesPage.fechar();
    }

    @Test
    public void deveriaCadastrarLeilao() {
        LoginPage loginPage = new LoginPage();
        loginPage.preencheFormularioDeLogin("fulano", "pass");
        this.leiloesPage = loginPage.efetuaLogin();
        CadastroLeilaoPage paginaDeCadastro = leiloesPage.preencherFormulario();

        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilao do dia " + hoje;
        String valor = "500.00";

        this.leiloesPage = paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);

        Assertions.assertTrue(leiloesPage.isLeilaoCadastrado(nome, valor, hoje));
    }
}

