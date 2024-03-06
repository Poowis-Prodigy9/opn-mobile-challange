package com.example.opnchallenge.model

data class CartProduct(
    val product: Product,
    val count: Int = 0,
)

fun CartProduct.totalPrice(): Double = product.price * count

fun List<CartProduct>.totalPrice(): Double = this.sumOf { it.totalPrice() }

fun List<CartProduct>.totalCount(): Int = this.sumOf { it.count }
