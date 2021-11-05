package br.com.mauda.seminario.cientificos.junit.provider;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.params.provider.ArgumentsSource;

import br.com.mauda.seminario.cientificos.bc.PatternCrudBC;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ArgumentsSource(FindAllSourceProvider.class)
public @interface FindAllSource {

    /**
     * The names of the test class methods to use as sources for arguments; must not be empty.
     */
    Class<? extends PatternCrudBC<?, ?, ?>> value();

}
