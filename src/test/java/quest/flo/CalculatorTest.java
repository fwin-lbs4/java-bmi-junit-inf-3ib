package quest.flo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {


    static Stream<Map<String, Float[]>> hashMapProvider() {
        return Stream.of(
                Map.of("You are critically underweight", new Float[]{0F, 15F, 16F}),
                Map.of("You are underweight", new Float[]{16.1F, 19F, 19.9F}),
                Map.of("You are normal weight", new Float[]{20F, 24F, 24.9F}),
                Map.of("You are overweight", new Float[]{25F, 29F, 29.9F}),
                Map.of("You are obese (class 1)", new Float[]{30F, 34F, 34.9F}),
                Map.of("You are obese (class 2)", new Float[]{35F, 39F, 39.9F}),
                Map.of("You are obese (class 3)", new Float[]{40F, 45.5F, 50.9F, 100F})
        );
    }

    @Test
    @DisplayName("Get the BMI as a Float!")
    void getBmi() throws Exception {
        assertEquals(new Calculator(70F, 1.78F).getBmi(), (70F / (1.78F * 1.78F)), "Can calculate BMI");
    }

    @Test
    @DisplayName("Print a String judging the users BMI!")
    void judgeUser() {

    }

    @ParameterizedTest
    @MethodSource("hashMapProvider")
    void judgeDifferentUsers(Map<String, Float[]> argument) {
        String str;
        Float[] numbers = new Float[3];

        for (String key :
                argument.keySet()) {
            str = key;
            numbers = argument.get(key);

            break;
        }
    }
}