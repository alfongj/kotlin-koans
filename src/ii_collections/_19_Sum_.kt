package ii_collections

import ii_collections.data.customers

fun example6() {
    val sum = listOf(1, 5, 3).sum()
}

fun Customer.getTotalOrderPrice(): Double {
    // Return the sum of prices of all products that a customer has ordered.
    // Note: a customer may order the same product for several times.
    return orders.sumByDouble { it.products.sumByDouble { it.price } }
}
