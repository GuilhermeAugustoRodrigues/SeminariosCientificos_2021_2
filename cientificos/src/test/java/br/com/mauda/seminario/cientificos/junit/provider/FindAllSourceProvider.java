package br.com.mauda.seminario.cientificos.junit.provider;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;
import org.junit.platform.commons.util.Preconditions;

import br.com.mauda.seminario.cientificos.bc.PatternCrudBC;

public class FindAllSourceProvider implements ArgumentsProvider, AnnotationConsumer<FindAllSource> {

    protected PatternCrudBC<?, ?, ?> bc;

    @Override
    public void accept(FindAllSource source) {
        try {
            // Trecho que invoca o metodo static getInstance da classe BC correspondente usando reflection
            this.bc = (PatternCrudBC<?, ?, ?>) source.value().getMethod("getInstance").invoke(null);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            Preconditions.notNull(this.bc, "Ocorreram problemas ao obter o BC");
        }
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        Collection<?> objs = this.bc.findAll();
        Preconditions.notNull(objs, "O retorno do metodo findAll nao pode ser nulo");
        Preconditions.notEmpty(objs, "O retorno do metodo findAll deve conter algum elemento");
        return objs.stream().map(Arguments::of);
    }
}
