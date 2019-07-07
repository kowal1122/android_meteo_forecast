package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class weather extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "b4a.example.weather");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.weather.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public String _weatherapikey = "";
public String _weathercityid = "";
public int _m_refreshinterval = 0;
public anywheresoftware.b4a.objects.Timer _tmrinterval = null;
public Object _callbackactivity = null;
public String _eventprefix = "";
public String[] _iconlist = null;
public String[] _descriptionlist = null;
public int[] _dtlist = null;
public int[] _speedlist = null;
public double[] _templist = null;
public int _i = 0;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public b4a.example.dateutils _dateutils = null;
public b4a.example.main _main = null;
public b4a.example.powrot _powrot = null;
public b4a.example.starter _starter = null;
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 2;BA.debugLine="Public weatherAPIKey As String = \"d6870f22a6a2ce7";
_weatherapikey = "d6870f22a6a2ce786ae2e31bb33a1a2f";
 //BA.debugLineNum = 3;BA.debugLine="Public weatherCityID As String";
_weathercityid = "";
 //BA.debugLineNum = 4;BA.debugLine="Private m_RefreshInterval As Int";
_m_refreshinterval = 0;
 //BA.debugLineNum = 5;BA.debugLine="Private tmrInterval As Timer";
_tmrinterval = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 7;BA.debugLine="Private callBackActivity As Object";
_callbackactivity = new Object();
 //BA.debugLineNum = 8;BA.debugLine="Private eventPrefix As String";
_eventprefix = "";
 //BA.debugLineNum = 9;BA.debugLine="Private iconlist(40) As String";
_iconlist = new String[(int) (40)];
java.util.Arrays.fill(_iconlist,"");
 //BA.debugLineNum = 10;BA.debugLine="Private descriptionlist(40) As String";
_descriptionlist = new String[(int) (40)];
java.util.Arrays.fill(_descriptionlist,"");
 //BA.debugLineNum = 11;BA.debugLine="Private dtlist(40) As Int";
_dtlist = new int[(int) (40)];
;
 //BA.debugLineNum = 12;BA.debugLine="Private speedlist(40) As Int";
_speedlist = new int[(int) (40)];
;
 //BA.debugLineNum = 13;BA.debugLine="Private templist(40) As Double";
_templist = new double[(int) (40)];
;
 //BA.debugLineNum = 15;BA.debugLine="Dim i As Int = 0";
