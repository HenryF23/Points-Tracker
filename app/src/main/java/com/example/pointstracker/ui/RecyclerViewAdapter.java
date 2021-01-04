package com.example.pointstracker.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointstracker.R;
import com.example.pointstracker.model.User;
import com.example.pointstracker.model.Users;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<User> myUserList;
    private Users users;

    public RecyclerViewAdapter(Context myContext) {
        users = Users.getInstance();
        this.myUserList = users.getMyUsers();
        this.context = myContext;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTxt, numLLTxt, numPointsTxt;
        private ImageView avatar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.name_textView);
            numLLTxt = itemView.findViewById(R.id.numLandlord_textView5);
            numPointsTxt = itemView.findViewById(R.id.numpoints_textView);
            avatar = itemView.findViewById(R.id.main_list_imageView);
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.main_activitity_list_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapter.MyViewHolder holder, final int position) {
        String name = myUserList.get(position).getName();
        holder.nameTxt.setText(name);
        holder.avatar.setBackgroundResource(R.drawable.ic_launcher_background);
        holder.avatar.setImageResource(R.drawable.ic_launcher_foreground);
        holder.numPointsTxt.setText(Integer.toString(myUserList.get(position).getPoints()));
        holder.numLLTxt.setText(Integer.toString(myUserList.get(position).getNumLandlords()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Landlord, did you win?");
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        users.updateScoreLandlordWin(true, position);
                        notifyDataSetChanged();
                        Toast.makeText(context, R.string.scoreUpdate, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        users.updateScoreLandlordWin(false, position);
                        notifyDataSetChanged();
                        Toast.makeText(context, R.string.scoreUpdate, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.update_user_dialog);

                User user = users.getUser(position);
                final EditText nameEditText = dialog.findViewById(R.id.name_edittext);
                nameEditText.setText(user.getName());
                final EditText pointsEditText = dialog.findViewById(R.id.points_edittext);
                pointsEditText.setText(Integer.toString(user.getPoints()));
                final EditText LLEditText = dialog.findViewById(R.id.numLL_edittext);
                LLEditText.setText(Integer.toString(user.getNumLandlords()));

                Button button = dialog.findViewById(R.id.update_button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = nameEditText.getText().toString();
                        int numPoints =
                                Integer.parseInt(pointsEditText.getText().toString());
                        int numLL =
                                Integer.parseInt(LLEditText.getText().toString());
                        users.updateUser(position, new User(name, numPoints, numLL));
                        notifyDataSetChanged();
                        Toast.makeText(v.getContext(), R.string.userUpdated, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                button = dialog.findViewById(R.id.remove_button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        users.removeUser(position);
                        Toast.makeText(v.getContext(), R.string.userRemoved, Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                dialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return myUserList.size();
    }
}
