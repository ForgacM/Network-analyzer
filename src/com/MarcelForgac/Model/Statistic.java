package com.MarcelForgac.Model;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by marcelforgac on 22.3.15.
 */
public abstract class Statistic {

	private static LinkedHashMap<Integer, Integer> chart = new LinkedHashMap<>();

	public static void createChart() {
		chart.put(19, 0);
		chart.put(39, 0);
		chart.put(79, 0);
		chart.put(159, 0);
		chart.put(319, 0);
		chart.put(639, 0);
		chart.put(1279, 0);
		chart.put(2559, 0);
	}

	public static void setStatistics(Integer length) {
			for(Integer key : chart.keySet()) {
				if(length < key) {
					chart.put(key, (chart.get(key) + 1));
					break;
				}
			}
	}

	public static String getStatistic() {
		StringBuilder output = new StringBuilder();

		int previous = 0;
		for(Integer key : chart.keySet()) {
			output.append(previous).append(" - ").append(key).append(key > 79 ? "\t" : "\t\t").append(chart.get(key)).append("\n");
			previous = key + 1;
		}
		return output.toString();
	}
}
