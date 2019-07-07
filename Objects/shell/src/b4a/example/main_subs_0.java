package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,31);
if (RapidSub.canDelegate("activity_create")) return main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 31;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 32;BA.debugLine="Activity.LoadLayout(\"2\")";
Debug.ShouldStop(-2147483648);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("2")),main.mostCurrent.activityBA);
 BA.debugLineNum = 33;BA.debugLine="wt.Initialize(Me, \"wt\", \"7530858\", True, 10) '753";
Debug.ShouldStop(1);
main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_initialize",main.processBA,(Object)(main.getObject()),(Object)(BA.ObjectToString("wt")),(Object)(BA.ObjectToString("7530858")),(Object)(main.mostCurrent.__c.getField(true,"True")),(Object)(BA.numberCast(int.class, 10)));
 BA.debugLineNum = 35;BA.debugLine="End Sub";
Debug.ShouldStop(4);
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
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,40);
if (RapidSub.canDelegate("activity_pause")) return main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 40;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(128);
 BA.debugLineNum = 41;BA.debugLine="End Sub";
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
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,37);
if (RapidSub.canDelegate("activity_resume")) return main.remoteMe.runUserSub(false, "main","activity_resume");
 BA.debugLineNum = 37;BA.debugLine="Sub Activity_Resume()";
Debug.ShouldStop(16);
 BA.debugLineNum = 38;BA.debugLine="End Sub";
Debug.ShouldStop(32);
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
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
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
		Debug.PushSubsStack("ShowWeather (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,52);
if (RapidSub.canDelegate("showweather")) return main.remoteMe.runUserSub(false, "main","showweather");
 BA.debugLineNum = 52;BA.debugLine="Sub ShowWeather()";
Debug.ShouldStop(524288);
 BA.debugLineNum = 54;BA.debugLine="Label1.Text = \"ikona 1:\"  & wt.giveicon(0) & CRLF";
Debug.ShouldStop(2097152);
main.mostCurrent._label1.runMethod(true,"setText",(RemoteObject.concat(RemoteObject.createImmutable("ikona 1:"),main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_giveicon",(Object)(BA.numberCast(int.class, 0))),main.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("ikona 2:"),main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_giveicon",(Object)(BA.numberCast(int.class, 1))),main.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("ikona 2:"),main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_giveicon",(Object)(BA.numberCast(int.class, 3))),main.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("ikona 2:"),main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_giveicon",(Object)(BA.numberCast(int.class, 4))))));
 BA.debugLineNum = 57;BA.debugLine="Label2.Text = wt.givedtlist(1) & CRLF & \"temperat";
Debug.ShouldStop(16777216);
main.mostCurrent._label2.runMethod(true,"setText",(RemoteObject.concat(main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_givedtlist",(Object)(BA.numberCast(int.class, 1))),main.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("temperatura:"),main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_givetemp",(Object)(BA.numberCast(int.class, 1))),main.mostCurrent.__c.getField(true,"CRLF"),main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_givedescription",(Object)(BA.numberCast(int.class, 1))))));
 BA.debugLineNum = 58;BA.debugLine="Label3.Text = wt.givedtlist(2) & CRLF & \"temperat";
Debug.ShouldStop(33554432);
main.mostCurrent._label3.runMethod(true,"setText",(RemoteObject.concat(main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_givedtlist",(Object)(BA.numberCast(int.class, 2))),main.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("temperatura:"),main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_givetemp",(Object)(BA.numberCast(int.class, 2))),main.mostCurrent.__c.getField(true,"CRLF"),main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_givedescription",(Object)(BA.numberCast(int.class, 2))))));
 BA.debugLineNum = 59;BA.debugLine="Label4.Text = wt.givedtlist(3) & CRLF & \"temperat";
Debug.ShouldStop(67108864);
main.mostCurrent._label4.runMethod(true,"setText",(RemoteObject.concat(main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_givedtlist",(Object)(BA.numberCast(int.class, 3))),main.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("temperatura:"),main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_givetemp",(Object)(BA.numberCast(int.class, 3))),main.mostCurrent.__c.getField(true,"CRLF"),main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_givedescription",(Object)(BA.numberCast(int.class, 3))))));
 BA.debugLineNum = 61;BA.debugLine="imgWeather1.Bitmap = LoadBitmap(File.DirAssets, w";
Debug.ShouldStop(268435456);
main.mostCurrent._imgweather1.runMethod(false,"setBitmap",(main.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_giveicon",(Object)(BA.numberCast(int.class, 0))))).getObject()));
 BA.debugLineNum = 62;BA.debugLine="imgWeather2.Bitmap = LoadBitmap(File.DirAssets, w";
Debug.ShouldStop(536870912);
main.mostCurrent._imgweather2.runMethod(false,"setBitmap",(main.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_giveicon",(Object)(BA.numberCast(int.class, 1))))).getObject()));
 BA.debugLineNum = 63;BA.debugLine="imgWeather3.Bitmap = LoadBitmap(File.DirAssets, w";
Debug.ShouldStop(1073741824);
main.mostCurrent._imgweather3.runMethod(false,"setBitmap",(main.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_giveicon",(Object)(BA.numberCast(int.class, 2))))).getObject()));
 BA.debugLineNum = 64;BA.debugLine="imgWeather4.Bitmap = LoadBitmap(File.DirAssets, w";
Debug.ShouldStop(-2147483648);
main.mostCurrent._imgweather4.runMethod(false,"setBitmap",(main.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(main.mostCurrent._wt.runClassMethod (b4a.example.weather.class, "_giveicon",(Object)(BA.numberCast(int.class, 3))))).getObject()));
 BA.debugLineNum = 65;BA.debugLine="End Sub";
Debug.ShouldStop(1);
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
		Debug.PushSubsStack("wt_Failed (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,47);
if (RapidSub.canDelegate("wt_failed")) return main.remoteMe.runUserSub(false, "main","wt_failed");
 BA.debugLineNum = 47;BA.debugLine="Sub wt_Failed()";
Debug.ShouldStop(16384);
 BA.debugLineNum = 48;BA.debugLine="Log(\"wt_Failed\")";
Debug.ShouldStop(32768);
main.mostCurrent.__c.runVoidMethod ("Log",(Object)(RemoteObject.createImmutable("wt_Failed")));
 BA.debugLineNum = 49;BA.debugLine="Label1.Text = \"brak info o pogodzie\"";
Debug.ShouldStop(65536);
main.mostCurrent._label1.runMethod(true,"setText",RemoteObject.createImmutable(("brak info o pogodzie")));
 BA.debugLineNum = 50;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
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
		Debug.PushSubsStack("wt_Ready (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,43);
if (RapidSub.canDelegate("wt_ready")) return main.remoteMe.runUserSub(false, "main","wt_ready");
 BA.debugLineNum = 43;BA.debugLine="Sub wt_Ready()";
Debug.ShouldStop(1024);
 BA.debugLineNum = 44;BA.debugLine="ShowWeather";
Debug.ShouldStop(2048);
_showweather();
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
}