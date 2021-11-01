package com.openclassrooms.Model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.openclassrooms.mareu.R;


public class ToastCustomization {

    private Context context;
    private String subject;

    public ToastCustomization(Context context) {
        this.context = context;

    }

    public ToastCustomization(Context context, String subject) {
        this.context = context;
        this.subject = subject;

    }

    public void toastHour() {

        Toast toast = Toast.makeText(context, R.string.sort_by_hour, Toast.LENGTH_SHORT);
        View view1 = toast.getView();
        view1.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        TextView text = view1.findViewById(android.R.id.message);
        text.setTextColor(Color.WHITE);

        toast.show();
    }

    public void toastLocation() {

        Toast toast = Toast.makeText(context, R.string.sort_by_location, Toast.LENGTH_SHORT);
        View view2 = toast.getView();
        view2.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        TextView text2 = view2.findViewById(android.R.id.message);
        text2.setTextColor(Color.WHITE);

        toast.show();
    }

    public void toastItem() {
        String stringSubject = context.getResources().getString(R.string.subject_of_meeting, subject);

        Toast toast = Toast.makeText(context, stringSubject, Toast.LENGTH_SHORT);
        View view2 = toast.getView();
        view2.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        TextView text2 = view2.findViewById(android.R.id.message);
        text2.setTextColor(Color.WHITE);

        toast.show();
    }

    public void toastEmptyList() {

        Toast toast = Toast.makeText(context, R.string.empty_list, Toast.LENGTH_SHORT);
        View view2 = toast.getView();
        view2.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        TextView text2 = view2.findViewById(android.R.id.message);
        text2.setTextColor(Color.WHITE);

        toast.show();
    }

    public void toastEmptyEditText() {

        Toast toast = Toast.makeText(context, R.string.empty_textView, Toast.LENGTH_SHORT);
        View view2 = toast.getView();
        view2.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        TextView text2 = view2.findViewById(android.R.id.message);
        text2.setTextColor(Color.WHITE);

        toast.show();
    }

}
