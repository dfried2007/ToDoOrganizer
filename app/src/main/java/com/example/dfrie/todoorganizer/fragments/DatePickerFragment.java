package com.example.dfrie.todoorganizer.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.dfrie.todoorganizer.MainActivity;
import com.example.dfrie.todoorganizer.R;
import com.example.dfrie.todoorganizer.util.ToDoUtil;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dfrie on 2/9/2017.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public final static String D_TAG = "DatePicker";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        EditText editview = (EditText)getActivity().findViewById(R.id.etDueDate);
        if (editview != null) {
            String duedate = editview.getText().toString();

            if (duedate.length()>4) {
                Date date;
                try {
                    date = ToDoUtil.SDF.parse(duedate);
                    if (duedate.equals(ToDoUtil.SDF.format(date))) {
                        // It had a valid date, so...
                        year = date.getYear() + 1900;
                        month = date.getMonth();
                        day = date.getDate();
                    }
                } catch (ParseException e) {
                    // do nothing...
                }
            }
        }

        // Create a new instance of DatePickerDialog and return it
/*
        //  This requires API level 16...
        DatePickerDialog dpd = new DatePickerDialog(getActivity(), this, year, month, day);
        dpd.getDatePicker().setBackground(new ColorDrawable(0x80202020));
*/

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Save the date chosen by the user...
        Intent intent = getActivity().getIntent();
        intent.putExtra(MainActivity.EXTRA_DATE, new int[] {year, month, day});

        // Show the date chosen by the user...
        ////Log.i(D_TAG , "result date=" + year + "-" + month + "-" + day);
        EditText editview = (EditText)getActivity().findViewById(R.id.etDueDate);
        if (editview != null) {
            final Calendar c = Calendar.getInstance();
            c.set(year, month, day);
            //java.text.DateFormat df = DateFormat.getDateFormat(view.getContext());
            editview.setText(ToDoUtil.SDF.format(c.getTime()));
            Log.i(D_TAG , "result date.  text=" + ToDoUtil.SDF.format(c.getTime()));
        } else {
            Log.i(D_TAG , "result DATE NOT loaded.  editview is NULL.");
        }
    }
}