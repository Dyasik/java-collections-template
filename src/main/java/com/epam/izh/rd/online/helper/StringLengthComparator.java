package com.epam.izh.rd.online.helper;

import com.epam.izh.rd.online.helper.Direction;

import java.util.Comparator;

import static com.epam.izh.rd.online.helper.Direction.*;

public class StringLengthComparator implements Comparator<String> {

    private Direction direction;

    public StringLengthComparator(Direction direction) {
        this.direction = direction;
    }

    @Override
    public int compare(String s1, String s2) {
        int length1 = s1.length();
        int length2 = s2.length();

        if (length1 > length2) {
            return direction == ASC ? 1 : -1;
        }

        if (length1 < length2) {
            return direction == ASC ? -1 : 1;
        }

        return 0;
    }

}
