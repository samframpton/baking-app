package sam.frampton.bakingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SelectRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecipeAdapter adapter = createRecipeAdapter();
        prepareRecyclerView(adapter);
        RecipesModel recipesModel = new ViewModelProvider(this).get(RecipesModel.class);
        loadRecipes(recipesModel, adapter);
    }

    private RecipeAdapter createRecipeAdapter() {
        return new RecipeAdapter(this,
                new RecipeAdapter.OnRecipeSelectedListener() {
                    @Override
                    public void onRecipeSelected(Recipe recipe) {
                        Intent intent = new Intent(SelectRecipeActivity.this,
                                RecipeDetailsActivity.class);
                        intent.putExtra(RecipeDetailsActivity.EXTRA_RECIPE, recipe);
                        startActivity(intent);
                    }
                });
    }

    private void prepareRecyclerView(RecipeAdapter adapter) {
        RecyclerView recyclerView = findViewById(R.id.rv_recipes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void loadRecipes(RecipesModel recipesModel, final RecipeAdapter adapter) {
        recipesModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                adapter.setRecipes(recipes);
            }
        });
    }
}
