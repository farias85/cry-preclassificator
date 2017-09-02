/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com>.
 */
package Clases;

import java.awt.Color;
import java.awt.Dimension;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraficoOtro {

    private JFreeChart chart;
    CategoryPlot plot;
    ChartPanel chartPanel;
    DefaultCategoryDataset dataset;

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    public void mostrarGrid(boolean estadoGrid) {
        plot.setDomainGridlinesVisible(estadoGrid); //quitar las cuadriculas verticales 
        plot.setDomainGridlinePaint(new Color(229, 229, 115));// color de las cuadriculas verticales 

        plot.setRangeGridlinesVisible(estadoGrid);//quitar las cuadriculas horizontales 
        plot.setRangeGridlinePaint(new Color(229, 229, 115));//color de las cuadriculas horizontales
    }

    private void configurarEjeY() {
        // customise the range axis...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    }

    private void createChart(CategoryDataset datos) {

        chart = ChartFactory.createLineChart(null, // chart title
                " ", // x axis label
                "Amplitud[V] ", // y axis label
                datos, // data
                PlotOrientation.VERTICAL,
                false, // include legend
                false, // tooltips
                false // urls 
        );
    }

    public GraficoOtro() {
        dataset = new DefaultCategoryDataset();
        createChart(dataset);
        chartPanel = new ChartPanel(chart);

        chartPanel.setPreferredSize(new Dimension(830, 245));

        //Otras funcionalidades para personalizar el gráfico      
        chart.setBackgroundPaint(new Color(0, 204, 204));//se le da color de fondo al chart        

        plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.BLACK);// se le da color al fondo de la gráfica   

        configurarEjeY();
    }
}
