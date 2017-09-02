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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Stroke;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GraficoChart {
    private final XYDataset dataset;
    private JFreeChart chart;
    ChartPanel chartPanel; 
    XYPlot plot;
    
    public XYDataset getDataset() {
        return dataset;
    }
    
    public JFreeChart getChart() {
        return chart;
    }
    
    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    private void createChart(XYDataset datos) {
        
        chart = ChartFactory.createXYLineChart(null, // chart title
                                               " ", // x axis label
                                               "Amplitud[V] ", // y axis label
                                                datos, // data
                                                PlotOrientation.VERTICAL,
                                                false, // include legend
                                                false, // tooltips
                                                false // urls 
                );      
    }
    
    public void agregarSerie(String nombre, Color color, int posSerie){
        XYSeries serie = new XYSeries(nombre);
        ((XYSeriesCollection) dataset).addSeries(serie);
        
        //Asigna el color a la serie
        plot = (XYPlot) chart.getPlot();
        XYItemRenderer xyrender =  plot.getRenderer();
        xyrender.setSeriesPaint(posSerie,color);    
    }
    
    public void mostrarGrid(boolean estadoGrid){
        plot.setDomainGridlinesVisible(estadoGrid); //quitar las cuadriculas verticales 
        plot.setDomainGridlinePaint(new Color(229, 229, 115));// color de las cuadriculas verticales 
        
        plot.setRangeGridlinesVisible(estadoGrid);//quitar las cuadriculas horizontales 
        plot.setRangeGridlinePaint(new Color(229, 229, 115));//color de las cuadriculas horizontales
    }
    
    // Para configurar el eje X de la gráfica
    private void configurarEjeX() {
        plot.getDomainAxis().setTickLabelsVisible(false); //oculta los datos del eje X 
        plot.getDomainAxis().setAxisLineVisible(false);//ocultar la recta del eje X
        plot.getDomainAxis().setTickMarkStroke((Stroke)new BasicStroke(2));//cambia e grosor del guion del eje X       
        plot.getDomainAxis().setTickMarkPaint(Color.YELLOW);//cambia el color del guion del eje X 
    }
    
    // Para configurar el eje Y de la gráfica
    private void configurarEjeY() {
        /*NumberAxis rAxis = new NumberAxis();       
        rAxis.setTickUnit(new NumberTickUnit(1)); */
        
        //plot.getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());//establece el rango de numeros enteros
        plot.getRangeAxis().setRange(-1, 1);//rango  a mostrar en el eje Y        
        plot.getRangeAxis().setLabelPaint(Color.YELLOW);//cambia el color al nombre del eje Y       
        
        
        plot.getRangeAxis().setTickLabelPaint(Color.YELLOW);//cambia el color a los datos del eje Y
        plot.getRangeAxis().setAxisLineVisible(false);//ocultar la recta del eje Y        
        plot.getRangeAxis().setTickMarkStroke((Stroke)new BasicStroke(2)); //cambia e grosor del guion del eje Y       
        plot.getRangeAxis().setTickMarkPaint(Color.YELLOW);//cambia el color del guion del eje Y
    }      
    
    public GraficoChart(){
        dataset = new XYSeriesCollection();
        createChart(dataset);            
        chartPanel = new ChartPanel(chart);
        
        chartPanel.setPreferredSize(new Dimension(830, 245));
                    
        //Otras funcionalidades para personalizar el gráfico      
        chart.setBackgroundPaint(new Color(0, 204, 204));//se le da color de fondo al chart        
        
        plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.BLACK);// se le da color al fondo de la gráfica         
        
        //configurar el grosor del borde interno del grafico              
        plot.setOutlineStroke((Stroke)new BasicStroke(3));         
        plot.setOutlinePaint(Color.RED); //cambiar el color del borde interno del grafico
        
        mostrarGrid(false);//quitar el grid 
        
        //Configuración de los ejes
        configurarEjeX();
        configurarEjeY(); 
    }
    
    /*public void dibujar(double[] datos, int posSerie){
        ((XYSeriesCollection) dataset).getSeries(posSerie).clear();
        for (int i = 0; i < datos.length; i++)
            ((XYSeriesCollection) dataset).getSeries(posSerie).add(i,datos[i]);                  
    }*/
    
    public void dibujarSenal(double[] datos, int posSerie){
        ((XYSeriesCollection) dataset).getSeries(posSerie).clear();
        int temp = 0;        
        for (int i = 0; i < datos.length; i+=13){  
            if (i < datos.length) {
                 ((XYSeriesCollection) dataset).getSeries(posSerie).add(temp,datos[i]);
            }
            temp++;
        }          
    }
    
    /*public int cantSeries(){
        int cant = 0;        
        cant = ((XYSeriesCollection) dataset).getSeries().size();        
        return cant;
    }*/
    
    public void addXY(double x, double y, int posSerie){
        ((XYSeriesCollection) dataset).getSeries(posSerie).add(x,y);
    }
    
    public void eliminarPtosSerie(int posSerie){
        ((XYSeriesCollection) dataset).getSeries(posSerie).clear();
    }
    
    public int sizeSerie(int posSerie){
        int sizeS;
        sizeS = ((XYSeriesCollection) dataset).getSeries(posSerie).getItems().size();
        return sizeS;
    }
}