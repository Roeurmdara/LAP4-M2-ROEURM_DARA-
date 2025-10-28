package com.example.todo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// The public class name "AddExpenseActivity" matches the file name "AddExpenseActivity.java"
public class AddExpenseActivity extends AppCompatActivity {

    private EditText editTextAmount;
    private EditText editTextCurrency;
    // ... other EditTexts and Buttons

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        // ... findViewById and listener code ...
    }

    private void saveExpense() {
        // ... save logic ...
    }
}
