Android Screenshot Library
Native application project
**************************

This project is native part of Android Screenshot Library. It's a C code compiled into ARM executable using the variant of GCC compiler, available here:

	http://www.codesourcery.com/sgpp/lite/arm/portal/release1293

It shall be compiled with:

	{ARM-PATH}/bin/arm-none-linux-gnueabi-gcc -static main.c -o {output}

where {ARM-PATH} is the location of files from .tar.gz from the URL above.

The resulting executable is to be attached as an asset to 'native-launcher' .apk.