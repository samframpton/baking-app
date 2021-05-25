package sam.frampton.bakingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private final Context mContext;
    private final OnRecipeSelectedListener mOnRecipeSelectedListener;
    private List<Recipe> mRecipes;

    public RecipeAdapter(Context context, OnRecipeSelectedListener onRecipeSelectedListener) {
        mContext = context;
        mOnRecipeSelectedListener = onRecipeSelectedListener;
        mRecipes = Collections.emptyList();
    }

    public void setRecipes(List<Recipe> recipes) {
        mRecipes = recipes;
        notifyItemRangeChanged(0, recipes.size());
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_recipe, parent, false);
        return new RecipeViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = mRecipes.get(position);
        String imageUrl = recipe.getImage().isEmpty()?
                mContext.getString(R.string.recipe_image_default_url) : recipe.getImage();
        Glide.with(mContext).load(imageUrl).into(holder.getRecipeImageView());
        holder.getNameTextView().setText(recipe.getName());
        holder.getServingsTextView().setText(mContext.getString(R.string.recipe_servings_label,
                recipe.getServings()));
        holder.getIngredientsTextView().setText(mContext.getString(
                R.string.recipe_ingredients_label, String.valueOf(recipe.getIngredients().size())));
        holder.getStepsTextView().setText(mContext.getString(R.string.recipe_steps_label,
                String.valueOf(recipe.getSteps().size())));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public final class RecipeViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private final ImageView mRecipeImageView;
        private final TextView mNameTextView;
        private final TextView mServingsTextView;
        private final TextView mIngredientsTextView;
        private final TextView mStepsTextView;

        public RecipeViewHolder(@NonNull CardView cardView) {
            super(cardView);
            cardView.setOnClickListener(this);
            mRecipeImageView = cardView.findViewById(R.id.iv_recipe_image);
            mNameTextView = cardView.findViewById(R.id.tv_recipe_name);
            mServingsTextView = cardView.findViewById(R.id.tv_recipe_servings);
            mIngredientsTextView = cardView.findViewById(R.id.tv_recipe_ingredients);
            mStepsTextView = cardView.findViewById(R.id.tv_recipe_steps);
        }

        public ImageView getRecipeImageView () {
            return mRecipeImageView;
        }

        public TextView getNameTextView() {
            return mNameTextView;
        }

        public TextView getServingsTextView() {
            return mServingsTextView;
        }

        public TextView getIngredientsTextView() {
            return mIngredientsTextView;
        }

        public TextView getStepsTextView() {
            return mStepsTextView;
        }

        @Override
        public void onClick(View v) {
            mOnRecipeSelectedListener.onRecipeSelected(mRecipes.get(getAdapterPosition()));
        }
    }

    interface OnRecipeSelectedListener {
        void onRecipeSelected(Recipe recipe);
    }
}
