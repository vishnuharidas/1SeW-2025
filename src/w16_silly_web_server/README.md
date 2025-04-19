# A Silly Simple Web Server

I decided to vibe-code (Copilot on IntelliJ IDEA) a simple HTTP server for this week's solution. The goal was simple: to
recreate the feeling of working with raw servers, just to bring back the nostalgia from my college days from the
mid-2000s. In those days, I used to explore HTTP and SMTP protocols by building simple servers in C and C++. This Kotlin
version is a way to revisit that era, but with modern language syntax.

The server runs on port 8080 and handles basic HTTP requests. Its main functionality is to return the current time in a
user-specified format using the `SimpleDateFormat` pattern via a query parameter.

## Example usage

Request:

```text
GET /time?format=yyyy-MM-dd HH:mm:ss
```

Response:

```json
{
  "time": "2025-04-19 16:20:00",
  "format": "yyyy-MM-dd HH:mm:ss"
}
```

If no format is provided or an unknown route is accessed, the server responds with a JSON message describing how to use
the `/time` endpoint.

## No Coroutines/Threads were Used

I intentionally avoided coroutines and modern frameworks. It uses classic blocking sockets and manual HTTP parsing to
keep the spirit of early server programming. The idea was to keep things minimal, low-level, and nostalgic.

## Notes

* This is not meant to be a production server.
* It does not handle concurrent connections or errors beyond the basics.
* It's just a personal coding exercise and a way to relive how things used to be built, line by line.