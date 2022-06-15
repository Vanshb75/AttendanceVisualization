/*
*Team information:
* Eisa Taherkalateh (etaherk1)
* Aeric Plitz (aplitz)
* Raghav Aggarwal (raggar13)
* Vansh Bagaria (vbagari1)
* William Owen (wowen1)
*
* File description: This file plots the data of students as required in the project as a feature. 
*
*/



import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import java.util.Random;


public class plotData extends JFrame
{
    public plotData(Dates[] dates)
    {
        XYDataset dataset  = genData(dates);//Generates the data to be plotted.
        JFreeChart scatter = ChartFactory.createScatterPlot(
                "Attendance Chart",
                "", "Number of Students", dataset); //Creates ScatterPlot

        NumberAxis xAx = new NumberAxis();              //Formats X-Axis
        xAx.setTickUnit(new NumberTickUnit(10));
        xAx.setLabel("Percent of Attendance(100% = 75min)");

        XYPlot scatterplot = (XYPlot)scatter.getPlot(); //Formats the plot to change color and apply X-Axis.
        scatterplot.setBackgroundPaint(new Color(255,228,196));
        scatterplot.setDomainAxis(xAx);

        ChartPanel panel = new ChartPanel(scatter);  //puts scatterplot into a panel to be displayed.
        setContentPane(panel);
    }

    /**
     * Method: genData
     * takes an array of Dates then creates a series of data for each date.
     * returns the XYSeriesCollection once all the series have been created.
     * @param dates
     * @return dataset
     */
    public XYDataset genData(Dates[] dates)
    {
        XYSeriesCollection dataset= new XYSeriesCollection();

        double slot; //Slot on the graph
        XYSeries series;//Series data
        String seriesName;//Name of the current series


        int[] graphSlots = new int[11]; // 11 slots, {0%,10%,...,100%}

        for(int index = 0; index < graphSlots.length; index++)
        {
            graphSlots[index] = 0; //set count to 0 for each slot
        }

        for(int ix = 0; ix < dates.length; ix++)//This loop cycles through dates to create a series for each date.
        {
            for(int jy = 0; jy < dates[ix].attendance.size(); jy++) //This loop cycles through the attendance times to
            {                                                       //create the series data into an array of integers.
                slot = dates[ix].attendance.get(jy) / 75.0;  //Determines the slot each attendance time belongs to.
                if(slot >= 1)
                {
                    graphSlots[10]++;
                }
                else if(slot >= 0.9)
                {
                    graphSlots[9]++;
                }
                else if(slot >= 0.8)
                {
                    graphSlots[8]++;
                }
                else if(slot >= 0.7)
                {
                    graphSlots[7]++;
                }
                else if(slot >= 0.6)
                {
                    graphSlots[6]++;
                }
                else if(slot >= 0.5)
                {
                    graphSlots[5]++;
                }
                else if(slot >= 0.4)
                {
                    graphSlots[4]++;
                }
                else if(slot >= 0.3)
                {
                    graphSlots[3]++;
                }
                else if(slot >= 0.2)
                {
                    graphSlots[2]++;
                }
                else if(slot >= 0.1)
                {
                    graphSlots[1]++;
                }
                else if(slot >= 0)
                {
                    graphSlots[0]++;
                }
            }
            seriesName = dates[ix].date; //sets the series name to the date name at current index.
            series =  new XYSeries(seriesName);//creates the series.
            for(int kz = 0; kz < graphSlots.length; kz++)
            {
                series.add( ( kz * 10) , graphSlots[kz] ); //adds data to the series
            }

            dataset.addSeries(series); //add new series to the collection
        }

        return dataset; //returns collection of series to be plotted.

    }

   
}