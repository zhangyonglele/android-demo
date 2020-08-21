package com.example.myapplication2.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication2.R;
import com.example.myapplication2.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Project> projects;

    private static final int TYPE_NORMAL = 1;

    private static final int TYPE_FOOTER = 2;

    // 加载状态描述
    private int loadState = 2;
    // 正在加载
    public static final int LOADING = 1;
    // 加载完成
    public static final int LOADING_COMPLETE = 2;
    // 加载到底
    public static final int LOADING_END = 3;

    public void addNewDataForComponent(List<Project> data) {
        if(projects == null) {
            synchronized (this) {
                if(projects == null) {
                    projects = new ArrayList<>();
                }
            }
        }
        projects.addAll(data);
    }

    public void setLoadState(int state) {
        loadState = state;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        }else{
            return TYPE_NORMAL;
        }

        //return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_layout,parent,false);
            return new Footer(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_component,parent,false);
            return new NormalCard(view);
        }
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof NormalCard) {
            NormalCard normalCard = (NormalCard)holder;
            normalCard.title.setText(projects.get(position).getGroupName());
            normalCard.contains.setText(projects.get(position).getGroupIntroduction());

        }else if (holder instanceof Footer) {
            Footer footViewHolder = (Footer) holder;
            switch (loadState) {
                case LOADING: // 正在加载
                    footViewHolder.progressBar.setVisibility(View.VISIBLE);
                    footViewHolder.description.setVisibility(View.VISIBLE);
                    //footViewHolder.llEnd.setVisibility(View.GONE);
                    break;

                case LOADING_COMPLETE: // 加载完成
                    footViewHolder.progressBar.setVisibility(View.INVISIBLE);
                    footViewHolder.description.setVisibility(View.INVISIBLE);
                    //footViewHolder.llEnd.setVisibility(View.GONE);
                    break;

                case LOADING_END: // 加载到底
                    footViewHolder.progressBar.setVisibility(View.GONE);
                    footViewHolder.description.setVisibility(View.GONE);
                    //footViewHolder.llEnd.setVisibility(View.VISIBLE);
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if(projects == null) {
            return 0;
        }
        return projects.size() + 1;
    }

    class NormalCard extends RecyclerView.ViewHolder {
        TextView title;
        TextView contains;
        public NormalCard(@NonNull View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.project_title);
            contains = (TextView)itemView.findViewById(R.id.project_description);
        }
    }

    class Footer extends RecyclerView.ViewHolder {
        TextView description;

        ProgressBar progressBar;

        public Footer(@NonNull View itemView) {
            super(itemView);
            progressBar = (ProgressBar)itemView.findViewById(R.id.progress_bar);
            description = (TextView)itemView.findViewById(R.id.load_description);
        }
    }
}
