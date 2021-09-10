package com.example.demo2

fun main() {
    val list = (1..10).toList()
    val customlist = list.customFilter { it % 2 == 1 }
    println("list--" + customlist)

}

fun  List<Int>.customFilter(filterFunction: (Int) -> (Boolean)): Int
{
    var sum=0
    for (item in this) {
        if (filterFunction(item)) {
            sum+=item
        }
    }
    return sum
}