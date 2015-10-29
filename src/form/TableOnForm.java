package form;
import dboperation.UserData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

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

    public void load(ArrayList<String[]> arrayListData,String tabTitle,String itTitleId, String itTitle1,String itTitle2) {

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
        //String strV1;String strV;String strV2;String strV3;int k;
        int size=table.getColumnCount();
        int sizeData=arrayListData.size();
        String[] arrV=new String[size];
        for (int i = 0; i < sizeData; i++) {

            // ok now store it in the table
            TableItem item = new TableItem(table, SWT.NONE);
            arrV=arrayListData.get(i);

/*
            //item.setText(v);
            k=v.indexOf(";");
            strV1=v.substring(0, k);
            item.setData("Id", strV1);
            System.out.println(" Id="+item.getData("Id"));

            strV=v.substring(k+1);
            k=strV.indexOf(";");
            strV2=strV.substring(0, k);
            strV3=strV.substring(k + 1);
*/ //251015

            item.setText(arrV);

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

        //System.out.println("table.getSelection()="+table.getSelection());
        //System.out.println("table.getSelectionIndex()="+table.getSelectionIndex());
        final TableCursor cursor = new TableCursor(table, SWT.NONE);
        cursor.addSelectionListener(new SelectionAdapter() {
                                        // This is called as the user navigates around the table
                                        public void widgetSelected(SelectionEvent event) {
                                            // Select the row in the table where the TableCursor is
                                            table.setSelection(new TableItem[]{cursor.getRow()});
                                            System.out.println("cursor.getRow().getText()=" + cursor.getRow().getText());

                                        }
                                    }
        );

            mItem1.setData("Id", cursor.getRow());


            //Insert
            mItem1.addListener(SWT.Selection, new

                            Listener() {
                                public void handleEvent(Event e) {
                                    System.out.println("Add Test.");


                                    final Shell shellEdit = new Shell(display2, SWT.NO);
                                    RowLayout layout = new RowLayout(SWT.VERTICAL);
                                    layout.marginLeft = 20;
                                    layout.marginTop = 20;
                                    //layout.justify = true;

                                    //shellEdit = new Shell(display2, SWT.NO);
                                    shellEdit.setText("Add");
                                    shellEdit.setSize(300, 300);
                                    shellEdit.setLayout(layout);

                    /*
                    final Text text = new Text(cursor, SWT.VERTICAL);
                    text.setFocus();
                    // Copy the text from the cell to the Text control
                    text.setText(cursor.getRow().getText());
                    text.setFocus();
                    */

                /*
                int itInd=table.getSelectionIndex();
                System.out.println("itInd="+itInd);
                System.out.println("table.getItem(itInd).getText()=" + table.getItem(itInd).getText());
                */

                    /*
                    System.out.println("e.widget=" + e.widget);
                    Object object = e.widget;
                    MenuItem menuItemEdit = (MenuItem) object;
                    String id = //String.valueOf(table.getSelectionIndex());
                            e.widget.getData("Id").toString();
                    */

                                    System.out.println("id=" + cursor.getRow());


                    /*
                    int idInt = Integer.getInteger(id);
                    TableItem tableItemFrom = table.getItem(idInt);
                    System.out.println("tableItemFrom.getText()=" + tableItemFrom.getText());
                    */

                                    HashMap<Integer, String> map = new HashMap<Integer, String>();
                                    for (int i = 1; i < size; i++) {

                                        Label label = new Label(shellEdit, SWT.SINGLE);
                                        label.setText(table.getColumn(i).getText());

                                        Text text = new Text(shellEdit, SWT.PUSH | SWT.BORDER);
                                        text.setFocus();
                                        // Copy the text from the cell to the Text control

                                        //table.getItem(itInd).getText()

                                        //text.setText(cursor.getRow().getText(i));
                                        text.setText("");
                                        //map.put(i,cursor.getRow().getText(i));
                                        map.put(i, "");
                                        final Integer k = i;
                                        //text.setData("columnId",i);
                                        text.setEditable(true);
                                        text.setFocus();

                                        text.addModifyListener(new ModifyListener() {
                                            public void modifyText(ModifyEvent me) {
                                                //Text textM=(Text)me.widget;
                                                //System.out.println("textM.getText()="+textM.getText());
                                                //text.setData("");
                                                map.remove(k);
                                                map.put(k, text.getText());
                                                System.out.println("k=" + k);
                                                System.out.println("map.get(k)=" + map.get(k));

                                            }
                                        });
                                        //Text text = new Text(shell2, SWT.SINGLE | SWT.BORDER);

                                        System.out.println("i=" + i);
                                        //text.setText("888");

                                    }


                                    Button button = new Button(shellEdit, SWT.BUTTON2);
                                    button.setText("Save");
                                    button.addListener(SWT.Selection, new Listener() {
                                        public void handleEvent(Event e) {
                                            switch (e.type) {
                                                case SWT.Selection:
                                                    System.out.println("Button pressed");

                                                    TableItem tableItemEdit;
                                                    tableItemEdit = new TableItem(table, SWT.NONE);


                                                    String strV;
                                                    String[] arrV = new String[size];
                                                    System.out.println("size=" + size);
                                                    for (int i = 1; i < size; i++) {

                                                        System.out.println("i=" + i);
                                                        arrV[i] = map.get(i);
                                                        //text.setText("888");

                                                    }
                                                    arrV[0] = UserData.insTWLTCSTATUS(arrV);

                                                    tableItemEdit.setText(arrV);


                                                    break;
                                            }
                                        }
                                    });


                                    shellEdit.open();


                                }
                            }

            );

            //Edit
            mItem2.addListener(SWT.Selection, new

                            Listener() {
                                public void handleEvent(Event e) {
                                    System.out.println("Edit Test.");


                                    final Shell shellEdit = new Shell(display2, SWT.NO);
                                    RowLayout layout = new RowLayout(SWT.VERTICAL);
                                    layout.marginLeft = 20;
                                    layout.marginTop = 20;
                                    //layout.justify = true;

                                    //shellEdit = new Shell(display2, SWT.NO);
                                    shellEdit.setText("Edit");
                                    shellEdit.setSize(300, 300);
                                    shellEdit.setLayout(layout);



                                    System.out.println("id=" + cursor.getRow());
                                    //TableItem item=(TableItem)e.widget;
                                    //System.out.println("table.indexOf(item)="+table.indexOf(item));


                                    HashMap<Integer, String> map = new HashMap<Integer, String>();
                                    for (int i = 0; i < size; i++) {

                                        Label label=new Label(shellEdit, SWT.SINGLE );
                                        label.setText(table.getColumn(i).getText());

                                        Text text = new Text(shellEdit, SWT.PUSH| SWT.BORDER);
                                        text.setFocus();
                                        text.setText(cursor.getRow().getText(i));
                                        map.put(i,cursor.getRow().getText(i));
                                        //map.put(i,"");
                                        final Integer k=i;
                                        //text.setData("columnId",i);
                                        if (i!=0)
                                        {text.setEditable(true);}
                                        text.setFocus();

                                        text.addModifyListener(new ModifyListener() {
                                            public void modifyText(ModifyEvent me) {
                                                map.remove(k);
                                                map.put(k, text.getText());
                                                System.out.println("k="+k);
                                                System.out.println("map.get(k)="+map.get(k));

                                            }
                                        });
                                        //Text text = new Text(shell2, SWT.SINGLE | SWT.BORDER);

                                        System.out.println("i=" + i);
                                        //text.setText("888");

                                    }


                                    Button button=new Button(shellEdit,SWT.BUTTON2);
                                    button.setText("Save");
                                    button.addListener(SWT.Selection, new Listener() {
                                        public void handleEvent(Event e) {
                                            switch (e.type) {
                                                case SWT.Selection:
                                                    System.out.println("Button pressed");


                                                    System.out.println("id3333=" + cursor.getRow());
                                                    //TableItem item=(TableItem)e.widget;
                                                    //System.out.println("table.indexOf(item)="+table.indexOf(item));
                                                    int index = table.getSelectionIndex();
                                                    System.out.println("index table.getSelectionIndex()=" + index);
                                                    TableItem tableItemEdit = table.getItem(index);


                                                    //tableItemEdit = new TableItem(table, SWT.NONE);
                                                    String strV;
                                                    String[] arrV = new String[size];
                                                    System.out.println("size=" + size);
                                                    for (int i = 0; i < size; i++) {

                                                        System.out.println("i=" + i);
                                                        arrV[i] = map.get(i);
                                                        //text.setText("888");

                                                    }
                                                    //arrV[0]= UserData.updTWLTCSTATUS(arrV);
                                                    arrV[0] = UserData.updTWLTCSTATUS(arrV);

                                                    tableItemEdit.setText(arrV);

                                                    shellEdit.close();
                                                    System.out.println("shellEdit.close();====");
                                                    break;
                                            }
                                        }
                                    });


                                    System.out.println("shellEdit.open();====");
                                    shellEdit.open();







                                }
                            }

            );

        //Delete
        mItem3.addListener(SWT.Selection, new

                        Listener() {
                            public void handleEvent(Event e) {
                                System.out.println("Delete Test.");


                                final Shell shellEdit = new Shell(display2, SWT.NO);
                                RowLayout layout = new RowLayout(SWT.VERTICAL);
                                layout.marginLeft = 20;
                                layout.marginTop = 20;
                                //layout.justify = true;

                                //shellEdit = new Shell(display2, SWT.NO);
                                shellEdit.setText("Delete");
                                shellEdit.setSize(300, 300);
                                shellEdit.setLayout(layout);



                                System.out.println("id=" + cursor.getRow());
                                //TableItem item=(TableItem)e.widget;
                                //System.out.println("table.indexOf(item)="+table.indexOf(item));


                                HashMap<Integer, String> map = new HashMap<Integer, String>();
                                for (int i = 0; i < size; i++) {

                                    Label label=new Label(shellEdit, SWT.SINGLE );
                                    label.setText(table.getColumn(i).getText());

                                    Text text = new Text(shellEdit, SWT.PUSH| SWT.BORDER);
                                    text.setFocus();
                                    text.setText(cursor.getRow().getText(i));
                                    map.put(i,cursor.getRow().getText(i));
                                    //map.put(i,"");
                                    final Integer k=i;
                                    //text.setData("columnId",i);
                                    if (i!=0)
                                    {text.setEditable(true);}
                                    text.setFocus();

                                    text.addModifyListener(new ModifyListener() {
                                        public void modifyText(ModifyEvent me) {
                                            map.remove(k);
                                            map.put(k, text.getText());
                                            System.out.println("k="+k);
                                            System.out.println("map.get(k)="+map.get(k));

                                        }
                                    });
                                    //Text text = new Text(shell2, SWT.SINGLE | SWT.BORDER);

                                    System.out.println("i=" + i);
                                    //text.setText("888");

                                }


                                Button button=new Button(shellEdit,SWT.BUTTON2);
                                button.setText("Delete");
                                button.addListener(SWT.Selection, new Listener() {
                                    public void handleEvent(Event e) {
                                        switch (e.type) {
                                            case SWT.Selection:
                                                System.out.println("Button pressed");


                                                System.out.println("id3333=" + cursor.getRow());
                                                //TableItem item=(TableItem)e.widget;
                                                //System.out.println("table.indexOf(item)="+table.indexOf(item));
                                                int index = table.getSelectionIndex();
                                                System.out.println("index table.getSelectionIndex()=" + index);
                                                TableItem tableItemEdit = table.getItem(index);


                                                //tableItemEdit = new TableItem(table, SWT.NONE);
                                                String strV;
                                                String[] arrV = new String[size];
                                                System.out.println("size=" + size);
                                                for (int i = 0; i < size; i++) {

                                                    System.out.println("i=" + i);
                                                    arrV[i] = map.get(i);
                                                    //text.setText("888");

                                                }
                                                //arrV[0]= UserData.updTWLTCSTATUS(arrV);
                                                arrV[0] = UserData.delTWLTCSTATUS(arrV);

                                                tableItemEdit.setText(arrV);

                                                shellEdit.close();
                                                System.out.println("shellEdit.close();====");
                                                shell2.close();
                                                break;
                                        }
                                    }
                                });


                                System.out.println("shellEdit.open();====");
                                shellEdit.open();







                            }
                        }

        );

        table.addMouseListener(new

                                       MouseListener() {
                                           public void mouseDown(MouseEvent e) {
                                               System.out.println("Mouse Down." + e.button);
                                               if (e.button == 3) {

                                                   contextMenu.setVisible(true);
                                                   System.out.println("tabTitle=" + tabTitle);


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
