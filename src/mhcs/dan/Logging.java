package mhcs.dan;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Daniel Hammond
 */
public class Logging { // !implements EntryPoint

    /* properties */

    private Label locationInfoLabel;
    private Label damageInfoLabel;
    private Label orientationInfoLabel;
    private Label typeInfoLabel;
    private ListBox moduleListBox;
    private Label deleteTitle;
    private VerticalPanel deletePanel;
    private VerticalPanel deleteLeftPanel;
    private VerticalPanel deleteRightPanel;
    private HorizontalPanel deleteButtonPanel;
    private HorizontalPanel deleteDataPanel;
    private HorizontalPanel backgroundPanel;
    private DecoratorPanel loggingPanel;
    private AbsolutePanel mainPanel;
    private Grid grid;
    private Label codeLabel;
    private TextBox codeTextBox;
    private Label damageLabel;
    private ListBox damageListBox;
    private Label xCoorLabel;
    private TextBox xCoorBox;
    private Label yCoorLabel;
    private TextBox yCoorBox;
    private Label turnsLabel;
    private ListBox turnsListBox;
    private Button enterButton;
    private Button deleteButton;
    private Button confirmDeleteButton;
    private Button closeDeleteButton;
    private Histogram histogram;
    private Grid icons;
    private Image airlock;
    private Image canteen;
    private Image control;
    private Image dorm;
    private Image gym;
    private Image med;
    private Image plain;
    private Image power;
    private Image sanitation;
    private Image storage;
    private int thumbDim;
    private VerticalPanel histogramPanel;

