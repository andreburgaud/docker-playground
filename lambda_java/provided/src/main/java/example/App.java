package example;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class NameAge {
    private final String name;
    private final int age;

    public NameAge(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " is " + age + " years old!";
    }
}

public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * AWS Lambda function handler
     * @param input contains the JSON payload from the API Gateway proxy (assuming that the trigger is an HTTP or REST
     *              API Gateway. The payload intended for the handler is contain in the JSON body field, serialized as
     *              a string. It may need to be deserialized into JSON to access the payload fields.
     * @param context
     * @return
     */
    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {

        String inBody = input.getBody();
        NameAge nameAge = gson.fromJson(inBody, NameAge.class);


        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);

        return response
                .withStatusCode(200)
                .withBody(nameAge.toString());
    }
}
