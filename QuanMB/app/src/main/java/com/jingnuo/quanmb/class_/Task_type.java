package com.jingnuo.quanmb.class_;

/**
 * Created by Administrator on 2018/4/4.
 */

public  class Task_type {
    public static   String  task_type(int type){
        switch (type){
            case 101:
                return "同城帮";
            case 102:
                return "维 修";
            case 103:
                return "家 政";
            case 104:
                return "互联网";
            case 105:
                return "设 计";
            case 106:
                return "运 输";
            case 107:
                return "代 购";
            case 108:
                return "商 务";
            case 109:
                return "其 他";
        }
        return "其 他";
    }
}
