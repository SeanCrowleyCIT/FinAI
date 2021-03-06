package ProcessBuilder;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UTFDataFormatException;
import java.lang.ProcessBuilder;
import java.lang.Process;
import java.net.URI;
import java.net.HttpURLConnection;
import java.net.HttpCookie;

import android.widget.EditText;
import java.net.*;
import com.example.finai.R;

import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;

import java.lang.CharSequence;

import static java.net.Proxy.Type.HTTP;

public class AppProcessBuilder {
/*
    Process p = new ProcessBuilder("myCommand", "myArg").start();

    public AppProcessBuilder() throws IOException {
    }

    // some code here that reads in username and password from textbox



    String command = "curl -X GET http://localhost:5000/newUser -d \"username = myemail@gmail.com\" -d \"password=keanuisbreathtaking\" -X post –v";
    ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));


    Process process = processBuilder.start();
    InputStream inputStream = process.getInputStream();
    int exitCode = process.exitValue();

    //processBuilder.command(new String[]{"curl", "-X", "GET", "https://postman-echo.com?foo=bar"});

    //process.destroy();
*/

    //DB Parser (curl)
    public static String getRequest(String curl) {
        StringBuffer stringBuffer = new StringBuffer("");
        BufferedReader bufferedReader = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet();

            URI uri = new URI(curl);
            httpGet.setURI(uri);
            httpGet.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials("user", "password"),
                    "UTF_8", false));

            HttpResponse httpResponse = httpClient.execute(httpGet);
            InputStream inputStream = httpResponse.getEntity().getContent();
            bufferedReader = new BufferedReader(new InputStreamReader(
                    inputStream));

            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                stringBuffer.append(readLine);
                stringBuffer.append("\n");
                readLine = bufferedReader.readLine();
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    // TODO: handle exception
                }
            }
        }
        return stringBuffer.toString();
    }
}
