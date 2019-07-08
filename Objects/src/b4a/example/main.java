package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEvent(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        Object[] o;
        if (permissions.length > 0)
            o = new Object[] {permissions[0], grantResults[0] == 0};
        else
            o = new Object[] {"", false};
        processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.Serial _serial1 = null;
public static anywheresoftware.b4a.objects.Serial.BluetoothAdmin _admin = null;
public static anywheresoftware.b4a.phone.Phone.PhoneWakeState _ekran = null;
public b4a.example.weather _wt = null;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label4 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgweather2 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgweather1 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgweather3 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgweather4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _temp1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _temp2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _temp3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _temp4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _godzina = null;
public anywheresoftware.b4a.objects.Timer _timer1 = null;
public anywheresoftware.b4a.objects.Timer _timer2_json = null;
public static int _odliczaj = 0;
public static boolean _cykl = false;
public static String _godzina_text = "";
public anywheresoftware.b4a.objects.LabelWrapper _test = null;
public static long _zmienna = 0L;
public static float _odliczanie = 0f;
public anywheresoftware.b4a.objects.Timer _timer2 = null;
public static boolean _connected = false;
public anywheresoftware.b4a.objects.streams.File.TextReaderWrapper _textreader1 = null;
public anywheresoftware.b4a.objects.streams.File.TextWriterWrapper _textwriter1 = null;
public static String _odczyt_bt = "";
public static String _odczyt = "";
public static double _temp_max = 0;
public static double _temp_min = 0;
public static boolean _urz = false;
public static boolean _zmiana = false;
public static long _i = 0L;
public anywheresoftware.b4a.objects.LabelWrapper _tempwew = null;
public anywheresoftware.b4a.objects.LabelWrapper _tempzew = null;
public anywheresoftware.b4a.objects.LabelWrapper _woodstock1 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public b4a.example.dateutils _dateutils = null;
public b4a.example.powrot _powrot = null;
public b4a.example.starter _starter = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}
public static class _nameandmac{
public boolean IsInitialized;
public String Name;
public String Mac;
public void Initialize() {
IsInitialized = true;
Name = "";
Mac = "";
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4a.phone.Phone.PhoneId _phoneid = null;
anywheresoftware.b4a.phone.PhoneEvents _zasilanie = null;
String _maxmin = "";
long _data = 0L;
 //BA.debugLineNum = 68;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 69;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 70;BA.debugLine="admin.Initialize(\"admin\")";
_admin.Initialize(processBA,"admin");
 //BA.debugLineNum = 71;BA.debugLine="If admin.IsEnabled = False Then";
if (_admin.IsEnabled()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 72;BA.debugLine="admin.Enable";
_admin.Enable();
 //BA.debugLineNum = 73;BA.debugLine="Do Until admin.IsEnabled";
while (!(_admin.IsEnabled())) {
 //BA.debugLineNum = 74;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 }
;
 };
 //BA.debugLineNum = 77;BA.debugLine="Serial1.Initialize(\"Serial1\")";
_serial1.Initialize("Serial1");
 //BA.debugLineNum = 78;BA.debugLine="Timer1.Initialize(\"Timer1\", 500)";
mostCurrent._timer1.Initialize(processBA,"Timer1",(long) (500));
 //BA.debugLineNum = 79;BA.debugLine="timer2_json.Initialize(\"timer2_json\", 150000) '";
mostCurrent._timer2_json.Initialize(processBA,"timer2_json",(long) (150000));
 //BA.debugLineNum = 80;BA.debugLine="Timer2.Initialize(\"Timer2\", 4000)";
mostCurrent._timer2.Initialize(processBA,"Timer2",(long) (4000));
 };
 //BA.debugLineNum = 83;BA.debugLine="Activity.LoadLayout(\"2\")";
mostCurrent._activity.LoadLayout("2",mostCurrent.activityBA);
 //BA.debugLineNum = 84;BA.debugLine="wt.Initialize(Me, \"wt\", \"7530858\", True, 10) '753";
mostCurrent._wt._initialize(processBA,main.getObject(),"wt","7530858",anywheresoftware.b4a.keywords.Common.True,(int) (10));
 //BA.debugLineNum = 86;BA.debugLine="Timer1.Enabled = True";
mostCurrent._timer1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 87;BA.debugLine="timer2_json.Enabled = True";
mostCurrent._timer2_json.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 88;BA.debugLine="Timer2.Enabled = False";
mostCurrent._timer2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 90;BA.debugLine="Dim PhoneId As PhoneId";
_phoneid = new anywheresoftware.b4a.phone.Phone.PhoneId();
 //BA.debugLineNum = 91;BA.debugLine="Dim zasilanie As PhoneEvents";
_zasilanie = new anywheresoftware.b4a.phone.PhoneEvents();
 //BA.debugLineNum = 92;BA.debugLine="zasilanie.InitializeWithPhoneState(\"zasilanie\", P";
_zasilanie.InitializeWithPhoneState(processBA,"zasilanie",_phoneid);
 //BA.debugLineNum = 94;BA.debugLine="ekran.KeepAlive (True)";
_ekran.KeepAlive(processBA,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 96;BA.debugLine="Dim maxmin As String, i As Long";
_maxmin = "";
_i = 0L;
 //BA.debugLineNum = 97;BA.debugLine="Try";
try { //BA.debugLineNum = 98;BA.debugLine="maxmin = File.ReadString(File.DirRootExternal, \"";
_maxmin = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"MaxMin.txt");
 //BA.debugLineNum = 99;BA.debugLine="Dim data As Long";
_data = 0L;
 //BA.debugLineNum = 100;BA.debugLine="data = File.LastModified(File.DirRootExternal, \"";
_data = anywheresoftware.b4a.keywords.Common.File.LastModified(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"MaxMin.txt");
 //BA.debugLineNum = 101;BA.debugLine="If DateTime.Date(data) = DateTime.Date(DateTime.";
if ((anywheresoftware.b4a.keywords.Common.DateTime.Date(_data)).equals(anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))) { 
 //BA.debugLineNum = 102;BA.debugLine="i = maxmin.IndexOf(\";\")";
_i = (long) (_maxmin.indexOf(";"));
 //BA.debugLineNum = 103;BA.debugLine="temp_min =  NumberFormat(maxmin.SubString(i+1),";
_temp_min = (double)(Double.parseDouble(anywheresoftware.b4a.keywords.Common.NumberFormat((double)(Double.parseDouble(_maxmin.substring((int) (_i+1)))),(int) (1),(int) (1))));
 //BA.debugLineNum = 104;BA.debugLine="temp_max =  NumberFormat(maxmin.SubString2(0, i";
_temp_max = (double)(Double.parseDouble(anywheresoftware.b4a.keywords.Common.NumberFormat((double)(Double.parseDouble(_maxmin.substring((int) (0),(int) (_i)))),(int) (1),(int) (1))));
 }else {
 //BA.debugLineNum = 106;BA.debugLine="temp_min =  100";
_temp_min = 100;
 //BA.debugLineNum = 107;BA.debugLine="temp_max = -100";
_temp_max = -100;
 };
 } 
       catch (Exception e37) {
			processBA.setLastException(e37); //BA.debugLineNum = 110;BA.debugLine="temp_min =  100";
_temp_min = 100;
 //BA.debugLineNum = 111;BA.debugLine="temp_max = -100";
_temp_max = -100;
 };
 //BA.debugLineNum = 113;BA.debugLine="admin.StartDiscovery";
_admin.StartDiscovery();
 //BA.debugLineNum = 115;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 120;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 121;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 117;BA.debugLine="Sub Activity_Resume()";
 //BA.debugLineNum = 118;BA.debugLine="End Sub";
return "";
}
public static String  _admin_devicefound(String _name,String _macaddress) throws Exception{
anywheresoftware.b4a.objects.collections.Map _paireddevices = null;
anywheresoftware.b4a.objects.collections.List _founddevices = null;
b4a.example.main._nameandmac _nm = null;
 //BA.debugLineNum = 186;BA.debugLine="Sub admin_DeviceFound (Name As String, MacAddress";
 //BA.debugLineNum = 187;BA.debugLine="Dim PairedDevices As Map, foundDevices As List";
_paireddevices = new anywheresoftware.b4a.objects.collections.Map();
_founddevices = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 188;BA.debugLine="Dim nm As NameAndMac";
_nm = new b4a.example.main._nameandmac();
 //BA.debugLineNum = 189;BA.debugLine="foundDevices.Initialize";
_founddevices.Initialize();
 //BA.debugLineNum = 190;BA.debugLine="nm.Name = Name";
_nm.Name = _name;
 //BA.debugLineNum = 191;BA.debugLine="nm.Mac = MacAddress";
_nm.Mac = _macaddress;
 //BA.debugLineNum = 192;BA.debugLine="foundDevices.Add( nm )";
_founddevices.Add((Object)(_nm));
 //BA.debugLineNum = 193;BA.debugLine="If foundDevices.Size > 0 Then";
if (_founddevices.getSize()>0) { 
 //BA.debugLineNum = 194;BA.debugLine="For i= 0 To foundDevices.Size-1";
{
final long step8 = 1;
final long limit8 = (long) (_founddevices.getSize()-1);
for (_i = (long) (0) ; (step8 > 0 && _i <= limit8) || (step8 < 0 && _i >= limit8); _i = ((long)(0 + _i + step8)) ) {
 //BA.debugLineNum = 195;BA.debugLine="nm = foundDevices.Get(i)";
_nm = (b4a.example.main._nameandmac)(_founddevices.Get((int) (_i)));
 //BA.debugLineNum = 196;BA.debugLine="If nm.Name = \"HC-05\" Then";
if ((_nm.Name).equals("HC-05")) { 
 //BA.debugLineNum = 197;BA.debugLine="PairedDevices = Serial1.GetPairedDevices";
_paireddevices = _serial1.GetPairedDevices();
 //BA.debugLineNum = 198;BA.debugLine="Serial1.Connect(PairedDevices.Get(\"HC-05\"))";
_serial1.Connect(processBA,BA.ObjectToString(_paireddevices.Get((Object)("HC-05"))));
 //BA.debugLineNum = 199;BA.debugLine="urz = True";
_urz = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 200;BA.debugLine="connected = True";
_connected = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 201;BA.debugLine="Timer2.Enabled = True";
mostCurrent._timer2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 203;BA.debugLine="If urz = True Then Exit";
if (_urz==anywheresoftware.b4a.keywords.Common.True) { 
if (true) break;};
 //BA.debugLineNum = 204;BA.debugLine="urz = False";
_urz = anywheresoftware.b4a.keywords.Common.False;
 }
};
 };
 //BA.debugLineNum = 207;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 24;BA.debugLine="Private wt As Weather";
mostCurrent._wt = new b4a.example.weather();
 //BA.debugLineNum = 26;BA.debugLine="Private Label1 As Label";
mostCurrent._label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private Label2 As Label";
mostCurrent._label2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private Label3 As Label";
mostCurrent._label3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private Label4 As Label";
mostCurrent._label4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private imgWeather2 As ImageView";
mostCurrent._imgweather2 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private imgWeather1 As ImageView";
mostCurrent._imgweather1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private imgWeather3 As ImageView";
mostCurrent._imgweather3 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private imgWeather4 As ImageView";
mostCurrent._imgweather4 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private temp1 As Label";
mostCurrent._temp1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private temp2 As Label";
mostCurrent._temp2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private temp3 As Label";
mostCurrent._temp3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private temp4 As Label";
mostCurrent._temp4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private godzina As Label";
mostCurrent._godzina = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Dim Timer1 As Timer";
mostCurrent._timer1 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 41;BA.debugLine="Dim timer2_json As Timer";
mostCurrent._timer2_json = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 43;BA.debugLine="Dim odliczaj As Int = 0";
_odliczaj = (int) (0);
 //BA.debugLineNum = 44;BA.debugLine="Dim cykl As Boolean";
_cykl = false;
 //BA.debugLineNum = 45;BA.debugLine="Dim godzina_text As String";
mostCurrent._godzina_text = "";
 //BA.debugLineNum = 47;BA.debugLine="Private test As Label";
mostCurrent._test = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Dim zmienna  As Long";
_zmienna = 0L;
 //BA.debugLineNum = 49;BA.debugLine="Dim odliczanie As Float";
_odliczanie = 0f;
 //BA.debugLineNum = 52;BA.debugLine="Dim Timer2 As Timer";
mostCurrent._timer2 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 53;BA.debugLine="Dim connected As Boolean";
_connected = false;
 //BA.debugLineNum = 54;BA.debugLine="Dim TextReader1 As TextReader";
mostCurrent._textreader1 = new anywheresoftware.b4a.objects.streams.File.TextReaderWrapper();
 //BA.debugLineNum = 55;BA.debugLine="Dim TextWriter1 As TextWriter";
mostCurrent._textwriter1 = new anywheresoftware.b4a.objects.streams.File.TextWriterWrapper();
 //BA.debugLineNum = 56;BA.debugLine="Dim odczyt_BT As String";
mostCurrent._odczyt_bt = "";
 //BA.debugLineNum = 57;BA.debugLine="Dim odczyt As String";
mostCurrent._odczyt = "";
 //BA.debugLineNum = 58;BA.debugLine="Dim temp_max As Double, temp_min As Double";
_temp_max = 0;
_temp_min = 0;
 //BA.debugLineNum = 59;BA.debugLine="Dim urz As Boolean";
_urz = false;
 //BA.debugLineNum = 60;BA.debugLine="Dim zmiana As Boolean";
_zmiana = false;
 //BA.debugLineNum = 61;BA.debugLine="Dim i As Long";
_i = 0L;
 //BA.debugLineNum = 63;BA.debugLine="Private tempwew As Label";
mostCurrent._tempwew = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 64;BA.debugLine="Private tempzew As Label";
mostCurrent._tempzew = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 65;BA.debugLine="Private woodstock1 As Label";
mostCurrent._woodstock1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 66;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        anywheresoftware.b4a.samples.httputils2.httputils2service._process_globals();
b4a.example.dateutils._process_globals();
main._process_globals();
powrot._process_globals();
starter._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 16;BA.debugLine="Dim Serial1 As Serial";
_serial1 = new anywheresoftware.b4a.objects.Serial();
 //BA.debugLineNum = 17;BA.debugLine="Type NameAndMac( Name As String, Mac As String )";
;
 //BA.debugLineNum = 18;BA.debugLine="Dim admin As BluetoothAdmin";
_admin = new anywheresoftware.b4a.objects.Serial.BluetoothAdmin();
 //BA.debugLineNum = 19;BA.debugLine="Dim ekran As PhoneWakeState";
_ekran = new anywheresoftware.b4a.phone.Phone.PhoneWakeState();
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public static String  _serial1_connected(boolean _success) throws Exception{
 //BA.debugLineNum = 210;BA.debugLine="Sub Serial1_Connected (Success As Boolean)";
 //BA.debugLineNum = 211;BA.debugLine="If Success Then";
if (_success) { 
 //BA.debugLineNum = 212;BA.debugLine="TextReader1.Initialize(Serial1.InputStream)";
mostCurrent._textreader1.Initialize(_serial1.getInputStream());
 //BA.debugLineNum = 213;BA.debugLine="TextWriter1.Initialize(Serial1.OutputStream)";
mostCurrent._textwriter1.Initialize(_serial1.getOutputStream());
 //BA.debugLineNum = 214;BA.debugLine="TextWriter1.Flush";
mostCurrent._textwriter1.Flush();
 //BA.debugLineNum = 215;BA.debugLine="connected = True";
_connected = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 216;BA.debugLine="Timer2.Enabled = True";
mostCurrent._timer2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 217;BA.debugLine="ToastMessageShow(\"Połączono!\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("Połączono!",anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 219;BA.debugLine="ToastMessageShow(LastException.Message, False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 221;BA.debugLine="urz = False";
_urz = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 222;BA.debugLine="admin.StartDiscovery";
_admin.StartDiscovery();
 };
 //BA.debugLineNum = 224;BA.debugLine="End Sub";
return "";
}
public static String  _showweather() throws Exception{
 //BA.debugLineNum = 133;BA.debugLine="Sub ShowWeather()";
 //BA.debugLineNum = 135;BA.debugLine="Label1.Text = wt.givedtlist(0)";
mostCurrent._label1.setText((Object)(mostCurrent._wt._givedtlist((int) (0))));
 //BA.debugLineNum = 136;BA.debugLine="Label2.Text = wt.givedtlist(1)";
mostCurrent._label2.setText((Object)(mostCurrent._wt._givedtlist((int) (1))));
 //BA.debugLineNum = 137;BA.debugLine="Label3.Text = wt.givedtlist(2)";
mostCurrent._label3.setText((Object)(mostCurrent._wt._givedtlist((int) (2))));
 //BA.debugLineNum = 138;BA.debugLine="Label4.Text = wt.givedtlist(3)";
mostCurrent._label4.setText((Object)(mostCurrent._wt._givedtlist((int) (3))));
 //BA.debugLineNum = 141;BA.debugLine="temp1.Text = wt.givetemp(0) & \"°C\"";
mostCurrent._temp1.setText((Object)(BA.NumberToString(mostCurrent._wt._givetemp((int) (0)))+"°C"));
 //BA.debugLineNum = 142;BA.debugLine="temp2.Text = wt.givetemp(1) & \"°C\"";
mostCurrent._temp2.setText((Object)(BA.NumberToString(mostCurrent._wt._givetemp((int) (1)))+"°C"));
 //BA.debugLineNum = 143;BA.debugLine="temp3.Text = wt.givetemp(2) & \"°C\"";
mostCurrent._temp3.setText((Object)(BA.NumberToString(mostCurrent._wt._givetemp((int) (2)))+"°C"));
 //BA.debugLineNum = 144;BA.debugLine="temp4.Text = wt.givetemp(3) & \"°C\"";
mostCurrent._temp4.setText((Object)(BA.NumberToString(mostCurrent._wt._givetemp((int) (3)))+"°C"));
 //BA.debugLineNum = 146;BA.debugLine="imgWeather1.Bitmap = LoadBitmap(File.DirAssets, w";
mostCurrent._imgweather1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),mostCurrent._wt._giveicon((int) (0))).getObject()));
 //BA.debugLineNum = 147;BA.debugLine="imgWeather2.Bitmap = LoadBitmap(File.DirAssets, w";
