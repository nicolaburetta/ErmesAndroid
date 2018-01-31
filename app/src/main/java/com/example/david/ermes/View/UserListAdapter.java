package com.example.david.ermes.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.TextView;
=======
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
>>>>>>> 7d6df54de0d2ab5df3ce1d6cecfc83157612ce0f

import com.example.david.ermes.Model.db.FirebaseCallback;
import com.example.david.ermes.Model.models.Friendship;
import com.example.david.ermes.Model.models.Notification;
<<<<<<< HEAD
import com.example.david.ermes.Model.models.Sport;
import com.example.david.ermes.Model.models.User;
import com.example.david.ermes.Model.repository.FriendshipRepository;
import com.example.david.ermes.Model.repository.NotificationRepository;
import com.example.david.ermes.Model.repository.SportRepository;
import com.example.david.ermes.Model.repository.UserRepository;
import com.example.david.ermes.Presenter.OnUserListFetchEnd;
=======
import com.example.david.ermes.Model.models.User;
>>>>>>> 7d6df54de0d2ab5df3ce1d6cecfc83157612ce0f
import com.example.david.ermes.Presenter.UserListPresenter;
import com.example.david.ermes.Presenter.utils.TimeUtils;
import com.example.david.ermes.R;
import com.example.david.ermes.View.activities.AccountActivity;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.List;
import java.util.Map;
=======
import java.util.Collections;
import java.util.List;
import java.util.Random;
>>>>>>> 7d6df54de0d2ab5df3ce1d6cecfc83157612ce0f

