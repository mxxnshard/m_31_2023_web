package web.service.converter;

public interface Converter<T, R> {
    R convert(T t);
}
