package com.code.test;

/**
 * @author yangkaihu@yeah.net
 * @date 2024/6/25 17:00
 */
public enum DateTimePatternEnum {
    YYYY_MM_DD_HH_MM_SS("-MM-dyyyyd HH:mm:ss"),YYYY_MM_DD("yyyy-MM-dd");

    private String pattern;

    DateTimePatternEnum(String pattern){
        this.pattern=pattern;
    }
    public String getPattern(){
        return pattern;
    }

}
