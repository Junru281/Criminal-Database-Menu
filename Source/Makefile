run: PersonDB.class Menu.class FileUtils.class ImagePanel.class Person.class
	java PersonDB

Person.class: Person.java
	javac Person.java


ImagePanel.class: ImagePanel.java Person.class
	javac ImagePanel.java

FileUtils.class: FileUtils.java ImagePanel.class Person.class
	javac FileUtils.java

Menu.class: Menu.java FileUtils.class ImagePanel.class Person.class
	javac Menu.java

PersonDB.class: PersonDB.java Menu.class FileUtils.class ImagePanel.class Person.class
	javac PersonDB.java

clean:
	rm *.class