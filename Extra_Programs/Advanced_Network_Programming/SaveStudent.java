{\rtf1\ansi\ansicpg1252\deff0\deflang1033{\fonttbl{\f0\fswiss\fcharset0 Arial;}}
\viewkind4\uc1\pard\f0\fs20 import java.io.*;\par
/**\par
* @(#)SaveStudent.java\par
* This class creates some instances of the Student\par
* class and serializes them by saving their states to\par
* a file.\par
*/\par
public class SaveStudent \{\par
public static void main(String argv[]) throws Exception \{\par
// Create some objects.\par
Student manoj = new Student(\ldblquote Manoj Kumar\rdblquote , 200101001, 15000);\par
Employee john = new Employee(\ldblquote J. McDonald\rdblquote ,290, 39000);\par
// Serialize the objects emily and john.\par
FileOutputStream fos = new FileOutputStream(\ldblquote db\rdblquote );\par
ObjectOutputStream oos = new ObjectOutputStream(fos);\par
oos.writeObject(emily);\par
oos.writeObject(john);\par
oos.flush();\par
\}\par
\}\par
}
 