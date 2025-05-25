package org.example.common.console.movieInput;

@FunctionalInterface
public interface ThrowingSupplier<T> {
    T get() throws Exception;
}
