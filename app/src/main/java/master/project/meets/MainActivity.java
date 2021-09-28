package master.project.meets;



import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


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
      // Done action
    }
    void gameAction10() {
      logCurrentState("GameAction10",currentSystemGoal,controllerState);
      final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.mainPage); c.setVisibility(View.INVISIBLE);
    }
    void gameAction11() {
      logCurrentState("GameAction11",currentSystemGoal,controllerState);
      final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.mainPage); c.setVisibility(View.VISIBLE);
    }
    void gameAction12() {
      logCurrentState("GameAction12",currentSystemGoal,controllerState);
      final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.connectPage); c.setVisibility(View.INVISIBLE);
    }
    void gameAction13() {
      logCurrentState("GameAction13",currentSystemGoal,controllerState);
      final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.connectPage); c.setVisibility(View.VISIBLE);
    }
    void gameAction14() {
      logCurrentState("GameAction14",currentSystemGoal,controllerState);
      final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.managerPage); c.setVisibility(View.INVISIBLE);
    }
    void gameAction15() {
      logCurrentState("GameAction15",currentSystemGoal,controllerState);
      final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.managerPage); c.setVisibility(View.VISIBLE);
    }
    void gameAction16() {
      logCurrentState("GameAction16",currentSystemGoal,controllerState);
      final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.playerRegistrationPage); c.setVisibility(View.INVISIBLE);
    }
    void gameAction17() {
      logCurrentState("GameAction17",currentSystemGoal,controllerState);
      final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.playerRegistrationPage); c.setVisibility(View.VISIBLE);
    }
    void gameAction18() {
      logCurrentState("GameAction18",currentSystemGoal,controllerState);
      final androidx.constraintlayout.widget.ConstraintLayout c = findViewById(R.id.registrationCancelingPage); c.setVisibility(View.INVISIBLE);
    }
    void gameAction19() {
      logCurrentState("GameAction19",currentSystemGoal,controllerState);
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
Log.w("ActionLine","0 0 0 2 3 5 7 9 0 0 1");
controllerState = 1;
gameAction0(); gameAction11(); gameAction12(); gameAction14();
gameAction16(); gameAction18(); gameAction9();
return; }
  if ((currentSystemGoal==0) && (controllerState==1)) {
Log.w("ActionLine","0 1 0 0 0 2");
controllerState = 2;
gameAction0(); gameAction9();
return; }
 Log.e("Action","Failure -- Case uncovered.");
}
void onInputAction1() {
  logCurrentState("inputActionbutton1.click",currentSystemGoal,controllerState);
  if ((currentSystemGoal==0) && (controllerState==0)) {
Log.w("ActionLine","0 0 1 1 4 5 7 9 0 0 2");
controllerState = 2;
gameAction1(); gameAction10(); gameAction13(); gameAction14();
gameAction16(); gameAction18(); gameAction9();
return; }
  if ((currentSystemGoal==0) && (controllerState==1)) {
Log.w("ActionLine","0 1 1 1 4 5 7 9 0 0 1");
gameAction1(); gameAction10(); gameAction13(); gameAction14();
gameAction16(); gameAction18(); gameAction9();
return; }
 Log.e("Action","Failure -- Case uncovered.");
}
void onInputAction2() {
  logCurrentState("inputActionbutton2.click",currentSystemGoal,controllerState);
  if ((currentSystemGoal==0) && (controllerState==0)) {
Log.w("ActionLine","0 0 2 1 3 5 8 9 0 0 2");
controllerState = 2;
gameAction2(); gameAction10(); gameAction12(); gameAction14();
gameAction17(); gameAction18(); gameAction9();
return; }
  if ((currentSystemGoal==0) && (controllerState==1)) {
Log.w("ActionLine","0 1 2 1 8 9 0 0 1");
gameAction2(); gameAction10(); gameAction17(); gameAction18();
gameAction9();
return; }
 Log.e("Action","Failure -- Case uncovered.");
}
void onInputAction3() {
  logCurrentState("inputActionconnect.click",currentSystemGoal,controllerState);
  if ((currentSystemGoal==0) && (controllerState==0)) {
Log.w("ActionLine","0 0 3 1 3 6 7 9 0 0 2");
controllerState = 2;
gameAction3(); gameAction10(); gameAction12(); gameAction15();
gameAction16(); gameAction18(); gameAction9();
return; }
  if ((currentSystemGoal==0) && (controllerState==1)) {
Log.w("ActionLine","0 1 3 1 3 6 7 9 0 0 1");
gameAction3(); gameAction10(); gameAction12(); gameAction15();
gameAction16(); gameAction18(); gameAction9();
return; }
 Log.e("Action","Failure -- Case uncovered.");
}
void onInputAction4() {
  logCurrentState("inputActionprevious.click",currentSystemGoal,controllerState);
  if ((currentSystemGoal==0) && (controllerState==0)) {
Log.w("ActionLine","0 0 4 2 3 5 7 9 0 0 2");
controllerState = 2;
gameAction4(); gameAction11(); gameAction12(); gameAction14();
gameAction16(); gameAction18(); gameAction9();
return; }
  if ((currentSystemGoal==0) && (controllerState==1)) {
Log.w("ActionLine","0 1 4 2 3 5 7 9 0 0 1");
gameAction4(); gameAction11(); gameAction12(); gameAction14();
gameAction16(); gameAction18(); gameAction9();
return; }
 Log.e("Action","Failure -- Case uncovered.");
}
void onInputAction5() {
  logCurrentState("inputActionlogOut.click",currentSystemGoal,controllerState);
  if ((currentSystemGoal==0) && (controllerState==0)) {
Log.w("ActionLine","0 0 5 1 4 5 7 9 0 0 2");
controllerState = 2;
gameAction5(); gameAction10(); gameAction13(); gameAction14();
gameAction16(); gameAction18(); gameAction9();
return; }
  if ((currentSystemGoal==0) && (controllerState==1)) {
Log.w("ActionLine","0 1 5 1 4 5 7 9 0 0 1");
gameAction5(); gameAction10(); gameAction13(); gameAction14();
gameAction16(); gameAction18(); gameAction9();
return; }
 Log.e("Action","Failure -- Case uncovered.");
}
void onInputAction6() {
  logCurrentState("inputActionRegCancel.click",currentSystemGoal,controllerState);
  if ((currentSystemGoal==0) && (controllerState==0)) {
Log.w("ActionLine","0 0 6 1 3 5 7 10 0 0 2");
controllerState = 2;
gameAction6(); gameAction10(); gameAction12(); gameAction14();
gameAction16(); gameAction19(); gameAction9();
return; }
  if ((currentSystemGoal==0) && (controllerState==1)) {
Log.w("ActionLine","0 1 6 1 3 5 7 10 0 0 1");
gameAction6(); gameAction10(); gameAction12(); gameAction14();
gameAction16(); gameAction19(); gameAction9();
return; }
 Log.e("Action","Failure -- Case uncovered.");
}
void onInputAction7() {
  logCurrentState("inputActionprevious1.click",currentSystemGoal,controllerState);
  if ((currentSystemGoal==0) && (controllerState==0)) {
Log.w("ActionLine","0 0 7 2 3 5 7 9 0 0 2");
controllerState = 2;
gameAction7(); gameAction11(); gameAction12(); gameAction14();
gameAction16(); gameAction18(); gameAction9();
return; }
  if ((currentSystemGoal==0) && (controllerState==1)) {
Log.w("ActionLine","0 1 7 2 3 5 7 9 0 0 1");
gameAction7(); gameAction11(); gameAction12(); gameAction14();
gameAction16(); gameAction18(); gameAction9();
return; }
 Log.e("Action","Failure -- Case uncovered.");
}
void onInputAction8() {
  logCurrentState("inputActionprevious2.click",currentSystemGoal,controllerState);
  if ((currentSystemGoal==0) && (controllerState==0)) {
Log.w("ActionLine","0 0 8 1 3 5 8 9 0 0 2");
controllerState = 2;
gameAction8(); gameAction10(); gameAction12(); gameAction14();
gameAction17(); gameAction18(); gameAction9();
return; }
  if ((currentSystemGoal==0) && (controllerState==1)) {
Log.w("ActionLine","0 1 8 1 3 5 8 9 0 0 1");
gameAction8(); gameAction10(); gameAction12(); gameAction14();
gameAction17(); gameAction18(); gameAction9();
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
      { final Button k = findViewById(R.id.button2);
    k.setOnClickListener(new Button.OnClickListener() {
        public void onClick(View v) {
                onInputAction2();
                }
        }); }
      { final Button k = findViewById(R.id.connect);
    k.setOnClickListener(new Button.OnClickListener() {
        public void onClick(View v) {
                onInputAction3();
                }
        }); }
      { final Button k = findViewById(R.id.previous);
    k.setOnClickListener(new Button.OnClickListener() {
        public void onClick(View v) {
                onInputAction4();
                }
        }); }
      { final Button k = findViewById(R.id.logOut);
    k.setOnClickListener(new Button.OnClickListener() {
        public void onClick(View v) {
                onInputAction5();
                }
        }); }
      { final Button k = findViewById(R.id.RegCancel);
    k.setOnClickListener(new Button.OnClickListener() {
        public void onClick(View v) {
                onInputAction6();
                }
        }); }
      { final Button k = findViewById(R.id.previous1);
    k.setOnClickListener(new Button.OnClickListener() {
        public void onClick(View v) {
                onInputAction7();
                }
        }); }
      { final Button k = findViewById(R.id.previous2);
    k.setOnClickListener(new Button.OnClickListener() {
        public void onClick(View v) {
                onInputAction8();
                }
        }); }
Log.w("Action","Application start");
        // --SYNTHESIZED-CODE-ON-CREATE-END--

    }
}









