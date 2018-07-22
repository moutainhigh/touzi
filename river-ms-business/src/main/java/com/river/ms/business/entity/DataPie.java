package com.river.ms.business.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DataPie {
	private BigDecimal cost;
	private Integer total;
	private BigDecimal irr;
	private String title;
	private List<String> legend;
	private List<String> series;
	private List<String> color;
	/**
	 * @return the cost
	 */
	public BigDecimal getCost() {
		return cost;
	}
	/**
	 * @param cost the cost to set
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
	/**
	 * @return the irr
	 */
	public BigDecimal getIrr() {
		return irr;
	}
	/**
	 * @param irr the irr to set
	 */
	public void setIrr(BigDecimal irr) {
		this.irr = irr;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the legend
	 */
	public List<String> getLegend() {
		return legend;
	}
	public void addLegend(String _legend){
		if(legend==null){
			legend=new ArrayList<String>();
		}
		legend.add(_legend);
	}
	/**
	 * @param legend the legend to set
	 */
	public void setLegend(List<String> legend) {
		this.legend = legend;
	}
	/**
	 * @return the series
	 */
	public List<String> getSeries() {
		return series;
	}
	/**
	 * 
	 * @param _serie
	 */
	public void addSerie(String _serie){
		if(series==null){
			series=new ArrayList<String>();
		}
		series.add(_serie);
	}
	/**
	 * @param series the series to set
	 */
	public void setSeries(List<String> series) {
		this.series = series;
	}
	/**
	 * @return the color
	 */
	public List<String> getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(List<String> color) {
		this.color = color;
	}
	
}
