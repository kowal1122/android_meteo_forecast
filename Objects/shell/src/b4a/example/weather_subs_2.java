package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class weather_subs_2 {


public static RemoteObject  _class_globals(RemoteObject __ref) throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 2;BA.debugLine="Public weatherAPIKey As String = \"############\"";
weather._weatherapikey = BA.ObjectToString("############");__ref.setField("_weatherapikey",weather._weatherapikey);
 //BA.debugLineNum = 3;BA.debugLine="Public weatherCityID As String";
weather._weathercityid = RemoteObject.createImmutable("");__ref.setField("_weathercityid",weather._weathercityid);
 //BA.debugLineNum = 4;BA.debugLine="Private m_RefreshInterval As Int";
weather._m_refreshinterval = RemoteObject.createImmutable(0);__ref.setField("_m_refreshinterval",weather._m_refreshinterval);
 //BA.debugLineNum = 5;BA.debugLine="Private tmrInterval As Timer";
weather._tmrinterval = RemoteObject.createNew ("anywheresoftware.b4a.objects.Timer");__ref.setField("_tmrinterval",weather._tmrinterval);
 //BA.debugLineNum = 7;BA.debugLine="Private callBackActivity As Object";
weather._callbackactivity = RemoteObject.createNew ("Object");__ref.setField("_callbackactivity",weather._callbackactivity);
 //BA.debugLineNum = 8;BA.debugLine="Private eventPrefix As String";
weather._eventprefix = RemoteObject.createImmutable("");__ref.setField("_eventprefix",weather._eventprefix);
 //BA.debugLineNum = 9;BA.debugLine="Private iconlist(40) As String";
weather._iconlist = RemoteObject.createNewArray ("String", new int[] {40}, new Object[]{});__ref.setField("_iconlist",weather._iconlist);
 //BA.debugLineNum = 10;BA.debugLine="Private descriptionlist(40) As String";
weather._descriptionlist = RemoteObject.createNewArray ("String", new int[] {40}, new Object[]{});__ref.setField("_descriptionlist",weather._descriptionlist);
 //BA.debugLineNum = 11;BA.debugLine="Private dtlist(40) As Int";
weather._dtlist = RemoteObject.createNewArray ("int", new int[] {40}, new Object[]{});__ref.setField("_dtlist",weather._dtlist);
 //BA.debugLineNum = 12;BA.debugLine="Private speedlist(40) As Int";
weather._speedlist = RemoteObject.createNewArray ("int", new int[] {40}, new Object[]{});__ref.setField("_speedlist",weather._speedlist);
 //BA.debugLineNum = 13;BA.debugLine="Private templist(40) As Double";
weather._templist = RemoteObject.createNewArray ("double", new int[] {40}, new Object[]{});__ref.setField("_templist",weather._templist);
 //BA.debugLineNum = 15;BA.debugLine="Dim i As Int = 0";
weather._i = BA.numberCast(int.class, 0);__ref.setField("_i",weather._i);
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _downloadweather(RemoteObject __ref) throws Exception{
try {
		Debug.PushSubsStack("DownloadWeather (weather) ","weather",1,__ref.getField(false, "ba"),__ref,42);
if (RapidSub.canDelegate("downloadweather")) return __ref.runUserSub(false, "weather","downloadweather", __ref);
RemoteObject _j = RemoteObject.declareNull("anywheresoftware.b4a.samples.httputils2.httpjob");
RemoteObject _url = RemoteObject.createImmutable("");
 BA.debugLineNum = 42;BA.debugLine="Public Sub DownloadWeather()";
Debug.ShouldStop(512);
 BA.debugLineNum = 43;BA.debugLine="Dim j As HttpJob";
Debug.ShouldStop(1024);
_j = RemoteObject.createNew ("anywheresoftware.b4a.samples.httputils2.httpjob");Debug.locals.put("j", _j);
 BA.debugLineNum = 44;BA.debugLine="Dim url As String = \"https://api.openweathermap.o";
Debug.ShouldStop(2048);
_url = BA.ObjectToString("https://api.openweathermap.org/data/2.5/forecast");Debug.locals.put("url", _url);Debug.locals.put("url", _url);
 BA.debugLineNum = 47;BA.debugLine="j.Initialize(\"main\", Me)";
Debug.ShouldStop(16384);
_j.runVoidMethod ("_initialize",__ref.getField(false, "ba"),(Object)(BA.ObjectToString("main")),(Object)(__ref));
 BA.debugLineNum = 48;BA.debugLine="j.Download2(url, Array As String (\"id\", weatherCi";
Debug.ShouldStop(32768);
_j.runVoidMethod ("_download2",(Object)(_url),(Object)(RemoteObject.createNewArray("String",new int[] {8},new Object[] {BA.ObjectToString("id"),__ref.getField(true,"_weathercityid"),BA.ObjectToString("units"),BA.ObjectToString("metric"),BA.ObjectToString("appid"),__ref.getField(true,"_weatherapikey"),BA.ObjectToString("lang"),RemoteObject.createImmutable("pl")})));
 BA.debugLineNum = 49;BA.debugLine="Log(\"download: \" & j)";
Debug.ShouldStop(65536);
weather.__c.runVoidMethod ("Log",(Object)(RemoteObject.concat(RemoteObject.createImmutable("download: "),_j)));
 BA.debugLineNum = 51;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _givedescription(RemoteObject __ref,RemoteObject _number) throws Exception{
try {
		Debug.PushSubsStack("givedescription (weather) ","weather",1,__ref.getField(false, "ba"),__ref,89);
if (RapidSub.canDelegate("givedescription")) return __ref.runUserSub(false, "weather","givedescription", __ref, _number);
Debug.locals.put("number", _number);
 BA.debugLineNum = 89;BA.debugLine="Public Sub givedescription(number As Int) As Strin";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 90;BA.debugLine="Return(descriptionlist(number))";
Debug.ShouldStop(33554432);
if (true) return (__ref.getField(false,"_descriptionlist").getArrayElement(true,_number));
 BA.debugLineNum = 91;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _givedtlist(RemoteObject __ref,RemoteObject _number) throws Exception{
try {
		Debug.PushSubsStack("givedtlist (weather) ","weather",1,__ref.getField(false, "ba"),__ref,113);
if (RapidSub.canDelegate("givedtlist")) return __ref.runUserSub(false, "weather","givedtlist", __ref, _number);
RemoteObject _expdate = RemoteObject.createImmutable(0L);
Debug.locals.put("number", _number);
 BA.debugLineNum = 113;BA.debugLine="Public Sub givedtlist(number As Int) As String";
Debug.ShouldStop(65536);
 BA.debugLineNum = 114;BA.debugLine="Dim ExpDate As Long = DateUtils.UnixTimeToTicks(d";
Debug.ShouldStop(131072);
_expdate = weather._dateutils.runMethod(true,"_unixtimetoticks",__ref.runMethod(false,"getActivityBA"),(Object)(BA.numberCast(long.class, __ref.getField(false,"_dtlist").getArrayElement(true,_number))));Debug.locals.put("ExpDate", _expdate);Debug.locals.put("ExpDate", _expdate);
 BA.debugLineNum = 115;BA.debugLine="Return(DateTime.Date(ExpDate)  & \" \" & DateTime.t";
Debug.ShouldStop(262144);
if (true) return (RemoteObject.concat(weather.__c.getField(false,"DateTime").runMethod(true,"Date",(Object)(_expdate)),RemoteObject.createImmutable(" "),weather.__c.getField(false,"DateTime").runMethod(true,"Time",(Object)(_expdate))));
 BA.debugLineNum = 116;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _giveicon(RemoteObject __ref,RemoteObject _number) throws Exception{
try {
		Debug.PushSubsStack("giveicon (weather) ","weather",1,__ref.getField(false, "ba"),__ref,94);
if (RapidSub.canDelegate("giveicon")) return __ref.runUserSub(false, "weather","giveicon", __ref, _number);
Debug.locals.put("number", _number);
 BA.debugLineNum = 94;BA.debugLine="Public Sub giveicon(number As Int) As String";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 95;BA.debugLine="Select iconlist(number)";
Debug.ShouldStop(1073741824);
switch (BA.switchObjectToInt(__ref.getField(false,"_iconlist").getArrayElement(true,_number),BA.ObjectToString("01d"),BA.ObjectToString("01n"),BA.ObjectToString("02d"),BA.ObjectToString("02n"),BA.ObjectToString("03d"),BA.ObjectToString("03n"),BA.ObjectToString("04d"),BA.ObjectToString("04n"),BA.ObjectToString("09d"),BA.ObjectToString("09n"),BA.ObjectToString("10d"),BA.ObjectToString("10n"),BA.ObjectToString("11d"),BA.ObjectToString("11n"),BA.ObjectToString("13d"),BA.ObjectToString("13n"))) {
case 0: 
case 1: {
 BA.debugLineNum = 97;BA.debugLine="Return(\"01.png\")";
Debug.ShouldStop(1);
if (true) return (RemoteObject.createImmutable("01.png"));
 break; }
case 2: 
case 3: {
 BA.debugLineNum = 99;BA.debugLine="Return(\"02.png\")";
Debug.ShouldStop(4);
if (true) return (RemoteObject.createImmutable("02.png"));
 break; }
case 4: 
case 5: 
case 6: 
case 7: {
 BA.debugLineNum = 101;BA.debugLine="Return(\"03.png\")";
Debug.ShouldStop(16);
if (true) return (RemoteObject.createImmutable("03.png"));
 break; }
case 8: 
case 9: 
case 10: 
case 11: {
 BA.debugLineNum = 103;BA.debugLine="Return(\"09.png\")";
Debug.ShouldStop(64);
if (true) return (RemoteObject.createImmutable("09.png"));
 break; }
case 12: 
case 13: {
 BA.debugLineNum = 105;BA.debugLine="Return(\"11.png\")";
Debug.ShouldStop(256);
if (true) return (RemoteObject.createImmutable("11.png"));
 break; }
case 14: 
case 15: {
 BA.debugLineNum = 107;BA.debugLine="Return(\"13.png\")";
Debug.ShouldStop(1024);
if (true) return (RemoteObject.createImmutable("13.png"));
 break; }
default: {
 BA.debugLineNum = 109;BA.debugLine="Return(\"loading.png\")";
Debug.ShouldStop(4096);
if (true) return (RemoteObject.createImmutable("loading.png"));
 break; }
}
;
 BA.debugLineNum = 111;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _givetemp(RemoteObject __ref,RemoteObject _number) throws Exception{
try {
		Debug.PushSubsStack("givetemp (weather) ","weather",1,__ref.getField(false, "ba"),__ref,85);
if (RapidSub.canDelegate("givetemp")) return __ref.runUserSub(false, "weather","givetemp", __ref, _number);
Debug.locals.put("number", _number);
 BA.debugLineNum = 85;BA.debugLine="Public Sub givetemp(number As Int) As Double";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 86;BA.debugLine="Return(templist(number))";
Debug.ShouldStop(2097152);
if (true) return (__ref.getField(false,"_templist").getArrayElement(true,_number));
 BA.debugLineNum = 87;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
return RemoteObject.createImmutable(0);
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _initialize(RemoteObject __ref,RemoteObject _ba,RemoteObject _targetactivity,RemoteObject _eventname,RemoteObject _cityid,RemoteObject _getlatest,RemoteObject _refreshinterval) throws Exception{
try {
		Debug.PushSubsStack("Initialize (weather) ","weather",1,__ref.getField(false, "ba"),__ref,22);
if (RapidSub.canDelegate("initialize")) return __ref.runUserSub(false, "weather","initialize", __ref, _ba, _targetactivity, _eventname, _cityid, _getlatest, _refreshinterval);
__ref.runVoidMethodAndSync("innerInitializeHelper", _ba);
Debug.locals.put("ba", _ba);
Debug.locals.put("TargetActivity", _targetactivity);
Debug.locals.put("EventName", _eventname);
Debug.locals.put("CityID", _cityid);
Debug.locals.put("GetLatest", _getlatest);
Debug.locals.put("RefreshInterval", _refreshinterval);
 BA.debugLineNum = 22;BA.debugLine="Public Sub Initialize(TargetActivity As Object, Ev";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 24;BA.debugLine="weatherCityID = CityID";
Debug.ShouldStop(8388608);
__ref.setField ("_weathercityid",_cityid);
 BA.debugLineNum = 25;BA.debugLine="callBackActivity = TargetActivity";
Debug.ShouldStop(16777216);
__ref.setField ("_callbackactivity",_targetactivity);
 BA.debugLineNum = 26;BA.debugLine="eventPrefix = EventName";
Debug.ShouldStop(33554432);
__ref.setField ("_eventprefix",_eventname);
 BA.debugLineNum = 27;BA.debugLine="m_RefreshInterval = RefreshInterval";
Debug.ShouldStop(67108864);
__ref.setField ("_m_refreshinterval",_refreshinterval);
 BA.debugLineNum = 28;BA.debugLine="tmrInterval.Initialize(\"tmrInterval\", 3600)  'akt";
Debug.ShouldStop(134217728);
__ref.getField(false,"_tmrinterval").runVoidMethod ("Initialize",__ref.getField(false, "ba"),(Object)(BA.ObjectToString("tmrInterval")),(Object)(BA.numberCast(long.class, 3600)));
 BA.debugLineNum = 29;BA.debugLine="tmrInterval.Enabled = True";
Debug.ShouldStop(268435456);
__ref.getField(false,"_tmrinterval").runMethod(true,"setEnabled",weather.__c.getField(true,"True"));
 BA.debugLineNum = 31;BA.debugLine="If GetLatest Then";
Debug.ShouldStop(1073741824);
if (_getlatest.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 32;BA.debugLine="DownloadWeather";
Debug.ShouldStop(-2147483648);
__ref.runClassMethod (b4a.example.weather.class, "_downloadweather");
 };
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
public static RemoteObject  _jobdone(RemoteObject __ref,RemoteObject _job) throws Exception{
try {
		Debug.PushSubsStack("JobDone (weather) ","weather",1,__ref.getField(false, "ba"),__ref,54);
if (RapidSub.canDelegate("jobdone")) return __ref.runUserSub(false, "weather","jobdone", __ref, _job);
Debug.locals.put("Job", _job);
 BA.debugLineNum = 54;BA.debugLine="Private Sub JobDone(Job As HttpJob)";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 56;BA.debugLine="If Job.Success = False Then";
Debug.ShouldStop(8388608);
if (RemoteObject.solveBoolean("=",_job.getField(true,"_success"),weather.__c.getField(true,"False"))) { 
 BA.debugLineNum = 57;BA.debugLine="Select Job.JobName";
Debug.ShouldStop(16777216);
switch (BA.switchObjectToInt(_job.getField(true,"_jobname"),BA.ObjectToString("main"))) {
case 0: {
 BA.debugLineNum = 59;BA.debugLine="If SubExists(callBackActivity, eventPrefix & \"";
Debug.ShouldStop(67108864);
if (weather.__c.runMethod(true,"SubExists",__ref.runMethod(false,"getActivityBA"),(Object)(__ref.getField(false,"_callbackactivity")),(Object)(RemoteObject.concat(__ref.getField(true,"_eventprefix"),RemoteObject.createImmutable("_Failed")))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 60;BA.debugLine="CallSubDelayed(callBackActivity, eventPrefix";
Debug.ShouldStop(134217728);
weather.__c.runVoidMethod ("CallSubDelayed",__ref.runMethod(false,"getActivityBA"),(Object)(__ref.getField(false,"_callbackactivity")),(Object)(RemoteObject.concat(__ref.getField(true,"_eventprefix"),RemoteObject.createImmutable("_Failed"))));
 };
 break; }
}
;
 BA.debugLineNum = 63;BA.debugLine="Job.Release";
Debug.ShouldStop(1073741824);
_job.runVoidMethod ("_release");
 BA.debugLineNum = 64;BA.debugLine="Return";
Debug.ShouldStop(-2147483648);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 67;BA.debugLine="Select Job.JobName";
Debug.ShouldStop(4);
switch (BA.switchObjectToInt(_job.getField(true,"_jobname"),BA.ObjectToString("main"))) {
case 0: {
 BA.debugLineNum = 69;BA.debugLine="ParseWeatherData(Job.GetString)";
Debug.ShouldStop(16);
__ref.runClassMethod (b4a.example.weather.class, "_parseweatherdata",(Object)(_job.runMethod(true,"_getstring")));
 BA.debugLineNum = 70;BA.debugLine="If m_RefreshInterval > 0 Then";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean(">",__ref.getField(true,"_m_refreshinterval"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 71;BA.debugLine="tmrInterval.Interval = m_RefreshInterval * 600";
Debug.ShouldStop(64);
__ref.getField(false,"_tmrinterval").runMethod(true,"setInterval",BA.numberCast(long.class, RemoteObject.solve(new RemoteObject[] {__ref.getField(true,"_m_refreshinterval"),RemoteObject.createImmutable(60000)}, "*",0, 1)));
 BA.debugLineNum = 72;BA.debugLine="tmrInterval.Enabled = True";
Debug.ShouldStop(128);
__ref.getField(false,"_tmrinterval").runMethod(true,"setEnabled",weather.__c.getField(true,"True"));
 };
 break; }
}
;
 BA.debugLineNum = 76;BA.debugLine="Job.Release";
Debug.ShouldStop(2048);
_job.runVoidMethod ("_release");
 BA.debugLineNum = 78;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _parseweatherdata(RemoteObject __ref,RemoteObject _jsondata) throws Exception{
try {
		Debug.PushSubsStack("ParseWeatherData (weather) ","weather",1,__ref.getField(false, "ba"),__ref,120);
if (RapidSub.canDelegate("parseweatherdata")) return __ref.runUserSub(false, "weather","parseweatherdata", __ref, _jsondata);
RemoteObject _js = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.JSONParser");
RemoteObject _root = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _map = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _parser = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.JSONParser");
RemoteObject _list = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _collist = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _dt = RemoteObject.createImmutable(0);
RemoteObject _weather = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _colweather = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _icon = RemoteObject.createImmutable("");
RemoteObject _description = RemoteObject.createImmutable("");
RemoteObject _main2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _temp = RemoteObject.createImmutable(0);
RemoteObject _wind = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _speed = RemoteObject.createImmutable(0);
Debug.locals.put("jsonData", _jsondata);
 BA.debugLineNum = 120;BA.debugLine="Public Sub ParseWeatherData(jsonData As String)";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 121;BA.debugLine="Dim js As JSONParser";
Debug.ShouldStop(16777216);
_js = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser");Debug.locals.put("js", _js);
 BA.debugLineNum = 122;BA.debugLine="Dim root As Map";
Debug.ShouldStop(33554432);
_root = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("root", _root);
 BA.debugLineNum = 123;BA.debugLine="Dim map As Map";
Debug.ShouldStop(67108864);
_map = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("map", _map);
 BA.debugLineNum = 125;BA.debugLine="map.Initialize";
Debug.ShouldStop(268435456);
_map.runVoidMethod ("Initialize");
 BA.debugLineNum = 127;BA.debugLine="Log(\"jsondata:\" & jsonData)";
Debug.ShouldStop(1073741824);
weather.__c.runVoidMethod ("Log",(Object)(RemoteObject.concat(RemoteObject.createImmutable("jsondata:"),_jsondata)));
 BA.debugLineNum = 128;BA.debugLine="js.Initialize(jsonData)";
Debug.ShouldStop(-2147483648);
_js.runVoidMethod ("Initialize",(Object)(_jsondata));
 BA.debugLineNum = 129;BA.debugLine="root.Initialize";
Debug.ShouldStop(1);
_root.runVoidMethod ("Initialize");
 BA.debugLineNum = 130;BA.debugLine="root = js.NextObject";
Debug.ShouldStop(2);
_root = _js.runMethod(false,"NextObject");Debug.locals.put("root", _root);
 BA.debugLineNum = 131;BA.debugLine="Log(\"root:\" & root)";
Debug.ShouldStop(4);
weather.__c.runVoidMethod ("Log",(Object)(RemoteObject.concat(RemoteObject.createImmutable("root:"),_root)));
 BA.debugLineNum = 133;BA.debugLine="Dim parser As JSONParser";
Debug.ShouldStop(16);
_parser = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser");Debug.locals.put("parser", _parser);
 BA.debugLineNum = 136;BA.debugLine="parser.Initialize(jsonData)";
Debug.ShouldStop(128);
_parser.runVoidMethod ("Initialize",(Object)(_jsondata));
 BA.debugLineNum = 137;BA.debugLine="Dim root As Map = parser.NextObject";
Debug.ShouldStop(256);
_root = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_root = _parser.runMethod(false,"NextObject");Debug.locals.put("root", _root);Debug.locals.put("root", _root);
 BA.debugLineNum = 138;BA.debugLine="Dim list As List = root.Get(\"list\")";
Debug.ShouldStop(512);
_list = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_list.setObject(_root.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("list")))));Debug.locals.put("list", _list);
 BA.debugLineNum = 139;BA.debugLine="For Each collist As Map In list";
Debug.ShouldStop(1024);
_collist = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
final RemoteObject group14 = _list;
final int groupLen14 = group14.runMethod(true,"getSize").<Integer>get();
for (int index14 = 0;index14 < groupLen14 ;index14++){
_collist.setObject(group14.runMethod(false,"Get",index14));
Debug.locals.put("collist", _collist);
 BA.debugLineNum = 140;BA.debugLine="Dim dt As Int = collist.Get(\"dt\")";
Debug.ShouldStop(2048);
_dt = BA.numberCast(int.class, _collist.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("dt")))));Debug.locals.put("dt", _dt);Debug.locals.put("dt", _dt);
 BA.debugLineNum = 141;BA.debugLine="dtlist(i) = dt";
Debug.ShouldStop(4096);
__ref.getField(false,"_dtlist").setArrayElement (_dt,__ref.getField(true,"_i"));
 BA.debugLineNum = 142;BA.debugLine="Dim weather As List = collist.Get(\"weather\")";
Debug.ShouldStop(8192);
_weather = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_weather.setObject(_collist.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("weather")))));Debug.locals.put("weather", _weather);
 BA.debugLineNum = 144;BA.debugLine="For Each colweather As Map In weather";
Debug.ShouldStop(32768);
_colweather = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
final RemoteObject group18 = _weather;
final int groupLen18 = group18.runMethod(true,"getSize").<Integer>get();
for (int index18 = 0;index18 < groupLen18 ;index18++){
_colweather.setObject(group18.runMethod(false,"Get",index18));
Debug.locals.put("colweather", _colweather);
 BA.debugLineNum = 145;BA.debugLine="Dim icon As String  = colweather.Get(\"icon\")";
Debug.ShouldStop(65536);
_icon = BA.ObjectToString(_colweather.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("icon")))));Debug.locals.put("icon", _icon);Debug.locals.put("icon", _icon);
 BA.debugLineNum = 146;BA.debugLine="Dim description As String  = colweather.Get(\"de";
Debug.ShouldStop(131072);
_description = BA.ObjectToString(_colweather.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("description")))));Debug.locals.put("description", _description);Debug.locals.put("description", _description);
 BA.debugLineNum = 147;BA.debugLine="iconlist(i) = icon";
Debug.ShouldStop(262144);
__ref.getField(false,"_iconlist").setArrayElement (_icon,__ref.getField(true,"_i"));
 BA.debugLineNum = 148;BA.debugLine="descriptionlist(i) = description";
Debug.ShouldStop(524288);
__ref.getField(false,"_descriptionlist").setArrayElement (_description,__ref.getField(true,"_i"));
 BA.debugLineNum = 149;BA.debugLine="Log(i & \"-\" & \"dane: \" & iconlist(i) & \" \" & dt";
Debug.ShouldStop(1048576);
weather.__c.runVoidMethod ("Log",(Object)(RemoteObject.concat(__ref.getField(true,"_i"),RemoteObject.createImmutable("-"),RemoteObject.createImmutable("dane: "),__ref.getField(false,"_iconlist").getArrayElement(true,__ref.getField(true,"_i")),RemoteObject.createImmutable(" "),__ref.getField(false,"_dtlist").getArrayElement(true,__ref.getField(true,"_i")),RemoteObject.createImmutable(" "),__ref.getField(false,"_descriptionlist").getArrayElement(true,__ref.getField(true,"_i")))));
 }
Debug.locals.put("colweather", _colweather);
;
 BA.debugLineNum = 154;BA.debugLine="Dim Main2 As Map = collist.Get(\"main\")";
Debug.ShouldStop(33554432);
_main2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_main2.setObject(_collist.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("main")))));Debug.locals.put("Main2", _main2);
 BA.debugLineNum = 155;BA.debugLine="Dim temp As Double = Main2.Get(\"temp\")      'tem";
Debug.ShouldStop(67108864);
_temp = BA.numberCast(double.class, _main2.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("temp")))));Debug.locals.put("temp", _temp);Debug.locals.put("temp", _temp);
 BA.debugLineNum = 156;BA.debugLine="templist(i) = temp";
