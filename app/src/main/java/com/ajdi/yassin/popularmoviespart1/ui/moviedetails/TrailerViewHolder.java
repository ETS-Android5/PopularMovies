package com.ajdi.yassin.popularmoviespart1.ui.moviedetails;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajdi.yassin.popularmoviespart1.R;
import com.ajdi.yassin.popularmoviespart1.data.model.Trailer;
import com.ajdi.yassin.popularmoviespart1.databinding.ItemTrailerBinding;
import com.ajdi.yassin.popularmoviespart1.utils.GlideApp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Yassin Ajdi.
 */
public class TrailerViewHolder extends RecyclerView.ViewHolder {

    private ItemTrailerBinding binding;

    private Context context;

    public TrailerViewHolder(@NonNull ItemTrailerBinding binding, Context context) {
        super(binding.getRoot());

        this.binding = binding;
        this.context = context;
    }

    public void bindTo(final Trailer trailer) {
        String thumbnail =
                "https://img.youtube.com/vi/" + trailer.getKey() + "/hqdefault.jpg";
        GlideApp.with(context)
                .load(thumbnail)
                .placeholder(R.color.md_grey_200)
                .into(binding.imageTrailer);

        binding.trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("vnd.youtube:" + trailer.getKey()));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" + trailer.getKey()));
                try {
                    context.startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    context.startActivity(webIntent);
                }
            }
        });

        binding.executePendingBindings();
    }

    static TrailerViewHolder create(ViewGroup parent) {
        // Inflate
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Create the binding
        ItemTrailerBinding binding =
                ItemTrailerBinding.inflate(layoutInflater, parent, false);
        return new TrailerViewHolder(binding, parent.getContext());
    }
}
