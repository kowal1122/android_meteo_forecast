package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class weather extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new anywheresoftware.b4a.ShellBA(_ba, this, htSubs, "b4a.example.weather");
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

 
    public void  innerInitializeHelper(anywheresoftware.b4a.BA _ba) throws Exception{
        innerInitialize(_ba);
    }
    public Object callSub(String sub, Object sender, Object[] args) throws Exception {
        return BA.SubDelegator.SubNotFound;
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
public b4a.example.starter _starter = null;
public String  _initialize(b4a.example.weather __ref,anywheresoftware.b4a.BA _ba,Object _targetactivity,String _eventname,String _cityid,boolean _getlatest,int _refreshinterval) throws Exception{
__ref = this;
innerInitialize(_ba);
RDebugUtils.currentModule="weather";
if (Debug.shouldDelegate(ba, "initialize"))
	return (String) Debug.delegate(ba, "initialize", new Object[] {_ba,_targetactivity,_eventname,_cityid,_getlatest,_refreshinterval});
RDebugUtils.currentLine=589824;
 //BA.debugLineNum = 589824;BA.debugLine="Public Sub Initialize(TargetActivity As Object, Ev";
RDebugUtils.currentLine=589826;
 //BA.debugLineNum = 589826;BA.debugLine="weatherCityID = CityID";
__ref._weathercityid = _cityid;
RDebugUtils.currentLine=589827;
 //BA.debugLineNum = 589827;BA.debugLine="callBackActivity = TargetActivity";
__ref._callbackactivity = _targetactivity;
RDebugUtils.currentLine=589828;
 //BA.debugLineNum = 589828;BA.debugLine="eventPrefix = EventName";
__ref._eventprefix = _eventname;
RDebugUtils.currentLine=589829;
 //BA.debugLineNum = 589829;BA.debugLine="m_RefreshInterval = RefreshInterval";
__ref._m_refreshinterval = _refreshinterval;
RDebugUtils.currentLine=589830;
 //BA.debugLineNum = 589830;BA.debugLine="tmrInterval.Initialize(\"tmrInterval\", 3600)  'akt";
__ref._tmrinterval.Initialize(ba,"tmrInterval",(long) (3600));
RDebugUtils.currentLine=589831;
 //BA.debugLineNum = 589831;BA.debugLine="tmrInterval.Enabled = True";
__ref._tmrinterval.setEnabled(__c.True);
RDebugUtils.currentLine=589833;
 //BA.debugLineNum = 589833;BA.debugLine="If GetLatest Then";
if (_getlatest) { 
RDebugUtils.currentLine=589834;
 //BA.debugLineNum = 589834;BA.debugLine="DownloadWeather";
__ref._downloadweather(null);
 };
RDebugUtils.currentLine=589837;
 //BA.debugLineNum = 589837;BA.debugLine="End Sub";
return "";
}
public String  _givedtlist(b4a.example.weather __ref,int _number) throws Exception{
__ref = this;
RDebugUtils.currentModule="weather";
if (Debug.shouldDelegate(ba, "givedtlist"))
	return (String) Debug.delegate(ba, "givedtlist", new Object[] {_number});
long _expdate = 0L;
RDebugUtils.currentLine=1048576;
 //BA.debugLineNum = 1048576;BA.debugLine="Public Sub givedtlist(number As Int) As String";
RDebugUtils.currentLine=1048577;
 //BA.debugLineNum = 1048577;BA.debugLine="Dim ExpDate As Long = DateUtils.UnixTimeToTicks(d";
_expdate = _dateutils._unixtimetoticks(getActivityBA(),(long) (__ref._dtlist[_number]));
RDebugUtils.currentLine=1048578;
 //BA.debugLineNum = 1048578;BA.debugLine="DateTime.DateFormat = \"dd-MM\"";
__c.DateTime.setDateFormat("dd-MM");
RDebugUtils.currentLine=1048579;
 //BA.debugLineNum = 1048579;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
__c.DateTime.setTimeFormat("HH:mm");
RDebugUtils.currentLine=1048580;
 //BA.debugLineNum = 1048580;BA.debugLine="Return(DateTime.Time(ExpDate)  & \" \" & DateTime.D";
if (true) return (__c.DateTime.Time(_expdate)+" "+__c.DateTime.Date(_expdate));
RDebugUtils.currentLine=1048581;
 //BA.debugLineNum = 1048581;BA.debugLine="Log(DateTime.Date(ExpDate))";
__c.Log(__c.DateTime.Date(_expdate));
RDebugUtils.currentLine=1048582;
 //BA.debugLineNum = 1048582;BA.debugLine="End Sub";
return "";
}
public double  _givetemp(b4a.example.weather __ref,int _number) throws Exception{
__ref = this;
RDebugUtils.currentModule="weather";
if (Debug.shouldDelegate(ba, "givetemp"))
	return (Double) Debug.delegate(ba, "givetemp", new Object[] {_number});
RDebugUtils.currentLine=851968;
 //BA.debugLineNum = 851968;BA.debugLine="Public Sub givetemp(number As Int) As Double";
RDebugUtils.currentLine=851969;
 //BA.debugLineNum = 851969;BA.debugLine="Return(templist(number))";
if (true) return (__ref._templist[_number]);
RDebugUtils.currentLine=851970;
 //BA.debugLineNum = 851970;BA.debugLine="End Sub";
return 0;
}
public String  _giveicon(b4a.example.weather __ref,int _number) throws Exception{
__ref = this;
RDebugUtils.currentModule="weather";
if (Debug.shouldDelegate(ba, "giveicon"))
	return (String) Debug.delegate(ba, "giveicon", new Object[] {_number});
RDebugUtils.currentLine=983040;
 //BA.debugLineNum = 983040;BA.debugLine="Public Sub giveicon(number As Int) As String";
RDebugUtils.currentLine=983041;
 //BA.debugLineNum = 983041;BA.debugLine="Select iconlist(number)";
switch (BA.switchObjectToInt(__ref._iconlist[_number],"01d","01n","02d","02n","03d","03n","04d","04n","09d","09n","10d","10n","11d","11n","13d","13n")) {
case 0: 
case 1: {
RDebugUtils.currentLine=983043;
 //BA.debugLineNum = 983043;BA.debugLine="Return(\"01.png\")";
if (true) return ("01.png");
 break; }
case 2: 
case 3: {
RDebugUtils.currentLine=983045;
 //BA.debugLineNum = 983045;BA.debugLine="Return(\"02.png\")";
if (true) return ("02.png");
 break; }
case 4: 
case 5: 
case 6: 
case 7: {
RDebugUtils.currentLine=983047;
 //BA.debugLineNum = 983047;BA.debugLine="Return(\"03.png\")";
if (true) return ("03.png");
 break; }
case 8: 
case 9: 
case 10: 
case 11: {
RDebugUtils.currentLine=983049;
 //BA.debugLineNum = 983049;BA.debugLine="Return(\"09.png\")";
if (true) return ("09.png");
 break; }
case 12: 
case 13: {
RDebugUtils.currentLine=983051;
 //BA.debugLineNum = 983051;BA.debugLine="Return(\"11.png\")";
if (true) return ("11.png");
 break; }
case 14: 
case 15: {
RDebugUtils.currentLine=983053;
 //BA.debugLineNum = 983053;BA.debugLine="Return(\"13.png\")";
if (true) return ("13.png");
 break; }
default: {
RDebugUtils.currentLine=983055;
 //BA.debugLineNum = 983055;BA.debugLine="Return(\"loading.png\")";
if (true) return ("loading.png");
 break; }
}
;
RDebugUtils.currentLine=983057;
 //BA.debugLineNum = 983057;BA.debugLine="End Sub";
return "";
}
public String  _class_globals(b4a.example.weather __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="weather";
RDebugUtils.currentLine=524288;
 //BA.debugLineNum = 524288;BA.debugLine="Sub Class_Globals";
RDebugUtils.currentLine=524289;
 //BA.debugLineNum = 524289;BA.debugLine="Public weatherAPIKey As String = \"d6870f22a6a2ce7";
_weatherapikey = "d6870f22a6a2ce786ae2e31bb33a1a2f";
RDebugUtils.currentLine=524290;
 //BA.debugLineNum = 524290;BA.debugLine="Public weatherCityID As String";
_weathercityid = "";
RDebugUtils.currentLine=524291;
 //BA.debugLineNum = 524291;BA.debugLine="Private m_RefreshInterval As Int";
_m_refreshinterval = 0;
RDebugUtils.currentLine=524292;
 //BA.debugLineNum = 524292;BA.debugLine="Private tmrInterval As Timer";
_tmrinterval = new anywheresoftware.b4a.objects.Timer();
RDebugUtils.currentLine=524294;
 //BA.debugLineNum = 524294;BA.debugLine="Private callBackActivity As Object";
_callbackactivity = new Object();
RDebugUtils.currentLine=524295;
 //BA.debugLineNum = 524295;BA.debugLine="Private eventPrefix As String";
_eventprefix = "";
RDebugUtils.currentLine=524296;
 //BA.debugLineNum = 524296;BA.debugLine="Private iconlist(40) As String";
_iconlist = new String[(int) (40)];
java.util.Arrays.fill(_iconlist,"");
RDebugUtils.currentLine=524297;
 //BA.debugLineNum = 524297;BA.debugLine="Private descriptionlist(40) As String";
_descriptionlist = new String[(int) (40)];
java.util.Arrays.fill(_descriptionlist,"");
RDebugUtils.currentLine=524298;
 //BA.debugLineNum = 524298;BA.debugLine="Private dtlist(40) As Int";
_dtlist = new int[(int) (40)];
;
RDebugUtils.currentLine=524299;
 //BA.debugLineNum = 524299;BA.debugLine="Private speedlist(40) As Int";
_speedlist = new int[(int) (40)];
;
RDebugUtils.currentLine=524300;
 //BA.debugLineNum = 524300;BA.debugLine="Private templist(40) As Double";
_templist = new double[(int) (40)];
;
RDebugUtils.currentLine=524302;
 //BA.debugLineNum = 524302;BA.debugLine="Dim i As Int = 0";
_i = (int) (0);
RDebugUtils.currentLine=524303;
 //BA.debugLineNum = 524303;BA.debugLine="End Sub";
return "";
}
public String  _downloadweather(b4a.example.weather __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="weather";
if (Debug.shouldDelegate(ba, "downloadweather"))
	return (String) Debug.delegate(ba, "downloadweather", null);
anywheresoftware.b4a.samples.httputils2.httpjob _j = null;
String _url = "";
RDebugUtils.currentLine=655360;
 //BA.debugLineNum = 655360;BA.debugLine="Public Sub DownloadWeather()";
RDebugUtils.currentLine=655361;
 //BA.debugLineNum = 655361;BA.debugLine="Dim j As HttpJob";
_j = new anywheresoftware.b4a.samples.httputils2.httpjob();
RDebugUtils.currentLine=655362;
 //BA.debugLineNum = 655362;BA.debugLine="Dim url As String = \"https://api.openweathermap.o";
_url = "https://api.openweathermap.org/data/2.5/forecast";
RDebugUtils.currentLine=655365;
 //BA.debugLineNum = 655365;BA.debugLine="j.Initialize(\"main\", Me)";
_j._initialize(ba,"main",this);
RDebugUtils.currentLine=655366;
 //BA.debugLineNum = 655366;BA.debugLine="j.Download2(url, Array As String (\"id\", weatherCi";
_j._download2(_url,new String[]{"id",__ref._weathercityid,"units","metric","appid",__ref._weatherapikey,"lang","pl"});
RDebugUtils.currentLine=655367;
 //BA.debugLineNum = 655367;BA.debugLine="Log(\"download: \" & j)";
__c.Log("download: "+BA.ObjectToString(_j));
RDebugUtils.currentLine=655369;
 //BA.debugLineNum = 655369;BA.debugLine="End Sub";
return "";
}
public String  _givedescription(b4a.example.weather __ref,int _number) throws Exception{
__ref = this;
RDebugUtils.currentModule="weather";
if (Debug.shouldDelegate(ba, "givedescription"))
	return (String) Debug.delegate(ba, "givedescription", new Object[] {_number});
RDebugUtils.currentLine=917504;
 //BA.debugLineNum = 917504;BA.debugLine="Public Sub givedescription(number As Int) As Strin";
RDebugUtils.currentLine=917505;
 //BA.debugLineNum = 917505;BA.debugLine="Return(descriptionlist(number))";
if (true) return (__ref._descriptionlist[_number]);
RDebugUtils.currentLine=917506;
 //BA.debugLineNum = 917506;BA.debugLine="End Sub";
return "";
}
public String  _jobdone(b4a.example.weather __ref,anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
__ref = this;
RDebugUtils.currentModule="weather";
if (Debug.shouldDelegate(ba, "jobdone"))
	return (String) Debug.delegate(ba, "jobdone", new Object[] {_job});
RDebugUtils.currentLine=720896;
 //BA.debugLineNum = 720896;BA.debugLine="Private Sub JobDone(Job As HttpJob)";
RDebugUtils.currentLine=720898;
 //BA.debugLineNum = 720898;BA.debugLine="If Job.Success = False Then";
if (_job._success==__c.False) { 
RDebugUtils.currentLine=720899;
 //BA.debugLineNum = 720899;BA.debugLine="Select Job.JobName";
switch (BA.switchObjectToInt(_job._jobname,"main")) {
case 0: {
RDebugUtils.currentLine=720901;
 //BA.debugLineNum = 720901;BA.debugLine="If SubExists(callBackActivity, eventPrefix & \"";
if (__c.SubExists(getActivityBA(),__ref._callbackactivity,__ref._eventprefix+"_Failed")) { 
RDebugUtils.currentLine=720902;
 //BA.debugLineNum = 720902;BA.debugLine="CallSubDelayed(callBackActivity, eventPrefix";
__c.CallSubDelayed(getActivityBA(),__ref._callbackactivity,__ref._eventprefix+"_Failed");
 };
 break; }
}
;
RDebugUtils.currentLine=720905;
 //BA.debugLineNum = 720905;BA.debugLine="Job.Release";
_job._release();
RDebugUtils.currentLine=720906;
 //BA.debugLineNum = 720906;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=720909;
 //BA.debugLineNum = 720909;BA.debugLine="Select Job.JobName";
switch (BA.switchObjectToInt(_job._jobname,"main")) {
case 0: {
RDebugUtils.currentLine=720911;
 //BA.debugLineNum = 720911;BA.debugLine="ParseWeatherData(Job.GetString)";
__ref._parseweatherdata(null,_job._getstring());
RDebugUtils.currentLine=720912;
 //BA.debugLineNum = 720912;BA.debugLine="If m_RefreshInterval > 0 Then";
if (__ref._m_refreshinterval>0) { 
RDebugUtils.currentLine=720913;
 //BA.debugLineNum = 720913;BA.debugLine="tmrInterval.Interval = m_RefreshInterval * 600";
__ref._tmrinterval.setInterval((long) (__ref._m_refreshinterval*60000));
RDebugUtils.currentLine=720914;
 //BA.debugLineNum = 720914;BA.debugLine="tmrInterval.Enabled = True";
__ref._tmrinterval.setEnabled(__c.True);
 };
 break; }
}
;
RDebugUtils.currentLine=720918;
 //BA.debugLineNum = 720918;BA.debugLine="Job.Release";
_job._release();
RDebugUtils.currentLine=720920;
 //BA.debugLineNum = 720920;BA.debugLine="End Sub";
return "";
}
public String  _parseweatherdata(b4a.example.weather __ref,String _jsondata) throws Exception{
__ref = this;
RDebugUtils.currentModule="weather";
if (Debug.shouldDelegate(ba, "parseweatherdata"))
	return (String) Debug.delegate(ba, "parseweatherdata", new Object[] {_jsondata});
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
RDebugUtils.currentLine=1114112;
 //BA.debugLineNum = 1114112;BA.debugLine="Public Sub ParseWeatherData(jsonData As String)";
RDebugUtils.currentLine=1114113;
 //BA.debugLineNum = 1114113;BA.debugLine="Dim js As JSONParser";
_js = new anywheresoftware.b4a.objects.collections.JSONParser();
RDebugUtils.currentLine=1114114;
 //BA.debugLineNum = 1114114;BA.debugLine="Dim root As Map";
_root = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=1114115;
 //BA.debugLineNum = 1114115;BA.debugLine="Dim map As Map";
_map = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=1114117;
 //BA.debugLineNum = 1114117;BA.debugLine="map.Initialize";
_map.Initialize();
RDebugUtils.currentLine=1114119;
 //BA.debugLineNum = 1114119;BA.debugLine="Log(\"jsondata:\" & jsonData)";
__c.Log("jsondata:"+_jsondata);
RDebugUtils.currentLine=1114120;
 //BA.debugLineNum = 1114120;BA.debugLine="js.Initialize(jsonData)";
_js.Initialize(_jsondata);
RDebugUtils.currentLine=1114121;
 //BA.debugLineNum = 1114121;BA.debugLine="root.Initialize";
_root.Initialize();
RDebugUtils.currentLine=1114122;
 //BA.debugLineNum = 1114122;BA.debugLine="root = js.NextObject";
_root = _js.NextObject();
RDebugUtils.currentLine=1114123;
 //BA.debugLineNum = 1114123;BA.debugLine="Log(\"root:\" & root)";
__c.Log("root:"+BA.ObjectToString(_root));
RDebugUtils.currentLine=1114125;
 //BA.debugLineNum = 1114125;BA.debugLine="Dim parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
RDebugUtils.currentLine=1114128;
 //BA.debugLineNum = 1114128;BA.debugLine="parser.Initialize(jsonData)";
_parser.Initialize(_jsondata);
RDebugUtils.currentLine=1114129;
 //BA.debugLineNum = 1114129;BA.debugLine="Dim root As Map = parser.NextObject";
_root = new anywheresoftware.b4a.objects.collections.Map();
_root = _parser.NextObject();
RDebugUtils.currentLine=1114130;
 //BA.debugLineNum = 1114130;BA.debugLine="Dim list As List = root.Get(\"list\")";
_list = new anywheresoftware.b4a.objects.collections.List();
_list.setObject((java.util.List)(_root.Get((Object)("list"))));
RDebugUtils.currentLine=1114131;
 //BA.debugLineNum = 1114131;BA.debugLine="For Each collist As Map In list";
_collist = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group14 = _list;
final int groupLen14 = group14.getSize();
for (int index14 = 0;index14 < groupLen14 ;index14++){
_collist.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group14.Get(index14)));
RDebugUtils.currentLine=1114132;
 //BA.debugLineNum = 1114132;BA.debugLine="Dim dt As Int = collist.Get(\"dt\")";
_dt = (int)(BA.ObjectToNumber(_collist.Get((Object)("dt"))));
RDebugUtils.currentLine=1114133;
 //BA.debugLineNum = 1114133;BA.debugLine="dtlist(i) = dt";
__ref._dtlist[__ref._i] = _dt;
RDebugUtils.currentLine=1114134;
 //BA.debugLineNum = 1114134;BA.debugLine="Dim weather As List = collist.Get(\"weather\")";
_weather = new anywheresoftware.b4a.objects.collections.List();
_weather.setObject((java.util.List)(_collist.Get((Object)("weather"))));
RDebugUtils.currentLine=1114136;
 //BA.debugLineNum = 1114136;BA.debugLine="For Each colweather As Map In weather";
_colweather = new anywheresoftware.b4a.objects.collections.Map();
final anywheresoftware.b4a.BA.IterableList group18 = _weather;
final int groupLen18 = group18.getSize();
for (int index18 = 0;index18 < groupLen18 ;index18++){
_colweather.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(group18.Get(index18)));
RDebugUtils.currentLine=1114137;
 //BA.debugLineNum = 1114137;BA.debugLine="Dim icon As String  = colweather.Get(\"icon\")";
_icon = BA.ObjectToString(_colweather.Get((Object)("icon")));
RDebugUtils.currentLine=1114138;
 //BA.debugLineNum = 1114138;BA.debugLine="Dim description As String  = colweather.Get(\"de";
_description = BA.ObjectToString(_colweather.Get((Object)("description")));
RDebugUtils.currentLine=1114139;
 //BA.debugLineNum = 1114139;BA.debugLine="iconlist(i) = icon";
__ref._iconlist[__ref._i] = _icon;
RDebugUtils.currentLine=1114140;
 //BA.debugLineNum = 1114140;BA.debugLine="descriptionlist(i) = description";
__ref._descriptionlist[__ref._i] = _description;
RDebugUtils.currentLine=1114141;
 //BA.debugLineNum = 1114141;BA.debugLine="Log(i & \"-\" & \"dane: \" & iconlist(i) & \" \" & dt";
__c.Log(BA.NumberToString(__ref._i)+"-"+"dane: "+__ref._iconlist[__ref._i]+" "+BA.NumberToString(__ref._dtlist[__ref._i])+" "+__ref._descriptionlist[__ref._i]);
 }
;
RDebugUtils.currentLine=1114146;
 //BA.debugLineNum = 1114146;BA.debugLine="Dim Main2 As Map = collist.Get(\"main\")";
_main2 = new anywheresoftware.b4a.objects.collections.Map();
_main2.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_collist.Get((Object)("main"))));
RDebugUtils.currentLine=1114147;
 //BA.debugLineNum = 1114147;BA.debugLine="Dim temp As Double = Main2.Get(\"temp\")      'tem";
