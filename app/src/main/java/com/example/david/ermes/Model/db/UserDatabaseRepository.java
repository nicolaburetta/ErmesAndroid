package com.example.david.ermes.Model.db;


import android.util.Log;

import com.example.david.ermes.Model.db.DatabaseManager.OnDataChangedListener;
import com.example.david.ermes.Model.db.DbModels._User;
import com.example.david.ermes.Model.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserDatabaseRepository {

    private static UserDatabaseRepository instance = new UserDatabaseRepository();

    public static UserDatabaseRepository getInstance() {
        return instance;
    }

    private DatabaseReference usersRef;

    private UserDatabaseRepository() {
        DatabaseManager databaseManager = DatabaseManager.get();

        usersRef = databaseManager.getUsersRef();
    }

    public User getCurrentUser(){
        return DatabaseManager.get().getCurrentUser();
    }

    public void setUserDataChangedListener(final OnDataChangedListener<User> listener) {
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                DbModels._User value = dataSnapshot.getValue(DbModels._User.class);
                Log.d("FIREBASE", "Value is: " + value);

                listener.onDataChanged(value.convertToUser());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("FIREBASE", "Failed to read value.", error.toException());
            }
        });
    }

    public void save(String uid, User user) {
        DbModels._User u = new DbModels._User(user.getIdFavSport(), user.getCity());
        this.usersRef.child(uid).setValue(user);
    }

    public void fetchUserById(String id, final FirebaseCallback fc) {
        final List<_User> list = new ArrayList<>();
        Query getUser = this.usersRef.equalTo(id);
        getUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.add(dataSnapshot.getValue(_User.class));
                fc.callback(list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void fetchUsersById(final List<String> idList, final FirebaseCallback fc) {
        final List<_User> list = new ArrayList<>();
        Query getUsers = this.usersRef.orderByKey();
        getUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    if (idList.contains(d.getKey().toString())) {
                        _User user = d.getValue(_User.class);
                        user.setUID(d.getKey());
                        list.add(user);
                    }
                }
                fc.callback(list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
