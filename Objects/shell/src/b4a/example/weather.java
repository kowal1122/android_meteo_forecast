
package b4a.example;

import anywheresoftware.b4a.pc.PCBA;
import anywheresoftware.b4a.pc.RemoteObject;

public class weather {
    public static RemoteObject myClass;
	public weather() {
	}
    public static PCBA staticBA = new PCBA(null, weather.class);

public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _weatherapikey = RemoteObject.createImmutable("");
public static RemoteObject _weathercityid = RemoteObject.createImmutable("");
public static RemoteObject _m_refreshinterval = RemoteObject.createImmutable(0);
public static RemoteObject _tmrinterval = RemoteObject.declareNull("anywheresoftware.b4a.objects.Timer");
public static RemoteObject _callbackactivity = RemoteObject.declareNull("Object");
public static RemoteObject _eventprefix = RemoteObject.createImmutable("");
public static RemoteObject _iconlist = null;
public static RemoteObject _descriptionlist = null;
public static RemoteObject _dtlist = null;
public static RemoteObject _speedlist = null;
public static RemoteObject _templist = null;
public static RemoteObject _i = RemoteObject.createImmutable(0);
public static RemoteObject _httputils2service = RemoteObject.declareNull("anywheresoftware.b4a.samples.httputils2.httputils2service");
public static RemoteObject _dateutils = RemoteObject.declareNull("b4a.example.dateutils");
public static b4a.example.main _main = null;
public static b4a.example.starter _starter = null;
public static Object[] GetGlobals(RemoteObject _ref) throws Exception {
		return new Object[] {"callBackActivity",_ref.getField(false, "_callbackactivity"),"DateUtils",_ref.getField(false, "_dateutils"),"descriptionlist",_ref.getField(false, "_descriptionlist"),"dtlist",_ref.getField(false, "_dtlist"),"eventPrefix",_ref.getField(false, "_eventprefix"),"HttpUtils2Service",_ref.getField(false, "_httputils2service"),"i",_ref.getField(false, "_i"),"iconlist",_ref.getField(false, "_iconlist"),"m_RefreshInterval",_ref.getField(false, "_m_refreshinterval"),"speedlist",_ref.getField(false, "_speedlist"),"templist",_ref.getField(false, "_templist"),"tmrInterval",_ref.getField(false, "_tmrinterval"),"weatherAPIKey",_ref.getField(false, "_weatherapikey"),"weatherCityID",_ref.getField(false, "_weathercityid")};
}
}