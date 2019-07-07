package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,35);
if (RapidSub.canDelegate("activity_create")) return main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 35;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(4);
 BA.debugLineNum = 36;BA.debugLine="Activity.LoadLayout(\"2\")";
Debug.ShouldStop(8);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("2")),main.mostCurrent.activityBA);
 BA.debugLineNum = 37;BA.debugLine="wt.Initialize(Me, \"wt\", \"7530858\", True, 10) '753";
Debug.ShouldStop(16);
main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_initialize",main.processBA,(Object)(main.getObject()),(Object)(BA.ObjectToString("wt")),(Object)(BA.ObjectToString("7530858")),(Object)(main.mostCurrent.__c.getField(true,"True")),(Object)(BA.numberCast(int.class, 10)));
 BA.debugLineNum = 39;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,44);
if (RapidSub.canDelegate("activity_pause")) return main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 44;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(2048);
 BA.debugLineNum = 45;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,41);
if (RapidSub.canDelegate("activity_resume")) return main.remoteMe.runUserSub(false, "main","activity_resume");
 BA.debugLineNum = 41;BA.debugLine="Sub Activity_Resume()";
Debug.ShouldStop(256);
 BA.debugLineNum = 42;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 18;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 19;BA.debugLine="Private wt As Weather";
main.mostCurrent._wt = RemoteObject.createNew ("b4a.example.weather");
 //BA.debugLineNum = 21;BA.debugLine="Private Label1 As Label";