Debug.ShouldStop(134217728);
__ref.getField(false,"_templist").setArrayElement (_temp,__ref.getField(true,"_i"));
 BA.debugLineNum = 157;BA.debugLine="Dim wind As Map = collist.Get(\"wind\")";
Debug.ShouldStop(268435456);
_wind = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_wind.setObject(_collist.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("wind")))));Debug.locals.put("wind", _wind);
 BA.debugLineNum = 158;BA.debugLine="Dim speed As Double = wind.Get(\"speed\")     'prę";
Debug.ShouldStop(536870912);
_speed = BA.numberCast(double.class, _wind.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("speed")))));Debug.locals.put("speed", _speed);Debug.locals.put("speed", _speed);
 BA.debugLineNum = 159;BA.debugLine="speedlist(i) = speed";
Debug.ShouldStop(1073741824);
__ref.getField(false,"_speedlist").setArrayElement (BA.numberCast(int.class, _speed),__ref.getField(true,"_i"));
 BA.debugLineNum = 161;BA.debugLine="i = i +1";
Debug.ShouldStop(1);
__ref.setField ("_i",RemoteObject.solve(new RemoteObject[] {__ref.getField(true,"_i"),RemoteObject.createImmutable(1)}, "+",1, 1));
 }
Debug.locals.put("collist", _collist);
;
 BA.debugLineNum = 165;BA.debugLine="If SubExists(callBackActivity, eventPrefix & \"_Re";
