package com.example.user.gcmapp;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class addListenerOnTextChange implements TextWatcher {


    DatabaseColumn databaseColumn;

    public addListenerOnTextChange(DatabaseColumn databaseColumn) {
        super();
        this.databaseColumn=databaseColumn;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
            databaseColumn.setMarks(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
