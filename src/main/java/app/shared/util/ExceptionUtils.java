package app.shared.util;

import java.util.function.Supplier;

/**
 * 하위 메서드의 예외와 관계없이 상위 메서드에서 지정한 예외로 핸들링하기 위한 유틸리티 클래스
 */
public class ExceptionUtils {

    public static <R> R execute(Supplier<R> supplier, RuntimeException exception) {
        try {
            return supplier.get();
        } catch (Exception e) {
            throw exception;
        }
    }

    public static void execute(Runnable runnable, RuntimeException exception) {
        try {
            runnable.run();
        } catch (Exception e) {
            throw exception;
        }
    }
}
