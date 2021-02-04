package com.homework.matrix

import kotlin.math.sqrt


class Complex {
    private var re: Double
    private var im: Double

    constructor()
    {
        re = 0.0
        im = 0.0
    }

    constructor(a: Double)
    {
        this.re = a
        im = 0.0
    }

    constructor(a: Double, b: Double)
    {
        this.re = a
        this.im = b
    }

    operator fun plus(x: Complex): Complex
    {
        val a = re + x.re
        val b = im + x.im
        return Complex(a, b)
    }

    operator fun minus(x: Complex): Complex
    {
        val a = re - x.re
        val b = im - x.im
        return Complex(a, b)
    }

    fun multiply(x: Complex): Complex
    {
        val a = re * x.re - im * x.im
        val b = this.re * x.im + im * x.re
        return Complex(a, b)
    }

    fun abs(): Double
    {
        return sqrt(re * re + im * im)
    }

    fun setValue(x: Complex)
    {
        re = x.re
        im = x.im
    }

    fun getReal(): Double
    {
        return this.re
    }

    fun getImaginary(): Double
    {
        return this.im
    }

    override fun toString(): String
    {
        return if (im != 0.0) re.toString() + "+" + im + "i" else re.toString() + ""
    }
}