_temp = (double)(BA.ObjectToNumber(_main2.Get((Object)("temp"))));
RDebugUtils.currentLine=1114148;
 //BA.debugLineNum = 1114148;BA.debugLine="templist(i) = temp";
__ref._templist[__ref._i] = _temp;
RDebugUtils.currentLine=1114149;
 //BA.debugLineNum = 1114149;BA.debugLine="Dim wind As Map = collist.Get(\"wind\")";
_wind = new anywheresoftware.b4a.objects.collections.Map();
_wind.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_collist.Get((Object)("wind"))));
RDebugUtils.currentLine=1114150;
 //BA.debugLineNum = 1114150;BA.debugLine="Dim speed As Double = wind.Get(\"speed\")     'prę";
_speed = (double)(BA.ObjectToNumber(_wind.Get((Object)("speed"))));
RDebugUtils.currentLine=1114151;
 //BA.debugLineNum = 1114151;BA.debugLine="speedlist(i) = speed";
__ref._speedlist[__ref._i] = (int) (_speed);
RDebugUtils.currentLine=1114153;
 //BA.debugLineNum = 1114153;BA.debugLine="i = i +1";
__ref._i = (int) (__ref._i+1);
 }
;
RDebugUtils.currentLine=1114157;
 //BA.debugLineNum = 1114157;BA.debugLine="If SubExists(callBackActivity, eventPrefix & \"_Re";
