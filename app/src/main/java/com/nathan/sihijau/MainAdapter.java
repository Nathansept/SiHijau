package com.nathan.sihijau;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    Activity activity;
    ArrayList<String> arrayList;

    public MainAdapter(Activity activity,ArrayList<String> arrayList){
        this.activity = activity;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_drawer_main,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(arrayList.get(position));

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                switch (position){
                    case 0:
                        //Redirect to Dashboard
                        activity.startActivity(new Intent(activity,SecondActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;
                    case 1:
                        // Redirect to Account
                        activity.startActivity(new Intent(activity,ProfileUser.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;
                    case 2:
                        // Redirect to Konten Page
                        activity.startActivity(new Intent(activity,KontenSampah.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;
                    case 3:
                        // Redirect to Konten Page
                        activity.startActivity(new Intent(activity,DatabaseContact.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;
                    case 4:
                        // Redirect to About Us
                        activity.startActivity(new Intent(activity,AboutUs.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                    case 5:
                        // Redirect to Maps
                        activity.startActivity(new Intent(activity,ThirdActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;
                    case 6:
                        // Redirect to Tips
                        activity.startActivity(new Intent(activity,FAQdanTnT.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;
                    case 7:
                        // Redirect to FAQ
                        activity.startActivity(new Intent(activity,TnT.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;
                    case 8:
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setIcon(R.drawable.ic_baseline_contact_support_24);
                        builder.setTitle(R.string.title);
                        builder.setMessage(R.string.message);

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseAuth.getInstance().signOut();
                                activity.finishAffinity();
                                System.exit(0);

                            }
                        });
                        builder.setNegativeButton("Sign Out", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                FirebaseAuth.getInstance().signOut();
                                activity.startActivity(new Intent(activity,MainActivity.class)
                                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                Toast.makeText(activity,"You have Sign Out", Toast.LENGTH_LONG).show();
                            }
                        });
                        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });
                        builder.show();
                        break;


                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
        }
    }
}
