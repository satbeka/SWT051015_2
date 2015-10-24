package form;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

import java.util.ArrayList;

public class TableOnForm {
    public Display getDisplay2() {
        return display2;
    }

    public void setDisplay2(Display display2) {
        this.display2 = display2;
    }

    private Display display2;
    private Shell shell2;

    private Table table;
    private TableColumn stringColumn1,stringColumn2,dateColumn,doubleColumn,hourColumn;
    private String tabTitle;

    Menu contextMenu;

    public void load(ArrayList arrayList,String tabTitle,String itTitleId, String itTitle1,String itTitle2) {

        shell2 = new Shell(display2);

        shell2.setSize(600, 450);
        shell2.setText(tabTitle);
        shell2.setLayout(new FillLayout());


        table = new Table(shell2, SWT.HIDE_SELECTION | SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        stringColumn1 = new TableColumn(table, SWT.CENTER);
        stringColumn1.setText(itTitleId);
        stringColumn1.setWidth(120);

        stringColumn1 = new TableColumn(table, SWT.CENTER);
        stringColumn1.setText(itTitle1);
        stringColumn1.setWidth(120);
        //intColumn.addListener(SWT.Selection, SortListenerFactory.getListener(SortListenerFactory.INT_COMPARATOR));

        stringColumn2 = new TableColumn(table, SWT.CENTER);
        stringColumn2.setText(itTitle2);
        stringColumn2.setWidth(120);
        //stringColumn.addListener(SWT.Selection, SortListenerFactory.getListener(SortListenerFactory.STRING_COMPARATOR));

        /*
        dateColumn = new TableColumn(table, SWT.CENTER);
        dateColumn.setText("Date Column");
        dateColumn.setWidth(120);
        //dateColumn.addListener(SWT.Selection, SortListenerFactory.getListener(SortListenerFactory.DATE_COMPARATOR));

        doubleColumn = new TableColumn(table, SWT.CENTER);
        doubleColumn.setText("Double Column");
        doubleColumn.setWidth(120);
        //doubleColumn.addListener(SWT.Selection, SortListenerFactory.getListener(SortListenerFactory.DOUBLE_COMPARATOR));

        hourColumn = new TableColumn(table, SWT.CENTER);
        hourColumn.setText("Hour Column");
        hourColumn.setWidth(120);
        //hourColumn.addListener(SWT.Selection, SortListenerFactory.getListener(SortListenerFactory.HOUR_COMPARATOR));
*/

        // let's populate the table with random data!
        String strV1;String strV;String strV2;String strV3;int k;
        for (int i = 0; i < arrayList.size(); i++) {


            // ok now store it in the table
            TableItem item = new TableItem(table, SWT.NONE);
            String v=arrayList.get(i).toString();
            //item.setText(v);
            k=v.indexOf(";");
            strV1=v.substring(0, k);

            strV=v.substring(k+1);
            k=strV.indexOf(";");
            strV2=strV.substring(0, k);
            strV3=strV.substring(k + 1);

            item.setText(new String[]{strV1, strV2,strV3});

            //item.setText(new String[] { int_value, string_value, date_value, double_value, hour_value });


            }


        // Create the listener
        //MouseEventExample myMouseEventExample = new MouseEventExample(shell2);
        shell2.pack();

        this.tabTitle=tabTitle; //name of tbl in Db
        System.out.println("tabTitle="+tabTitle);

        contextMenu = new Menu(table);
        table.setMenu(contextMenu);
        MenuItem mItem1 = new MenuItem(contextMenu, SWT.None);
        MenuItem mItem2 = new MenuItem(contextMenu, SWT.None);
        MenuItem mItem3 = new MenuItem(contextMenu, SWT.None);
        mItem1.setText(" Add Test.");
        mItem2.setText(" Edit Test.");
        mItem3.setText(" Delete Test.");


        TableEditor tableEditor=new TableEditor(table);
        int i=tableEditor.getColumn();



        //Insert
        mItem1.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event e) {
                System.out.println("Add Test.");


            }
        });

        //Edit
        mItem2.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event e) {
                System.out.println("Edit Test.");


            }
        });







        table.addMouseListener(new MouseListener() {
            public void mouseDown(MouseEvent e) {
                System.out.println("Mouse Down."+e.button);
                if (e.button==3){

                    contextMenu.setVisible(true);
                    System.out.println("tabTitle="+tabTitle);



                }
            }

            public void mouseUp(MouseEvent e) {
                System.out.println("Mouse Up.");
            }

            public void mouseDoubleClick(MouseEvent e) {
                System.out.println("Mouse Double click.");
            }

        });


        shell2.open();


    }
}
