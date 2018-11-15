package edu.gatech.cs2340.centsible.adapter;

import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.gatech.cs2340.centsible.R;
import edu.gatech.cs2340.centsible.model.Donation;

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
 *
 * RecyclerView adapter for a list of donations.
 */

public class DonationAdapter extends FirestoreAdapter<DonationAdapter.ViewHolder> {

    public interface OnDonationSelectedListener {

        /**
         * what to do when donation is selected
         *
         * @param location the location the donation is at
         */
        void onDonationSelected(DocumentSnapshot location);

    }

    private final OnDonationSelectedListener mListener;

    /**
     * create intent of context to listn to donation in firestore
     *
     * @param query data info
     * @param listener to get donation from firestore
     */
    public DonationAdapter(Query query, OnDonationSelectedListener listener) {
        super(query);
        super.startListening();
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.donation_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), mListener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.donation_name)
        TextView nameView;

        @BindView(R.id.donation_category)
        TextView categoryView;

//        @BindView(R.id.donation_location)
//        TextView locationView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final DocumentSnapshot snapshot,
                  final OnDonationSelectedListener listener) {

            Donation donation = snapshot.toObject(Donation.class);
            Resources resources = itemView.getResources();

            nameView.setText(Objects.requireNonNull(donation).getName());
            categoryView.setText(donation.getCategory());

            // Click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onDonationSelected(snapshot);
                    }
                }
            });
        }

    }
}
