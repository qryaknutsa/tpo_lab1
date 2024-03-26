package a.tpo_lab1.task1;

import static java.lang.Math.sin;

import a.tpo_lab1.task1.Sin;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class SinTests {
    double eps = 0.001;

    @ParameterizedTest
    @ValueSource(doubles = {-2 * Math.PI, -Math.PI, -0.5 * Math.PI})
    public void testNegX(double x) {
        assertEquals(sin(x), Sin.sinTailor(x, 100), eps);
    }


    @ParameterizedTest
    @ValueSource(doubles = {0.5 * Math.PI, Math.PI, 1.5 * Math.PI, 2 * Math.PI})
    public void testPosX(double x) {
        assertEquals(sin(x), Sin.sinTailor(x, 100), eps);
    }


    @ParameterizedTest
    @ValueSource(doubles = {0.001, 0.01, 0.1, 0})
    public void testCloToZeroX(double x) {
        assertEquals(sin(x), Sin.sinTailor(x, 100), eps);
    }
}

