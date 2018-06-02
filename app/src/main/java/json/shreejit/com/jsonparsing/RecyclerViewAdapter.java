package json.shreejit.com.jsonparsing;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {

    public RecyclerViewAdapter(Context context, List<MenuModelClass> menuModelClasses) {
        this.context = context;
        this.menuModelClasses = menuModelClasses;
    }

    Context context;
        List<MenuModelClass> menuModelClasses;
    @NonNull
    @Override
    public RecyclerViewAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,parent,false);
       return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        final MenuModelClass menuModelClass=menuModelClasses.get(position);
        holder.txt_item_price.setText(menuModelClass.getItem_Price());
        holder.txt_item_desc.setText(menuModelClass.getItem_Desc_English());
        holder.txt_item_name.setText(menuModelClass.getItem_Name());

        Glide.with(context).load("http://vedisapp.berlin-webdesign-agentur.de/Image/"+menuModelClass.getImage()).placeholder(R.drawable.ic_launcher_background).into(holder.txt_item_imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "You Clicked"+ menuModelClass.getItem_Name(), Toast.LENGTH_SHORT).show();
                System.out.println("ItemName"+menuModelClass.getItem_Name());


                Intent intent=new Intent(context,IntentActivity.class);
                intent.putExtra("item_name",menuModelClass.getItem_Name());
                intent.putExtra("item_price",menuModelClass.getItem_Price());
                context.startActivity(intent);
                //context.startActivity(new Intent(context,IntentActivity.class));
             }
        });
    }

    @Override
    public int getItemCount() {


        return menuModelClasses.size();
    }
    class Holder extends RecyclerView.ViewHolder{

        TextView txt_item_desc;
        TextView txt_item_price;
        TextView txt_item_name;
        ImageView  txt_item_imageView;
        public Holder(View itemView) {
            super(itemView);

            txt_item_desc=itemView.findViewById(R.id.item_description);
            txt_item_name=itemView.findViewById(R.id.name);
            txt_item_price=itemView.findViewById(R.id.item_price);
            txt_item_imageView=itemView.findViewById(R.id.image_view);

        }
    }
 }
