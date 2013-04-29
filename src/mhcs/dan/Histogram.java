package mhcs.dan;

import mhcs.dan.Module.ModuleType;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author Daniel Hammond
 *
 */
public class Histogram extends Widget {

    /**
     *
     */
    public enum Type {
        /**
         *
         */
        ADD,
        /**
         *
         */
        DELETE;
    }

    private FlowPanel panel;
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
    private Canvas canvas;
    private Context2d context;
    private int barWidth;
    private int barHeight;
    private int barSpacing;
    private int totalHeight;

    /**
     *
     */
    public Histogram() {
        barWidth = 50;
        barHeight = 5;
        barSpacing = 2;
        totalHeight = 200;
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
        canvas = Canvas.createIfSupported();
        makeCanvas();
        panel.add(canvas);
    }

    /**
     *
     */
    private void makeCanvas() {
        panel = new FlowPanel();
        canvas.setHeight("200px");
        canvas.setWidth("518px");
        canvas.setCoordinateSpaceHeight(200);
        canvas.setCoordinateSpaceWidth(518);
        context = canvas.getContext2d();
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
     */
    public final void update() {
        context.clearRect(barSpacing, totalHeight - plain * barHeight,
                barWidth, plain * barHeight);
        context.clearRect(barSpacing + (barWidth + barSpacing),
                totalHeight - dormitory * barHeight, barWidth,
                dormitory * barHeight);
        context.clearRect(barSpacing + 2 * (barWidth + barSpacing),
                totalHeight - sanitation * barHeight, barWidth,
                sanitation * barHeight);
        context.clearRect(barSpacing + 3 * (barWidth + barSpacing),
                totalHeight - foodAndWater * barHeight,
                barWidth, foodAndWater * barHeight);
        context.clearRect(barSpacing + 4 * (barWidth + barSpacing),
                totalHeight - gymAndRelaxation * barHeight,
                barWidth, gymAndRelaxation * barHeight);
        context.clearRect(barSpacing + 5 * (barWidth + barSpacing),
                totalHeight - canteen * barHeight,
                barWidth, canteen * barHeight);
        context.clearRect(barSpacing + 6 * (barWidth + barSpacing),
                totalHeight - power * barHeight,
                barWidth, power * barHeight);
        context.clearRect(barSpacing + 7 * (barWidth + barSpacing),
                totalHeight - control * barHeight,
                barWidth, control * barHeight);
        context.clearRect(barSpacing + 8 * (barWidth + barSpacing),
                totalHeight - airlock * barHeight,
                barWidth, airlock * barHeight);
        context.clearRect(barSpacing + 9 * (barWidth + barSpacing),
                totalHeight - medical * barHeight,
                barWidth, medical * barHeight);
        for (Module mod : ModuleList.moduleList) {
            if (mod.getType().equals(Module.ModuleType.PLAIN)) {
                plain++;
            } else if (mod.getType().equals(Module.ModuleType.DORMITORY)) {
                dormitory++;
            } else if (mod.getType().equals(Module.ModuleType.SANITATION)) {
                sanitation++;
            } else if (mod.getType().equals(Module.ModuleType.FOOD_AND_WATER)) {
                foodAndWater++;
            } else if (mod.getType().equals(
                    Module.ModuleType.GYM_AND_RELAXATION)) {
                gymAndRelaxation++;
            } else if (mod.getType().equals(Module.ModuleType.CANTEEN)) {
                canteen++;
            } else if (mod.getType().equals(Module.ModuleType.POWER)) {
                power++;
            } else if (mod.getType().equals(Module.ModuleType.CONTROL)) {
                control++;
            } else if (mod.getType().equals(Module.ModuleType.AIRLOCK)) {
                airlock++;
            } else if (mod.getType().equals(Module.ModuleType.MEDICAL)) {
                medical++;
            }
        }
        context.fillRect(barSpacing, totalHeight - plain * barHeight,
                barWidth, plain * barHeight);
        context.fillRect(barSpacing + (barWidth + barSpacing),
                totalHeight - dormitory * barHeight, barWidth,
                dormitory * barHeight);
        context.fillRect(barSpacing + 2 * (barWidth + barSpacing),
                totalHeight - sanitation * barHeight, barWidth,
                sanitation * barHeight);
        context.fillRect(barSpacing + 3 * (barWidth + barSpacing),
                totalHeight - foodAndWater * barHeight,
                barWidth, foodAndWater * barHeight);
        context.fillRect(barSpacing + 4 * (barWidth + barSpacing),
                totalHeight - gymAndRelaxation * barHeight,
                barWidth, gymAndRelaxation * barHeight);
        context.fillRect(barSpacing + 5 * (barWidth + barSpacing),
                totalHeight - canteen * barHeight, barWidth,
                canteen * barHeight);
        context.fillRect(barSpacing + 6 * (barWidth + barSpacing),
                totalHeight - power * barHeight,
                barWidth, power * barHeight);
        context.fillRect(barSpacing + 7 * (barWidth + barSpacing),
                totalHeight - control * barHeight,
                barWidth, control * barHeight);
        context.fillRect(barSpacing + 8 * (barWidth + barSpacing),
                totalHeight - airlock * barHeight,
                barWidth, airlock * barHeight);
        context.fillRect(barSpacing + 9 * (barWidth + barSpacing),
                totalHeight - medical * barHeight,
                barWidth, medical * barHeight);
    }

    /**
     *
     * @param moduleType type of update for the histogram (ADD, DELETE)
     * @param type type of module being updated
     */
     public final void update(final Module.ModuleType moduleType,
             final Histogram.Type type) {
         if (moduleType == ModuleType.PLAIN) {
             context.clearRect(barSpacing, totalHeight - plain * barHeight,
                     barWidth, plain * barHeight);
             if (type == Type.ADD) {
                 plain++;
             } else {
                 plain--;
             }
             context.fillRect(barSpacing, totalHeight - plain * barHeight,
                     barWidth, plain * barHeight);
         } else if (moduleType == ModuleType.DORMITORY) {
             context.clearRect(barSpacing + (barWidth + barSpacing),
                     totalHeight - dormitory * barHeight, barWidth,
                     dormitory * barHeight);
             if (type == Type.ADD) {
                 dormitory++;
             } else {
                 dormitory--;
             }
             context.fillRect(barSpacing + (barWidth + barSpacing),
                     totalHeight - dormitory * barHeight, barWidth,
                     dormitory * barHeight);
         } else if (moduleType == ModuleType.SANITATION) {
             context.clearRect(barSpacing + 2 * (barWidth + barSpacing),
                     totalHeight - sanitation * barHeight, barWidth,
                     sanitation * barHeight);
             if (type == Type.ADD) {
                 sanitation++;
             } else {
                 sanitation--;
             }
             context.fillRect(barSpacing + 2 * (barWidth + barSpacing),
                     totalHeight - sanitation * barHeight, barWidth,
                     sanitation * barHeight);
         } else if (moduleType == ModuleType.FOOD_AND_WATER) {
             context.clearRect(barSpacing + 3 * (barWidth + barSpacing),
                     totalHeight - foodAndWater * barHeight,
                     barWidth, foodAndWater * barHeight);
             if (type == Type.ADD) {
                 foodAndWater++;
             } else {
                 foodAndWater--;
             }
             context.fillRect(barSpacing + 3 * (barWidth + barSpacing),
                     totalHeight - foodAndWater * barHeight,
                     barWidth, foodAndWater * barHeight);
         } else if (moduleType == ModuleType.GYM_AND_RELAXATION) {
             context.clearRect(barSpacing + 4 * (barWidth + barSpacing),
                     totalHeight - gymAndRelaxation * barHeight,
                     barWidth, gymAndRelaxation * barHeight);
             if (type == Type.ADD) {
                 gymAndRelaxation++;
             } else {
                 gymAndRelaxation--;
             }
             context.fillRect(barSpacing + 4 * (barWidth + barSpacing),
                     totalHeight - gymAndRelaxation * barHeight,
                     barWidth, gymAndRelaxation * barHeight);
         } else if (moduleType == ModuleType.CANTEEN) {
             context.clearRect(barSpacing + 5 * (barWidth + barSpacing),
                     totalHeight - canteen * barHeight,
                     barWidth, canteen * barHeight);
             if (type == Type.ADD) {
                 canteen++;
             } else {
                 canteen--;
             }
             context.fillRect(barSpacing + 5 * (barWidth + barSpacing),
                     totalHeight - canteen * barHeight, barWidth,
                     canteen * barHeight);
         } else if (moduleType == ModuleType.POWER) {
             context.clearRect(barSpacing + 6 * (barWidth + barSpacing),
                     totalHeight - power * barHeight,
                     barWidth, power * barHeight);
             if (type == Type.ADD) {
                 power++;
             } else {
                 power--;
             }
             context.fillRect(barSpacing + 6 * (barWidth + barSpacing),
                     totalHeight - power * barHeight,
                     barWidth, power * barHeight);
         } else if (moduleType == ModuleType.CONTROL) {
             context.clearRect(barSpacing + 7 * (barWidth + barSpacing),
                     totalHeight - control * barHeight,
                     barWidth, control * barHeight);
             if (type == Type.ADD) {
                 control++;
             } else {
                 control--;
             }
             context.fillRect(barSpacing + 7 * (barWidth + barSpacing),
                     totalHeight - control * barHeight,
                     barWidth, control * barHeight);
         } else if (moduleType == ModuleType.AIRLOCK) {
             context.clearRect(barSpacing + 8 * (barWidth + barSpacing),
                     totalHeight - airlock * barHeight,
                     barWidth, airlock * barHeight);
             if (type == Type.ADD) {
                 airlock++;
             } else {
                 airlock--;
             }
             context.fillRect(barSpacing + 8 * (barWidth + barSpacing),
                     totalHeight - airlock * barHeight,
                     barWidth, airlock * barHeight);
         } else if (moduleType == ModuleType.MEDICAL) {
             context.clearRect(barSpacing + 9 * (barWidth + barSpacing),
                     totalHeight - medical * barHeight,
                     barWidth, medical * barHeight);
             if (type == Type.ADD) {
                 medical++;
             } else {
                 medical--;
             }
             context.fillRect(barSpacing + 9 * (barWidth + barSpacing),
                     totalHeight - medical * barHeight,
                     barWidth, medical * barHeight);
         }
     }
}
