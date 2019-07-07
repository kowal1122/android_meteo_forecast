Type=Service
Version=6.48
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: False
	
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Service_Create
	
End Sub

Sub Service_Start (StartingIntent As Intent)
	StartActivity(Main)
	Main.ekran.KeepAlive (True)
	StopService("POWROT")
End Sub

Sub Service_Destroy

End Sub
