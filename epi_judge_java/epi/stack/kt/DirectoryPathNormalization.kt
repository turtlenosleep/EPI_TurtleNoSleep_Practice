package epi.stack.kt

import epi.test_framework.EpiTest
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest
import java.lang.StringBuilder
import java.util.*

object DirectoryPathNormalization {
    @JvmStatic
    @EpiTest(testDataFile = "directory_path_normalization.tsv")
    fun shortestEquivalentPath(path: String?): String {
        if(path.isNullOrEmpty()) return "";

        val pathes = path.split("/");
        val pathQueue = LinkedList<String>();
        val isStartWithRoot = path.startsWith("/");
/*
./tc/awk/././,
isStartWithRoot = flase;
[ . , tc , awk , . , . ]

        .  tc    awk	        .  .
pathQueue  [] [tc]  ,[awk,tc ]

“tc/awk”
tc/awk

*/
        for(route in pathes){
            when(route){
                    ".","" -> continue;
                    ".." ->{
                if(pathQueue.isEmpty()){
                    if(isStartWithRoot) continue;
                    else pathQueue.addFirst(route);
                }else{
                    // ../ ../
                    if(pathQueue.peekFirst() == ".."){
                        pathQueue .addFirst(route)
                    }else{
                        pathQueue.removeFirst();
                    }
                }

            }
                else -> pathQueue.addFirst(route);

            }
        }

        val builder = StringBuilder();
        if(isStartWithRoot) builder.append("/");
        while(pathQueue.size > 1){
            builder.append(pathQueue.removeLast());
            builder.append("/");
        }

        if(!pathQueue.isEmpty()) builder.append(pathQueue.removeLast());

        return builder.toString();

    }

//    fun shortestEquivalentPath(path: String?): String {
//        if(path == null) return "";
//        val stack = LinkedList<String>();
//        val pathes = path.split("/");
//        val startWithRoot = path.startsWith("/");
//
//        for(route in pathes){
//            if(route.isEmpty()) continue;
//
//            if(route == ".") continue;
//
//            if(route == ".."){
//                if(stack.isEmpty() || stack.peek() == "..") {
//                    stack.push(route);
//
//                } else{
//                    stack.pop();
//                }
//
//            }else{
//                stack.push(route);
//            }
//        }
//
//        val builder = StringBuilder();
//        if(startWithRoot) builder.append("/");
//        while(!stack.isEmpty()){
//            builder.append(stack.removeLast());
//            if(!stack.isEmpty()){
//                builder.append("/");
//            }
//        }
//
//        return builder.toString();
//    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DirectoryPathNormalization.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}