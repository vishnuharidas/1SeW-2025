package w16_silly_web_server

import java.io.BufferedReader
import java.net.ServerSocket
import java.net.Socket
import java.net.URLDecoder
import java.text.SimpleDateFormat

fun main() {

    val serverSocket = ServerSocket(8080)

    // Get the headers from the input stream
    fun getHeaders(input: BufferedReader): StringBuilder {
        val requestHeaders = StringBuilder()
        while (true) {
            val line = input.readLine() ?: break
            if (line.isEmpty()) break // End of headers
            requestHeaders.appendLine(line)
        }
        return requestHeaders
    }

    fun extractUrlFromHttpRequest(request: String): String {
        val lines = request.split("\r\n")
        if (lines.isNotEmpty()) {
            val firstLine = lines[0]
            val parts = firstLine.split(" ")
            if (parts.size > 1) {
                return parts[1] // Return the URL part
            }
        }
        return ""
    }

    fun extractParameterFromUrl(url: String, parameter: String): String? {
        val queryString = url.substringAfter("?", "")
        val params = queryString.split("&")
        for (param in params) {
            val keyValue = param.split("=")
            if (keyValue.size == 2 && keyValue[0] == parameter) {
                return URLDecoder.decode(keyValue[1], "UTF-8")
            }
        }
        return null
    }


    fun handleTimeRequest(url: String): String {

        val timeFormat = extractParameterFromUrl(url, "format")
            ?: "yyyy-MM-dd'T'HH:mm:ssXXX" // ISO 8601 format

        try {

            val formattedTime = SimpleDateFormat(timeFormat).format(System.currentTimeMillis())

            val escapedTime = formattedTime
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")

            val escapedFormat = timeFormat
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")

            return "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: application/json\r\n" +
                    "\r\n" +
                    "{\"time\": \"$escapedTime\", " +
                    "\"format\": \"$escapedFormat\"}\r\n"

        } catch (_: Exception) {

            return "HTTP/1.1 400 Bad Request\r\n" +
                    "Content-Type: application/json\r\n" +
                    "\r\n" +
                    "{\"error\": \"Invalid format\"}\r\n"
        }

    }

    fun helpText(): String {
        return "HTTP/1.1 200 OK\r\n" +
                "Content-Type: application/json\r\n" +
                "Connection: close\r\n" +
                "\r\n" +
                "{\n" +
                "  \"error\": \"No format provided\",\n" +
                "  \"message\": \"To get the current time, send a GET /time request with a 'format' query parameter. Example: /time?format=yyyy-MM-dd HH:mm:ss. See https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html for format options.\"\n" +
                "}\r\n"

    }

    fun processUrl(url: String): String {

        return when {

            url == "/" -> {
                helpText()
            }

            url == "/time" || url.startsWith("/time?") -> {
                handleTimeRequest(url)
            }

            else -> {
                helpText()
            }
        }


    }

    while (true) {

        val clientSocket: Socket = serverSocket.accept()
        println("New connection from ${clientSocket.inetAddress.hostAddress}")

        try {
            val input = clientSocket.getInputStream().bufferedReader()
            val output = clientSocket.getOutputStream().bufferedWriter()

            // Read data from the client
            val requestHeaders = getHeaders(input)
            println("Received request headers:\n$requestHeaders")

            val url = extractUrlFromHttpRequest(requestHeaders.toString())
            println("Extracted URL: $url")

            val response = processUrl(url)

            output.write(response)
            output.flush()

            // Properly close streams
            output.close()
            input.close()

        } catch (e: Exception) {
            println("Error handling client: ${e.message}")

        } finally {
            // Close the connection
            if (!clientSocket.isClosed) {
                clientSocket.close()
            }
            println("Connection closed")
        }
    }

}