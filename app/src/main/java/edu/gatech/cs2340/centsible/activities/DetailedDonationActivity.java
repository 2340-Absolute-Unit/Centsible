package edu.gatech.cs2340.centsible.activities;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.gatech.cs2340.centsible.R;
import edu.gatech.cs2340.centsible.model.Donation;
import edu.gatech.cs2340.centsible.model.LocationManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

import java.text.DateFormat;
import java.util.Objects;

import javax.annotation.Nullable;

public class DetailedDonationActivity extends AppCompatActivity implements EventListener<DocumentSnapshot> {

    public static final String DONATION_ID = "DONATION_ID";

    @BindView(R.id.donation_name)
    TextView nameView;

    @BindView(R.id.donation_category)
    TextView categoryView;

    @BindView(R.id.donation_short_description)
    TextView shortDescriptionView;

    @BindView(R.id.donation_long_description2)
    TextView longDescriptionView;

    @BindView(R.id.donation_value)
    TextView valueView;

    @BindView(R.id.donation_date)
    TextView dateView;

    @BindView(R.id.donation_location)
    TextView locationView;

    private DocumentReference mDonationRef;
    private ListenerRegistration mDonationsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_donation);
        ButterKnife.bind(this);

        String donationId = Objects.requireNonNull(getIntent().getExtras()).getString(DONATION_ID);
        if (donationId == null) {
            throw new IllegalArgumentException("Must pass " + DONATION_ID);
        }

        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();

        mDonationRef = mFirestore.collection("donations").document(donationId);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mDonationsListener = mDonationRef.addSnapshotListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mDonationsListener != null) {
            mDonationsListener.remove();
            mDonationsListener = null;
        }
    }

    @Override
    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
        if (e != null) {
            Log.d("CENTSIBLE", e.toString());
            return;
        }
        onDonationLoaded(Objects.requireNonNull(documentSnapshot).toObject(Donation.class));
    }

    private void onDonationLoaded(Donation d) {
        nameView.setText(d.getName());
        categoryView.setText(d.getCategory());
        shortDescriptionView.setText(d.getShortDescription());
        longDescriptionView.setText(d.getLongDescription());
        valueView.setText(Double.toString(d.getValue()));

        //DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        DateFormat df = DateFormat.getDateTimeInstance();
        dateView.setText(df.format(d.getLastUpdated()));
        locationView.setText(LocationManager.getInstance().getLocation(d.getLocation()).toString());
    }
}