mostCurrent._imgweather2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),mostCurrent._wt._giveicon((int) (1))).getObject()));
 //BA.debugLineNum = 148;BA.debugLine="imgWeather3.Bitmap = LoadBitmap(File.DirAssets, w";
mostCurrent._imgweather3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),mostCurrent._wt._giveicon((int) (2))).getObject()));
 //BA.debugLineNum = 149;BA.debugLine="imgWeather4.Bitmap = LoadBitmap(File.DirAssets, w";
mostCurrent._imgweather4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),mostCurrent._wt._giveicon((int) (3))).getObject()));
 //BA.debugLineNum = 150;BA.debugLine="End Sub";
return "";
}
public static String  _timer1_tick() throws Exception{
long _wood_stamp = 0L;
long _now_stamp = 0L;
 //BA.debugLineNum = 152;BA.debugLine="Sub timer1_Tick";
 //BA.debugLineNum = 156;BA.debugLine="Dim wood_stamp As Long = 1564380000000  - (360000";
_wood_stamp = (long) (1564380000000L-(3600000*24));
 //BA.debugLineNum = 157;BA.debugLine="Dim now_stamp As Long = DateTime.Now";
_now_stamp = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 159;BA.debugLine="zmienna = wood_stamp - now_stamp";
_zmienna = (long) (_wood_stamp-_now_stamp);
 //BA.debugLineNum = 160;BA.debugLine="DateTime.DateFormat = \"HH dd:MM:yyyy\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("HH dd:MM:yyyy");
 //BA.debugLineNum = 163;BA.debugLine="DateTime.DateFormat = \"dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("dd");
 //BA.debugLineNum = 164;BA.debugLine="DateTime.TimeFormat = \"HH:mm:ss\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm:ss");
 //BA.debugLineNum = 165;BA.debugLine="woodstock1.text = DateTime.Time(zmienna) & CRLF &";
mostCurrent._woodstock1.setText((Object)(anywheresoftware.b4a.keywords.Common.DateTime.Time(_zmienna)+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.DateTime.Date(_zmienna)+" dni"));
 //BA.debugLineNum = 168;BA.debugLine="cykl = Not (cykl)";
_cykl = anywheresoftware.b4a.keywords.Common.Not(_cykl);
 //BA.debugLineNum = 169;BA.debugLine="godzina_text = NumberFormat(DateTime.GetHour(Dat";
mostCurrent._godzina_text = anywheresoftware.b4a.keywords.Common.NumberFormat(anywheresoftware.b4a.keywords.Common.DateTime.GetHour(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),(int) (2),(int) (0))+":"+anywheresoftware.b4a.keywords.Common.NumberFormat(anywheresoftware.b4a.keywords.Common.DateTime.GetMinute(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),(int) (2),(int) (0));
 //BA.debugLineNum = 171;BA.debugLine="If cykl= True Then";
if (_cykl==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 172;BA.debugLine="godzina.Text = godzina_text & \".\"";
mostCurrent._godzina.setText((Object)(mostCurrent._godzina_text+"."));
 }else {
 //BA.debugLineNum = 174;BA.debugLine="godzina.Text = godzina_text & \"\"";
mostCurrent._godzina.setText((Object)(mostCurrent._godzina_text+""));
 };
 //BA.debugLineNum = 177;BA.debugLine="End Sub";
return "";
}
public static String  _timer2_json_tick() throws Exception{
 //BA.debugLineNum = 179;BA.debugLine="Sub timer2_json_Tick";
 //BA.debugLineNum = 180;BA.debugLine="Log(\"uruchomienie timer2_json_Tick\")";
anywheresoftware.b4a.keywords.Common.Log("uruchomienie timer2_json_Tick");
 //BA.debugLineNum = 181;BA.debugLine="ShowWeather";
_showweather();
 //BA.debugLineNum = 183;BA.debugLine="End Sub";
return "";
}
public static String  _timer2_tick() throws Exception{
double _war = 0;
double _liczba = 0;
String _godz = "";
String _minuta = "";
 //BA.debugLineNum = 228;BA.debugLine="Sub timer2_Tick";
 //BA.debugLineNum = 229;BA.debugLine="If connected Then";
if (_connected) { 
 //BA.debugLineNum = 230;BA.debugLine="Try";
try { //BA.debugLineNum = 231;BA.debugLine="TextWriter1.WriteLine(\"O\")";
mostCurrent._textwriter1.WriteLine("O");
 //BA.debugLineNum = 232;BA.debugLine="TextWriter1.Flush";
mostCurrent._textwriter1.Flush();
 //BA.debugLineNum = 233;BA.debugLine="Do Until TextReader1.Ready 'check if there is a";
while (!(mostCurrent._textreader1.Ready())) {
 //BA.debugLineNum = 234;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 }
;
 //BA.debugLineNum = 236;BA.debugLine="odczyt_BT = TextReader1.ReadLine";
mostCurrent._odczyt_bt = mostCurrent._textreader1.ReadLine();
 //BA.debugLineNum = 237;BA.debugLine="If connected  Then";
if (_connected) { 
 //BA.debugLineNum = 238;BA.debugLine="i = odczyt_BT.IndexOf(\";\")";
_i = (long) (mostCurrent._odczyt_bt.indexOf(";"));
 //BA.debugLineNum = 239;BA.debugLine="odczyt = odczyt_BT.SubString(i+1)";
mostCurrent._odczyt = mostCurrent._odczyt_bt.substring((int) (_i+1));
 //BA.debugLineNum = 240;BA.debugLine="If odczyt <> \"Blad\" Then";
if ((mostCurrent._odczyt).equals("Blad") == false) { 
 //BA.debugLineNum = 241;BA.debugLine="Dim war As Double";
_war = 0;
 //BA.debugLineNum = 242;BA.debugLine="war = odczyt";
_war = (double)(Double.parseDouble(mostCurrent._odczyt));
 //BA.debugLineNum = 244;BA.debugLine="If war > 0 Then";
if (_war>0) { 
 //BA.debugLineNum = 245;BA.debugLine="tempzew.Text = \"+\" & odczyt_BT.SubString(i+1";
mostCurrent._tempzew.setText((Object)("+"+mostCurrent._odczyt_bt.substring((int) (_i+1))+"°C"));
 }else {
 //BA.debugLineNum = 247;BA.debugLine="tempzew.Text = CRLF & odczyt_BT.SubString(i+";
mostCurrent._tempzew.setText((Object)(anywheresoftware.b4a.keywords.Common.CRLF+mostCurrent._odczyt_bt.substring((int) (_i+1))+"°C"));
 };
 //BA.debugLineNum = 249;BA.debugLine="If odczyt_BT.SubString2(0, i) <> \"Blad\" Then";
if ((mostCurrent._odczyt_bt.substring((int) (0),(int) (_i))).equals("Blad") == false) { 
 //BA.debugLineNum = 250;BA.debugLine="tempwew.Text = odczyt_BT.SubString2(0, i)  &";
mostCurrent._tempwew.setText((Object)(mostCurrent._odczyt_bt.substring((int) (0),(int) (_i))+"°C"));
 }else {
 //BA.debugLineNum = 252;BA.debugLine="tempwew.Text = \"Błąd\"";
mostCurrent._tempwew.setText((Object)("Błąd"));
 //BA.debugLineNum = 253;BA.debugLine="Log(\"test1\")";
anywheresoftware.b4a.keywords.Common.Log("test1");
 };
 //BA.debugLineNum = 255;BA.debugLine="Dim i As Long, liczba As Double";
_i = 0L;
_liczba = 0;
 //BA.debugLineNum = 256;BA.debugLine="Dim godz As String, minuta As String";
_godz = "";
_minuta = "";
 //BA.debugLineNum = 257;BA.debugLine="liczba =  odczyt";
_liczba = (double)(Double.parseDouble(mostCurrent._odczyt));
 //BA.debugLineNum = 258;BA.debugLine="godz = NumberFormat(DateTime.GetHour(DateTime";
_godz = anywheresoftware.b4a.keywords.Common.NumberFormat(anywheresoftware.b4a.keywords.Common.DateTime.GetHour(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),(int) (2),(int) (0));
 //BA.debugLineNum = 259;BA.debugLine="minuta = NumberFormat( DateTime.GetMinute(Dat";
_minuta = anywheresoftware.b4a.keywords.Common.NumberFormat(anywheresoftware.b4a.keywords.Common.DateTime.GetMinute(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),(int) (2),(int) (0));
 //BA.debugLineNum = 260;BA.debugLine="If godz <> \"00\" Then zmiana = True";
if ((_godz).equals("00") == false) { 
_zmiana = anywheresoftware.b4a.keywords.Common.True;};
 }else {
 //BA.debugLineNum = 263;BA.debugLine="tempzew.Text = \"Błąd\"";
mostCurrent._tempzew.setText((Object)("Błąd"));
 };
 };
 } 
       catch (Exception e37) {
			processBA.setLastException(e37); //BA.debugLineNum = 267;BA.debugLine="ToastMessageShow(LastException,True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 268;BA.debugLine="If connected Then Serial1.Disconnect";
if (_connected) { 
_serial1.Disconnect();};
 //BA.debugLineNum = 269;BA.debugLine="connected = False";
_connected = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 270;BA.debugLine="Timer2.Enabled = False";
mostCurrent._timer2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 271;BA.debugLine="urz= False";
_urz = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 272;BA.debugLine="StartService (POWROT)";
anywheresoftware.b4a.keywords.Common.StartService(mostCurrent.activityBA,(Object)(mostCurrent._powrot.getObject()));
 };
 };
 //BA.debugLineNum = 275;BA.debugLine="End Sub";
return "";
}
public static String  _wt_failed() throws Exception{
 //BA.debugLineNum = 128;BA.debugLine="Sub wt_Failed()";
 //BA.debugLineNum = 129;BA.debugLine="Log(\"wt_Failed\")";
anywheresoftware.b4a.keywords.Common.Log("wt_Failed");
 //BA.debugLineNum = 130;BA.debugLine="Label1.Text = \"brak info o pogodzie\"";
mostCurrent._label1.setText((Object)("brak info o pogodzie"));
 //BA.debugLineNum = 131;BA.debugLine="End Sub";
return "";
}
public static String  _wt_ready() throws Exception{
 //BA.debugLineNum = 123;BA.debugLine="Sub wt_Ready()";
 //BA.debugLineNum = 124;BA.debugLine="ShowWeather";
_showweather();
 //BA.debugLineNum = 125;BA.debugLine="Log(\"uruchomienie wt_Ready\")";
anywheresoftware.b4a.keywords.Common.Log("uruchomienie wt_Ready");
 //BA.debugLineNum = 126;BA.debugLine="End Sub";
return "";
}
}
