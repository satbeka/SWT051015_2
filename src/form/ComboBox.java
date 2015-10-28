package form;
import dboperation.UserData;
import model.Sbor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.*;

import java.util.Iterator;
import java.util.List;

public class ComboBox {
    // Strings to use as list items
    private static String[] sborItems;
    private static String[] upragnItems;
    private static String comboSborChoose="";
    private static String comboUpagnChoose="";
    private static Shell shell;
    private static Label labelD;
    private static Text titleDate;
    private static Button openUpragn;

    public static void main(String[] args) {
        Display display = new Display();
        shell = new Shell(display);
        GridLayout layout = new GridLayout(2, false);
        shell.setLayout(layout);

        //UserData userData=new UserData();
        // Create a dropdown Combo
        Label labelSbor=new Label(shell,SWT.NONE);
        labelSbor.setText("1 Choose Sbor: ");
        //labelSbor.setLocation(1020,20);
        final Combo comboSbor = new Combo(shell, SWT.DROP_DOWN);

        System.out.println("#1 iterator");
        List<Sbor> list=UserData.getSborFromSQLite();
        Iterator<Sbor> iterator = list.iterator();
        int i=0;
        sborItems=new String[list.size()];
        while (iterator.hasNext()) {
            //System.out.println(iterator.next().getName());
            sborItems[i]=iterator.next().getName();
            i++;
        }
        System.out.println(sborItems[3]);
        comboSbor.setItems(sborItems);

        // create new layout data
        GridData data = new GridData(SWT.FILL, SWT.TOP, true, false);
        data.horizontalSpan = 3;
        //rowLayout.center;
        //shell.setLayout(gridLayout);

        final Button open;
        open = new Button (shell, SWT.PUSH);
        open.setLayoutData(data);
        open.setLocation(10, 10);
        open.setText(" 2 Choose date ");

        open.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                final Shell dialog = new Shell(shell, SWT.DIALOG_TRIM);
                dialog.setLayout(new GridLayout(3, false));

                final DateTime calendar = new DateTime(dialog, SWT.CALENDAR | SWT.BORDER);
                final DateTime date = new DateTime(dialog, SWT.DATE | SWT.SHORT);
                final DateTime time = new DateTime(dialog, SWT.TIME | SWT.SHORT);

                new Label(dialog, SWT.NONE);
                new Label(dialog, SWT.NONE);
                Button ok = new Button(dialog, SWT.PUSH);
                ok.setText("Choose date");
                ok.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
                ok.addSelectionListener(new SelectionAdapter() {
                    public void widgetSelected(SelectionEvent e) {
                        System.out.println("Calendar date selected (MM/DD/YYYY) = " + (calendar.getMonth() + 1) + "/" + calendar.getDay() + "/" + calendar.getYear());
                        System.out.println("Date selected (MM/YYYY) = " + (date.getMonth() + 1) + "/" + date.getYear());
                        System.out.println("Time selected (HH:MM) = " + time.getHours() + ":" + time.getMinutes());

                        openUpragn.setVisible(true);
                        labelD.setVisible(true);
                        titleDate.setVisible(true);

                        titleDate.setText("Calendar date selected (MM/DD/YYYY) = " + (calendar.getMonth() + 1) + "/" + calendar.getDay() + "/" + calendar.getYear());


                        dialog.close();

                        //textDate
                    }
                });
                dialog.setDefaultButton(ok);
                dialog.pack();
                dialog.open();
            }
        });
        open.setVisible(false);

// create new layout data
        data = new GridData(SWT.FILL, SWT.TOP, true, false);
        data.horizontalSpan = 2;

        // TextDate
        labelD = new Label(shell, SWT.NULL);
        labelD.setText("# Chosen Date is ");
        titleDate = new Text(shell, SWT.SINGLE | SWT.BORDER);
        GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData.horizontalSpan = 3;
        titleDate.setLayoutData(gridData);
        labelD.setVisible(false);
        titleDate.setVisible(false);


// create new layout data
        data = new GridData(SWT.FILL, SWT.TOP, true, false);
        data.horizontalSpan = 2;

        // Title
        final Label label = new Label(shell, SWT.NULL);
        label.setText("# trenirvk ");
        final Text titleTrenik = new Text(shell, SWT.SINGLE | SWT.BORDER);
        gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData.horizontalSpan = 3;
        titleTrenik.setLayoutData(gridData);
        label.setVisible(false);
        titleTrenik.setVisible(false);


        comboSbor.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {

                System.out.println(" " + comboSbor.getSelectionIndex());
                comboSborChoose=comboSbor.getItem(comboSbor.getSelectionIndex());
                System.out.println("comboSborChoose="+comboSborChoose);
                if (!comboSborChoose.isEmpty()){


                    open.setVisible(true);
                    label.setVisible(true);
                    titleTrenik.setVisible(true);






                }

            }
        });
        shell.pack();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}


