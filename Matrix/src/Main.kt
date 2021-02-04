package com.homework.matrix

object Main {

    private fun generateDouleMatrix(x: Matrix) {
        for (i in 0 until x.getCols())
        {
            for (j in 0 until x.getRows())
            {
                x.setValue( Complex((1 + (Math.random() * 10).toInt()).toDouble()), i, j)
            }
        }
    }

    private fun generateComplexMatrix(x: Matrix) {
        for (i in 0 until x.getCols()) {
            for (j in 0 until x.getRows()) {
                x.setValue(
                    Complex(
                        (1 + (Math.random() * 10).toInt()).toDouble(),
                        (1 + (Math.random() * 10).toInt()).toDouble()
                    ), i, j
                )
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val x = Matrix(2, 2)
        val y = Matrix(2, 3)

        println("<=== Double Matrix ===>")
        generateDouleMatrix(x)
        generateDouleMatrix(y)

        println("First matrix(x) \n$x")
        println("Second matrix(y)\n$y")
        println("Transpose matrix (x)\n" + x.transpose())
        println("x + x\n" + x.plus(x))
        println("x * y\n" + x.multip(y))

        println("<=== Double Complex ===>")
        generateComplexMatrix(x)
        generateComplexMatrix(y)

        println("First matrix(x)\n$x")
        println("Second matrix(y)\n$y")
        println("Transpose matrix (x)\n" + x.transpose())
        println("y + y\n" + y.plus(y))
        println("x * y\n" + x.multip(y))
    }
}