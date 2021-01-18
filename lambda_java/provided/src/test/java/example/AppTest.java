package example;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class AppTest {

  @Test
  public void successfulResponse() {
    App app = new App();
    APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent()
            .withBody("{ \"name\": \"jon\", \"age\": 10 }");
    APIGatewayProxyResponseEvent result = app.handleRequest(request, null);
    assertEquals(result.getStatusCode().intValue(), 200);
    assertEquals(result.getHeaders().get("Content-Type"), "application/json");
    String content = result.getBody();
    assertNotNull(content);
    assertEquals("jon is 10 years old!", content);
  }
}
