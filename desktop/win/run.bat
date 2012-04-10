@echo off

echo Initializing...

echo Waiting for device to be connected...
%~dp0adb wait-for-device

echo - Installing native service...
%~dp0adb push %~dp0native /data/local/native
%~dp0adb shell chmod 777 /data/local/native

echo Starting...

%~dp0adb shell kill -9 "/data/local/native"
start /B %~dp0adb shell "/data/local/native /data/local/native.log"

echo Service started successfully.

exit