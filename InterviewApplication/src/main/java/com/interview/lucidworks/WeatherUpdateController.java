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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WeatherUpdateController {
    @Autowired
    private AccountService accountService;

    @Retryable(value = {IOException.class, JSONException.class},
            maxAttempts = 5,
            backoff = @Backoff(delay= 1000,
                    maxDelay = 5000, multiplier = 3.1))
    @RequestMapping(value = "/weather", method = RequestMethod.GET, produces = "application/json")
    public Map<String, String> home(HttpServletRequest request, HttpServletResponse response,@RequestBody String city) throws IOException, ForecastException {

        Map<String, String > map = new HashMap<>();

        String basicAuthHeaders = request.getHeader("Authorization");
        String[] credentials = null;

        if (basicAuthHeaders != null && basicAuthHeaders.startsWith("Basic")) {
            String tok = basicAuthHeaders.substring("Basic ".length()).trim();
            credentials = TokenDecoder.decodeToken(tok);
            User currentUser = accountService.findAccount(credentials[0]);
            //System.out.println(currentUser.getEmail() + "   " + credentials[1] + "   " + currentUser.getPassword());
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
                System.out.println(json);
                JSONObject current= (JSONObject)json.get("currently");
                String time = current.get("time").toString();
                String temp = current.get("temprature").toString();
                System.out.println(time+ ""+ temp);
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
    }

