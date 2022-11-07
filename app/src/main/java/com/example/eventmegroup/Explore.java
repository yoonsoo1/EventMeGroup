package com.example.eventmegroup;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.picopalette.apps.event_me.Adapters.EventsAdapter;
import io.picopalette.apps.event_me.Adapters.SearchAdapter;
import io.picopalette.apps.event_me.Adapters.SearchViewHolder;
import io.picopalette.apps.event_me.Models.Event;
import io.picopalette.apps.event_me.R;
import io.picopalette.apps.event_me.Utils.Constants;
import io.picopalette.apps.event_me.Utils.Utilities;

public class Explore extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private RecyclerView searchRecycler;
    private GridLayoutManager mLayoutManager;
    private ArrayList<Event> events;
    private SearchAdapter adapter;
    private List<Event> homeevents;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overlay_view);
        homeevents = (List<Event>) getIntent().getExtras().get("mylist");
        getmydata(homeevents);
        searchRecycler = (RecyclerView) findViewById(R.id.searchRecycler);
        events = new ArrayList<>();
        searchRecycler.setHasFixedSize(true);
        adapter = new SearchAdapter(getApplicationContext(), events, searchRecycler, this);
        searchRecycler.setAdapter(adapter);
        mLayoutManager = new GridLayoutManager(this, 2);
        searchRecycler.setLayoutManager(mLayoutManager);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<Event> neweve  =new ArrayList<>();
        for(Event event: events){
            String name  = event.getName().toLowerCase();
            if(name.contains( newText )){
                neweve.add( event );
            }
            adapter.setfilter(neweve);

        }
        return false;
    }

}
