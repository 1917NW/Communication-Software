package com.lxy.imapp.util;


import javafx.geometry.Bounds;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AutoSizeTool {

    private static final double MAX_LABEL_WIDTH = 450; // 最大宽度值
    public static double getWidth(String msg) {
        double textWidth = getTextWidth(msg);
        return Math.min(textWidth, MAX_LABEL_WIDTH);

    }

    public static double getTextWidth(String msg){
        Text text = new Text(msg);
        text.setFont(Font.font(16));

        // 获取文本的宽度
        Bounds textBounds = text.getBoundsInLocal();
        double textWidth = textBounds.getWidth() + 20;

        System.out.println("textWidth:" + textWidth);
        return textWidth;
    }

    public static double getHeight(String msg) {
        double textWidth = getTextWidth(msg);

        double remainder = textWidth % 450;
        int line = (int) (textWidth / 450);

        if (remainder != 0) {
            line = line + 1;
        }
        System.out.println("line:" + line);
        double autoHeight = line * 24 + 10;

        return autoHeight < 30 ? 30 : autoHeight;

    }

    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }
}
