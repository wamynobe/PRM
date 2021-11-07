package com.example.prmmusic.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmmusic.R;
import com.example.prmmusic.activity.ListTypeByTopicActivity;
import com.example.prmmusic.model.Type;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListTypeByTopicAdapter extends RecyclerView.Adapter<ListTypeByTopicAdapter.ViewHolder>{

    Context context;
    ArrayList<Type> typeArrayList;

    public ListTypeByTopicAdapter(Context context, ArrayList<Type> typeArrayList) {
        this.context = context;
        this.typeArrayList = typeArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.type_by_topic_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Type type = typeArrayList.get(position);
        Picasso.get().load(type.getImage()).into(holder.imageView);
        holder.textViewtypename.setText(type.getName());
    }

    @Override
    public int getItemCount() {
        return typeArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewtypename;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageviewtopicitem);
            textViewtypename = itemView.findViewById(R.id.textviewtypename);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListTypeByTopicActivity.class);
                    intent.putExtra("typeId", typeArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
