package com.example.splitthat.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val CREATE_SQL = "CREATE TABLE expenses (" +
        "${DbContract.ExpensesTable.COLUMN_NAME_EXPENSE_NAME} TEXT," +
        "${DbContract.ExpensesTable.COLUMN_NAME_COST} REAL)"

class DbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_SQL)
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