javac src/main/java/ru/nsu/berezin/* -d build/run_script_classes
javadoc -d build/run_script_docs -sourcepath src/main/java -subpackages ru.nsu.berezin
java -cp ./build/run_script_classes ru.nsu.berezin.Heapsort  