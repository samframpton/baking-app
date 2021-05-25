package sam.frampton.bakingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE = "RECIPE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Recipe recipe = intent.getParcelableExtra(EXTRA_RECIPE);
    }
}
