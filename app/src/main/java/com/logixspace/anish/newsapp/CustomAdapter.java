package com.logixspace.anish.newsapp;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
List<Model> my_List;
Context context;



    public CustomAdapter(List<Model> my_List, Context context) {
        this.my_List = my_List;
        this.context = context;
    }



        @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,parent,false);
      // recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

         final Model model=my_List.get(position);
         myViewHolder.tv1.setText(model.getNesatitle());
         myViewHolder.tv2.setText(model.getNewsdesc());
         myViewHolder.tv3.setText(model.getTimes());
        //  myViewHolder.img.setImageDrawable(context.getResources().getDrawable(model.getNewsimage()));
        Picasso.with(context).load(model.getNewsimage()).into(myViewHolder.img);

        //Click Event
        myViewHolder.r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,DetailedActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Title",model.getNesatitle());
                intent.putExtra("Desc",model.getNewsdesc());
                intent.putExtra("Image",model.getNewsimage());
                intent.putExtra("Date",model.getTimes());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return my_List.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tv1,tv2,tv3;
        RelativeLayout r;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           img=itemView.findViewById(R.id.avathar);
           tv1=itemView.findViewById(R.id.title);
           tv2=itemView.findViewById(R.id.desc);
           tv3=itemView.findViewById(R.id.times);
           r=itemView.findViewById(R.id.relative);

        }
    }

}
