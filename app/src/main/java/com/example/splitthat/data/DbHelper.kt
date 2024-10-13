package com.example.splitthat.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val CREATE_SQL = "CREATE TABLE expenses (" +
        "${DbContract.ExpensesTable.COLUMN_NAME_EXPENSE_NAME} TEXT," +
        "${DbContract.ExpensesTable.COLUMN_NAME_COST} REAL)"
const val INSERT_SQL_DONUTS = "INSERT INTO expenses VALUES ('Donuts', 32.3)"
const val INSERT_SQL_CRANBERRIES = "INSERT INTO expenses VALUES ('Cranberries', 42.0)"

class DbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_SQL)
        db?.execSQL(INSERT_SQL_DONUTS)
        db?.execSQL(INSERT_SQL_CRANBERRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        // schema properties
        const val DATABASE_NAME = "expenses.db"
        const val DATABASE_VERSION = 1
    }
}