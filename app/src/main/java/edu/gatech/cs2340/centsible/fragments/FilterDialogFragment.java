/**
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.gatech.cs2340.centsible.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.gatech.cs2340.centsible.R;
import edu.gatech.cs2340.centsible.Filters;
import edu.gatech.cs2340.centsible.model.Location;
import edu.gatech.cs2340.centsible.model.LocationManager;

public class FilterDialogFragment extends DialogFragment {

    public static final String TAG = "Filter";

    public interface FilterListener {

        void onFilter(Filters filters);

    }

    private View mRootView;

    @BindView(R.id.location_spinner)
    Spinner mLocationSpinner;

    @BindView(R.id.name_textfield)
    TextView mNameTextField;

    @BindView(R.id.category_textfield)
    TextView mCategoryTextField;

    private FilterListener mFilterListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.dialog_filters, container, false);
        ButterKnife.bind(this, mRootView);

        return mRootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        ArrayAdapter<Location> adapter = new ArrayAdapter<Location>(getActivity(), android.R.layout.simple_spinner_item, LocationManager.getInstance().getList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLocationSpinner.setAdapter(adapter);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FilterListener) {
            mFilterListener = (FilterListener) context;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
    }

    @OnClick(R.id.search_button)
    public void onSearchClicked() {
        if (mFilterListener != null) {
            mFilterListener.onFilter(getFilters());
        }

        dismiss();
    }

    @OnClick(R.id.cancel_button)
    public void onCancelClicked() {
        dismiss();
    }


    private Filters getFilters() {
        Filters filter = new Filters();
        filter.setCategory(getCategory());
        filter.setName(getName());
        filter.setLocation(getLocation());
        return filter;
    }

    public void resetFilters() {
        if (mRootView != null) {
            mLocationSpinner.setSelection(0);
            mCategoryTextField.setText("");
            mNameTextField.setText("");
        }
    }

    private String getCategory() {
        if (mCategoryTextField.getText().toString() == "") {
            return null;
        } else {
            return mCategoryTextField.getText().toString();
        }
    }

    private String getName() {
        if (mNameTextField.getText().toString() == "") {
            return null;
        } else {
            return mNameTextField.getText().toString();
        }
    }

    private String getLocation() {
        Location loc = (Location) mLocationSpinner.getSelectedItem();
        if (loc.getName().equals("Name")) {
            return null;
        } else {
            return loc.getKey();
        }
    }

}
