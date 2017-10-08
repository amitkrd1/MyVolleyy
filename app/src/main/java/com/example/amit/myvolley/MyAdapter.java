package com.example.amit.myvolley;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by AMIT on 10/8/2017.
 */

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    ArrayList<DepartmentSetGet> alist;

    public MyAdapter(ArrayList<DepartmentSetGet> alist) {
        this.alist = alist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.row,parent,false);
        MyViewHolder vhold=new MyViewHolder(v);

        return vhold;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.tv1.setText(alist.get(position).getDepartmentId());

      holder.tv2.setText(alist.get(position).getDepartmentName());


    }

    @Override
    public int getItemCount() {

        Log.v("arraysize",String.valueOf(alist.size()));
        return alist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{



        TextView tv1,tv2;
        public MyViewHolder(View itemView) {
            super(itemView);

            tv1=(TextView)itemView.findViewById(R.id.Did);
            tv2=(TextView)itemView.findViewById(R.id.Dname);
        }
    }
}
