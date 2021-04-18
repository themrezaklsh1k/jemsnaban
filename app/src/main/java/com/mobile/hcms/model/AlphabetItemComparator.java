package com.mobile.hcms.model;

import com.viethoa.models.AlphabetItem;

import java.util.Comparator;

/**
 * Created by jemsnaban on 1/12/17.
 */

public class AlphabetItemComparator implements Comparator<AlphabetItem> {
    @Override
    public int compare(AlphabetItem a1, AlphabetItem a2) {

        if (a1.word.compareTo(a2.word) > 0){
            return  1;
        } else if (a1.word.compareTo(a2.word) < 0){
            return -1;
        }

        return 0;
    }
}
