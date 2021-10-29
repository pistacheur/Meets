package master.project.meets;



import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {

    private static String currentGame = "";
    private static final String POST_URL = "https://expensesplit.safeml.de/cgi-bin/kvstore.py?id=playerList&pw=mykey&val=";
    private static final String POST_GAME_URL = "https://expensesplit.safeml.de/cgi-bin/kvstore.py?id=currentGame&pw=mykey&val=";
    private static final String GET_URL = "https://expensesplit.safeml.de/cgi-bin/kvstore.py?id=playerList";
    private static final String GET_GAME_URL = "https://expensesplit.safeml.de/cgi-bin/kvstore.py?id=currentGame";
    private static final String USER_AGENT = "Mozilla/5.0";

    public final Activity activity = this;
    //get response from api
    public ArrayList<String> getApiCall() throws IOException, JSONException {
        ArrayList<String> listString = new ArrayList<>();
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL url = new URL(GET_URL);

            HttpURLConnection cnx = (HttpURLConnection) url.openConnection();
            cnx.setRequestMethod("GET");
            cnx.setRequestProperty("User-Agent",USER_AGENT);
            int responseCode = cnx.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader bfr = new BufferedReader(new InputStreamReader(cnx.getInputStream()));
                String inputLine = bfr.readLine();
                StringBuffer response = new StringBuffer();
                while(inputLine != null){
                    if(inputLine.toString() != null && inputLine.toString() != "" && inputLine.toString() != "[]"){
                        response.append(inputLine.toString());
                        System.out.println("Getline =: "+inputLine);
                        listString.add(inputLine);
                    }
                    inputLine = bfr.readLine();
                }

                System.out.println(" GetResponse  :: "+response.toString());
                if(response.toString() != "[]"){
                    JSONArray jr = new JSONArray((response.toString()));
                    listString.clear();
                    for(int i=0; i< jr.length(); i++){
                        JSONObject jo = jr.getJSONObject(i);
                        System.out.println(" GetListe  :: "+String.valueOf(jo));
                        listString.add(String.valueOf(jr.getJSONObject(i)));
                    }
                } else {
                    System.out.println("List empty");
                }

                bfr.close();
            } else {
                System.out.println("GET request not worked");
            }

        }

        return listString;
    }

    //post data to api
    public int postApiCall(String data) throws IOException {
        int responseCode = 0;
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL url = new URL(POST_URL+data);

            HttpURLConnection cnx = (HttpURLConnection) url.openConnection();
            cnx.setRequestMethod("GET");
            cnx.setRequestProperty("User-Agent",USER_AGENT);
            responseCode = cnx.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                System.out.println("Data was added");
            } else {
                System.out.println("GET request not worked");
            }

        }
        return responseCode;
    }

    //post new game to the server
    public int postGameToServer(String game) throws IOException {
        int responseCode = 0;
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL url = new URL(POST_GAME_URL+game);

            HttpURLConnection cnx = (HttpURLConnection) url.openConnection();
            cnx.setRequestMethod("GET");
            cnx.setRequestProperty("User-Agent",USER_AGENT);
            responseCode = cnx.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                System.out.println("Game was added");
                postApiCall("[]");
            } else {
                System.out.println("GET request not worked");
            }

        }
        return responseCode;
    }
    //check if player already exist
    public boolean playerExist(String name, String surname, String address,String phoneNumber) throws IOException, JSONException {
        boolean response = false;
        ArrayList<String> listData = getApiCall();
        System.out.println("Current list : : "+listData);
        for(String line_render : listData){
            try {
                JSONObject jo = new JSONObject(line_render);
                if(jo.getString("name").equalsIgnoreCase(name)
                        && jo.getString("surname").equalsIgnoreCase(surname)
                        && jo.getString("address").equalsIgnoreCase(address)
                        && jo.getString("phoneNumber").equalsIgnoreCase(phoneNumber))
                    response = true;

            } catch (JSONException ex) {
                System.out.println(" Error json object  " + ex.toString());
            }
        }
        return response;

    }

    //check if Game exist and set the player form
    public String ifGameExist() throws IOException, JSONException {
        String time = "";
        String date = "";
        final TextView tv_title = (TextView) findViewById(R.id.textView);
        boolean state = true;
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL url = new URL(GET_GAME_URL);

            HttpURLConnection cnx = (HttpURLConnection) url.openConnection();
            cnx.setRequestMethod("GET");
            cnx.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = cnx.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader bfr = new BufferedReader(new InputStreamReader(cnx.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = bfr.readLine()) != null) {
                    if (inputLine != null && inputLine != "" && inputLine.length() > 0) {
                        JSONObject jo = new JSONObject(inputLine);
                        if(!jo.getString("date").equalsIgnoreCase("")
                                && !jo.getString("time").equalsIgnoreCase("")
                                && !jo.getString("address").equalsIgnoreCase("")){
                            state = true;
                            currentGame = inputLine;
                            time = jo.getString("time");
                            date = jo.getString("date");
                        }
                    }
                }
                bfr.close();
            }
        }

        final EditText edName = (EditText) findViewById(R.id.pName);
        final EditText edSurname = (EditText) findViewById(R.id.pSurname);
        final EditText edAddress = (EditText) findViewById(R.id.pAddress);
        final EditText edPhoneNumber = (EditText) findViewById(R.id.phoneNumber);
        if(state == false){
            //set fields visibility
            edName.setEnabled(false);
            edSurname.setEnabled(false);
            edAddress.setEnabled(false);
            edPhoneNumber.setEnabled(false);
            tv_title.setText("There is no game avaible now. Please try again later");
        } else {
            edName.setEnabled(true);
            edSurname.setEnabled(true);
            edAddress.setEnabled(true);
            edPhoneNumber.setEnabled(true);
            System.out.println(getCurrentNumberOfPlayer()+"  vs "+getMaxNumberOfPlayer());
            if(getCurrentNumberOfPlayer() > getMaxNumberOfPlayer())
                tv_title.setText("Welcome! \n Registrations deadline to the actuel Play is on "+date+" at "+time+" ." +
                        " Fill out the form and save the given Unique ID.\n" + " you will be Number "+ ((getCurrentNumberOfPlayer() - getMaxNumberOfPlayer()) +1) + " in the wait list");
            else
                tv_title.setText("Welcome! \n Registrations to the actuel Play is on "+date+" at "+time+" ." +
                        "\n Fill out the form and save the given Unique ID." +
                        "\n Actuel "+getCurrentNumberOfPlayer()+" /"+getMaxNumberOfPlayer()+" Player");
        }

        return currentGame;
    }

    //return max number of player for the current game
    public int getMaxNumberOfPlayer() throws IOException, JSONException {
        int max = 0;
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL url = new URL(GET_GAME_URL);

            HttpURLConnection cnx = (HttpURLConnection) url.openConnection();
            cnx.setRequestMethod("GET");
            cnx.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = cnx.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader bfr = new BufferedReader(new InputStreamReader(cnx.getInputStream()));
                String inputLine = bfr.readLine();
                StringBuffer response = new StringBuffer();
                while (inputLine != null) {
                    if (inputLine != null && inputLine != "" && inputLine.length() > 0) {
                        JSONObject jo = new JSONObject(inputLine);
                        if(!jo.getString("maxplayer").equalsIgnoreCase("")){
                            max = Integer.parseInt(jo.getString("maxplayer"));
                        }
                    }
                    inputLine = bfr.readLine();
                }
            }
        }

        return max;
    }

    //get current number of player in the current player list
    public int getCurrentNumberOfPlayer() throws IOException, JSONException {
        int count = 0;
        ArrayList<String> listPlayer = new ArrayList<>();
        listPlayer = getApiCall();
        for(String line_render : listPlayer){
            try {
                JSONObject jo = new JSONObject(line_render);
                if(!jo.getString("name").equalsIgnoreCase(""))
                    count++;

            } catch (JSONException ex) {
                System.out.println(" Error json object current number  of player  " + ex.toString());
            }
        }

        return count;
    }
    //delete player by id

    public boolean deletePlayerById(String uuid) throws IOException, JSONException {
        boolean response = false;
        boolean state = false;
        ArrayList<String> listData = new ArrayList<>();
        JSONArray jr = new JSONArray();
        listData = getApiCall();
        JSONObject jo_firstPlayerOfWaitList = new JSONObject();
        if(listData.size() > getMaxNumberOfPlayer()){
            String str_firstPlayerOfWaitList = listData.get(getMaxNumberOfPlayer() -1 );
            jo_firstPlayerOfWaitList = new JSONObject(str_firstPlayerOfWaitList);
            state = true;
        }
        try {
            StringBuilder data = new StringBuilder();
            for(String line_render : listData){
                try {
                    JSONObject jo = new JSONObject(line_render);
                    if(jo.getString("uniqId").equalsIgnoreCase(uuid)) {
                        response = true;
                        if(state == true ){
                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.SEND_SMS},101);
                            try {
                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage(jo_firstPlayerOfWaitList.getString("phoneNumber"), null, "Congratulation !! You are now in the top list.", null, null);
                                System.out.println("Message was sent ");
                            } catch (Exception ex){
                                System.out.println("Message cannot be send "+ex.toString());
                            }
                        }

                    }
                    else
                        jr.put(jo);
                    //data.append(line_render).append("\n");

                } catch (JSONException ex) {
                    System.out.println(" Error json object  " + ex.toString());
                }
            }
            //rewritte data and post to server
            postApiCall(String.valueOf(jr));
        }catch(IOException ex) {
            System.out.println(" Error Filereader  " + ex.toString());
        }
        return response;
    }

    //check if uuid already exist
    public boolean uuidExist(String id) throws IOException, JSONException {
        boolean response = false;
        ArrayList<String> listData = getApiCall();
        for(String line_render : listData){
            try {
                JSONObject jo = new JSONObject(line_render);
                if(jo.getString("uniqId").equalsIgnoreCase(id))
                    response = true;

            } catch (JSONException ex) {
                System.out.println(" Error json object  " + ex.toString());
            }
        }

        return response;
    }

    //load data from file
    public void loadList() throws IOException, JSONException {
        ListView lv = (ListView) findViewById(R.id.listViewPlayer);
        ArrayList<String> alist = new ArrayList<String>();
        ArrayList<String> listData = getApiCall();
        JSONArray jr = new JSONArray();
        for(String inputLine : listData){
            jr.put(new JSONObject(inputLine));
        }
        //here we have the parsed data into string

        int count = 1;
        for(int i = 0; i < jr.length(); i++){
            JSONObject jo = jr.getJSONObject(i);
            String playerString = count + ") Name : "+jo.getString("name") +
                    " \nSurname : "+jo.getString("surname")+" \nAddress : "+
                    jo.getString("address")+"\nPhone Number : "+jo.getString("phoneNumber");
            alist.add(playerString);
            if(count == getMaxNumberOfPlayer())
                alist.add("\n *********** Wait List ********** \n");
            count++;
        }

        final ArrayAdapter<String> adp = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,android.R.id.text1, alist);
        lv.setAdapter(adp);

    }
    // --SYNTHESIZED-CODE-SUBCLASSES-START--
    void gameAction0() {
        logCurrentState("GameAction0",currentSystemGoal,controllerState);
    }
    void gameAction1() {
        logCurrentState("GameAction1",currentSystemGoal,controllerState);
    }
    void gameAction2() {
        logCurrentState("GameAction2",currentSystemGoal,controllerState);
    }
    void gameAction3() {
        logCurrentState("GameAction3",currentSystemGoal,controllerState);
    }
    void gameAction4() {
        logCurrentState("GameAction4",currentSystemGoal,controllerState);
    }
    void gameAction5() {
        logCurrentState("GameAction5",currentSystemGoal,controllerState);
    }
    void gameAction6() {
        logCurrentState("GameAction6",currentSystemGoal,controllerState);
    }
    void gameAction7() {
        logCurrentState("GameAction7",currentSystemGoal,controllerState);
    }
    void gameAction8() {
        logCurrentState("GameAction8",currentSystemGoal,controllerState);
    }
    void gameAction9() {
        logCurrentState("GameAction9",currentSystemGoal,controllerState);
    }
    void gameAction10() {
        logCurrentState("GameAction10",currentSystemGoal,controllerState);
    }
    void gameAction11() {
        logCurrentState("GameAction11",currentSystemGoal,controllerState);
    }
    void gameAction12() {
        logCurrentState("GameAction12",currentSystemGoal,controllerState);
    }
    void gameAction13() {
        logCurrentState("GameAction13",currentSystemGoal,controllerState);
    }
    void gameAction14() {
        logCurrentState("GameAction14",currentSystemGoal,controllerState);
        // Done action
    }
    void gameAction15() {
        logCurrentState("GameAction15",currentSystemGoal,controllerState);
        final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.mainPage); c.setVisibility(View.INVISIBLE);
    }
    void gameAction16() {
        logCurrentState("GameAction16",currentSystemGoal,controllerState);
        final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.mainPage); c.setVisibility(View.VISIBLE);
    }
    void gameAction17() {
        logCurrentState("GameAction17",currentSystemGoal,controllerState);
        final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.connectPage); c.setVisibility(View.INVISIBLE);
    }
    void gameAction18() {
        logCurrentState("GameAction18",currentSystemGoal,controllerState);
        final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.connectPage); c.setVisibility(View.VISIBLE);
    }
    void gameAction19() {
        logCurrentState("GameAction19",currentSystemGoal,controllerState);
        final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.managerPage); c.setVisibility(View.INVISIBLE);
    }
    void gameAction20() {
        logCurrentState("GameAction20",currentSystemGoal,controllerState);
        final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.managerPage); c.setVisibility(View.VISIBLE);
    }
    void gameAction21() {
        logCurrentState("GameAction21",currentSystemGoal,controllerState);
        final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.playerListPage); c.setVisibility(View.INVISIBLE);
    }
    void gameAction22() {
        logCurrentState("GameAction22",currentSystemGoal,controllerState);
        final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.playerListPage); c.setVisibility(View.VISIBLE);
    }
    void gameAction23() {
        logCurrentState("GameAction23",currentSystemGoal,controllerState);
        final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.createGamePage); c.setVisibility(View.INVISIBLE);
    }
    void gameAction24() {
        logCurrentState("GameAction24",currentSystemGoal,controllerState);
        final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.createGamePage); c.setVisibility(View.VISIBLE);
    }
    void gameAction25() {
        logCurrentState("GameAction25",currentSystemGoal,controllerState);
        final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.playerRegistrationPage); c.setVisibility(View.INVISIBLE);
    }
    void gameAction26() {
        logCurrentState("GameAction26",currentSystemGoal,controllerState);
        final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.playerRegistrationPage); c.setVisibility(View.VISIBLE);
    }
    void gameAction27() {
        logCurrentState("GameAction27",currentSystemGoal,controllerState);
        final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.registrationCancelingPage); c.setVisibility(View.INVISIBLE);
    }
    void gameAction28() {
        logCurrentState("GameAction28",currentSystemGoal,controllerState);
        final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.registrationCancelingPage); c.setVisibility(View.VISIBLE);
    }
    void logCurrentState(String inputAction, int currentGoal, int controllerState) {
        String a = inputAction+" ("+Integer.toString(controllerState);
        a = a + ") with goal "+Integer.toString(currentGoal);
        Log.w("Action",a);
    }
    int currentSystemGoal = 0;
    int controllerState = 0;
    void onInputAction0() {
        logCurrentState("inputActioninit",currentSystemGoal,controllerState);
        if ((currentSystemGoal==0) && (controllerState==0)) {
            Log.w("ActionLine","0 0 0 2 3 5 7 9 11 13 0 0 1");
            controllerState = 1;
            gameAction0(); gameAction16(); gameAction17(); gameAction19();
            gameAction21(); gameAction23(); gameAction25(); gameAction27();
            gameAction14();
            return; }
        if ((currentSystemGoal==0) && (controllerState==1)) {
            Log.w("ActionLine","0 1 0 0 0 2");
            controllerState = 2;
            gameAction0(); gameAction14();
            return; }
        Log.e("Action","Failure -- Case uncovered.");
    }
    void onInputAction1() {
        logCurrentState("inputActionbutton1.click",currentSystemGoal,controllerState);
        if ((currentSystemGoal==0) && (controllerState==0)) {
            Log.w("ActionLine","0 0 1 1 4 5 7 9 11 13 0 0 2");
            controllerState = 2;
            gameAction1(); gameAction15(); gameAction18(); gameAction19();
            gameAction21(); gameAction23(); gameAction25(); gameAction27();
            gameAction14();
            return; }
        if ((currentSystemGoal==0) && (controllerState==1)) {
            Log.w("ActionLine","0 1 1 1 4 5 7 9 11 13 0 0 1");
            gameAction1(); gameAction15(); gameAction18(); gameAction19();
            gameAction21(); gameAction23(); gameAction25(); gameAction27();
            gameAction14();
            return; }
        Log.e("Action","Failure -- Case uncovered.");
    }
    void onInputAction2() {
        logCurrentState("inputActionbutton2.click",currentSystemGoal,controllerState);
        if ((currentSystemGoal==0) && (controllerState==0)) {
            Log.w("ActionLine","0 0 2 1 3 5 7 9 12 13 0 0 2");
            controllerState = 2;
            gameAction2(); gameAction15(); gameAction17(); gameAction19();
            gameAction21(); gameAction23(); gameAction26(); gameAction27();
            gameAction14();
            return; }
        if ((currentSystemGoal==0) && (controllerState==1)) {
            Log.w("ActionLine","0 1 2 1 12 13 0 0 1");
            gameAction2(); gameAction15(); gameAction26(); gameAction27();
            gameAction14();
            return; }
        Log.e("Action","Failure -- Case uncovered.");
    }
    void onInputAction3() {
        logCurrentState("inputActionconnect.click",currentSystemGoal,controllerState);
        if ((currentSystemGoal==0) && (controllerState==0)) {
            Log.w("ActionLine","0 0 3 1 3 6 7 9 11 13 0 0 2");
            controllerState = 2;
            gameAction3(); gameAction15(); gameAction17(); gameAction20();
            gameAction21(); gameAction23(); gameAction25(); gameAction27();
            gameAction14();
            return; }
        if ((currentSystemGoal==0) && (controllerState==1)) {
            Log.w("ActionLine","0 1 3 1 3 6 7 9 11 13 0 0 1");
            gameAction3(); gameAction15(); gameAction17(); gameAction20();
            gameAction21(); gameAction23(); gameAction25(); gameAction27();
            gameAction14();
            return; }
        Log.e("Action","Failure -- Case uncovered.");
    }
    void onInputAction4() {
        logCurrentState("inputActionprevious.click",currentSystemGoal,controllerState);
        if ((currentSystemGoal==0) && (controllerState==0)) {
            Log.w("ActionLine","0 0 4 2 3 5 7 9 11 13 0 0 2");
            controllerState = 2;
            gameAction4(); gameAction16(); gameAction17(); gameAction19();
            gameAction21(); gameAction23(); gameAction25(); gameAction27();
            gameAction14();
            return; }
        if ((currentSystemGoal==0) && (controllerState==1)) {
            Log.w("ActionLine","0 1 4 2 3 5 7 9 11 13 0 0 1");
            gameAction4(); gameAction16(); gameAction17(); gameAction19();
            gameAction21(); gameAction23(); gameAction25(); gameAction27();
            gameAction14();
            return; }
        Log.e("Action","Failure -- Case uncovered.");
    }
    void onInputAction5() {
        logCurrentState("inputActioncreateP.click",currentSystemGoal,controllerState);
        if ((currentSystemGoal==0) && (controllerState==0)) {
            Log.w("ActionLine","0 0 5 1 3 5 7 10 11 13 0 0 2");
            controllerState = 2;
            gameAction5(); gameAction15(); gameAction17(); gameAction19();
            gameAction21(); gameAction24(); gameAction25(); gameAction27();
            gameAction14();
            return; }
        if ((currentSystemGoal==0) && (controllerState==1)) {
            Log.w("ActionLine","0 1 5 1 3 5 7 10 11 13 0 0 1");
            gameAction5(); gameAction15(); gameAction17(); gameAction19();
            gameAction21(); gameAction24(); gameAction25(); gameAction27();
            gameAction14();
            return; }
        Log.e("Action","Failure -- Case uncovered.");
    }
    void onInputAction6() {
        logCurrentState("inputActionmodifyP.click",currentSystemGoal,controllerState);
        if ((currentSystemGoal==0) && (controllerState==0)) {
            Log.w("ActionLine","0 0 6 1 3 5 8 9 11 13 0 0 2");
            controllerState = 2;
            gameAction6(); gameAction15(); gameAction17(); gameAction19();
            gameAction22(); gameAction23(); gameAction25(); gameAction27();
            gameAction14();
            return; }
        if ((currentSystemGoal==0) && (controllerState==1)) {
            Log.w("ActionLine","0 1 6 1 3 5 8 9 11 13 0 0 1");
            gameAction6(); gameAction15(); gameAction17(); gameAction19();
            gameAction22(); gameAction23(); gameAction25(); gameAction27();
            gameAction14();
            return; }
        Log.e("Action","Failure -- Case uncovered.");
    }
    void onInputAction7() {
        logCurrentState("inputActionlogOut.click",currentSystemGoal,controllerState);
        if ((currentSystemGoal==0) && (controllerState==0)) {
            Log.w("ActionLine","0 0 7 1 4 5 7 9 11 13 0 0 2");
            controllerState = 2;
            gameAction7(); gameAction15(); gameAction18(); gameAction19();
            gameAction21(); gameAction23(); gameAction25(); gameAction27();
            gameAction14();
            return; }
        if ((currentSystemGoal==0) && (controllerState==1)) {
            Log.w("ActionLine","0 1 7 1 4 5 7 9 11 13 0 0 1");
            gameAction7(); gameAction15(); gameAction18(); gameAction19();
            gameAction21(); gameAction23(); gameAction25(); gameAction27();
            gameAction14();
            return; }
        Log.e("Action","Failure -- Case uncovered.");
    }
    void onInputAction8() {
        logCurrentState("inputActionretour.click",currentSystemGoal,controllerState);
        if ((currentSystemGoal==0) && (controllerState==0)) {
            Log.w("ActionLine","0 0 8 1 3 6 7 9 11 13 0 0 2");
            controllerState = 2;
            gameAction8(); gameAction15(); gameAction17(); gameAction20();
            gameAction21(); gameAction23(); gameAction25(); gameAction27();
            gameAction14();
            return; }
        if ((currentSystemGoal==0) && (controllerState==1)) {
            Log.w("ActionLine","0 1 8 1 3 6 7 9 11 13 0 0 1");
            gameAction8(); gameAction15(); gameAction17(); gameAction20();
            gameAction21(); gameAction23(); gameAction25(); gameAction27();
            gameAction14();
            return; }
        Log.e("Action","Failure -- Case uncovered.");
    }
    void onInputAction9() {
        logCurrentState("inputActionback1.click",currentSystemGoal,controllerState);
        if ((currentSystemGoal==0) && (controllerState==0)) {
            Log.w("ActionLine","0 0 9 1 3 6 7 9 11 13 0 0 2");
            controllerState = 2;
            gameAction9(); gameAction15(); gameAction17(); gameAction20();
            gameAction21(); gameAction23(); gameAction25(); gameAction27();
            gameAction14();
            return; }
        if ((currentSystemGoal==0) && (controllerState==1)) {
            Log.w("ActionLine","0 1 9 1 3 6 7 9 11 13 0 0 1");
            gameAction9(); gameAction15(); gameAction17(); gameAction20();
            gameAction21(); gameAction23(); gameAction25(); gameAction27();
            gameAction14();
            return; }
        Log.e("Action","Failure -- Case uncovered.");
    }
    void onInputAction10() {
        logCurrentState("inputActionback.click",currentSystemGoal,controllerState);
        if ((currentSystemGoal==0) && (controllerState==0)) {
            Log.w("ActionLine","0 0 10 1 3 6 7 9 11 13 0 0 2");
            controllerState = 2;
            gameAction10(); gameAction15(); gameAction17(); gameAction20();
            gameAction21(); gameAction23(); gameAction25(); gameAction27();
            gameAction14();
            return; }
        if ((currentSystemGoal==0) && (controllerState==1)) {
            Log.w("ActionLine","0 1 10 1 3 6 7 9 11 13 0 0 1");
            gameAction10(); gameAction15(); gameAction17(); gameAction20();
            gameAction21(); gameAction23(); gameAction25(); gameAction27();
            gameAction14();
            return; }
        Log.e("Action","Failure -- Case uncovered.");
    }
    void onInputAction11() {
        logCurrentState("inputActionRegCancel.click",currentSystemGoal,controllerState);
        if ((currentSystemGoal==0) && (controllerState==0)) {
            Log.w("ActionLine","0 0 11 1 3 5 7 9 11 14 0 0 2");
            controllerState = 2;
            gameAction11(); gameAction15(); gameAction17(); gameAction19();
            gameAction21(); gameAction23(); gameAction25(); gameAction28();
            gameAction14();
            return; }
        if ((currentSystemGoal==0) && (controllerState==1)) {
            Log.w("ActionLine","0 1 11 1 3 5 7 9 11 14 0 0 1");
            gameAction11(); gameAction15(); gameAction17(); gameAction19();
            gameAction21(); gameAction23(); gameAction25(); gameAction28();
            gameAction14();
            return; }
        Log.e("Action","Failure -- Case uncovered.");
    }
    void onInputAction12() {
        logCurrentState("inputActionprevious1.click",currentSystemGoal,controllerState);
        if ((currentSystemGoal==0) && (controllerState==0)) {
            Log.w("ActionLine","0 0 12 2 3 5 7 9 11 13 0 0 2");
            controllerState = 2;
            gameAction12(); gameAction16(); gameAction17(); gameAction19();
            gameAction21(); gameAction23(); gameAction25(); gameAction27();
            gameAction14();
            return; }
        if ((currentSystemGoal==0) && (controllerState==1)) {
            Log.w("ActionLine","0 1 12 2 3 5 7 9 11 13 0 0 1");
            gameAction12(); gameAction16(); gameAction17(); gameAction19();
            gameAction21(); gameAction23(); gameAction25(); gameAction27();
            gameAction14();
            return; }
        Log.e("Action","Failure -- Case uncovered.");
    }
    void onInputAction13() {
        logCurrentState("inputActionprevious2.click",currentSystemGoal,controllerState);
        if ((currentSystemGoal==0) && (controllerState==0)) {
            Log.w("ActionLine","0 0 13 1 3 5 7 9 12 13 0 0 2");
            controllerState = 2;
            gameAction13(); gameAction15(); gameAction17(); gameAction19();
            gameAction21(); gameAction23(); gameAction26(); gameAction27();
            gameAction14();
            return; }
        if ((currentSystemGoal==0) && (controllerState==1)) {
            Log.w("ActionLine","0 1 13 1 3 5 7 9 12 13 0 0 1");
            gameAction13(); gameAction15(); gameAction17(); gameAction19();
            gameAction21(); gameAction23(); gameAction26(); gameAction27();
            gameAction14();
            return; }
        Log.e("Action","Failure -- Case uncovered.");
    }
    // --SYNTHESIZED-CODE-SUBCLASSES-END--

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // --SYNTHESIZED-CODE-ON-CREATE-START--
        onInputAction0();
        { final Button k = findViewById(R.id.button1);
            k.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    onInputAction1();
                }
            }); }

        {
            final Button k = findViewById(R.id.buttonCancelSubmit);
            k.setOnClickListener(new Button.OnClickListener(){
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View view) {
                    final EditText ed = (EditText)findViewById(R.id.pUniqueID);
                    final TextView tv_response = (TextView) findViewById(R.id.textViewCP);
                    if(ed.getText().toString() != ""){
                        try {
                            if (deletePlayerById(ed.getText().toString())) {
                                tv_response.setText("you were deleted ");
                                tv_response.setTextColor(R.color.design_default_color_on_secondary);
                                System.out.println("The player was deleted  ");
                                ed.setText("");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            System.out.println(" Error parsing data by delete "+e.toString());
                        }
                    }
                }
            });
        }

        //onclick submit Player
        {
            final Button k = findViewById(R.id.buttonRegSubmit);
            k.setOnClickListener(new Button.OnClickListener(){
                @SuppressLint("ResourceAsColor")
                @RequiresApi(api = Build.VERSION_CODES.R)
                @Override
                public void onClick(View view) {
                    try {
                        ifGameExist();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    final EditText edName = (EditText)findViewById(R.id.pName);
                    final EditText edSurname = (EditText)findViewById(R.id.pSurname);
                    final EditText edAdresse = (EditText)findViewById(R.id.pAddress);
                    final EditText edPhoneNumber = (EditText)findViewById(R.id.phoneNumber);
                    //File playerFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + "listPlayer.json");
                    JSONObject jsonObject = new JSONObject();
                    try {
                        if(!edName.getText().toString().equalsIgnoreCase("")
                                && !edSurname.getText().toString().equalsIgnoreCase("") &&
                                !edAdresse.getText().toString().equalsIgnoreCase("")){

                            //check if player already exist
                            if(playerExist(edName.getText().toString() , edSurname.getText().toString() , edAdresse.getText().toString(), edPhoneNumber.getText().toString()) == false) {
                                jsonObject.put("name", edName.getText().toString());
                                jsonObject.put("surname", edSurname.getText().toString());
                                jsonObject.put("address", edAdresse.getText().toString());
                                jsonObject.put("phoneNumber", edPhoneNumber.getText().toString());

                                //generate random uuid if not exist
                                String uuid = "";
                                do {
                                    uuid = UUID.randomUUID().toString();
                                } while (uuidExist(uuid) == true);


                                UUID.randomUUID().toString();
                                jsonObject.put("uniqId", uuid);
                                TextView tv_uuid = (TextView) findViewById(R.id.uuidShow);
                                tv_uuid.setText("Speichern Sie die folgende ID \n "+uuid);
                                tv_uuid.setTextColor(R.color.design_default_color_secondary_variant);

                                StringBuilder strb = new StringBuilder();
                                String player = jsonObject.toString();
                                ArrayList<String> listData = getApiCall();
                                JSONArray jr_ = new JSONArray();
                                for(String line : listData){
                                    JSONObject tmp_jo = new JSONObject(line.toString());
                                    jr_.put(tmp_jo);
                                    System.out.println("Line : "+line);
                                    strb.append(line).append("\n");
                                }
                                // add last player
                                jr_.put(jsonObject);
                                strb.append(player);
                                //post data
                                postApiCall(String.valueOf(jr_));

                            } else {
                                TextView tv_uuid = (TextView) findViewById(R.id.uuidShow);
                                tv_uuid.setText("Der Player mit eigegebenen Daten ist in der aktuellen List bereits vorhanden");
                                tv_uuid.setTextColor(R.color.design_default_color_error);
                            }
                        }


                    } catch (JSONException | IOException e) {
                        System.out.println(" Error parsing jsonObject "+e.toString());
                        //e.printStackTrace();
                    }

                    //clear editview
                    edName.setText("");
                    edSurname.setText("");
                    edAdresse.setText("");
                    edPhoneNumber.setText("");
                }
            });
        }


        { final Button k = findViewById(R.id.button2);
            k.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    final TextView edLabel = (TextView)findViewById(R.id.textView);
                    edLabel.setText("");
                    try {
                        ifGameExist();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    onInputAction2();
                }
            }); }
        { final Button k = findViewById(R.id.connect);
            k.setOnClickListener(new Button.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                public void onClick(View v) {
                    final EditText edName = (EditText)findViewById(R.id.managerName);
                    final EditText edPwd = (EditText)findViewById(R.id.mangerPassword);
                    if(edPwd.getText().toString().equalsIgnoreCase("") || edName.getText().toString().equalsIgnoreCase("") ){
                        edPwd.setBackgroundColor(R.color.design_default_color_error);
                        edPwd.setTextColor(R.color.design_default_color_background);

                        edName.setBackgroundColor(R.color.design_default_color_error);
                        edName.setTextColor(R.color.design_default_color_background);
                    } else {
                        if(edName.getText().toString().equalsIgnoreCase("admin") && edPwd.getText().toString().equalsIgnoreCase("admin")){
                            edName.setBackgroundColor(R.color.cardview_light_background);
                            edName.setTextColor(R.color.design_default_color_on_primary);
                            edPwd.setBackgroundColor(R.color.cardview_light_background);
                            edPwd.setTextColor(R.color.design_default_color_on_primary);
                            onInputAction3();
                        } else {
                            if(!edName.getText().toString().equalsIgnoreCase("admin")){
                                edName.setBackgroundColor(R.color.design_default_color_error);
                                edName.setTextColor(R.color.design_default_color_background);
                            }

                            if(!edPwd.getText().toString().equalsIgnoreCase("admin")){
                                edPwd.setBackgroundColor(R.color.design_default_color_error);
                                edPwd.setTextColor(R.color.design_default_color_background);
                            }
                        }

                    }

                }
            }); }

        {
            final Button k = findViewById(R.id.create);
            k.setOnClickListener(new View.OnClickListener(){
                //create new play
                @Override
                public void onClick(View view) {
                    //get form data
                    final EditText edDate = (EditText) findViewById(R.id.date);
                    final EditText edTime = (EditText) findViewById(R.id.time);
                    final EditText edAdress = (EditText) findViewById(R.id.gAddress);
                    final EditText edMaxPlayer = (EditText) findViewById(R.id.maxplayer);
                    JSONObject jsonObject = new JSONObject();

                    //File gameFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + "listGame.json");
                    if(!edDate.getText().toString().equalsIgnoreCase("")
                            && !edTime.getText().toString().equalsIgnoreCase("")
                            && !edAdress.getText().toString().equalsIgnoreCase("")
                            && !edMaxPlayer.getText().toString().equalsIgnoreCase("") ) {

                        try {
                            jsonObject.put("date", edDate.getText().toString());
                            jsonObject.put("time", edTime.getText().toString());
                            jsonObject.put("address", edAdress.getText().toString());
                            jsonObject.put("maxplayer", edMaxPlayer.getText().toString());
                            jsonObject.put("state", "open");

                            StringBuilder strb_play = new StringBuilder();
                            String play = jsonObject.toString();
                            postGameToServer(play);

                            strb_play.append(play);

                            //clear fields
                            edDate.setText("");
                            edTime.setText("");
                            edAdress.setText("");
                            edMaxPlayer.setText("");

                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Das Formular bitte ausfühlen");
                    }
                }
            });
        }
        { final Button k = findViewById(R.id.previous);
            k.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    onInputAction4();
                }
            }); }
        { final Button k = findViewById(R.id.createP);
            k.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    onInputAction5();
                }
            }); }
        { final Button k = findViewById(R.id.modifyP);
            k.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    onInputAction6();
                    try {
                        loadList();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }); }
        { final Button k = findViewById(R.id.logOut);
            k.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    onInputAction7();
                }
            }); }
        { final Button k = findViewById(R.id.retour);
            k.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    onInputAction8();
                }
            }); }
        { final Button k = findViewById(R.id.back1);
            k.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    onInputAction9();
                }
            }); }
        { final Button k = findViewById(R.id.back);
            k.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    onInputAction10();
                }
            }); }
        { final Button k = findViewById(R.id.RegCancel);
            k.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    onInputAction11();
                }
            }); }
        { final Button k = findViewById(R.id.previous1);
            k.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    onInputAction12();
                }
            }); }
        { final Button k = findViewById(R.id.previous2);
            k.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    onInputAction13();
                }
            }); }
        Log.w("Action","Application start");
        // --SYNTHESIZED-CODE-ON-CREATE-END--

    }
}









