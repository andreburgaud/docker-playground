package main

import (
    "encoding/json"
    "fmt"
    "net/http"

    "github.com/aws/aws-lambda-go/events"
    "github.com/aws/aws-lambda-go/lambda"
)

type RequestBody struct {
    Name string `json:"name"`
    Age  int    `json:"age"`
}

type ResponseBody struct {
    Message string `json:"message"`
}

func HandleResponse(req events.APIGatewayProxyRequest) (events.APIGatewayProxyResponse, error) {
    var reqBody RequestBody

    //fmt.Printf("RequestBody: %v\n", req.Body)

    err := json.Unmarshal([]byte(req.Body), &reqBody)
    if err != nil {
        fmt.Println(err)
        return events.APIGatewayProxyResponse{
            StatusCode: http.StatusUnprocessableEntity,
            Body:       http.StatusText(http.StatusUnprocessableEntity),
        }, nil
    }

    respBody := ResponseBody{
        Message: fmt.Sprintf("%s is %d years old!", reqBody.Name, reqBody.Age),
    }

    js, err := json.Marshal(respBody)
    if err != nil {
        return events.APIGatewayProxyResponse{
            StatusCode: http.StatusInternalServerError,
            Body:       http.StatusText(http.StatusInternalServerError),
        }, nil
    }

    return events.APIGatewayProxyResponse{
        StatusCode: http.StatusOK,
        Body:       string(js),
    }, nil
}

func main() {
    lambda.Start(HandleResponse)
}
