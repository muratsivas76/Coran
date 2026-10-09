./clean.sh

#/usr/java/android-sdk/tools/android list target
#/usr/java/android-sdk/tools/android --verbose create avd --name MuratAVD --target 3--sdcard 1024M

/usr/java/jdk1.6.0/bin/keytool -genkey -alias Coran.keystore -keyalg RSA -validity 1000000 -dname "CN=Murat inan, OU=Freelance, O=Traductor, S=Merkez, C=TR" -keystore Coran.keystore -storepass 5EmrE432 -keypass 5EmrE432 

/usr/java/android-sdk/platforms/android-1.5/tools/aapt package -v -f -m -S res -J src -M AndroidManifest.xml -I /usr/java/android-sdk/platforms/android-1.5/android.jar

/usr/java/jdk1.6.0/bin/javac -bootclasspath /usr/java/android-sdk/platforms/android-1.5/android.jar: -sourcepath src/: -cp /usr/java/android-sdk/platforms/android-1.5/android.jar:build/classes: -g:none -proc:none -nowarn -O src/net/murat/coran/Coran.java -d build/classes

/usr/java/android-sdk/platforms/android-1.5/tools/dx --dex --verbose --output=dist/classes.dex build/classes dist/lib

/usr/java/android-sdk/platforms/android-1.5/tools/aapt package -v -f -M AndroidManifest.xml -A assets -S res -I /usr/java/android-sdk/platforms/android-1.5/android.jar -F dist/Coran.unsigned.apk dist

/usr/java/jdk1.6.0/bin/jarsigner -verbose -keystore Coran.keystore -storepass 5EmrE432 -keypass 5EmrE432 -signedjar dist/Coran.signed.apk dist/Coran.unsigned.apk Coran.keystore

/usr/java/android-sdk/tools/zipalign -v -f 4 dist/Coran.signed.apk dist/Coran.apk

rm -fv dist/*.signed.* dist/*.unsigned.* dist/classes.dex

#/usr/java/jdk1.6.0/bin/javadoc -verbose -d docs -sourcepath src -classpath /usr/java/android-sdk/platforms/android-1.5/android.jar:build/classes -author -package -use -splitindex -version -windowtitle 'Coran' -doctitle 'Coran' src/net/murat/vgml/*.java

echo " "
echo "Run this firstly: "
echo "/usr/java/android-sdk/tools/emulator -wipe-data -avd MuratAVD &"

echo " "
echo "Run this when the emulator is ready:"
echo "/usr/java/android-sdk/tools/adb -e install dist/Coran.apk"

echo " "
echo "You can remove it from emulator with this command:"
echo "/usr/java/android-sdk/tools/adb shell rm /data/app/net.murat.coran.apk"

echo " "
echo "You can uninstall it from emulator with this command:"
echo "/usr/java/android-sdk/tools/adb -e uninstall net.murat.coran"
echo " "
