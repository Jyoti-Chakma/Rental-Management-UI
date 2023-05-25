package com.jumcoder.rentalmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;

public class AddProperty extends AppCompatActivity {

    private static final String TAG = "AddProperty";
    private static final String KEY_PROPERTY = "property";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_NOTES = "notes";

    EditText editText1, editText2, editText3;
    Button button;
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference propertyRef = db.collection("Property");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

        //Findings
        editText1 = findViewById(R.id.propertyName);
        editText2 = findViewById(R.id.propertyAddress);
        editText3 = findViewById(R.id.addPropertyNotes);
        button = findViewById(R.id.addPropertyButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText1.getText().toString();
                String address = editText2.getText().toString();
                String notes = editText3.getText().toString();

                AddingProperty addingProperty = new AddingProperty();

                propertyRef.add(addingProperty);

                Intent intent = new Intent(AddProperty.this, Property.class);
                startActivity(intent);

            }
        });
    }
}