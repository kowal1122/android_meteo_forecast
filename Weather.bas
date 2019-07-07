Type=Class
Version=6.48
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
Sub Class_Globals
	Public weatherAPIKey As String = "############"
	Public weatherCityID As String
	Private m_RefreshInterval As Int
	Private tmrInterval As Timer
	
	Private callBackActivity As Object
	Private eventPrefix As String
	Private iconlist(40) As String
	Private descriptionlist(40) As String
	Private dtlist(40) As Int
	Private speedlist(40) As Int
	Private templist(40) As Double
	
	Dim i As Int = 0
End Sub

'TargetActivity is the activity that will receive the ready and failed events
'CityId is the ID of the city as set in http://bulk.openweathermap.org/sample/city.list.json.gz
'RefreshInterval is the number of minutes after which a new call to the server will be made. Keep it not too short
'If GetLatest is true, a call for the latest information will immediately be made, if false, you'll have to call DownloadWeather() to get it
Public Sub Initialize(TargetActivity As Object, EventName As String, CityID As String, GetLatest As Boolean, RefreshInterval As Int)
	
	weatherCityID = CityID
	callBackActivity = TargetActivity
	eventPrefix = EventName
	m_RefreshInterval = RefreshInterval
	tmrInterval.Initialize("tmrInterval", 3600)  'aktualizuj co godzinę
	tmrInterval.Enabled = True

	If GetLatest Then
		DownloadWeather
	End If
	
End Sub

'------------------------'
'koniec głównego programu
'------------------------'
'pobierz pogodę info i przyjmij _Ready lub _Failed events

Public Sub DownloadWeather()
	Dim j As HttpJob
	Dim url As String = "https://api.openweathermap.org/data/2.5/forecast"
	'https://api.openweathermap.org/data/2.5/forecast?id=7530858&units=metric&appid=d6870f22a6a2ce786ae2e31bb33a1a2f&lang=pl
	
	j.Initialize("main", Me)
	j.Download2(url, Array As String ("id", weatherCityID, "units", "metric", "appid", weatherAPIKey,"lang","pl"))
	Log("download: " & j)
	
End Sub

'pobieranie daynch json narazie nie ruszac
Private Sub JobDone(Job As HttpJob)
	
	If Job.Success = False Then
		Select Job.JobName
			Case "main"
				If SubExists(callBackActivity, eventPrefix & "_Failed") Then
					CallSubDelayed(callBackActivity, eventPrefix & "_Failed")
				End If
		End Select
		Job.Release
		Return
	End If
	
	Select Job.JobName
		Case "main"
			ParseWeatherData(Job.GetString)
			If m_RefreshInterval > 0 Then
				tmrInterval.Interval = m_RefreshInterval * 60000
				tmrInterval.Enabled = True
			End If
	End Select
	
	Job.Release
	
End Sub

private Sub tmrInterval_Tick()
	tmrInterval.Enabled = False
	DownloadWeather
End Sub

Public Sub givetemp(number As Int) As Double
	Return(templist(number))
End Sub

Public Sub givedescription(number As Int) As String
	Return(descriptionlist(number))
End Sub

'pobieranie ikony pogody
Public Sub giveicon(number As Int) As String
	Select iconlist(number)
		Case "01d", "01n"
			Return("01.png")
		Case "02d", "02n"
			Return("02.png")
		Case "03d" , "03n", "04d" , "04n"
			Return("03.png")
		Case "09d", "09n", "10d", "10n"
			Return("09.png")
		Case "11d", "11n"
			Return("11.png")
		Case "13d", "13n"
			Return("13.png")
		Case Else
			Return("loading.png")
	End Select	
End Sub

Public Sub givedtlist(number As Int) As String		
	Dim ExpDate As Long = DateUtils.UnixTimeToTicks(dtlist(number))
	Return(DateTime.Date(ExpDate)  & " " & DateTime.time(ExpDate))
End Sub



Public Sub ParseWeatherData(jsonData As String)
	Dim js As JSONParser
	Dim root As Map
	Dim map As Map
	
	map.Initialize
	
	Log("jsondata:" & jsonData)
	js.Initialize(jsonData)
	root.Initialize
	root = js.NextObject
	Log("root:" & root)
	
	Dim parser As JSONParser
	
	'------------------------------------------------ forecast
	parser.Initialize(jsonData)
	Dim root As Map = parser.NextObject
	Dim list As List = root.Get("list")
	For Each collist As Map In list
		Dim dt As Int = collist.Get("dt")
		dtlist(i) = dt
		Dim weather As List = collist.Get("weather")
		
		For Each colweather As Map In weather
			Dim icon As String  = colweather.Get("icon") 				'nazwa ikonki
			Dim description As String  = colweather.Get("description")   'opis słowny
			iconlist(i) = icon
			descriptionlist(i) = description
			Log(i & "-" & "dane: " & iconlist(i) & " " & dtlist(i) & " " & descriptionlist(i))
		Next
		
	'	Log("IndexOf;" & description.ToLowerCase())	
		
		Dim Main2 As Map = collist.Get("main")
		Dim temp As Double = Main2.Get("temp")      'temperatura
		templist(i) = temp
		Dim wind As Map = collist.Get("wind")
		Dim speed As Double = wind.Get("speed")     'prędkośc wiatru
		speedlist(i) = speed
		
		i = i +1
	Next
	
		'bez tego nie pojawiaja sie dane, rozkimnic po co to
	If SubExists(callBackActivity, eventPrefix & "_Ready") Then
		CallSubDelayed(callBackActivity, eventPrefix & "_Ready")
	End If
	
End Sub