if (__c.SubExists(getActivityBA(),__ref._callbackactivity,__ref._eventprefix+"_Ready")) { 
RDebugUtils.currentLine=1114158;
 //BA.debugLineNum = 1114158;BA.debugLine="CallSubDelayed(callBackActivity, eventPrefix & \"";
__c.CallSubDelayed(getActivityBA(),__ref._callbackactivity,__ref._eventprefix+"_Ready");
 };
RDebugUtils.currentLine=1114161;
 //BA.debugLineNum = 1114161;BA.debugLine="End Sub";
return "";
}
public String  _tmrinterval_tick(b4a.example.weather __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="weather";
if (Debug.shouldDelegate(ba, "tmrinterval_tick"))
	return (String) Debug.delegate(ba, "tmrinterval_tick", null);
RDebugUtils.currentLine=786432;
 //BA.debugLineNum = 786432;BA.debugLine="private Sub tmrInterval_Tick()";
RDebugUtils.currentLine=786433;
 //BA.debugLineNum = 786433;BA.debugLine="tmrInterval.Enabled = False";
__ref._tmrinterval.setEnabled(__c.False);
RDebugUtils.currentLine=786434;
 //BA.debugLineNum = 786434;BA.debugLine="DownloadWeather";
__ref._downloadweather(null);
RDebugUtils.currentLine=786435;
 //BA.debugLineNum = 786435;BA.debugLine="End Sub";
return "";
}
}