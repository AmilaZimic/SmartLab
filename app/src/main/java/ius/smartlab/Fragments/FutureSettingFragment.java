package ius.smartlab.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import ius.smartlab.Helpers.FutureSettings;
import ius.smartlab.R;

import static android.widget.Toast.LENGTH_LONG;

public class FutureSettingFragment extends DialogFragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private DatePickerDialog fromDatePickerDialog;
    private TimePickerDialog fromTimePickerDialog;

    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat timeFormatter;

    private EditText fromDateEtxt;
    private EditText fromTimeEtxt;

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;

    private Button reservation_done;

    private FutureSettingFragment.OnFragmentInteractionListener mListener;

    private static final String TAG = FutureSettingFragment.class.getSimpleName();
    private EditText inputTemperature, inputLight, inputDoors, inputDate, inputTime;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String customerId;

    public FutureSettingFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_future_setting, container, false);

        /** Date & Time Picker **/

        dateFormatter = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
        fromDateEtxt = (EditText) v.findViewById(R.id.future_date);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();

        timeFormatter = new SimpleDateFormat("HH:mm", Locale.US);
        fromTimeEtxt = (EditText) v.findViewById(R.id.future_time);
        fromTimeEtxt.setInputType(InputType.TYPE_NULL);
        fromTimeEtxt.requestFocus();

        setDateField();
        setTimeField();

        editText1 = (EditText) v.findViewById(R.id.future_temperature);
        editText2 = (EditText) v.findViewById(R.id.future_light);
        editText3 = (EditText) v.findViewById(R.id.future_doors);


        editText1.addTextChangedListener(mTextWatcher);
        editText2.addTextChangedListener(mTextWatcher);
        editText3.addTextChangedListener(mTextWatcher);
        fromDateEtxt.addTextChangedListener(mTextWatcher);
        fromTimeEtxt.addTextChangedListener(mTextWatcher);


        reservation_done = (Button) v.findViewById(R.id.btn_future_done);
        reservation_done.setEnabled(false);

        reservation_done.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Thank You! See You soon :)", LENGTH_LONG).show();
            }
        });

        inputTemperature = (EditText) v.findViewById(R.id.future_temperature);
        inputLight = (EditText) v.findViewById(R.id.future_light);
        inputDoors = (EditText) v.findViewById(R.id.future_doors);
        inputDate = (EditText) v.findViewById(R.id.future_date);
        inputTime = (EditText) v.findViewById(R.id.future_time);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'FutureSettings' node
        mFirebaseDatabase = mFirebaseInstance.getReference("FutureSettings");

        return v;
    }

    /* Customer data change listener */
    private void addCustomerChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(customerId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FutureSettings future = dataSnapshot.getValue(FutureSettings.class);

                /* Check for null */
                if (future == null) {
                    Log.e(TAG, "Data is null!");
                    return;
                }

                Log.e(TAG, "Data is changed!" + future.temperature + ", " + future.light + ", " + future.doors + ", " + future.date + ", " + future.time);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                /* Failed to read value */
                Log.e(TAG, "Failed to read data.", error.toException());
            }
        });
    }

    /* Set Date */
    private void setDateField() {

        fromDateEtxt.setOnClickListener(this);

        final java.util.Calendar newCalendar = java.util.Calendar.getInstance();

        fromDatePickerDialog = new DatePickerDialog(getActivity(), R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                java.util.Calendar newDate = java.util.Calendar.getInstance();
                newDate.set(Calendar.YEAR, year);
                newDate.set(Calendar.MONTH, monthOfYear);
                newDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));

                Toast.makeText(getActivity(), "You choose: " + dayOfMonth + "." + (monthOfYear + 1) + "." + year, Toast.LENGTH_LONG).show();
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(java.util.Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    /* Set Time */
    private void setTimeField() {

        fromTimeEtxt.setOnClickListener(this);

        java.util.Calendar newCalendar = java.util.Calendar.getInstance();

        fromTimePickerDialog = new TimePickerDialog(getActivity(), R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                java.util.Calendar newTime = java.util.Calendar.getInstance();
                newTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                newTime.set(Calendar.MINUTE, minute);
                fromTimeEtxt.setText(timeFormatter.format(newTime.getTime()));

                Toast.makeText(getActivity(), "You choose: " + hourOfDay + ":" + minute, Toast.LENGTH_LONG).show();

            }

        }, newCalendar.get(java.util.Calendar.HOUR_OF_DAY), newCalendar.get(java.util.Calendar.MINUTE), false);

    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {

            String s1 = editText1.getText().toString();
            String s2 = editText2.getText().toString();
            String s3 = editText3.getText().toString();
            String s5 = fromDateEtxt.getText().toString();
            String s6 = fromTimeEtxt.getText().toString();

            if (s1.equals("") || s2.equals("") || s3.equals("") || s5.equals("") || s6.equals("")) {
                reservation_done.setEnabled(false);
            } else {
                reservation_done.setEnabled(true);
            }
        }
    };

    @Override
    public void onClick(View v) {
        if (v == fromDateEtxt) {
            fromDatePickerDialog.show();
        } else if (v == fromTimeEtxt) {
            fromTimePickerDialog.show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(), "Location: " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}