    /**
     * constructor for logging page.
     */
    public Logging() {

        makeEverything();

        histogram.update();

        enterButton.addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                try {
                    int code = Integer.parseInt(codeTextBox.getText());
                    int xcoor = Integer.parseInt(xCoorBox.getText());
                    int ycoor = Integer.parseInt(yCoorBox.getText());
                    checkCode(code);
                    checkCoords(xcoor, ycoor);
                    checkDuplicate(codeTextBox.getText());
                    Module newModule = new Module(codeTextBox.getText(),
                            damageListBox.getItemText(
                                    damageListBox.getSelectedIndex()),
                                    xCoorBox.getText(), yCoorBox.getText(),
                                    turnsListBox.getItemText(
                                            turnsListBox.getSelectedIndex()));
                    ModuleList.moduleList.add(newModule);
                    histogram.update(newModule.getType(), Histogram.Type.ADD);

                } catch (NumberFormatException e) {
                    Window.alert("Input Not A Number");
                } catch (Exception e) {
                    Window.alert(e.getMessage());
                }
                codeTextBox.setText("");
                damageListBox.setSelectedIndex(0);
                xCoorBox.setText("");
                yCoorBox.setText("");
                turnsListBox.setSelectedIndex(0);
            }
        });

        deleteButton.addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                populateModuleListBox();
                deletePanel.setVisible(true);
            }
        });

        confirmDeleteButton.addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                histogram.update(
                        ModuleList.moduleList.get(Integer.valueOf(
                                moduleListBox.getSelectedIndex())).getType(),
                                Histogram.Type.DELETE);

                ModuleList.moduleList.remove(
                        ModuleList.moduleList.get(Integer.valueOf(
                                moduleListBox.getSelectedIndex())));
                populateModuleListBox();
                locationInfoLabel.setText("Location:");
                damageInfoLabel.setText("Damage:");
                orientationInfoLabel.setText("Orientation:");
                typeInfoLabel.setText("Type:");

            }
        });

        closeDeleteButton.addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                locationInfoLabel.setText("Location:");
                damageInfoLabel.setText("Damage:");
                orientationInfoLabel.setText("Orientation:");
                typeInfoLabel.setText("Type:");
                deletePanel.setVisible(false);
            }
        });

        moduleListBox.addChangeHandler(new ChangeHandler() {
            public void onChange(final ChangeEvent event) {
                if (!ModuleList.moduleList.isEmpty()) {
                    Module mod =  ModuleList.moduleList.get(
                            ModuleList.getIndexByCode(
                                    moduleListBox.getItemText(
                                            moduleListBox.getSelectedIndex())));
                    locationInfoLabel.setText(
                            "Location: (" + mod.getXCoor() + ", "
                                    + mod.getYCoor() + ")");
                    damageInfoLabel.setText("Damage: " + mod.getDamage());
                    orientationInfoLabel.setText(
                            "Orientation: " + mod.getTurns() + " flips");
                    typeInfoLabel.setText("Type: " + mod.getType().toString());
                }
            }
        });

        assemblePanels();
    }

    /**
     *
     * @return mainPanel the panel containing everything
     * for module logging
     */

    public final Widget getLoggingPage() {
        return mainPanel;
    }

    /**
     *
     */

    private void makeEverything() {
        locationInfoLabel = new Label("Location:");
        damageInfoLabel = new Label("Damage:");
        orientationInfoLabel = new Label("Orientation:");
        typeInfoLabel = new Label("Type:");
        moduleListBox = new ListBox();
        deleteTitle = new Label("Delete Module");
        deleteLeftPanel = new VerticalPanel();
        deleteRightPanel = new VerticalPanel();
        deleteButtonPanel = new HorizontalPanel();
        deleteDataPanel = new HorizontalPanel();
        deletePanel = new VerticalPanel();
        backgroundPanel = new HorizontalPanel();
        loggingPanel = new DecoratorPanel();
        histogramPanel = new VerticalPanel();
        mainPanel = new AbsolutePanel();
        grid = new Grid(12, 1);
        codeLabel = new Label("Module Code");
        codeTextBox = new TextBox();
        damageLabel = new Label("Module Damage");
        damageListBox = new ListBox();
        xCoorLabel = new Label("X Coordinate");
        xCoorBox = new TextBox();
        yCoorLabel = new Label("Y Coordinate");
        yCoorBox = new TextBox();
        turnsLabel = new Label("Turns to Upright");
        turnsListBox = new ListBox();
        enterButton = new Button("Enter Module");
        deleteButton = new Button("Delete Entry");
        confirmDeleteButton = new Button("Delete Module");
        closeDeleteButton = new Button("Close");
        histogram = new Histogram();
        icons = new Grid(1, 10);
        icons.setCellPadding(0);
        thumbDim = 50;
        airlock = new Image("images/airlock.jpg");
        canteen = new Image("images/canteen.png");
        control = new Image("images/control.jpg");
        dorm = new Image("images/dorm.jpg");
        gym = new Image("images/gym.png");
        med = new Image("images/medical.png");
        plain = new Image("images/plain.png");
        power = new Image("images/power.jpg");
        sanitation = new Image("images/sanitation.jpg");
        storage = new Image("images/storage.jpg");
        airlock.setPixelSize(thumbDim, thumbDim);
        canteen.setPixelSize(thumbDim, thumbDim);
        control.setPixelSize(thumbDim, thumbDim);
        dorm.setPixelSize(thumbDim, thumbDim);
        gym.setPixelSize(thumbDim, thumbDim);
        med.setPixelSize(thumbDim, thumbDim);
        plain.setPixelSize(thumbDim, thumbDim);
        power.setPixelSize(thumbDim, thumbDim);
        sanitation.setPixelSize(thumbDim, thumbDim);
        storage.setPixelSize(thumbDim, thumbDim);
    }

    /**
     * Places panels within each other for overall structure.
     */
    private void assemblePanels() {
        moduleListBox.setWidth("200px");
        moduleListBox.setVisibleItemCount(10);

        deleteLeftPanel.add(moduleListBox);
        deleteLeftPanel.add(locationInfoLabel);
        deleteLeftPanel.add(damageInfoLabel);
        deleteLeftPanel.add(orientationInfoLabel);
        deleteLeftPanel.add(typeInfoLabel);

        //        deleteRightPanel.add(map);

        deleteDataPanel.add(deleteLeftPanel);
        deleteDataPanel.add(deleteRightPanel);

        deleteButtonPanel.add(confirmDeleteButton);
        deleteButtonPanel.add(closeDeleteButton);

        deletePanel.addStyleName("documentClass-delete");
        deletePanel.add(deleteTitle);
        deletePanel.add(deleteDataPanel);
        deletePanel.add(deleteButtonPanel);
        deletePanel.setVisible(false);

        damageListBox.addItem("Undamaged");
        damageListBox.addItem("Damaged");
        damageListBox.addItem("Unceratin");

        turnsListBox.addItem("0");
        turnsListBox.addItem("1");
        turnsListBox.addItem("2");

        grid.setWidget(0, 0, codeLabel);
        grid.setWidget(1, 0, codeTextBox);
        grid.setWidget(2, 0, damageLabel);
        grid.setWidget(3, 0, damageListBox);
        grid.setWidget(4, 0, xCoorLabel);
        grid.setWidget(5, 0, xCoorBox);
        grid.setWidget(6, 0, yCoorLabel);
        grid.setWidget(7, 0, yCoorBox);
        grid.setWidget(8, 0, turnsLabel);
        grid.setWidget(9, 0, turnsListBox);
        grid.setWidget(10, 0, enterButton);
        grid.setWidget(11, 0, deleteButton);
        
        icons.setWidget(0, 0, plain);
        icons.setWidget(0, 1, dorm);
        icons.setWidget(0, 2, sanitation);
        icons.setWidget(0, 3, storage);
        icons.setWidget(0, 4, gym);
        icons.setWidget(0, 5, canteen);
        icons.setWidget(0, 6, power);
        icons.setWidget(0, 7, control);
        icons.setWidget(0, 8, airlock);
        icons.setWidget(0, 9, med);

        loggingPanel.add(grid);
        histogramPanel.add(histogram.get());
        histogramPanel.add(icons);
        backgroundPanel.add(loggingPanel);
        backgroundPanel.add(histogramPanel);

        mainPanel.setPixelSize(800, 400);
        mainPanel.add(backgroundPanel);
        mainPanel.add(deletePanel, 50, 50);
    }

    /**
     *
     * @param x x coordinate
     * @param y y coordinate
     * @throws Exception invalid exception
     */
    private void checkCoords(final int x, final int y) throws Exception {
        if (x < 0 || x > 100) {
            throw new Exception("Invalid X Coordinate");
        }
        if (y < 0 || y > 50) {
            throw new Exception("Invalid Y Coordinate");
        }
    }

    /**
     *
     * @param code code of module
     * @throws Exception thrown if module code already entered
     */
    private void checkDuplicate(final String code) throws Exception {
        if (ModuleList.getIndexByCode(code) != -1) {
            throw new Exception("Module code already entered");
        }
    }

    /**
     *
     * @param code the module code being entered
     * @throws Exception invalid code exception
     */
    private void checkCode(final int code) throws Exception {
        boolean isValid = true;
        if (code < 1) {
            isValid = false;
        }
        if (code > 40 && code < 61) {
            isValid = false;
        }
        if (code > 80 && code < 91) {
            isValid = false;
        }
        if (code > 100 && code < 111) {
            isValid = false;
        }
        if (code > 120 && code < 131) {
            isValid = false;
        }
        if (code > 134 && code < 141) {
            isValid = false;
        }
        if (code > 144 && code < 151) {
            isValid = false;
        }
        if (code > 154 && code < 161) {
            isValid = false;
        }
        if (code > 164 && code < 171) {
            isValid = false;
        }
        if (code > 174 && code < 181) {
            isValid = false;
        }
        if (code > 184) {
            isValid = false;
        }
        if (!isValid) {
            throw new Exception("Invalid Module Code");
        }
    }

    /**
     *
     */
    private void populateModuleListBox() {
        moduleListBox.clear();
        for (Module mod : ModuleList.moduleList) {
            moduleListBox.addItem(mod.getCode());
        }
    }
}
