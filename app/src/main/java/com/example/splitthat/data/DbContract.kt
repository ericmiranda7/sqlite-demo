package com.example.splitthat.data

import android.provider.BaseColumns

object DbContract {
    object ExpensesTable : BaseColumns {
        const val TABLE_NAME = "expenses"
        const val COLUMN_NAME_EXPENSE_NAME = "expense_name"
        const val COLUMN_NAME_COST = "cost"
    }
}