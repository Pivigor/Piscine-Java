### Launch:
# java -jar target/images-to-chars-printer.jar [WHILE] [BLACK]

rm -rf target
mkdir target
javac -d target src/java/edu/school21/printer/*/*.java
cp -r src/resources target
jar cfm target/images-to-chars-printer.jar src/manifest.txt -C target/ .
java -jar target/images-to-chars-printer.jar "." "0"