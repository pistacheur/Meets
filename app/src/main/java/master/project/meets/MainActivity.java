package master.project.meets;



import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {

    //check if player already exist
    public boolean playerExist(String name, String surname, String address){
        boolean response = false;
        File playerFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/" +"listPlayer.json");
        if(playerFile.exists()){
            try {
                FileReader fr = new FileReader(playerFile);
                BufferedReader bfr_render = new BufferedReader(fr);
                String line_render = bfr_render.readLine();
                while (line_render != null) {
                    try {
                        JSONObject jo = new JSONObject(line_render.toString());
                        if(jo.getString("name").equalsIgnoreCase(name)
                                && jo.getString("surname").equalsIgnoreCase(surname)
                                && jo.getString("address").equalsIgnoreCase(address))
                            response = true;
                        line_render = bfr_render.readLine();


                    } catch (JSONException ex) {
                        System.out.println(" Error json object  " + ex.toString());
                    }
                }
                bfr_render.close();
            }catch(IOException ex) {
                System.out.println(" Error Filereader  " + ex.toString());
            }
        } else {
            System.out.println("File not exit in the given directory ");
        }
        return response;

    }

    //delete player by id

    public boolean deletePlayerById(String uuid){
        boolean response = false;
        File playerFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/" +"listPlayer.json");
        if(playerFile.exists()){
            try {
                FileReader fr = new FileReader(playerFile);
                BufferedReader bfr_render = new BufferedReader(fr);
                String line_render = bfr_render.readLine();
                StringBuilder data = new StringBuilder();
                while (line_render != null) {
                    try {
                        JSONObject jo = new JSONObject(line_render.toString());
                        if(jo.getString("uniqId").equalsIgnoreCase(uuid))
                            response = true;
                        else
                            data.append(line_render).append("\n");

                    } catch (JSONException ex) {
                        System.out.println(" Error json object  " + ex.toString());
                    }
                    line_render = bfr_render.readLine();
                }
                bfr_render.close();
                //rewritte data into file
                try {
                    FileWriter filewriter = new FileWriter(playerFile);
                    BufferedWriter bfrW = new BufferedWriter(filewriter);
                    bfrW.write(String.valueOf(data));
                    bfrW.close();
                } catch(IOException ex){
                    System.out.println("Writer error rewritting file  "+ex.toString());
                }

            }catch(IOException ex) {
                System.out.println(" Error Filereader  " + ex.toString());
            }
        } else {
            System.out.println(" file not exist to delete player  ");
        }
        return response;
    }

    //check if uuid already exist
    public boolean uuidExist(String id){
        boolean response = false;
        File playerFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/" +"listPlayer.json");
        if(playerFile.exists()){
            try {
                FileReader fr = new FileReader(playerFile);
                BufferedReader bfr_render = new BufferedReader(fr);
                String line_render = bfr_render.readLine();
                while (line_render != null) {
                    try {
                        JSONObject jo = new JSONObject(line_render.toString());
                        if(jo.getString("uniqId").equalsIgnoreCase(id))
                            response = true;

                    } catch (JSONException ex) {
                        System.out.println(" Error json object  " + ex.toString());
                    }
                    line_render = bfr_render.readLine();
                }
                bfr_render.close();
            }catch(IOException ex) {
                System.out.println(" Error Filereader  " + ex.toString());
            }
        } else {
            response = !response;
        }

        return response;
    }

    //load data from file
    public void loadList(){
        //read existing json file
        File playerFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/" +"listPlayer.json");
        if(playerFile.exists()){
            try {
                FileReader fr = new FileReader(playerFile);
                BufferedReader bfr_render = new BufferedReader(fr);
                StringBuilder str = new StringBuilder();
                String line_render = bfr_render.readLine();
                JSONArray jr = new JSONArray();
                ListView lv = (ListView) findViewById(R.id.listViewPlayer);
                ArrayList<String> alist = new ArrayList<String>();
                int count = 0;
                while (line_render != null) {
                    try {
                        JSONObject jo = new JSONObject(line_render.toString());
                        String playerString = "Name : "+jo.getString("name") +" \nSurname : "+jo.getString("surname")+" \nAddress : "+jo.getString("address");
                        alist.add(playerString);
                        jr.put(jo);
                        str.append(line_render).append("\n");
                        line_render = bfr_render.readLine();
                    } catch (JSONException ex){
                        System.out.println(" Error json object  "+ex.toString());
                    }

                    count++;
                }
                bfr_render.close();
                System.out.println("List of existing Players  : " + jr);
                final ArrayAdapter<String> adp = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,android.R.id.text1, alist);
                lv.setAdapter(adp);
            } catch (IOException e) {
                System.out.println(" Error read existing file  "+e.toString());
            }
        } else {
            System.out.println("File not found in the given directory");
        }
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
                        if (deletePlayerById(ed.getText().toString())) {
                            tv_response.setText("you were deleted ");
                            tv_response.setTextColor(R.color.design_default_color_on_secondary);
                            System.out.println("The player was deleted  ");
                            ed.setText("");
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
                    final EditText edName = (EditText)findViewById(R.id.pName);
                    final EditText edSurname = (EditText)findViewById(R.id.pSurname);
                    final EditText edAdresse = (EditText)findViewById(R.id.pAddress);
                    JSONObject jsonObject = new JSONObject();
                    try {
                        if(!edName.getText().toString().equalsIgnoreCase("")
                                && !edSurname.getText().toString().equalsIgnoreCase("") &&
                                !edAdresse.getText().toString().equalsIgnoreCase("")){

                            //check if player already exist
                            if(!playerExist(edName.getText().toString() , edSurname.getText().toString() , edAdresse.getText().toString())) {
                                jsonObject.put("name", edName.getText().toString());
                                jsonObject.put("surname", edSurname.getText().toString());
                                jsonObject.put("address", edAdresse.getText().toString());

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
                                File playerFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + "listPlayer.json");
                                if(playerFile.exists()){
                                    try {
                                        //read existing json file
                                        FileReader fr = new FileReader(playerFile);
                                        if(playerFile.exists()){
                                            BufferedReader bfr = new BufferedReader(fr);
                                            String line = bfr.readLine();
                                            while (line != null) {
                                                strb.append(line).append("\n");
                                                line = bfr.readLine();
                                            }
                                            bfr.close();
                                        }


                                    } catch (IOException e) {
                                        System.out.println("Error writting in json file " + e.toString());
                                        //e.printStackTrace();
                                    }
                                }
                                // add last player
                                strb.append(player);
                                try {
                                    FileWriter filewriter = new FileWriter(playerFile);
                                    BufferedWriter bfrW = new BufferedWriter(filewriter);
                                    bfrW.write(String.valueOf(strb));
                                    bfrW.close();
                                } catch(IOException ex){
                                    System.out.println("Writer error adding player not allow "+ex.toString());
                                }

                            } else {
                                TextView tv_uuid = (TextView) findViewById(R.id.uuidShow);
                                tv_uuid.setText("Der Player mit eigegebenen Daten ist in der aktuellen List bereits vorhanden");
                                tv_uuid.setTextColor(R.color.design_default_color_error);
                            }
                        }

                        //read the file again to find new data
                        //read existing json file
                        File playerFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/" +"listPlayer.json");
                        if(playerFile.exists()) {
                            try {
                                FileReader fr = new FileReader(playerFile);
                                BufferedReader bfr_render = new BufferedReader(fr);
                                StringBuilder str = new StringBuilder();
                                String line_render = bfr_render.readLine();
                                JSONArray jr = new JSONArray();
                                while (line_render != null) {
                                    JSONObject jo = new JSONObject(line_render.toString());
                                    jr.put(jo);
                                    str.append(line_render).append("\n");
                                    line_render = bfr_render.readLine();
                                }
                                // add last player
                                bfr_render.close();
                                System.out.println("List of existing Players  : " + jr);
                            } catch (IOException e) {
                                System.out.println(" Error read existing file  "+e.toString());
                            }
                        }

                    } catch (JSONException e) {
                        System.out.println(" Error parsing jsonObject "+e.toString());
                        //e.printStackTrace();
                    }

                    //clear editview
                    edName.setText("");
                    edSurname.setText("");
                    edAdresse.setText("");
                }
            });
        }


        { final Button k = findViewById(R.id.button2);
    k.setOnClickListener(new Button.OnClickListener() {
        public void onClick(View v) {
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
                loadList();
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









