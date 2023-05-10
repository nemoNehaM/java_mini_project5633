package spam;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class spam {

    public static boolean checkSpam(String text) throws IOException{
        OkHttpClient client = new OkHttpClient();

        String modelEndpoint = "https://api-inference.huggingface.co/models/skandavivek2/spam-classifier";
        String apiToken = "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";
        // String text = "Hi how are you?";

        JSONObject requestData = new JSONObject();
        requestData.put("inputs", text);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());

        Request request = new Request.Builder()
                .url(modelEndpoint)
                .addHeader("Authorization", "Bearer " + apiToken)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();

        // Check the response status code
        if (response.code() != 200) {
            System.err.println("Request failed with status code " + response.code());
            System.err.println("Response body: " + responseJson);
            return false ;
        }

        try {
            JSONArray responseArray = new JSONArray(responseJson);
            JSONArray logits = responseArray.getJSONArray(0);
            // double hamProbability;
            // double spamProbability;
        
            // The logits array contains the predicted probabilities for each class.
            // In this case, there are two classes: ham (not spam) and spam.
            // double spamProbability = logits.getJSONObject(0).getDouble("score");
            // double hamProbability = logits.getJSONObject(1).getDouble("score");
            if((logits.getJSONObject(0).getString("label")).equals("HAM")){
                // String hamProbability = logits.getJSONObject(0).getString("score");
                // System.out.println("The text \"" + text + "\" is " + "ham");
                return true;
            }
            if((logits.getJSONObject(0).getString("label")).equals("SPAM")){
                // String hamProbability = logits.getJSONObject(0).getString("score");
                return false;
            }
            // }
            // if((logits.getJSONObject(0).getString("label")).equals("SPAM")){
            //     spamProbability = logits.getJSONObject(0).getDouble("score");
            // }
            // System.out.println(hamProbability);
            // System.out.println(spamProbability);
            // if(hamProbability>spamProbability){
            //     System.out.println("The text \"" + text + "\" is " + "ham");
            // }
            // if(spamProbability>hamProbability){
            //     System.out.println("The text \"" + text + "\" is " + "spam");
            // }

        
            // We can classify the text as spam if the spam probability is greater than the ham probability.
            // boolean isSpam = spamProbability < hamProbability;
            // System.out.println("The text \"" + text + "\" is " + (isSpam ? "spam" : "not spam"));
        } catch (JSONException e) {
            System.err.println("Failed to parse JSON response: " + e.getMessage());
            System.err.println("Response body: " + responseJson);
        }
        return false;
        
    }
    
    public static void main(String[] args) throws IOException {
       
        
    }
}