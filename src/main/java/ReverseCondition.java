import org.apache.commons.lang3.StringUtils;
import spoon.Launcher;
import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.code.CtIf;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.factory.Factory;
import spoon.reflect.visitor.filter.TypeFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReverseCondition {


    public static CtClass reverseOperators(CtClass code) {

        Map<String, String> OperatorMap = new HashMap<>() {
            {
                put("<", ">");
                put(">", "<");
                put("==", "!=");
                put("!=", "==");
                put("<=", ">=");
                put(">=", "<=");
                put("&&", "||");
                put("||", "&&");
            }
        };
        // use a factory to create AST elements
        Factory factory = new Launcher().createFactory();

        // search for if statements
        List<CtIf> conditions = code.getElements(new TypeFilter(CtIf.class));

        // for each if statements, get condition and change operators
        for (CtIf con : conditions) {
                CtCodeSnippetExpression exp = factory.createCodeSnippetExpression(
                        StringUtils.replaceEach(con.getCondition().toString(), OperatorMap.keySet().toArray(new String[0]), OperatorMap.values().toArray(new String[0]))
                ) ;
            con.setCondition(exp);
        }
        return code;
    }
}
