package innopolis.innopass.views.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import innopolis.innopass.R;
import innopolis.innopass.models.entities.PassCard;

/**
 * Created by davlet on 7/6/17.
 */

public class CardRecyclerViewAdapter extends RecyclerView.Adapter<CardRecyclerViewAdapter.CardHolder> {
    private List<PassCard> cardList;
    private List<PassCard> cardListCopy;
    private LayoutInflater layoutInflater;
    OnCardClickListener onCardClickListener;

    public CardRecyclerViewAdapter(Context context, List<PassCard> cardList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.cardList = cardList;
        cardListCopy = new ArrayList<>();
        this.cardListCopy.addAll(cardList);
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_card, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        holder.id.setText(String.format("0000 0000 0000 000%s", cardList.get(position).getId().toString()));
//        holder.id.setText(cardList.get(position).getId().toString());
        holder.isValid.setText(cardList.get(position).isValid() ? "ACTIVATED" : "DISABLED");
        holder.name.setText(cardList.get(position).getUser().getFirstName());
        holder.surname.setText(cardList.get(position).getUser().getSurname());
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public void setOnCardClickListener(OnCardClickListener onCardClickListener) {
        this.onCardClickListener = onCardClickListener;
    }

    public PassCard getCardByPosition(int position){
        return cardList.get(position);
    }

    public void filter(CharSequence s) {
        s = s.toString().toLowerCase();
        cardList.clear();
        for (PassCard card: cardListCopy){
            if (card.getUser().getFirstName().toLowerCase().contains(s) ||
                    card.getUser().getSurname().toLowerCase().contains(s))
                cardList.add(card);
        }
        notifyDataSetChanged();
    }

    class CardHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.cardNumber) TextView id;
        @BindView(R.id.cardHolderName) TextView name;
        @BindView(R.id.cardHolderSurname) TextView surname;
        @BindView(R.id.cardValidity) TextView isValid;
        @BindView(R.id.cardViewCard) CardView cardView;

        public CardHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cardView.setOnClickListener(onClickListener);
        }

        @Override
        public void onClick(View v) {

        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCardClickListener.onCardClick(v, getAdapterPosition());
            }
        };
    }
}
