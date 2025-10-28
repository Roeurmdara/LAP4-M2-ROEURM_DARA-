package com.example.todo;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.todo.data.ExpenseData;
import com.example.todo.model.Expense;

public class DetailExpenseActivity extends AppCompatActivity {

    private TextView tvAmount, tvCurrency, tvCategory, tvRemark, tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);

        tvAmount = findViewById(R.id.detail_tv_amount);
        tvCurrency = findViewById(R.id.detail_tv_currency);
        tvCategory = findViewById(R.id.detail_tv_category);
        tvRemark = findViewById(R.id.detail_tv_remark);
        tvDate = findViewById(R.id.detail_tv_date);

        // Retrieve the expense ID from the Intent
        long expenseId = getIntent().getLongExtra("EXPENSE_ID", -1);

        if (expenseId != -1) {
            // Find the full expense details from the data source
            Expense expense = ExpenseData.findExpenseById(expenseId);
            if (expense != null) {
                populateData(expense);
            } else {
                Toast.makeText(this, "Expense not found!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid Expense ID!", Toast.LENGTH_SHORT).show();
        }
    }

    private void populateData(Expense expense) {
        tvAmount.setText("Amount: " + expense.getAmount());
        tvCurrency.setText("Currency: " + expense.getCurrency());
        tvCategory.setText("Category: " + expense.getCategory());
        tvRemark.setText("Remark: " + expense.getRemark());
        tvDate.setText("Date: " + expense.getCreatedDate());
    }
}
