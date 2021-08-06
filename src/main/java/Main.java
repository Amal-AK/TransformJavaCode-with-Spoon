import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.NamedElementFilter;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // create the spoon launcher and build the AST model
        Launcher launcher = new Launcher ();
        launcher.addInputResource("./src/main/java");
        launcher.buildModel ();
        CtModel model = launcher.getModel ();

        // search for the inputCode class
        List<CtClass> Classes = (model.
                filterChildren(new NamedElementFilter<>(CtClass.class,"inputCode")).list());
        CtClass original = Classes.get(0) ;
        System.out.println("    ==> Original Code : \n\n" + original);

        // reverse operators
        CtClass transformed = ReverseCondition.reverseOperators(original);
        System.out.println("\n    ==> Transformed Code : \n\n" + transformed );

    }
}

