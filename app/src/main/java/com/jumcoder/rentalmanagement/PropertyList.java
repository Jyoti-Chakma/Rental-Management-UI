package com.jumcoder.rentalmanagement;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

import androidx.appcompat.app.AppCompatActivity;

public class PropertyList extends AppCompatActivity {

    private TextView textView;
    private ListenerRegistration propertyListener;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference propertyRef = db.collection("Property");
    private DocumentReference addProperty = db.document("Property/Property List");

    private static final String KEY_PROPERTY = "property";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_NOTES = "notes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_list);

        textView = findViewById(R.id.propertyList);

    }
}