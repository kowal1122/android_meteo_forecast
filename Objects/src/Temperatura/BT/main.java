package Temperatura.BT;


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
			processBA = new BA(this.getApplicationContext(), null, null, "Temperatura.BT", "Temperatura.BT.main");
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
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, true))
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
		activityBA = new BA(this, layout, processBA, "Temperatura.BT", "Temperatura.BT.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "Temperatura.BT.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
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
public static anywheresoftware.b4a.objects.Timer _timer1 = null;
public static anywheresoftware.b4a.objects.Timer _timer2 = null;
public static anywheresoftware.b4a.objects.Serial.BluetoothAdmin _admin = null;
public static anywheresoftware.b4a.phone.Phone.PhoneWakeState _ekran = null;
public static anywheresoftware.b4a.phone.Phone _telefon = null;
public static anywheresoftware.b4a.phone.Phone.PhoneSensors _psensor = null;
public static byte _kolor = (byte)0;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label7 = null;
public anywheresoftware.b4a.objects.PanelWrapper _p = null;
public static boolean _connected = false;
public static long _i = 0L;
public static double _jasnosc = 0;
public static double _poczatek = 0;
public static double _poczateky = 0;
public static boolean _cykl = false;
public anywheresoftware.b4a.objects.streams.File.TextReaderWrapper _textreader1 = null;
public anywheresoftware.b4a.objects.streams.File.TextWriterWrapper _textwriter1 = null;
public static String _odczyt_bt = "";
public static String _odczyt = "";
public static boolean _urz = false;
public anywheresoftware.b4a.objects.LabelWrapper _label3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label4 = null;
public static double _temp_max = 0;
public static double _temp_min = 0;
public static boolean _zmiana = false;
public static boolean _swiatlo = false;
public static double _wsp = 0;
public static double _podswietlenie = 0;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label8 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label9 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label10 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label11 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label12 = null;
public Temperatura.BT.powrot _powrot = null;

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
 //BA.debugLineNum = 61;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 62;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 63;BA.debugLine="admin.Initialize(\"admin\")";
_admin.Initialize(processBA,"admin");
 //BA.debugLineNum = 64;BA.debugLine="If admin.IsEnabled = False Then";
if (_admin.IsEnabled()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 65;BA.debugLine="admin.Enable";
_admin.Enable();
 //BA.debugLineNum = 66;BA.debugLine="Do Until admin.IsEnabled";
while (!(_admin.IsEnabled())) {
 //BA.debugLineNum = 67;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 }
;
 };
 //BA.debugLineNum = 70;BA.debugLine="Serial1.Initialize(\"Serial1\")";
_serial1.Initialize("Serial1");
 //BA.debugLineNum = 71;BA.debugLine="Timer1.Initialize(\"Timer1\", 500)";
_timer1.Initialize(processBA,"Timer1",(long) (500));
 //BA.debugLineNum = 72;BA.debugLine="Timer2.Initialize(\"Timer2\", 4000)";
_timer2.Initialize(processBA,"Timer2",(long) (4000));
 };
 //BA.debugLineNum = 74;BA.debugLine="Activity.LoadLayout(\"2\")";
mostCurrent._activity.LoadLayout("2",mostCurrent.activityBA);
 //BA.debugLineNum = 75;BA.debugLine="Timer2.Enabled = False";
_timer2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 76;BA.debugLine="Dim PhoneId As PhoneId";
_phoneid = new anywheresoftware.b4a.phone.Phone.PhoneId();
 //BA.debugLineNum = 77;BA.debugLine="Dim zasilanie As PhoneEvents";
_zasilanie = new anywheresoftware.b4a.phone.PhoneEvents();
 //BA.debugLineNum = 78;BA.debugLine="zasilanie.InitializeWithPhoneState(\"zasilanie\", P";
_zasilanie.InitializeWithPhoneState(processBA,"zasilanie",_phoneid);
 //BA.debugLineNum = 79;BA.debugLine="ekran.KeepAlive (True)";
_ekran.KeepAlive(processBA,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 80;BA.debugLine="Timer1.Enabled = True";
_timer1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 81;BA.debugLine="urz = False";
_urz = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 82;BA.debugLine="Serial1.Disconnect";
_serial1.Disconnect();
 //BA.debugLineNum = 83;BA.debugLine="p.Initialize(\"p\")";
mostCurrent._p.Initialize(mostCurrent.activityBA,"p");
 //BA.debugLineNum = 84;BA.debugLine="Activity.AddView(p, 0, 0, 100%x, 100%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._p.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 85;BA.debugLine="pSensor.Initialize(pSensor.TYPE_LIGHT)";
_psensor.Initialize(_psensor.TYPE_LIGHT);
 //BA.debugLineNum = 86;BA.debugLine="pSensor.StartListening(\"LightEvent\")";
_psensor.StartListening(processBA,"LightEvent");
 //BA.debugLineNum = 87;BA.debugLine="jasnosc = 0.65";
_jasnosc = 0.65;
 //BA.debugLineNum = 88;BA.debugLine="swiatlo = True";
_swiatlo = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 89;BA.debugLine="podswietlenie = 0.4";
_podswietlenie = 0.4;
 //BA.debugLineNum = 90;BA.debugLine="telefon.SetScreenBrightness (podswietlenie / wsp)";
_telefon.SetScreenBrightness(processBA,(float) (_podswietlenie/(double)_wsp));
 //BA.debugLineNum = 91;BA.debugLine="Label1.TextColor = Colors.RGB(0, jasnosc * 255, 0";
mostCurrent._label1.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 92;BA.debugLine="Label2.TextColor = Colors.RGB(0, jasnosc * 255, 0";
mostCurrent._label2.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 93;BA.debugLine="Label3.TextColor = Colors.RGB(0, jasnosc * 255, 0";
mostCurrent._label3.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 94;BA.debugLine="Label4.TextColor = Colors.RGB(0, jasnosc * 255, 0";
mostCurrent._label4.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 95;BA.debugLine="Label5.TextColor = Colors.RGB(0, jasnosc * 255, 0";
mostCurrent._label5.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 96;BA.debugLine="Label6.TextColor = Colors.RGB(0, jasnosc * 255, 0";
mostCurrent._label6.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 97;BA.debugLine="Label7.TextColor = Colors.RGB(0, jasnosc * 255, 0";
mostCurrent._label7.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 98;BA.debugLine="Label8.TextColor = Colors.RGB(0, jasnosc * 255, 0";
mostCurrent._label8.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 99;BA.debugLine="Label9.TextColor = Colors.RGB(0, jasnosc * 255, 0";
mostCurrent._label9.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 100;BA.debugLine="Label10.TextColor = Colors.RGB(0, jasnosc * 255,";
mostCurrent._label10.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 101;BA.debugLine="Label11.TextColor = Colors.RGB(0, jasnosc * 255,";
mostCurrent._label11.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 102;BA.debugLine="Label12.TextColor = Colors.RGB(0, jasnosc * 255,";
mostCurrent._label12.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 103;BA.debugLine="Panel1.Color = Colors.RGB(0, jasnosc * 255, 0)";
mostCurrent._panel1.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 104;BA.debugLine="Panel2.Color = Colors.RGB(0, jasnosc * 255, 0)";
mostCurrent._panel2.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 105;BA.debugLine="Dim maxmin As String, i As Long";
_maxmin = "";
_i = 0L;
 //BA.debugLineNum = 106;BA.debugLine="Try";
try { //BA.debugLineNum = 107;BA.debugLine="maxmin = File.ReadString(File.DirRootExternal, \"";
_maxmin = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"MaxMin.txt");
 //BA.debugLineNum = 108;BA.debugLine="Dim data As Long";
_data = 0L;
 //BA.debugLineNum = 109;BA.debugLine="data = File.LastModified(File.DirRootExternal, \"";
_data = anywheresoftware.b4a.keywords.Common.File.LastModified(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"MaxMin.txt");
 //BA.debugLineNum = 110;BA.debugLine="If DateTime.Date(data) = DateTime.Date(DateTime.";
if ((anywheresoftware.b4a.keywords.Common.DateTime.Date(_data)).equals(anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))) { 
 //BA.debugLineNum = 111;BA.debugLine="i = maxmin.IndexOf(\";\")";
_i = (long) (_maxmin.indexOf(";"));
 //BA.debugLineNum = 112;BA.debugLine="temp_min =  NumberFormat(maxmin.SubString(i+1),";
_temp_min = (double)(Double.parseDouble(anywheresoftware.b4a.keywords.Common.NumberFormat((double)(Double.parseDouble(_maxmin.substring((int) (_i+1)))),(int) (1),(int) (1))));
 //BA.debugLineNum = 113;BA.debugLine="temp_max =  NumberFormat(maxmin.SubString2(0, i";
_temp_max = (double)(Double.parseDouble(anywheresoftware.b4a.keywords.Common.NumberFormat((double)(Double.parseDouble(_maxmin.substring((int) (0),(int) (_i)))),(int) (1),(int) (1))));
 }else {
 //BA.debugLineNum = 115;BA.debugLine="temp_min =  100";
_temp_min = 100;
 //BA.debugLineNum = 116;BA.debugLine="temp_max = -100";
_temp_max = -100;
 };
 } 
       catch (Exception e58) {
			processBA.setLastException(e58); //BA.debugLineNum = 119;BA.debugLine="temp_min =  100";
_temp_min = 100;
 //BA.debugLineNum = 120;BA.debugLine="temp_max = -100";
_temp_max = -100;
 };
 //BA.debugLineNum = 122;BA.debugLine="admin.StartDiscovery";
