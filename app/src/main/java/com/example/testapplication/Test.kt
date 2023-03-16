package com.example.testapplication

fun main(){

    val list = mutableListOf(1,2,3,1,2,4,5,5,4)
    val array = intArrayOf(0,-3,1,3,4,3)

    print("Number that does not repeat is ${findSingleNumberUsingXor(list)}\n" )
    print("Min number of coins is ${countCoins(75)}\n")
    print("Smallest positive integer is ${smallestPositiveInteger(array)}\n")
    print("Sum = ${sumBinary("1010", "1011")}\n")
    print("Number of Variants is ${countVariants(4)}\n")

    // 6
    val myList = CustomLinkedList<Int>()

    myList.add(1)
    myList.add(2)
    myList.add(3)
    myList.add(9)
    myList.printList()
    myList.deleteByIndex(3)
    myList.printList()
    myList.deleteByValue(2)
    myList.printList()

}

// 1
fun findSingleNumberUsingXor(numbers: List<Int>): Int {
    var result = 0
    for (num in numbers) {
        result = result xor num
    }
    return result
}

// 2
fun countCoins(money: Int): Int {
    val coins = listOf(50, 20, 10, 5, 1)
    var numCoins = 0
    var remainingMoney = money
    for (coin in coins) {
        while (remainingMoney >= coin) {
            remainingMoney -= coin
            numCoins++
        }
    }
    return numCoins
}

// 3
fun smallestPositiveInteger(array: IntArray): Int {
    val list = mutableListOf<Int>()
    for (num in array) {
        if (num > 0) {
            list.add(num)
        }
    }
    var smallest = 1
    while (list.contains(smallest)) {
        smallest++
    }
    return smallest
}

// 4
fun sumBinary(a: String, b: String): String {
    val sb = StringBuilder()
    var carry = 0
    var i = a.length - 1
    var j = b.length - 1
    while (i >= 0 || j >= 0 || carry > 0) {
        val sum = carry +
                (if (i >= 0) a[i--] - '0' else 0) +
                (if (j >= 0) b[j--] - '0' else 0)
        sb.append(sum % 2)
        carry = sum / 2
    }
    return sb.reverse().toString()
}

// 5
fun countVariants(n: Int): Int {
    if (n <= 2) return n
    val ways = mutableListOf(1, 2)
    for (i in 3..n) {
        ways.add(ways[i - 2] + ways[i - 3])
    }
    return ways.last()
}

// 6
class CustomLinkedList<T> {
    data class Node<T>(val value: T, var prev: Node<T>? = null, var next: Node<T>? = null)

    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private val nodeMap = LinkedHashMap<T, Node<T>>()

    fun add(value: T) {
        val node = Node(value)
        nodeMap[value] = node

        if (head == null) {
            head = node
            tail = node
        } else {
            tail?.next = node
            node.prev = tail
            tail = node
        }
    }

    fun deleteByValue(value: T) {
        val node = nodeMap[value] ?: return

        if (node.prev != null) {
            node.prev?.next = node.next
        } else {
            head = node.next
        }

        if (node.next != null) {
            node.next?.prev = node.prev
        } else {
            tail = node.prev
        }

        nodeMap.remove(value)
    }

    fun deleteByIndex(index: Int) {
        var node = head
        var currentIndex = 0

        while (node != null && currentIndex != index) {
            node = node.next
            currentIndex++
        }

        if (node == null) return

        if (node.prev != null) {
            node.prev?.next = node.next
        } else {
            head = node.next
        }

        if (node.next != null) {
            node.next?.prev = node.prev
        } else {
            tail = node.prev
        }

        nodeMap.remove(node.value)
    }

    fun printList() {
        var node = head
        while (node != null) {
            print("${node.value} ")
            node = node.next
        }
        println()
    }
}


