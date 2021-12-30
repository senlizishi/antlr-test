import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Cal {

    public static void main(String[] args) {
        String s = "1 + 2 * 8";
        ANTLRInputStream input = new ANTLRInputStream(s);
        // 第一阶段的词法分析
        TestLexer lexer = new TestLexer(input);
        // 词法分析进行分词，获取 token
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // 第二阶段的语法分析
        TestParser parser = new TestParser(tokens);
        // 生成语法树
        ParseTree tree = parser.prog();
        // 实例化访问器类，只需要调用 visit 方法就可以实现我们想要的功能
        TestAntlr4 test = new TestAntlr4();
        test.visit(tree);
    }
}
