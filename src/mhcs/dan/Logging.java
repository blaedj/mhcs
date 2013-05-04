package mhcs.dan;

import mhcs.blaed.ModuleSerializer;
import mhcs.danielle.MinimumConfiguration;

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

    private boolean minConfig;

    // delete panel widgets
    private static Label locationInfoLabel;
    private static Label damageInfoLabel;
    private static Label orientationInfo;
    private static Label typeInfoLabel;
    private static ListBox moduleListBox;
    private static VerticalPanel deletePanel;
    private static VerticalPanel deleteLeftPanel;
    private static HorizontalPanel deleteButtonPanel;
    private static HorizontalPanel deleteDataPanel;
    private static Button confirmDelButton;
    private static Button closeDeleteButton;

    // Logging panel widgets
    private static Grid grid;
    private static TextBox codeTextBox;
    private static ListBox damageListBox;
    private static TextBox xCoorBox;
    private static TextBox yCoorBox;
    private static ListBox turnsListBox;
    private static Button enterButton;
    private static Button deleteButton;

    // Meta widgets
    private static HorizontalPanel backgroundPanel;
    private static DecoratorPanel loggingPanel;
    private static AbsolutePanel mainPanel;
    private static Histogram histogram;
    private static VerticalPanel rightPanel;

    // GPS widgets
    private static DecoratorPanel loadingWrapper;
    private static HorizontalPanel loadingPanel;
    private static ListBox testCaseList;
    private static Button addTestButton;
    private static Button saveButton;
    private static Button loadButton;

    /**
     * constructor for logging page.
     */
    public Logging() {

        makeEverything();

        enterButton.addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                if (!ModuleList.addModule(new Module(codeTextBox.getText(),
                        damageListBox.getItemText(damageListBox.getSelectedIndex()),
                        xCoorBox.getText(), yCoorBox.getText(),
                        turnsListBox.getItemText(turnsListBox.getSelectedIndex())))) {
                    Window.alert("Invalid input");
                }
                histogram.update();
                if (!minConfig) {
                    if (MinimumConfiguration.testMinConfig()) {
                        minConfig = true;
                        Window.alert("Minimum configuration possible");
                    }
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
                if (!MinimumConfiguration.testMinConfig()) {
                    Window.alert("Minimum configuration no longer possible.");
                    minConfig = false;
                }
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
                if (!ModuleList.isEmptyList()) {
                    Module mod =  ModuleList.getModuleByCode(moduleListBox.getItemText(moduleListBox.getSelectedIndex()));
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

        saveButton.addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                ModuleSerializer saver = new ModuleSerializer(ModuleList.get());
                saver.saveToLocal("saveList");
                histogram.update();
            }
        });

        loadButton.addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                ModuleSerializer loader = new ModuleSerializer();
                if (!loader.retrieveModuleList("saveList")) {
                    Window.alert("Failed to load modules");
                }
                histogram.update();
            }
        });

        addTestButton.addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                GPSDataReceiver.loadDataByNumber(testCaseList.getSelectedIndex() + 1);
                histogram.update();
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
        minConfig = false;
        locationInfoLabel = new Label("Location:");
        damageInfoLabel = new Label("Damage:");
        orientationInfo = new Label("Orientation:");
        typeInfoLabel = new Label("Type:");
        moduleListBox = new ListBox();
        deleteLeftPanel = new VerticalPanel();
        deleteButtonPanel = new HorizontalPanel();
        deleteDataPanel = new HorizontalPanel();
        deletePanel = new VerticalPanel();
        backgroundPanel = new HorizontalPanel();
        loggingPanel = new DecoratorPanel();
        mainPanel = new AbsolutePanel();
        grid = new Grid(12, 1);
        codeTextBox = new TextBox();
        damageListBox = new ListBox();
        xCoorBox = new TextBox();
        yCoorBox = new TextBox();
        turnsListBox = new ListBox();
        enterButton = new Button("Enter Module");
        deleteButton = new Button("Delete Entry");
        confirmDelButton = new Button("Delete Module");
        closeDeleteButton = new Button("Close");
        histogram = new Histogram();
        testCaseList = new ListBox();
        addTestButton = new Button("Get GPS Data");
        saveButton = new Button("Save Modules");
        loadButton = new Button("Load Modules");
        rightPanel = new VerticalPanel();
        loadingPanel = new HorizontalPanel();
        loadingWrapper = new DecoratorPanel();
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

        deleteDataPanel.add(deleteLeftPanel);

        deleteButtonPanel.add(confirmDelButton);
        deleteButtonPanel.add(closeDeleteButton);

        deletePanel.addStyleName("documentClass-delete");
        deletePanel.add(new Label("Delete Module"));
        deletePanel.add(deleteDataPanel);
        deletePanel.add(deleteButtonPanel);
        deletePanel.setVisible(false);

        damageListBox.addItem("undamaged");
        damageListBox.addItem("damaged");
        damageListBox.addItem("unceratin");

        turnsListBox.addItem("0");
        turnsListBox.addItem("1");
        turnsListBox.addItem("2");

        for (int i = 0; i < 10; i++) {
            testCaseList.addItem(String.valueOf(i + 1));
        }

        grid.setWidget(0, 0, new Label("Module Code"));
        grid.setWidget(1, 0, codeTextBox);
        grid.setWidget(2, 0, new Label("Module Damage"));
        grid.setWidget(3, 0, damageListBox);
        grid.setWidget(4, 0, new Label("X Coordinate"));
        grid.setWidget(5, 0, xCoorBox);
        grid.setWidget(6, 0, new Label("Y Coordinate"));
        grid.setWidget(7, 0, yCoorBox);
        grid.setWidget(8, 0, new Label("Turns to Upright"));
        grid.setWidget(9, 0, turnsListBox);
        grid.setWidget(10, 0, enterButton);
        grid.setWidget(11, 0, deleteButton);

        loadingPanel.add(testCaseList);
        loadingPanel.add(addTestButton);
        loadingPanel.add(saveButton);
        loadingPanel.add(loadButton);

        loggingPanel.add(grid);

        loadingWrapper.add(loadingPanel);

        rightPanel.add(histogram.get());
        rightPanel.add(loadingWrapper);

        backgroundPanel.add(loggingPanel);
        backgroundPanel.add(rightPanel);

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
        for (Module mod : ModuleList.getDamaged()) {
            moduleListBox.addItem(mod.getCode());
        }
    }
}
