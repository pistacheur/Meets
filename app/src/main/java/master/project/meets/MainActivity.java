package master.project.meets;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    // --SYNTHESIZED-CODE-SUBCLASSES-START--
    void gameAction0() {
      logCurrentState("GameAction0",currentSystemGoal,controllerState);
    }
    void gameAction1() {
      logCurrentState("GameAction1",currentSystemGoal,controllerState);
    }
    void gameAction2() {
      logCurrentState("GameAction2",currentSystemGoal,controllerState);
      // Done action
    }
    void gameAction3() {
      logCurrentState("GameAction3",currentSystemGoal,controllerState);
      final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.mainPage); c.setVisibility(View.INVISIBLE);
    }
    void gameAction4() {
      logCurrentState("GameAction4",currentSystemGoal,controllerState);
      final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.playerRegistrationPage); c.setVisibility(View.INVISIBLE);
    }
    void gameAction5() {
      logCurrentState("GameAction5",currentSystemGoal,controllerState);
      final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.playerRegistrationPage); c.setVisibility(View.VISIBLE);
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
Log.w("ActionLine","0 0 0 1 3 0 0 1");
controllerState = 1;
gameAction0(); gameAction3(); gameAction5(); gameAction2();
return; }
  if ((currentSystemGoal==0) && (controllerState==1)) {
Log.w("ActionLine","0 1 0 0 0 2");
controllerState = 2;
gameAction0(); gameAction2();
return; }
 Log.e("Action","Failure -- Case uncovered.");
}
void onInputAction1() {
  logCurrentState("inputActionbutton2.click",currentSystemGoal,controllerState);
  if ((currentSystemGoal==0) && (controllerState==0)) {
Log.w("ActionLine","0 0 1 1 2 0 0 2");
controllerState = 2;
gameAction1(); gameAction3(); gameAction4(); gameAction2();
return; }
  if ((currentSystemGoal==0) && (controllerState==1)) {
Log.w("ActionLine","0 1 1 1 2 0 0 1");
gameAction1(); gameAction3(); gameAction4(); gameAction2();
return; }
 Log.e("Action","Failure -- Case uncovered.");
}
    // --SYNTHESIZED-CODE-SUBCLASSES-END--



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        // --SYNTHESIZED-CODE-ON-CREATE-START—



        // --SYNTHESIZED-CODE-ON-CREATE-END--

    }

}