_i = (int) (0);
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return "";
}
public String  _downloadweather() throws Exception{
anywheresoftware.b4a.samples.httputils2.httpjob _j = null;
String _url = "";
 //BA.debugLineNum = 42;BA.debugLine="Public Sub DownloadWeather()";
 //BA.debugLineNum = 43;BA.debugLine="Dim j As HttpJob";
_j = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 44;BA.debugLine="Dim url As String = \"https://api.openweathermap.o";
_url = "https://api.openweathermap.org/data/2.5/forecast";
 //BA.debugLineNum = 47;BA.debugLine="j.Initialize(\"main\", Me)";
_j._initialize(ba,"main",this);
 //BA.debugLineNum = 48;BA.debugLine="j.Download2(url, Array As String (\"id\", weatherCi";
_j._download2(_url,new String[]{"id",_weathercityid,"units","metric","appid",_weatherapikey,"lang","pl"});
 //BA.debugLineNum = 49;BA.debugLine="Log(\"download: \" & j)";
__c.Log("download: "+BA.ObjectToString(_j));
 //BA.debugLineNum = 51;BA.debugLine="End Sub";
return "";
}
public String  _givedescription(int _number) throws Exception{
 //BA.debugLineNum = 89;BA.debugLine="Public Sub givedescription(number As Int) As Strin";
 //BA.debugLineNum = 90;BA.debugLine="Return(descriptionlist(number))";
if (true) return (_descriptionlist[_number]);
 //BA.debugLineNum = 91;BA.debugLine="End Sub";
return "";
}
public String  _givedtlist(int _number) throws Exception{
long _expdate = 0L;
 //BA.debugLineNum = 113;BA.debugLine="Public Sub givedtlist(number As Int) As String";
 //BA.debugLineNum = 114;BA.debugLine="Dim ExpDate As Long = DateUtils.UnixTimeToTicks(d";
_expdate = _dateutils._unixtimetoticks(getActivityBA(),(long) (_dtlist[_number]));
 //BA.debugLineNum = 115;BA.debugLine="DateTime.DateFormat = \"dd-MM\"";
__c.DateTime.setDateFormat("dd-MM");
 //BA.debugLineNum = 116;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
__c.DateTime.setTimeFormat("HH:mm");
 //BA.debugLineNum = 117;BA.debugLine="Return(DateTime.Time(ExpDate)  & \" \" & DateTime.D";
if (true) return (__c.DateTime.Time(_expdate)+" "+__c.DateTime.Date(_expdate));
 //BA.debugLineNum = 118;BA.debugLine="Log(DateTime.Date(ExpDate))";
__c.Log(__c.DateTime.Date(_expdate));
 //BA.debugLineNum = 119;BA.debugLine="End Sub";
return "";
}
public String  _giveicon(int _number) throws Exception{
 //BA.debugLineNum = 94;BA.debugLine="Public Sub giveicon(number As Int) As String";
 //BA.debugLineNum = 95;BA.debugLine="Select iconlist(number)";
switch (BA.switchObjectToInt(_iconlist[_number],"01d","01n","02d","02n","03d","03n","04d","04n","09d","09n","10d","10n","11d","11n","13d","13n")) {
case 0: 
case 1: {
 //BA.debugLineNum = 97;BA.debugLine="Return(\"01.png\")";
if (true) return ("01.png");
 break; }
case 2: 
case 3: {
 //BA.debugLineNum = 99;BA.debugLine="Return(\"02.png\")";
if (true) return ("02.png");
 break; }
case 4: 
case 5: 
case 6: 
case 7: {
 //BA.debugLineNum = 101;BA.debugLine="Return(\"03.png\")";
if (true) return ("03.png");
 break; }
case 8: 
case 9: 
case 10: 
case 11: {
 //BA.debugLineNum = 103;BA.debugLine="Return(\"09.png\")";
if (true) return ("09.png");
 break; }
case 12: 
case 13: {
 //BA.debugLineNum = 105;BA.debugLine="Return(\"11.png\")";
if (true) return ("11.png");
 break; }
case 14: 
case 15: {
 //BA.debugLineNum = 107;BA.debugLine="Return(\"13.png\")";
if (true) return ("13.png");
 break; }
default: {
 //BA.debugLineNum = 109;BA.debugLine="Return(\"loading.png\")";
if (true) return ("loading.png");
 break; }
}
;
 //BA.debugLineNum = 111;BA.debugLine="End Sub";
return "";
}
public double  _givetemp(int _number) throws Exception{
 //BA.debugLineNum = 85;BA.debugLine="Public Sub givetemp(number As Int) As Double";
 //BA.debugLineNum = 86;BA.debugLine="Return(NumberFormat(templist(number), 0, 1))";
if (true) return (double)(Double.parseDouble((__c.NumberFormat(_templist[_number],(int) (0),(int) (1)))));
 //BA.debugLineNum = 87;BA.debugLine="End Sub";
return 0;
}
public String  _initialize(anywheresoftware.b4a.BA _ba,Object _targetactivity,String _eventname,String _cityid,boolean _getlatest,int _refreshinterval) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 22;BA.debugLine="Public Sub Initialize(TargetActivity As Object, Ev";
 //BA.debugLineNum = 24;BA.debugLine="weatherCityID = CityID";
_weathercityid = _cityid;
 //BA.debugLineNum = 25;BA.debugLine="callBackActivity = TargetActivity";
_callbackactivity = _targetactivity;
 //BA.debugLineNum = 26;BA.debugLine="eventPrefix = EventName";
_eventprefix = _eventname;
 //BA.debugLineNum = 27;BA.debugLine="m_RefreshInterval = RefreshInterval";
_m_refreshinterval = _refreshinterval;
 //BA.debugLineNum = 28;BA.debugLine="tmrInterval.Initialize(\"tmrInterval\", 3600)  'akt";
_tmrinterval.Initialize(ba,"tmrInterval",(long) (3600));
 //BA.debugLineNum = 29;BA.debugLine="tmrInterval.Enabled = True";
_tmrinterval.setEnabled(__c.True);
 //BA.debugLineNum = 31;BA.debugLine="If GetLatest Then";
if (_getlatest) { 
 //BA.debugLineNum = 32;BA.debugLine="DownloadWeather";
_downloadweather();
 };
 //BA.debugLineNum = 35;BA.debugLine="End Sub";
