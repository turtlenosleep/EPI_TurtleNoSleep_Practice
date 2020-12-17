package epi.strings;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.Arrays;

public class ReverseWords {

    public static void reverseWords(char[] input) {
        StringBuilder builder = new StringBuilder();
        StringBuilder word = new StringBuilder();
        for (int i = input.length - 1; i >= 0; i--) {
            char ch = input[i];
            if (ch == ' ') {
                if (word.length() > 0) {
                    word.reverse();
                    builder.append(word);
                    word = new StringBuilder();
                }
                builder.append(ch);
            } else {
                word.append(ch);
            }
        }

        if(word.length()>0){
            word.reverse();
            builder.append(word);
        }

        for(int i=0;i<builder.length();i++){
            input[i] = builder.charAt(i);
        }

        return;
    }

    @EpiTest(testDataFile = "reverse_words.tsv")
    public static String reverseWordsWrapper(TimedExecutor executor, String s)
            throws Exception {
        char[] sCopy = s.toCharArray();

        executor.run(() -> reverseWords(sCopy));

        return String.valueOf(sCopy);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