Debug.ShouldStop(16);
if (weather.__c.runMethod(true,"SubExists",__ref.runMethod(false,"getActivityBA"),(Object)(__ref.getField(false,"_callbackactivity")),(Object)(RemoteObject.concat(__ref.getField(true,"_eventprefix"),RemoteObject.createImmutable("_Ready")))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 166;BA.debugLine="CallSubDelayed(callBackActivity, eventPrefix & \"";
Debug.ShouldStop(32);
weather.__c.runVoidMethod ("CallSubDelayed",__ref.runMethod(false,"getActivityBA"),(Object)(__ref.getField(false,"_callbackactivity")),(Object)(RemoteObject.concat(__ref.getField(true,"_eventprefix"),RemoteObject.createImmutable("_Ready"))));
 };
 BA.debugLineNum = 169;BA.debugLine="End Sub";
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
public static RemoteObject  _tmrinterval_tick(RemoteObject __ref) throws Exception{
try {
		Debug.PushSubsStack("tmrInterval_Tick (weather) ","weather",1,__ref.getField(false, "ba"),__ref,80);
if (RapidSub.canDelegate("tmrinterval_tick")) return __ref.runUserSub(false, "weather","tmrinterval_tick", __ref);
 BA.debugLineNum = 80;BA.debugLine="private Sub tmrInterval_Tick()";
Debug.ShouldStop(32768);
 BA.debugLineNum = 81;BA.debugLine="tmrInterval.Enabled = False";
Debug.ShouldStop(65536);
__ref.getField(false,"_tmrinterval").runMethod(true,"setEnabled",weather.__c.getField(true,"False"));
 BA.debugLineNum = 82;BA.debugLine="DownloadWeather";
Debug.ShouldStop(131072);
__ref.runClassMethod (b4a.example.weather.class, "_downloadweather");
 BA.debugLineNum = 83;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
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