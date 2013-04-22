package mhcs.dan;

import mhcs.dan.Module.ModuleType;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Widget;

import com.google.gwt.user.client.Window;

/**
 *
 * @author Daniel Hammond
 *
 */
public class Histogram extends Widget {

    public enum Type {
        ADD,
        DELETE;
    }

    private DecoratorPanel panel;
    private int plain;
    private int dormitory;
    private int sanitation;
    private int foodAndWater;
    private int gymAndRelaxation;
    private int canteen;
    private int power;
    private int control;
    private int airlock;
    private int medical;
    private Canvas canvas = Canvas.createIfSupported();
    private Context2d context;

    /**
     *
     */
    public Histogram() {
        plain = 0;
        dormitory = 0;
        sanitation = 0;
        foodAndWater = 0;
        gymAndRelaxation = 0;
        canteen = 0;
        power = 0;
        control = 0;
        airlock = 0;
        medical = 0;
        makeCanvas();
        panel.add(canvas);
    }

    /**
     *
     */
    private void makeCanvas() {
        panel = new DecoratorPanel();
        canvas.setHeight("200px");
        canvas.setWidth("500px");
        canvas.setCoordinateSpaceHeight(1000);
        canvas.setCoordinateSpaceWidth(2000);
        context = canvas.getContext2d();
        context.fillRect(0, 1000 - plain * 25, 40, plain * 25);
    }

    /**
     *
     * @return panel the panel that holds the histogram
     */
    public final Widget get() {
        return panel;
    }

    /**
     *
     * @param module the module being added or deleted
     * @param type the type of update (ADD or DELETE)
     */
    public final void update(final Module.ModuleType moduleType,
            final Histogram.Type type) {
        if (moduleType == ModuleType.PLAIN) {
            context.clearRect(0, 1000 - plain * 25, 40, plain * 25);
            if (type == Type.ADD) {
                plain++;
            } else {
                plain--;
            }
            context.fillRect(0, 1000 - plain * 25, 40, plain * 25);
        } else if (moduleType == ModuleType.DORMITORY) {
            context.clearRect(80, 1000 - dormitory * 25, 40, dormitory * 25);
            if (type == Type.ADD) {
                dormitory++;
            } else {
                dormitory--;
            }
            context.fillRect(80, 1000 - dormitory * 25, 40, dormitory * 25);
        } else if (moduleType == ModuleType.SANITATION) {
            context.clearRect(160, 1000 - sanitation * 25, 40, sanitation * 25);
            if (type == Type.ADD) {
                sanitation++;
            } else {
                sanitation--;
            }
            context.fillRect(160, 1000 - sanitation * 25, 40, sanitation * 25);
        } else if (moduleType == ModuleType.FOOD_AND_WATER) {
            context.clearRect(240, 1000 - foodAndWater * 25,
                    40, foodAndWater * 25);
            if (type == Type.ADD) {
                foodAndWater++;
            } else {
                foodAndWater--;
            }
            context.fillRect(240, 1000 - foodAndWater * 25,
                    40, foodAndWater * 25);
        } else if (moduleType == ModuleType.GYM_AND_RELAXATION) {
            context.clearRect(320, 1000 - gymAndRelaxation * 25,
                    40, gymAndRelaxation * 25);
            if (type == Type.ADD) {
                gymAndRelaxation++;
            } else {
                gymAndRelaxation--;
            }
            context.fillRect(320, 1000 - gymAndRelaxation * 25,
                    40, gymAndRelaxation * 25);
        } else if (moduleType == ModuleType.CANTEEN) {
            context.clearRect(400, 1000 - canteen * 25, 40, canteen * 25);
            if (type == Type.ADD) {
                canteen++;
            } else {
                canteen--;
            }
            context.fillRect(400, 1000 - canteen * 25, 40, canteen * 25);
        } else if (moduleType == ModuleType.POWER) {
            context.clearRect(480, 1000 - power * 25, 40, power * 25);
            if (type == Type.ADD) {
                power++;
            } else {
                power--;
            }
            context.fillRect(480, 1000 - power * 25, 40, power * 25);
        } else if (moduleType == ModuleType.CONTROL) {
            context.clearRect(560, 1000 - control * 25, 40, control * 25);
            if (type == Type.ADD) {
                control++;
            } else {
                control--;
            }
            context.fillRect(560, 1000 - control * 25, 40, control * 25);
        } else if (moduleType == ModuleType.AIRLOCK) {
            context.clearRect(640, 1000 - airlock * 25, 40, airlock * 25);
            if (type == Type.ADD) {
                airlock++;
            } else {
                airlock--;
            }
            context.fillRect(640, 1000 - airlock * 25, 40, airlock * 25);
        } else if (moduleType == ModuleType.MEDICAL) {
            context.clearRect(720, 1000 - medical * 25, 40, medical * 25);
            if (type == Type.ADD) {
                medical++;
            } else {
                medical--;
            }
            context.fillRect(720, 1000 - medical * 25, 40, medical * 25);
        }
    }
}
