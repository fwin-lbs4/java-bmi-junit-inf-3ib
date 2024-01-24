package quest.flo;

import java.util.Scanner;

public class Calculator {
    private Float mass;
    private Float height;
    private Float bmi;

    /**
     * Ask for mass and height from user.
     *
     * @throws Exception thrown when input is erroneous.
     */
    public Calculator() throws Exception {
        try {
            mass = ask("mass");

            height = ask("height");
        } catch (Exception e) {
            String exceptionMessage = "Error setting values! Message: ".concat(e.getMessage());
            throw new Exception(exceptionMessage);
        }
    }

    /**
     * Accept mass and height of user as parameters.
     *
     * @param externalMass   the mass of the user
     * @param externalHeight the height of the user
     * @throws Exception thrown when the values passed to constructor can't be handled
     */
    public Calculator(Float externalMass, Float externalHeight) throws Exception {
        try {
            mass = validate(externalMass, "Mass");
            height = validate(externalHeight, "Height");
        } catch (Exception e) {
            String exceptionMessage = "Erroneous values passed to constructor! Message: ".concat(e.getMessage());
            throw new Exception(exceptionMessage);
        }
    }

    /**
     * Ask the user to input a value.
     *
     * @param type the value we are asking for
     * @return the value we determined by asking the user
     * @throws Exception if we are asking for an invalid value or validation fails
     */
    private Float ask(String type) throws Exception {
        try (Scanner in = new Scanner(System.in)) {
            switch (type) {
                case "mass" -> System.out.print("How much do you weigh in kg?");
                case "height" -> System.out.print("How tall are you in m?");
                default -> throw new Exception("Not asking for a valid type!");
            }
            Float value;
            value = in.nextFloat();

            return switch (type) {
                case "mass" -> validate(value, "mass");
                case "height" -> validate(value, "height");
                default -> throw new Exception("Not asking for a valid type!");
            };
        } catch (Exception e) {
            throw new Exception("Error setting values! Message: ".concat(e.getMessage()));
        }
    }

    /**
     * Calculate the bmi using the mass and height of the user.
     *
     * @throws Exception if mass and/or height are null and determining those fails or if the calculation fails
     */
    private void calculate() throws Exception {
        try {
            if (mass == null) {
                mass = ask("mass");
            }

            if (height == null) {
                height = ask("height");
            }

            bmi = mass / (height * height);
        } catch (Exception e) {
            throw new Exception("Error calculating bmi: ".concat(e.getMessage()));
        }
    }

    /**
     * Validate the mass or height, throwing a customized error message if needed.
     *
     * @param value the value to be validated.
     * @param name  the name of the value passed in, used to customize error message
     * @return the mass or height if it is valid
     * @throws Exception if the mass or height is less or equal to 0
     */
    private Float validate(Float value, String name) throws Exception {
        if (value <= 0) {
            throw new Exception(name.concat(" can't be lower or equal to 0!"));
        }

        return value;
    }

    /**
     * Get the bmi value.
     *
     * @return the bmi
     * @throws Exception if bmi is null and calculation fails
     */
    public Float getBmi() throws Exception {
        // first check if bmi is set and try to calculate if not
        if (bmi == null) {
            try {
                calculate();
            } catch (Exception e) {
                throw new Exception("Failed calculating BMI: " + e.getMessage());
            }
        }

        return bmi;
    }

    /**
     * Judge the users bmi.
     *
     * @throws Exception when bmi is not set and the calculation fails.
     */
    public String judgeUser() throws Exception {
        // first check if bmi is set and try to calculate if not
        if (bmi == null) {
            try {
                calculate();
            } catch (Exception e) {
                throw new Exception("Failed judging User: " + e.getMessage());
            }
        }

        // Here we use early returns to stop execution if the correct "case" has matched
        if (bmi <= 16F) {
            return printJudgement("You are critically underweight");
        }

        if (bmi <= 19.9F) {
            return printJudgement("You are underweight");
        }

        if (bmi <= 24.9F) {
            return printJudgement("You are normal weight");
        }

        if (bmi <= 29.9F) {
            return printJudgement("You are overweight");
        }

        if (bmi <= 34.9F) {
            return printJudgement("You are obese (class 1)");
        }

        if (bmi <= 39.9F) {
            return printJudgement("You are obese (class 2)");
        }

        return printJudgement("You are obese (class 3)");
    }

    /**
     * Helper function for printing the judgement message
     *
     * @param message message to print before bmi
     */
    private String printJudgement(String message) {
        return message + ": " + bmi.toString() + "\n";
    }
}
