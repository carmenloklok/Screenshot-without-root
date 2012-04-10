# Android Screenshot Library
# -- Native Android application --
# Makefile
NDK=/Users/carmenloklok/work/android-ndk-r7b
x86_GCC=gcc
ARM_GCC=${NDK}/toolchains/arm-linux-androideabi-4.4.3/prebuilt/darwin-x86/bin/arm-linux-androideabi-gcc

C_FLAGS=-DDEBUG -I${NDK}/platforms/android-9/arch-arm/usr/include
L_FLAGS=-L${NDK}/platforms/android-9/arch-arm/usr/lib -nostdlib ${NDK}/platforms/android-9/arch-arm/usr/lib/crtbegin_static.o ${NDK}/platforms/android-9/arch-arm/usr/lib/crtend_android.o -lc -lm -ldl

ADB=/var/lib/google/android-sdk-linux_86/tools/adb
DEVICE_FILE=/data/local/asl-native


native:	fbshot main
	${ARM_GCC} ${L_FLAGS} fbshot.o main.o -o native -lgcc

fbshot:	fbshot.c common.h
	${ARM_GCC} -c ${C_FLAGS} fbshot.c -o fbshot.o

main:	main.c common.h
	${ARM_GCC} -c ${C_FLAGS} main.c -o main.o


clean:
	rm native*
	rm *.o


upload: native
	${ADB} push ./native ${DEVICE_FILE}


x86:	main.c fbshot.c common.h
	${x86_GCC} -c ${C_FLAGS} main.c -o main-x86.o
	${x86_GCC} -c ${C_FLAGS} fbshot.c -o fbshot-x86.o
	${x86_GCC} ${L_FLAGS} fbshot-x86.o main-x86.o -o native-x86
