package view.dashboard.menu;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;
import view.components.dashboard.CardBox;
import view.system.Form;
import view.utils.SystemForm;

import javax.swing.*;
import java.awt.*;

@SystemForm(name = "Dashboard", description = "dashboard form display some details")
public class Stat extends Form {

    public Stat() {init();}

    private void init() {
        setLayout(new MigLayout("wrap,fill", "[fill]", "[grow 0][fill]"));

        JPanel panel = new JPanel(new MigLayout("fillx", "[]push[][]"));
        JLabel title = new JLabel("Dashboard");

        title.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +3");

//        ToolBarSelection<ColorThemes> toolBarSelection = new ToolBarSelection<>(ColorThemes.values(), colorThemes -> {
//            if (DefaultChartTheme.setChartColors(colorThemes)) {
//                DefaultChartTheme.applyTheme(timeSeriesChart.getFreeChart());
//                DefaultChartTheme.applyTheme(candlestickChart.getFreeChart());
//                DefaultChartTheme.applyTheme(barChart.getFreeChart());
//                DefaultChartTheme.applyTheme(pieChart.getFreeChart());
//                DefaultChartTheme.applyTheme(spiderChart.getFreeChart());
//                cardBox.setCardIconColor(0, DefaultChartTheme.getColor(0));
//                cardBox.setCardIconColor(1, DefaultChartTheme.getColor(1));
//                cardBox.setCardIconColor(2, DefaultChartTheme.getColor(2));
//                cardBox.setCardIconColor(3, DefaultChartTheme.getColor(3));
//            }
//        });
        panel.add(title);
//        panel.add(toolBarSelection);
        add(panel);

        createCard();
    }
    private CardBox cardBox;

    private void createCard() {
        JPanel panel = new JPanel(new MigLayout("fillx", "[fill]"));
        cardBox = new CardBox();
        cardBox.addCardItem(createIcon("raven/modal/demo/icons/dashboard/customer.svg", new Color(255, 155, 145)), "Total Customer");
        cardBox.addCardItem(createIcon("raven/modal/demo/icons/dashboard/income.svg", new Color(255, 155, 145)), "Total Income");
        cardBox.addCardItem(createIcon("raven/modal/demo/icons/dashboard/expense.svg", new Color(255, 155, 145)), "Total Expense");
        cardBox.addCardItem(createIcon("raven/modal/demo/icons/dashboard/profit.svg", new Color(255, 155, 145)), "Last Profit");
        panel.add(cardBox);
        add(panel);
    }

    private Icon createIcon(String icon, Color color) {
        return new FlatSVGIcon(icon, 0.4f).setColorFilter(new FlatSVGIcon.ColorFilter(color1 -> color));
    }
}
