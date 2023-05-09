### GRPC Sample Server And Client

- This is a simple API that takes a user number say 100 and generate a random number between 0 and the provided number.
- If the random number is both divisible by 2 and 5, the API responds to user letting them know how lucky they are or otherwise how unlucky they are.

## How to test
- Build the application by running `./gradlew build` or using your IDE.
- Run `org.grpc.sample.service.GuessNumberMain`. This will start our GRPC server on port 50051. Feel free to change it.
- To test it open `org.grpc.sample.client.GuessNumberClient` and look at main method. You can run it using the existing input or update the input to another guessed number and run the class.
- You will receive a response depending on what random number was generated.