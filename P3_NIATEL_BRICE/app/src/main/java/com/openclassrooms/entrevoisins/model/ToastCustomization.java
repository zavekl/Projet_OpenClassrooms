package com.openclassrooms.entrevoisins.model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by <NIATEL BRICE> on <18/10/2019>.
 */


public class ToastCustomization {

    private Context context;
    private Neighbour neighbour;


    public ToastCustomization(Context context, Neighbour neighbour) {
        this.context = context;
        this.neighbour = neighbour;

    }

    public void toastCustom1() {

        Toast toast1 = Toast.makeText(context, "Le voisin " + neighbour.getName() + " est déjà dans la liste des favoris!", Toast.LENGTH_LONG);
        View view1 = toast1.getView();
        view1.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        TextView text = view1.findViewById(android.R.id.message);
        text.setTextColor(Color.WHITE);

        toast1.show();
    }

    public void toastCustom2() {

        Toast toast2 = Toast.makeText(context, "Le voisin " + neighbour.getName() + " vient d'être ajouté dans la liste des favoris!", Toast.LENGTH_LONG);
        View view2 = toast2.getView();
        view2.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        TextView text2 = view2.findViewById(android.R.id.message);
        text2.setTextColor(Color.WHITE);

        toast2.show();
    }
}