return "";
}
public String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
 //BA.debugLineNum = 54;BA.debugLine="Private Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 56;BA.debugLine="If Job.Success = False Then";
if (_job._success==__c.False) { 
 //BA.debugLineNum = 57;BA.debugLine="Select Job.JobName";
switch (BA.switchObjectToInt(_job._jobname,"main")) {
case 0: {
 //BA.debugLineNum = 59;BA.debugLine="If SubExists(callBackActivity, eventPrefix & \"";
if (__c.SubExists(getActivityBA(),_callbackactivity,_eventprefix+"_Failed")) { 
 //BA.debugLineNum = 60;BA.debugLine="CallSubDelayed(callBackActivity, eventPrefix";
__c.CallSubDelayed(getActivityBA(),_callbackactivity,_eventprefix+"_Failed");
 };
 break; }
}
;
 //BA.debugLineNum = 63;BA.debugLine="Job.Release";
_job._release();
 //BA.debugLineNum = 64;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 67;BA.debugLine="Select Job.JobName";
switch (BA.switchObjectToInt(_job._jobname,"main")) {
case 0: {
 //BA.debugLineNum = 69;BA.debugLine="ParseWeatherData(Job.GetString)";
_parseweatherdata(_job._getstring());
 //BA.debugLineNum = 70;BA.debugLine="If m_RefreshInterval > 0 Then";
if (_m_refreshinterval>0) { 
 //BA.debugLineNum = 71;BA.debugLine="tmrInterval.Interval = m_RefreshInterval * 600";
_tmrinterval.setInterval((long) (_m_refreshinterval*60000));
 //BA.debugLineNum = 72;BA.debugLine="tmrInterval.Enabled = True";
_tmrinterval.setEnabled(__c.True);
 };
 break; }
}
;
 //BA.debugLineNum = 76;BA.debugLine="Job.Release";
_job._release();
 //BA.debugLineNum = 78;BA.debugLine="End Sub";
return "";
}
public String  _parseweatherdata(String _jsondata) throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _js = null;
anywheresoftware.b4a.objects.collections.Map _root = null;
anywheresoftware.b4a.objects.collections.Map _map = null;
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.List _list = null;
anywheresoftware.b4a.objects.collections.Map _collist = null;
int _dt = 0;
anywheresoftware.b4a.objects.collections.List _weather = null;
anywheresoftware.b4a.objects.collections.Map _colweather = null;
String _icon = "";
String _description = "";
anywheresoftware.b4a.objects.collections.Map _main2 = null;
double _temp = 0;
anywheresoftware.b4a.objects.collections.Map _wind = null;
double _speed = 0;
 //BA.debugLineNum = 123;BA.debugLine="Public Sub ParseWeatherData(jsonData As String)";
 //BA.debugLineNum = 124;BA.debugLine="Dim js As JSONParser";
_js = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 125;BA.debugLine="Dim root As Map";
_root = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 126;BA.debugLine="Dim map As Map";
_map = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 128;BA.debugLine="map.Initialize";
_map.Initialize();
 //BA.debugLineNum = 130;BA.debugLine="Log(\"jsondata:\" & jsonData)";
__c.Log("jsondata:"+_jsondata);
 //BA.debugLineNum = 131;BA.debugLine="js.Initialize(jsonData)";
_js.Initialize(_jsondata);
 //BA.debugLineNum = 132;BA.debugLine="root.Initialize";
_root.Initialize();
 //BA.debugLineNum = 133;BA.debugLine="root = js.NextObject";
_root = _js.NextObject();
 //BA.debugLineNum = 134;BA.debugLine="Log(\"root:\" & root)";
__c.Log("root:"+BA.ObjectToString(_root));
 //BA.debugLineNum = 136;BA.debugLine="Dim parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 139;BA.debugLine="parser.Initialize(jsonData)";
_parser.Initialize(_jsondata);
 //BA.debugLineNum = 140;BA.debugLine="Dim root As Map = parser.NextObject";
_root = new anywheresoftware.b4a.objects.collections.Map();
_root = _parser.NextObject();
 //BA.debugLineNum = 141;BA.debugLine="Dim list As List = root.Get(\"list\")";
_list = new anywheresoftware.b4a.objects.collections.List();
_list.setObject((java.util.List)(_root.Get((Object)("list"))));
 //BA.debugLineNum = 142;BA.debugLine="For Each collist As Map In list";
