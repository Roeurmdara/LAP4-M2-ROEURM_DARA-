package com.example.todo.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.todo.R;
import com.example.todo.data.ExpenseData;
import com.example.todo.model.Expense;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddExpenseFragment extends Fragment {

    private TextInputEditText editTextAmount, editTextRemark, editTextDate;
    private AutoCompleteTextView autocompleteCategory;
    private TextInputLayout layoutAmount, layoutCategory, layoutDate;
    private Button buttonSaveExpense;

    private final Calendar myCalendar = Calendar.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);

        // Initialize Views
        editTextAmount = view.findViewById(R.id.edit_text_amount);
        editTextRemark = view.findViewById(R.id.edit_text_remark);
        autocompleteCategory = view.findViewById(R.id.autocomplete_category);
        editTextDate = view.findViewById(R.id.edit_text_date);
        buttonSaveExpense = view.findViewById(R.id.button_save_expense);

        // Layouts for showing errors
        layoutAmount = view.findViewById(R.id.layout_amount);
        layoutCategory = view.findViewById(R.id.layout_category);
        layoutDate = view.findViewById(R.id.layout_date);

        // Setup Category Dropdown
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_dropdown_item_1line, ExpenseData.getCategories());
        autocompleteCategory.setAdapter(categoryAdapter);

        // Setup Date Picker
        setupDatePicker();

        // Setup Save Button
        buttonSaveExpense.setOnClickListener(v -> saveExpense());

        return view;
    }

    private void setupDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDateLabel();
        };

        editTextDate.setOnClickListener(v -> new DatePickerDialog(getContext(), dateSetListener,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show());
    }

    private void updateDateLabel() {
        String myFormat = "yyyy-MM-dd"; // Use a consistent format
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editTextDate.setText(sdf.format(myCalendar.getTime()));
        layoutDate.setError(null); // Clear error on selection
    }

    private void saveExpense() {
        // --- 1. Get Input ---
        String amountStr = editTextAmount.getText().toString().trim();
        String category = autocompleteCategory.getText().toString().trim();
        String remark = editTextRemark.getText().toString().trim();
        String date = editTextDate.getText().toString().trim();

        // --- 2. Validate Input ---
        boolean isValid = true;
        if (TextUtils.isEmpty(amountStr)) {
            layoutAmount.setError("Amount cannot be empty");
            isValid = false;
        } else {
            layoutAmount.setError(null);
        }

        if (TextUtils.isEmpty(category)) {
            layoutCategory.setError("Please select a category");
            isValid = false;
        } else {
            layoutCategory.setError(null);
        }

        if (TextUtils.isEmpty(date)) {
            layoutDate.setError("Please select a date");
            isValid = false;
        } else {
            layoutDate.setError(null);
        }

        if (!isValid) {
            return; // Stop if validation fails
        }

        // --- 3. Create Expense Object and Save ---
        try {
            double amount = Double.parseDouble(amountStr);
            Expense newExpense = new Expense(0, amount, "USD", category, remark, date); // ID is 0, will be set in ExpenseData
            ExpenseData.addExpense(newExpense);

            Toast.makeText(getContext(), "Expense Saved!", Toast.LENGTH_SHORT).show();

            // --- 4. Clear the form for the next entry ---
            clearForm();

        } catch (NumberFormatException e) {
            layoutAmount.setError("Invalid number format");
        }
    }

    private void clearForm() {
        editTextAmount.setText("");
        autocompleteCategory.setText("", false); // false to not trigger filter
        editTextRemark.setText("");
        editTextDate.setText("");
        editTextAmount.requestFocus(); // Set focus back to the first field
    }
}
