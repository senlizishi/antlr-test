import java.util.HashMap;
import java.util.Map;

public class TestAntlr4 extends TestBaseVisitor<Integer> {

    // map 中存放的是赋值的结果，比如 a = 5，map 中存放 a:5
    Map<String, Integer> map = new HashMap<>();
    // 每一个参数都是来自于 TestParse.XXXXContext，XXXX 也就是我们在规则中定义的名字
    // 完成变量赋值
    @Override
    public Integer visitAssign(TestParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        int value = visit(ctx.expr());
        map.put(id, value);
        return value;
    }
    // 输出数值
    @Override
    public Integer visitPrintExpr(TestParser.PrintExprContext ctx) {
        Integer value = visit(ctx.expr());
        System.out.println(value);
        return 0;
    }
    // 匹配括号
    @Override
    public Integer visitParens(TestParser.ParensContext ctx) {
        return visit(ctx.expr());
    }
    //通过 op 进行识别是乘还是除
    @Override
    public Integer visitMulDiv(TestParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        if (ctx.op.getType() == TestParser.MUL) {
            return left * right;
        }
        return left / right;
    }
    // 通过 op 识别是加还是减
    @Override
    public Integer visitAddSub(TestParser.AddSubContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        if (ctx.op.getType() == TestParser.ADD) {
            return left + right;
        }
        return left - right;
    }

    @Override
    public Integer visitId(TestParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (map.containsKey(id)) {
            return map.get(id);
        }
        return 0;
    }

    @Override
    public Integer visitInt(TestParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }
}