/**
 * Created by nicol on 15/01/2018.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.FriendViewHolder> {

    public enum ListType {
        GENERIC, FRIENDS
    }

    private int refreshCount = 0;
<<<<<<< HEAD
=======
    private ProgressDialog progressDialog;
>>>>>>> 7d6df54de0d2ab5df3ce1d6cecfc83157612ce0f

    private List<User> userList;
    private List<Notification> myRequestList;
    private List<Notification> toMeRequestList;
    private List<Friendship> friendshipList;
    private List<String> sportList;

    private UserListPresenter presenter;
    private Context context;

    private User currentUser;

<<<<<<< HEAD
=======
    private int teamsSeparatorIndex;

>>>>>>> 7d6df54de0d2ab5df3ce1d6cecfc83157612ce0f
    public UserListAdapter(Context context) {
        this.context = context;
        sportList = new ArrayList<>();

        presenter = new UserListPresenter();
<<<<<<< HEAD
=======
        teamsSeparatorIndex = 0;
        progressDialog = new ProgressDialog(context);
>>>>>>> 7d6df54de0d2ab5df3ce1d6cecfc83157612ce0f
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.default_friend_item,
                parent,
                false
        );

        return new UserListAdapter.FriendViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FriendViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return userList != null ? userList.size() : 0;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    private void refresh(List<User> users, List<Friendship> friendships,
                         List<Notification> myFriendshipRequests, List<String> sports,
                         List<Notification> toMeFriendshipRequests) {
        userList = users;
        friendshipList = friendships;
        myRequestList = myFriendshipRequests;
        toMeRequestList = toMeFriendshipRequests;
        sportList = sports;

<<<<<<< HEAD
        notifyDataSetChanged();
    }

=======
        teamsSeparatorIndex = 0;

        notifyDataSetChanged();
    }

    public void createRandomTeams() {
        if (userList != null && !userList.isEmpty() && friendshipList != null && myRequestList != null
                && toMeRequestList != null && sportList != null) {
            long seed = System.nanoTime();

            Collections.shuffle(userList, new Random(seed));
            Collections.shuffle(friendshipList, new Random(seed));
            Collections.shuffle(myRequestList, new Random(seed));
            Collections.shuffle(toMeRequestList, new Random(seed));
            Collections.shuffle(sportList, new Random(seed));

            teamsSeparatorIndex = userList.size() / 2;

            notifyDataSetChanged();
        } else {
            Toast.makeText(context, "Attendi lo scaricamento dei dati, riprova più tardi",
                    Toast.LENGTH_LONG).show();
        }
    }

>>>>>>> 7d6df54de0d2ab5df3ce1d6cecfc83157612ce0f
    private void setPresenterCallback(FirebaseCallback firebaseCallback) {
        presenter.setOnUserListFetchEnd((users, friendships, myFriendshipRequests,
                                         toMeFriendshipRequests, sports) -> {
            refresh(users, friendships, myFriendshipRequests, sports, toMeFriendshipRequests);

            if (firebaseCallback != null) {
                if (users != null) {
                    firebaseCallback.callback(users.size());
                } else {
                    firebaseCallback.callback(0);
                }
            }
<<<<<<< HEAD
=======

            progressDialog.dismiss();
>>>>>>> 7d6df54de0d2ab5df3ce1d6cecfc83157612ce0f
        });
    }

    public void refreshFriendList(FirebaseCallback firebaseCallback) {
<<<<<<< HEAD
=======
        progressDialog.show();
>>>>>>> 7d6df54de0d2ab5df3ce1d6cecfc83157612ce0f
        setPresenterCallback(firebaseCallback);
        presenter.prepareFriendList();
    }

    public void refreshUserList(List<String> userIdList, FirebaseCallback firebaseCallback) {
<<<<<<< HEAD
=======
        progressDialog.show();
>>>>>>> 7d6df54de0d2ab5df3ce1d6cecfc83157612ce0f
        setPresenterCallback(firebaseCallback);
        presenter.prepareList(userIdList);
    }

    public class FriendViewHolder extends RecyclerView.ViewHolder {

        TextView friendName;
        TextView friendInfo;
        TextView friendshipDate;
        CircularImageView friendImage;
        Button friendshipRequestButton;
<<<<<<< HEAD
=======
        LinearLayout teamLabel;
        TextView teamLabelText;
>>>>>>> 7d6df54de0d2ab5df3ce1d6cecfc83157612ce0f

        View itemView;
        UserListPresenter.RelationType relationType;
        long friendship_date;

        public FriendViewHolder(View itemView) {
            super(itemView);

            friendName = itemView.findViewById(R.id.friend_name_invitation);
            friendInfo = itemView.findViewById(R.id.friend_info);
            friendshipDate = itemView.findViewById(R.id.friendship_date);
            friendImage = itemView.findViewById(R.id.friend_image);
            friendshipRequestButton = itemView.findViewById(R.id.friendship_request_button);
<<<<<<< HEAD
=======
            teamLabel = itemView.findViewById(R.id.random_team_label);
            teamLabelText = itemView.findViewById(R.id.random_team_label_text);
>>>>>>> 7d6df54de0d2ab5df3ce1d6cecfc83157612ce0f

            this.itemView = itemView;
            this.itemView.setOnClickListener(view -> {
                Intent accountActivity = new Intent(context, AccountActivity.class);

                Bundle extras = new Bundle();
                extras.putParcelable("user", userList.get(getAdapterPosition()));

                accountActivity.putExtras(extras);
                context.startActivity(accountActivity);
            });

            friendshipRequestButton.setOnClickListener(view -> {
                relationType = getRelationType(getAdapterPosition());

                switch (relationType) {
                    case NO_RELATION:
<<<<<<< HEAD
=======
                        progressDialog.show();

>>>>>>> 7d6df54de0d2ab5df3ce1d6cecfc83157612ce0f
                        User user = userList.get(getAdapterPosition());
                        Friendship.requestFriendshipTo(currentUser, user.getUID(), object -> {
                            presenter.updateMyFriendhipRequest(getAdapterPosition(),
                                    (Notification) object);
<<<<<<< HEAD
=======

                            progressDialog.dismiss();
>>>>>>> 7d6df54de0d2ab5df3ce1d6cecfc83157612ce0f
                            notifyDataSetChanged();
                        });
                        break;
                    case REPLY_REQUEST:
<<<<<<< HEAD
=======
                        progressDialog.show();

>>>>>>> 7d6df54de0d2ab5df3ce1d6cecfc83157612ce0f
                        Notification request = toMeRequestList.get(getAdapterPosition());
                        Friendship.acceptRequest(currentUser, request, object -> {
                            presenter.updateFriendship(getAdapterPosition(),
                                    (Friendship) object);
<<<<<<< HEAD
=======

                            progressDialog.dismiss();
>>>>>>> 7d6df54de0d2ab5df3ce1d6cecfc83157612ce0f
                            notifyDataSetChanged();
                        });
                        break;
                    default:
                        break;
                }
            });
        }

        public void bind(int position) {
            if (userList != null && userList.size() > 0) {
<<<<<<< HEAD
=======
                if (teamsSeparatorIndex > 0 && (position == teamsSeparatorIndex || position == 0)) {
                    teamLabel.setVisibility(View.VISIBLE);

                    if (position == 0) {
                        teamLabelText.setText("Squadra 1");
                    } else if (position == teamsSeparatorIndex) {
                        teamLabelText.setText("Squadra 2");
                    }
                } else {
                    teamLabel.setVisibility(View.GONE);
                }

>>>>>>> 7d6df54de0d2ab5df3ce1d6cecfc83157612ce0f
                relationType = getRelationType(getAdapterPosition());
                User user = userList.get(position);
                String sport = sportList.get(position);

                if (user != null) {
                    // name
                    friendName.setText(user.getName());

                    // image
                    if (user.getPhotoURL() != null && !user.getPhotoURL().isEmpty()) {
                        Picasso.with(context).load(user.getPhotoURL()).into(friendImage);
                    } else {
                        Picasso.with(context).load(R.drawable.user_placeholder).into(friendImage);
                    }

                    // info
                    String info = String.format("%s anni",
                            String.valueOf(TimeUtils.getAgeFromBirth(user.getBirthDate())));
                    if (sport != null && !sport.isEmpty()) {
<<<<<<< HEAD
                        info += " | " + sport;
=======
                        info += ", " + sport;
>>>>>>> 7d6df54de0d2ab5df3ce1d6cecfc83157612ce0f
                    }
                    friendInfo.setText(info);

                    // friendship date
                    switch (relationType) {
                        case ME:
                            friendshipDate.setVisibility(View.GONE);
                            friendshipRequestButton.setVisibility(View.GONE);
                            break;
                        case FRIENDS:
                            friendshipDate.setText(new StringBuilder()
                                    .append("Amici dal ")
                                    .append(TimeUtils.fromMillistoYearMonthDay(friendship_date)));
                            friendshipDate.setVisibility(View.VISIBLE);

                            friendshipRequestButton.setVisibility(View.GONE);
                            break;
                        case WAITING_FOR_RESPONSE:
                            friendshipRequestButton.setText("Richiesta\neffettuata");
                            friendshipRequestButton.setVisibility(View.VISIBLE);
                            friendshipRequestButton.setActivated(false);

                            friendshipDate.setVisibility(View.GONE);
                            break;
                        case REPLY_REQUEST:
                            friendshipRequestButton.setText("Rispondi alla\nrichiesta");
                            friendshipRequestButton.setVisibility(View.VISIBLE);
                            friendshipRequestButton.setActivated(true);

                            friendshipDate.setVisibility(View.GONE);
                            break;
                        default:
                            friendshipRequestButton.setText("Chiedi\namicizia");
                            friendshipRequestButton.setVisibility(View.VISIBLE);
                            friendshipRequestButton.setActivated(true);

                            friendshipDate.setVisibility(View.GONE);
                            break;
                    }
                }
            }
        }

        private UserListPresenter.RelationType getRelationType(int position) {
            if (position >= 0) {
                User user = userList != null && position < userList.size() ?
                        userList.get(position) : null;

                Friendship friendship = friendshipList != null && position < friendshipList.size() ?
                        friendshipList.get(position) : null;

                Notification friendshipRequestNotification =
                        myRequestList != null && position < myRequestList.size() ?
                                myRequestList.get(position) : null;

                Notification toMeFriendshipRequestNotification =
                        toMeRequestList != null && position < toMeRequestList.size() ?
                                toMeRequestList.get(position) : null;

                if (user != null && User.getCurrentUserId().equals(user.getUID())) {
                    return UserListPresenter.RelationType.ME;
                } else if (friendship != null) {
                    friendship_date = friendship.getDate();
                    return UserListPresenter.RelationType.FRIENDS;
                } else if (friendshipRequestNotification != null) {
                    return UserListPresenter.RelationType.WAITING_FOR_RESPONSE;
                } else if (toMeFriendshipRequestNotification != null) {
                    return UserListPresenter.RelationType.REPLY_REQUEST;
                } else {
                    return UserListPresenter.RelationType.NO_RELATION;
                }
            }

            return UserListPresenter.RelationType.NO_RELATION;
        }
    }
}
