package sam.frampton.bakingapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class RecipesModel extends AndroidViewModel {

    private MutableLiveData<List<Recipe>> mRecipes;

    public RecipesModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Recipe>> getRecipes() {
        if (mRecipes == null) {
            mRecipes = new MutableLiveData<>();
            loadRecipes();
        }
        return mRecipes;
    }

    private void loadRecipes() {
        new Thread() {
            @Override
            public void run() {
                mRecipes.postValue(NetworkUtils.loadRecipes(getApplication()));
            }
        }.start();
    }
}
