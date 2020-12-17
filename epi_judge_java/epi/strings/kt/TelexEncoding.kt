package epi.strings.kt

import epi.test_framework.EpiTest
import epi.test_framework.GenericTest
import epi.test_framework.TimedExecutor
import java.util.*

object TelexEncoding {
    @JvmStatic
    fun TelexEncoding(size: Int, c: CharArray?): CharArray? {
        if (c == null || c.isEmpty()) return null;

        var lastIndex = c.size - 1
        for (i in size-1 downTo 0) {
            val ch = c[i];
            if (ch == '.') {
                c[lastIndex--] = 'T';
                c[lastIndex--] = 'O';
                c[lastIndex--] = 'D';

            } else if (ch == ',') {
                c[lastIndex--] = 'A';
                c[lastIndex--] = 'M';
                c[lastIndex--] = 'M';
                c[lastIndex--] = 'O';
                c[lastIndex--] = 'C';

            } else if (ch == '?') {
                c[lastIndex--] = 'K';
                c[lastIndex--] = 'R';
                c[lastIndex--] = 'A';
                c[lastIndex--] = 'M';
                c[lastIndex--] = ' ';
                c[lastIndex--] = 'N';
                c[lastIndex--] = 'O';
                c[lastIndex--] = 'I';
                c[lastIndex--] = 'T';
                c[lastIndex--] = 'S';
                c[lastIndex--] = 'E';
                c[lastIndex--] = 'U';
                c[lastIndex--] = 'Q';

            } else if (ch == '!') {
                c[lastIndex--] = 'K';
                c[lastIndex--] = 'R';
                c[lastIndex--] = 'A';
                c[lastIndex--] = 'M';
                c[lastIndex--] = ' ';
                c[lastIndex--] = 'N';
                c[lastIndex--] = 'O';
                c[lastIndex--] = 'I';
                c[lastIndex--] = 'T';
                c[lastIndex--] = 'A';
                c[lastIndex--] = 'M';
                c[lastIndex--] = 'A';
                c[lastIndex--] = 'L';
                c[lastIndex--] = 'C';
                c[lastIndex--] = 'X';
                c[lastIndex--] = 'E';

            } else {
                c[lastIndex--] = ch;
            }
        }

        return c;
    }

}