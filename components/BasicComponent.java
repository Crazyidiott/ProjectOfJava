package components;//可被编辑 点击？

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class BasicComponent extends JComponent{
    public BasicComponent(){
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                super.mousePressed(e);
                onMouseClicked();
            }
        });
    }

    public abstract void onMouseClicked();
}
