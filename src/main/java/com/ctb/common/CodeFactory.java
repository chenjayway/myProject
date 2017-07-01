package com.ctb.common;

import org.apache.log4j.Logger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by lms on 2017/6/23.
 */
public class CodeFactory {
    private Logger logger = Logger.getLogger(CodeFactory.class);
    private static CodeFactory instance;

    private CodeFactory (){}

    public synchronized String getCode() {
        return code();
    }

    private static class SingleHandler {
        private static CodeFactory instance = new CodeFactory();
    }

    public static CodeFactory getInstance() {
        return SingleHandler.instance;
    }

    public String code() {
        String[] beforeShuffle = new String[] {"0","1", "2", "3", "4", "5", "6", "7",
                "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z" };
        List<String> list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(5, 9);
        logger.debug("生成验证码成功：" + result);
        return result;
    }
}
