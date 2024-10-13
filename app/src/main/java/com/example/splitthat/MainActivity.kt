package com.example.splitthat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.splitthat.model.Expense
import com.example.splitthat.ui.theme.SplitThatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SplitThatTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ExpenseList(
                        name = "Expenses",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ExpenseItem(expenseName: String, cost: Double, modifier: Modifier) {
    Row(modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = expenseName)
        Text(text = cost.toString(), color = Color.Red)
    }
}

@Composable
fun ExpenseList(name: String, modifier: Modifier = Modifier) {
    val expenses = remember { mutableStateListOf(Expense("Donuts", 32.3),
        Expense("Cranberries", 42.3)
    ) }

    Column {
        Header(
            name = "Expenses",
            modifier = modifier
        )

        for (expense in expenses) {
            ExpenseItem(expense.name, expense.cost, modifier)
        }


        AddExpenseButton { expenses.add(Expense("Example Expense", 0.0)) }
        DeleteExpenseButton { expenses.removeLast() }
    }
}

@Composable
fun AddExpenseButton(onClick: () -> Unit) {
    Button(onClick = { onClick() }) {
        Text("Add Expense")
    }
}

@Composable
fun DeleteExpenseButton(onClick: () -> Unit) {
    Button(onClick = { onClick() }) {
        Text("Remove Last Expense")
    }
}

@Composable
fun Header(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ExpenseListPreview() {
    SplitThatTheme {
        ExpenseList("Expenses")
    }
}