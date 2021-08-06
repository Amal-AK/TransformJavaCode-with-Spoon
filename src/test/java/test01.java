
import org.junit.Assert;
import org.junit.Test;
import spoon.Launcher;
import spoon.reflect.declaration.CtClass;


public class test01 {
    @Test
    public void testCodeEquality() {

        String originalCode = """
                class A {
                
                public XYDataItem addOrUpdate(Number x, Number y) {
                if (x == null) {
                throw new IllegalArgumentException("Null 'x' argument.");
                }
                XYDataItem overwritten = null;
                int index = indexOf(x);
                if (index >= 0 && !this.allowDuplicateXValues) {
                XYDataItem existing = (XYDataItem) this.data.get(index);
                try {
                overwritten = (XYDataItem) existing.clone();
                }
                catch (CloneNotSupportedException e) {
                throw new SeriesException("Couldn't clone XYDataItem!");
                }
                existing.setY(y);
                }
                else {
                if (this.autoSort) {
                this.data.add(-index - 1, new XYDataItem(x, y));
                }
                else {
                this.data.add(new XYDataItem(x, y));
                }
                if (getItemCount() > this.maximumItemCount) {
                this.data.remove(0);
                }
                }
                fireSeriesChanged();
                return overwritten;
                }
                }
                """;

        String expectedCode = """
                class A{
                public XYDataItem addOrUpdate(Number x, Number y) {
                if (x != null) {
                throw new IllegalArgumentException("Null 'x' argument.");
                }
                XYDataItem overwritten = null;
                int index = indexOf(x);
                if (index <= 0 || !this.allowDuplicateXValues) {
                XYDataItem existing = (XYDataItem) this.data.get(index);
                try {
                overwritten = (XYDataItem) existing.clone();
                }
                catch (CloneNotSupportedException e) {
                throw new SeriesException("Couldn't clone XYDataItem!");
                }
                existing.setY(y);
                }
                else {
                if (this.autoSort) {
                this.data.add(-index - 1, new XYDataItem(x, y));
                }
                else {
                this.data.add(new XYDataItem(x, y));
                }
                if (getItemCount() < this.maximumItemCount) {
                this.data.remove(0);
                }
                }
                fireSeriesChanged();
                return overwritten;
                }
                }
                """;
        CtClass<?> expected = Launcher.parseClass(expectedCode) ;
        CtClass transformed= ReverseCondition.reverseOperators(Launcher.parseClass(originalCode));
        Assert.assertEquals(transformed.toString(), expected.toString());

    }

}
