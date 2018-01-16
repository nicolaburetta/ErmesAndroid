package com.example.david.ermes.View.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.david.ermes.Model.db.FirebaseCallback;
import com.example.david.ermes.Model.models.Match;
import com.example.david.ermes.Model.models.User;
import com.example.david.ermes.Model.repository.MatchRepository;
import com.example.david.ermes.Model.repository.UserRepository;
import com.example.david.ermes.R;
import com.example.david.ermes.View.MainAdapter;

import java.util.List;

/**
 * Created by nicol on 16/01/2018.
 */

public class MyMatchesActivity extends AppCompatActivity {

    private TextView no_matches_label;
    private RecyclerView recyclerView;
    private Toolbar toolbar;

    private MainAdapter adapter;
    private User currentUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_matches);

        no_matches_label = findViewById(R.id.no_matches_label);

        toolbar = findViewById(R.id.my_matches_toolbar);
        toolbar.setTitle("Partite giocate");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new MainAdapter(getApplicationContext());

        recyclerView = findViewById(R.id.my_matches_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        currentUser = getIntent().getExtras().getParcelable("user");
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (currentUser != null) {
            no_matches_label.setText("Nessuna partita");
            toolbar.setSubtitle(currentUser.getName());

            MatchRepository.getInstance().fetchFinishedJoinedMatchesByUserId(currentUser.getUID(),
                    new FirebaseCallback() {
                        @Override
                        public void callback(Object object) {
                            List<Match> matches = (List<Match>) object;

                            if (matches != null) {
                                no_matches_label.setVisibility(View.GONE);
                                toolbar.setTitle("Partite giocate (" + matches.size() + ")");

                                adapter.refreshList(matches);
                            } else {
                                no_matches_label.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        } else {
            no_matches_label.setText("Nessun utente loggato");
            no_matches_label.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