_collist = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group14 = _list;
final int groupLen14 = group14.getSize();
for (int index14 = 0;index14 < groupLen14 ;index14++){
_collist.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group14.Get(index14)));
 //BA.debugLineNum = 143;BA.debugLine="Dim dt As Int = collist.Get(\"dt\")";
_dt = (int)(BA.ObjectToNumber(_collist.Get((Object)("dt"))));
 //BA.debugLineNum = 144;BA.debugLine="dtlist(i) = dt";
_dtlist[_i] = _dt;
 //BA.debugLineNum = 145;BA.debugLine="Dim weather As List = collist.Get(\"weather\")";
_weather = new anywheresoftware.b4a.objects.collections.List();
_weather.setObject((java.util.List)(_collist.Get((Object)("weather"))));
 //BA.debugLineNum = 147;BA.debugLine="For Each colweather As Map In weather";
_colweather = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group18 = _weather;
final int groupLen18 = group18.getSize();
for (int index18 = 0;index18 < groupLen18 ;index18++){
_colweather.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group18.Get(index18)));
 //BA.debugLineNum = 148;BA.debugLine="Dim icon As String  = colweather.Get(\"icon\")";
_icon = BA.ObjectToString(_colweather.Get((Object)("icon")));
 //BA.debugLineNum = 149;BA.debugLine="Dim description As String  = colweather.Get(\"de";
_description = BA.ObjectToString(_colweather.Get((Object)("description")));
 //BA.debugLineNum = 150;BA.debugLine="iconlist(i) = icon";
_iconlist[_i] = _icon;
 //BA.debugLineNum = 151;BA.debugLine="descriptionlist(i) = description";
_descriptionlist[_i] = _description;
 //BA.debugLineNum = 152;BA.debugLine="Log(i & \"-\" & \"dane: \" & iconlist(i) & \" \" & dt";
__c.Log(BA.NumberToString(_i)+"-"+"dane: "+_iconlist[_i]+" "+BA.NumberToString(_dtlist[_i])+" "+_descriptionlist[_i]);
 }
;
 //BA.debugLineNum = 157;BA.debugLine="Dim Main2 As Map = collist.Get(\"main\")";
_main2 = new anywheresoftware.b4a.objects.collections.Map();
_main2.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_collist.Get((Object)("main"))));
 //BA.debugLineNum = 158;BA.debugLine="Dim temp As Double = Main2.Get(\"temp\")      'tem";
_temp = (double)(BA.ObjectToNumber(_main2.Get((Object)("temp"))));
 //BA.debugLineNum = 159;BA.debugLine="templist(i) = temp";
_templist[_i] = _temp;
 //BA.debugLineNum = 160;BA.debugLine="Dim wind As Map = collist.Get(\"wind\")";
_wind = new anywheresoftware.b4a.objects.collections.Map();
_wind.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_collist.Get((Object)("wind"))));
 //BA.debugLineNum = 161;BA.debugLine="Dim speed As Double = wind.Get(\"speed\")     'prę";
_speed = (double)(BA.ObjectToNumber(_wind.Get((Object)("speed"))));
 //BA.debugLineNum = 162;BA.debugLine="speedlist(i) = speed";
_speedlist[_i] = (int) (_speed);
 //BA.debugLineNum = 164;BA.debugLine="i = i +1";
_i = (int) (_i+1);
 }
;
 //BA.debugLineNum = 168;BA.debugLine="If SubExists(callBackActivity, eventPrefix & \"_Re";
if (__c.SubExists(getActivityBA(),_callbackactivity,_eventprefix+"_Ready")) { 
 //BA.debugLineNum = 169;BA.debugLine="CallSubDelayed(callBackActivity, eventPrefix & \"";
__c.CallSubDelayed(getActivityBA(),_callbackactivity,_eventprefix+"_Ready");
 };
 //BA.debugLineNum = 172;BA.debugLine="End Sub";
return "";
}
public String  _tmrinterval_tick() throws Exception{
 //BA.debugLineNum = 80;BA.debugLine="private Sub tmrInterval_Tick()";
 //BA.debugLineNum = 81;BA.debugLine="tmrInterval.Enabled = False";
_tmrinterval.setEnabled(__c.False);
 //BA.debugLineNum = 82;BA.debugLine="DownloadWeather";
_downloadweather();
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
