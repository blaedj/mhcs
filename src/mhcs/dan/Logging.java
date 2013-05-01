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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Daniel Hammond
 */
public class Logging {

    /* properties */

    private static Label locationInfoLabel;
    private static Label damageInfoLabel;
    private static Label orientationInfo;
    private static Label typeInfoLabel;
    private static ListBox moduleListBox;
    private static Label deleteTitle;
    private static VerticalPanel deletePanel;
    private static VerticalPanel deleteLeftPanel;
    private static VerticalPanel deleteRightPanel;
    private static HorizontalPanel deleteButtonPanel;
    private static HorizontalPanel deleteDataPanel;
    private static HorizontalPanel backgroundPanel;
    private static DecoratorPanel loggingPanel;
    private static AbsolutePanel mainPanel;
    private static Grid grid;
    private static Label codeLabel;
    private static TextBox codeTextBox;
    private static Label damageLabel;
    private static ListBox damageListBox;
    private static Label xCoorLabel;
    private static TextBox xCoorBox;
    private static Label yCoorLabel;
    private static TextBox yCoorBox;
    private static Label turnsLabel;
    private static ListBox turnsListBox;
    private static Button enterButton;
    private static Button deleteButton;
    private static Button confirmDelButton;
    private static Button closeDeleteButton;
    private static Histogram histogram;

    /**
     * constructor for logging page.
     */
    public Logging() {

        makeEverything();

        enterButton.addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                try {
                    if (!ModuleList.addModule(new Module(codeTextBox.getText(),
                            damageListBox.getItemText(
                                    damageListBox.getSelectedIndex()),
                                    xCoorBox.getText(), yCoorBox.getText(),
                                    turnsListBox.getItemText(
                                            turnsListBox.getSelectedIndex())))) {
                        Window.alert("Invalid input");
                    }
                    histogram.update();
                } catch (NumberFormatException e) {
                    Window.alert("Input Not A Number");
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

        confirmDelButton.addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                ModuleList.removeModule(
                        ModuleList.getModuleByCode(moduleListBox.getItemText(moduleListBox.getSelectedIndex())));
                populateModuleListBox();
                locationInfoLabel.setText("Location:");
                damageInfoLabel.setText("Damage:");
                orientationInfo.setText("Orientation:");
                typeInfoLabel.setText("Type:");
                histogram.update();
            }
        });

        closeDeleteButton.addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                locationInfoLabel.setText("Location:");
                damageInfoLabel.setText("Damage:");
                orientationInfo.setText("Orientation:");
                typeInfoLabel.setText("Type:");
                deletePanel.setVisible(false);
            }
        });

        moduleListBox.addChangeHandler(new ChangeHandler() {
            public void onChange(final ChangeEvent event) {
                ModuleList modList = ModuleList.get();
                if (!modList.isEmpty()) {
                    Module mod =  modList.get(
                            ModuleList.getIndexByCode(
                                    moduleListBox.getItemText(
                                            moduleListBox.getSelectedIndex())));
                    locationInfoLabel.setText(
                            "Location: (" + mod.getXCoor() + ", "
                                    + mod.getYCoor() + ")");
                    damageInfoLabel.setText("Damage: " + mod.getDamage());
                    orientationInfo.setText(
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
        orientationInfo = new Label("Orientation:");
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
        confirmDelButton = new Button("Delete Module");
        closeDeleteButton = new Button("Close");
        histogram = new Histogram();
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
        deleteLeftPanel.add(orientationInfo);
        deleteLeftPanel.add(typeInfoLabel);

        //        deleteRightPanel.add(map);

        deleteDataPanel.add(deleteLeftPanel);
        deleteDataPanel.add(deleteRightPanel);

        deleteButtonPanel.add(confirmDelButton);
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

        loggingPanel.add(grid);
        backgroundPanel.add(loggingPanel);
        backgroundPanel.add(histogram.get());

        mainPanel.setPixelSize(800, 400);
        mainPanel.add(backgroundPanel);
        mainPanel.add(deletePanel, 50, 50);
    }

    /**
     * Add the modules to the list box.
     */
    private void populateModuleListBox() {
        moduleListBox.clear();
        for (Module mod : ModuleList.get()) {
            moduleListBox.addItem(mod.getCode());
        }
    }
}
