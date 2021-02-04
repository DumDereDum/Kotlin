package com.homework.matrix

class Matrix {

    private var matrix: Array<Array<Complex?>>
    private var rows: Int
    private var cols: Int

    constructor(n: Int, m: Int)
    {
        this.rows = m
        this.cols = n
        matrix = Array(n) { arrayOfNulls(m) }
        for (i in 0 until n)
        {
            for (j in 0 until m)
            {
                matrix[i][j] = Complex()
            }
        }
    }

    constructor(matrix: Array<Array<Complex>>)
    {
        rows = matrix.size
        cols = matrix[0].size
        this.matrix = Array(rows) { arrayOfNulls(cols) }
        for (i in 0 until cols)
        {
            for (j in 0 until rows)
            {
                this.matrix[i][j] =
                    Complex(matrix[i][j].getReal(), matrix[i][j].getImaginary())
            }
        }
    }

    fun transpose(): Matrix
    {
        val x = Matrix(cols, rows)
        for (i in 0 until cols)
        {
            for (j in 0 until rows)
            {
                x.setValue(matrix[i][j], j, i)
            }
        }
        return x
    }

    operator fun plus(x: Matrix): Matrix
    {
        val tmp = Matrix(cols, rows)
        for (i in 0 until cols)
        {
            for (j in 0 until rows)
            {
                tmp.setValue(matrix[i][j]!!.plus(x.matrix[i][j]!!), i, j)
            }
        }
        return tmp
    }

    fun multip(matrix: Matrix): Matrix
    {
        val tmp = Matrix(cols, matrix.rows)
        for (i in 0 until cols)
        {
            for (j in 0 until matrix.rows)
            {
                var x = Complex()
                for (k in 0 until matrix.cols)
                {
                    x = x.plus(this.matrix[i][k]!!.multiply(matrix.getValue(k, j)!!))
                }
                tmp.setValue(x, i, j)
            }
        }
        return tmp
    }

    fun getRows(): Int
    {
        return this.rows
    }

    fun getCols(): Int
    {
        return this.cols
    }

    fun getValue(i: Int, j: Int): Complex?
    {
        return matrix[i][j]
    }

    fun setValue(x: Complex?, i: Int, j: Int)
    {
        matrix[i][j]!!.setValue(x!!)
    }

    override fun toString(): String
    {
        val str = StringBuilder()
        for (i in 0 until cols)
        {
            str.append("[  ")
            for (j in 0 until rows)
                str.append(matrix[i][j].toString()).append("  ")
            str.append("]\n")
        }
        return str.toString()
    }
}