_admin.StartDiscovery();
 //BA.debugLineNum = 123;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 145;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 147;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 125;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 127;BA.debugLine="End Sub";
return "";
}
public static String  _admin_devicefound(String _name,String _macaddress) throws Exception{
anywheresoftware.b4a.objects.collections.Map _paireddevices = null;
anywheresoftware.b4a.objects.collections.List _founddevices = null;
Temperatura.BT.main._nameandmac _nm = null;
 //BA.debugLineNum = 291;BA.debugLine="Sub admin_DeviceFound (Name As String, MacAddress";
 //BA.debugLineNum = 292;BA.debugLine="Dim PairedDevices As Map, foundDevices As List";
_paireddevices = new anywheresoftware.b4a.objects.collections.Map();
_founddevices = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 293;BA.debugLine="Dim nm As NameAndMac";
_nm = new Temperatura.BT.main._nameandmac();
 //BA.debugLineNum = 294;BA.debugLine="foundDevices.Initialize";
_founddevices.Initialize();
 //BA.debugLineNum = 295;BA.debugLine="nm.Name = Name";
_nm.Name = _name;
 //BA.debugLineNum = 296;BA.debugLine="nm.Mac = MacAddress";
_nm.Mac = _macaddress;
 //BA.debugLineNum = 297;BA.debugLine="foundDevices.Add( nm )";
_founddevices.Add((Object)(_nm));
 //BA.debugLineNum = 298;BA.debugLine="If foundDevices.Size > 0 Then";
if (_founddevices.getSize()>0) { 
 //BA.debugLineNum = 299;BA.debugLine="For i= 0 To foundDevices.Size-1";
{
final long step8 = 1;
final long limit8 = (long) (_founddevices.getSize()-1);
for (_i = (long) (0) ; (step8 > 0 && _i <= limit8) || (step8 < 0 && _i >= limit8); _i = ((long)(0 + _i + step8)) ) {
 //BA.debugLineNum = 300;BA.debugLine="nm = foundDevices.Get(i)";
_nm = (Temperatura.BT.main._nameandmac)(_founddevices.Get((int) (_i)));
 //BA.debugLineNum = 301;BA.debugLine="If nm.Name = \"HC-05\" Then";
if ((_nm.Name).equals("HC-05")) { 
 //BA.debugLineNum = 302;BA.debugLine="PairedDevices = Serial1.GetPairedDevices";
_paireddevices = _serial1.GetPairedDevices();
 //BA.debugLineNum = 303;BA.debugLine="Serial1.Connect(PairedDevices.Get(\"HC-05\"))";
_serial1.Connect(processBA,BA.ObjectToString(_paireddevices.Get((Object)("HC-05"))));
 //BA.debugLineNum = 304;BA.debugLine="urz = True";
_urz = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 305;BA.debugLine="connected = True";
_connected = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 306;BA.debugLine="Timer2.Enabled = True";
_timer2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 308;BA.debugLine="If urz = True Then Exit";
if (_urz==anywheresoftware.b4a.keywords.Common.True) { 
if (true) break;};
 //BA.debugLineNum = 309;BA.debugLine="urz = False";
_urz = anywheresoftware.b4a.keywords.Common.False;
 }
};
 };
 //BA.debugLineNum = 312;BA.debugLine="End Sub";
return "";
}
public static String  _admin_discoveryfinished() throws Exception{
 //BA.debugLineNum = 314;BA.debugLine="Sub admin_DiscoveryFinished";
 //BA.debugLineNum = 315;BA.debugLine="If urz = False Then admin.StartDiscovery";
if (_urz==anywheresoftware.b4a.keywords.Common.False) { 
_admin.StartDiscovery();};
 //BA.debugLineNum = 317;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 25;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 26;BA.debugLine="Private Label1 As Label";
mostCurrent._label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private Label2 As Label";
mostCurrent._label2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private Label5 As Label";
mostCurrent._label5 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private Label6 As Label";
mostCurrent._label6 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private Label7 As Label";
mostCurrent._label7 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Dim p As Panel";
mostCurrent._p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Dim connected As Boolean";
_connected = false;
 //BA.debugLineNum = 33;BA.debugLine="Dim i As Long";
_i = 0L;
 //BA.debugLineNum = 34;BA.debugLine="Dim jasnosc As Double";
_jasnosc = 0;
 //BA.debugLineNum = 35;BA.debugLine="Dim poczatek As Double";
_poczatek = 0;
 //BA.debugLineNum = 36;BA.debugLine="Dim poczatekY As Double";
_poczateky = 0;
 //BA.debugLineNum = 37;BA.debugLine="Dim cykl As Boolean";
_cykl = false;
 //BA.debugLineNum = 38;BA.debugLine="Dim TextReader1 As TextReader";
mostCurrent._textreader1 = new anywheresoftware.b4a.objects.streams.File.TextReaderWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Dim TextWriter1 As TextWriter";
mostCurrent._textwriter1 = new anywheresoftware.b4a.objects.streams.File.TextWriterWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Dim odczyt_BT As String";
mostCurrent._odczyt_bt = "";
 //BA.debugLineNum = 41;BA.debugLine="Dim i As Long";
_i = 0L;
 //BA.debugLineNum = 42;BA.debugLine="Dim odczyt As String";
mostCurrent._odczyt = "";
 //BA.debugLineNum = 43;BA.debugLine="Dim urz As Boolean";
_urz = false;
 //BA.debugLineNum = 44;BA.debugLine="Private Label3 As Label";
mostCurrent._label3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private Label4 As Label";
mostCurrent._label4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Dim temp_max As Double, temp_min As Double";
_temp_max = 0;
_temp_min = 0;
 //BA.debugLineNum = 47;BA.debugLine="Dim zmiana As Boolean";
_zmiana = false;
 //BA.debugLineNum = 48;BA.debugLine="Dim swiatlo As Boolean";
_swiatlo = false;
 //BA.debugLineNum = 49;BA.debugLine="Dim wsp As Double";
_wsp = 0;
 //BA.debugLineNum = 50;BA.debugLine="Dim podswietlenie As Double";
_podswietlenie = 0;
 //BA.debugLineNum = 51;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Private Panel2 As Panel";
mostCurrent._panel2 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Private Label8 As Label";
mostCurrent._label8 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 55;BA.debugLine="Private Label9 As Label";
mostCurrent._label9 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 56;BA.debugLine="Private Label10 As Label";
mostCurrent._label10 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 57;BA.debugLine="Private Label11 As Label";
mostCurrent._label11 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 58;BA.debugLine="Private Label12 As Label";
mostCurrent._label12 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 59;BA.debugLine="End Sub";
return "";
}
public static String  _lightevent_sensorchanged(float[] _values) throws Exception{
 //BA.debugLineNum = 319;BA.debugLine="Sub LightEvent_SensorChanged (Values() As Float)";
 //BA.debugLineNum = 320;BA.debugLine="If Values(0) = 0 Then";
if (_values[(int) (0)]==0) { 
 //BA.debugLineNum = 321;BA.debugLine="telefon.SetScreenBrightness (podswietlenie / (w";
_telefon.SetScreenBrightness(processBA,(float) (_podswietlenie/(double)(_wsp*5)));
 //BA.debugLineNum = 322;BA.debugLine="swiatlo = False";
_swiatlo = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 324;BA.debugLine="If Values(0) > 6 Then";
if (_values[(int) (0)]>6) { 
 //BA.debugLineNum = 325;BA.debugLine="telefon.SetScreenBrightness (podswietlenie / wsp";
_telefon.SetScreenBrightness(processBA,(float) (_podswietlenie/(double)_wsp));
 //BA.debugLineNum = 326;BA.debugLine="swiatlo = True";
_swiatlo = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 328;BA.debugLine="End Sub";
return "";
}
public static String  _p_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 252;BA.debugLine="Sub p_Touch (Action As Int, X As Float, Y As Float";
 //BA.debugLineNum = 253;BA.debugLine="Select Action";
switch (BA.switchObjectToInt(_action,mostCurrent._activity.ACTION_DOWN,mostCurrent._activity.ACTION_MOVE,mostCurrent._activity.ACTION_UP)) {
case 0: {
 //BA.debugLineNum = 255;BA.debugLine="poczatek = X";
_poczatek = _x;
 //BA.debugLineNum = 256;BA.debugLine="poczatekY = Y";
_poczateky = _y;
 //BA.debugLineNum = 257;BA.debugLine="Label1.Visible = False";
mostCurrent._label1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 258;BA.debugLine="Label9.Visible = False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 259;BA.debugLine="Label10.Visible = False";
mostCurrent._label10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 260;BA.debugLine="Label3.Visible = True";
mostCurrent._label3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 261;BA.debugLine="Label4.Visible = True";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.True);
 break; }
case 1: {
 //BA.debugLineNum = 263;BA.debugLine="If swiatlo Then";
if (_swiatlo) { 
 //BA.debugLineNum = 264;BA.debugLine="podswietlenie = podswietlenie + (poczatekY - Y";
_podswietlenie = _podswietlenie+(_poczateky-_y)/(double)(mostCurrent._p.getHeight()*10);
 //BA.debugLineNum = 265;BA.debugLine="If podswietlenie < 0.05 Then podswietlenie = 0";
if (_podswietlenie<0.05) { 
_podswietlenie = 0.05;};
 //BA.debugLineNum = 266;BA.debugLine="If podswietlenie > 1 Then podswietlenie = 1";
if (_podswietlenie>1) { 
_podswietlenie = 1;};
 //BA.debugLineNum = 267;BA.debugLine="telefon.SetScreenBrightness (podswietlenie / ws";
_telefon.SetScreenBrightness(processBA,(float) (_podswietlenie/(double)_wsp));
 };
 //BA.debugLineNum = 269;BA.debugLine="jasnosc = jasnosc + (X -poczatek) / (p.Width";
_jasnosc = _jasnosc+(_x-_poczatek)/(double)(mostCurrent._p.getWidth()*20);
 //BA.debugLineNum = 270;BA.debugLine="If jasnosc < 0.3 Then jasnosc = 0.3";
if (_jasnosc<0.3) { 
_jasnosc = 0.3;};
 //BA.debugLineNum = 271;BA.debugLine="If jasnosc > 1 Then jasnosc = 1";
if (_jasnosc>1) { 
_jasnosc = 1;};
 //BA.debugLineNum = 272;BA.debugLine="Label1.TextColor = Colors.RGB(0, jasnosc * 2";
mostCurrent._label1.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 273;BA.debugLine="Label2.TextColor = Colors.RGB(0, jasnosc * 255,";
mostCurrent._label2.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 274;BA.debugLine="Label3.TextColor = Colors.RGB(0, jasnosc * 255,";
mostCurrent._label3.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 275;BA.debugLine="Label4.TextColor = Colors.RGB(0, jasnosc * 255,";
mostCurrent._label4.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 276;BA.debugLine="Label5.TextColor = Colors.RGB(0, jasnosc * 255,";
mostCurrent._label5.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 277;BA.debugLine="Label6.TextColor = Colors.RGB(0, jasnosc * 255,";
mostCurrent._label6.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 278;BA.debugLine="Label7.TextColor = Colors.RGB(0, jasnosc * 255,";
mostCurrent._label7.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 279;BA.debugLine="Label8.TextColor = Colors.RGB(0, jasnosc * 255,";
mostCurrent._label8.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 280;BA.debugLine="Label9.TextColor = Colors.RGB(0, jasnosc * 255,";
mostCurrent._label9.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 281;BA.debugLine="Label10.TextColor = Colors.RGB(0, jasnosc * 255";
mostCurrent._label10.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 282;BA.debugLine="Label11.TextColor = Colors.RGB(0, jasnosc * 255";
mostCurrent._label11.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 283;BA.debugLine="Label12.TextColor = Colors.RGB(0, jasnosc * 255";
mostCurrent._label12.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 284;BA.debugLine="Panel1.Color = Colors.RGB(0, jasnosc * 255, 0)";
mostCurrent._panel1.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 //BA.debugLineNum = 285;BA.debugLine="Panel2.Color = Colors.RGB(0, jasnosc * 255, 0)";
mostCurrent._panel2.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (_jasnosc*255),(int) (0)));
 break; }
case 2: {
 //BA.debugLineNum = 287;BA.debugLine="poczatek = X";
_poczatek = _x;
 break; }
}
;
 //BA.debugLineNum = 289;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
