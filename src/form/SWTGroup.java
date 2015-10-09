package form;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

public class SWTGroup extends Composite
{
    public SWTGroup(Composite parent)
    {
        super(parent, SWT.NONE);
        this.setSize(600, 600);

        Group group = new Group(this,SWT.PUSH);
        group.setLocation(10, 10);
        //group.setSize(1000,1000);
        //group.setSize(200,300);

        group.setText("Group of Dictionaries");

        Label label = new Label(group, SWT.PUSH);
        label.setText("Fill ");
        label.setLocation(20, 20);
        label.pack();

        Label label1= new Label(group, SWT.PUSH);
        label1.setText("Dict1:");
        label1.setLocation(0, 40);
        label1.pack();
        Combo combo1;
        combo1 = new Combo(group, SWT.NONE);
        combo1.setItems(new String[]{"Collie", "Pitbull", "Poodle",
                "Scottie", "Black Lab"});
        combo1.setLocation(40, 40);
        combo1.pack();

        Label label2= new Label(group, SWT.PUSH);
        label2.setText("Dict2:");
        label2.setLocation(0, 60);
        label2.pack();
        Combo combo2;
        combo2 = new Combo(group, SWT.NONE);
        combo2.setItems(new String[]{"Collie", "Pitbull", "Poodle",
                "Scottie", "Black Lab"});
        combo2.setLocation(40, 60);
        combo2.pack();

        Label label3= new Label(group, SWT.PUSH);
        label3.setText("Dict3:");
        label3.setLocation(0, 80);
        label3.pack();
        Combo combo3;
        combo3 = new Combo(group, SWT.NONE);
        combo3.setItems(new String[]{"Collie", "Pitbull", "Poodle",
                "Scottie", "Black Lab"});
        combo3.setLocation(40, 80);
        combo3.pack();

        Label label4= new Label(group, SWT.PUSH);
        label4.setText("Dict4:");
        label4.setLocation(0, 100);
        label4.pack();
        Combo combo4;
        combo4 = new Combo(group, SWT.NONE);
        combo4.setItems(new String[]{"Collie", "Pitbull", "Poodle",
                "Scottie", "Black Lab"});
        combo4.setLocation(40, 100);
        combo4.pack();

        Label label5= new Label(group, SWT.PUSH);
        label5.setText("Dict5:");
        label5.setLocation(0, 120);
        label5.pack();
        Combo combo5;
        combo5 = new Combo(group, SWT.NONE);
        combo5.setItems(new String[]{"Collie", "Pitbull", "Poodle",
                "Scottie", "Black Lab"});
        combo5.setLocation(40, 120);
        combo5.pack();

        Label label6= new Label(group, SWT.PUSH);
        label6.setText("Dict6:");
        label6.setLocation(0, 140);
        label6.pack();
        Combo combo6;
        combo6 = new Combo(group, SWT.NONE);
        combo6.setItems(new String[]{"Collie", "Pitbull", "Poodle",
                "Scottie", "Black Lab"});
        combo6.setLocation(40, 140);
        combo6.pack();

        Label label7= new Label(group, SWT.PUSH);
        label7.setText("Dict7:");
        label7.setLocation(0, 160);
        label7.pack();
        Combo combo7;
        combo7 = new Combo(group, SWT.NONE);
        combo7.setItems(new String[]{"Collie", "Pitbull", "Poodle",
                "Scottie", "Black Lab"});
        combo7.setLocation(40, 160);
        combo7.pack();

        Label label8= new Label(group, SWT.PUSH);
        label8.setText("Dict8:");
        label8.setLocation(0, 180);
        label8.pack();
        Combo combo8;
        combo8 = new Combo(group, SWT.NONE);
        combo8.setItems(new String[]{"Collie", "Pitbull", "Poodle",
                "Scottie", "Black Lab"});
        combo8.setLocation(40, 180);
        combo8.pack();

        Label label9= new Label(group, SWT.PUSH);
        label9.setText("Dict9:");
        label9.setLocation(0, 200);
        label9.pack();
        Combo combo9;
        combo9 = new Combo(group, SWT.NONE);
        combo9.setItems(new String[]{"Collie", "Pitbull", "Poodle",
                "Scottie", "Black Lab"});
        combo9.setLocation(40, 200);
        combo9.pack();



        Button button = new Button(group, SWT.PUSH);
        button.setText("Dobavit v tab10");
        button.setLocation(470,240);
        button.pack();

        group.pack();

    }
}
