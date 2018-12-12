package com.udacity.sandwichclub.utils;

import android.text.TextUtils;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// NOTE - I think I only need to parse each item here, not iterate through items... not sure,
// maybe I do need to iterate. Just seems like array is accessed already in DetailActivity.

/**
 * Helper methods related to requesting and receiving sandwich data
 * from the Strings.xml file.
 */

public class JsonUtils {

    /**
     * Tag for the log messages
     */

    private static final String LOG_TAG = JsonUtils.class.getSimpleName();
    public static Sandwich sandwich;

    /**
     * Create a private constructor because no one should ever create a {@link JsonUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */

    private JsonUtils() {

    }

    /**
     * Return a list of {@link Sandwich} objects that has been built up from
     * <p>
     * parsing the given JSON response.
     */

    public static Sandwich parseSandwichJson(String sandwichItemJSON) {

        // If the JSON string is empty or null, then return early.

        if (TextUtils.isEmpty(sandwichItemJSON)) {

            return null;

        }

        // Try to parse the JSON response string. If there's a problem with the way the JSON

        // is formatted, a JSONException exception object will be thrown.

        // Catch the exception so the app doesn't crash, and print the error message to the logs.

        try {

            // Create a JSONObject from the JSON response string

            JSONObject currentSandwich = new JSONObject(sandwichItemJSON);

            // Extract the value for the key called "name"

            String mainName = currentSandwich.getString("name");

            // Extract the value for the key called "alsoKnownAs"

            JSONArray alsoKnownAsJsonArray = currentSandwich.getJSONObject("").getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
                alsoKnownAs.add(alsoKnownAsJsonArray.getString(i));
            }

            // Extract the value for the key called "placeOfOrigin"

            String placeOfOrigin = currentSandwich.getString("placeOfOrigin");

            // Extract the value for the key called "description"

            String description = currentSandwich.getString("description");

            // Extract the value for the key called "image"

            String image = currentSandwich.getString("image");

            // Extract the value for the key called "ingredients"

//            String ingredients = currentSandwich.getJSONArray("ingredients").toString();
//            Log.v (LOG_TAG, ingredients);

            JSONArray ingredientsJsonArray = currentSandwich.getJSONObject("").getJSONArray("alsoKnownAs");
            List<String> ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientsJsonArray.length(); i++) {
                ingredients.add(alsoKnownAsJsonArray.getString(i));
            }

            // Create a new {@link Sandwich} object with the magnitude, location, time,

            // and url from the JSON response.

            sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

            return sandwich;

        } catch (JSONException e) {

            // If an error is thrown when executing any of the above statements in the "try" block,

            // catch the exception here, so the app doesn't crash. Print a log message

            // with the message from the exception.

            Log.e("QueryUtils", "Problem parsing the sandwich JSON results", e);

        }

        return sandwich;

    }

}

