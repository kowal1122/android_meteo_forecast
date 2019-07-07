Type=Service
Version=6.5
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: False
	
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim sms_in As SmsInterceptor
	Public kolor As Byte
	
End Sub

Sub Service_Create
	 sms_in.Initialize2("sms_in",999)     
End Sub

Sub sms_in_MessageReceived (From As String, Body As String) As Boolean
	If From = "+48668149087" Then 
		If Body = "B" Then 
			kolor = 0
		End If
		If Body = "Z" Then
			kolor = 255
		End If
		StartActivity(Main)
	End If

End Sub

Sub Service_Start (StartingIntent As Intent)

End Sub

Sub Service_Destroy

End Sub
