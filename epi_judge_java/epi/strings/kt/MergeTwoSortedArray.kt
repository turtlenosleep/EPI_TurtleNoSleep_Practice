package epi.strings.kt

import epi.test_framework.EpiTest
import epi.test_framework.GenericTest
import epi.test_framework.TimedExecutor
import java.util.*

object MergeTwoSortedArray {
    @JvmStatic
    fun mergeTwoSortedArray(a: CharArray?,m:Int,b:CharArray?,n:Int): CharArray? {
        if(a == null) return null;
        if(b == null) return null;
        var lastIndex = m+n-2;
        var indexA = m-1;
        var indexB = n-1;

        while(indexA>=0 && indexB>=0){
            if(a[indexA] > b[indexB]){
                a[indexA--] = a[lastIndex--];
            }else{
                b[indexB--] = a[lastIndex--];
            }

        }

        return a;
    }
}