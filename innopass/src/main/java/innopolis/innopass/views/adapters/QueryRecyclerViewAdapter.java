package innopolis.innopass.views.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import innopolis.innopass.R;
import innopolis.innopass.models.entities.QueryCard;

/**
 * Created by davlet on 7/6/17.
 */

public class QueryRecyclerViewAdapter extends RecyclerView.Adapter<QueryRecyclerViewAdapter.QueryHolder> {
    List<QueryCard> queryCardList;
    private LayoutInflater layoutInflater;
    OnQueryClickListener onItemClickListener;

    public QueryRecyclerViewAdapter(Context context, List<QueryCard> queryCardList) {
        this.queryCardList = queryCardList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public QueryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_query, parent, false);
        return new QueryHolder(view);
    }

    @Override
    public void onBindViewHolder(QueryHolder holder, int position) {
        if (getQueryByPosition(position).isStatus()){
    //        holder.imageView.setImageResource(queryCardList.get(position).getUser().getPhotoId());
            holder.textName.setText(queryCardList.get(position).getUser().getFirstName());
            holder.textSurname.setText(queryCardList.get(position).getUser().getSurname());
            holder.textMessage.setText(queryCardList.get(position).getQueryMessage());
            holder.textPriority.setText(queryCardList.get(position).getPriority().toString());
        }
    }

    @Override
    public int getItemCount() {
        return queryCardList.size();
    }

    public void setQueryClickListener(OnQueryClickListener queryClickListener){
        this.onItemClickListener = queryClickListener;
    }

    public QueryCard getQueryByPosition(int position){
        return queryCardList.get(position);
    }

    public void removeQueryByPosition(int position){
        queryCardList.remove(position);
        notifyDataSetChanged();
    }

    class QueryHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//        @BindView(R.id.photo) CircleImageView imageView;
        @BindView(R.id.textStudentName) TextView textName;
        @BindView(R.id.textStudentSurname) TextView textSurname;
        @BindView(R.id.textMessage) TextView textMessage;
        @BindView(R.id.textPriority) TextView textPriority;
        @BindView(R.id.cardViewQuery) CardView cardView;

        public QueryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cardView.setOnClickListener(onClickListener);
        }

        @Override
        public void onClick(View v) {
//            onItemClickListener.onQueryClick(v, getAdapterPosition());
        }

        private View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onItemClickListener.onQueryClick(v, getAdapterPosition());
            }
        };
    }
}
