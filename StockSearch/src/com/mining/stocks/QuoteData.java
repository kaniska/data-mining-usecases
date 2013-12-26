package com.mining.stocks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * <QuoteApiModel>
<Data>
<Status>SUCCESS</Status>
<Name>Google Inc</Name>
<Symbol>GOOG</Symbol>
<LastPrice>1035.72</LastPrice>
<Change>20.72</Change>
<ChangePercent>2.0413793103</ChangePercent>
<Timestamp>Tue Oct 29 15:59:50 UTC-04:00 2013</Timestamp>
<MarketCap>346020587640</MarketCap>
<Volume>77459</Volume>
<ChangeYTD>707.38</ChangeYTD>
<ChangePercentYTD>46.4163533037</ChangePercentYTD>
<High>1036.69</High>
<Low>1013.875</Low>
<Open>1019.02</Open>
</Data>
</QuoteApiModel>
 * 
 * @author Kaniska_Mandal
 *
 */

@XStreamAlias("Data")
public class QuoteData implements Serializable {

	private static final long serialVersionUID = 738834838444L;

	@XStreamAlias("Status")
	private String status;
	@XStreamAlias("Name")
	private String name;
	@XStreamAlias("Symbol")
	private String symbol;
	@XStreamAlias("LastPrice")
	private String lastPrice;
	@XStreamAlias("Change")
	private String change;
	@XStreamAlias("ChangePercent")
	private String changePercent;
	@XStreamAlias("Timestamp")
	private String timestamp;
	@XStreamAlias("Volume")
	private String volume;
	@XStreamAlias("High")
	private String high;
	@XStreamAlias("Low")
	private String low;
	@XStreamAlias("MarketCap")
	private String marketCap;
	@XStreamAlias("ChangeYTD")
	private String changeYTD;
	@XStreamAlias("ChangePercentYTD")
	private String changePercentYTD;
	@XStreamAlias("Open")
	private String open;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the lastPrice
	 */
	public String getLastPrice() {
		return lastPrice;
	}

	/**
	 * @param lastPrice the lastPrice to set
	 */
	public void setLastPrice(String lastPrice) {
		this.lastPrice = lastPrice;
	}

	/**
	 * @return the change
	 */
	public String getChange() {
		return change;
	}

	/**
	 * @param change the change to set
	 */
	public void setChange(String change) {
		this.change = change;
	}

	/**
	 * @return the changePercent
	 */
	public String getChangePercent() {
		return changePercent;
	}

	/**
	 * @param changePercent the changePercent to set
	 */
	public void setChangePercent(String changePercent) {
		this.changePercent = changePercent;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the volume
	 */
	public String getVolume() {
		return volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(String volume) {
		this.volume = volume;
	}

	/**
	 * @return the high
	 */
	public String getHigh() {
		return high;
	}

	/**
	 * @param high the high to set
	 */
	public void setHigh(String high) {
		this.high = high;
	}

	/**
	 * @return the low
	 */
	public String getLow() {
		return low;
	}

	/**
	 * @param low the low to set
	 */
	public void setLow(String low) {
		this.low = low;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private Object readResolve() {
		return this;
	}
}
