package edu.gatech.cs2340.centsible.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.gatech.cs2340.centsible.Filters;
import edu.gatech.cs2340.centsible.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.gatech.cs2340.centsible.adapter.DonationAdapter;
import edu.gatech.cs2340.centsible.fragments.FilterDialogFragment;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class DonationActivity extends AppCompatActivity
        implements DonationAdapter.OnDonationSelectedListener, FilterDialogFragment.FilterListener {
    @BindView(R.id.donation_recycler)
    RecyclerView mRecyclerView;

    @BindView(R.id.no_results_error)
    TextView mNoResultsError;

    private FirebaseFirestore mFirestore;
    private Query mQuery;
    private FilterDialogFragment mFilterDialog;

    private DonationAdapter mAdapter;
    private static final String TAG = "CentsibleTAG";


    public static Intent createIntent(Context context) {
        return new Intent(context, DonationActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        ButterKnife.bind(this);
        initFirestore();
        initRecyclerView();

        mFilterDialog = new FilterDialogFragment();
    }

    private void initFirestore() {
        mFirestore = FirebaseFirestore.getInstance();
        CollectionReference donations = mFirestore.collection("donations");
        mQuery = donations.orderBy("name");
    }
    private void initRecyclerView() {
        if (mQuery == null) {
            Log.w(TAG, "No query, not initializing RecyclerView");
        }

        mAdapter = new DonationAdapter(mQuery, this) {

            @Override
            protected void onDataChanged() {
                // Show/hide content if the query returns empty.
                if (getItemCount() == 0) {
                    mRecyclerView.setVisibility(View.GONE);
                    mNoResultsError.setVisibility(View.VISIBLE);
                } else {
                    mRecyclerView.setVisibility(View.VISIBLE);
                    mNoResultsError.setVisibility(View.GONE);
                }
            }

// --Commented out by Inspection START (11/8/18, 8:32 PM):
//            @Override
//            protected void onError(FirebaseFirestoreException e) {
//                // Show a snackbar on errors
//                Snackbar.make(findViewById(android.R.id.content),
//                        "Error: check logs for info.", Snackbar.LENGTH_LONG).show();
//            }
// --Commented out by Inspection STOP (11/8/18, 8:32 PM)
        };

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onDonationSelected(DocumentSnapshot donation) {
        Intent intent = new Intent(this, DetailedDonationActivity.class);
        intent.putExtra(DetailedDonationActivity.DONATION_ID, donation.getId());
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }

    @Override
    public void onFilter(Filters filters) {
        Query query = mFirestore.collection("donations");

        if (filters.hasCategory()) {
            query = query.whereEqualTo("category", filters.getCategory());
        }

        if (filters.hasName()) {
            query = query.whereEqualTo("name", filters.getName());
        }

        if (filters.hasLocation()) {
            query = query.whereEqualTo("location", filters.getLocation());
        }

        query.limit(100);

        mQuery = query;
        mAdapter.setQuery(query);
    }

    @OnClick(R.id.filter_bar)
    public void onFilterClicked() {
        // Show the dialog containing filter options
        mFilterDialog.show(getSupportFragmentManager(), FilterDialogFragment.TAG);
    }

    @OnClick(R.id.button_clear_filter)
    public void onClearFilterClicked() {
        mFilterDialog.resetFilters();

        onFilter(Filters.getDefault());
    }
}
