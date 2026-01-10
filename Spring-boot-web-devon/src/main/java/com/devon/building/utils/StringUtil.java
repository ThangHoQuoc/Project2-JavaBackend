package com.devon.building.utils;

import java.util.List;

public  class StringUtil {

    public static <T> boolean CheckNull(T  value) {
        if(value != null ) {
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean CheckList(List<String> value) {
        if(value != null && !value.isEmpty() ) {
            return true;
        }
        else {
            return false;
        }
    }

        public static boolean hasText(String s) {
            return s != null && !s.trim().isEmpty();
        }


}
