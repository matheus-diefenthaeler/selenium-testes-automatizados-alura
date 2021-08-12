package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LeiloesTest {

    private LeiloesPage leiloesPage;
    private CadastroLeilaoPage paginaDeCadastro;

    @BeforeEach
    public void beforeEach(){
        LoginPage loginPage = new LoginPage();
        loginPage.preencheFormularioDeLogin("fulano", "pass");
        this.leiloesPage = loginPage.efetuaLogin();
        this.paginaDeCadastro = leiloesPage.preencherFormulario();
    }

    @AfterEach
    public void afterEach() {
        this.leiloesPage.fechar();
    }

    @Test
    public void deveriaCadastrarLeilao() {
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilao do dia " + hoje;
        String valor = "500.00";

        this.leiloesPage = paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);

        Assertions.assertTrue(leiloesPage.isLeilaoCadastrado(nome, valor, hoje));
    }

    @Test
    public void deveriaValidarCadastroDeLeilao() {
        this.leiloesPage = paginaDeCadastro.cadastrarLeilao("", "", "");
        Assertions.assertFalse(this.paginaDeCadastro.isPaginaAtual());
        Assertions.assertTrue(this.leiloesPage.isPaginaAtual());
        Assertions.assertTrue(this.paginaDeCadastro.isMensagensDeValidacaoVisiveis());
    }

}

