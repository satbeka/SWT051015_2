package form;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.swtchart.Chart;
import org.swtchart.ILineSeries;
import org.swtchart.LineStyle;
import org.swtchart.ISeries.SeriesType;
import org.eclipse.swt.widgets.Composite;

public class ChartOnForm {

    public double[] getxSeries() {
        return xSeries;
    }

    public void setxSeries(double[] xSeries) {
        this.xSeries = xSeries;
    }

    public double[] getySeries() {
        return ySeries;
    }

    public void setySeries(double[] ySeries) {
        this.ySeries = ySeries;
    }

    private double[] xSeries = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
    private double[] ySeries = { 0, -1.3, -2.0, -3.9, -5.6, -4.1, -5.3, -7.0, -3.9, -3.6, -1.1, 0};

    public void setDisplay3(Display display3) {
        this.display3 = display3;
    }

    private Display display3;
    private Shell shell3;

    public void create() {

        shell3 = new Shell(display3);

        shell3.setSize(500, 500);
        shell3.setText("Dive Profile");
        shell3.setSize(500, 400);
        shell3.setLayout(new FillLayout());
        createChart(shell3);
        shell3.open();

        /*
        while (!shell3.isDisposed()) {
            if (!display3.readAndDispatch()) {
                display3.sleep();
            }
        }
        display3.dispose();
        */

    }


    public Chart createChart(Composite parent) {
// create a chart
        Chart chart = new Chart(parent, SWT.NONE);
// set titles
        chart.getTitle().setText("Dive profile");
        chart.getAxisSet().getXAxis(0).getTitle().setText("Time");
        chart.getAxisSet().getYAxis(0).getTitle().setText("Depth");
// create scatter series
        ILineSeries series = (ILineSeries) chart.getSeriesSet()
                .createSeries(SeriesType.LINE, "series");
        series.setLineStyle(LineStyle.SOLID);
        series.enableArea(true);
        series.setXSeries(xSeries);
        series.setYSeries(ySeries);
// adjust the axis range
        chart.getAxisSet().adjustRange();
        return chart;
    }
}
