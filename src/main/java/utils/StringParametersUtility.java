package utils;

import java.security.InvalidParameterException;
import java.util.Arrays;

public class StringParametersUtility implements Utility<String> {

    @Override
    public void isValid(String[] params) {
        if (params.length % 2 == 0 || params.length < 3) {
            throw new InvalidParameterException("Incorrect count of parameters. There shouldn’t be an even count of parameters.");
        } else if (isHasSameParameters(params)) {
            throw new InvalidParameterException("There are two or more same parameters.");
        }
    }

    private boolean isHasSameParameters(String[] params) {
        return Arrays.stream(params).map(String::toLowerCase).distinct().count() != params.length;
    }
}
