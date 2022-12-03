package com.example.eventmegroup;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;

public class Detail extends AppCompatActivity {
    private FirebaseAuth auth;
    private String uid;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseFirestore db;
    private TextView eventName;
    private TextView eventDate;
    private TextView eventAddy;
    private TextView eventCost;
    private TextView eventTime;
    private TextView eventNumPpl;
    private TextView eventDur;
    private ImageView imgView;
    private Button reg;
    private boolean registered;
    private User currUser;
    private int confNotif = 0;
    private boolean conflict = false;
    private ArrayList<String> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        String eventId = i.getStringExtra("eventId");

        eventName = findViewById(R.id.event_name);
        eventDate = findViewById(R.id.event_date);
        eventAddy = findViewById(R.id.event_loc);
        eventCost = findViewById(R.id.event_cost);
        eventTime = findViewById(R.id.event_time);
        eventNumPpl = findViewById(R.id.event_num);
        eventDur = findViewById(R.id.event_duration2);
        imgView = findViewById(R.id.event_pic);
        reg = findViewById(R.id.register);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        db = FirebaseFirestore.getInstance();

        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() == null) {
            uid = null;
            reg.setText(R.string.sign_in);
        }
        else {
            uid = auth.getCurrentUser().getUid();
            currUser = new User(uid);

            // Collect the data from the Firestore database
            db.collection("Users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    DocumentSnapshot ds = task.getResult();
                    HashMap<String, Object> addEvents = new HashMap<>();
                    events = (ArrayList<String>) ds.get("events");
                    currUser.setRegisteredEvents(events);

                    switch(currUser.registeredEvent(eventId)) {
                        case 1:
                        case 3:
                            registered = false;
                            reg.setText(R.string.registeration);
                            break;
                        case 2:
                            registered = true;
                            reg.setText(R.string.unreg);
                            break;
                    }
                    ArrayList<EventDate> dates = new ArrayList<>();
                    if(events != null) {
                        for(String e : events) {
                            db.collection("Events").document(e).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    String eDate = task.getResult().getString("date");
                                    String eTime = task.getResult().getString("time");
                                    Long eDur = task.getResult().getLong("duration");
                                    EventDate currEDate = new EventDate(eDate, eTime, eDur.toString());
                                    dates.add(currEDate);
                                }
                            });
                        }
                    }

                    currUser.setEventDates(dates);
                }
            });


        }

        loadPage(eventId);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(auth.getCurrentUser() == null) {
                    // User not logged in so send them to login page
                    startActivity(new Intent(Detail.this, SignInActivity.class));
                }
                else {
                    if(registered) {
                        unregister(eventId);
                        loadPage(eventId);
                    }
                    else {


//                        // User is signed in so let them register
//                        db.collection("Users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                DocumentSnapshot ds = task.getResult();
//                                HashMap<String, Object> addEvents = new HashMap<>();
//                                List<String> events = (List<String>) ds.get("events");
//                                if(events == null) {
//                                    // If there is no events, it returns null
//                                    System.out.println("Events returned null");
//                                    // Add the event into the document
//                                    addEvents.put("events", Arrays.asList(eventId));
//                                    db.collection("Users").document(uid)
//                                            .update(addEvents)
//                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                @Override
//                                                public void onSuccess(Void unused) {
//                                                    Log.d(TAG, "DocumentSnapshot successfully updated!");
//                                                }
//                                            })
//                                            .addOnFailureListener(new OnFailureListener() {
//                                                @Override
//                                                public void onFailure(@NonNull Exception e) {
//                                                    Log.w(TAG, "Error updating document", e);
//                                                }
//                                            });
//                                    db.collection("Events").document(eventId)
//                                            .update("numPeople", FieldValue.increment(1))
//                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                @Override
//                                                public void onSuccess(Void unused) {
//                                                    Log.d(TAG, "Increment successfully updated!");
//                                                }
//                                            })
//                                            .addOnFailureListener(new OnFailureListener() {
//                                                @Override
//                                                public void onFailure(@NonNull Exception e) {
//                                                    Log.w(TAG, "Error incrementing num in document", e);
//                                                }
//                                            });
//                                }
//                                else {
//                                    // If it does already exist, we can append to the list
//                                    System.out.println(Arrays.toString(events.toArray()));
//                                    // Append to the existing list
//                                    db.collection("Users").document(uid)
//                                            .update("events", FieldValue.arrayUnion(eventId))
//                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                @Override
//                                                public void onSuccess(Void unused) {
//                                                    Log.d(TAG, "DocumentSnapshot successfully updated!");
//                                                }
//                                            })
//                                            .addOnFailureListener(new OnFailureListener() {
//                                                @Override
//                                                public void onFailure(@NonNull Exception e) {
//                                                    Log.w(TAG, "Error updating document", e);
//                                                }
//                                            });
//                                    db.collection("Events").document(eventId)
//                                            .update("numPeople", FieldValue.increment(1))
//                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                @Override
//                                                public void onSuccess(Void unused) {
//                                                    Log.d(TAG, "Increment successfully updated!");
//                                                }
//                                            })
//                                            .addOnFailureListener(new OnFailureListener() {
//                                                @Override
//                                                public void onFailure(@NonNull Exception e) {
//                                                    Log.w(TAG, "Error incrementing num in document", e);
//                                                }
//                                            });
//                                }
//                                loadPage(eventId);
//                                registered = true;
//                                reg.setText(R.string.unreg);
//                            }
//                        });

                        register(eventId);
                    }

                }
            }
        });
    }

    private void warnConf() {
        Toast.makeText(Detail.this, "You have a conflicting event!", Toast.LENGTH_SHORT).show();
    }

    private void register(String eventId) {
        db.collection("Events").document(eventId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String eDate = task.getResult().getString("date");
                String eTime = task.getResult().getString("time");
                Long eDur = task.getResult().getLong("duration");

                EventDate currEDate = new EventDate(eDate, eTime, eDur.toString());
                if(conflict) {
                    if(confNotif > 0) {
                        confNotif = 0;
                    }
                    else { // This shouldn't happen but in case
                        confNotif++;
                        warnConf();
                        return;
                    }
                }
                else {
                    // conflict uninitialized or no conflict
                    conflict = currUser.eventConf(currEDate);
                    if(conflict) {
                        warnConf();
                        confNotif++;
                        return;
                    }
                }
                // Checking for conflict done so continue to add event
                System.out.println("currUser = " + currUser);
                currUser.addEvent(eventId, currEDate);
                HashMap<String, Object> addEvents = new HashMap<>();
                addEvents.put("events", currUser.getRegisteredEvents());
                db.collection("Users").document(uid)
                        .update(addEvents)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "DocumentSnapshot successfully updated!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error updating document", e);
                            }
                        });
                db.collection("Events").document(eventId)
                        .update("numPeople", FieldValue.increment(1))
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "Increment successfully updated!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error incrementing num in document", e);
                            }
                        });
                registered = true;
                reg.setText(R.string.unreg);
                loadPage(eventId);
            }
        });
    }

    private void unregister(String eventId) {
        db.collection("Users").document(uid)
                .update("events", FieldValue.arrayRemove(eventId))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
        db.collection("Events").document(eventId)
                .update("numPeople", FieldValue.increment(-1))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "Decrement successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error incrementing num in document", e);
                    }
                });
        registered = false;
        currUser.removeEvent(eventId);
        reg.setText(R.string.registeration);
    }

    private void loadPage(String eventId) {
        db.collection("Events").document(eventId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String eName = task.getResult().getString("name");
                String eDate = task.getResult().getString("date");
                String eAdd = task.getResult().getString("location");
                String eCost = task.getResult().getString("cost");
                String eTime = task.getResult().getString("time");
                Long numP = task.getResult().getLong("numPeople");
                Long eDur = task.getResult().getLong("duration");
                String imgUri = task.getResult().getString("image");
                eventName.setText(eName);
                eventDate.setText(eDate);
                eventAddy.setText(eAdd);
                eventCost.setText(eCost);
                eventTime.setText(eTime);
                eventDur.setText(eDur.toString());
                eventNumPpl.setText(numP.toString());
                Glide.with(Detail.this).load(imgUri).into(imgView);
            }
        });
    }
}