main.mostCurrent._label1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 22;BA.debugLine="Private Label2 As Label";
main.mostCurrent._label2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 23;BA.debugLine="Private Label3 As Label";
main.mostCurrent._label3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 24;BA.debugLine="Private Label4 As Label";
main.mostCurrent._label4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 25;BA.debugLine="Private imgWeather2 As ImageView";
main.mostCurrent._imgweather2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 26;BA.debugLine="Private imgWeather1 As ImageView";
main.mostCurrent._imgweather1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 27;BA.debugLine="Private imgWeather3 As ImageView";
main.mostCurrent._imgweather3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 28;BA.debugLine="Private imgWeather4 As ImageView";
main.mostCurrent._imgweather4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 29;BA.debugLine="Private temp1 As Label";
main.mostCurrent._temp1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 30;BA.debugLine="Private temp2 As Label";
main.mostCurrent._temp2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 31;BA.debugLine="Private temp3 As Label";
main.mostCurrent._temp3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 32;BA.debugLine="Private temp4 As Label";
main.mostCurrent._temp4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 33;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main_subs_0._process_globals();
starter_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("b4a.example.main");
weather.myClass = BA.getDeviceClass ("b4a.example.weather");
starter.myClass = BA.getDeviceClass ("b4a.example.starter");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _showweather() throws Exception{
try {
		Debug.PushSubsStack("ShowWeather (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,56);
if (RapidSub.canDelegate("showweather")) return main.remoteMe.runUserSub(false, "main","showweather");
 BA.debugLineNum = 56;BA.debugLine="Sub ShowWeather()";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 58;BA.debugLine="Label1.Text = wt.givedtlist(0)";
Debug.ShouldStop(33554432);
main.mostCurrent._label1.runMethod(true,"setText",(main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_givedtlist",(Object)(BA.numberCast(int.class, 0)))));
 BA.debugLineNum = 59;BA.debugLine="Label2.Text = wt.givedtlist(1)";
Debug.ShouldStop(67108864);
main.mostCurrent._label2.runMethod(true,"setText",(main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_givedtlist",(Object)(BA.numberCast(int.class, 1)))));
 BA.debugLineNum = 60;BA.debugLine="Label3.Text = wt.givedtlist(2)";
Debug.ShouldStop(134217728);
main.mostCurrent._label3.runMethod(true,"setText",(main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_givedtlist",(Object)(BA.numberCast(int.class, 2)))));
 BA.debugLineNum = 61;BA.debugLine="Label4.Text = wt.givedtlist(3)";
Debug.ShouldStop(268435456);
main.mostCurrent._label4.runMethod(true,"setText",(main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_givedtlist",(Object)(BA.numberCast(int.class, 3)))));
 BA.debugLineNum = 64;BA.debugLine="temp1.Text = wt.givetemp(0) & \"°C\"";
Debug.ShouldStop(-2147483648);
main.mostCurrent._temp1.runMethod(true,"setText",(RemoteObject.concat(main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_givetemp",(Object)(BA.numberCast(int.class, 0))),RemoteObject.createImmutable("°C"))));
 BA.debugLineNum = 65;BA.debugLine="temp2.Text = wt.givetemp(1) & \"°C\"";
Debug.ShouldStop(1);
main.mostCurrent._temp2.runMethod(true,"setText",(RemoteObject.concat(main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_givetemp",(Object)(BA.numberCast(int.class, 1))),RemoteObject.createImmutable("°C"))));
 BA.debugLineNum = 66;BA.debugLine="temp3.Text = wt.givetemp(2) & \"°C\"";
Debug.ShouldStop(2);
main.mostCurrent._temp3.runMethod(true,"setText",(RemoteObject.concat(main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_givetemp",(Object)(BA.numberCast(int.class, 2))),RemoteObject.createImmutable("°C"))));
 BA.debugLineNum = 67;BA.debugLine="temp4.Text = wt.givetemp(3) & \"°C\"";
Debug.ShouldStop(4);
main.mostCurrent._temp4.runMethod(true,"setText",(RemoteObject.concat(main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_givetemp",(Object)(BA.numberCast(int.class, 3))),RemoteObject.createImmutable("°C"))));
 BA.debugLineNum = 69;BA.debugLine="imgWeather1.Bitmap = LoadBitmap(File.DirAssets, w";
Debug.ShouldStop(16);
main.mostCurrent._imgweather1.runMethod(false,"setBitmap",(main.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_giveicon",(Object)(BA.numberCast(int.class, 0))))).getObject()));
 BA.debugLineNum = 70;BA.debugLine="imgWeather2.Bitmap = LoadBitmap(File.DirAssets, w";
Debug.ShouldStop(32);
main.mostCurrent._imgweather2.runMethod(false,"setBitmap",(main.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_giveicon",(Object)(BA.numberCast(int.class, 1))))).getObject()));
 BA.debugLineNum = 71;BA.debugLine="imgWeather3.Bitmap = LoadBitmap(File.DirAssets, w";
Debug.ShouldStop(64);
main.mostCurrent._imgweather3.runMethod(false,"setBitmap",(main.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_giveicon",(Object)(BA.numberCast(int.class, 2))))).getObject()));
 BA.debugLineNum = 72;BA.debugLine="imgWeather4.Bitmap = LoadBitmap(File.DirAssets, w";
Debug.ShouldStop(128);
main.mostCurrent._imgweather4.runMethod(false,"setBitmap",(main.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_giveicon",(Object)(BA.numberCast(int.class, 3))))).getObject()));
 BA.debugLineNum = 73;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _wt_failed() throws Exception{
try {
		Debug.PushSubsStack("wt_Failed (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,51);
if (RapidSub.canDelegate("wt_failed")) return main.remoteMe.runUserSub(false, "main","wt_failed");
 BA.debugLineNum = 51;BA.debugLine="Sub wt_Failed()";
Debug.ShouldStop(262144);
 BA.debugLineNum = 52;BA.debugLine="Log(\"wt_Failed\")";
Debug.ShouldStop(524288);
main.mostCurrent.__c.runVoidMethod ("Log",(Object)(RemoteObject.createImmutable("wt_Failed")));
 BA.debugLineNum = 53;BA.debugLine="Label1.Text = \"brak info o pogodzie\"";
Debug.ShouldStop(1048576);
main.mostCurrent._label1.runMethod(true,"setText",RemoteObject.createImmutable(("brak info o pogodzie")));
 BA.debugLineNum = 54;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _wt_ready() throws Exception{
try {
		Debug.PushSubsStack("wt_Ready (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,47);
if (RapidSub.canDelegate("wt_ready")) return main.remoteMe.runUserSub(false, "main","wt_ready");
 BA.debugLineNum = 47;BA.debugLine="Sub wt_Ready()";
Debug.ShouldStop(16384);
 BA.debugLineNum = 48;BA.debugLine="ShowWeather";
Debug.ShouldStop(32768);
_showweather();
 BA.debugLineNum = 49;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
}