package com.interview.lucidworks;

import com.interview.lucidworks.Entity.User;
import com.interview.lucidworks.Security.TokenDecoder;
import com.interview.lucidworks.Service.AccountService;
import com.interview.lucidworks.Util.PasswordUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.plogitech.darksky.forecast.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@RestController
public class WeatherUpdateController {
    @Autowired
    private AccountService accountService;

    static final int DELAY=1000;
    static final int MAXDELAY=15000;
    static  int MULTIPLIER=3;


    @Retryable(value = {IOException.class, JSONException.class},
            maxAttemptsExpression = "#{${my.app.maxAttempts}}",
            backoff = @Backoff(delayExpression = "#{${my.app.backOffDelay}}",
                    maxDelayExpression = "#{${my.app.maxDelay}}", multiplierExpression = "#{${my.app.multiplier}}"))
    @RequestMapping(value = "/weather", method = RequestMethod.GET, produces = "application/json")
    public Map<String, String> home(HttpServletRequest request, HttpServletResponse response) throws IOException, ForecastException {

        Map<String, String > map = new HashMap<>();

        String basicAuthHeaders = request.getHeader("Authorization");
        String[] credentials = null;

        if (basicAuthHeaders != null && basicAuthHeaders.startsWith("Basic")) {
            String tok = basicAuthHeaders.substring("Basic ".length()).trim();
            credentials = TokenDecoder.decodeToken(tok);
            User currentUser = accountService.findAccount(credentials[0]);
            if(currentUser ==null){
                map = new HashMap<>();
                map.put("Error", "Invalid User!");
                response.setStatus(400);
                return map;
            }
            PasswordUtil.checkPass(credentials[1], currentUser.getPassword());
            if (currentUser != null && currentUser.getEmail().equalsIgnoreCase(credentials[0]) && PasswordUtil.verifyUserPassword(credentials[1], currentUser.getPassword())) {
                ForecastRequest requestWeather = new ForecastRequestBuilder()
                        .key(new APIKey("43a2391960ce6f27dc01dc5ce90512e1"))
                        .time(Instant.now().minus(5, ChronoUnit.DAYS))
                        .language(ForecastRequestBuilder.Language.de)
                        .units(ForecastRequestBuilder.Units.us)
                        .exclude(ForecastRequestBuilder.Block.minutely)
                        .extendHourly()
                        .location(new GeoCoordinates(new Longitude(13.377704), new Latitude(52.516275))).build();

                DarkSkyClient client = new DarkSkyClient();
                String forecast = client.forecastJsonString(requestWeather);
                JSONObject json = new JSONObject(forecast);
                //System.out.println(json);
                System.out.println("RETRYING TO FETCH CORRECT DATA");
                JSONObject current= (JSONObject)json.get("currently");
                String time = current.get("time").toString();
                String temp = current.get("temprature").toString();
               // System.out.println(time+ ""+ temp);
                map.put("Weather:",forecast);

                return map;
            }
        }
        else {
            map = new HashMap<>();
            map.put("Error", "You are currently not logged in!");
            response.setStatus(401);
            return map;
        }
        return null;
    }
    @Recover
    public Map<String,String> recover(IOException e){
        Map<String,String> map = new HashMap<>();
        map.put("Error:  ","Could not reach remote website");
        return null;
    }

    @Recover
    public Map<String,String> recoverJSON(JSONException e){
        Map<String,String> map = new HashMap<>();
        map.put("Error:  ","Some Exception Occured");
        return map;
    }

    @Retryable(value = {IOException.class, JSONException.class},
    maxAttemptsExpression = "#{${my.app.maxAttempts}}",
    backoff = @Backoff(delayExpression = "#{${my.app.backOffDelay}}",
            maxDelayExpression = "#{${my.app.maxDelay}}", multiplierExpression = "#{${my.app.multiplier}}"))
    @RequestMapping(value = "/weatherC", method = RequestMethod.GET, produces = "application/json")
    public Map<String, String> homeCorrect(HttpServletRequest request, HttpServletResponse response) throws IOException, ForecastException {

        Map<String, String > map = new HashMap<>();

        String basicAuthHeaders = request.getHeader("Authorization");
        String[] credentials = null;

        if (basicAuthHeaders != null && basicAuthHeaders.startsWith("Basic")) {
            String tok = basicAuthHeaders.substring("Basic ".length()).trim();
            credentials = TokenDecoder.decodeToken(tok);
            User currentUser = accountService.findAccount(credentials[0]);
            if(currentUser ==null){
                map = new HashMap<>();
                map.put("Error", "Invalid User!");
                response.setStatus(400);
                return map;
            }
            PasswordUtil.checkPass(credentials[1], currentUser.getPassword());
            if (currentUser != null && currentUser.getEmail().equalsIgnoreCase(credentials[0]) && PasswordUtil.verifyUserPassword(credentials[1], currentUser.getPassword())) {
                ForecastRequest requestWeather = new ForecastRequestBuilder()
                        .key(new APIKey("43a2391960ce6f27dc01dc5ce90512e1"))
                        .time(Instant.now().minus(5, ChronoUnit.DAYS))
                        .language(ForecastRequestBuilder.Language.en)
                        .units(ForecastRequestBuilder.Units.us)
                        .exclude(ForecastRequestBuilder.Block.minutely)
                        .exclude(ForecastRequestBuilder.Block.hourly)
                        .exclude(ForecastRequestBuilder.Block.daily)
                        .exclude(ForecastRequestBuilder.Block.flags)
                        .exclude(ForecastRequestBuilder.Block.alerts)

                        .location(new GeoCoordinates(new Longitude(-71.0589), new Latitude(42.3601))).build();
                        //42.3601° N, 71.0589° W

                DarkSkyClient client = new DarkSkyClient();
                String forecast = client.forecastJsonString(requestWeather);
                map.put("Weather:",forecast);
                return map;
            }
        }
        else {
            map = new HashMap<>();
            map.put("Error", "You are currently not logged in!");
            response.setStatus(401);
            return map;
        }
        return null;
    }
    @Recover
    public Map<String,String> recoverCorrect(IOException e){
        Map<String,String> map = new HashMap<>();
        map.put("Error:  ","Could not reach remote website");
        return null;
    }

    @Recover
    public Map<String,String> recoverJSONCorrect(JSONException e){
        Map<String,String> map = new HashMap<>();
        map.put("Error:  ","JSONException Occured");
        return map;
    }
    
    }

