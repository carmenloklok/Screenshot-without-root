@ set GCC=arm-none-linux-gnueabi-gcc.exe
@ set C_FLAGS=-DDEBUG
@ set ADB=C:\Android\tools\adb.exe

@echo Compiling...
@ %GCC% %C_FLAGS% -c main.c -o main.o
	@if not ERRORLEVEL 0 goto Error
@ %GCC% %C_FLAGS% -c fbshot.c -o fbshot.o
	@if not ERRORLEVEL 0 goto Error

@echo Linking...
@ %GCC% --static fbshot.o main.o -o asl-native
@if not ERRORLEVEL 0 goto Error

@echo Pushing to device...
@ %ADB% push ./asl-native /data/local/asl-native

@echo Running...
@ %ADB% shell /system/bin/chmod 0777 /data/local/asl-native
@ start /B %ADB% shell "/data/local/asl-native /data/local/asl-native.log"

@goto Exit

:Error
@echo Error while compiling/linking.

:Exit