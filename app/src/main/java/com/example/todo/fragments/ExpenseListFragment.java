package com.example.todo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.todo.R;
import com.example.todo.adapter.ExpenseAdapter;
import com.example.todo.data.ExpenseData;

public class ExpenseListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_expenses);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Load dummy data and set the adapter
        ExpenseAdapter adapter = new ExpenseAdapter(getContext(), ExpenseData.getExpenses());
        recyclerView.setAdapter(adapter);

        return view;
    }
}
