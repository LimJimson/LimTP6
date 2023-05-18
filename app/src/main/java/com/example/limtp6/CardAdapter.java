package com.example.limtp6;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import java.util.List;

public class CardAdapter extends BaseAdapter {
        private List<Card> cards;

        public CardAdapter(List<Card> cards) {
            this.cards = cards;
        }
        @Override
        public int getCount() {
            return cards.size();
        }
        @Override
        public Object getItem(int position) {
            return cards.get(position);
        }
        @Override
        public long getItemId(int position) {
            return cards.get(position).getId();
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(parent.getContext());
                imageView.setLayoutParams(new GridView.LayoutParams(150, 180));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            } else {
                imageView = (ImageView) convertView;
            }
            Card card = cards.get(position);
            if (card.isFaceUp()) {
                imageView.setImageResource(card.getImage());
            } else {
                imageView.setImageResource(R.drawable.card_back);
            }
            return imageView;
        }
}

