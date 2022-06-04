### Launch:
# java -cp target edu.school21.printer.app.Program [WHILE] [BLACK] [PATH]

rm -rf target
mkdir target
javac -d target src/java/edu/school21/printer/*/*.java
java -cp target edu.school21.printer.app.Program "." "0" /home/pivub/School42/Piscine_Java/Day04/ex00/it.bmp