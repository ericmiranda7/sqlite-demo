package com.example.splitthat

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.splitthat.data.DbHelper
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

    val dbHelper = DbHelper(context = LocalContext.current)
    val db = dbHelper.writableDatabase

    val inputExpenseName = remember { mutableStateOf("Expense name") }
    val inputExpenseCost = remember { mutableStateOf("42") }

    Column {
        Header(
            name = "Expenses",
            modifier = modifier
        )

        for (expense in expenses) {
            ExpenseItem(expense.name, expense.cost, modifier)
        }


        Row (modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            AddExpenseButton { expenses.add(Expense(inputExpenseName.value, inputExpenseCost.value.toDouble())) }

            BasicTextField(
                value = inputExpenseName.value,
                onValueChange = { inputExpenseName.value = it },
                modifier = modifier.width(IntrinsicSize.Min),
                textStyle = TextStyle(background = Color.Gray)
            )

            BasicTextField(
                value = inputExpenseCost.value,
                onValueChange = { inputExpenseCost.value = it },
                modifier = modifier.width(IntrinsicSize.Min),
                textStyle = TextStyle(background = Color.Gray)
            )
        }
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