package com.github.lzhuii.ricebot.util;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

/**
 * @author hui
 * @since 2024-10-03
 */
public class QQCommandUtil {

    public static String[] resolveMessage(String message) {
        String input = message.replace("\\u003c", "<").replace("\\u003e", ">");
        String regex = "(?<=<@!).*?(?=>)|(?<=/)\\s*.*|\\S+";
        Pattern pattern = Pattern.compile(regex);
        String[] output = pattern.matcher(input).results()
                .map(MatchResult::group)
                .toArray(String[]::new);
        String[] result = new String[]{"", "", ""};
        if (output.length == 1) {
            result[2] = output[0];
        } else if (output.length == 2) {
            result[1] = output[0];
            result[2] = output[1];
        } else if (output.length == 3) {
            result[0] = output[0];
            result[1] = output[1];
            result[2] = output[2];
        }
        return result;
    }

}