powrot._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 13;BA.debugLine="Dim Serial1 As Serial";
_serial1 = new anywheresoftware.b4a.objects.Serial();
 //BA.debugLineNum = 14;BA.debugLine="Dim Timer1 As Timer";
_timer1 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 15;BA.debugLine="Dim Timer2 As Timer";
_timer2 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 16;BA.debugLine="Dim admin As BluetoothAdmin";
_admin = new anywheresoftware.b4a.objects.Serial.BluetoothAdmin();
 //BA.debugLineNum = 17;BA.debugLine="Dim ekran As PhoneWakeState";
_ekran = new anywheresoftware.b4a.phone.Phone.PhoneWakeState();
 //BA.debugLineNum = 18;BA.debugLine="Dim telefon As Phone";
_telefon = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 19;BA.debugLine="Type NameAndMac( Name As String, Mac As String )";
;
 //BA.debugLineNum = 20;BA.debugLine="Dim pSensor As PhoneSensors";
_psensor = new anywheresoftware.b4a.phone.Phone.PhoneSensors();
 //BA.debugLineNum = 21;BA.debugLine="Public kolor As Byte";
_kolor = (byte)0;
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public static String  _serial1_connected(boolean _success) throws Exception{
 //BA.debugLineNum = 129;BA.debugLine="Sub Serial1_Connected (Success As Boolean)";
 //BA.debugLineNum = 130;BA.debugLine="If Success Then";
if (_success) { 
 //BA.debugLineNum = 131;BA.debugLine="TextReader1.Initialize(Serial1.InputStream)";
mostCurrent._textreader1.Initialize(_serial1.getInputStream());
 //BA.debugLineNum = 132;BA.debugLine="TextWriter1.Initialize(Serial1.OutputStream)";
mostCurrent._textwriter1.Initialize(_serial1.getOutputStream());
 //BA.debugLineNum = 133;BA.debugLine="TextWriter1.Flush";
mostCurrent._textwriter1.Flush();
 //BA.debugLineNum = 134;BA.debugLine="connected = True";
_connected = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 135;BA.debugLine="Timer2.Enabled = True";
_timer2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 136;BA.debugLine="ToastMessageShow(\"Połączono!\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("Połączono!",anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 138;BA.debugLine="ToastMessageShow(LastException.Message, False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 140;BA.debugLine="urz = False";
_urz = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 141;BA.debugLine="admin.StartDiscovery";
_admin.StartDiscovery();
 };
 //BA.debugLineNum = 143;BA.debugLine="End Sub";
return "";
}
public static String  _timer1_tick() throws Exception{
 //BA.debugLineNum = 149;BA.debugLine="Sub timer1_Tick";
 //BA.debugLineNum = 150;BA.debugLine="cykl = Not (cykl)";
_cykl = anywheresoftware.b4a.keywords.Common.Not(_cykl);
 //BA.debugLineNum = 151;BA.debugLine="If DateTime.GetHour(DateTime.Now) > 9 Then";
if (anywheresoftware.b4a.keywords.Common.DateTime.GetHour(anywheresoftware.b4a.keywords.Common.DateTime.getNow())>9) { 
 //BA.debugLineNum = 152;BA.debugLine="Label5.Text = NumberFormat(DateTime.GetHour(Date";
mostCurrent._label5.setText((Object)(anywheresoftware.b4a.keywords.Common.NumberFormat(anywheresoftware.b4a.keywords.Common.DateTime.GetHour(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),(int) (2),(int) (0))));
 }else {
 //BA.debugLineNum = 154;BA.debugLine="Label5.Text = NumberFormat(DateTime.GetHour(Date";
mostCurrent._label5.setText((Object)(anywheresoftware.b4a.keywords.Common.NumberFormat(anywheresoftware.b4a.keywords.Common.DateTime.GetHour(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),(int) (1),(int) (0))));
 };
 //BA.debugLineNum = 156;BA.debugLine="Label7.Text = NumberFormat( DateTime.GetMinute(Da";
mostCurrent._label7.setText((Object)(anywheresoftware.b4a.keywords.Common.NumberFormat(anywheresoftware.b4a.keywords.Common.DateTime.GetMinute(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),(int) (2),(int) (0))));
 //BA.debugLineNum = 157;BA.debugLine="If cykl= True Then";
if (_cykl==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 158;BA.debugLine="Label6.Text = \":\"";
mostCurrent._label6.setText((Object)(":"));
 }else {
 //BA.debugLineNum = 160;BA.debugLine="Label6.Text = \"\"";
mostCurrent._label6.setText((Object)(""));
 };
 //BA.debugLineNum = 163;BA.debugLine="End Sub";
return "";
}
public static String  _timer2_tick() throws Exception{
double _war = 0;
double _liczba = 0;
String _godz = "";
String _minuta = "";
 //BA.debugLineNum = 165;BA.debugLine="Sub timer2_Tick";
 //BA.debugLineNum = 166;BA.debugLine="If connected Then";
if (_connected) { 
 //BA.debugLineNum = 167;BA.debugLine="Try";
try { //BA.debugLineNum = 168;BA.debugLine="TextWriter1.WriteLine(\"O\")";
mostCurrent._textwriter1.WriteLine("O");
 //BA.debugLineNum = 169;BA.debugLine="TextWriter1.Flush";
mostCurrent._textwriter1.Flush();
 //BA.debugLineNum = 170;BA.debugLine="Do Until TextReader1.Ready 'check if there is";
while (!(mostCurrent._textreader1.Ready())) {
 //BA.debugLineNum = 171;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 }
;
 //BA.debugLineNum = 173;BA.debugLine="odczyt_BT = TextReader1.ReadLine";
mostCurrent._odczyt_bt = mostCurrent._textreader1.ReadLine();
 //BA.debugLineNum = 174;BA.debugLine="If connected  Then";
if (_connected) { 
 //BA.debugLineNum = 175;BA.debugLine="i = odczyt_BT.IndexOf(\";\")";
_i = (long) (mostCurrent._odczyt_bt.indexOf(";"));
 //BA.debugLineNum = 176;BA.debugLine="odczyt = odczyt_BT.SubString(i+1)";
mostCurrent._odczyt = mostCurrent._odczyt_bt.substring((int) (_i+1));
 //BA.debugLineNum = 177;BA.debugLine="If odczyt <> \"Blad\" Then";
if ((mostCurrent._odczyt).equals("Blad") == false) { 
 //BA.debugLineNum = 178;BA.debugLine="Dim war As Double";
_war = 0;
 //BA.debugLineNum = 179;BA.debugLine="war = odczyt";
_war = (double)(Double.parseDouble(mostCurrent._odczyt));
 //BA.debugLineNum = 180;BA.debugLine="Label3.Text = \"\"";
mostCurrent._label3.setText((Object)(""));
 //BA.debugLineNum = 181;BA.debugLine="Label4.Text = \"\"";
mostCurrent._label4.setText((Object)(""));
 //BA.debugLineNum = 182;BA.debugLine="Label9.Visible = True";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 183;BA.debugLine="Label10.Visible = True";
mostCurrent._label10.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 184;BA.debugLine="Label11.Visible = True";
mostCurrent._label11.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 185;BA.debugLine="Label12.Visible = True";
mostCurrent._label12.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 186;BA.debugLine="If war > 0 Then";
if (_war>0) { 
 //BA.debugLineNum = 187;BA.debugLine="Label1.Text = \"+\" & odczyt_BT.SubString(i+";
mostCurrent._label1.setText((Object)("+"+mostCurrent._odczyt_bt.substring((int) (_i+1))));
 }else {
 //BA.debugLineNum = 189;BA.debugLine="Label1.Text = odczyt_BT.SubString(i+1)' &";
mostCurrent._label1.setText((Object)(mostCurrent._odczyt_bt.substring((int) (_i+1))));
 };
 //BA.debugLineNum = 191;BA.debugLine="If odczyt_BT.SubString2(0, i) <> \"Blad\" The";
if ((mostCurrent._odczyt_bt.substring((int) (0),(int) (_i))).equals("Blad") == false) { 
 //BA.debugLineNum = 192;BA.debugLine="Label2.text = odczyt_BT.SubString2(0, i) '";
mostCurrent._label2.setText((Object)(mostCurrent._odczyt_bt.substring((int) (0),(int) (_i))));
 }else {
 //BA.debugLineNum = 194;BA.debugLine="Label2.Text = \"Błąd\"";
mostCurrent._label2.setText((Object)("Błąd"));
 };
 //BA.debugLineNum = 196;BA.debugLine="Dim i As Long, liczba As Double";
_i = 0L;
_liczba = 0;
 //BA.debugLineNum = 197;BA.debugLine="Dim godz As String, minuta As String";
_godz = "";
_minuta = "";
 //BA.debugLineNum = 198;BA.debugLine="liczba =  odczyt";
_liczba = (double)(Double.parseDouble(mostCurrent._odczyt));
 //BA.debugLineNum = 199;BA.debugLine="godz = NumberFormat(DateTime.GetHour(DateTi";
_godz = anywheresoftware.b4a.keywords.Common.NumberFormat(anywheresoftware.b4a.keywords.Common.DateTime.GetHour(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),(int) (2),(int) (0));
 //BA.debugLineNum = 200;BA.debugLine="minuta = NumberFormat( DateTime.GetMinute(D";
_minuta = anywheresoftware.b4a.keywords.Common.NumberFormat(anywheresoftware.b4a.keywords.Common.DateTime.GetMinute(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),(int) (2),(int) (0));
 //BA.debugLineNum = 201;BA.debugLine="If godz <> \"00\" Then zmiana = True";
if ((_godz).equals("00") == false) { 
_zmiana = anywheresoftware.b4a.keywords.Common.True;};
 //BA.debugLineNum = 202;BA.debugLine="If godz = \"00\" And minuta = \"00\" And zmiana";
if ((_godz).equals("00") && (_minuta).equals("00") && _zmiana==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 203;BA.debugLine="temp_max = liczba";
_temp_max = _liczba;
 //BA.debugLineNum = 204;BA.debugLine="temp_min = liczba";
_temp_min = _liczba;
 //BA.debugLineNum = 205;BA.debugLine="File.WriteString(File.DirRootExternal, \"Ma";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"MaxMin.txt",anywheresoftware.b4a.keywords.Common.NumberFormat(_temp_max,(int) (1),(int) (1))+";"+anywheresoftware.b4a.keywords.Common.NumberFormat(_temp_min,(int) (1),(int) (1)));
 //BA.debugLineNum = 206;BA.debugLine="zmiana = False";
_zmiana = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 208;BA.debugLine="If liczba > temp_max Then";
if (_liczba>_temp_max) { 
 //BA.debugLineNum = 209;BA.debugLine="temp_max = liczba";
_temp_max = _liczba;
 //BA.debugLineNum = 210;BA.debugLine="File.WriteString(File.DirRootExternal, \"Ma";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"MaxMin.txt",anywheresoftware.b4a.keywords.Common.NumberFormat(_temp_max,(int) (1),(int) (1))+";"+anywheresoftware.b4a.keywords.Common.NumberFormat(_temp_min,(int) (1),(int) (1)));
 };
 //BA.debugLineNum = 212;BA.debugLine="If liczba < temp_min Then";
if (_liczba<_temp_min) { 
 //BA.debugLineNum = 213;BA.debugLine="temp_min = liczba";
_temp_min = _liczba;
 //BA.debugLineNum = 214;BA.debugLine="File.WriteString(File.DirRootExternal, \"Ma";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"MaxMin.txt",anywheresoftware.b4a.keywords.Common.NumberFormat(_temp_max,(int) (1),(int) (1))+";"+anywheresoftware.b4a.keywords.Common.NumberFormat(_temp_min,(int) (1),(int) (1)));
 };
 //BA.debugLineNum = 216;BA.debugLine="If temp_max > 0 Then";
if (_temp_max>0) { 
 //BA.debugLineNum = 217;BA.debugLine="Label3.Text = \"MAX:\" & \"+\" & NumberFormat";
mostCurrent._label3.setText((Object)("MAX:"+"+"+anywheresoftware.b4a.keywords.Common.NumberFormat(_temp_max,(int) (1),(int) (1))+"°C"));
 }else {
 //BA.debugLineNum = 219;BA.debugLine="Label3.Text = \"MAX:\" &  NumberFormat(temp_";
mostCurrent._label3.setText((Object)("MAX:"+anywheresoftware.b4a.keywords.Common.NumberFormat(_temp_max,(int) (1),(int) (1))+"°C"));
 };
 //BA.debugLineNum = 221;BA.debugLine="If temp_min > 0 Then";
if (_temp_min>0) { 
 //BA.debugLineNum = 222;BA.debugLine="Label4.Text = \"MIN:\" &  \"+\" & NumberFormat";
mostCurrent._label4.setText((Object)("MIN:"+"+"+anywheresoftware.b4a.keywords.Common.NumberFormat(_temp_min,(int) (1),(int) (1))+"°C"));
 }else {
 //BA.debugLineNum = 224;BA.debugLine="Label4.Text = \"MIN:\" &  NumberFormat(temp_";
mostCurrent._label4.setText((Object)("MIN:"+anywheresoftware.b4a.keywords.Common.NumberFormat(_temp_min,(int) (1),(int) (1))+"°C"));
 };
 //BA.debugLineNum = 226;BA.debugLine="Label3.Visible = False";
mostCurrent._label3.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 227;BA.debugLine="Label4.Visible = False";
mostCurrent._label4.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 228;BA.debugLine="Label1.Visible = True";
mostCurrent._label1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 230;BA.debugLine="Label1.Text = \"Błąd\"";
mostCurrent._label1.setText((Object)("Błąd"));
 };
 };
 } 
       catch (Exception e69) {
			processBA.setLastException(e69); //BA.debugLineNum = 234;BA.debugLine="ToastMessageShow(LastException,True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 235;BA.debugLine="If connected Then Serial1.Disconnect";
if (_connected) { 
_serial1.Disconnect();};
 //BA.debugLineNum = 236;BA.debugLine="connected = False";
_connected = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 237;BA.debugLine="Timer2.Enabled = False";
_timer2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 238;BA.debugLine="urz= False";
_urz = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 239;BA.debugLine="Label1.Text = \"\"";
mostCurrent._label1.setText((Object)(""));
 //BA.debugLineNum = 240;BA.debugLine="Label2.Text = \"\"";
mostCurrent._label2.setText((Object)(""));
 //BA.debugLineNum = 241;BA.debugLine="Label3.Text = \"\"";
mostCurrent._label3.setText((Object)(""));
 //BA.debugLineNum = 242;BA.debugLine="Label4.Text =\"\"";
mostCurrent._label4.setText((Object)(""));
 //BA.debugLineNum = 243;BA.debugLine="Label9.Visible = False";
mostCurrent._label9.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 244;BA.debugLine="Label10.Visible = False";
mostCurrent._label10.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 245;BA.debugLine="Label11.Visible = False";
mostCurrent._label11.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 246;BA.debugLine="Label12.Visible = False";
mostCurrent._label12.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 247;BA.debugLine="StartService (POWROT)";
anywheresoftware.b4a.keywords.Common.StartService(mostCurrent.activityBA,(Object)(mostCurrent._powrot.getObject()));
 };
 };
 //BA.debugLineNum = 250;BA.debugLine="End Sub";
return "";
}
public static String  _zasilanie_batterychanged(int _level,int _scale,boolean _plugged,anywheresoftware.b4a.objects.IntentWrapper _intent) throws Exception{
 //BA.debugLineNum = 330;BA.debugLine="Sub zasilanie_BatteryChanged (Level As Int, Scale";
 //BA.debugLineNum = 331;BA.debugLine="If Plugged Then";
if (_plugged) { 
 //BA.debugLineNum = 332;BA.debugLine="wsp = 1";
_wsp = 1;
 }else {
 //BA.debugLineNum = 334;BA.debugLine="wsp = 5";
_wsp = 5;
 };
 //BA.debugLineNum = 336;BA.debugLine="End Sub";
return "";
}
}
