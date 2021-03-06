package com.openclassrooms.mareu.utils;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import com.openclassrooms.mareu.R;

import org.hamcrest.Matcher;

/**
 * Created by Alph4 le 10/04/2020.
 * Projet: Mareu
 **/
public class DeleteViewAction implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on specific button";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.item_list_delete_button);
        // Maybe check for null
        button.performClick();
    }
}
