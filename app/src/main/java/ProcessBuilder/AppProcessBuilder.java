package ProcessBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ProcessBuilder;
import java.lang.Process;

public class AppProcessBuilder {

    Process p = new ProcessBuilder("myCommand", "myArg").start();

    public AppProcessBuilder() throws IOException {
    }

    String command = "curl -X GET http://localhost:5000/newUser -d \"username = myemail@gmail.com\" -d \"password=keanuisbreathtaking\" -X post –v";
    ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));


    Process process = processBuilder.start();
    InputStream inputStream = process.getInputStream();
    int exitCode = process.exitValue();

    //processBuilder.command(new String[]{"curl", "-X", "GET", "https://postman-echo.com?foo=bar"});

    //process.destroy();

}
