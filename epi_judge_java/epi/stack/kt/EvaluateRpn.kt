package epi.stack.kt

import epi.test_framework.EpiTest
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest
import java.util.*

object EvaluateRpn {
    @JvmStatic
    @EpiTest(testDataFile = "evaluate_rpn.tsv")
    fun eval(expression: String?): Int {
        if(expression.isNullOrEmpty()) return 0;
        val stack = LinkedList<Int>();

        val operations = expression.split(",");
        var num1 = 0;
        var num2 =0;
        for(i in operations.indices){
            val num = operations[i].toIntOrNull();
            if(num !=null){
                stack.push(num);
                continue;
            }

            num1 = stack.pop();
            num2 = stack.pop();
            when{
                operations[i] =="+" ->{
                  stack.push((num2+num1));
                }
                operations[i] =="-" ->{
                    stack.push((num2-num1));
                }
                operations[i] =="*" ->{
                    stack.push(num2*num1);
                }
                operations[i] =="/" ->{
                    stack.push(num2/num1)
                }
            }
        }

        return stack.peek().toInt();
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EvaluateRpn.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}