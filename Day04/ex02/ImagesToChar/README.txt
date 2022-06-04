### Launch:
# java -jar target/images-to-chars-printer.jar --white=[WHILE] --black=[BLACK]

rm -rf target
rm -rf lib
mkdir target
mkdir lib
curl -o lib/jcommander-1.82.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar
curl -o lib/JCDP-4.0.2.jar https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.2/JCDP-4.0.2.jar
javac -d target -cp lib/jcommander-1.82.jar:lib/JCDP-4.0.2.jar src/java/edu/school21/printer/*/*.java
cp -r src/resources target
jar -xf lib/jcommander-1.82.jar com
jar -xf lib/JCDP-4.0.2.jar com
mv com target
jar cfm target/images-to-chars-printer.jar src/manifest.txt -C target/ .
java -jar target/images-to-chars-printer.jar --white=RED --black=GREEN