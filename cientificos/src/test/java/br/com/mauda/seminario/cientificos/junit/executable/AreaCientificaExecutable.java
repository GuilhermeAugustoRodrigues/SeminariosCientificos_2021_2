package br.com.mauda.seminario.cientificos.junit.executable;

import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertEquals;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertIsNotBlank;
import static br.com.mauda.seminario.cientificos.junit.util.AssertionsMauda.assertNotNull;

import org.junit.jupiter.api.function.Executable;

import br.com.mauda.seminario.cientificos.junit.massa.MassaAreaCientifica;
import br.com.mauda.seminario.cientificos.model.AreaCientifica;

public class AreaCientificaExecutable implements Executable {

    private AreaCientifica areaCientifica;
    private MassaAreaCientifica areaCientificaEnum;

    public AreaCientificaExecutable(AreaCientifica areaCientifica) {
        this.areaCientifica = areaCientifica;
    }

    public AreaCientificaExecutable(AreaCientifica areaCientifica, MassaAreaCientifica enumm) {
        this(areaCientifica);
        this.areaCientificaEnum = enumm;
    }

    public void basicVerification(AreaCientifica areaCientifica) throws Throwable {
        assertNotNull(areaCientifica, "Uma Area Cientifica nao pode ser nula");
        assertNotNull(areaCientifica.getCursos(), "É necessário inicializar a lista de cursos");
        assertIsNotBlank(areaCientifica.getNome(), "O nome de uma Area Cientifica nao pode ser nulo ou em branco");
    }

    @Override
    public void execute() throws Throwable {
        this.basicVerification(this.areaCientifica);

        if (this.areaCientificaEnum != null) {
            assertEquals(this.areaCientificaEnum.getNome(), this.areaCientifica.getNome(), "Nomes das areas cientificas nao sao iguais");
            return;
        }
    }
}
