
import org.junit.Assert;
import org.junit.Test;
import spoon.Launcher;
import spoon.reflect.declaration.CtClass;

public class test02 {
    @Test
    public void testCodeEquality() {

        String originalCode = """
                public class LeapYearExample {   \s
                public static void main(String[] args) {   \s
                    int year=2020;   \s
                    if(((year % 4 ==0) && (year % 100 !=0)) || (year % 400==0)){ \s
                        System.out.println("LEAP YEAR"); \s
                    } \s
                    else{ \s
                        System.out.println("COMMON YEAR"); \s
                    } \s
                }   \s
                }   \s            
                """;

        String expectedCode = """
                public class LeapYearExample {   \s
                public static void main(String[] args) {   \s
                    int year=2020;   \s
                    if(((year % 4 !=0) || (year % 100 ==0)) && (year % 400!=0)){ \s
                        System.out.println("LEAP YEAR"); \s
                    } \s
                    else{ \s
                        System.out.println("COMMON YEAR"); \s
                    } \s
                }   \s
                }   \s
                """;
        CtClass<?> expected = Launcher .parseClass(expectedCode) ;
        CtClass transformed= ReverseCondition.reverseOperators(Launcher.parseClass(originalCode));
        Assert.assertEquals(transformed.toString(), expected.toString());
    }

}
