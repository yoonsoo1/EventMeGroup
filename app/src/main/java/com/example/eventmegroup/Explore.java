package com.example.eventmegroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
