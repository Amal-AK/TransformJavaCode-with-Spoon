# TransformJavaCode-with-Spoon

This is a java program that uses the Spoon static analysis framework to transform an arbitrary piece of Java code to a new one with the relational
and logical operators at IF statements reversed.


### Spoon and other libraries are added with maven

     <dependency>
            <groupId>fr.inria.gforge.spoon</groupId>
            <artifactId>spoon-core</artifactId>
            <version>9.1.0-beta-20</version>
     </dependency>
     
     
### Main class

The input code used for the test in Main class is in "src/main/java/inputCode.java".

When we execute Main class , we'll have this result :

-> Original code :


    public class inputCode {
        public void code() {
            int x = 2;
            int y = 5;
            if ((x > 0) && ((y / x) == 3)) {
                java.lang.System.out.println("first case");
            } else if ((x == 0) || ((y / x) <= 3)) {
                java.lang.System.out.println("first case");
            } else {
                java.lang.System.out.println("second case");
            }
        }
    }
    
    
-> transformed code :
 
    
    public class inputCode {
        public void code() {
            int x = 2;
            int y = 5;
            if ((x < 0) || ((y / x) != 3)) {
                java.lang.System.out.println("first case");
            } else if ((x != 0) && ((y / x) >= 3)) {
                java.lang.System.out.println("first case");
            } else {
                java.lang.System.out.println("second case");
            }
        }
    }


### Test
The test files are in the folder "src/test/java"
To test the code , we compared the output code with the expected code of the instance given in the task description
