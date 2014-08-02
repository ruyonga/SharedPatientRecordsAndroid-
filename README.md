SharedPatientRecordsAndroid-
============================

Android application to use in collaboration with Patients information System.
The application helps patients to access their medical records from where they be.

The application connects to a DJANGO system and returns JSON data that is later decoded and 
displayed in a listview.

****Tech used****
- android 2.2 and later
- Json
- DJango
- listviews
- 
***How to****
Download the Zip and extract
Import the folder to your eclipse
Change the two urls 

private static String url = "http://192.168.1.108:8000/api/patient/detail/";

and

String otherUrl = "http://192.168.1.108:8000/api/patient/conditions/" + p_id;


For the web system check out @roldys https://github.com/roldy/SharedRecords
For the apk check out 1mobile.com/ruyonga

developed 
ruyonga Daniel
