package dk.welzel.pinkodehusker;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CardViewHolder> {

    class CardViewHolder extends RecyclerView.ViewHolder {
        private final TextView cardName;
        private final TextView cardStatus;

        private CardViewHolder(View itemView) {
            super(itemView);
            cardName = itemView.findViewById(R.id.card_name);
            cardStatus = itemView.findViewById(R.id.card_status);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, Show_PIN_Card.class);
                    context.startActivity(intent);
                }
            });

            itemView.setOnCreateContextMenuListener(mOnCreateContextMenuListener);
        }

        private final View.OnCreateContextMenuListener mOnCreateContextMenuListener = new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                if (mCards != null) {
                    MenuItem deleteItem = contextMenu.add("Delete");
                    MenuItem designItem = contextMenu.add("Change card design");
                    deleteItem.setOnMenuItemClickListener(mOnDeleteActionClickListener);
                    designItem.setOnMenuItemClickListener(mOnDesignActionClickListener);
                }
            }
        };

        private final MenuItem.OnMenuItemClickListener mOnDeleteActionClickListener = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                cardStatus.setText("Status: Deleted");

                return false;
            }
        };

        private final MenuItem.OnMenuItemClickListener mOnDesignActionClickListener = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                cardStatus.setText("Status: Design changed");
                return false;
            }
        };
    }

    private final LayoutInflater mInflater;
    private List<PIN_Card> mCards; // Cached copy of cards

    CardListAdapter(Context context) { mInflater =  LayoutInflater.from(context); }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, viewGroup, false);
        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        if (mCards != null) {
            PIN_Card current = mCards.get(position);
            holder.cardName.setText(current.getName());
            holder.cardStatus.setText(current.getStatus());
        } else {
            // Covers the case of data not being ready yet.
            holder.cardName.setText(R.string.no_card);
            holder.cardStatus.setText(R.string.no_card);
        }
    }

    void setCards(List<PIN_Card> cards) {
        mCards = cards;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mCards != null)
            return mCards.size();
        else return 0;
    }
}
