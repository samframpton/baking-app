package sam.frampton.bakingapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {

    @SerializedName("id")
    private final int mId;
    @SerializedName("name")
    private final String mName;
    @SerializedName("ingredients")
    private final List<Ingredient> mIngredients;
    @SerializedName("steps")
    private final List<Step> mSteps;
    @SerializedName("servings")
    private final String mServings;
    @SerializedName("image")
    private final String mImage;

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    private Recipe(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mIngredients = new ArrayList<>();
        in.readList(mIngredients, Ingredient.class.getClassLoader());
        mSteps = new ArrayList<>();
        in.readList(mSteps, Step.class.getClassLoader());
        mServings = in.readString();
        mImage = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeList(mIngredients);
        dest.writeList(mSteps);
        dest.writeString(mServings);
        dest.writeString(mImage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public List<Ingredient> getIngredients() {
        return mIngredients;
    }

    public List<Step> getSteps() {
        return mSteps;
    }

    public String getServings() {
        return mServings;
    }

    public String getImage() {
        return mImage;
    }
}
